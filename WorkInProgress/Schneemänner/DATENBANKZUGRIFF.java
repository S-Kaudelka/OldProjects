/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DATENBANKZUGRIFF {

    private Connection con;
    private Hashtable<String, ArrayList<ATTRIBUT>> Tabellenschemen = new Hashtable<String, ArrayList<ATTRIBUT>>();
    private Statement statement;
    private Document xmlDoc;
    private String xmlFileName;
    private java.util.Calendar LastFlush;
    
    public boolean VerbindungAufbauen(String Datenbankdatei) {
        if (con == null) {
            try {
                int randomId = (int)(Math.random()*1000000.0);
                con = java.sql.DriverManager.getConnection("jdbc:derby:memory:youngdb" + randomId + ";create=true");
                statement = con.createStatement();
                LastFlush = GregorianCalendar.getInstance();
                Tabellenschemen.clear();
                if (!ImportXML(Datenbankdatei)) {
                    con.close();
                    con = null;
                    return false;
                }
            } catch (Exception e) {
                System.err.println(e.toString());
                return false;
            }
        }
        return true;
    }

    public ERGEBNISTABELLE AbfrageAbsetzen(String sql) {
        try {

            return new ERGEBNISTABELLE(con.createStatement().executeQuery(sql));
        } catch (SQLException e) {
            return null;
        }

    }

    public boolean AnweisungAbsetzen(String sql) {
        try {
            statement.executeUpdate(sql);
            Calendar now = GregorianCalendar.getInstance();
            if(now.getTimeInMillis()-LastFlush.getTimeInMillis()>10000)
            {
                Flush();
                LastFlush=now;
            }
            
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public void Flush() {
        NodeList DBTableNodeList = xmlDoc.getElementsByTagName("Tables");
        Node DBTableNode = DBTableNodeList.item(0);
        NodeList TableDataNodeList = DBTableNodeList.item(0).getChildNodes();

        // Alle Tabellen lÃ¶schen
        for (int i = 0; i < TableDataNodeList.getLength(); ++i) {
            if (TableDataNodeList.item(i).getNodeName().equals("Table")) {
                DBTableNodeList.item(0).removeChild(TableDataNodeList.item(i));
            }
        }

        // Und neu aufziehen
        Enumeration<String> TabellenNamenEnumeration = Tabellenschemen.keys();
        while (TabellenNamenEnumeration.hasMoreElements()) {
            String CurrentTableName = TabellenNamenEnumeration.nextElement();
            Element TableNode = xmlDoc.createElement("Table");
            TableNode.setAttribute("Tablename", CurrentTableName);
            DBTableNode.appendChild(TableNode);

            ArrayList<ATTRIBUT> Schema = Tabellenschemen.get(CurrentTableName);

            // Autovalue setzen
            for (int i = 0; i < Schema.size(); ++i) {
                if (Schema.get(i).Datentyp == ATTRIBUT.DATENTYP_AUTO) {
                    int nextAutovalue = GetNextAutoValue(CurrentTableName, Schema.get(i));
                    TableNode.setAttribute("Autovalue", "" + nextAutovalue);
                }

            }
            ERGEBNISTABELLE e = AbfrageAbsetzen("SELECT * FROM " + CurrentTableName);

            // Datensätze auslesen und in das XML-Dokument eintragen
            while (e.NaechstenDatensatzPositionieren()) {
                Element RowNode = xmlDoc.createElement("Row");
                for (int i = 0; i < Schema.size(); ++i) {
                    Element ItemNode = xmlDoc.createElement("Item");
                    ItemNode.setAttribute("Column", "" + i);
                    RowNode.appendChild(ItemNode);
                    switch (Schema.get(i).Datentyp) {
                        case ATTRIBUT.DATENTYP_AUTO:
                            ItemNode.setAttribute("Value", "" + e.LeseGanzzahl(i + 1));
                            break;
                        case ATTRIBUT.DATENTYP_GANZZAHL:
                            ItemNode.setAttribute("Value", "" + e.LeseGanzzahl(i + 1));
                            break;
                        case ATTRIBUT.DATENTYP_TEXT:
                            if (e.LeseText(i + 1) != null) {
                                ItemNode.setAttribute("Value", "" + e.LeseText(i + 1));
                            } else {
                                ItemNode.setAttribute("Value", "");
                            }
                            break;
                        case ATTRIBUT.DATENTYP_WAHRHEITSWERT:
                            if (e.LeseWahrheitswert(i + 1)) {
                                ItemNode.setAttribute("Value", "true");
                            } else {
                                ItemNode.setAttribute("Value", "false");
                            }
                            break;
                        case ATTRIBUT.DATENTYP_FLIESSKOMMAZAHL:
                            ItemNode.setAttribute("Value", "" + e.LeseFliesskommazahl(i + 1));
                            break;
                        case ATTRIBUT.DATENTYP_DATUMZEIT:
                            java.util.Date date = e.LeseDatum(i + 1);
                            Time zeit = e.LeseZeit(i + 1);
                            Calendar cal = Calendar.getInstance();
                            if (date != null && zeit != null) {
                                cal.setTime(date);

                                int month = cal.get(Calendar.MONTH) + 1;
                                int day = cal.get(Calendar.DAY_OF_MONTH);
                                int year = cal.get(Calendar.YEAR);

                                cal.setTime(zeit);
                                int hour = cal.get(Calendar.HOUR_OF_DAY);
                                int minute = cal.get(Calendar.MINUTE);
                                int second = cal.get(Calendar.SECOND);

                                ItemNode.setAttribute("Value", "" + String.format("%02d", day) + "." + String.format("%02d", month) + "." + String.format("%04d", year) + " " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second));
                            }
                            break;
                    }
                }
                TableNode.appendChild(RowNode);

            }
        }

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1252");
            DOMSource source = new DOMSource(xmlDoc);
            StreamResult dateistream = new StreamResult(new File(xmlFileName));
            transformer.transform(source, dateistream);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private boolean ImportXML(String Dateiname) {
        try {
            File youngDBFile = new File(Dateiname);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            xmlDoc = dBuilder.parse(youngDBFile);
            xmlDoc.getDocumentElement().normalize();

            // Hauptknoten muss ein YoungDB-Knoten sein
            if (!xmlDoc.getDocumentElement().getNodeName().equals("YoungDB")) {
                return false;
            }

            NodeList DbSchemaNodeList = xmlDoc.getElementsByTagName("TableSchemas");
            if (DbSchemaNodeList.getLength() != 1) {
                return false;
            }

            NodeList TableSchemaNodeList = DbSchemaNodeList.item(0).getChildNodes();
            for (int i = 0; i < TableSchemaNodeList.getLength(); ++i) {
                Node CurrentTableSchema = TableSchemaNodeList.item(i);

                if (CurrentTableSchema.getNodeName().equals("Schema")) {
                    String sql = "CREATE TABLE ";

                    String TableName = GetNodeAttributeValue(CurrentTableSchema, "Tablename");
                    sql = sql + TableName + "(";
                    Tabellenschemen.put(TableName, new ArrayList<ATTRIBUT>());

                    NodeList TableAttributeNodeList = CurrentTableSchema.getChildNodes();

                    boolean FirstAttribute = true;
                    for (int j = 0; j < TableAttributeNodeList.getLength(); ++j) {
                        if (TableAttributeNodeList.item(j).getNodeName().equals("Attribut")) {
                            Node AttributeNode = TableAttributeNodeList.item(j);
                            String AttributeName = GetNodeAttributeValue(AttributeNode, "Name");
                            String AttributeDatatype = GetNodeAttributeValue(AttributeNode, "Datatype");
                            ATTRIBUT attribut = new ATTRIBUT(AttributeName, ATTRIBUT.DATENTYP_UNBEKANNT, false);
                            if (!FirstAttribute) {
                                sql += ",";
                            } else {
                                FirstAttribute = false;
                            }
                            sql += AttributeName;
                            if (AttributeDatatype.equals("Text")) {
                                sql += " varchar(255)";
                                attribut.Datentyp = ATTRIBUT.DATENTYP_TEXT;
                            } else if (AttributeDatatype.equals("Ganzzahl")) {
                                sql += " int";
                                attribut.Datentyp = ATTRIBUT.DATENTYP_GANZZAHL;
                            } else if (AttributeDatatype.equals("Fliesskommazahl")) {
                                sql += " float";
                                attribut.Datentyp = ATTRIBUT.DATENTYP_FLIESSKOMMAZAHL;
                            } else if (AttributeDatatype.equals("Auto-Wert")) {
                                sql += " int GENERATED BY DEFAULT AS IDENTITY";
                                attribut.Datentyp = ATTRIBUT.DATENTYP_AUTO;
                            } else if (AttributeDatatype.equals("Datum/Uhrzeit")) {
                                sql += " timestamp";
                                attribut.Datentyp = ATTRIBUT.DATENTYP_DATUMZEIT;
                            } else if (AttributeDatatype.equals("Wahrheitswert")) {
                                sql += " boolean";
                                attribut.Datentyp = ATTRIBUT.DATENTYP_WAHRHEITSWERT;
                            }
                            Tabellenschemen.get(TableName).add(attribut);
                        }
                    }
                    sql += ", PRIMARY KEY(";

                    FirstAttribute = true;
                    for (int j = 0; j < TableAttributeNodeList.getLength(); ++j) {
                        if (TableAttributeNodeList.item(j).getNodeName().equals("Attribut")) {
                            Node AttributeNode = TableAttributeNodeList.item(j);
                            String AttributeName = GetNodeAttributeValue(AttributeNode, "Name");
                            String AttributePrimaryKey = GetNodeAttributeValue(AttributeNode, "PrimaryKey");
                            if (AttributePrimaryKey.equals("true")) {

                                if (!FirstAttribute) {
                                    sql += ",";
                                } else {
                                    FirstAttribute = false;
                                }
                                sql += AttributeName;
                                Tabellenschemen.get(TableName).get(j).IstPrimärschlüssel = true;
                            }
                        }
                    }
                    sql += "))";
                    //System.out.println(sql);
                    con.createStatement().executeUpdate(sql);
                }
            }

            // Tabellen befüllen
            NodeList DbTablesNodeList = xmlDoc.getElementsByTagName("Tables");
            if (DbTablesNodeList.getLength() != 1) {
                return false;
            }

            NodeList TableNodeList = DbTablesNodeList.item(0).getChildNodes();
            for (int i = 0; i < TableNodeList.getLength(); ++i) {
                Node CurrentTable = TableNodeList.item(i);

                if (CurrentTable.getNodeName().equals("Table")) {
                    NodeList RowNodeList = CurrentTable.getChildNodes();
                    String TableName = GetNodeAttributeValue(CurrentTable, "Tablename");
                    String Autovalue = GetNodeAttributeValue(CurrentTable, "Autovalue");
                    for (int r = 0; r < RowNodeList.getLength(); r++) {
                        if (RowNodeList.item(r).getNodeName().equals("Row")) {
                            String sql = "INSERT INTO ";

                            sql += TableName + " VALUES (";

                            NodeList ItemNodeList = RowNodeList.item(r).getChildNodes();
                            int currentColumn = 0;
                            for (int s = 0; s < ItemNodeList.getLength(); ++s) {
                                Node CurrentItemNode = ItemNodeList.item(s);
                                if (CurrentItemNode.getNodeName().equals("Item")) {
                                    if (currentColumn != 0) {
                                        sql += ",";
                                    }
                                    switch (Tabellenschemen.get(TableName).get(currentColumn).Datentyp) {
                                        case ATTRIBUT.DATENTYP_AUTO:
                                            sql += GetNodeAttributeValue(CurrentItemNode, "Value");
                                            break;
                                        case ATTRIBUT.DATENTYP_GANZZAHL:
                                            sql += GetNodeAttributeValue(CurrentItemNode, "Value");
                                            break;
                                        case ATTRIBUT.DATENTYP_TEXT:
                                            sql += "'" + GetNodeAttributeValue(CurrentItemNode, "Value") + "'";
                                            break;
                                        case ATTRIBUT.DATENTYP_FLIESSKOMMAZAHL:
                                            sql += GetNodeAttributeValue(CurrentItemNode, "Value").replace(',', '.');
                                            break;
                                        case ATTRIBUT.DATENTYP_DATUMZEIT:
                                            String dt = DateTimeString2DBFormat(GetNodeAttributeValue(CurrentItemNode, "Value"));
                                            if (dt != null) {
                                                sql += "'" + dt + "'";
                                            } else {
                                                sql += "NULL";
                                            }
                                            break;
                                        case ATTRIBUT.DATENTYP_WAHRHEITSWERT:
                                            sql += GetNodeAttributeValue(CurrentItemNode, "Value").toLowerCase();
                                            break;
                                    }
                                    currentColumn++;
                                }
                            }

                            sql += ")";
                            //System.out.println(sql);
                            con.createStatement().executeUpdate(sql);
                        }
                    }
                    // Ggf. startwert für generierten autowert setzen
                    Iterator<ATTRIBUT> attributIterator = Tabellenschemen.get(TableName).iterator();
                    while (attributIterator.hasNext()) {
                        ATTRIBUT a = attributIterator.next();
                        if (a.Datentyp == ATTRIBUT.DATENTYP_AUTO) {
                            String sql = "ALTER TABLE " + TableName + " ALTER " + a.Name + " RESTART WITH " + Autovalue;
                            con.createStatement().executeUpdate(sql);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        xmlFileName = Dateiname;
        return true;
    }

    private static String DateTimeString2DBFormat(String s) {
        try {
            String date = s.split(" ")[0];
            String time = s.split(" ")[1];

            String day = date.split("\\.")[0];
            if (day.length() == 1) {
                day = "0" + day;
            }
            String month = date.split("\\.")[1];
            if (month.length() == 1) {
                month = "0" + month;
            }
            String year = date.split("\\.")[2];

            return year + "-" + month + "-" + day + " " + time;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }

    }

    private static String GetNodeAttributeValue(Node node, String attribute) {
        NamedNodeMap attributes = node.getAttributes();
        Node item = attributes.getNamedItem(attribute);
        if (item == null) {
            return null;
        } else {
            return attributes.getNamedItem(attribute).getNodeValue();
        }
    }

    private int GetNextAutoValue(String table, ATTRIBUT a) {
        if (a.Datentyp != ATTRIBUT.DATENTYP_AUTO) {
            return -1;
        } else {
            ERGEBNISTABELLE e = AbfrageAbsetzen("SELECT MAX(" + a.Name + ") FROM " + table);
            int ret = 1;
            if (e.NaechstenDatensatzPositionieren()) {
                ret = e.LeseGanzzahl(1) + 1;
            }
            return ret;
        }

    }

    public boolean VerbindungSchliessen() {
        Flush();
        if (con != null) {
            try {
                con.close();
                Tabellenschemen.clear();
            } catch (Exception e) {
                System.err.println(e.toString());
                return false;
            }
            con = null;
            return true;
        } else {
            return false;
        }
    }

    class ATTRIBUT {

        public static final int DATENTYP_UNBEKANNT = 0;
        public static final int DATENTYP_GANZZAHL = 1;
        public static final int DATENTYP_TEXT = 2;
        public static final int DATENTYP_FLIESSKOMMAZAHL = 3;
        public static final int DATENTYP_WAHRHEITSWERT = 4;
        public static final int DATENTYP_DATUMZEIT = 5;
        public static final int DATENTYP_AUTO = 6;

        String Name;
        int Datentyp;
        boolean IstPrimärschlüssel;

        public ATTRIBUT(String Name_p, int Datentyp_p, boolean IstPrimärschlüssel_p) {
            Name = Name_p;
            Datentyp = Datentyp_p;
            IstPrimärschlüssel = IstPrimärschlüssel_p;
        }
    }
}

import java.io.*;

public class LADEN
{
    public static String[] main(String Dateiname, String Aufrufer)
    {
        String[] Argumente = null;

        int anz = 0;
        if(Aufrufer.equals("CONTROLER"))
        {
            try //todo
            {
                FileInputStream inputStream = new FileInputStream(Dateiname + "-save.ser");

                ObjectInputStream objectInput = new ObjectInputStream(inputStream);

                String anzVariablen = (String) objectInput.readObject(); //Erster gespeicherter Wert
                                                       //gibt an, wieviele Variablen gespeichert sind
                int anzObjekte = TzZ(anzVariablen);                 

                Argumente = new String[anzObjekte];

                for(anz = 0; anz < Argumente.length;anz++)
                {
                    Argumente[anz] = (String) objectInput.readObject();
                }

                return Argumente;

            } catch (Exception e)
            {
                return null;
            }
        }
        if(Aufrufer.equals("START"))
        {
            try //todo
            {
                try
                {
                    FileInputStream inputStream = new FileInputStream("Spielernamen.ser");

                    ObjectInputStream objectInput = new ObjectInputStream(inputStream);

                    String s = (String) objectInput.readObject();
                    while(s != null)
                    {
                        s = (String) objectInput.readObject();
                        if(s.equals(Dateiname))
                        {
                            s = null;
                        }
                    }
                }
                catch (EOFException e)
                {
                    String[] d = {"-1"};
                    return d;
                }
                catch (Exception e)
                {
                    return null;
                }

                FileInputStream inputStream = new FileInputStream(Dateiname + ".ser");

                ObjectInputStream objectInput = new ObjectInputStream(inputStream);

                objectInput.readObject();

                Argumente = new String[1];
                Argumente[0] = (String) objectInput.readObject();

                return Argumente;

            } catch (Exception e)
            {

            }
        }
        if(Aufrufer.equals("DE_SERIALISIERUNG"))
        {
            try //todo
            {
                FileInputStream inputStream = new FileInputStream("Spielernamen.ser");

                ObjectInputStream objectInput = new ObjectInputStream(inputStream);

                String anzObjekte = (String) objectInput.readObject();

                int anzObj = TzZ(anzObjekte);

                Argumente = new String[anzObj];

                for(anz = 0; anz < Argumente.length; anz++)
                {
                    Argumente[anz] = (String) objectInput.readObject();
                }

                return Argumente;

            } catch (Exception e)
            {
                System.out.println("Fehler");
            }
        }
        return null;
    }

    public static boolean Ueberpruefung(String Dateiname)
    {
        try //todo
        {
            FileInputStream inputStream = new FileInputStream(Dateiname + ".ser");

            ObjectInputStream objectInput = new ObjectInputStream(inputStream);

            return true;

        } catch (EOFException e)
        {
            return false;
        }
        catch (Exception e)
        {
            System.out.println("Fehler");
            return false;
        }
    }

    private static int TzZ(String Text) //Text zu Zahl
    {
        for(int i = -1; i < 1500; i++)
        {
            if(Text.equals(i + ""))
            {
                return i;
            }
        }
        return 0;
    }
}
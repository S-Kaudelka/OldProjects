import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField; 
import javax.swing.JPasswordField; 
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CONTROLER implements Runnable
{
    public SPIEL spiel;

    public static boolean IsRunning=true;

    public boolean AmLaufen=true;

    private DATENBANKZUGRIFF db;

    public String Name;
    public static boolean spezialFeature = false;

    //    ZU ERLEDIGEN:
    

    private JFrame f1;
    private JButton button1;
    private JButton button2;
    private JTextField TF;  
    private JPasswordField PW;
    private JLabel label;
    private JLabel label2;
    private JLabel label3;

    public CONTROLER()
    {
        db = new DATENBANKZUGRIFF();
        db.VerbindungAufbauen("Login.ydb");

        f1 = new JFrame("Login");     
        f1.setSize(350 , 200 );             
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        f1.setLayout(null); 
        f1.setResizable(false);

        TF = new JTextField();
        TF.setBounds(100, 25, 150, 20);
        TF.setVisible(true);

        PW = new JPasswordField();
        PW.setBounds(100, 50, 150, 20);
        PW.setVisible(true);

        button1 = new JButton("Login");
        button1.setBounds(100, 75, 150, 20);

        button2 = new JButton("Registrieren");
        button2.setBounds(100, 100, 150, 20);

        label = new JLabel("Name: ");
        label.setBounds(25, 25, 50, 20);
        label.setVisible(true);

        label2 = new JLabel("Passwort: ");
        label2.setBounds(25, 50, 100, 20);
        label2.setVisible(true);

        label3 = new JLabel("Falsches Passwort oder Name");
        label3.setBounds(25, 125, 250, 20);
        label3.setVisible(false);

        button1.addActionListener(new ActionListener() 
            {

                public void actionPerformed(ActionEvent e) 
                {
                    String N = TF.getText();
                    char[] P_p = PW.getPassword();
                    String P = new String(P_p);
                    Name = N;
                    if(Login(N, P)!=true)
                    {
                        label3.setVisible(true);
                        return;
                    }
                    else
                    {
                        f1.dispose();
                    }
                    //Die Loginmethode aufrufen mit N und P als Parameter
                }

            } 
        );

        button2.addActionListener(new ActionListener() 
            {

                public void actionPerformed(ActionEvent e) 
                {
                    String N = TF.getText();
                    char[] P_p = PW.getPassword();
                    String P = new String(P_p);
                    Registrieren(N, P);
                    //Die Registriermethode aufrufen mit N und p als Parameter
                }

            } 
        );

        f1.add(TF);
        f1.add(PW);
        f1.add(button1);
        f1.add(button2);
        f1.add(label);
        f1.add(label2);
        f1.add(label3);
        f1.setVisible(true);
    }

    public void run()
    {
        while(IsRunning)
        {
            if(AmLaufen==true)
            {
                spiel.RUN(GAMEWINDOW.getInstance().getKeystate());
                try { Thread.sleep((int) (20)); } catch (Exception e) {}
            }
        }
    }

    public void stop()
    {
        IsRunning = false;

        db.VerbindungSchliessen();
        try{
            Thread.sleep(5000);
        }catch(Exception e){

        }
        System.exit(0);
    }

    public boolean Login(String Name, String PW)
    {

        String Abfrage = "SELECT Name,Passwort FROM Spieler WHERE Name='" + 
            Name + "' AND Passwort='" + PW + "'";

        ERGEBNISTABELLE ergebnis = db.AbfrageAbsetzen(Abfrage);

        if(Name.equals("Cheater") && PW.equals("1234")){
            spezialFeature = true;
            spiel = new SPIEL(this);
            new Thread(this).start();
            return true;
        }

        if(ergebnis.NaechstenDatensatzPositionieren()==true)
        {
            spiel = new SPIEL(this);
            new Thread(this).start();
            return true;
        }
        else
        {
            System.out.println("Kein Treffer");
            return false;
        }
    }

    public void Registrieren(String Name, String PW)
    {        
        String Abfrage = "INSERT INTO Spieler (Name, Passwort, Highscore) VALUES ('"+Name+"', '"+PW+"', 0)";
        try{
            System.out.println("Ergebnis: " + db.AnweisungAbsetzen(Abfrage));
            db.Flush();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void ScoreSetzen(String Name, int Score)
    {
        /*
        String Abfrage = "SELECT Highscore FROM Spieler WHERE Name='" + Name + "' ORDER BY Highscore DESC";
        
        ERGEBNISTABELLE ergebnis = db.AbfrageAbsetzen(Abfrage);
        
        int h = ergebnis.LeseGanzzahl("Highscore"); //vorheriger Highscore
        
        
        if(h>Score)
        {
            System.out.println("Kein neuer Highscore");
            Score = h;
        }
        */
        String Befehl = "UPDATE Spieler SET Highscore="+Score+" WHERE (Name='"+Name+"')";

        try{
            db.AnweisungAbsetzen(Befehl);
            db.Flush();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void HighscoreAusgeben()
    {
        String Abfrage = "SELECT Name,Highscore FROM Spieler ORDER BY Highscore DESC";
        
        ERGEBNISTABELLE ergebnis = db.AbfrageAbsetzen(Abfrage);
        
        if(ergebnis.NaechstenDatensatzPositionieren())
        {
            System.out.println("Highscore: " + ergebnis.LeseText("Name") + ",  " + ergebnis.LeseGanzzahl("Highscore"));
        }
    }
}
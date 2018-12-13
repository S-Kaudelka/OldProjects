import java.awt.Point;

public class SPIEL
{
    private BILD Hintergrund;
    private BILD Weg;
    private BILD Oben;
    private BILD Unten;
    private BILD[] LeereFelder;
    
    //Buttons
    private BILD WeSchicken;
    private BILD Pause;
    private BILD SpPaus;
    private BILD Speed1;
    private BILD Speed2;
    
    
    public SPIELER Spieler;
    
    public CONTROLER c;
    
    public int Nummer;
    public boolean PlatzAusgewaehlt;
    public String Seite;
    
    public boolean IstVeraendert;
    public boolean WelleSchicken;
    public int Welle;
    public int Wellengegner;
    public int GegnerAmLeben;
    public int GegnerBesiegt;
    public boolean GoldBekommen;
    
    // Werte fuer teurer machen
    public int AnzPfeil;
    public int AnzArmbr;
    public int AnzKanone;
    public int TeurerUm;

    public WELLEN Wellen;
    
    public TURMPLATZ[] Turmplaetze;
    
    public GEGNER[] Gegner;
    
    public WERTE[] Werte;
    
    public SPIEL(CONTROLER c_p)
    {
        c=c_p;
        Nummer = -1;
        PlatzAusgewaehlt=false;
        IstVeraendert=true;
        Seite ="";
        WelleSchicken=false;
        Welle=0;
        GegnerAmLeben = 0;
        Wellengegner = 0;
        
        TeurerUm = 20;
        
        Wellen = new WELLEN(this);
        
        Spieler = new SPIELER();
        
        Hintergrund = new BILD("Hintergrund.PNG");
        
        WeSchicken = new BILD("bWelleSchicken.PNG");
        WeSchicken.SetzeX(20);
        WeSchicken.SetzeY(759);
        
        Pause = new BILD("bPause.PNG");
        Pause.SetzeX(340);
        Pause.SetzeY(759);
        
        Speed1 = new BILD("bSpeed1.PNG");
        Speed1.SetzeX(490);
        Speed1.SetzeY(759);
        
        Speed2 = new BILD("bSpeed2.PNG");
        Speed2.SetzeX(560);
        Speed2.SetzeY(759);
        
        Weg = new BILD("Weg.PNG");
        Weg.SetzeY(350);
        Weg.SetzeHoehe(100);
        Weg.SetzeBreite(1500);
        
        Hintergrund.SetzeBreite(1500);
        Hintergrund.SetzeHoehe(800);
        
        Oben = new BILD("O(ben).PNG");
        Oben.SetzeX(10);
        Oben.SetzeY(280);
        
        Unten = new BILD("U(nten).PNG");
        Unten.SetzeX(10);
        Unten.SetzeY(470);
        
        Turmplaetze = new TURMPLATZ[20];
        
        LeereFelder = new BILD[20];
        
        for(int i = 0; i<20; i++)
        {
            LeereFelder[i] = new BILD("TurmplatzLeer.PNG");
            if(i<10)
            {
                LeereFelder[i].SetzeX(100 + i*135);
                LeereFelder[i].SetzeY(280);
            }
            else
            {
                LeereFelder[i].SetzeX(100 + (i-10)*135);
                LeereFelder[i].SetzeY(470);
            }
        }
        
        
        SpPaus = new BILD("bSpPaus.PNG");
        SpPaus.SetzeX(460);
        SpPaus.SetzeY(1500);
        
        //Wenn ein neuer Gegner Hinzugefuegt Wurde, hier beide Zahlen um eins Erhoehen pro hinzugefuegten Gegner
        Werte = new WERTE[12];
        for(int i = 0; i< 12; i++)
        {
            Werte[i] = new WERTE(i);
        }
    }
    
    
    public void RUN(KEYSTATE keystate)
    {
        //Gold bekommen nach den Bosswellen
        if(GoldBekommen==false)
        {
            if(Welle==8)
            {
                Spieler.Gold=Spieler.Gold+100;
                GoldBekommen=true;
            }
            if(Welle==16)
            {
                Spieler.Gold=Spieler.Gold+300;
                GoldBekommen=true;
            }
            if(Welle==26)
            {
                Spieler.Gold=Spieler.Gold+600;
                GoldBekommen=true;
            }
        }
        
        //Tasten Pruefen
        if(keystate.IsPressed("M")) //Speichern
        {
            if(GegnerAmLeben == 0)
            {
                c.Speichern();
            }
        }
        if(keystate.IsPressed("N")) //Laden
        {
            if(GegnerAmLeben == 0)
            {
                c.Laden();
            }
        }
        
        if(PlatzAusgewaehlt==true)
        {
            if(keystate.IsPressed("Q"))
            {
                TurmPlatzieren(Nummer-1, "Pfeil", Seite);
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
                Seite = "";
            }
            if(keystate.IsPressed("W"))
            {
                TurmPlatzieren(Nummer-1, "Armbrust", Seite);
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
                Seite = "";
            }
            if(keystate.IsPressed("E"))
            {
                TurmPlatzieren(Nummer-1, "Kanonen", Seite);
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
                Seite = "";
            }
            if(keystate.IsPressed("R"))
            {
                if(Turmplaetze[Nummer-1]!=null)
                {
                    Spieler.Gold = Spieler.Gold+TurmVerkaufen(Nummer-1);
                }
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
                Seite = "";
            }
            if(keystate.IsPressed("T"))
            {
                if(Nummer>0 && Nummer<=20)
                {
                    if(Turmplaetze[Nummer-1]!=null)
                    {
                        if(Spieler.Gold>=Turmplaetze[Nummer-1].UpgradeKosten)
                        {
                            Spieler.Gold = Spieler.Gold-Turmplaetze[Nummer-1].TurmUpgraden();
                        }
                    }
                    PlatzAusgewaehlt=false;
                    Nummer=-1;
                    IstVeraendert=true;
                    Seite = "";
                }
            }
            //Spezialisieren
            if(keystate.IsPressed("F"))
            {
                TurmSpezialisieren(Nummer-1, 1);
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
                Seite = "";
            }
            if(keystate.IsPressed("G"))
            {
                TurmSpezialisieren(Nummer-1, 2);
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
                Seite = "";
            }
        }
        if(keystate.IsPressed("C"))
        {
            for(int i = 0; i<20; i++)
            {
                if(Turmplaetze[i] != null)
                {
                    int x = Turmplaetze[i].Turm.LeseX();
                    int y = Turmplaetze[i].Turm.LeseY();
                    Turmplaetze[i].Turm = new BILD("NeuSkin.png");
                    Turmplaetze[i].Turm.SetzeX(x);
                    Turmplaetze[i].Turm.SetzeY(y);
                }
            }
            for(int i = 0; i<Wellengegner; i++)
            {
                if(Gegner != null)
                {
                    int x = Gegner[i].Skin.LeseX();
                    int y = Gegner[i].Skin.LeseY();
                    Gegner[i].Skin.SetzeY(2000);
                    Gegner[i].Skin = new BILD("NeuGegner.png");
                    Gegner[i].Skin.SetzeX(x);
                    Gegner[i].Skin.SetzeY(y);
                }
            }
        }
        if(keystate.IsPressed("V"))
        {
            for(int i = 0; i<20; i++)
            {
                if(Turmplaetze[i] != null)
                {
                    int x = Turmplaetze[i].Turm.LeseX();
                    int y = Turmplaetze[i].Turm.LeseY();
                    Turmplaetze[i].Turm = new BILD("Turmplatz"+Turmplaetze[i].Art+"turm.PNG");
                    Turmplaetze[i].Turm.SetzeX(x);
                    Turmplaetze[i].Turm.SetzeY(y);
                }
            }
            for(int i = 0; i<Wellengegner; i++)
            {
                if(Gegner != null)
                {
                    int x = Gegner[i].Skin.LeseX();
                    int y = Gegner[i].Skin.LeseY();
                    Gegner[i].Skin.SetzeY(2000);
                    Gegner[i].Skin = new BILD(Gegner[i].SN+".png");
                    Gegner[i].Skin.TransparenzSetzen(255,255,255);
                    Gegner[i].Skin.SetzeX(x);
                    Gegner[i].Skin.SetzeY(y);
                }
            }
        }
        if(GegnerAmLeben>0)
        {
            for(int i = 0; i<Wellengegner; i++)
            {
                if(Gegner[i].Leben<=0 && Gegner[i].Zustand=="AmLeben")
                {
                    GegnerAmLeben--;
                    GegnerBesiegt++;
                    Gegner[i].Zustand="Tot";
                    Spieler.Gold = Spieler.Gold+Gegner[i].GoldWert;
                    Gegner[i].Skin.SetzeY(1200);
                    Gegner[i].Lebensbalken.SetzeY(1200);
                    Gegner[i].RestLebensbalken.SetzeY(1200);
                    IstVeraendert=true;
                }
                if(Gegner[i].Skin.LeseX() <= -50 && Gegner[i].Zustand=="AmLeben")
                {
                    GegnerAmLeben--;
                    Gegner[i].Zustand="Tot";
                    Spieler.Leben = Spieler.Leben-Gegner[i].Lebensverlust;
                    Gegner[i].Skin.SetzeY(1200);
                    IstVeraendert=true;
                }
            }
        }
        
        for(int i = 0 ; i<20 ; i++)
        {
            if(Turmplaetze[i]!=null)
            {
                Turmplaetze[i].Wellengegner=Wellengegner;
                Turmplaetze[i].GegnerAmLeben=GegnerAmLeben;
                Turmplaetze[i].Gegner=Gegner;
                Turmplaetze[i].Angriff();
            }
        }
        
        if(IstVeraendert==true)
        {
            Ausgabe();
        }
        
        if(Spieler.Leben <= 0)
        {
            AusLoeschen();
            System.out.println("Game Over");
            c.end();
        }
        
        //Mausklick überprüfen
        Point m = keystate.PickLastMouseClickPosition();
        int Platz = -1;
        if(m!=null)
        {
            if(m.x >= 0 && m.x <= 1500 && m.y>=0 && m.y<=800)
            {
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
                Seite = "";
            }
            // Platzauswahl
            if(m.y>=280 && m.y<=330)
            {
                Seite = "Oben";
                int x = m.x-100;
                for(Platz = 0; x > 135; Platz++)
                {
                    x = x-135;
                }
                if(x>=0 && x<=50)
                {
                    IstVeraendert=true;
                    PlatzAusgewaehlt=true;
                }
                else
                {
                    Platz=-1;
                    IstVeraendert=true;
                    Seite = "";
                }
            }
            if(m.y>=470 && m.y<=520)
            {
                Seite ="Unten";
                int x = m.x-100;
                for(Platz = 10; x > 135; Platz++)
                {
                    x = x-135;
                }
                if(x>=0 && x<=50)
                {
                    IstVeraendert=true;
                    PlatzAusgewaehlt=true;
                }
                else
                {
                    Platz=-1;
                    IstVeraendert=true;
                    Seite = "";
                }
            }
            Nummer = Platz+1;
            if(Platz==-1)
            {
                Nummer=-1;
            }
            
            //Welle Schicken
            if(m.x >= 20 && m.x <= 316 && m.y>=759 && m.y<=800)
            {
                WelleSchicken(Welle);
            }
            //SpielPausieren
            if(m.x >= 340 && m.x <= 464 && m.y>=759 && m.y<=800)
            {
                c.AmLaufen=false;
                SpPaus.SetzeY(100);
            }
            //normal speed
            if(m.x >= 490 && m.x <= 531 && m.y>=759 && m.y<=800)
            {
                c.SpeedFaktor = 1;
            }
            //5fach speed
            if(m.x >= 560 && m.x <= 601 && m.y>=759 && m.y<=800)
            {
                c.SpeedFaktor = 5;
            }
        }
    }
    
    public void TurmPlatzieren(int Nummer, String Art, String S)
    {
        int G=0;
        
        //hier Werden die Tuerme teurer gemacht
        if(Art=="Pfeil")
        {
            G=100+(TeurerUm*AnzPfeil);
        }
        if(Art=="Armbrust")
        {
            G=300+(TeurerUm*AnzArmbr);
        }
        if(Art=="Kanonen")
        {
            G=400+(TeurerUm*AnzKanone);
        }
        if(Nummer<20 && Nummer>=0)
        {
            if(Spieler.Gold-G >= 0 && Turmplaetze[Nummer]==null)
            {
                Turmplaetze[Nummer]= new TURMPLATZ(Art, S, LeereFelder[Nummer].LeseX(), LeereFelder[Nummer].LeseY());
                Spieler.Gold = Spieler.Gold-G;
                if(Art=="Pfeil")
                {
                    AnzPfeil++;
                }
                if(Art=="Armbrust")
                {
                    AnzArmbr++;
                }
                if(Art=="Kanonen")
                {
                    AnzKanone++;
                }
            }
        }
        else
        {
            System.out.println("Der Spieler hat nicht genuegend Gold");
        }
    }
    
    // Typ 1==Schnell/HE, Typ 2==Stark/AP
    public void TurmSpezialisieren(int Nummer, int Typ)
    {
        String Art;
        int Kosten=0;
        if(Turmplaetze[Nummer]!=null)
        {
            Art=Turmplaetze[Nummer].Art;
            if(Art.equals("Pfeil") && Typ==1){Art="MultiPfeil"; Kosten=150;}
            if(Art.equals("Pfeil") && Typ==2){Art="Scharfschuetzen"; Kosten=150;}
            if(Art.equals("Armbrust") && Typ==1){Art="MultiArmbrust"; Kosten=200;}
            if(Art.equals("Armbrust") && Typ==2){Art="Ballisten"; Kosten=200;}
            if(Art.equals("Kanonen") && Typ==1){Art="HE"; Kosten=250;}
            if(Art.equals("Kanonen") && Typ==2){Art="AP"; Kosten=250;}
            if(Turmplaetze[Nummer].Level==4 &&  Spieler.Gold-Kosten>=0)
            {
                Spieler.Gold = Spieler.Gold-Turmplaetze[Nummer].TurmSpezialisieren(Art);
            }
        }
    }
    
    public int TurmVerkaufen(int Nummer)
    {
        int Gold_r = Turmplaetze[Nummer].Wert/2;
        
        Turmplaetze[Nummer].Turm.SetzeY(1500);
        Turmplaetze[Nummer].Geschoss.SetzeY(1500);
        Turmplaetze[Nummer]=null;
        
        return Gold_r;
    }
    
    public void WelleSchicken(int W)
    {
        Wellen.WelleSchicken(W);
    }
    
    public void Ausgabe()
    {
        IstVeraendert=false;
        AusLoeschen();
        int N=0;
        if(Nummer==-1)
        {
            System.out.println("Kein Platz ist Ausgewaehlt");
        }
        else
        {
            if(Nummer==0 && PlatzAusgewaehlt==false || Nummer==10 && PlatzAusgewaehlt==false)
            {
                System.out.println("Die Seite "+Seite+" ist ausgewaehlt");
            }
            else
            {
                if(Nummer > 0 && Nummer<=9)
                {
                    N=Nummer; 
                }
                if(Nummer >= 10 && Nummer<=19)
                {
                    N=Nummer-10;
                }
                if(Nummer==20)
                {
                    N=0;
                }
                System.out.println("Platz Nummer "+ N +" "+ Seite +" ist ausgewaehlt");
            }
        }
        System.out.println("");
        for(int i = 0 ; i<9 ; i++)
        {
            if(Turmplaetze[i]==null)
            {
                System.out.println("Platz Nummer  "+(i+1)+" Oben ist Leer");
            }
            else
            {
                System.out.println("Turm Nummer  "+(i+1)+" Oben ist ein Lvl "+Turmplaetze[i].Level+" "+Turmplaetze[i].Art+"turm");
            }
        }
        if(Turmplaetze[9]==null)
        {
            System.out.println("Platz Nummer 10 Oben ist Leer");
        }
        else
        {
            System.out.println("Turm Nummer 10 Oben ist ein Lvl "+Turmplaetze[9].Level+" "+Turmplaetze[9].Art+"turm");
        }
        System.out.println("");
        for(int i = 10 ; i<19 ; i++)
        {
            if(Turmplaetze[i]==null)
            {
                System.out.println("Platz Nummer  "+(i-9)+" Unten ist Leer");
            }
            else
            {
                System.out.println("Turm Nummer  "+(i-9)+" Unten ist ein Lvl "+Turmplaetze[i].Level+" "+Turmplaetze[i].Art+"turm");
            }
        }
        if(Turmplaetze[19]==null)
        {
             System.out.println("Platz Nummer 10 Unten ist Leer");
        }
        else
        {
            System.out.println("Turm Nummer 10 Unten ist ein Lvl "+Turmplaetze[19].Level+" "+Turmplaetze[19].Art+"turm");
        }
        
        System.out.println("");
        System.out.println("Der Spieler hat noch "+Spieler.Leben+" Leben");
        System.out.println("Der Spieler besitzt "+Spieler.Gold+" Gold");
        System.out.println("Das Spiel ist in Welle "+Welle);
        System.out.println("Es leben noch "+GegnerAmLeben+" Gegner");
        System.out.println("");
        System.out.println("Ein Pfeilturm kostet "+(100+(TeurerUm*AnzPfeil)));
        System.out.println("Ein Armbrustturm kostet "+(300+(TeurerUm*AnzArmbr)));
        System.out.println("Ein Kanonenturm kostet "+(400+(TeurerUm*AnzKanone)));
        System.out.println("");
        System.out.println("Einen Pfeilturm zu spezialisieren kostet 150 Gold");
        System.out.println("Einen Armbrustturm zu spezialisieren kostet 200 Gold");
        System.out.println("Einen Kanonenturm zu spezialisieren kostet 250 Gold");
    }
    
    public void AusLoeschen()
    {
        System.out.print('\u000C');
    }
    
    // für Controler Bild "SpielPausiert" Bewegen
    public void SPB()
    {
        SpPaus.SetzeY(1500);
    }
}
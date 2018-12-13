public class SPIEL
{
    private BILD Hintergrund;
    private BILD Weg;
    private BILD[] TurmNummern;
    private BILD Oben;
    private BILD Unten;
    private BILD[] LeereFelder;
    
    public SPIELER Spieler;
    
    public CONTROLER c;
    
    private int Nummer;
    private boolean PlatzAusgewaehlt;
    private boolean IstVeraendert;
    private String Seite;
    public boolean WelleSchicken;
    public int Welle;
    public int Wellengegner;
    public int GegnerAmLeben;
    
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
        
        Spieler = new SPIELER();
        
        Hintergrund = new BILD("Hintergrund.PNG");
        
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
        
        TurmNummern = new BILD[20];
        String BN = "";
        for(int i = 0; i<20; i++)
        {
            if(i<10)
            {
                if(i==9)
                {
                    BN=""+0;
                }
                else
                {
                    BN=""+(i+1);
                }
            }
            else
            {
                if(i==19)
                {
                    BN=""+0;
                }
                else                {
                    BN=""+(i-9);
                }
            }
            TurmNummern[i] = new BILD(BN+".PNG");
            if(i<10)
            {
                TurmNummern[i].SetzeX(100 + i*135);
                TurmNummern[i].SetzeY(200);
            }
            else
            {
                TurmNummern[i].SetzeX(100 + (i-10)*135);
                TurmNummern[i].SetzeY(550);
            }
            BN="";
        }
        
        Werte = new WERTE[3];
        for(int i = 0; i< 3; i++)
        {
            Werte[i] = new WERTE(i);
        }
    }
    
    
    public void Action(KEYSTATE keystate)
    {
        if(keystate.IsPressed("P"))
        {
            c.AmLaufen=false;
        }
        if(keystate.IsPressed("Z"))
        {
            WelleSchicken(Welle);
        }
        if(keystate.IsPressed("I"))
        {
            Nummer = -1;
            PlatzAusgewaehlt=false;
            IstVeraendert=true;
            Seite = "";
        }
        if(keystate.IsPressed("O"))
        {
            Nummer = 0;
            PlatzAusgewaehlt=false;
            IstVeraendert=true;
            Seite = "Oben";
        }
        if(keystate.IsPressed("U"))
        {
            Nummer = 10;
            PlatzAusgewaehlt=false;
            IstVeraendert=true;
            Seite = "Unten";
        }
        if(Nummer==0 || Nummer==10 && PlatzAusgewaehlt==false)
        {
            if(keystate.IsPressed("1"))
            {
                Nummer=Nummer+1;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("2"))
            {
                Nummer=Nummer+2;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("3"))
            {
                Nummer=Nummer+3;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("4"))
            {
                Nummer=Nummer+4;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("5"))
            {
                Nummer=Nummer+5;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("6"))
            {
                Nummer=Nummer+6;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("7"))
            {
                Nummer=Nummer+7;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("8"))
            {
                Nummer=Nummer+8;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("9"))
            {
                Nummer=Nummer+9;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("0"))
            {
                Nummer=Nummer+10;
                PlatzAusgewaehlt=true;
                IstVeraendert=true;
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
                Spieler.Gold = Spieler.Gold+TurmVerkaufen(Nummer-1);
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
                Seite = "";
            }
            if(keystate.IsPressed("T"))
            {
                if(Spieler.Gold>=Turmplaetze[Nummer-1].UpgradeKosten)
                {
                    Spieler.Gold = Spieler.Gold-Turmplaetze[Nummer-1].TurmUpgraden();
                }
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
                Seite = "";
            }
        }
        if(IstVeraendert==true)
        {
            Ausgabe();
        }
        if(GegnerAmLeben>0)
        {
            for(int i = 0; i<Wellengegner; i++)
            {
                if(Gegner[i].Leben<=0 && Gegner[i].Zustand=="AmLeben")
                {
                    GegnerAmLeben--;
                    Gegner[i].Zustand="Tot";
                    Spieler.Gold = Spieler.Gold+Gegner[i].GoldWert;
                    Gegner[i].Skin.SetzeY(1200);
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
        
        if(Spieler.Leben <= 0)
        {
            AusLoeschen();
            System.out.println("Game Over");
            c.end();
        }
    }
    
    public void TurmPlatzieren(int Nummer, String Art, String S)
    {
        int G=0;
        if(Art=="Pfeil")
        {
            G=100;
        }
        if(Art=="Armbrust")
        {
            G=300;
        }
        if(Art=="Kanonen")
        {
            G=400;
        }
        
        if(Spieler.Gold-G >= 0 && Turmplaetze[Nummer]==null)
        {
            Turmplaetze[Nummer]= new TURMPLATZ(Art, S, LeereFelder[Nummer].LeseX(), LeereFelder[Nummer].LeseY());
            Spieler.Gold = Spieler.Gold-G;
        }
        else
        {
            System.out.println("Der Spieler hat nicht genuegend Gold");
        }
        
        Turmplaetze[Nummer].Gegner=Gegner;
    }
    
    public int TurmVerkaufen(int Nummer)
    {
        int Gold_r = Turmplaetze[Nummer].Wert/2;
        
        Turmplaetze[Nummer].Turm.SetzeY(1500);
        Turmplaetze[Nummer]=null;
        
        return Gold_r;
    }
    
    public void WelleSchicken(int W)
    {
        if(GegnerAmLeben>0)
        {
            return;
        }
        IstVeraendert=true;
        if(W==0)
        {
            Gegner = null;
            Gegner = new GEGNER[5];
            for(int i = 0; i<5; i++)
            {
                Gegner[i] = new GEGNER(0,Werte);
                int x = 1500+(i*50);
                Gegner[i].Skin.SetzeX(x);
                Gegner[i].Skin.SetzeY(375);
            }
            Welle++;
            Wellengegner=5;
            GegnerAmLeben=5;
            return;
        }
        if(W==1)
        {
            Gegner = null;
            Gegner = new GEGNER[6];
            int x;
            for(int i = 0; i<5; i++)
            {
                Gegner[i] = new GEGNER(0,Werte);
                x = 1500+(i*50);
                Gegner[i].Skin.SetzeX(x);
                Gegner[i].Skin.SetzeY(375);
            }
            for(int i = 5; i<6; i++)
            {
                Gegner[i] = new GEGNER(1,Werte);
                x = 1500+(i*50);
                Gegner[i].Skin.SetzeX(x);
                Gegner[i].Skin.SetzeY(375);
            }
            Welle++;
            Wellengegner=6;
            GegnerAmLeben=6;
            return;
        }
        if(W==2)
        {
            Gegner = null;
            Gegner = new GEGNER[8];
            int x;
            for(int i = 0; i<6; i++)
            {
                Gegner[i] = new GEGNER(0,Werte);
                x = 1500+(i*50);
                Gegner[i].Skin.SetzeX(x);
                Gegner[i].Skin.SetzeY(375);
            }
            for(int i = 6; i<8; i++)
            {
                Gegner[i] = new GEGNER(1,Werte);
                x = 1500+(i*50);
                Gegner[i].Skin.SetzeX(x);
                Gegner[i].Skin.SetzeY(375);
            }
            Welle++;
            Wellengegner=8;
            GegnerAmLeben=8;
            return;
        }
        if(W==3)
        {
            Gegner = null;
            Gegner = new GEGNER[20];
            int x;
            for(int i = 0; i<1; i++)
            {
                Gegner[i] = new GEGNER(2,Werte);
                x = 1500+(i*50);
                Gegner[i].Skin.SetzeX(x);
                Gegner[i].Skin.SetzeY(375);
            }
            for(int i = 1; i<7; i++)
            {
                Gegner[i] = new GEGNER(0,Werte);
                x = 1500+(i*50);
                Gegner[i].Skin.SetzeX(x);
                Gegner[i].Skin.SetzeY(375);
            }
            for(int i = 7; i<10; i++)
            {
                Gegner[i] = new GEGNER(1,Werte);
                x = 1500+(i*50);
                Gegner[i].Skin.SetzeX(x);
                Gegner[i].Skin.SetzeY(375);
            }
            Welle++;
            Wellengegner=10;
            GegnerAmLeben=10;
            return;
        }
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
                System.out.println("Platz Nummer "+(i+1)+" Oben ist Leer");
            }
            else
            {
                System.out.println("Turm Nummer "+(i+1)+" Oben ist ein "+Turmplaetze[i].Art+"turm Lvl "+Turmplaetze[i].Level);
            }
        }
        if(Turmplaetze[9]==null)
        {
            System.out.println("Platz Nummer 0 Oben ist Leer");
        }
        else
        {
            System.out.println("Turm Nummer 0 Oben ist ein "+Turmplaetze[9].Art+"turm Lvl "+Turmplaetze[9].Level);
        }
        System.out.println("");
        for(int i = 10 ; i<19 ; i++)
        {
            if(Turmplaetze[i]==null)
            {
                System.out.println("Platz Nummer "+(i-9)+" Unten ist Leer");
            }
            else
            {
                System.out.println("Turm Nummer "+(i-9)+" Unten ist ein "+Turmplaetze[i].Art+"turm Lvl "+Turmplaetze[i].Level);
            }
        }
        if(Turmplaetze[19]==null)
        {
             System.out.println("Platz Nummer 0 Unten ist Leer");
        }
        else
        {
            System.out.println("Turm Nummer 0 Unten ist ein "+Turmplaetze[19].Art+"turm Lvl "+Turmplaetze[19].Level);
        }
        
        System.out.println("");
        System.out.println("Der Spieler hat noch "+Spieler.Leben+" Leben");
        System.out.println("Der Spieler besitzt "+Spieler.Gold+" Gold");
        System.out.println("Das Spiel ist in Welle "+Welle);
        System.out.println("Es leben noch "+GegnerAmLeben+" Gegner");
    }
    
    public void AusLoeschen()
    {
        System.out.print('\u000C');
    }
}
import java.awt.Point;

public class SPIEL
{
    private BILD Hintergrund;
    private BILD[] weg;
    private BILD[] LeereFelder;
    private BILD MarkierterPlatz;

    //Buttons
    private BILD WeSchicken;
    private BILD Pause;
    private BILD SpPaus;
    private BILD Speed1;
    private BILD Speed2;

    public SPIELER Spieler;

    public CONTROLER c;

    public Point Ende;

    public static final int AnzLevel = 3;

    public int SpielLevel;

    //Turm Auswaehlen
    public int Nummer;
    public boolean PlatzAusgewaehlt;

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
    public int AnzTuerme;

    public GEGNER[] Gegner;

    public SPIEL(CONTROLER c_p, int Lvl)
    {
        c=c_p;
        SpielLevel = Lvl;
        Nummer = -1;
        PlatzAusgewaehlt=false;
        IstVeraendert=true;
        WelleSchicken=false;
        Welle=0;
        GegnerAmLeben = 0;
        Wellengegner = 0;

        TeurerUm = 20;

        Wellen = new WELLEN(this);

        Spieler = new SPIELER();

        Hintergrund = new BILD("Hintergrund.PNG");
        Hintergrund.SetzeBreite(1500);
        Hintergrund.SetzeHoehe(800);

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

        LevelErzeugen(Lvl);

        SpPaus = new BILD("bSpPaus.PNG");
        SpPaus.SetzeX(460);
        SpPaus.SetzeY(1500);
        
        MarkierterPlatz = new BILD("TurmplatzMarkiert.PNG");
        MarkierterPlatz.SetzeX(-200);
    }

    /* -> Turmplätze + weg(BILD) je nach Level festlegen & in WEG müssen die passenden Wege mit der gleichen nummer versehen werden & AnzLevel+1
    
        Vorlage:
        
        if(Level == 0)
        {
            int AnzTuerme_p = 20;
            int AnzWegabschnitte = 1;
            int[] w_x = {0};
            int[] w_y = {350};
            int[] w_h = {100};
            int[] w_b = {1500};
            boolean[] Diagonal = {false};
            Point Start_p = new Point(1500, 375);
            Point Ende_p = new Point(-75, 400);
            int[] t_x = new int[AnzTuerme_p];
            int[] t_y = new int[AnzTuerme_p];
            for(int i = 0; i < AnzTuerme_p; i++)
            {
                if(i<10)
                {
                    t_x[i] = 100 + i*135;
                    t_y[i] = 280;
                }
                else
                {
                    t_x[i] = 100 + (i-10)*135;
                    t_y[i] = 470;
                }
            }
            LevelNeu(AnzTuerme_p, AnzWegabschnitte, w_x, w_y, w_h, w_b, Diagonal, Start_p, Ende_p, t_x, t_y);
        }
        */
    public void LevelErzeugen(int Level) //Neue Version
    {   
        if(Level == 0)
        {
            int AnzTuerme_p = 20;
            int AnzWegabschnitte = 1;
            int[] w_x = {0};
            int[] w_y = {350};
            int[] w_h = {100};
            int[] w_b = {1500};
            boolean[] Diagonal = {false};
            Point Start_p = new Point(1500, 375);
            Point Ende_p = new Point(-75, 400);
            int[] t_x = new int[AnzTuerme_p];
            int[] t_y = new int[AnzTuerme_p];
            for(int i = 0; i < AnzTuerme_p; i++)
            {
                if(i<10)
                {
                    t_x[i] = 100 + i*135;
                    t_y[i] = 280;
                }
                else
                {
                    t_x[i] = 100 + (i-10)*135;
                    t_y[i] = 470;
                }
            }
            LevelNeu(AnzTuerme_p, AnzWegabschnitte, w_x, w_y, w_h, w_b, Diagonal, Start_p, Ende_p, t_x, t_y);
        }
        if(Level == 1)
        {
            int AnzTuerme_p = 19;
            int AnzWegabschnitte = 2;
            int[] w_x = {0, 750};
            int[] w_y = {0, 700};
            int[] w_h = {800, 100};
            int[] w_b = {800, 750};
            boolean[] Diagonal = {true,false};
            Point Start_p = new Point(1500, 750);
            Point Ende_p = new Point(-75, -75);
            int[] t_x = new int[AnzTuerme_p];
            int[] t_y = new int[AnzTuerme_p];
            for(int i = 0; i < 16; i++)
            {
                if(i<8)
                {
                    t_x[i] = i*80;
                    t_y[i] = 130 + i*80;
                }
                else
                {
                    t_x[i] = 130 + (i-8)*80;
                    t_y[i] = (i-8)*80;
                }
            }
            t_x[16] = 850;
            t_y[16] = 625;
            t_x[17] = 1100;
            t_y[17] = 625;
            t_x[18] = 1350;
            t_y[18] = 625;
            LevelNeu(AnzTuerme_p, AnzWegabschnitte, w_x, w_y, w_h, w_b, Diagonal, Start_p, Ende_p, t_x, t_y);
        }
        if(Level == 2)
        {
            int AnzTuerme_p = 16;
            int AnzWegabschnitte = 4;
            int[] w_x = {450, 450, 1050, 0};
            int[] w_y = {0, 200, 200, 550};
            int[] w_h = {250, 100, 450, 100};
            int[] w_b = {100, 600, 100, 1100};
            boolean[] Diagonal = {false,false,false,false};
            Point Start_p = new Point(475, 0);
            Point Ende_p = new Point(-75, 600);
            int[] t_x = {575,375,500,750,975,875,1175,1175,1175,850,525,225,925,650,400,150};
            int[] t_y = {125,175,325,325,325,125,125,400,675,475,475,475,675,675,675,675};
            LevelNeu(AnzTuerme_p, AnzWegabschnitte, w_x, w_y, w_h, w_b, Diagonal, Start_p, Ende_p, t_x, t_y);
        }
    }

    /**
     * w_ => fuer Wegabschnitte
     * Spawn: genau eine der Koordinten muss 0 sein
     * Ende: 75 pixel vom Rand entfernt
     * t_ => fuer Tuerme
     */
    public void LevelNeu(int AnzTuerme_p, int Wegabschnitte, int[] w_x, int[] w_y, int[] w_h, int[] w_b, boolean[] Diagonal, Point Spawn, Point Ende_p,  int[] t_x, int[] t_y)
    {
        AnzTuerme = AnzTuerme_p;

        Turmplaetze = new TURMPLATZ[AnzTuerme];

        weg = new BILD[Wegabschnitte];
        for(int i = 0; i<Wegabschnitte; i++)
        {
            WegErz(i, w_x[i], w_y[i], w_h[i], w_b[i], Diagonal[i]);
        }

        Wellen.Spawnpunkt = Spawn; 
        if(Spawn.x <= 0 && Spawn.y > 0 && Spawn.y < GAMEWINDOW.Hoehe){Wellen.erzX = -1; Wellen.erzY = 0;}
        if(Spawn.x >= GAMEWINDOW.Breite && Spawn.y > 0 && Spawn.y < GAMEWINDOW.Hoehe){Wellen.erzX = 1; Wellen.erzY = 0;}
        if(Spawn.y <= 0 && Spawn.x > 0 && Spawn.x < GAMEWINDOW.Breite){Wellen.erzY = -1; Wellen.erzX = 0;}
        if(Spawn.y >= GAMEWINDOW.Hoehe && Spawn.x > 0 && Spawn.x < GAMEWINDOW.Breite){Wellen.erzY = 1; Wellen.erzX = 0;}

        Ende = Ende_p;

        LeereFelder = new BILD[AnzTuerme];

        for(int i = 0; i<AnzTuerme; i++)
        {
            TurmplatzErz(i, t_x[i], t_y[i]);
        }
    }

    private void TurmplatzErz(int FeldId, int x, int y)
    {
        LeereFelder[FeldId] = new BILD("TurmplatzLeer.PNG");
        LeereFelder[FeldId].SetzeX(x);
        LeereFelder[FeldId].SetzeY(y);
    }

    private void WegErz(int FeldId, int x, int y, int Hoehe, int Breite, boolean Diagonal)
    {
        if(Diagonal)
        {
            weg[FeldId] = new BILD("WegDiagonal.PNG");
            weg[FeldId].TransparenzSetzen(255, 255, 255);
        }
        else
        {
            weg[FeldId] = new BILD("Weg.PNG");
        }
        weg[FeldId].SetzeX(x);
        weg[FeldId].SetzeY(y);
        weg[FeldId].SetzeHoehe(Hoehe);
        weg[FeldId].SetzeBreite(Breite);
    }

    public void RUN(KEYSTATE keystate)
    {
        GoldNachBoss();

        TastenPruefen(keystate);

        MausklickUeberpruefen(keystate);

        UpdateTuerme_Gegner();

        EndePruefen();

        //Ausgabe -> immer ganz am Schluss ausfuehren
        if(IstVeraendert==true)
        {
            Ausgabe();
        }
    }

    //
    //
    // Teile der RUN-Methode
    //
    //

    public void GoldNachBoss()
    {
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
    }

    public void TastenPruefen(KEYSTATE keystate)
    {
        if(keystate.IsPressed("M")) //Speichern
        {
            if(GegnerAmLeben == 0)
            {
                c.Speichern();
            }
        }

        if(PlatzAusgewaehlt==true)
        {
            if(keystate.IsPressed("Q"))
            {
                TurmPlatzieren(Nummer-1, "Pfeil");
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("W"))
            {
                TurmPlatzieren(Nummer-1, "Armbrust");
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("E"))
            {
                TurmPlatzieren(Nummer-1, "Kanonen");
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
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
            }
            if(keystate.IsPressed("T"))
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
            }
            //Spezialisieren
            if(keystate.IsPressed("F"))
            {
                TurmSpezialisieren(Nummer-1, 1);
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
            }
            if(keystate.IsPressed("G"))
            {
                TurmSpezialisieren(Nummer-1, 2);
                PlatzAusgewaehlt=false;
                Nummer=-1;
                IstVeraendert=true;
            }
        }
    }

    public void MausklickUeberpruefen(KEYSTATE keystate)
    {
        Point m = keystate.PickLastMouseClickPosition();
        int Platz = -1;
        if(Nummer == -1){MarkierterPlatz.SetzeX(-200);}
        if(m!=null)
        {
            if(m.x >= 0 && m.x <= 1500 && m.y>=0 && m.y<=800)
            {
                PlatzAusgewaehlt=false;
                MarkierterPlatz.SetzeX(-200);
                Nummer=-1;
                IstVeraendert=true;
            }
            // Platzauswahl

            for(int i = 0; i<AnzTuerme; i++)
            {
                if(m.x>=LeereFelder[i].LeseX() && m.x<=LeereFelder[i].LeseX()+LeereFelder[i].LeseBreite() &&
                m.y>=LeereFelder[i].LeseY() && m.y<=LeereFelder[i].LeseY()+LeereFelder[i].LeseHoehe() )
                {
                    PlatzAusgewaehlt = true;
                    IstVeraendert=true;
                    Platz = i+1;
                    MarkierterPlatz.SetzeX(LeereFelder[i].LeseX()-2);
                    MarkierterPlatz.SetzeY(LeereFelder[i].LeseY()-2);
                }
            }

            if(Platz==-1)
            {
                Nummer=-1;
            }
            else
            {
                Nummer = Platz;
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
            //1fach speed
            if(m.x >= 490 && m.x <= 531 && m.y>=759 && m.y<=800)
            {
                c.SpeedFaktor = 1;
            }
            //4fach speed
            if(m.x >= 560 && m.x <= 601 && m.y>=759 && m.y<=800)
            {
                c.SpeedFaktor = 4;
            }
        }
    }

    public void UpdateTuerme_Gegner()
    {
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
                    Gegner[i].Skin.SetzeY(3000);
                    Gegner[i].Lebensbalken.SetzeY(3000);
                    Gegner[i].RestLebensbalken.SetzeY(3000);
                    IstVeraendert=true;
                }
                if(EndeErreicht(Gegner[i].Skin) && Gegner[i].Zustand=="AmLeben")
                {
                    GegnerAmLeben--;
                    Gegner[i].Zustand="Tot";
                    Spieler.Leben = Spieler.Leben-Gegner[i].Lebensverlust;
                    Gegner[i].Skin.SetzeY(3000);
                    IstVeraendert=true;
                }
            }
        }

        for(int i = 0 ; i<AnzTuerme ; i++)
        {
            if(Turmplaetze[i]!=null)
            {
                Turmplaetze[i].Wellengegner=Wellengegner;
                Turmplaetze[i].GegnerAmLeben=GegnerAmLeben;
                Turmplaetze[i].Gegner=Gegner;
                Turmplaetze[i].Angriff();
            }
        }
    }

    public boolean EndeErreicht(BILD b)
    {
        if(b.Abstand(Ende)<51)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void EndePruefen()
    {
        if(Spieler.Leben <= 0)
        {
            AusgabeLoeschen();
            System.out.println("Game Over");
            c.end();
        }
    }

    //
    //
    // Teile der RUN-Methode ENDE
    //
    //

    public void TurmPlatzieren(int Nummer, String Art)
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
        if(Spieler.Gold-G >= 0 && Turmplaetze[Nummer]==null)
        {
            Turmplaetze[Nummer]= new TURMPLATZ(Art, LeereFelder[Nummer].LeseX(), LeereFelder[Nummer].LeseY());
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

        String a = Turmplaetze[Nummer].Art;

        if(a.equals("Pfeil") || a.equals("MultiPfeil") || a.equals("Scharfschuetzen"))
        {
            AnzPfeil--;
        }
        if(a.equals("Armbrust") || a.equals("MultiArmbrust") || a.equals("Ballisten"))
        {
            AnzArmbr--;
        }
        if(a.equals("Kanonen") || a.equals("HE") || a.equals("AP"))
        {
            AnzKanone--;
        }

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
        AusgabeLoeschen();
        if(Nummer==-1){System.out.println("Kein Platz ist Ausgewaehlt");}else{System.out.println("");}
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
        System.out.println("Ein Pfeilturm zu verbessern kostet 50");
        System.out.println("Ein Armbrustturm zu verbessern kostet 100");
        System.out.println("Ein Kanonenturm zu verbessern kostet 150");
        System.out.println("");
        System.out.println("Beim Spiezialisieren:");
        System.out.println("-F erzeugt einen schnell-schießenden/HE Turm");
        System.out.println("-G erzeugt einen starken/AP Turm");
        System.out.println("");
        System.out.println("Einen Pfeilturm zu spezialisieren kostet 150 Gold");
        System.out.println("Einen Armbrustturm zu spezialisieren kostet 200 Gold");
        System.out.println("Einen Kanonenturm zu spezialisieren kostet 250 Gold");
    }

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }

    // für Controler Bild "SpielPausiert" Bewegen
    public void SPB()
    {
        SpPaus.SetzeY(1500);
    }
}
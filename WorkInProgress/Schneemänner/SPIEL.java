import java.awt.Point;

public class SPIEL
{
    public CONTROLER c;

    private BILD Hintergrund;

    public SPIELER Spieler;

    public GEGNER[] Gegner;
    public boolean IstVeraendert;

    //Welle Schicken
    private WELLEN w;
    public boolean WelleSchickenWahr;
    public static int Welle;
    public int Wellengegner;
    private int GegnerAmLeben;
    private int GegnerBesiegt;
    
    private int timerSchicken;
    
    public boolean LevelUpNochBekommen;
    private int LevelScore;

    public SPIEL(CONTROLER c_p)
    {
        c = c_p;

        Hintergrund = new BILD("Grafiken\\hintergrund.jpg");
        Hintergrund.SetzeBreite(GAMEWINDOW.Breite);
        Hintergrund.SetzeHoehe(GAMEWINDOW.Hoehe);

        Spieler = new SPIELER();
        w = new WELLEN(this);
        LevelUpNochBekommen = true;
        LevelScore = 3;
    }

    public void RUN(KEYSTATE keystate)
    {
        Point m = keystate.PickLastMouseClickPosition();

        TastenPruefen(keystate, m);

        MausklickUeberpruefen(keystate, m);
        
        GegnerKI();
        
        WelleSchicken();

        Baelle();

        Kollisionen();
        
        Leveln();
        
        //Ausgabe+Timer
        Spieler.zeitVergangen = Spieler.zeitVergangen+20;
        timerSchicken = timerSchicken-20;
        
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

    public void TastenPruefen(KEYSTATE keystate, Point m)
    {
        if(keystate.IsPressed("W") && !(keystate.IsPressed("D")) && !(keystate.IsPressed("A")) && !(keystate.IsPressed("S"))) // Nur wenn die Taste W gedrückt wird
        {
            Spieler.Bewegen(1);
        }
        
        if(keystate.IsPressed("W") && (keystate.IsPressed("D")) && (keystate.IsPressed("A")) && !(keystate.IsPressed("S"))) // W + A&D
        {
            Spieler.Bewegen(1);
        }

        if(keystate.IsPressed("W") && keystate.IsPressed("D") && !(keystate.IsPressed("A")) && !(keystate.IsPressed("S")))      // Nur wenn die Taste W und D gedrückt wird
        {
            Spieler.Bewegen(2);
        }

        if(keystate.IsPressed("W") && keystate.IsPressed("A") && !(keystate.IsPressed("D")) && !(keystate.IsPressed("S")))      // Nur wenn die Taste W und A gedrückt wird
        {
            Spieler.Bewegen(8);
        }

        if(keystate.IsPressed("D") && !(keystate.IsPressed("W")) && !(keystate.IsPressed("S")) && !(keystate.IsPressed("A")))       // Nur wenn die Taste D gedrückt wird
        {
            Spieler.Bewegen(3);
        }
        
        if((keystate.IsPressed("D")) && keystate.IsPressed("W") && (keystate.IsPressed("S")) && !(keystate.IsPressed("A"))) // D + W&S
        {
            Spieler.Bewegen(3);
        }

        if(keystate.IsPressed("S") && !(keystate.IsPressed("D")) && !(keystate.IsPressed("A")) && !(keystate.IsPressed("W")))   // Nur wenn die Taste S gedrückt wird
        {
            Spieler.Bewegen(5);
        }
        
        if((keystate.IsPressed("D")) && (keystate.IsPressed("S")) && (keystate.IsPressed("A")) && !keystate.IsPressed("W")) // S + A&D
        {
            Spieler.Bewegen(5);
        }

        if(keystate.IsPressed("S") && keystate.IsPressed("D") && !(keystate.IsPressed("A")) && !(keystate.IsPressed("W")))      // Nur wenn die Taste S und D gedrückt wird
        {
            Spieler.Bewegen(4);
        }

        if(keystate.IsPressed("S") && keystate.IsPressed("A") && !(keystate.IsPressed("D")) && !(keystate.IsPressed("W")))  // Nur wenn die Taste S und A gedrückt wird
        {
            Spieler.Bewegen(6);
        }

        if(keystate.IsPressed("A")&&!(keystate.IsPressed("S")) && !(keystate.IsPressed("W")) && !(keystate.IsPressed("D")))       // Nur wenn die Taste A gedrückt wird
        {
            Spieler.Bewegen(7);
        }
        
        if((keystate.IsPressed("A")) && (keystate.IsPressed("W")) && (keystate.IsPressed("S")) && !keystate.IsPressed("D")) // A + W&S
        {
            Spieler.Bewegen(7);
        }

        if(keystate.IsPressed("P"))       // Bei P wird die Werfenmethode aufgerufen
        {
            Spieler.Werfen(Spieler.Richtung);
        }

        if(m != null)     // Bei Mausklick wird die Werfenmethode aufgerufen
        {
            int r = BestWerfR(m);
            Spieler.Werfen(r);
        }

        if(keystate.IsPressed("N")) //Neue Welle Schicken
        {
            if(GegnerAmLeben == 0)
            {
                WelleSchickenWahr = true;
                Welle++;
            }
        }
    }

    public int BestWerfR(Point m) //Werfrichtung bestimmen
    {
        double b = Spieler.Skin.LeseX()+Spieler.Skin.LeseBreite()/2 - m.x ;
        double h = Spieler.Skin.LeseY()+Spieler.Skin.LeseHoehe()/2 - m.y;

        double d = Math.sqrt(b*b + h*h);

        double v = h/d; //sinus

        /*
         * sinus:
         * 22.5     r,o     0.38
         * 67.5     o,r     0.92
         * 112.5    o,l     0.92
         * 157.5    l,o     0.38
         * 202.5    l,u    -0.38
         * 247.5    u,l    -0.92
         * 292.5    u,r    -0.92
         * 337.5    r,u    -0.38
         * 
         * richtungen:
         * 1    o
         * 2    o,r
         * 3    r
         * 4    r,u
         * 5    u
         * 6    u,l
         * 7    l
         * 8    o,l
         * 
         */

        if(0 < h) //oben von Spieler
        {
            if(0 < b) //links von spieler
            {
                if(sin(112.5)<v) //o
                {
                    return 1;
                }
                if(v<sin(157.5)) //l
                {
                    return 7;
                }
                //o,l
                return 8;
            }
            else //rechts von Spieler
            {
                if(sin(67.5)<v) //o
                {
                    return 1;
                }
                if(v<sin(22.5)) //r
                {
                    return 3;
                }
                //o,r
                return 2;
            }
        }
        else //unten von spieler
        {
            if(0 < b) //links von spieler ; v & sin => negativ
            {
                if(v<sin(247.5)) //u
                {
                    return 5;
                }
                if(sin(202.5)<v) //l
                {
                    return 7;
                }
                //u,l
                return 6;
            }
            else //rechts von Spieler  ; v & sin => negativ
            {
                if(v<sin(292.5)) //u
                {
                    return 5;
                }
                if(sin(337.5)<v) //r
                {
                    return 3;
                }
                //u,r
                return 4;
            }
        }
    }

    public double sin(double w) //sinus mit Winkeln als °
    {
        double r = Math.sin(Math.toRadians(w));
        return r;
    }

    public void MausklickUeberpruefen(KEYSTATE keystate, Point m)
    {
        if(m!=null)
        {
            //Bsp
            if(m.x >= 560 && m.x <= 601 && m.y>=759 && m.y<=800)
            {

            }
        }
    }

    public void Baelle()
    {
        for(int schn = 0; schn < 20; schn++)
        {
            if(Spieler.b[schn] != null)
            {
                Spieler.b[schn].Bewegen();
                if(Spieler.b[schn].loeschen)
                {
                    Spieler.b[schn] = null;
                }
            }
        }
    }
    
    public void Kollisionen()
    {
        for(int wg = 0;wg < Wellengegner ; wg++)
        {
            for(int schn = 0; schn < 20; schn++)
            {
                if(Spieler.b[schn] != null && Gegner[wg] != null)
                {
                    if(Kollisionsabfrage(Gegner[wg].Skin, Spieler.b[schn].Ball))
                    {
                        Spieler.b[schn].Ball.SetzeY(2000);
                        Spieler.b[schn] = null;
                        Gegner[wg].Leben = Gegner[wg].Leben - Spieler.LeseSchaden();
                        if(Gegner[wg].Leben <= 0)
                        {
                            //Spezial Feature:
                            if(CONTROLER.spezialFeature){
                                BILD b = new BILD("Grafiken\\spezial.png");
                                b.SetzeBreite(100);
                                b.SetzeHoehe(100);
                                b.SetzeY(Gegner[wg].Skin.LeseY());
                                b.SetzeX(Gegner[wg].Skin.LeseX());
                            }
                            Gegner[wg].Skin.SetzeY(2000);
                            Gegner[wg] = null;
                            GegnerAmLeben--;
                            Spieler.Score++;
                            LevelUpNochBekommen = true;
                        }
                    }
                }
            }

        }

        for(int wg = 0;wg < Wellengegner ; wg++)
        {

            if(Gegner[wg] != null)
            {
                if(Kollisionsabfrage(Gegner[wg].Skin, Spieler.Skin))
                {
                    Gegner[wg].Skin.SetzeY(2000);
                    Gegner[wg] = null;
                    GegnerAmLeben--;
                    Spieler.Leben--;
                    if(Spieler.Leben <= 0)
                    {
                        Gameover();
                    }
                }
            }
        }
    }

    public boolean Kollisionsabfrage(BILD a, BILD b)
    {
        if(a.Abstand(b) <= 50)
        {
            return true;
        }

        return false;
    }
    
    public void GegnerKI()
    {
        for(int wg = 0;wg < Wellengegner ; wg++)
        {
            if(Gegner[wg] != null)
            {
                Gegner[wg].KI();
            }
        }
    }
    
    public void WelleSchicken()
    {
        if(WelleSchickenWahr == true)
        {
            if(GegnerAmLeben == 0)
            {
                Wellengegner = 4+Welle;
                IstVeraendert = true;
                Gegner = new GEGNER[Wellengegner];
                GegnerAmLeben = Wellengegner;
                w.nGeg = 0;
            }
            if(timerSchicken <= 0)
            {
                w.WelleSchicken();
                IstVeraendert = true;
                timerSchicken = 1000;
            }
        }
    }
    
    public void Leveln()
    {
        if(Spieler.Score == LevelScore && LevelUpNochBekommen)
        {
            Spieler.Level++;
            LevelScore = LevelScore + Spieler.Level*3;
            if(Spieler.Wurfdelay>=320){Spieler.Wurfdelay = Spieler.Wurfdelay-20;}
            if(Spieler.Level%2 == 1 && Spieler.Leben <= 10) //Level 3,5,7,9,... ein Leben Dazu
            {
                Spieler.Leben++;
            }
            LevelUpNochBekommen = false;
        }
    }

    public void Ausgabe()
    {
        
    }

    public void Gameover()
    {
        c.ScoreSetzen(c.Name,Spieler.Score);
        c.HighscoreAusgeben();
        BILD g = new BILD("Grafiken\\gameover.png");
        g.SetzeBreite(GAMEWINDOW.Breite);
        g.SetzeHoehe(GAMEWINDOW.Hoehe);
        c.stop();
    }
    
    //
    //Ende Run
    //
    
    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }

    public int LeseGegnerAmLeben()
    {
        return GegnerAmLeben;
    }

}
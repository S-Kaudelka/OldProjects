import java.awt.Point;

public class SPIEL
{
    private CONTROLER c;

    private boolean IstVeraendert;

    private BILD[][] Rand;

    private STEIN[][] Fest; //Spielfeld

    private String Naechster;

    private PAKET p;
    
    private int AnzReihenZerstoert;

    private int ZeitVergangen;
    private int Wartezeit;
    private int ZeitSeitw;
    private int WartenSeit;
    private boolean WartenDreh;

    public SPIEL(CONTROLER c_p)
    {
        c = c_p;

        Wartezeit = 300;
        WartenSeit = 160;
        WartenDreh = true;

        Rand = new BILD[2][18];
        for(int i = 0; i< 2; i++)
        {
            for(int j = 0; j<18; j++)
            {
                String n;
                if(i == 0)
                {
                    n = "Rand-L.png";
                }
                else
                {
                    n = "Rand-R.png";
                }
                Rand[i][j] = new BILD(n);
                Rand[i][j].SetzeX(i*550);
                Rand[i][j].SetzeY(j*50);
            }
        }

        Fest = new STEIN[18][10];

        Naechster = ZufallStein();

        NeuPaket(Naechster);
    }

    public void RUN(KEYSTATE keystate)
    {
        TastenPruefen(keystate);

        Point p = keystate.PickLastMouseClickPosition();
        if(p != null)
        {
            MausklickUeberpruefen(p);
        }

        SteineBewegen();

        LinienPruefen();

        ZeitVergangen = ZeitVergangen + CONTROLER.Sleep;
        ZeitSeitw = ZeitSeitw + CONTROLER.Sleep;
        if(keystate.IsPressed("W"))
        {
            WartenDreh = false;
        }
        else
        {
            WartenDreh = true;
        }
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

    public void TastenPruefen(KEYSTATE keystate)
    {
        if(keystate.IsPressed("A"))
        {
            if(ZeitSeitw >= WartenSeit)
            {
                ZeitSeitw = 0;
                Bewegen("A");
            }
        }
        if(keystate.IsPressed("D"))
        {
            if(ZeitSeitw >= WartenSeit)
            {
                ZeitSeitw = 0;
                Bewegen("D");
            }
        }
        if(keystate.IsPressed("W"))
        {
            if(WartenDreh)
            {
                Drehen();
            }
        }
    }

    public void MausklickUeberpruefen(Point m)
    {
        if(m.x >= 560 && m.x <= 601 && m.y>=759 && m.y<=800) //Bsp
        {

        }
    }

    public void Ausgabe()
    {
        IstVeraendert = false;
        AusgabeLoeschen();
        System.out.println(Naechster);
        System.out.println(AnzReihenZerstoert);
    }

    public void SteineBewegen()
    {
        if(ZeitVergangen < Wartezeit)
        {
            return;
        }

        Bewegen("U");
        ZeitVergangen = 0;

        if(p == null)
        {
            NeuPaket(Naechster);
        }
    }

    public void NeuPaket(String Typ)
    {
        p = new PAKET(Naechster);
        p.Platzieren();

        Naechster = ZufallStein();

        IstVeraendert = true;
    }

    public void Bewegen(String Richtung)
    {
        if(p == null)
        {
            return;
        }

        if(Richtung.equals("U"))
        {
            for(int i = 0; i<4; i++)
            {
                int u = p.Steine[i].Skin.LeseY()/50;
                int r = (p.Steine[i].Skin.LeseX()-50)/50;
                if(u < 0)
                {
                    u = 0;
                }
                if(u+1 == 18 || Fest[u+1][r] != null)
                {
                    SteinAmBoden();
                    return;
                }
            }
            for(int i = 0; i<4; i++)
            {
                p.Steine[i].Skin.SetzeY(p.Steine[i].Skin.LeseY()+50);
            }
        }
        else
        {
            if(Richtung.equals("A"))
            {
                for(int i = 0; i<4; i++)
                {
                    int u = p.Steine[i].Skin.LeseY()/50;
                    int r = (p.Steine[i].Skin.LeseX()-50)/50;
                    if(u < 0)
                    {
                        u = 0;
                    }
                    if(r <= 0 || Fest[u][r-1] != null)
                    {
                        return;
                    }
                }
                for(int i = 0; i<4; i++)
                {
                    p.Steine[i].Skin.SetzeX(p.Steine[i].Skin.LeseX()-50);
                }
            }
            if(Richtung.equals("D"))
            {
                for(int i = 0; i<4; i++)
                {
                    int u = p.Steine[i].Skin.LeseY()/50;
                    int r = (p.Steine[i].Skin.LeseX()-50)/50;
                    if(u < 0)
                    {
                        u = 0;
                    }
                    if(r >= 9 || Fest[u][r+1] != null)
                    {
                        return;
                    }
                }
                for(int i = 0; i<4; i++)
                {
                    p.Steine[i].Skin.SetzeX(p.Steine[i].Skin.LeseX()+50);
                }
            }
        }
    }

    public void LinienPruefen()
    {
        for(int u = 0; u<18; u++)
        {
            if(LinieVoll(u))
            {
                LinieLeeren(u);
            }
        }
    }

    private boolean LinieVoll(int u)
    {
        for(int r = 0; r<10; r++)
        {
            if(Fest[u][r] == null)
            {
                return false;
            }
        }
        return true;
    }

    public void LinieLeeren(int u)
    {
        for(int r = 0; r<10; r++)
        {
            Fest[u][r].Skin.SetzeY(2000);
            Fest[u][r] = null;
            AnzReihenZerstoert++;
        }
        LinienFallen(u);
    }

    public void LinienFallen(int u) //ab wo sie fallen
    {
        for(; u>0; u--)
        {
            for(int r = 0; r<10; r++)
            {
                if(Fest[u-1][r] != null)
                {
                    Fest[u][r] = Fest[u-1][r];
                    Fest[u][r].Skin.SetzeY(Fest[u][r].Skin.LeseY()+50);
                }
                else
                {
                    Fest[u][r] = null;
                }
            }
        }
    }

    public void Drehen() //90° im Uhrzeigersinn
    {
        if(p.Art.equals("I")) //ok
        {
            if(p.form == 0)
            {
                if(p.Steine[2].Skin.LeseX() > 400 || p.Steine[2].Skin.LeseX() <= 50)
                {
                    return;
                }
            }
            if(p.form == 2)
            {
                if(p.Steine[2].Skin.LeseX() > 400 || p.Steine[2].Skin.LeseX() <= 100)
                {
                    return;
                }
            }

            p.Steine[0].Skin.SetzeX(p.Steine[0].Skin.LeseX()+100*p.Steine[0].VX);
            p.Steine[0].Skin.SetzeY(p.Steine[0].Skin.LeseY()+100*p.Steine[0].VY);
            p.Steine[1].Skin.SetzeX(p.Steine[1].Skin.LeseX()+50*p.Steine[1].VX);
            p.Steine[1].Skin.SetzeY(p.Steine[1].Skin.LeseY()+50*p.Steine[1].VY);
            //Steine[2] ist mittelpunkt
            p.Steine[3].Skin.SetzeX(p.Steine[3].Skin.LeseX()+50*p.Steine[3].VX);
            p.Steine[3].Skin.SetzeY(p.Steine[3].Skin.LeseY()+50*p.Steine[3].VY);
        }
        if(p.Art.equals("J")) //ok
        {
            if(p.form == 1) // testet nur wände @all
            {
                if(p.Steine[2].Skin.LeseX() <= 50)
                {
                    return;
                }
            }
            if(p.form == 3)
            {
                if(p.Steine[2].Skin.LeseX() >= 450)
                {
                    return;
                }
            }
            if(p.form == 0)
            {
                if(p.Steine[2].Skin.LeseX() > 800)
                {
                    return;
                }
            }

            p.Steine[0].Skin.SetzeX(p.Steine[0].Skin.LeseX()+50*p.Steine[0].VX);
            p.Steine[0].Skin.SetzeY(p.Steine[0].Skin.LeseY()+50*p.Steine[0].VY);
            p.Steine[1].Skin.SetzeX(p.Steine[1].Skin.LeseX()+50*p.Steine[1].VX);
            p.Steine[1].Skin.SetzeY(p.Steine[1].Skin.LeseY()+50*p.Steine[1].VY);
            //Steine[2] ist mittelpunkt
            p.Steine[3].Skin.SetzeX(p.Steine[3].Skin.LeseX()+50*p.Steine[3].VX);
            p.Steine[3].Skin.SetzeY(p.Steine[3].Skin.LeseY()+50*p.Steine[3].VY);
        }
        if(p.Art.equals("L"))
        {
            
        }
        if(p.Art.equals("O")) //ok
        {
            return;
        }
        if(p.Art.equals("S")) //ok
        {
            if(p.form == 1)
            {
                if(p.Steine[3].Skin.LeseY() > 800)
                {
                    return;
                }
            }
            if(p.form == 0)
            {
                if(p.Steine[3].Skin.LeseX() <= 50)
                {
                    return;
                }
            }
            if(p.Steine[3].Skin.LeseY() >= 850)
            {
                return;
            }

            p.Steine[0].Skin.SetzeX(p.Steine[0].Skin.LeseX()+50*p.Steine[0].VX);
            p.Steine[0].Skin.SetzeY(p.Steine[0].Skin.LeseY()+50*p.Steine[0].VY);
            p.Steine[1].Skin.SetzeX(p.Steine[1].Skin.LeseX()+50*p.Steine[1].VX);
            p.Steine[1].Skin.SetzeY(p.Steine[1].Skin.LeseY()+50*p.Steine[1].VY);
            p.Steine[2].Skin.SetzeX(p.Steine[2].Skin.LeseX()+50*p.Steine[2].VX);
            p.Steine[2].Skin.SetzeY(p.Steine[2].Skin.LeseY()+50*p.Steine[2].VY);
            //Steine[3] ist mittelpunkt
        }
        if(p.Art.equals("T")) //ok
        {
            if(p.form == 1)
            {
                if(p.Steine[2].Skin.LeseX() <= 50)
                {
                    return;
                }
            }
            if(p.form == 3)
            {
                if(p.Steine[2].Skin.LeseX() > 450)
                {
                    return;
                }
            }
            if(p.Steine[2].Skin.LeseY() > 800)
            {
                return;
            }

            p.Steine[0].Skin.SetzeX(p.Steine[0].Skin.LeseX()+50*p.Steine[0].VX);
            p.Steine[0].Skin.SetzeY(p.Steine[0].Skin.LeseY()+50*p.Steine[0].VY);
            p.Steine[1].Skin.SetzeX(p.Steine[1].Skin.LeseX()+50*p.Steine[1].VX);
            p.Steine[1].Skin.SetzeY(p.Steine[1].Skin.LeseY()+50*p.Steine[1].VY);
            //Steine[2] ist mittelpunkt
            p.Steine[3].Skin.SetzeX(p.Steine[3].Skin.LeseX()+50*p.Steine[3].VX);
            p.Steine[3].Skin.SetzeY(p.Steine[3].Skin.LeseY()+50*p.Steine[3].VY);
        }
        if(p.Art.equals("Z")) //ok
        {
            if(p.form == 1)
            {
                if(p.Steine[2].Skin.LeseY() > 800)
                {
                    return;
                }
            }
            if(p.form == 0)
            {
                if(p.Steine[2].Skin.LeseX() > 500)
                {
                    return;
                }
            }
            if(p.Steine[2].Skin.LeseY() >= 850)
            {
                return;
            }

            p.Steine[0].Skin.SetzeX(p.Steine[0].Skin.LeseX()+50*p.Steine[0].VX);
            p.Steine[0].Skin.SetzeY(p.Steine[0].Skin.LeseY()+50*p.Steine[0].VY);
            p.Steine[1].Skin.SetzeX(p.Steine[1].Skin.LeseX()+50*p.Steine[1].VX);
            p.Steine[1].Skin.SetzeY(p.Steine[1].Skin.LeseY()+50*p.Steine[1].VY);
            //Steine[2] ist mittelpunkt
            p.Steine[3].Skin.SetzeX(p.Steine[3].Skin.LeseX()+50*p.Steine[3].VX);
            p.Steine[3].Skin.SetzeY(p.Steine[3].Skin.LeseY()+50*p.Steine[3].VY);
        }
        Vbest();
    }

    private void Vbest()
    {
        if(p.Art.equals("I"))// ok
        {
            if(p.form == 0)
            {
                p.form = 1;
                p.Steine[0].VX = -1;
                p.Steine[0].VY = 1;
                p.Steine[1].VX = -1;
                p.Steine[1].VY = 1;
                p.Steine[3].VX = 1;
                p.Steine[3].VY = -1;
                return;
            }
            if(p.form == 1)
            {
                p.form = 2;
                p.Steine[0].VX = -1;
                p.Steine[0].VY = -1;
                p.Steine[1].VX = -1;
                p.Steine[1].VY = -1;
                p.Steine[3].VX = 1;
                p.Steine[3].VY = 1;
                return;
            }
            if(p.form == 2)
            {
                p.form = 3;
                p.Steine[0].VX = 1;
                p.Steine[0].VY = -1;
                p.Steine[1].VX = 1;
                p.Steine[1].VY = -1;
                p.Steine[3].VX = -1;
                p.Steine[3].VY = 1;
                return;
            }
            if(p.form == 3)
            {
                p.form = 0;
                p.Steine[0].VX = 1;
                p.Steine[0].VY = 1;
                p.Steine[1].VX = 1;
                p.Steine[1].VY = 1;
                p.Steine[3].VX = -1;
                p.Steine[3].VY = -1;
                return;
            }
        }
        if(p.Art.equals("J"))
        {
            if(p.form == 0)
            {
                p.form = 1;
                p.Steine[0].VX = 0;
                p.Steine[0].VY = 2;
                p.Steine[1].VX = 1;
                p.Steine[1].VY = 1;
                p.Steine[3].VX = -1;
                p.Steine[3].VY = -1;
                return;
            }
            if(p.form == 1)
            {
                p.form = 2;
                p.Steine[0].VX = -2;
                p.Steine[0].VY = 0;
                p.Steine[1].VX = -1;
                p.Steine[1].VY = 1;
                p.Steine[3].VX = 1;
                p.Steine[3].VY = -1;
                return;
            }
            if(p.form == 2)
            {
                p.form = 3;
                p.Steine[0].VX = 0;
                p.Steine[0].VY = -2;
                p.Steine[1].VX = -1;
                p.Steine[1].VY = -1;
                p.Steine[3].VX = 1;
                p.Steine[3].VY = 1;
                return;
            }
            if(p.form == 3)
            {
                p.form = 0;
                p.Steine[0].VX = 2;
                p.Steine[0].VY = 0;
                p.Steine[1].VX = 1;
                p.Steine[1].VY = -1;
                p.Steine[3].VX = -1;
                p.Steine[3].VY = 1;
                return;
            }
        }
        if(p.Art.equals("L"))
        {

        }
        if(p.Art.equals("O"))// ok
        {
            return;
        }
        if(p.Art.equals("S"))// ok
        {
            if(p.form == 0)
            {
                p.form = 1;
                p.Steine[0].VX = -1;
                p.Steine[0].VY = -1;
                p.Steine[1].VX = 0;
                p.Steine[1].VY = -2;
                p.Steine[2].VX = -1;
                p.Steine[2].VY = 1;
                return;
            }
            if(p.form == 1)
            {
                p.form = 0;
                p.Steine[0].VX = 1;
                p.Steine[0].VY = 1;
                p.Steine[1].VX = 0;
                p.Steine[1].VY = 2;
                p.Steine[2].VX = 1;
                p.Steine[2].VY = -1;
                return;
            }
        }
        if(p.Art.equals("T"))// ok
        {
            if(p.form == 0)
            {
                p.form = 1;
                p.Steine[0].VX = -1;
                p.Steine[0].VY = 1;
                p.Steine[1].VX = 1;
                p.Steine[1].VY = 1;
                p.Steine[3].VX = -1;
                p.Steine[3].VY = -1;
                return;
            }
            if(p.form == 1)
            {
                p.form = 2;
                p.Steine[0].VX = -1;
                p.Steine[0].VY = -1;
                p.Steine[1].VX = -1;
                p.Steine[1].VY = 1;
                p.Steine[3].VX = 1;
                p.Steine[3].VY = -1;
                return;
            }
            if(p.form == 2)
            {
                p.form = 3;
                p.Steine[0].VX = 1;
                p.Steine[0].VY = -1;
                p.Steine[1].VX = -1;
                p.Steine[1].VY = -1;
                p.Steine[3].VX = 1;
                p.Steine[3].VY = 1;
                return;
            }
            if(p.form == 3)
            {
                p.form = 0;
                p.Steine[0].VX = 1;
                p.Steine[0].VY = 1;
                p.Steine[1].VX = 1;
                p.Steine[1].VY = -1;
                p.Steine[3].VX = -1;
                p.Steine[3].VY = 1;
                return;
            }
        }
        if(p.Art.equals("Z"))// ok
        {
            if(p.form == 0)
            {
                p.form = 1;
                p.Steine[0].VX = 1;
                p.Steine[0].VY = -1;
                p.Steine[1].VX = 0;
                p.Steine[1].VY = -2;
                p.Steine[3].VX = 1;
                p.Steine[3].VY = 1;
                return;
            }
            if(p.form == 1)
            {
                p.form = 0;
                p.Steine[0].VX = -1;
                p.Steine[0].VY = 1;
                p.Steine[1].VX = 0;
                p.Steine[1].VY = 2;
                p.Steine[3].VX = -1;
                p.Steine[3].VY = -1;
                return;
            }
        }
    }

    public void SteinAmBoden()
    {
        for(int i = 0; i<4; i++)
        {
            int u = p.Steine[i].Skin.LeseY()/50;
            int r = (p.Steine[i].Skin.LeseX()-50)/50;
            if(u <= 0)
            {
                GameOver();
                return;
            }
            Fest[u][r] = p.Steine[i];
        }
        p = null;
    }

    public String ZufallStein()
    {
        int z = (int)(Math.random()*7+1);
        if(z == 1){return "I";}
        if(z == 2){return "J";}
        if(z == 3){return "L";}
        if(z == 4){return "O";}
        if(z == 5){return "S";}
        if(z == 6){return "T";}
        if(z == 7){return "Z";}
        return null;
    }

    public void GameOver()
    {
        System.out.println("Game Over");
        c.stop();
    }

    //Ende Run

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }
}
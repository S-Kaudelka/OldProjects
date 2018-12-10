import java.awt.Point;

public class SPIEL 
{
    public static final int SpielHoehe = 600;
    public static final int SpielBreite = 450;

    private CHICKEN chicken;
    private PLATFORM[] p;
    private CONTROLER c;

    public BILD hintergrund;

    private boolean IstVeraendert;

    public int score;

    public int anzPlat;

    public boolean Spezial;
    public int GleichY;

    public SPIEL(CONTROLER c_p)
    {
        c = c_p;
        hintergrund = new BILD("image\\background.png");
        chicken = new CHICKEN();

        score = SpielHoehe/2;
        anzPlat = 15;

        p = new PLATFORM[anzPlat];
        for(int i = 0; i<anzPlat-1; i++) //Gehoert noch besser gemacht
        {
            p[i] = new PLATFORM((int)(Math.random()*SpielBreite), (int)(Math.random()*SpielHoehe));
        }
        p[anzPlat-1] = new PLATFORM();
        p[anzPlat-1].Platform.SetzeX((SpielBreite-p[anzPlat-1].Platform.LeseBreite())/2);
        p[anzPlat-1].Platform.SetzeY(SpielHoehe-p[anzPlat-1].Platform.LeseHoehe());
        p[anzPlat-1].Bewegen = false;
    }

    public void Action(KEYSTATE keystate)
    {
        Point P = keystate.PickLastMouseClickPosition();
        if(P != null)
        {
            MausklickUeberpruefen(P);
        }

        Kollisionsabfrage();
        Springen();

        Platformen();

        TastenPruefen(keystate);
    }

    //teile von Action-Methode

    public void MausklickUeberpruefen(Point p) //es wurde irgendwo hingeklickt
    {

    }

    public void Kollisionsabfrage()
    {
        if(chicken.Faellt)
        {
            for(int i = 0; i<anzPlat; i++)
            {
                if(p[i].Platform.LeseX()<chicken.Chicken.LeseX()+chicken.Chicken.LeseBreite() &&
                chicken.Chicken.LeseX() < p[i].Platform.LeseX()+p[i].Platform.LeseBreite() &&
                p[i].Platform.LeseY()-(chicken.Chicken.LeseY()+chicken.Chicken.LeseHoehe())<5 &&
                p[i].Platform.LeseY()-(chicken.Chicken.LeseY()+chicken.Chicken.LeseHoehe())>=-5)
                {
                    if(chicken.Chicken.LeseY() == chicken.SprungPositionY)
                    {
                        GleichY++;
                        if(GleichY == 10)
                        {
                            GleichY = 0;
                            Spezial = true;
                        }
                    }
                    else
                    {
                        GleichY = 0;
                        Spezial = false;
                    }
                    chicken.SprungPositionY = chicken.Chicken.LeseY();
                    chicken.Faellt = false;
                }
            }
        }
    }

    public void Springen()
    {
        if(Spezial)
        {
            chicken.Sprunghoehe = chicken.SpezialHoehe;
        }
        else
        {
            chicken.Sprunghoehe = chicken.NormalHoehe;
        }
        if(chicken.Faellt) //er faellt
        {
            if(chicken.Chicken.LeseY() >= SPIEL.SpielHoehe)
            {
                c.end();
                System.out.println("GameOver");
                System.out.println("Score: " + score/10);
                hintergrund = new BILD("image\\gameover0.png");
                return;
            }

            chicken.Chicken.SetzeY(chicken.Chicken.LeseY()+chicken.FallGeschwindigkeit);
            score = score - chicken.FallGeschwindigkeit;
        }
        else // er springt
        {
            if(chicken.Chicken.LeseY()+chicken.Sprunghoehe > chicken.SprungPositionY)
            {
                if(chicken.Chicken.LeseY() > SPIEL.SpielHoehe/2 || Spezial)
                {
                    chicken.Chicken.SetzeY(chicken.Chicken.LeseY()-chicken.SprungGeschwindigkeit);
                    score = score + chicken.FallGeschwindigkeit;
                }
                else
                {
                    HintergrundRunter();
                }
            }
            else
            {
                chicken.Faellt = true;
            }
        }
    }

    public void Platformen()
    {
        for(int i = 0; i<anzPlat; i++)
        {
            if(p[i].Platform.LeseY()>SpielHoehe)
            {
                p[i] = new PLATFORM((int)(Math.random()*(SpielBreite-35)), (int)(Math.random()*(SpielHoehe/2)));

                //wenns laggt, MaxDurchlaeufe runtersetzen
                int a = 0;
                int MaxDurchlaeufe = 1;
                while(ZuNah(p[i].Platform, i) && a<MaxDurchlaeufe)
                {
                    p[i].Platform.SichtbarSetzen(false);
                    p[i] = new PLATFORM((int)(Math.random()*(SpielBreite-35)), (int)(Math.random()*(SpielHoehe/2)));
                    a++;
                }
            }
        }
        
        for(int i = 0; i<anzPlat; i++)
        {
            p[i].Bewegen();
        }
    }

    private boolean ZuNah(BILD a, int b)
    {
        for(int i = 0; i<anzPlat; i++)
        {
            if(i != b)
            {
                if(p[b].Platform.Abstand(p[i].Platform)<60)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public void HintergrundRunter()
    {
        for(int i = 0; i<anzPlat; i++)
        {
            p[i].Platform.SetzeY(p[i].Platform.LeseY()+chicken.SprungGeschwindigkeit);
        }
        chicken.SprungPositionY = chicken.SprungPositionY+chicken.SprungGeschwindigkeit;
        score = score + chicken.FallGeschwindigkeit;

        //wenn platformen aus dem spiel verschwinden
    }

    public void TastenPruefen(KEYSTATE keystate)
    {
        if(keystate.IsPressed("A"))
        {
            if(chicken.Chicken.LeseX()>=15)
            {
                chicken.Chicken.SetzeX(chicken.Chicken.LeseX()-15);
            }
        }

        if(keystate.IsPressed("D"))
        {
            if(chicken.Chicken.LeseX()+ chicken.Chicken.LeseBreite()<= SpielBreite-15)
            {
                chicken.Chicken.SetzeX(chicken.Chicken.LeseX()+15);
            }
        }
    }

    public int Betrag(int a)
    {
        if(a < 0)
        {
            return -a;
        }
        else
        {
            return a;
        }
    }
}
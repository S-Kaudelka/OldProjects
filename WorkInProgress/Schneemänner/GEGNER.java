public class GEGNER
{
    public BILD Skin;
    public int Leben;
    private int Geschw;
    private int Schaden;
    private SPIELER s;

    public GEGNER(SPIELER sp) // für hoehere wellen parameter, die die Werte ändern
    {
        Skin = new BILD("Grafiken\\schneeman.png");
        int Breite = Skin.LeseBreite()/5;
        int Hoehe = Skin.LeseHoehe()/5;
        Skin.SetzeBreite(Breite);
        Skin.SetzeHoehe(Hoehe);
        s = sp;
        int a = (int) (Math.random()*2);
        int b = (int) (Math.random()*2);

        int x;
        int y;
        if(a == 0)
        {
            x = (int) (Math.random()*(GAMEWINDOW.Breite+(Breite*2))-Breite);
            if(b == 0)
            {
                y = -Hoehe/2;
            }
            else
            {
                y = GAMEWINDOW.Hoehe-Hoehe/2;
            }
        }
        else
        {
            y = (int) (Math.random()*(GAMEWINDOW.Hoehe+(Hoehe*2))-Hoehe);
            if(b == 0)
            {
                x = -Breite/2;
            }
            else
            {
                x = GAMEWINDOW.Breite-Breite/2;
            }
        }

        Skin.SetzeX(x);
        Skin.SetzeY(y);

        Leben = (int) (Math.random()*3+1); //1-3 Leben
        Geschw = 2;
        Schaden = 1;
        
        if(CONTROLER.spezialFeature)
        {
            Leben = 1;
            Geschw = 1;
        }
        
        KI();
    }

    public void KI()
    { 
        if(!(s.Skin.LeseX()+s.Skin.LeseBreite()/2 <= Skin.LeseX()+Skin.LeseBreite()/2+2 &&
             s.Skin.LeseX()+s.Skin.LeseBreite()/2 >= Skin.LeseX()+Skin.LeseBreite()/2-2 ))
        {
            if(s.Skin.LeseX()+s.Skin.LeseBreite()/2 < Skin.LeseX()+Skin.LeseBreite()/2){  // spieler links von Gegner
                Skin.SetzeX(Skin.LeseX()-Geschw);
            }else{                              // Spieler rechts von Gegner
                Skin.SetzeX(Skin.LeseX()+Geschw);
            }
        }
        
        if(!(s.Skin.LeseY()+s.Skin.LeseHoehe()/2 <= Skin.LeseY()+Skin.LeseHoehe()/2+2 &&
             s.Skin.LeseY()+s.Skin.LeseHoehe()/2 >= Skin.LeseY()+Skin.LeseHoehe()/2-2 ))
        {
            if(s.Skin.LeseY()+s.Skin.LeseHoehe()/2 < Skin.LeseY()+Skin.LeseHoehe()/2){  // spieler links von Gegner
                Skin.SetzeY(Skin.LeseY()-Geschw);
            }else{                              // spieler links von Gegner
                Skin.SetzeY(Skin.LeseY()+Geschw);
            }
        }
    }
}
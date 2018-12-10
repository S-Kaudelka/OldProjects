public class WEG
{
    private WEGABSCHNITT[] Weg;
    private int AnzAbschn;
    private int Nummer;
    
    private boolean erreichtX;
    private boolean erreichtY;
    
    public WEG(int Lvl)
    {
        //Hier den Weg definieren, den die Gegner gehen sollen
        // -1 => keine Angabe
        if(Lvl == 0)
        {
            AnzAbschn = 1;
            Weg = new WEGABSCHNITT[AnzAbschn];
            Weg[0] = new WEGABSCHNITT(-100, -1);
        }
        if(Lvl == 1)
        {
            AnzAbschn = 2;
            Weg = new WEGABSCHNITT[AnzAbschn];
            Weg[0] = new WEGABSCHNITT(740, 725);
            Weg[1] = new WEGABSCHNITT(-75, -75);
        }
        if(Lvl == 2)
        {
            AnzAbschn = 4;
            Weg = new WEGABSCHNITT[AnzAbschn];
            Weg[0] = new WEGABSCHNITT(-1, 225);
            Weg[1] = new WEGABSCHNITT(1075, -1);
            Weg[2] = new WEGABSCHNITT(-1, 575);
            Weg[3] = new WEGABSCHNITT(-100, -1);
        }
    }
    
    public void GehrichtungBestimmen(GEGNER g)
    {
        int x = 0;
        int y = 0;
        
        if(Nummer == AnzAbschn)
        {
            g.bewX = 0;
            g.bewY = 0;
            return;
        }
        
        if(Weg[Nummer].X != -1 && !erreichtX)
        {
            if(g.Skin.LeseX() >= Weg[Nummer].X-2 && g.Skin.LeseX() <= Weg[Nummer].X+2)
            {
                erreichtX = true;
            }
            else
            {
                if(g.Skin.LeseX() < Weg[Nummer].X)
                {
                    x = 1;
                }
                else
                {
                    x = -1;
                }
            }
        }
        else
        {
            x = 0;
        }
        if(Weg[Nummer].Y != -1 && !erreichtY)
        {
            if(g.Skin.LeseY() >= Weg[Nummer].Y-2 && g.Skin.LeseY() <= Weg[Nummer].Y+2)
            {
                erreichtY = true;
            }
            else
            {
                if(g.Skin.LeseY() < Weg[Nummer].Y)
                {
                    y = 1;
                }
                else
                {
                    y = -1;
                }
            }
        }
        else
        {
            y = 0;
        }
        
        if( (erreichtX || Weg[Nummer].X == -1) && (erreichtY || Weg[Nummer].Y == -1) )
        {
            Nummer++;
            erreichtX = false;
            erreichtY = false;
        }
        
        g.bewX = g.Speed*x;
        g.bewY = g.Speed*y;
    }
}
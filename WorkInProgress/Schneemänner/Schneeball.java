public class Schneeball
{
    public BILD Ball;
    
    public boolean loeschen;

    private int bewX;
    private int bewY;
    private int Geschw;
    
    public Schneeball(int x, int y, int r) //Erzeugung eines neuen Schneeballbildes
    {
        if(CONTROLER.spezialFeature){
            Ball = new BILD("Grafiken\\bombe.png");
        }else{
            Ball = new BILD("Grafiken\\schneeball.png");
        }
        
        Ball.SetzeBreite(25);
        Ball.SetzeHoehe(25);
        Ball.SetzeX(x);
        Ball.SetzeY(y);
        Ball.SichtbarSetzen(true);
        BewegungBest(r);
    }
    
    public void BewegungBest(int r) //Bestimmen der Bewegung
    {
        if(r == 1){ BewTeil(0, -1);} //Oben
        if(r == 2){ BewTeil(1, -1);} //Oben Rechts
        if(r == 3){ BewTeil(1, 0);} //Rechts
        if(r == 4){ BewTeil(1, 1);} //Rechts Unten
        if(r == 5){ BewTeil(0, 1);} //Unten
        if(r == 6){ BewTeil(-1, 1);} //Unten Links
        if(r == 7){ BewTeil(-1, 0);} //Links
        if(r == 8){ BewTeil(-1, -1);} //Links Oben
    }
    
    public void BewTeil(int x, int y) // x,y => -1 || 0 || 1 ; geben bewegung an als Vorfaktor
    {
        if(bewX == 0 || bewY == 0)
        {
            Geschw = 8;
        }
        else
        {
            Geschw = 4;
        }
        
        bewX = x*Geschw;
        bewY = y*Geschw;
    }
    
    public void Bewegen()
    {
        Ball.SetzeX(Ball.LeseX()+bewX);
        Ball.SetzeY(Ball.LeseY()+bewY);
        
        if(!(Ball.LeseX() > -10 && Ball.LeseX() < GAMEWINDOW.Breite+10 && Ball.LeseY() > -10 && Ball.LeseY() < GAMEWINDOW.Hoehe+10))
        {
            loeschen = true;
            Ball.SichtbarSetzen(false);
        }
    }
}
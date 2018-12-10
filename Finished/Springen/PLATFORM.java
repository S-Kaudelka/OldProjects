public class PLATFORM
{
    public BILD Platform;
    
    public PLATFORM()
    {
        Platform = new BILD("Platform.png");
        Platform.SetzeX((int)(Math.random()*SPIEL.Breite));
        Platform.SetzeY((int)(Math.random()*SPIEL.Hoehe));
        Platform.TransparenzSetzen(255, 255, 255);
    }
}

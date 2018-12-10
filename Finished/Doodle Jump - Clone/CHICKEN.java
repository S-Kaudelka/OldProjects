public class CHICKEN
{
    public BILD Chicken;
    public int SprungGeschwindigkeit;
    public int FallGeschwindigkeit;
    public int SprungPositionY;
    public boolean Faellt;
    public int Sprunghoehe;
    public int SpezialHoehe;
    public int NormalHoehe;
    
    public CHICKEN ()
    {
        Chicken = new BILD ("image\\Chicken.jpg");
        Chicken.SetzeBreite(55);
        Chicken.SetzeHoehe(80);
        Chicken.SetzeX(SPIEL.SpielBreite/2-Chicken.LeseBreite()/2);
        Chicken.SetzeY(SPIEL.SpielHoehe-Chicken.LeseHoehe());
        Chicken.TransparenzSetzen(255, 255, 255);
        
        SprungPositionY = Chicken.LeseY();
        FallGeschwindigkeit = 10;
        SprungGeschwindigkeit = 10;
        Sprunghoehe = 150;
        SpezialHoehe = 350;
        NormalHoehe = Sprunghoehe;
        Faellt = false;
    }
}

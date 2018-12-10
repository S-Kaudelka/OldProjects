public class PLATFORM
{
    public BILD Platform;
    public boolean Bewegen;
    
    private int anzBeweg;
    private boolean nachLinks;
    
    public PLATFORM()
    {
        Neu();
    }
    
    public PLATFORM(int x, int y)
    {
        Neu();
        Platform.SetzeX(x);
        Platform.SetzeY(y);
    }
    
    public void NeuePosition(int x, int y){
        Platform.SetzeX(x);
        Platform.SetzeY(y);
    }
    
    private void Neu()
    {
        Platform = new BILD("image\\Plattform.png");
        Platform.SetzeBreite(60);
        Platform.SetzeHoehe(20);
        Platform.TransparenzSetzen(255, 255, 255);
        int a = (int) (Math.random()*10);
        if(a < 5)
        {
            Bewegen = true;
        }
        anzBeweg = (int) (Math.random()*35);
    }
    
    public void Bewegen()
    {
        if(!Bewegen)
        {
            return;
        }
        if(nachLinks)
        {
            Platform.SetzeX(Platform.LeseX()-4);
            anzBeweg++;
            if(anzBeweg == 35)
            {
                nachLinks = false;
                anzBeweg = 0;
            }
        }
        else
        {
            Platform.SetzeX(Platform.LeseX()+4);
            anzBeweg++;
            if(anzBeweg == 35)
            {
                nachLinks = true;
                anzBeweg = 0;
            }
        }
    }
}
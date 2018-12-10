public class STEIN
{
    public BILD Skin;
    
    public int VX;
    public int VY;
    
    public STEIN(String Typ)
    {
        Skin = new BILD("s"+Typ+".png");
    }
    
    public STEIN(String Typ, int x)
    {
        Skin = new BILD("s"+Typ+".png");
        Skin.SetzeX(x);
    }
    
    public STEIN(String Typ, int vx, int vy)
    {
        Skin = new BILD("s"+Typ+".png");
        VX = vx;
        VY = vy;
    }
}
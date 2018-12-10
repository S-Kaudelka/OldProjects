public class PAKET
{
    public STEIN[] Steine;
    public String Art;
    public int form;
    
    public PAKET(String Typ)
    {
        Art = Typ;
        Steine = new STEIN[4];
        Steine[0] = new STEIN(Typ);
        Steine[1] = new STEIN(Typ);
        Steine[2] = new STEIN(Typ);
        Steine[3] = new STEIN(Typ);
    }
    
    public void Platzieren()
    {
        if(Art.equals("I")) // ok
        {
            Steine[0].Skin.SetzeX(250);
            Steine[0].Skin.SetzeY(-150);
            Steine[0].VX = 1;
            Steine[0].VY = 1;
            Steine[1].Skin.SetzeX(250);
            Steine[1].Skin.SetzeY(-100);
            Steine[1].VX = 1;
            Steine[1].VY = 1;
            Steine[2].Skin.SetzeX(250);
            Steine[2].Skin.SetzeY(-50);
            Steine[2].VX = 0;
            Steine[2].VY = 0;
            Steine[3].Skin.SetzeX(250);
            Steine[3].Skin.SetzeY(0);
            Steine[3].VX = -1;
            Steine[3].VY = -1;
        }
        if(Art.equals("J"))
        {
            Steine[0].Skin.SetzeX(200);
            Steine[0].Skin.SetzeY(-50);
            Steine[0].VX = 2;
            Steine[0].VY = 0;
            Steine[1].Skin.SetzeX(200);
            Steine[1].Skin.SetzeY(0);
            Steine[1].VX = 1;
            Steine[1].VY = -1;
            Steine[2].Skin.SetzeX(250);
            Steine[2].Skin.SetzeY(0);
            Steine[2].VX = 0;
            Steine[2].VY = 0;
            Steine[3].Skin.SetzeX(300);
            Steine[3].Skin.SetzeY(0);
            Steine[3].VX = -1;
            Steine[3].VY = 1;
        }
        if(Art.equals("L"))
        {
            Steine[0].Skin.SetzeX(300);
            Steine[0].Skin.SetzeY(-50);
            Steine[1].Skin.SetzeX(200);
            Steine[1].Skin.SetzeY(0);
            Steine[2].Skin.SetzeX(250);
            Steine[2].Skin.SetzeY(0);
            Steine[3].Skin.SetzeX(300);
            Steine[3].Skin.SetzeY(0);
        }
        if(Art.equals("O")) // ok
        {
            Steine[0].Skin.SetzeX(250);
            Steine[0].Skin.SetzeY(-50);
            Steine[1].Skin.SetzeX(300);
            Steine[1].Skin.SetzeY(-50);
            Steine[2].Skin.SetzeX(250);
            Steine[2].Skin.SetzeY(0);
            Steine[3].Skin.SetzeX(300);
            Steine[3].Skin.SetzeY(0);
        }
        if(Art.equals("S"))
        {
            Steine[0].Skin.SetzeX(250);
            Steine[0].Skin.SetzeY(-50);
            Steine[0].VX = 1;
            Steine[0].VY = 1;
            Steine[1].Skin.SetzeX(300);
            Steine[1].Skin.SetzeY(-50);
            Steine[1].VX = 0;
            Steine[1].VY = 2;
            Steine[2].Skin.SetzeX(200);
            Steine[2].Skin.SetzeY(0);
            Steine[2].VX = 1;
            Steine[2].VY = -1;
            Steine[3].Skin.SetzeX(250);
            Steine[3].Skin.SetzeY(0);
            Steine[3].VX = 0;
            Steine[3].VY = 0;
        }
        if(Art.equals("T")) //ok
        {
            Steine[0].Skin.SetzeX(250);
            Steine[0].Skin.SetzeY(-50);
            Steine[0].VX = 1;
            Steine[0].VY = 1;
            Steine[1].Skin.SetzeX(200);
            Steine[1].Skin.SetzeY(0);
            Steine[1].VX = 1;
            Steine[1].VY = -1;
            Steine[2].Skin.SetzeX(250);
            Steine[2].Skin.SetzeY(0);
            Steine[2].VX = 0;
            Steine[2].VY = 0;
            Steine[3].Skin.SetzeX(300);
            Steine[3].Skin.SetzeY(0);
            Steine[3].VX = -1;
            Steine[3].VY = 1;
        }
        if(Art.equals("Z"))
        {
            Steine[0].Skin.SetzeX(250);
            Steine[0].Skin.SetzeY(-50);
            Steine[0].VX = -1;
            Steine[0].VY = 1;
            Steine[1].Skin.SetzeX(200);
            Steine[1].Skin.SetzeY(-50);
            Steine[1].VX = 0;
            Steine[1].VY = 2;
            Steine[2].Skin.SetzeX(250);
            Steine[2].Skin.SetzeY(0);
            Steine[2].VX = 0;
            Steine[2].VY = 0;
            Steine[3].Skin.SetzeX(300);
            Steine[3].Skin.SetzeY(0);
            Steine[3].VX = -1;
            Steine[3].VY = -1;
        }
    }
}

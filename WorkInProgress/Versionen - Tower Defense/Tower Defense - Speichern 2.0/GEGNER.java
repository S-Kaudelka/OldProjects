public class GEGNER
{
    public BILD Skin;
    public int LebenMax;
    public String Zustand;
    
    public BILD Lebensbalken;
    public BILD RestLebensbalken;
    
    public WEG Weg;
    public int bewX;
    public int bewY;
    
    public int StreckeZurueckgelegt;
    
    //Variablen, die ihre Werte aus WERTE bekommen
    public String SkinName;
    public int Leben;
    public int Speed;
    public int Ruestung;
    public int Lebensverlust;
    public int GoldWert;
    
    public GEGNER(int Nummer, WEG w)
    {
        Zustand="AmLeben";
        GegnerErzeugen(Nummer);
        RestLebensbalken = new BILD("eRestLebensbalken.PNG");
        Lebensbalken = new BILD("eLebensbalken.PNG");
        
        Weg = w;
    }
    
    public GEGNER()
    {
        //Nur für Wiki
    }
    
    public void GegnerErzeugen(int N)
    {
        WERTE.GegnerWerteBeimErzeugen(N, this);
        
        Skin = new BILD(SkinName+".PNG");
        LebenMax=Leben;
        Skin.TransparenzSetzen(255,255,255);
    }
    
    public void Bewegen()
    {
        if(Leben>0)
        {
            Weg.GehrichtungBestimmen(this);
            Skin.SetzeX(Skin.LeseX()+bewX);
            Skin.SetzeY(Skin.LeseY()+bewY);
            Lebensbalken.SetzeX(Skin.LeseX());
            RestLebensbalken.SetzeX(Skin.LeseX());
            Lebensbalken.SetzeY(Skin.LeseY());
            RestLebensbalken.SetzeY(Skin.LeseY());
            LifeBalken();
            int bewa = 0;
            int bewb = 0;
            if(bewX != 0 && Skin.LeseX() >= 0  && Skin.LeseX() <= 1500)
            {
                bewa = Speed;
            }
            if(bewY != 0 && Skin.LeseY() >= 0 && Skin.LeseY() <= 800)
            {
                bewb = Speed;
            }
            StreckeZurueckgelegt = StreckeZurueckgelegt + bewa + bewb;
        }
    }
    
    public void LifeBalken()
    {
        double Prozent = Leben*100;
        Prozent = Prozent/LebenMax;
        Lebensbalken.SetzeBreite((int)(Prozent/2));
    }
}
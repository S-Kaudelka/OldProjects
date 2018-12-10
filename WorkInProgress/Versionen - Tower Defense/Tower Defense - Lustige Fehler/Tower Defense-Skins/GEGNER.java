public class GEGNER
{
    public WERTE[] Werte;
    public BILD Skin;
    public int Leben;
    public int LebenMax;
    public int Speed;
    public int Ruestung;
    public int Lebensverlust;
    public int GoldWert;
    public String Zustand;
    public String SN;
    
    public BILD Lebensbalken;
    public BILD RestLebensbalken;
    
    public GEGNER(int Nummer, WERTE[] W)
    {
        Zustand="AmLeben";
        Werte = W;
        GegnerErzeugen(Nummer);
        RestLebensbalken = new BILD("eRestLebensbalken.PNG");
        Lebensbalken = new BILD("eLebensbalken.PNG");
    }
    
    public void GegnerErzeugen(int N)
    {
        SN = Werte[N].SkinName;
        Skin = new BILD(SN+".PNG");
        Leben=Werte[N].Leben;
        LebenMax=Leben;
        Speed=Werte[N].Speed;
        Ruestung=Werte[N].Ruestung;
        Lebensverlust=Werte[N].Lebensverlust;
        Skin.TransparenzSetzen(255,255,255);
        GoldWert=Werte[N].GoldWert;
    }
    
    public void Bewegen()
    {
        if(Leben>0)
        {
            Skin.SetzeX(Skin.LeseX()-Speed);
            Lebensbalken.SetzeX(Skin.LeseX());
            RestLebensbalken.SetzeX(Skin.LeseX());
            LifeBalken();
        }
    }
    
    public void LifeBalken()
    {
        double Prozent = Leben*100;
        Prozent = Prozent/LebenMax;
        Lebensbalken.SetzeBreite((int)(Prozent/2));
    }
}
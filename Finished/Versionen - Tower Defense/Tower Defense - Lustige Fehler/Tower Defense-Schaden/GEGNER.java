public class GEGNER
{
    public WERTE[] Werte;
    public BILD Skin;
    public int Leben;
    public int Speed;
    public int Ruestung;
    public int Lebensverlust;
    public int GoldWert;
    public String Zustand;
    private String SN;
    public GEGNER(int Nummer, WERTE[] W)
    {
        Zustand="AmLeben";
        Werte = W;
        GegnerErzeugen(Nummer);
    }
    
    public void GegnerErzeugen(int N)
    {
        SN = Werte[N].SkinName;
        Skin = new BILD(SN+".PNG");
        Leben=Werte[N].Leben;
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
        }
    }
}
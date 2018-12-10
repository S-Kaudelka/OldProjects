public class FIGUR
{
    public String Farbe;
    public String Art;
    public int X;
    public String Y;
    public int FN; //Feldnummer
    
    public boolean Bewegt; //Für Rochade
    
    public BILD Skin;
    
    public FIGUR(String farbe, String art, int x, String y)
    {
        Farbe = farbe;
        Art = art;
        X = x;
        Y = y;
        
        FN = x-1+BzZ(y)*8;
        
        Skin = new BILD("images\\" + Art + Farbe + ".png");
        Skin.SetzeBreite(35);
        Skin.SetzeHoehe(65);
        
        BildBew();
        
        //Skin.TransparenzSetzen(255, 255, 255);
    }
    
    public void BildBew()
    {
        Skin.SetzeX((X-1)*75+20);
        Skin.SetzeY(BzZ(Y)*75+5);
    }
    
    /* Buchstabe zu Zahl */
    public static int BzZ(String b)
    {
        if(b.equals("A")){return 0;}
        if(b.equals("B")){return 1;}
        if(b.equals("C")){return 2;}
        if(b.equals("D")){return 3;}
        if(b.equals("E")){return 4;}
        if(b.equals("F")){return 5;}
        if(b.equals("G")){return 6;}
        if(b.equals("H")){return 7;}
        return 0;
    }
    
    /* Zahl zu Buchstabe */
    public static String ZzB(int z)
    {
        if(z == 0){return "A";}
        if(z == 1){return "B";}
        if(z == 2){return "C";}
        if(z == 3){return "D";}
        if(z == 4){return "E";}
        if(z == 5){return "F";}
        if(z == 6){return "G";}
        if(z == 7){return "H";}
        return "";
    }
}
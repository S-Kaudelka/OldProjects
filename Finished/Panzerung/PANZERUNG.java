public class PANZERUNG
{
    /*
     * Panzerung bei World of Tanks berechnen
     * 
     * Effektive Dicke berechnen und besten Winkel fuers schraege Hinstellen
     */
    ;
    //Effektive Dicke berechnen
    public double DickeBerechnen(double Winkel, int Wert)
    {
        double Faktor = 1/Math.sin(Math.toRadians(Winkel));
        double Dicke = Runden(Wert*Faktor);
        return Dicke;
    }
    
    public void BestenWinkel(int PanzerungA, int PanzerungB)
    {
        double[] d = new double[88];
        int i_p = 0;
        
        if(PanzerungA < PanzerungB)
        {
            int zw = PanzerungA;
            PanzerungA = PanzerungB;
            PanzerungB = zw;
        }
        
        
        
        for(int i = 1; i<89; i++)
        {
            d[i-1] = DickeBerechnen(i, PanzerungA)-DickeBerechnen(90-i, PanzerungB);
        }
        
        for(int i = 0; i<88; i++)
        {
            d[i] = Betrag(d[i]);
        }
        
        for(int i = 1; i<88; i++)
        {
            if(d[i] < d[i_p])
            {
                i_p = i;
            }
        }
        
        double DickeA = DickeBerechnen(i_p+1, PanzerungA);
        double DickeB = DickeBerechnen(90-i_p-1, PanzerungB);
        
        System.out.println("Bester Auftreffwinkel bei der dickeren Panzerung("+ PanzerungA + "mm): " + (i_p+1) + "°");
        System.out.println("Winkel bei der dünneren Panzerung("+ PanzerungB + "mm): " + (90-(i_p+1)) + "°");
        System.out.println("Werte: " + DickeA + "mm/ " + DickeB + "mm");
    }
    
    //auf eine Nachkommastelle
    private double Runden(double a)
    {
        int b = (int) (a*10);
        double c = b/1;
        return c/10;
    }
    
    private double Betrag(double a)
    {
        if(a < 0)
        {
            return a*-1;
        }
        else
        {
            return a;
        }
    }
}

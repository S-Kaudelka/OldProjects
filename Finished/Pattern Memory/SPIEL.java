import java.awt.Point;

public class SPIEL
{
    public boolean Marked;

    public BILD[][] Felder;
    
    private int Size; // size of the Array
    
    public int X_Marked;
    public int Y_Marked;
    
    public int count;

    public SPIEL(int Size_p)
    {
        Size = Size_p;
        Felder = new BILD[Size][Size];
        for(int i = 0; i < Size; i++)
        {
            for(int j = 0; j < Size; j++)
            {
                Felder[i][j] = new BILD("Feld.png");
                Felder[i][j].SetzeX(10+ j*60);
                Felder[i][j].SetzeY(10+ i*60);
            }
        }
    }

    public void RUN(KEYSTATE keystate)
    {
        Point p = keystate.PickLastMouseClickPosition();
        
        if(Marked)
            return;
        
        if(p != null)
        {
            MausklickUeberpruefen(p);
        }
    }

    //
    //
    // Teile der RUN-Methode
    //
    //

    public void MausklickUeberpruefen(Point m)
    {
        for(int i = 0; i < Size; i++)
        {
            for(int j = 0; j < Size; j++)
            {
                if(Felder[i][j].PunktBeruehrt(m))
                {
                    if(Felder[i][j].LeseName().equals("Feld.png"))
                    {
                        int X = Felder[i][j].LeseX();
                        int Y = Felder[i][j].LeseY();
                        Felder[i][j].SetzeX(-1000);
                        Felder[i][j] = new BILD("FeldM.png");
                        Felder[i][j].SetzeX(X);
                        Felder[i][j].SetzeY(Y);
                        Marked = true;
                        X_Marked = j;
                        Y_Marked = i;
                        
                        count++;
                    }
                }
            }
        }
    }

    //Ende Run
}
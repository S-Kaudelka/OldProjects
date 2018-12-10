import java.awt.Point;

public class SPIEL
{
    private boolean IstVeraendert;
    
    public BILD background;

    //first [] == y, second [] == x
    private POSITION[][] pos;
    
    public SPIEL()
    {
        background = new BILD("images\\background.png");
        pos = new POSITION[9][9];
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++){
                pos[i][j] = new POSITION(j,i);
            }
    }

    public void RUN(KEYSTATE keystate)
    {
        TastenPruefen(keystate);
        
        Point p = keystate.PickLastMouseClickPosition();
        if(p != null)
        {
            MausklickUeberpruefen(p);
        }
        
        if(IstVeraendert==true)
        {
            Ausgabe();
        }
    }

    //
    //
    // Teile der RUN-Methode
    //
    //

    public void TastenPruefen(KEYSTATE keystate)
    {
        if(keystate.IsPressed("B")) //Bsp
        {
            
        }
    }

    public void MausklickUeberpruefen(Point m)
    {
        if(m.x >= 560 && m.x <= 601 && m.y>=759 && m.y<=800) //Bsp
        {
            
        }
    }

    public void Ausgabe()
    {
        IstVeraendert = false;
    }

    //Ende Run

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }
}
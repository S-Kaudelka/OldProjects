import java.awt.Point;

public class SPIEL
{
    private boolean IstVeraendert;
    
    public BILD background;

    //first [] == y, second [] == x
    private POSITION[][] pos;
    
    private POSITION marked;
    
    private boolean NIsPressed;
    
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
        if(keystate.IsPressed("N") && NIsPressed){}else NIsPressed = false;
        if(keystate.IsPressed("N") && !NIsPressed){
            NIsPressed = true;
            marked = null;
            int[][] T = TEMPLATE.NewTemplate();
            /*
             * TODO: randomise Templates 
             */
            for(int i = 0; i < 9; i++)
                for(int j = 0; j < 9; j++){
                    pos[i][j] = new POSITION(i, j);
                    if(T[i][j] != 0)pos[i][j].FixedNumber(T[i][j]);
                }
        }
        if(marked != null){
            if(keystate.IsPressed("0")){
                marked.ChangeNumber(0);
                marked = null;
            }
            if(keystate.IsPressed("1")){
                marked.ChangeNumber(1);
                marked = null;
            }
            if(keystate.IsPressed("2")){
                marked.ChangeNumber(2);
                marked = null;
            }
            if(keystate.IsPressed("3")){
                marked.ChangeNumber(3);
                marked = null;
            }
            if(keystate.IsPressed("4")){
                marked.ChangeNumber(4);
                marked = null;
            }
            if(keystate.IsPressed("5")){
                marked.ChangeNumber(5);
                marked = null;
            }
            if(keystate.IsPressed("6")){
                marked.ChangeNumber(6);
                marked = null;
            }
            if(keystate.IsPressed("7")){
                marked.ChangeNumber(7);
                marked = null;
            }
            if(keystate.IsPressed("8")){
                marked.ChangeNumber(8);
                marked = null;
            }
            if(keystate.IsPressed("9")){
                marked.ChangeNumber(9);
                marked = null;
            }
        }
    }

    public void MausklickUeberpruefen(Point m)
    {
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(pos[j][i].img.PunktBeruehrt(m)){
                    if(marked == null){//no pos marked
                        pos[j][i].Select();
                        marked = pos[j][i];
                    } else {
                        if(!pos[j][i].equals(marked)){//different pos marked
                            marked.Select();
                            pos[j][i].Select();
                            marked = pos[j][i];
                        } else {//same pos marked
                            pos[j][i].Select();
                            marked = null;
                        }
                    }
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
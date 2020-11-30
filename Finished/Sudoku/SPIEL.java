import java.awt.Point;

public class SPIEL
{
    public BILD background;
    public BILD BlackLine;

    //first [] == y, second [] == x
    private POSITION[][] pos;

    private POSITION marked;

    //variable for new random Sudoku
    int [][] newTemplate;
    int A;
    int B;
    int C;

    public SPIEL()
    {
        background = new BILD("background.png");
        BlackLine = new BILD("line.png");
        BlackLine.SetzeY(396);
        BlackLine.SetzeBreite(396);
        BlackLine.SetzeHoehe(3);
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
    }

    //
    //
    // Teile der RUN-Methode
    //
    //

    public void TastenPruefen(KEYSTATE keystate)
    {
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

    public void New()
    {
        marked = null;
        
        int[][] T = randomField();
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
            {
                pos[i][j] = new POSITION(i, j);
                if(T[i][j] != 0)pos[i][j].FixedNumber(T[i][j]);
            }
    }

    public boolean Test(){
        for(int i = 0; i < 9; i++) //horizontal
            for(int j = 0; j < 8; j++){
                for(int k = j+1; k < 9; k++){
                    if(pos[i][j].number == pos[i][k].number || pos[i][j].number == 0)
                        return false;
                }
            }
        for(int i = 0; i < 9; i++) //vertical
            for(int j = 0; j < 8; j++){
                for(int k = j+1; k < 9; k++){
                    if(pos[j][i].number == pos[k][i].number || pos[j][i].number == 0)
                        return false;
                }
            }
        for(int i = 0; i < 3; i++) //Blocks, from block to block
            for(int j = 0; j < 3; j++) 
                for(int k = 0; k < 8; k++) //within blocks
                    for(int l = k+1; l < 9;l++){
                        int a_x = k%3;
                        int a_y = (k-a_x)/3;
                        int b_x = l%3;
                        int b_y = (l-b_x)/3;
                        if(pos[i*3+a_y][j*3+a_x].number == pos[i*3+b_y][j*3+b_x].number
                        || pos[i*3+a_y][j*3+a_x].number == 0)
                            return false;
                    }
        return true;
    }

    //Ende Run    

    public int[][] randomField(){
        newTemplate = TEMPLATE.NewTemplate();

        int random = (int) (Math.random()*11);

        if(random == 0) //No transformation
        {
            return newTemplate;
        }

        if(random == 1) // 1*SwapLine; 1*SwapColumn
        {
            Determin_A_B("WithinBlock");
            SwapLine(A, B);

            Determin_A_B("WithinBlock");
            SwapColumn(A, B);

            return newTemplate;
        }
        if(random == 2) // 2*SwapNumber
        {
            A = (int) (Math.random()*9+1);
            B = (int) (Math.random()*9+1);

            while(A == B)
            {
                B = (int) (Math.random()*9+1);
            }
            SwapNumber(A, B);

            A = (int) (Math.random()*9+1);
            B = (int) (Math.random()*9+1);

            while(A == B)
            {
                B = (int) (Math.random()*9+1);
            }
            SwapNumber(A, B);

            return newTemplate;
        }
        if(random == 3) // 1*BlockSwapLine; 1*BlockSwapColumn
        {
            Determin_A_B("BlockNumber");
            BlockSwapLine(A, B);

            Determin_A_B("BlockNumber");
            BlockSwapColumn(A, B);

            return newTemplate;
        }
        if(random == 4) // 1*BlockSwapLine; 1*SwapColumn; 1*SwapNumber
        {
            Determin_A_B("BlockNumber");
            BlockSwapLine(A, B);

            Determin_A_B("WithinBlock");
            SwapColumn(A, B);

            A = (int) (Math.random()*9+1);
            B = (int) (Math.random()*9+1);

            while(A == B)
            {
                B = (int) (Math.random()*9+1);
            }
            SwapNumber(A, B);

            return newTemplate;
        }
        if(random == 5) // 1*3SwapNumber; 1*SwapLine
        {
            A = (int) (Math.random()*9+1);
            B = (int) (Math.random()*9+1);
            C = (int) (Math.random()*9+1);

            while(A == B || C == B)
            {
                B = (int) (Math.random()*9+1);
            }
            while(A == C || B == C)
            {
                C = (int) (Math.random()*9+1);
            }
            SwapNumber(A, B);
            SwapNumber(B, C);

            Determin_A_B("WithinBlock");
            SwapLine(A, B);

            return newTemplate;
        }
        if(random == 6) // 1*BlockSwapColumn; 2*SwapLine
        {
            Determin_A_B("BlockNumber");
            BlockSwapColumn(A, B);

            Determin_A_B("WithinBlock");
            SwapLine(A, B);

            Determin_A_B("WithinBlock");
            SwapLine(A, B);

            return newTemplate;
        }
        if(random == 7) // 1*3SwapNumber; 1*SwapNumber; 1*BlockSwapColumn; 1*SwapLine
        {
            A = (int) (Math.random()*9+1);
            B = (int) (Math.random()*9+1);
            C = (int) (Math.random()*9+1);

            while(A == B || C == B)
            {
                B = (int) (Math.random()*9+1);
            }
            while(A == C || B == C)
            {
                C = (int) (Math.random()*9+1);
            }
            SwapNumber(A, B);
            SwapNumber(B, C);

            A = (int) (Math.random()*9+1);
            B = (int) (Math.random()*9+1);

            while(A == B)
            {
                B = (int) (Math.random()*9+1);
            }
            SwapNumber(A, B);
            SwapNumber(A, B);

            Determin_A_B("BlockNumber");
            BlockSwapColumn(A, B);

            Determin_A_B("WithinBlock");
            SwapLine(A, B);

            return newTemplate;
        }
        if(random == 8) // Mirror(L <-> R) -> Column
        {
            BlockSwapColumn(0, 2);

            SwapColumn(0, 2);

            SwapColumn(6, 8);

            SwapColumn(3, 5);

            return newTemplate;
        }
        if(random == 9) // Mirror(O <-> U) -> Line
        {
            BlockSwapLine(0, 2);

            SwapLine(0, 2);

            SwapLine(6, 8);

            SwapLine(3, 5);

            return newTemplate;
        }
        if(random == 10)// Mirror(Mitte) ;) -> Line & Column
        {
            BlockSwapColumn(0, 2);

            SwapColumn(0, 2);

            SwapColumn(6, 8);

            SwapColumn(3, 5);

            BlockSwapLine(0, 2);
            SwapLine(0, 2);
            SwapLine(6, 8);
            SwapLine(3, 5);
            return newTemplate;
        }

        return newTemplate;
    }

    //Transformations

    public void Determin_A_B(String Fall){ // Determin A and B 
        /*Parameter, whether BlockNumber or number from 0 to 8 is determined:

        "WithinBlock"
        "BlockNumber"

         */

        A = (int) (Math.random()*3);
        B = (int) (Math.random()*3);

        while(A == B)
        {
            B = (int) (Math.random()*3);
        }

        if(Fall.equals("BlockNumber")){
            return;
        }

        int Block = (int) (Math.random()*3);

        A = A+Block*3;
        B = B+Block*3;
    }

    public void SwapLine(int A, int B)
    {
        // A,B => Lines, you want to swap

        int[] buffer = new int[9];

        for(int r = 0; r<9; r++)
        {
            buffer[r] = newTemplate[A][r];
            newTemplate[A][r] = newTemplate[B][r];
            newTemplate[B][r] = buffer[r];
        }
    }

    public void SwapColumn(int A, int B)
    {
        // A,B => Column, you want to swap

        int[] buffer = new int[9];

        for(int u = 0; u<9; u++)
        {
            buffer[u] = newTemplate[u][A];
            newTemplate[u][A] = newTemplate[u][B];
            newTemplate[u][B] = buffer[u];
        }
    }

    public void SwapNumber(int A, int B)
    {
        // A,B => Numbers, you want to swap

        for(int u = 0; u<9; u++)
        {
            for(int r = 0; r<9; r++)
            {
                if(newTemplate[u][r] == A)
                {
                    newTemplate[u][r] = B;
                }
                else
                {
                    if(newTemplate[u][r] == B)
                    {
                        newTemplate[u][r] = A;
                    }
                }
            }
        }
    }

    public void BlockSwapLine(int A, int B) // A,B = 0/1/2 => symbolises the 3 Lines
    {
        for(int i = 0; i<3; i++)
        {
            SwapLine(A*3+i, B*3+i);
        }
    }

    public void BlockSwapColumn(int A, int B) // A,B = 0/1/2 => symbolises the 3 Columns
    {
        for(int i = 0; i<3; i++)
        {
            SwapColumn(A*3+i, B*3+i);
        }
    }
}
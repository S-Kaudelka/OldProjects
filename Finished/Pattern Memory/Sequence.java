public class Sequence
{
    public int[] In_X;
    public int[] In_Y;
    
    public int CInd = 1;
    
    public Sequence(int Size)
    {
        In_X = new int[100];
        In_Y = new int[100];
        
        for(int i = 0; i < 100; i++)
        {
            In_X[i] = (int) (Math.random()*Size);
            In_Y[i] = (int) (Math.random()*Size);
        }
    }
}
public class POSITION
{
    private BILD img;
    private int number;
    private int pos_x;
    private int pos_y;
    
    public POSITION(int p_x, int p_y){
        pos_x = p_x;
        pos_y = p_y;
        img = new BILD("images\\n0.png");
        img.SetzeX(pos_x*44+2);
        img.SetzeY(pos_y*44+2);
    }
    
    public void ChangeNumber(int num){
        number = num;
        img = new BILD("images\\n"+ num +".png");
        img.SetzeX(pos_x*44+2);
        img.SetzeY(pos_y*44+2);
    }
    
    public void FixedNumber(int num){
        number = num;
        img = new BILD("images\\n"+ num +"f.png");
        img.SetzeX(pos_x*44+2);
        img.SetzeY(pos_y*44+2);
    }
}
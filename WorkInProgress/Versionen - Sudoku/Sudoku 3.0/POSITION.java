public class POSITION
{
    public BILD img;
    private boolean marked;
    private boolean fixed;
    private int number;
    private int pos_x;
    private int pos_y;
    
    public POSITION(int p_x, int p_y){
        number = 0;
        pos_x = p_x;
        pos_y = p_y;
        img = new BILD("images\\n0.png");
        img.SetzeX(pos_x*44+2);
        img.SetzeY(pos_y*44+2);
    }
    
    public void ChangeNumber(int num){
        marked = !marked;
        if(fixed) return;
        number = num;
        img = new BILD("images\\n"+ num +".png");
        img.SetzeX(pos_x*44+2);
        img.SetzeY(pos_y*44+2);
    }
    
    public void FixedNumber(int num){
        number = num;
        fixed = true;
        img = new BILD("images\\n"+ num +"f.png");
        img.SetzeX(pos_x*44+2);
        img.SetzeY(pos_y*44+2);
    }
    
    public void Select(){
        marked = !marked;
        if(fixed) return;
        if(marked)img = new BILD("images\\n"+ number +"m.png");
        else      img = new BILD("images\\n"+ number +".png");
        img.SetzeX(pos_x*44+2);
        img.SetzeY(pos_y*44+2);
    }
}
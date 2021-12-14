public class Field
{
    public BILD image;
    public boolean Mine;
    public int MinensAdjacent;
    public boolean marked;
    
    public Field(int x, int y)
    {
        image = new BILD("fNeu.png");
        image.SetzeHoehe(25);
        image.SetzeBreite(25);
        image.SetzeX(x*25);
        image.SetzeY(y*25+55);
    }
    
    public void ChangeImage(String newName)
    {
        int x_p = image.LeseX();
        int y_p = image.LeseY();
        image.SetzeX(-100);
        image = new BILD(newName+".png");
        image.SetzeX(x_p);
        image.SetzeY(y_p);
        image.SetzeHoehe(25);
        image.SetzeBreite(25);
    }
}
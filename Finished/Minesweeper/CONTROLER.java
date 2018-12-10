public class CONTROLER implements Runnable
{
    public SPIEL spiel;

    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;
    
    private int X;
    private int Y;

    //    ZU ERLEDIGEN:
    //
    //

    public CONTROLER(int x, int y)
    {
        X = x;
        Y = y;
        GAMEWINDOW.Breite = x*25;
        GAMEWINDOW.Hoehe = y*25;
        spiel = new SPIEL(this, x, y);
        new Thread(this).start();
    }

    public void run()
    {
        while(IsRunning)
        {
            if(AmLaufen==true)
            {
                spiel.RUN(GAMEWINDOW.getInstance().getKeystate());
                try { Thread.sleep((int) (20)); } catch (Exception e) {}
            }
        }
    }

    public void stop()
    {
        IsRunning = false;
    }
}
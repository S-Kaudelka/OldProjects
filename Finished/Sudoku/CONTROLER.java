public class CONTROLER implements Runnable
{
    public SPIEL spiel;

    private boolean IsRunning=true;

    public boolean AmLaufen=true;

    //    ZU ERLEDIGEN:
    //
    //      More Templates?
    //      Count # of numbers?

    public CONTROLER()
    {
        spiel = new SPIEL();
        GAMEWINDOW.getInstance().s = spiel;
        new Thread(this).start();
    }
    
    public static void main(String[] args)
    {
        new CONTROLER();
    }

    public void run()
    {
        while(IsRunning)
        {
            spiel.RUN(GAMEWINDOW.getInstance().getKeystate());
            try { Thread.sleep((int) (20)); } catch (Exception e) {}
        }
    }

    public void stop()
    {
        IsRunning = false;
    }
}
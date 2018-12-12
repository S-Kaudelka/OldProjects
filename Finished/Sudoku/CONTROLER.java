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
        new Thread(this).start();
    }

    public void run()
    {
        while(IsRunning)
        {
            if(!spiel.Finished)
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
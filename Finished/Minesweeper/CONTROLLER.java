public class CONTROLLER implements Runnable
{
    public SPIEL spiel;

    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;

    //    ZU ERLEDIGEN:
    //
    //      fix victory condition

    public CONTROLLER(int height, int width)
    {
        if(height < 305) height = 305;
        if(width < 255) width = 255;
        GAMEWINDOW.High = height;
        GAMEWINDOW.Wide = width;
        spiel = new SPIEL();
        new Thread(this).start();
    }

    public void run()
    {
        while(IsRunning)
        {
            if(GAMEWINDOW.new_Spiel)
            {
                spiel = new SPIEL();
                GAMEWINDOW.new_Spiel = false;
                AmLaufen = true;
            }
            if(AmLaufen==true)
            {
                spiel.RUN(GAMEWINDOW.getInstance().getKeystate());
            }
            if(spiel.finished == true)
            {
                AmLaufen = false;
            }
            try { Thread.sleep((int) (20)); } catch (Exception e) {}
        }
    }

    public void stop()
    {
        IsRunning = false;
    }
}
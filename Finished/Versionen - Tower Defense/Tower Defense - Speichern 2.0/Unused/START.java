public class START implements Runnable
{
    private CONTROLER Controler;
    private WIKI Wiki;
    
    private BILD A;
    
    public boolean Aktiv;
    
    //Wiki erstellen
    //Auswahlmöglichkeiten am Anfang im Gamewindow anzeigen
    
    public START()
    {
        Aktiv = true;
        new Thread(this).start();
    }
    
    public void run()
    {
        while(Aktiv)
        {
            if(GAMEWINDOW.getInstance().getKeystate().IsPressed("S"))
            {
                Controler = new CONTROLER();
                stop();
            }
            if(GAMEWINDOW.getInstance().getKeystate().IsPressed("A"))
            {
                Wiki = new WIKI(this);
                stop();
            }
            try { Thread.sleep(20); } catch (Exception e) {}
        }
    }
    
    public void stop()
    {
        Aktiv = false;
    }
    
    public void start()
    {
        Aktiv = true;
        run();
    }
}
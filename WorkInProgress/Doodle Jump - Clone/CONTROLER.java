public class CONTROLER implements Runnable
{
    private SPIEL spiel;
    
    private BILD h;
    
    private boolean IsRunning=true;
    
    public boolean AmLaufen=false;
    
    public CONTROLER()
    {
        h = new BILD("image\\intro0.png");
        new Thread(this).start();
    }
    
    public void run()
    {
        while(IsRunning)
        {
            KEYSTATE k = GAMEWINDOW.getInstance().getKeystate();
            if(k.IsClickAvailable() && spiel == null)
            {
                AmLaufen = true;
                spiel = new SPIEL(this);
            }
            
            if(AmLaufen)
            {
                spiel.Action(k);
            }
            try { Thread.sleep((int) (20)); } catch (Exception e) {}
        }
    }
    
    public void end()
    {
        AmLaufen = false;
        spiel = null;
    }
}

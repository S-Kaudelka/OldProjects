import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Point;

public class KEYSTATE
{
    HashSet<String> set = new HashSet<String>();
    Point lastMouseClickPosition;
    
    Point MousePosition= new Point(0,0);
    
    public KEYSTATE()
    {
    }
    
    public void add(KeyEvent keyEvent)
    {
        set.add(keyEvent.getKeyText(keyEvent.getKeyCode()));
    }
    
    public void remove(KeyEvent keyEvent)
    {
        set.remove(keyEvent.getKeyText(keyEvent.getKeyCode()));
    }
    
    public boolean IsPressed(String key)
    {
        return set.contains(key);
    }
    
    public Point PickLastMouseClickPosition()
    {
        synchronized(this)
        {
        Point ret = lastMouseClickPosition;
        
        lastMouseClickPosition = null;
        return ret;
        }
    }
    
    public boolean IsClickAvailable()
    {
        return lastMouseClickPosition!=null;
    }
    
    public void setNewMouseClick(Point p)
    {
        lastMouseClickPosition = p;
    }

    public Point getMousePosition()
    {
        return MousePosition;
        
    }
    
    public void SetMousePosition(Point p)
    {
        MousePosition=p;
    }
}

public class WIKI
{    
    public START Start;
    
    public WIKI_EINTRAG[] WE;
    
    // Jedes mal wenn ein neuer eintrag Angelegt wurde, die Zahl in den Klammern bei "new WIKI_EINTR[];"
    // um eins erhoehen
    
    public WIKI(START s)
    {
        Start = s;
        
        WE= new WIKI_EINTRAG[12];
    }
}
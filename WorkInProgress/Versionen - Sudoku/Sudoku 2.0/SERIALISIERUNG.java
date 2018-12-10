import java.io.*;


public class SERIALISIERUNG 
{
    public static void main(int[] args)
    {
        OutputStream outputStream = null;
        
        int anz = 0;
        
        try
        {
            outputStream = new FileOutputStream("data.ser");
 
            ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
                        
            if(args != null)
            {
                for(int i = 0;i < args.length; i++)
                {
                    objectOutput.writeObject(args[i]);
                    anz++;
                }
            }
 
            objectOutput.close();
            
            System.out.println("Erfolgreich Gespeichert");
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                outputStream.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        DESERIALISIERUNG.anzObjekte = anz;
    }
}
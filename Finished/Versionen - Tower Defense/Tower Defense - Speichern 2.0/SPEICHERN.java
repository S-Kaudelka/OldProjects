import java.io.*;

public class SPEICHERN 
{
    public static void main(String Dateiname, String[] args)
    {
        OutputStream outputStream = null;
        
        try
        {
            outputStream = new FileOutputStream(Dateiname + ".ser");
 
            ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
            
            if(args != null)
            {
                objectOutput.writeObject(args.length + "");
                for(int i = 0; i < args.length; i++)
                {
                    objectOutput.writeObject(args[i]);
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
    }
}
import java.io.*;

public class SPEICHERN 
{
    public static void main(String[] Werte, String Dateiname)
    {
        OutputStream outputStream = null;
        
        try
        {
            outputStream = new FileOutputStream(Dateiname+".ser");
 
            ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
            
            objectOutput.writeObject(Werte.length + "");//Speichern, wie lang es insgesamt ist
            
            if(Werte != null)
            {
                for(int i = 0; i < Werte.length; i++)
                {
                    objectOutput.writeObject(Werte[i]);
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
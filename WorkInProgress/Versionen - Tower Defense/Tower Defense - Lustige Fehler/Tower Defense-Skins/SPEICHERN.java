import java.io.*;

public class SPEICHERN 
{
    public static void main(String[] argsFest, String[] argsTurm)
    {
        OutputStream outputStream = null;
        
        try
        {
            outputStream = new FileOutputStream("data.ser");
 
            ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
            
            //objectOutput.writeObject(argsFest.length + argsVariabl.length + "");
            //Speichern, wie lang es insgesamt ist
            
            if(argsFest != null)
            {
                for(int i = 0; i < argsFest.length; i++)
                {
                    objectOutput.writeObject(argsFest[i]);
                }
            }
            
            if(argsTurm != null)
            {
                for(int i = 0; i < argsTurm.length; i++)
                {
                    objectOutput.writeObject(argsTurm[i]);
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
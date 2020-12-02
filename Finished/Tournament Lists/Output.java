import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Output
{
    public static void WriteInFile(String args)
    {
        PrintWriter pWriter = null;
        try
        {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("Output.txt")));
            pWriter.println(args);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            if (pWriter != null)
            {
                pWriter.flush();
                pWriter.close();
            }
        }
    }
} 
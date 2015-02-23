/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacodecompile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Attoprottoyee!6
 * Check the time limit of given java program using thread.
 * This class implements Runnable interface.
 */
public class CheckTimeLimit implements Runnable
{
    Thread workingThread;
    InputStream inputStream;
    String outputFilePath;
    /**
     * This constructor set the value of inputStream, outputFilePath and working thread.
     * 
     * @param inputStream input stream which is the output of the given java program.
     * @param outputFilePath output file name of user.
     */
    public CheckTimeLimit(InputStream inputStream,String outputFilePath)
    {
        this.inputStream = inputStream;
        this.workingThread = new Thread(this,"Main Thread!");
        this.outputFilePath = outputFilePath;
    }
    /**
     * Run the working thread.
     */
    @Override
    public void run()
    {
        FileWriter writeInFile = null;
        try
        {
            File outputFile = new File(outputFilePath);
            writeInFile = new FileWriter(outputFile);
            outputFile.createNewFile();
            BufferedReader inputFromBuffer = new BufferedReader(new InputStreamReader(this.inputStream));
            String line=new String();
            while ((line = inputFromBuffer.readLine()) != null)
            {
                writeInFile.write(String.format("%s\n", line));
            }
        }
        catch (IOException ex)
        {
        }
        finally
        {
            try
            {
                writeInFile.close();
            }
            catch (IOException ex)
            {
            }
        }
    }
    
}

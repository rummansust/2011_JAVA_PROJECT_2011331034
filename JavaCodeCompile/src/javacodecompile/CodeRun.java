/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacodecompile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Attoprottoyee!6
 * This class run the given java program by input and print the output and error message and match the output file
 * with the given output file.
 */
public class CodeRun
{
    /**
     * Return a boolean value the status of the program where return "true" means the program execute within time limit
     * otherwise return "false".
     * Get the output from user.
     * 
     * @param inputStream input stream of user's output.
     * @param timeLimit maximum time limit to run the given java program.
     * @param outputFilePath user's output file directory. 
     * @return a boolean value the status of the program where return "true" means the program execute within time limit
     * otherwise return "false".
     */
    public boolean getOutput(InputStream inputStream,long timeLimit,String outputFilePath)
    {
        boolean isTimeLimit = true;
        try
        {
            CheckTimeLimit checkTle = new CheckTimeLimit(inputStream,outputFilePath);
            checkTle.workingThread.start();
            checkTle.workingThread.join(timeLimit);
            Thread.sleep(100);
            if(checkTle.workingThread.isAlive()){
                isTimeLimit=false;
            }
        }
        catch (InterruptedException e)
        {
        }
        return isTimeLimit;
    }

    /**
     * Returns a boolean the status of the given java program where return "true" means the program run successfully 
     * otherwise return "false" that means the program have some runtime error.
     * Get the runtime error message if the given java program have any and print them.
     * 
     * @param inputErrorStream a input stream of user's program's runtime error.
     * @param errorIcon an icon show with the runtime error message.
     * @return a boolean the status of the given java program where return "true" means the program run successfully 
     * otherwise return "false" that means the program have some runtime error.
     */
    public boolean getError(InputStream inputErrorStream,ImageIcon errorIcon)
    {
        String line = new String();
        boolean isRuntimeError = false;
        try
        {
            BufferedReader inputFromBuffer = new BufferedReader(new InputStreamReader(inputErrorStream));
            while ((line = inputFromBuffer.readLine()) != null)
            {
                if(!isRuntimeError){
                    
                    isRuntimeError=true;
                }
            }
            
            inputErrorStream.close();
        }
        catch (IOException e)
        {
        }
        return isRuntimeError;
    }

    /**
     * Give the input from the given input file to run the given java program.
     * 
     * @param outputStream a output stream which is used to write the input from the given input file
     * and write it to a stream to run the given java program.
     * @param inputFilePath directory of the given input file.
     */
    public void giveInput(OutputStream outputStream,String inputFilePath)
    {
        File judgeInput = new File(inputFilePath);
        try
        {
            Scanner readFromFile = new Scanner(judgeInput);
            String line = new String();
            while (readFromFile.hasNextLine())
            {
                line = readFromFile.nextLine();
                line = String.format("%s\n", line);
                outputStream.write(line.getBytes());
            }
            outputStream.close();
            outputStream.flush();
        }
        catch (IOException e)
        {
        }
    }

    /**
     * Match the given output file and the user's output file.
     * 
     * @param dirJudge directory of given output file.
     * @param dirUser directory of user's output file.
     * @param exclamationImage an icon show with the output matching message which indicate that the given 
     * output file was not found.
     * @param yesImage an icon show with the output matching message which indicate that output file match.
     * @param noImage an icon show with the output matching message which indicate that output file doesn't match.
     * @return true if output file matched, other wise false.
     */
    public boolean matchOutFile(String dirJudge,String dirUser, ImageIcon exclamationImage,ImageIcon yesImage,ImageIcon noImage)
    {
        File fileJudge = new File(dirJudge);
        File fileUser = new File(dirUser);
        boolean notMatch = false;
        try
        {
            Scanner inJudge = new Scanner(fileJudge);
            Scanner inUser = new Scanner(fileUser);
            String strJudge;
            String strUser;
            notMatch = false;
            while (inJudge.hasNextLine() && inUser.hasNextLine())
            {
                strJudge = inJudge.nextLine();
                strUser = inUser.nextLine();
                if (!strJudge.equals(strUser))
                {
                    notMatch = true;
                }
            }
            if (inJudge.hasNextLine() || inUser.hasNextLine())
                notMatch = true;
        }
        catch (FileNotFoundException e)
        {
        }
        return !notMatch;
    }
    
}

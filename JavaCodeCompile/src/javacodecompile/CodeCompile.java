/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacodecompile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Attoprottoyee!6
 * This class compile the give java program, find the compilation error, 
 * make the user's output directory and user's java program directory for compiling the program.
 */
public class CodeCompile
{
    /**
     * Returns a string, the formatted directory of given java program to compile.
     * 
     * @param fileToCompile given directory of the given java program.
     * @return returns a string, the formated directory of the given java program.
     */
    public String makeFileDirectory(String fileToCompile){
        int len = fileToCompile.length();
        String ret = new String();
        int pos=0;
        for(int it=len-6;it>-1;it--){
            if(fileToCompile.charAt(it) == '\\'){
                pos=it;
                break;
            }
        }
        for(int it=0;it<len-5;it++){
            ret+=fileToCompile.charAt(it);
            if(it==pos){
                ret+=' ';
            }
        }
        return ret;
    }
    
    /**
     * Returns a string, the directory of user's output.
     * 
     * @param fileToWrite directory of the user's output.
     * @return returns a string, the formated directory of the user's output file.
     */
    
    public String makeUserOutputfileDirectory(String fileToWrite){
        int len = fileToWrite.length();
        String ret = new String();
        int pos=0;
        for(int it=len-1;it>-1;it--){
            if(fileToWrite.charAt(it) == '\\'){
                pos=it;
                break;
            }
        }
        for(int it=0;it<len;it++){
            ret+=fileToWrite.charAt(it);
            if(it==pos){
                break;
            }
        }
        ret+="userOutput.txt";
        return ret;
    }
    /**
     * Print the standard input stream.
     * 
     * @param inputStream standard input stream. 
     */
    public void printStr(InputStream inputStream){
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
        while(true){
            try{
                String line = bufferReader.readLine();
                if(line==null) break;
                System.out.println(line);
            }
            catch(IOException e){
            }
        }
    }
    
    /**
     * Print the compilation error.
     * 
     * @param inputStream input stream of compilation error.
     * @param errorImage an icon show with the error message.
     */
    
    public void printError(InputStream inputStream,ImageIcon errorImage){
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
        while(true){
            try{
                String line = bufferReader.readLine();
                if(line==null) break;
            }
            catch(IOException e){
            }
        }
    }
    /**
     * Returns the compilation result. Compilation result is equal to "0" means compilation is successful and
     * "1" means compilation is unsuccessful.
     * 
     * @param fileToCompile formatted directory of the given java program.
     * @param errorImage an icon show with the error message.
     * @return the compilation result. Compilation result is equal to "0" means compilation is successful and
     * "1" means compilation is unsuccessful.
     */
    
    public int CompileJavaCode(String fileToCompile, ImageIcon errorImage){
        int compilationResult=0;
        try
        {
            Runtime compile = Runtime.getRuntime();
            fileToCompile = "javac \""+fileToCompile+"\"";
            Process compileProcess = compile.exec(fileToCompile);
            printStr(compileProcess.getInputStream());
            compilationResult = compileProcess.exitValue();
            printError(compileProcess.getErrorStream(),errorImage);
            compileProcess.destroy();
        }
        catch (IOException e)
        {
            compilationResult = 1;
        }
        return compilationResult;
    }
}

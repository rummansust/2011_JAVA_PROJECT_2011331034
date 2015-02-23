/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javacodecompile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Attoprottoyee!6
 */
public class InputOutputSimulation
{
    private ImageIcon backgroundImage = new ImageIcon(getClass().getResource("backGround.jpg"));
    private ImageIcon yesImage = new ImageIcon(getClass().getResource("yesImage.jpg"));
    private ImageIcon noImage = new ImageIcon(getClass().getResource("nOImage.jpg"));
    private ImageIcon exclamationImage = new ImageIcon(getClass().getResource("exclamationImage.jpg"));
    private ImageIcon errorImage = new ImageIcon(getClass().getResource("errorImage.jpg"));
    
    private boolean runtimeError;
    private boolean timeLimitError;
    private boolean isAccepted;
    private boolean isWrongAnswer;
    
    private String rootFolder;
    private String inputFolder;
    private String outputFolder;
    private int timeLimit;
    
    public void setRootDirectory(String root){
        this.rootFolder = root;
    }
    public void setInputDirectory(String input){
        this.inputFolder = input;
    }
    public void setOutputDirectory(String output){
        this.outputFolder = output;
    }
    
    public void setTimeLimit(int value){
        this.timeLimit = value;
    }
    
    private void setBooleanValue(){
        this.runtimeError=false;
        this.timeLimitError=false;
        this.isAccepted=false;
        this.isWrongAnswer=false;
    }
    
    @SuppressWarnings("empty-statement")
    public void process(File csvFile){
        int studentNum,programNum;
        studentNum = getNumberOfFiles(this.rootFolder);
        programNum = getNumberOfFiles(this.inputFolder);
        
        String[] regNumberList = new String[studentNum];
        String[] programList = new String[programNum];
        
        String[] inputDirectory = new String[programNum];
        String[] outputDirectory = new String[programNum];
        
        File[] studentReg = new File[studentNum];
        File[] programFiles = new File[programNum];
        File[] outputFiles = new File[programNum];
        
        studentReg = new File(this.rootFolder).listFiles();
        for(int i=0;i<studentNum;i++){
            regNumberList[i]=studentReg[i].toString();
        }
        
        programFiles = new File(this.inputFolder).listFiles();
        for(int i=0;i<programNum;i++){
            programList[i] = programFiles[i].getName().substring(0, programFiles[i].getName().length()-3)+"java";
            inputDirectory[i] = programFiles[i].toString();
        }
        
        outputFiles = new File(this.outputFolder).listFiles();
        
        for(int i=0;i<programNum;i++){
            outputDirectory[i] = outputFiles[i].toString();
        }
        int[] cnt = new int[studentNum];
        String[][] info = new String[studentNum][programNum];
        
        String codeDirectory;
        boolean isExist,isCompile;
        
        for(int j=0;j<studentNum;j++){
            cnt[j]=0;
        }
        
        for(int i=0;i<programNum;i++){
            
            for(int j=0;j<studentNum;j++){
                
                codeDirectory=regNumberList[j]+"\\"+programList[i];
                isExist = new File(codeDirectory).exists();
                if(!isExist) info[j][i]="N/A";
                else
                {
                    isCompile = compileMethod(codeDirectory);
                    if(!isCompile){
                        info[j][i]="CE";
                    }
                    else
                    {
                        runMethod(codeDirectory, inputDirectory[i], outputDirectory[i], this.timeLimit);
                        if(this.isAccepted){
                            info[j][i] = "AC";
                            cnt[j]++;
                        }
                        else if(this.isWrongAnswer){
                            info[j][i] = "WA";
                        }
                        else if(this.runtimeError){
                            info[j][i] = "RE";
                        }
                        else if(this.timeLimitError){
                            info[j][i] = "TE";
                        }
                    }
                }
            }
        }
        
        try
        {
            csvFile.createNewFile();
            FileWriter writeInFile = new FileWriter(csvFile);
            String lineToWrite = new String();
            lineToWrite+="No.,";
            lineToWrite+="Registration No.,";
            for(int i=0;i<programNum;i++){
                lineToWrite+=programList[i].substring(0, programList[i].length()-5)+",";
            }
            lineToWrite+="Percentage of accepted result\n";
            writeInFile.write(lineToWrite);
            for(int i=0;i<studentNum;i++)
            {
                System.out.printf(" "+studentReg[i].getName());
                lineToWrite=String.format("%d,%s",i+1,studentReg[i].getName());
                for(int j=0;j<programNum;j++){
                    System.out.printf("  "+info[i][j]);
                    lineToWrite+=String.format(",%s", info[i][j]);
                }
                lineToWrite+=String.format(",%.2f", (100.0*(double)cnt[i])/(double) programNum);
                lineToWrite+=",\n";
                writeInFile.write(lineToWrite);
                System.out.println("");
            }
            writeInFile.close();
        }
        catch (IOException ex)
        {
        }
    }
    
    private int getNumberOfFiles(String directory){
        int len;
        len = new File(directory).listFiles().length;
        return len;
    }
    
    /**
     * Return a boolean value which denotes that the compilation of the given java program is successful or not.
     * @param codeDirectory the directory of the given java program.
     * @return a boolean value where return "true" means compilation successful otherwise return "false".
     */
    private boolean compileMethod(String codeDirectory){
        String fileToCompile = new String();
        int compilationResult = 0;
        CodeCompile compileObject = new CodeCompile();
        compilationResult = compileObject.CompileJavaCode(codeDirectory,errorImage);
        return (boolean) (compilationResult==0);
    }
    /**
     * This method run the given java program.
     * @param codeDirectory directory of the given java program.
     * @param inputFilePath directory of the given input file.
     * @param outputFilePath directory of the given output file.
     * @param timeLimitVal the value of maximum time limit can have the java program to execute.
     */
    private void runMethod(String codeDirectory,String inputFilePath,String outputFilePath,int timeLimitVal)
    {
        CodeCompile compileObject = new CodeCompile();
        CodeRun codeRunObject = new CodeRun();
        String directoryForRun = compileObject.makeFileDirectory(codeDirectory);
        String command = "java -cp " + directoryForRun;
        try
        {
            Process p = Runtime.getRuntime().exec(command);
            codeRunObject.giveInput(p.getOutputStream(),inputFilePath);

            timeLimitVal*=1000;
            String userOutputFilePath = new String();
            userOutputFilePath = compileObject.makeUserOutputfileDirectory(outputFilePath);
            boolean isTimeLimit = codeRunObject.getOutput(p.getInputStream(), timeLimitVal,userOutputFilePath);
            if (isTimeLimit)
            {
                p.waitFor();
                boolean isRuntimeError = codeRunObject.getError(p.getErrorStream(),errorImage);
                if(isRuntimeError){
                    setBooleanValue();
                    this.runtimeError=true;
                    p.destroy();
                }
                else{
                    p.waitFor();
                    boolean isMatch = codeRunObject.matchOutFile(outputFilePath,userOutputFilePath,exclamationImage,yesImage,noImage);
                    if(isMatch){
                        setBooleanValue();
                        this.isAccepted=true;
                    }
                    else{
                        setBooleanValue();
                        this.isWrongAnswer=true;
                    }
                    p.waitFor();
                    p.destroy();
                }
            }
            else
            {
                setBooleanValue();
                this.timeLimitError=true;
                p.destroy();
            }
        }
        catch (IOException | InterruptedException e)
        {
        }
    }
}


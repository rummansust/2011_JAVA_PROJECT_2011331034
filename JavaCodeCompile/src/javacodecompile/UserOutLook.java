/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacodecompile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Attoprottoyee!6
 * This class extends JFrame.
 * It's make the outlook of the application.
 * Make the body of the frame.
 * Take the input from the user and give the corresponding output.
 */

public class UserOutLook extends JFrame
{
    private JLabel getInfoLabel;
    
    private ImageIcon backgroundImage = new ImageIcon(getClass().getResource("backGround.jpg"));
    private ImageIcon yesImage = new ImageIcon(getClass().getResource("yesImage.jpg"));
    private ImageIcon noImage = new ImageIcon(getClass().getResource("nOImage.jpg"));
    private ImageIcon exclamationImage = new ImageIcon(getClass().getResource("exclamationImage.jpg"));
    private ImageIcon errorImage = new ImageIcon(getClass().getResource("errorImage.jpg"));
    
    private String codeText = "Enter Program Folder";
    private String inputText = "Enter Input Folder";
    private String outputText = "Enter Output Folder";
    private String timeLimitText = "Enter Time Limit (in seconds)";
    private String resultDirectoryText = "Enter the result derectory";
    
//    private JTextField codeDirectory = new JTextField("Enter Program Folder",40);
//    private JTextField inputDirectory = new JTextField("Enter Input Folder",40);
//    private JTextField outputDirectory = new JTextField("Enter Output Folder",40);
//    private JTextField resultDirectoryField = new JTextField("Enter the result derectory",40);
    private JTextField timeLimitField = new JTextField("Enter Time Limit (in seconds)",40);
    private JTextField codeDirectory = new JTextField("D:\\MyJavaProject\\2011_JAVA_PROJECT_2011331034\\ClassAssaignment",40);
    private JTextField inputDirectory = new JTextField("D:\\MyJavaProject\\2011_JAVA_PROJECT_2011331034\\InputFolder",40);
    private JTextField outputDirectory = new JTextField("D:\\MyJavaProject\\2011_JAVA_PROJECT_2011331034\\OutputFolder",40);
    private JTextField resultDirectoryField = new JTextField("D:\\MyJavaProject\\2011_JAVA_PROJECT_2011331034\\Result",40);
    
    private JButton showResult = new JButton("Show Result");
    private JButton submitButton = new JButton("Submit");
    private JButton codeButton = new JButton("1. Program Folder");
    private JButton inputButton = new JButton("2. Input Folder");
    private JButton outputButton = new JButton("3. Output Folder");
    private JButton codeBrowseButton = new JButton("Browse");
    private JButton inputBrowseButton = new JButton("Browse");
    private JButton outputBrowseButton = new JButton("Browse");
    private JButton resultBrowseButton = new JButton("Browse");
    private JButton resultButton = new JButton("4. Result Directory");
    private JButton timeLimitButton = new JButton("5. Time Limit");
        
    private Choice timeLimitChoice = new Choice();
        
    private int timeLimitval=0;
    
    /**
     *This is constructor of UserOutlook class which set the background image of
     *the application, the location and size of the button, text-field, choice.
     */
    public UserOutLook()
    {
        super("Java Code Compiler(Beta version)");
        setLayout(new FlowLayout());
        getInfoLabel = new JLabel();
        setContentPane(new JLabel(backgroundImage));
        for(int i=0;i<61;i++) timeLimitChoice.add(String.format("%d",i));
        setLocation();
        HandlerClass handler = new HandlerClass();
        ItemHandlerClass itemHandler = new ItemHandlerClass();
        codeBrowseButton.addActionListener(handler);
        inputBrowseButton.addActionListener(handler);
        outputBrowseButton.addActionListener(handler);
        timeLimitChoice.addItemListener(itemHandler);
        submitButton.addActionListener(handler);
        showResult.addActionListener(handler);
        resultBrowseButton.addActionListener(handler);
        getInfoLabel.setVisible(true);
    }

    /**
     * This class check that the user click on the timeLimitChoice or not.
     * If user click on the timeLimitChoice then it set the time limit to the timeLimitField text-field.
     * Check the input format is right or wrong and if right then set the value of timeLimitval.
     */
    
    private class ItemHandlerClass implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e)
        {
            String timeLimit = (String) e.getItem().toString();
            timeLimitField.setText(timeLimit);
            try{
                timeLimitval = Integer.parseInt(timeLimit);
            }
            catch(NumberFormatException ex){
                timeLimitval = -1;
            }
        }
    }
    /**
     * This class check the action performed by the user and give the corresponding response.
     * If the user click on the codeBrowseButton it browse the directory of the java program and set the directory to 
     * codeDirectory.
     * If the user click on the inputBrowseButton it browse the directory of the input file and set the directory to 
     * inputDirectory.
     * If the user click on the outputBrowseButton it browse the directory of the output file and set the directory to 
     * outputDirectory.
     * If the user click on the compileButton it check the directory of the java program, input file, output file and compile
     * the java program if everything is ok.
     * If the user click on the runButton it check that the program is compile or not and run the program if everything is ok.
     */
    private class HandlerClass implements ActionListener{

        @Override
        @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent e)
        {
            String showOnDialog = new String("Message");
            if(e.getSource() == resultBrowseButton){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                String fileName = new String();
                int returnVal = fileChooser.showOpenDialog((Component) e.getSource());
                
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    try
                    {
                        fileName = file.toString();
                        resultDirectoryField.setText(fileName);
                    }
                    catch (Exception ex)
                    {
                        String showMessage = new String("Can't access this file.");
                        JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
                    }
                }
                else
                {
                    String showMessage = new String("File access cancelled by user.");
                    JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
                }
            }
            else if(e.getSource() == codeBrowseButton){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                String fileName = new String();
                int returnVal = fileChooser.showOpenDialog((Component) e.getSource());
                
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    try
                    {
                        fileName = file.toString();
                        codeDirectory.setText(fileName);
                    }
                    catch (Exception ex)
                    {
                        String showMessage = new String("Can't access this file.");
                        JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
                    }
                }
                else
                {
                    String showMessage = new String("File access cancelled by user.");
                    JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
                }
            }
            else if(e.getSource() == inputBrowseButton){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                String fileName = new String();
                int returnVal = fileChooser.showOpenDialog((Component) e.getSource());
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    try
                    {
                        fileName = file.toString();
                        inputDirectory.setText(fileName);
                    }
                    catch (Exception ex)
                    {
                        String showMessage = new String("Can't access this file.");
                        JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
                    }
                }
                else
                {
                    String showMessage = new String("File access cancelled by user.");
                    JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
                }
            }
            else if(e.getSource() == outputBrowseButton){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                String fileName = new String();
                int returnVal = fileChooser.showOpenDialog((Component) e.getSource());
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    try
                    {
                        fileName = file.toString();
                        outputDirectory.setText(fileName);
                    }
                    catch (Exception ex)
                    {
                        String showMessage = new String("Can't access this file.");
                        JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
                    }
                }
                else
                {
                    String showMessage = new String("File access cancelled by user.");
                    JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
                }
            }
            else if(e.getSource() == submitButton){
                if(isOk()){
                    
                    InputOutputSimulation simulateObject = new InputOutputSimulation();
                    simulateObject.setRootDirectory(codeDirectory.getText());
                    simulateObject.setInputDirectory(inputDirectory.getText());
                    simulateObject.setOutputDirectory(outputDirectory.getText());
                    simulateObject.setTimeLimit(timeLimitval);
                    String resultDirectory = resultDirectoryField.getText().toString()+"\\"+"Result.csv";
                    File csvFile = new File(resultDirectory);
                    submitButton.setText("Please Wait!");
                    submitButton.setEnabled(false);
                    String showMessage = "Result processing is going to be started.";
                    JOptionPane.showMessageDialog(null,showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE, yesImage);
                    simulateObject.process(csvFile);
                    showMessage = "Result Successfully processed";
                    JOptionPane.showMessageDialog(null,showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE, yesImage);
                    submitButton.setText("Submit");
                    submitButton.setEnabled(true);
                }
            }
            else if(e.getSource() == showResult){
                String resultDirectory = resultDirectoryField.getText().toString()+"\\"+"Result.csv";
                File result = new File(resultDirectory);
                if(result.exists()){
                    String command = "cmd /c start "+resultDirectory;
                    try
                    {
                        Runtime.getRuntime().exec(command);
                    }
                    catch (IOException ex)
                    {
                    }
                }
                else
                {
                    String showMessage = "Sorry! File not found!";
                    JOptionPane.showMessageDialog(null, showMessage, showOnDialog, JOptionPane.INFORMATION_MESSAGE, exclamationImage);
                }
            }
        }
        
    }
    
    private boolean isOk(){
        String codir = codeDirectory.getText().toString();
        String indir = inputDirectory.getText().toString();
        String outdir = outputDirectory.getText().toString();
        String timtext = timeLimitField.getText().toString();
        String resdir = resultDirectoryField.getText().toString();
        String showOnDialog = new String("Message");
        if(codir.equals(codeText))
        {
            String showMessage = new String("Please select a java program folder!");
            JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
            return false;
        }
        if(indir.equals(inputText))
        {
            String showMessage = new String("Please select an input folder!");
            JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
            return false;
        }
        if(outdir.equals(outputText))
        {
            String showMessage = new String("Please select an output folder!");
            JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
            return false;
        }
        if(resdir.equals(resultDirectoryText))
        {
            String showMessage = new String("Please select a result directory!");
            JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
            return false;
        }
        if(timtext.equals(timeLimitText))
        {
            String showMessage = new String("Time limit format is wrong!");
            JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
            return false;
        }
        String tmp=new String();
        for(int i=0;i<timtext.length();i++)
        {
            if((timtext.charAt(i)>='0'&&timtext.charAt(i)<='9') || timtext.charAt(i)=='+'||timtext.charAt(i)=='-')
            tmp+=(char) timtext.charAt(i);
            else break;
        }
        try{
            timeLimitval = Integer.parseInt(tmp);
        }
        catch(Exception ex){
            timeLimitval = -1;
            String showMessage = new String("Time limit format is wrong!");
            JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
            return false;
        }
        
        if(timeLimitval==-1){
            String showMessage = new String("Time limit format is wrong!");
            JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
            return false;
        }
        if(timeLimitval>60)
        {
            String showMessage = new String("Time limit must be less than or equal 60!");
            JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
            return false;
        }
        if(timeLimitval<=0){
            String showMessage = new String("Time limit must be greater than or equal 1!");
            JOptionPane.showMessageDialog(null, showMessage,showOnDialog,JOptionPane.INFORMATION_MESSAGE,exclamationImage);
            return false;
        }
        return true;
    }
    
    /**
     * Set the location of all the buttons, label, text-fields, choice and add them to the frame. 
     */
    private void setLocation(){
        codeButton.setBounds(190,150,150,20);
        inputButton.setBounds(190,180,150,20);
        outputButton.setBounds(190,210,150,20);
        timeLimitButton.setBounds(190,270,150,20);
        
        codeDirectory.setBounds(350,150,300,20);
        inputDirectory.setBounds(350,180,300,20);
        outputDirectory.setBounds(350,210,300,20);
        timeLimitField.setBounds(350,270,300,20);
        
        codeBrowseButton.setBounds(660,150,80,20);
        inputBrowseButton.setBounds(660,180,80,20);
        outputBrowseButton.setBounds(660,210,80,20);
        resultBrowseButton.setBounds(660,240,80,20);
        
        resultButton.setBounds(190,240,150,20);
        timeLimitField.setText("5");
        timeLimitChoice.setBounds(660,270,80,20);
        resultDirectoryField.setBounds(350,240,300,20);
        submitButton.setBounds(410, 350, 120, 20);
        showResult.setBounds(410, 390, 120, 20);
                        
        add(resultBrowseButton);
        add(resultButton);
        add(resultDirectoryField);
        add(showResult);
        add(submitButton);
        add(getInfoLabel);
        add(codeDirectory);
        add(inputDirectory);
        add(outputDirectory);
        add(codeButton);
        add(inputButton);
        add(outputButton);
        add(codeBrowseButton);
        add(inputBrowseButton);
        add(outputBrowseButton);
        add(timeLimitButton);
        add(timeLimitChoice);
        add(timeLimitField);
    }
}

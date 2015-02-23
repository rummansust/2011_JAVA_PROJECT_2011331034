/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javacodecompile;
 
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author Attoprottoyee!6
 * This class is used to start a JFrame which is the outlook of this application.
 */
public class StartUserOutlook
{
    /**
     * This method set the size of the frame.
     */
    public void startUser()
    {
        UserOutLook outLookObject =new UserOutLook();
        outLookObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        outLookObject.setBounds(180, 50, 960, 600);;
        outLookObject.setVisible(true);
        outLookObject.setResizable(false);
    }
}

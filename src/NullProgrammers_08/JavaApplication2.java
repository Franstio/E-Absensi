/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08;
import NullProgrammers_08.Libs.utilLib;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.JFrame;

/**
 *
 * @author DRONE003
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //AbsensiForm form = new AbsensiForm();
        System.out.println(utilLib.hash("1"));
        Login form = new Login();
        form.pack();
        form.setVisible(true);
//      Login login = new Login();
//      login.show();
    }
    
}

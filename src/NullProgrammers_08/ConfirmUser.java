/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
//import javaapplication2.Entities.Absensidetail;
//import javaapplication2.Entities.Absensiheader;
//import javaapplication2.Entities.Users;
import NullProgrammers_08.Libs.sqlLib;
import NullProgrammers_08.Libs.utilLib;
import NullProgrammers_08.Models.DetailAbsensiModel;
import NullProgrammers_08.Models.HeaderAbsensiModel;
import NullProgrammers_08.Models.UsersModel;
import javafx.concurrent.Task;
import javax.swing.JFrame;

/**
 *
 * @author DRONE003
 */
public class ConfirmUser extends javax.swing.JFrame {

    /**
     * Creates new form ConfirmUser
     */
    public static HeaderAbsensiModel header;
    public static List<DetailAbsensiModel> details;
    public static UsersModel user;
    public static JFrame parent2;
    public static JFrame parent;
    public ConfirmUser() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Password:");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println(jPasswordField1.getText() + "==" + user.password);
        System.out.println(jPasswordField1.getText().toString() == user.password.toString());
        if (utilLib.hash( jPasswordField1.getText().toString()).equals( user.password.toString()))
        {
            Thread thread = new Thread();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
//            DateTimeFormatter formatter = builder.appendPattern("yyyy-MM-dd HH:mm:ss.S").toFormatter();
//            Date dt = Date.from(Instant.now());
            //d = Timestamp.from(Instant.now());
            Timestamp d = Timestamp.valueOf(LocalDateTime.now());
            header.AbsenDate = d;
            header.total_mahasiswa = details.size();
            details.forEach((x) ->{
                switch (x.status.toString())
                {
                    case "Hadir":
                        header.total_hadir++;
                        break;
                    case "Sakit":
                        header.total_sakit++;
                        break;
                    case "Tidak Hadir":
                        header.total_alpha++;
                        break;
                    case "Izin":
                        header.total_izin++;
                        break;
                }
            });
            //System.out.println(jPasswordField1.getText() + "==" + user.password);
            sqlLib.InsertData("absensiHeader", header);
            for (DetailAbsensiModel dt : details)
            {
                dt.id = utilLib.generateCode("DTL", "absensiDetail", "id", DetailAbsensiModel.class);
                dt.id_header = header.id;
                sqlLib.InsertData("absensiDetail", dt);
            }
            parent.setVisible(true);
        }
        else
        {
            parent2.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfirmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfirmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfirmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfirmUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
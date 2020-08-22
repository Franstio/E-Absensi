/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08;
import NullProgrammers_08.Models.Imodels;
import NullProgrammers_08.Models.KelasModel;
import NullProgrammers_08.Models.ProdiModel;
import NullProgrammers_08.Models.HeaderAbsensiModel;
import NullProgrammers_08.Models.JurusanModel;
import NullProgrammers_08.Models.UsersModel;
import NullProgrammers_08.Libs.sqlLib;
import NullProgrammers_08.Libs.utilLib;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
//import javaapplication2.Entities.Absensiheader;
//import javaapplication2.Entities.Users;
import javax.swing.JOptionPane;
/**
 *
 * @author DRONE003
 */
public class DashboardDosen extends javax.swing.JFrame {

    public static UsersModel user;
    private static List<Imodels> _model2;
    private JurusanModel selectedJurusan;
    private ProdiModel selectedProdi;
    private KelasModel selectedKelas;
    /**
     * Creates new form DashboardDosen
     */
    public DashboardDosen() {
        initComponents();
        populateData();
        UsersModel[] users = new UsersModel[] { user};
        String role = utilLib.findRole(users ).getKey().role;
        System.out.print("role" + " ");
        this.jLabel1.setText( this.jLabel1.getText().replace("[role]", role));
        this.jLabel1.setText(this.jLabel1.getText().replace("[name]", user.name));
    }
    private void populateData()
    {
        System.out.println("PD");
         List<Imodels> _model = sqlLib.searchData4("jurusan", new String[]{"*"}, "", JurusanModel.class);
         this.jComboBox1.removeAllItems();
         jComboBox2.removeAllItems();
         jComboBox3.removeAllItems();
         jComboBox1.addItem("Jurusan");
         jComboBox2.addItem("Prodi");
         jComboBox3.addItem("Kelas");
         List<JurusanModel> jms = new ArrayList<JurusanModel>();
//         List<ProdiModel> pms = new ArrayList<ProdiModel>();
         List<ProdiModel> pms = new ArrayList<ProdiModel>();
         List<KelasModel> kms = new ArrayList<KelasModel>();
         for (Imodels m : _model)
         {
             JurusanModel model = m.loadClass(new JurusanModel());
             jms.add(model);
             this.jComboBox1.addItem(model.name);
         }
         this.jComboBox1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                 //To change body of generated methods, choose Tools | Templates.                                      jComboBox2.removeAllItems();
//                 jComboBox1.removeAllItems();
                 jComboBox2.removeAllItems();
                 jComboBox3.removeAllItems();
                 jComboBox2.addItem("Prodi");
                 jComboBox3.addItem("Kelas");
                 Boolean d = false;
                 for (JurusanModel n : jms)
                 {
                    if (n.name == jComboBox1.getItemAt(jComboBox1.getSelectedIndex()))
                    {
                        d=true;
                        selectedJurusan = n;
                        List<Imodels> Imodel = sqlLib.searchData4("prodi", new String[]{"*"}, String.format("where prodi.id_jurusan='%s'",n.id), ProdiModel.class);
                        Imodel.forEach((z) ->{
                             jComboBox2.addItem(z.loadClass(new ProdiModel()).name);
                             pms.add(z.loadClass(new ProdiModel()));
                         });
                    }
                 }
                 if (!d)
                 {
                     selectedJurusan = null;
                 }
                 selectedProdi = null;
                 selectedKelas = null;
            }             
         });
         this.jComboBox2.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                Boolean d = false;
                jComboBox3.removeAllItems();
                jComboBox3.addItem("Kelas");
                for (ProdiModel n : pms)
                {
                    if (n.name == jComboBox2.getItemAt(jComboBox2.getSelectedIndex()))
                    {
                        d=true;
                        selectedProdi = n;
                        List<Imodels> Imodel = sqlLib.searchData4("kelas", new String[]{"*"}, String.format("where kelas.id_prodi='%s'",n.id), KelasModel.class);
                        Imodel.forEach((z) -> {
                            jComboBox3.addItem(z.loadClass(new KelasModel()).name);
                            kms.add(z.loadClass(new KelasModel()));
                        });
                    }
                }
                if (!d)
                {
                    selectedProdi=null;
                }
                selectedKelas = null;
            }   
         });
         this.jComboBox3.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                kms.forEach((n) -> {
                 System.out.println(n.name + " == "+jComboBox3.getItemAt(jComboBox3.getSelectedIndex()));
                 if (n.name == jComboBox3.getItemAt(jComboBox3.getSelectedIndex()))
                 {
                     selectedKelas= n;
                 }
                });
            }
              
         });
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Howdy, [role] [name]");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jurusan" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setLabel("mulai absensi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prodi" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kelas" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 183, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
                
         
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
   
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if ((selectedKelas != null) && (selectedProdi != null) && (selectedJurusan != null))
        {
            System.out.println(selectedKelas);
            System.out.println(selectedProdi);
            System.out.println(selectedJurusan);
//            JOptionPane.showMessageDialog(this, "ERROR", "ERROR", JOptionPane.WARNING_MESSAGE);
            HeaderAbsensiModel mod = new HeaderAbsensiModel();
            mod.id = utilLib.generateCode("TRS", "absensiHeader", "id", HeaderAbsensiModel.class);
            mod.Dosen = Integer.parseInt(user.nik.toString());
            System.out.println(Timestamp.valueOf(LocalDateTime.now()));
            mod.id_kelas = selectedKelas.id;
            AbsensiForm.header = mod;
            AbsensiForm.user = user;
            AbsensiForm.parent = this;
//            System.out.println("TESTING "+ user.loadClass(new Users()).getNik().toString() );
            AbsensiForm confirm = new AbsensiForm();
            confirm.setVisible(true);
            this.setVisible(false);
        } 
        else 
        {
            //System.out.println(selectedJurusan.name + " " + selectedProdi.name + " " + selectedKelas.name);
            JOptionPane.showMessageDialog(this, "ERROR","ERROR",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardDosen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

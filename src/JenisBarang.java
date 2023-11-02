import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import koneksi.koneksi;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author utama
 */
public class JenisBarang extends javax.swing.JFrame {
    private DefaultTableModel model;

    /**
     * Creates new form JenisBarang
     */
    public JenisBarang() {
        initComponents();
        loadData();
        kosong();
       
    }
    
   
    private void loadData() {
        model = new DefaultTableModel();
        
        model.getDataVector().removeAllElements();
        
        model.fireTableDataChanged();
        
        tb_barang.setModel(model);
        model.addColumn("kode");
        model.addColumn("jenis");
        
        try{
            
            String sql = "SELECT * FROM tb_barang";
            
            Connection c = koneksi.getkoneksi();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(sql);
            
            while(r.next()){
                
                model.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2)
                });
            }
            tb_barang.setModel(model);
        }catch(SQLException e){
            System.out.println("Terjadi Error"); 
        }
    }

        private void kosong(){
        txtkodejenis.setText(null);
        txtjenisbarang.setText(null);
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
        jLabel2 = new javax.swing.JLabel();
        txtjenisbarang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_barang = new javax.swing.JTable();
        btnadd = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        btnclose = new javax.swing.JButton();
        txtkodejenis = new javax.swing.JTextField();
        btnupdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Kode Jenis");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel2.setText("Jenis Barang");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));
        getContentPane().add(txtjenisbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 260, -1));

        tb_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_barangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_barang);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 430, 150));

        btnadd.setText("Add Text");
        getContentPane().add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 80, 20));

        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 70, 20));

        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, 20));

        btncancel.setText("Cancel");
        getContentPane().add(btncancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, 20));

        btnclose.setText("Close");
        getContentPane().add(btnclose, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 80, 20));
        getContentPane().add(txtkodejenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 140, -1));

        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 80, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
         int i = tb_barang.getSelectedRow();

        if(i == -1){
            JOptionPane.showMessageDialog(this, "harap pilih data terlebih dahulu", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String kode = (String) model.getValueAt(i, 0);

        try{
            Connection c = koneksi.getkoneksi();

            String sql = "DELETE FROM tb_jenis_barang WHERE kode_jenis = ?";

            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, kode);
            p.executeUpdate();
            p.close();

            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "terjadi error"+e.getMessage());
        }finally{
            loadData();
            kosong();
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        int i = tb_barang.getSelectedRow();

        if(i == -1){
            JOptionPane.showMessageDialog(this, "harap pilih data terlebih dahulu", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String kode = (String)model.getValueAt(i, 0);
        String jenis = txtjenisbarang.getText();

        try{
            Connection c = koneksi.getkoneksi();

            String sql = "UPDATE tb_jenis_barang SET jenis_barang = ? WHERE kode_jenis";

            PreparedStatement p = c.prepareStatement(sql);

            p.setString(1, jenis);
            p.setString(2, kode);

            p.executeUpdate();
            p.close();

            JOptionPane.showMessageDialog(this, "Ubah Data Berhasil");

        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,"Terjadi Error" + e.getMessage());
        }finally{
            loadData();
            kosong();
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
         String kode = txtkodejenis.getText();
        String Jenis = txtjenisbarang.getText();

        if ("".equals(kode)|| "".equals(Jenis))

        {
            JOptionPane.showMessageDialog(this, "harap lengkap data", "error", JOptionPane.WARNING_MESSAGE);
        }else{

            try{
                Connection c = koneksi.getkoneksi();
                String sql = "INSERT INTO tb_jenis_barang VALUES (?,?)";
                PreparedStatement p = c.prepareStatement(sql);

                p.setString(1, kode);
                p.setString(2, Jenis);

                p.executeUpdate();
                p.close();

                JOptionPane.showMessageDialog(this, "penyimpanan Data Berhasil");

            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }finally{
                loadData();
                kosong();
            }
        }
    }//GEN-LAST:event_btnsaveActionPerformed

    private void tb_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_barangMouseClicked
        // TODO add your handling code here:
        int baris = tb_barang.getSelectedRow();
        
        if(baris == -1){
            //tak ada baris terseleksi
            return;
        
    }    
        
        String jenis_barang = tb_barang.getValueAt(baris, 2).toString();
        txtjenisbarang.setText(jenis_barang);
       String KodeBarang = tb_barang.getValueAt(baris, 1).toString();
       txtjenisbarang.setText(jenis_barang);
    }//GEN-LAST:event_tb_barangMouseClicked

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
            java.util.logging.Logger.getLogger(JenisBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JenisBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JenisBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JenisBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JenisBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btnclose;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_barang;
    private javax.swing.JTextField txtjenisbarang;
    private javax.swing.JTextField txtkodejenis;
    // End of variables declaration//GEN-END:variables
}

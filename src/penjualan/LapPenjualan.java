// Fidela Azzahra
// A11.2021.13417

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package penjualan;

import java.io.File;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TUF
 */

public class LapPenjualan extends javax.swing.JFrame {
    Connection Con;
    ResultSet RsJual;
    Statement stm;
    PreparedStatement pstat;
    String tgl1,tgl2;
    private Object[][] dataTable = null;
    
    private String[] header = 
        {"No Jual","Tanggal","Nama Konsumen","Kode Barang","Nama Barang","Harga Satuan","Jumlah","Total"};
    DefaultTableModel tableModel = new DefaultTableModel(new Object [][] {},header);
    
    
    /**
     * Creates new form LapPenjualan
     */
    public LapPenjualan() {
        initComponents();
        dtAwal.setDate(new Date());
        dtAkhir.setDate(new Date());
        open_db();
        baca_data();
    }
    
    private void open_db()
    {
        try{
            KoneksiMysql kon = new KoneksiMysql ("localhost","root","","penjualan");
            Con = kon.getConnection();
        
        } catch (Exception e) {
            System.out.println("Error : "+e);
        }
    }
    
    private void baca_data()
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tgl1=sdf.format(dtAwal.getDate());
            tgl2=sdf.format(dtAkhir.getDate());
            stm = Con.createStatement();

            pstat = Con.prepareStatement("SELECT a.no_jual,a.tgl_jual,c.nm_kons,d.kd_brg,d.nm_brg,b.harga_jual,b.jml_jual,(b.harga_jual*b.jml_jual) AS 'totjual' " +
            "FROM jual a LEFT JOIN djual b ON (a.no_jual=b.no_jual) " +
            "LEFT JOIN konsumen c ON (a.kd_kons=c.kd_kons) " +
            "LEFT JOIN barang d ON (b.kd_brg=d.kd_brg)" +
            "where date(a.tgl_jual)>=" + "date('"+ tgl1+"') and date(a.tgl_jual)<=" + "date('"+tgl2+"')order by a.tgl_jual desc",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            RsJual = pstat.executeQuery();
            ResultSetMetaData meta = RsJual.getMetaData();
            int col = meta.getColumnCount();
            int baris = 0;
            
            while(RsJual.next()) {
                baris = RsJual.getRow();
            }
            
            dataTable = new Object[baris][col];
            int x = 0;
            RsJual.beforeFirst();
            
            while(RsJual.next()) {
                dataTable[x][0] = RsJual.getString("no_jual");
                dataTable[x][1] = RsJual.getDate("tgl_jual");
                dataTable[x][2] = RsJual.getString("nm_kons");
                dataTable[x][3] = RsJual.getString("kd_brg");
                dataTable[x][4] = RsJual.getString("nm_brg");
                dataTable[x][5] = RsJual.getDouble("harga_jual");
                dataTable[x][6] = RsJual.getInt("jml_jual");
                dataTable[x][7] = RsJual.getDouble("totjual");
                x++;
            }
            
            tblLapJual.setModel(new DefaultTableModel(dataTable,header));
            
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
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
        dtAwal = new com.toedter.calendar.JDateChooser();
        dtAkhir = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLapJual = new javax.swing.JTable();
        cmdCari = new javax.swing.JButton();
        cmdExport = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("LAPORAN PENJUALAN ");

        dtAwal.setDateFormatString("yyyy-MM-dd");

        dtAkhir.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("Tanggal Sekarang");

        jLabel5.setText("Tanggal Selesai");

        tblLapJual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Jual", "Tanggal", "Nama Konsumen", "Kode Barang", "Nama Barang", "Harga Satuan", "Jumlah", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblLapJual);

        cmdCari.setBackground(new java.awt.Color(51, 51, 255));
        cmdCari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmdCari.setForeground(new java.awt.Color(255, 255, 255));
        cmdCari.setText("Cari");
        cmdCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCariActionPerformed(evt);
            }
        });

        cmdExport.setBackground(new java.awt.Color(51, 51, 255));
        cmdExport.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmdExport.setForeground(new java.awt.Color(255, 255, 255));
        cmdExport.setText("Export");
        cmdExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExportActionPerformed(evt);
            }
        });

        cmdKeluar.setBackground(new java.awt.Color(255, 51, 51));
        cmdKeluar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmdKeluar.setForeground(new java.awt.Color(255, 255, 255));
        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtAkhir, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(dtAwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdCari)
                        .addGap(18, 18, 18)
                        .addComponent(cmdExport)
                        .addGap(18, 18, 18)
                        .addComponent(cmdKeluar)))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(267, 267, 267))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtAwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtAkhir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdCari)
                            .addComponent(cmdExport)
                            .addComponent(cmdKeluar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCariActionPerformed
        // TODO add your handling code here:
        baca_data();
    }//GEN-LAST:event_cmdCariActionPerformed

    private void cmdExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExportActionPerformed
        // TODO add your handling code here:
        try{
            ExportToExcel ex=new ExportToExcel(tblLapJual, new File("DataPenjualan.xls"));
            
            //exportToExcel(tblBrg, new File("DataBarang.xls"));
            JOptionPane.showMessageDialog(null, "Sukses Export data .....");
           
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_cmdExportActionPerformed

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LapPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCari;
    private javax.swing.JButton cmdExport;
    private javax.swing.JButton cmdKeluar;
    private com.toedter.calendar.JDateChooser dtAkhir;
    private com.toedter.calendar.JDateChooser dtAwal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLapJual;
    // End of variables declaration//GEN-END:variables
}

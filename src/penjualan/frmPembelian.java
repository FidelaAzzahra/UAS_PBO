// Fidela Azzahra
// A11.2021.13417

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package penjualan;

import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class frmPembelian extends javax.swing.JFrame {
    Connection Con;
    ResultSet RsBrg;
    ResultSet RsPms;
    Statement stm;
    double total=0;
    String tanggal;
    Boolean edit=false;
    PreparedStatement pstat;
    String sKd_Pms;
    String sKd_Brg;
    String xnobeli;
    
    DefaultTableModel tableModel = new DefaultTableModel(
            new Object [][]{}, 
            new String [] {
                "Kd Barang", "Nama Barang","Harga Barang","Jumlah","Total"});
    
    /**
     * Creates new form frmPembelian
     */
    
    public frmPembelian() {
        initComponents();
        open_db();
        inisialisasi_tabel();
        aktif(false);
        setTombol(true);
        cmdCetak.setEnabled(false);
        txtTgl.setEditor(new JSpinner.DateEditor(txtTgl, "yyyy/MM/dd")); // harus spinner date
    }
    
    private void setField()
    {
        int row=tblBeli.getSelectedRow();
        cmbKd_Brg.setSelectedItem((String)tblBeli.getValueAt(row,0));
        txtNm_Brg.setText((String)tblBeli.getValueAt(row,1));
        String harga = Double.toString((Double)tblBeli.getValueAt(row,2));
        txtHarga.setText(harga);
        String jumlah=Integer.toString((Integer)tblBeli.getValueAt(row,3));
        txtJml.setText(jumlah);
        String total=Double.toString((Double)tblBeli.getValueAt(row,4));
        txtTot.setText(total);
    }
    
    // method hitung pembelian
    private void hitung_beli()
    {
        double xtot,xhrg;
        int xjml;
        
        xhrg=Double.parseDouble(txtHarga.getText());
        xjml=Integer.parseInt(txtJml.getText());
        xtot=xhrg*xjml;
        String xtotal=Double.toString(xtot);
        txtTot.setText(xtotal);
        total=total+xtot;
        txtTotal.setText(Double.toString(total));
    }
    
    // method koneksi ke database
    private void open_db()
    {
        try{
            KoneksiMysql kon = new KoneksiMysql ("localhost","root","","penjualan");
            Con = kon.getConnection();
            
        //System.out.println("Berhasil ");
        }catch (Exception e) {
            System.out.println("Error : "+e);
        }
    }
    
    // method baca data pemasok
    private void baca_pemasok()
    {
        try{
            stm=Con.createStatement();
            pstat = Con.prepareStatement("select kd_pms,nm_pms from pemasok",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstat.executeQuery();
            rs.beforeFirst();
        while(rs.next())
        {
            cmbKd_Pms.addItem(rs.getString(1).trim());
        }
        rs.close();
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    // metod set model table
    public void inisialisasi_tabel()
    {
        tblBeli.setModel(tableModel);
    }
    
    // method baca data barang
    private void baca_barang()
    {
        try{
            stm=Con.createStatement();
            pstat = Con.prepareStatement("select * from barang",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstat.executeQuery();
            rs.beforeFirst();
        while(rs.next())
        {
            cmbKd_Brg.addItem(rs.getString(1).trim());
        }
        rs.close();
        } catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    // method baca barang setelah combo barang di klik
    private void detail_barang(String xkode)
    {
        try{
            stm=Con.createStatement();
            pstat = Con.prepareStatement("select * from barang where kd_brg='"+xkode+"'",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstat.executeQuery();
            rs.beforeFirst();
            
        while(rs.next())
        {
            txtNm_Brg.setText(rs.getString(2).trim());
            txtHarga.setText(Double.toString((Double)rs.getDouble(4)));
        }
        
        rs.close();
        } catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    private void setTombol(boolean t)
    {
        cmdTambah.setEnabled(t);
        cmdSimpan.setEnabled(!t);
        cmdBatal.setEnabled(!t);
        cmdKeluar.setEnabled(t);
        cmdHapusItem.setEnabled(!t);
    }
    
    // method baca konsumen setelah combo konsumen di klik
    private void detail_pemasok(String xkode)
    {
        try{
            stm=Con.createStatement();
            pstat = Con.prepareStatement("select * from pemasok where kd_pms='"+xkode+"'",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstat.executeQuery();
            rs.beforeFirst();
        while(rs.next()){
            txtNama.setText(rs.getString(2).trim());
        }
        rs.close();
        } catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    // method pengkosongan isian
    private void kosong()
    {
        txtNoBeli.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtTotal.setText("");
        text.setText("");
    }
    
    // method kosongkan detail beli
    private void kosong_detail()
    {
        txtNm_Brg.setText("");
        txtHarga.setText("");
        txtJml.setText("");
        txtTot.setText("");
    }
    
    // method aktif on/off isian
    private void aktif(boolean x)
    {
        cmbKd_Pms.setEnabled(x);
        cmbKd_Brg.setEnabled(x);
        cmbKd_Pms.removeAllItems();
        cmbKd_Brg.removeAllItems();
        txtTgl.setEnabled(x);
        txtJml.setEditable(x);
    }
    
    // method buat nomor beli otomatis
    private void nomor_beli()
    {
        try {
            stm = Con.createStatement();
            pstat = Con.prepareStatement("SELECT no_beli FROM beli ORDER BY no_beli DESC LIMIT 1",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstat.executeQuery();
            int brs=0;
            
            while(rs.next()){
                brs=rs.getRow();
            }
            
            if(brs==0)
                txtNoBeli.setText("1");
                else {
                    rs.beforeFirst();
                    while(rs.next()){
                        int no=rs.getInt("no_beli")+1;
                        txtNoBeli.setText(Integer.toString(no));
                    }
                }
            
            rs.close();
            } catch(SQLException e){
                System.out.println("Error : "+e);
        }
    }
    
    private void format_tanggal()
    {
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();
        int year=c1.get(Calendar.YEAR);
        int month=c1.get(Calendar.MONTH)+1;
        int day=c1.get(Calendar.DAY_OF_MONTH);
        tanggal=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
    }
    
    // method simpan detail beli di tabel
    private void simpan_ditabel()
    {
        try {
            String tKode=cmbKd_Brg.getSelectedItem().toString();
            String tNama=txtNm_Brg.getText();
            double hrg=Double.parseDouble(txtHarga.getText());
            int jml=Integer.parseInt(txtJml.getText());
            double tot=Double.parseDouble(txtTot.getText());
            tableModel.addRow(new Object[]{tKode,tNama,hrg,jml,tot});
            inisialisasi_tabel();
            
        } catch(NumberFormatException e){
            System.out.println("Error : "+e);
        }
    }
    
    // method simpan transaksi pembelian pada table di Mysql
    private void simpan_pembelian()
    {
        try{
            xnobeli=txtNoBeli.getText();
            format_tanggal();
            String xkode=cmbKd_Pms.getSelectedItem().toString();
            String msql="insert into beli values('"+xnobeli+"','"+xkode+"','"+tanggal+"')";
            stm.executeUpdate(msql);
            
        for(int i=0;i<tblBeli.getRowCount();i++)
        {
            String xkd=(String)tblBeli.getValueAt(i,0);
            double xhrg=(Double)tblBeli.getValueAt(i,2);
            int xjml=(Integer)tblBeli.getValueAt(i,3);
            String zsql="insert into dbeli values('"+xnobeli+"','"+xkd+"',"+xhrg+","+xjml+")";
            stm.executeUpdate(zsql);
            
            //update stok
            String ysql="update barang set stok=stok+"+xjml+" where kd_brg='"+xkd+"'";
            stm.executeUpdate(ysql);
        }
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    private class PrintingTask extends SwingWorker<Object, Object> {
        private final MessageFormat headerFormat;
        private final MessageFormat footerFormat;
        private final boolean interactive;
        private volatile boolean complete = false;
        private volatile String message;

        public PrintingTask(MessageFormat header, MessageFormat footer, boolean interactive) {
            this.headerFormat = header;
            this.footerFormat = footer;
            this.interactive = interactive;
        }
        @Override
        protected Object doInBackground() {
            try {
                complete = text.print(headerFormat, footerFormat, true, null, null, interactive);
                message = "Printing " + (complete ? "complete" : "canceled");
            } catch (PrinterException ex) {
                message = "Sorry, a printer error occurred";
            } catch (SecurityException ex) {
                message = "Sorry, cannot access the printer due to security reasons";
            }
            return null;
            }

        @Override
        protected void done() {
            //message(!complete, message);
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
        jLabel2 = new javax.swing.JLabel();
        txtNoBeli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTgl = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        cmbKd_Pms = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        cmbKd_Brg = new javax.swing.JComboBox<>();
        txtNm_Brg = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtJml = new javax.swing.JTextField();
        txtTot = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBeli = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        cmdTambah = new javax.swing.JButton();
        cmdSimpan = new javax.swing.JButton();
        cmdHapusItem = new javax.swing.JButton();
        cmdBatal = new javax.swing.JButton();
        cmdCetak = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("TRANSAKSI PEMBELIAN");

        jLabel2.setText("No Beli");

        jLabel3.setText("Tgl Beli");

        txtTgl.setModel(new javax.swing.SpinnerDateModel());

        jLabel4.setText("Kode Pemasok");

        cmbKd_Pms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKd_PmsActionPerformed(evt);
            }
        });

        jLabel5.setText("Nama Pemasok");

        cmbKd_Brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKd_BrgActionPerformed(evt);
            }
        });

        txtNm_Brg.setText("Nama Barang");

        txtHarga.setText("Harga");

        txtJml.setText("Jml");
        txtJml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJmlKeyPressed(evt);
            }
        });

        txtTot.setText("Total");

        tblBeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kd Barang", "Nama Barang", "Harga Barang", "Jumlah ", "Total"
            }
        ));
        tblBeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBeliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBeli);

        text.setColumns(20);
        text.setRows(5);
        jScrollPane2.setViewportView(text);

        jLabel6.setText("Total Harga");

        txtTotal.setText("Total harganya nanti muncul disini");

        cmdTambah.setBackground(new java.awt.Color(51, 51, 255));
        cmdTambah.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        cmdTambah.setForeground(new java.awt.Color(255, 255, 255));
        cmdTambah.setText("Tambah");
        cmdTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTambahActionPerformed(evt);
            }
        });

        cmdSimpan.setBackground(new java.awt.Color(51, 51, 255));
        cmdSimpan.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        cmdSimpan.setForeground(new java.awt.Color(255, 255, 255));
        cmdSimpan.setText("Simpan");
        cmdSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSimpanActionPerformed(evt);
            }
        });

        cmdHapusItem.setBackground(new java.awt.Color(51, 51, 255));
        cmdHapusItem.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        cmdHapusItem.setForeground(new java.awt.Color(255, 255, 255));
        cmdHapusItem.setText("Hapus Item");
        cmdHapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHapusItemActionPerformed(evt);
            }
        });

        cmdBatal.setBackground(new java.awt.Color(51, 51, 255));
        cmdBatal.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        cmdBatal.setForeground(new java.awt.Color(255, 255, 255));
        cmdBatal.setText("Batal");
        cmdBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBatalActionPerformed(evt);
            }
        });

        cmdCetak.setBackground(new java.awt.Color(51, 51, 255));
        cmdCetak.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        cmdCetak.setForeground(new java.awt.Color(255, 255, 255));
        cmdCetak.setText("Cetak");
        cmdCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCetakActionPerformed(evt);
            }
        });

        cmdKeluar.setBackground(new java.awt.Color(255, 51, 51));
        cmdKeluar.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
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
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdTambah)
                        .addGap(18, 18, 18)
                        .addComponent(cmdSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(cmdHapusItem, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdBatal)
                        .addGap(18, 18, 18)
                        .addComponent(cmdCetak)
                        .addGap(18, 18, 18)
                        .addComponent(cmdKeluar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(cmbKd_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNm_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTot, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNoBeli)
                                        .addComponent(txtTgl, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                                    .addGap(70, 70, 70)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(3, 3, 3)))
                            .addGap(23, 23, 23)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbKd_Pms, 0, 147, Short.MAX_VALUE)
                                .addComponent(txtNama)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNoBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cmbKd_Pms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKd_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNm_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTambah)
                    .addComponent(cmdSimpan)
                    .addComponent(cmdHapusItem)
                    .addComponent(cmdBatal)
                    .addComponent(cmdCetak)
                    .addComponent(cmdKeluar))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbKd_PmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKd_PmsActionPerformed
        // TODO add your handling code here:
        JComboBox <String> cKd_Pms = (javax.swing.JComboBox<String>) evt.getSource();
        
        // Membaca Item Yang Terpilih -> String
        sKd_Pms = (String) cKd_Pms.getSelectedItem();
        detail_pemasok(sKd_Pms);
    }//GEN-LAST:event_cmbKd_PmsActionPerformed

    private void cmbKd_BrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKd_BrgActionPerformed
        // TODO add your handling code here:
        JComboBox cKd_Brg = (javax.swing.JComboBox)evt.getSource();
        
        //Membaca Item Yang Terpilih — > String
        sKd_Brg = (String)cKd_Brg.getSelectedItem();
        detail_barang(sKd_Brg);
        
        txtJml.setText("");
        txtTot.setText("");
    }//GEN-LAST:event_cmbKd_BrgActionPerformed

    private void txtJmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hitung_beli();
            simpan_ditabel();
        }
    }//GEN-LAST:event_txtJmlKeyPressed

    private void tblBeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBeliMouseClicked
        // TODO add your handling code here:
        setField();
    }//GEN-LAST:event_tblBeliMouseClicked

    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTambahActionPerformed
        // TODO add your handling code here:
        aktif(true);
        setTombol(false);
        kosong();
        baca_pemasok();
        baca_barang();
        nomor_beli();
    }//GEN-LAST:event_cmdTambahActionPerformed

    private void cmdSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSimpanActionPerformed
        // TODO add your handling code here:
        simpan_pembelian();
        aktif(false);
        setTombol(true);
        kosong();
        cmdCetak.setEnabled(true);
        kosong_detail();
    }//GEN-LAST:event_cmdSimpanActionPerformed

    private void cmdHapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHapusItemActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dataModel = (DefaultTableModel) tblBeli.getModel();
        int row=tblBeli.getSelectedRow();

        if (row != -1) { // Check if a row is selected
            dataModel.removeRow(row);

            double xtot, xhrg;
            int xjml;
            xhrg = Double.parseDouble(txtHarga.getText());
            xjml = Integer.parseInt(txtJml.getText());
            xtot = xhrg * xjml;
            double total = Double.parseDouble(txtTotal.getText()) - xtot;
            txtTotal.setText(Double.toString(total));
        }
    }//GEN-LAST:event_cmdHapusItemActionPerformed

    private void cmdBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBatalActionPerformed
        // TODO add your handling code here:
        aktif(false);
        setTombol(true);
        kosong();
    }//GEN-LAST:event_cmdBatalActionPerformed

    private void cmdCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCetakActionPerformed
        // TODO add your handling code here:
        format_tanggal();
        
        String ctk="Nota Pembelian\nNo : "+xnobeli+"\nTanggal : "+tanggal;
        ctk=ctk+"\n"+"--------------------------------------------------------------------";
        ctk=ctk+"\n"+"Kode\tNama Barang\tHarga\tJml\tTotal";
        ctk=ctk+"\n"+"--------------------------------------------------------------------";
        
        for(int i=0;i<tblBeli.getRowCount();i++)
        {
            String xkd=(String)tblBeli.getValueAt(i,0);
            String xnama=(String)tblBeli.getValueAt(i,1);
            double xhrg=(Double)tblBeli.getValueAt(i,2);
            int xjml=(Integer)tblBeli.getValueAt(i,3);
            double xtot=(Double)tblBeli.getValueAt(i,4);
            ctk=ctk+"\n"+xkd+"\t"+xnama+"\t"+xhrg+"\t"+xjml+"\t"+xtot;
        }
        
        ctk=ctk+"\n"+"--------------------------------------------------------------------";
        text.setText(ctk);
        String headerField="";
        String footerField="";
        MessageFormat header = new MessageFormat(headerField);
        MessageFormat footer = new MessageFormat(footerField);;
        boolean interactive = true;//interactiveCheck.isSelected();
        boolean background = true;//backgroundCheck.isSelected();
        frmPembelian.PrintingTask task = new frmPembelian.PrintingTask(header, footer, interactive);
        
        if (background) {
            task.execute();
        } else {
            task.run();
        }
    }//GEN-LAST:event_cmdCetakActionPerformed

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
            java.util.logging.Logger.getLogger(frmPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbKd_Brg;
    private javax.swing.JComboBox<String> cmbKd_Pms;
    private javax.swing.JButton cmdBatal;
    private javax.swing.JButton cmdCetak;
    private javax.swing.JButton cmdHapusItem;
    private javax.swing.JButton cmdKeluar;
    private javax.swing.JButton cmdSimpan;
    private javax.swing.JButton cmdTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBeli;
    private javax.swing.JTextArea text;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJml;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNm_Brg;
    private javax.swing.JTextField txtNoBeli;
    private javax.swing.JSpinner txtTgl;
    private javax.swing.JTextField txtTot;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

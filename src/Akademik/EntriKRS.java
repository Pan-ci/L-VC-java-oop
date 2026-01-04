/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Akademik;
import com.itextpdf.io.IOException;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 3cc
 */
public class EntriKRS extends javax.swing.JFrame {
    public Connection conn;
    public Statement cn;
    public ResultSet rs;
    public PreparedStatement stmt;
    /**
     * Creates new form EntriKRS
     */
    public EntriKRS() {
        initComponents();
        bersih();
        tblKRS.setModel(tblmodel);
        tampildataa();
    }

    String data[] = new String[3];
    private final javax.swing.table.DefaultTableModel tblmodel=getDefaultTableModel();
    int row =0;
    private javax.swing.table.DefaultTableModel getDefaultTableModel(){
    return new javax.swing.table.DefaultTableModel
    ( new Object[][]{},
    new String[]{"Kode","Matakuliah" ,"SKS"}
    );
    }
    
    private void koneksi() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/akademik2322009","root","");
            cn = conn.createStatement();
            System.out.println("Koneksi dan statement berhasil dibuat!");
        }catch(SQLException e){
            /*JOptionPane.showMessageDialog(null, "Field " + field.getName() + 
                    " tidak boleh kurang dari satu!", 
                    "input tidak valid!", JOptionPane.ERROR_MESSAGE);*/
            JOptionPane.showMessageDialog(null, "Kesalahan koneksi atau pembuatan statement\n" + e.getMessage(),
                                            "Masalah Koneksi-Statement", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Kesalahan penggunaan driver\n" + e.getMessage(),
                                            "Masalah Driver", JOptionPane.ERROR_MESSAGE);
            System.exit(0); //harusnya nilainya 1
        }   
    }
    
    private void tampildataa(){
        DefaultTableModel tabelnyo = new DefaultTableModel();
        tabelnyo.addColumn("Semester");
        tabelnyo.addColumn("NIM");
        tabelnyo.addColumn("Nama Mahasiswa");
        tabelnyo.addColumn("Tanggal Pengisian KRS");
        tabelnyo.addColumn("Kode Mata Kuliah");
        tabelnyo.addColumn("Mata Kuliah");
        tabelnyo.addColumn("SKS");
        try{
        koneksi();
        String sql = "Select semKRS_2322009, NIMMHSKRS_2322009, namaMHS_2322009,"
                + " tglIsiKRS_2322009, kodeMKKRS_2322009, namaMK_2322009,"
                + "nilaiKRS_2322009 from krs_2322009 "
                + "join mahasiswa_2322009 on NIMMHSKRS_2322009 = NIMMHS_2322009 "
                + "join matakuliah_2322009 on kodeMKKRS_2322009 = kodeMK_2322009";
                //+ " where NIMMHS_2322009 ='"+fdCariMK.getText()+"' or namaMHS_2322009='"+fdCariMK.getText()+"'";
        rs = cn.executeQuery(sql);
        while(rs.next())
        {
        tabelnyo.addRow(new Object[]{
        rs.getInt(1),
        rs.getString(2),
        rs.getString(3),
        rs.getDate(4),
        rs.getString(5),
        rs.getString(6),
        rs.getInt(7)
        });
        }
        tblAKRS.setModel(tabelnyo);
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Ada Kesalahan\n" + e);
        }
        }
    
    private void bersih(){
        fdSemester.setText("");
        fdNIM.setText("");
        fdNama.setText("");
        fdKDMK.setText("");
        fdMK.setText("");
        fdSKS.setText("");
        } 
    
    void cekkrs(){
        DefaultTableModel tabelnyo = new DefaultTableModel();
        tabelnyo.addColumn("Kode");
        tabelnyo.addColumn("Nama Matakuliah");
        tabelnyo.addColumn("SKS");
        try{
        koneksi();
        String sql = "SELECT idKRS_2322009,semKRS_2322009,kodeMKKRS_2322009,"
        + "NIMMHSKRS_2322009,namaMHS_2322009,nilaiKRS_2322009, namaMK_2322009, nilaiKRS_2322009 "
        + "FROM krs_2322009 join mahasiswa_2322009 on NIMMHSKRS_2322009=NIMMHS_2322009 "
        + "join matakuliah_2322009 on kodeMKKRS_2322009=kodeMK_2322009"
        + " WHERE semKRS_2322009='"+ fdSemester.getText() +"' AND NIMMHSKRS_2322009='"+fdNIM.getText() +"'";
        rs = cn.executeQuery(sql);
        while (rs.next()) {
        tabelnyo.addRow(new Object[]{
        rs.getString(3),
        rs.getString(7),
        rs.getString(8)
        });

        }
        tblKRS.setModel(tabelnyo);
        totalSKS(tblKRS);
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null,"Kesalahan Menampilkan Data"+ e);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fdSemester = new javax.swing.JTextField();
        fdNIM = new javax.swing.JTextField();
        fdNama = new javax.swing.JTextField();
        btnCariMHS = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCariMK = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fdKDMK = new javax.swing.JTextField();
        fdMK = new javax.swing.JTextField();
        fdSKS = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnKurang = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAKRS = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKRS = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnCetakPDF = new javax.swing.JButton();
        fieldTotalSKS = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("NAMA");

        fdSemester.setName("Semester"); // NOI18N
        fdSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdSemesterActionPerformed(evt);
            }
        });

        fdNIM.setName("NIM mahasiswa"); // NOI18N
        fdNIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdNIMActionPerformed(evt);
            }
        });
        fdNIM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fdNIMKeyPressed(evt);
            }
        });

        fdNama.setName("Nama"); // NOI18N
        fdNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdNamaActionPerformed(evt);
            }
        });

        btnCariMHS.setText("Cari Mahasiswa");
        btnCariMHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMHSActionPerformed(evt);
            }
        });

        jLabel1.setText("SEMESTER");

        jLabel3.setText("NIM MAHASISWA");

        btnCariMK.setText("Cari Mata Kuliah");
        btnCariMK.setName(""); // NOI18N
        btnCariMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fdSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fdNIM, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(fdNama, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCariMHS)
                        .addGap(18, 18, 18)
                        .addComponent(btnCariMK)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fdSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(fdNIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fdNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCariMHS)
                    .addComponent(btnCariMK))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel4.setText("SKS");

        fdKDMK.setName("Kode Mata Kuliah"); // NOI18N

        fdMK.setName("Mata Kuliah"); // NOI18N

        fdSKS.setName("SKS"); // NOI18N

        btnTambah.setText("+");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        jLabel5.setText("KODE MK");

        jLabel6.setText("MATA KULIAH");

        btnKurang.setText("-");
        btnKurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fdKDMK, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fdMK))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fdSKS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnKurang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fdKDMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fdMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fdSKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah)
                    .addComponent(btnKurang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblAKRS.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAKRS);

        tblKRS.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKRS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKRSMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKRS);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnCetakPDF.setText("Cetak PDF");
        btnCetakPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakPDFActionPerformed(evt);
            }
        });

        fieldTotalSKS.setName("Total SKS"); // NOI18N
        fieldTotalSKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTotalSKSActionPerformed(evt);
            }
        });

        jLabel8.setText("Total SKS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSimpan)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldTotalSKS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCetakPDF))
                .addGap(0, 42, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldTotalSKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnCetakPDF))
                .addContainerGap())
        );

        jLabel7.setText("Entri KRS / Cetak KRS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(467, 467, 467)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fdNIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdNIMActionPerformed
        // TODO add your handling code here:
        cekkrs();
        btnCariMK.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnTambah.setEnabled(false);
        btnKurang.setEnabled(false);
    }//GEN-LAST:event_fdNIMActionPerformed

    private void txtnimKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
        try{

        koneksi();
        String sql = "Select * from mahasiswa_2322009 where NIMMHS_2322009='"+fdNIM.getText()+"'";
        rs = cn.executeQuery(sql);
        if (rs.next())
        {
        fdNama.setText(rs.getString(2));
        fdKDMK.requestFocus();
        }
        else
        {
        JOptionPane.showMessageDialog(null,"Data Mahasiswa Tidak Ada");
        fdNIM.requestFocus();
        }
        } catch (HeadlessException | SQLException e){
        JOptionPane.showMessageDialog(null, "Ada Kesalahan Menampilkan Data Mahasiswa\n"
       + e);
        }

        }
        }
    
    private void fdNIMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fdNIMKeyPressed
        // TODO add your handling code here:
        txtnimKeyPressed(evt);
    }//GEN-LAST:event_fdNIMKeyPressed

    private void btnTambahActionPerformed1() {
    // TODO add your handling code here:
        String k = fdKDMK.getText();
        if (k.equals("")){
        JOptionPane.showMessageDialog(null, "Kode Matakuliah Belum Diisi");
        }
        else{
        data[0]= fdKDMK.getText();
        data[1]= fdMK.getText();
        data[2]= fdSKS.getText();
        tblmodel.insertRow(row, data);
        fdKDMK.setText("");
        fdMK.setText("");
        fdSKS.setText("");
        }
        } 
    
    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        btnTambahActionPerformed1();
        totalSKS(tblKRS);
    }//GEN-LAST:event_btnTambahActionPerformed
        
        private void btnKurangActionPerformed1() {
        // TODO add your handling code here:
            tblmodel.removeRow(row);
            fdKDMK.setText("");
            fdMK.setText("");
            fdSKS.setText("");
            }
    private void btnKurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurangActionPerformed
        // TODO add your handling code here:
        btnKurangActionPerformed1();
        totalSKS(tblKRS);
    }//GEN-LAST:event_btnKurangActionPerformed

    private void tblKRSMouseClicked1() {
    // TODO add your handling code here:
        row = tblKRS.getSelectedRow();
        fdKDMK.setText(tblKRS.getValueAt(row, 0).toString());
        fdMK.setText(tblKRS.getValueAt(row, 1).toString());
        fdSKS.setText(tblKRS.getValueAt(row, 2).toString());
        }
    private void tblKRSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKRSMouseClicked
        // TODO add your handling code here:
        tblKRSMouseClicked1();
    }//GEN-LAST:event_tblKRSMouseClicked

    private void btnCariMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMKActionPerformed
        // TODO add your handling code here:
        new CariMK().show();
    }//GEN-LAST:event_btnCariMKActionPerformed

    private void btnCariMHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMHSActionPerformed
        // TODO add your handling code here:
        btnCariMK.setEnabled(true);
        btnSimpan.setEnabled(true);
        btnTambah.setEnabled(true);
        btnKurang.setEnabled(true);
        tblKRS.setModel(tblmodel);
        totalSKS(tblKRS);
        new CariMHS().show();
    }//GEN-LAST:event_btnCariMHSActionPerformed

    /*import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SimpanKRS {

    // Misalkan ini adalah method yang dijalankan saat tombol simpan ditekan
    public void simpanKRS(JTextField tfNIM, JTextField tfNama, JTextField tfSemester, JTable tblMatakuliah) {
        String nim = tfNIM.getText();
        String nama = tfNama.getText();
        String semester = tfSemester.getText();
        String tglIsiKRS = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()); // Mengambil tanggal hari ini

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Buat koneksi ke database (misalkan menggunakan JDBC)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_krs", "username", "password");

            // Siapkan query untuk memasukkan data ke tabel KRS
            String sql = "INSERT INTO KRS (tglIsiKRS, semKRS, NIMMHS, kodeMK, nilai) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);

            // Ambil data dari tabel mata kuliah
            DefaultTableModel model = (DefaultTableModel) tblMatakuliah.getModel();
            int rowCount = model.getRowCount();

            // Proses semua baris data yang ada di tabel mata kuliah
            for (int i = 0; i < rowCount; i++) {
                String kodeMK = (String) model.getValueAt(i, 0);  // Ambil kode mata kuliah dari kolom pertama
                String namaMK = (String) model.getValueAt(i, 1);  // Ambil nama mata kuliah dari kolom kedua
                int sks = (int) model.getValueAt(i, 2);            // Ambil SKS mata kuliah dari kolom ketiga

                // Set nilai untuk query KRS
                ps.setString(1, tglIsiKRS);  // Set tanggal isi KRS
                ps.setString(2, semester);   // Set semester KRS
                ps.setString(3, nim);        // Set NIM mahasiswa
                ps.setString(4, kodeMK);     // Set kode mata kuliah
                ps.setInt(5, sks);           // Set SKS sebagai nilai

                // Eksekusi query untuk menyimpan data KRS
                ps.addBatch();  // Menambahkan query ini ke batch
            }

            // Menjalankan batch untuk menyimpan semua data KRS
            ps.executeBatch();

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(null, "Data KRS berhasil disimpan");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data KRS: " + e.getMessage());
        } finally {
            // Menutup koneksi dan statement
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
*/
    private void simpanKRS(){
        //String nama = fdNama.getText();
        String nim = fdNIM.getText();
        int sem = Integer.parseInt(fdSemester.getText());
        String tglIsiKRS = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()); // Mengambil tanggal hari ini
        
        try{
            koneksi();
            String sqlKRS = "INSERT INTO krs_2322009 (tglIsiKRS_2322009, "
                    + "semKRS_2322009, NIMMHSKRS_2322009, kodeMKKRS_2322009, nilaiKRS_2322009) "
                    + "VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sqlKRS);
            int rowCount = tblmodel.getRowCount();
            
            // Proses semua baris data yang ada di tabel mata kuliah
            for (int i = 0; i < rowCount; i++) {
                String kodeMK = (String) tblmodel.getValueAt(i, 0);  // Ambil kode mata kuliah dari kolom pertama
                //String namaMK = (String) tblmodel.getValueAt(i, 1);  // Ambil nama mata kuliah dari kolom kedua
                int sks = Integer.parseInt((String) tblmodel.getValueAt(i, 2));            // Ambil SKS mata kuliah dari kolom ketiga

                // Set nilai untuk query KRS
                stmt.setString(1, tglIsiKRS);  // Set tanggal isi KRS
                stmt.setInt(2, sem);   // Set semester KRS
                stmt.setString(3, nim);        // Set NIM mahasiswa
                stmt.setString(4, kodeMK);     // Set kode mata kuliah
                stmt.setInt(5, sks);           // Set SKS sebagai nilai

                // Eksekusi query untuk menyimpan data KRS
                stmt.addBatch();  // Menambahkan query ini ke batch
            }

            // Menjalankan batch untuk menyimpan semua data KRS
            stmt.executeBatch();

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(null, "Data KRS berhasil disimpan");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data KRS: " + e.getMessage());
        } finally {
            // Menutup koneksi dan statement
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
            }
        }
    }
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        simpanKRS();
        tampildataa();
    }//GEN-LAST:event_btnSimpanActionPerformed
    double totalSks = 0;
    public void totalSKS(JTable tabel){
        totalSks = 0;
        int jumlahRecord = tblKRS.getRowCount();
        for (int k = 0; k < jumlahRecord; k++){
            totalSks = totalSks + Double.parseDouble(tabel.getValueAt(k, 2).toString());
        }
        fieldTotalSKS.setText(String.valueOf(totalSks));
    }
    double totalMutu = 0;
    public void totalMUTU(JTable tabel){
        int jumlahRecord = tblKRS.getRowCount();
        for (int l = 0; l < jumlahRecord; l++){
            totalMutu = totalMutu + Double.parseDouble(tabel.getValueAt(l, 6).toString());
        }
        //fieldTotalMutu.setText(String.valueOf(totalMutu));
    }
    public void printPDF(String Laporan, JTable table) throws IOException, IOException {
        // Tentukan lokasi penyimpanan PDF
        String filePath = "C:/Users/3cc/Documents/NetBeansProjects/PDF/"+ Laporan +".pdf"; // Ganti dengan path yang sesuai

        // Membuat PdfWriter untuk menulis ke file PDF
        try {
            // Menulis ke file PDF menggunakan iText
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);

            // Membuat objek Document untuk menambahkan konten ke PDF
            try (Document document = new Document(pdf, PageSize.A4.rotate())) {
                // Menambahkan header
                document.add(new Paragraph(Laporan)
                        .setBold());

                // Menambahkan paragraf pengantar
                document.add(new Paragraph("NIM: " + fdNIM.getText())
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));

                // Menambahkan paragraf pengantar
                document.add(new Paragraph("Nama: " + fdNama.getText())
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));
                
                // Menambahkan paragraf pengantar
                document.add(new Paragraph("Semester: " + fdSemester.getText())
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));
                
                // Menambahkan tabel dari data JTable
                Table pdfTable = new Table(table.getColumnCount()); // Sesuaikan jumlah kolom dengan JTable
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int rowCount = model.getRowCount();

                // Menambahkan header tabel ke PDF
                for (int i = 0; i < table.getColumnCount(); i++) {
                    pdfTable.addCell(new Cell().add(new Paragraph(table.getColumnName(i)))); // Nama kolom
                }

                // Menambahkan data baris tabel ke PDF
                for (int i = 0; i < rowCount; i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        pdfTable.addCell(new Cell().add(new Paragraph(table.getValueAt(i, j).toString()))); // Data tabel
                    }
                }

                // Menambahkan tabel ke dokumen PDF
                document.add(pdfTable);

                /*/ Menambahkan footer atau informasi tambahan
                document.add(new Paragraph("Dokumen ini dihasilkan secara otomatis oleh aplikasi."));
                document.add(new Paragraph("Terima kasih telah menggunakan aplikasi."));*/
                document.add(new Paragraph(" "));
                
                totalSKS(tblKRS);
                // Menambahkan paragraf pengantar
                document.add(new Paragraph("Total SKS: " + totalSks)
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));
                
                /*/ Menambahkan paragraf pengantar
                document.add(new Paragraph("Total Mutu: " + totalMutu)
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));
                
                // Menambahkan paragraf pengantar
                document.add(new Paragraph("IP: " + fieldIP.getText())
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));
                
                // Menambahkan paragraf pengantar
                document.add(new Paragraph("Total Max SKS: " + fieldMaxSKS.getText())
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));
                // Menutup dokumen PDF setelah selesai*/
                document.close();
                System.out.println("PDF berhasil dibuat dan disimpan di: " + filePath);
            } catch (IOException ex) {
                Logger.getLogger(MataKuliah.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException | IOException e) {
        }
    }
    private void btnCetakPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakPDFActionPerformed
        // TODO add your handling code here:
        printPDF("Kartu Rencana Studi " + fdNIM.getText() +" Semester "+ fdSemester.getText(), tblKRS);
    }//GEN-LAST:event_btnCetakPDFActionPerformed

    private void fdNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fdNamaActionPerformed

    private void fieldTotalSKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTotalSKSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTotalSKSActionPerformed

    private void fdSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdSemesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fdSemesterActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntriKRS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EntriKRS().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariMHS;
    private javax.swing.JButton btnCariMK;
    private javax.swing.JButton btnCetakPDF;
    private javax.swing.JButton btnKurang;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    public static javax.swing.JTextField fdKDMK;
    public static javax.swing.JTextField fdMK;
    public static javax.swing.JTextField fdNIM;
    public static javax.swing.JTextField fdNama;
    public static javax.swing.JTextField fdSKS;
    public static javax.swing.JTextField fdSemester;
    private javax.swing.JTextField fieldTotalSKS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAKRS;
    private javax.swing.JTable tblKRS;
    // End of variables declaration//GEN-END:variables
}

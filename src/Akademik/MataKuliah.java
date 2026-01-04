/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Akademik;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;
import com.itextpdf.io.IOException;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 3cc
 */
public class MataKuliah extends javax.swing.JFrame {
public Connection conn;
public Statement cn;
public ResultSet rs;
public PreparedStatement stmt;

    /**
     * Creates new form MataKuliah
     */
    public MataKuliah() {
        initComponents();
        tampil();
        bersih();

    }

    private void bersih() {
        fieldKdMK.setText("");
        fieldMK.setText("");
        fieldSKS.setText("");
        fieldCari.setText("");
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
    
    boolean canEdit[] = new boolean[]{
        false, false, false, false, false, false, false, false};
    
    private void tampil(){
        // Override isCellEditable untuk mengembalikan nilai yang sesuai dari array canEdit
    DefaultTableModel tebelData = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return canEdit[column];  // Menentukan kolom mana yang bisa diedit (false = tidak bisa diedit)
    }
    };
        tebelData.addColumn("Kode");
        tebelData.addColumn("Mata Kuliah");
        tebelData.addColumn("SKS");
        
        try{
            koneksi();
            buttonEdit.setEnabled(false);
            buttonHapus.setEnabled(false);
            String sql = "Select * from matakuliah_2322009";
            rs = cn.executeQuery(sql);
            
            while(rs.next()){
                tebelData.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3)
                });
            }
            jTable1.setModel(tebelData);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan\n" + e);
        }
    }
    public void validasiMATKUL(){
        try {
        koneksi();
        String sql = "Select * from matakuliah_2322009 where kodeMK_2322009='"+fieldKdMK.getText()+"'";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery(sql);
        if (rs.next()){
        fieldKdMK.setText(rs.getString("kodeMK_2322009"));
        fieldMK.setText(rs.getString("namaMK_2322009"));
        fieldSKS.setText(String.valueOf(rs.getInt(3)));

        buttonEdit.setEnabled(true);
        buttonSimpan.setEnabled(false);
        try {
        int kel = JOptionPane.showConfirmDialog(this, "Kode Matakuliah :"
                + fieldKdMK.getText() 
                + "Sudah ada, Mau Di Edit atau di hapus...?",
                "INFORMASI",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(kel==JOptionPane.NO_OPTION){
        bersih();
        buttonEdit.setEnabled(true);
        buttonSimpan.setEnabled(true);
        fieldKdMK.requestFocus();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        } catch (HeadlessException e) {
        }
        }
        conn.close();
       } catch (SQLException e) {}

        }

    public void Cari(){
 String a = fieldCari.getText();
 if (a.equals("")){
 JOptionPane.showMessageDialog(null, "Field Masih kosong..",
         "INFORMASI",JOptionPane.INFORMATION_MESSAGE);
 fieldCari.requestFocus();
 }else{
 DefaultTableModel tblmatkul= new DefaultTableModel();
 tblmatkul.addColumn("Kode Mata Kuliah");
 tblmatkul.addColumn("Mata Kuliah");
 tblmatkul.addColumn("SKS");
 try {
 koneksi();
 /*saya mencoba menghapus kueri setelah or, 
 dan kode blok try berhasil dijalankan, 
 apa kueri dengan or seperti itu tidak dapat digunakan 
 di aplikasi form java netbeans 
 yang terhubung dengan phpmyadmin? 
 
 Gunakan PreparedStatement: 
 Untuk menghindari potensi masalah 
 dan juga masalah keamanan (SQL Injection), 
 disarankan untuk menggunakan PreparedStatement 
 daripada menyisipkan nilai secara langsung dalam query. 
 Anda bisa menggunakan parameter 
 dalam query seperti berikut:*/
 String sql = "SELECT * FROM matakuliah_2322009 WHERE kodeMK_2322009 LIKE ? OR namaMK_2322009 LIKE ?";
stmt = conn.prepareStatement(sql);
String searchTerm = "%" + fieldCari.getText() + "%";
stmt.setString(1, searchTerm);
stmt.setString(2, searchTerm);
rs = stmt.executeQuery();

 while (rs.next())
 {
 tblmatkul.addRow(new Object[]{
 rs.getString(1),
 rs.getString(2),
 rs.getInt(3)
 });
 }
 
 jTable1.setModel(tblmatkul);
 conn.close();
 } catch (SQLException e) {
 JOptionPane.showMessageDialog(null, "Data tidak ditemukan..","INFORMASI",JOptionPane.INFORMATION_MESSAGE);
 }
 }
 }

    
    private void simpan(){
        try{
            koneksi();
            String sql = "insert into matakuliah_2322009 values('"+ fieldKdMK.getText() +"','"+
           fieldMK.getText() +"','"+ Integer.valueOf(fieldSKS.getText()) +"')";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);
            conn.close();
            tampil();
            bersih();
            JOptionPane.showMessageDialog(null,"Data berhasil di simpan");
            }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"Proses penyimpanan gagal" + e);
            }
    }
    
    private void edit(){
        try {
 koneksi();
 String sql = "update matakuliah_2322009 set namaMK_2322009='"+ fieldMK.getText() +"'," +
 "sksMK_2322009='" + fieldSKS.getText() + "'" +
 "where kodeMK_2322009='" + fieldKdMK.getText() + "'";
 stmt = conn.prepareStatement(sql);
 stmt.executeUpdate(sql);
 stmt.close();
 tampil();
 bersih();
 fieldKdMK.setEnabled(true);
 buttonSimpan.setEnabled(true);
 fieldKdMK.requestFocus();
 buttonEdit.setEnabled(false);
 buttonHapus.setEnabled(false);
 conn.close();
 } catch (SQLException e) {
 }
    }
    
    private void hapus(){
                try {
         getToolkit().beep();
         int keluar = JOptionPane.showConfirmDialog(this,
                 "Anda Yakin Ingin Meghapus Ini..?",
                 "PERINGATAN",
                 JOptionPane.YES_NO_OPTION,
                 JOptionPane.WARNING_MESSAGE);
            if(keluar==JOptionPane.YES_OPTION){
         try {
         koneksi();
         String sql = "delete from matakuliah_2322009 where kodeMK_2322009 ='"+ fieldKdMK.getText() +"'";
         stmt = conn.prepareStatement(sql);
         stmt.executeUpdate(sql);
         stmt.close();
         tampil();
         bersih();
         fieldKdMK.setEnabled(true);
         buttonSimpan.setEnabled(true);
         buttonEdit.setEnabled(false);
         buttonHapus.setEnabled(false);
         fieldKdMK.requestFocus();
         conn.close();
         } catch (SQLException e) {
         JOptionPane.showMessageDialog(null,"Deleting failed..");
         }
         }
         } catch (HeadlessException e) {
         }
    }
    
    private void klikTabel(){
        int tabel = jTable1.getSelectedRow();
 String a = jTable1.getValueAt(tabel, 0).toString();
 String b = jTable1.getValueAt(tabel, 1).toString();
 String c = jTable1.getValueAt(tabel, 2).toString();

 fieldKdMK.setText(a);
 fieldMK.setText(b);
 fieldSKS.setText(c);
 fieldKdMK.setEnabled(false);
 buttonSimpan.setEnabled(false);
 buttonEdit.setEnabled(true);
 buttonHapus.setEnabled(true);
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
        fieldKdMK = new javax.swing.JTextField();
        fieldSKS = new javax.swing.JTextField();
        fieldMK = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        fieldCari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        buttonSimpan = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonKeluar = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        fieldKdMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldKdMKActionPerformed(evt);
            }
        });

        jLabel3.setText("Kode Mata Kuliah");

        jLabel4.setText("Nama Mata Kuliah");

        jLabel5.setText("SKS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldKdMK, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldSKS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldMK, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldKdMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldSKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        fieldCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCariActionPerformed(evt);
            }
        });

        jLabel1.setText("INPUT / CETAK DATA MATA KULIAH");

        jLabel2.setText("Cari nama mata kuliah ");

        buttonSimpan.setText("SIMPAN");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        buttonEdit.setText("EDIT");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonHapus.setText("HAPUS");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });

        buttonKeluar.setText("KELUAR");

        btnMenu.setText("MENU");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnCetak.setText("Cetak PDF");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMenu)
                            .addComponent(buttonKeluar)
                            .addComponent(buttonEdit)
                            .addComponent(buttonSimpan)
                            .addComponent(buttonHapus)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCetak)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(buttonSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonKeluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCetak)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldKdMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldKdMKActionPerformed
        // TODO add your handling code here:
        validasiMATKUL();
    }//GEN-LAST:event_fieldKdMKActionPerformed

    private void fieldCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCariActionPerformed
        // TODO add your handling code here:
        Cari();
    }//GEN-LAST:event_fieldCariActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        // TODO add your handling code here:
        simpan();
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        // TODO add your handling code here:
        edit();
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        klikTabel();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
        new Menu().show();
        this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        new Menu().show();
    }//GEN-LAST:event_formWindowClosed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        printPDF(jTable1);
    }//GEN-LAST:event_btnCetakActionPerformed

    /**
     * 
     * @param table
     */
    /*import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

public class CreateAndSavePDF {
    public static void main(String[] args) {
        // Tentukan lokasi penyimpanan PDF
        String filePath = "C:/path/to/your/folder/contoh_itext7.pdf"; // Ganti dengan path yang sesuai

        // Membuat PdfWriter untuk menulis ke file PDF
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);

            // Membuat objek Document untuk menambahkan konten
            Document document = new Document(pdf);

            // Menambahkan konten ke dalam PDF
            document.add(new Paragraph("Selamat datang di PDF menggunakan iText 7!"));
            document.add(new Paragraph("Dokumen ini dibuat dengan menggunakan pustaka iText 7."));

            // Menutup dokumen setelah selesai
            document.close();

            System.out.println("PDF berhasil dibuat dan disimpan di: " + filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


    
    import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.Font;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
*/
// Method untuk mencetak PDF
    public void printPDF(JTable table) throws IOException, IOException {
        // Tentukan lokasi penyimpanan PDF
        String filePath = "C:/Users/3cc/Documents/NetBeansProjects/PDF/Laporan Mata Kuliah.pdf"; // Ganti dengan path yang sesuai

        // Membuat PdfWriter untuk menulis ke file PDF
        try {
            // Menulis ke file PDF menggunakan iText
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);

            // Membuat objek Document untuk menambahkan konten ke PDF
            try (Document document = new Document(pdf)) {
                // Menambahkan header
                document.add(new Paragraph("Laporan Mata Kuliah")
                        .setBold());

                /*/ Menambahkan paragraf pengantar
                document.add(new Paragraph("Dokumen ini berisi data yang diambil dari tabel berikut.")
                        .setFontSize(12));*/

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

                // Menutup dokumen PDF setelah selesai
                document.close();
                System.out.println("PDF berhasil dibuat dan disimpan di: " + filePath);
            } catch (IOException ex) {
                Logger.getLogger(MataKuliah.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException | IOException e) {
        }
    }
 
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
            java.util.logging.Logger.getLogger(MataKuliah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MataKuliah().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonKeluar;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JTextField fieldCari;
    private javax.swing.JTextField fieldKdMK;
    private javax.swing.JTextField fieldMK;
    private javax.swing.JTextField fieldSKS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

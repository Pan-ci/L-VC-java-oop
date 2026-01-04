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
public class CetakLaporanNilai extends javax.swing.JFrame {
    public Connection conn;
    public Statement cn;
    public ResultSet rs;
    public PreparedStatement stmt;
    /**
     * Creates new form CariMK
     */
    public CetakLaporanNilai() {
        initComponents();
        tampildataa();
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
        tabelnyo.addColumn("NIM");
        tabelnyo.addColumn("Nama Mahasiswa");
        tabelnyo.addColumn("Semester");
        tabelnyo.addColumn("Mata Kuliah");
        tabelnyo.addColumn("SKS");
        tabelnyo.addColumn("Nilai Angka");
        tabelnyo.addColumn("Nilai Huruf");
        try{
        koneksi();
        String sql = "Select NIMMHSLHS_2322009, namaMHS_2322009,"
                + "semLHS_2322009, namaMK_2322009,"
                + "SKSMKLHS_2322009, NAngkaLHS_2322009,"
                + "NHurufLHS_2322009 from lhs_2322009 "
                + "join mahasiswa_2322009 on NIMMHSLHS_2322009 = NIMMHS_2322009 "
                + "join matakuliah_2322009 on KDMKLHS_2322009 = kodeMK_2322009";
                //+ " where NIMMHS_2322009 ='"+fdCariMK.getText()+"' or namaMHS_2322009='"+fdCariMK.getText()+"'";
        rs = cn.executeQuery(sql);
        while(rs.next())
        {
        tabelnyo.addRow(new Object[]{
        rs.getString(1),
        rs.getString(2),
        rs.getInt(3),
        rs.getString(4),
        rs.getInt(5),
        rs.getInt(6),
        rs.getString(7)
        });
        } 
        tblCariMK.setModel(tabelnyo);
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Ada Kesalahan");
        }
        }
    public void tampildata(){
        DefaultTableModel tabelnyo = new DefaultTableModel();
        tabelnyo.addColumn("NIM");
        tabelnyo.addColumn("Nama Mahasiswa");
        tabelnyo.addColumn("Semester");
        tabelnyo.addColumn("Mata Kuliah");
        tabelnyo.addColumn("SKS");
        tabelnyo.addColumn("Nilai Angka");
        tabelnyo.addColumn("Nilai Huruf");
        try{
        koneksi();
        String sql = "Select NIMMHSLHS_2322009, namaMHS_2322009,"
                + "semLHS_2322009, namaMK_2322009,"
                + "SKSMKLHS_2322009, NAngkaLHS_2322009,"
                + "NHurufLHS_2322009 from lhs_2322009 "
                + "join mahasiswa_2322009 on NIMMHSLHS_2322009 = NIMMHS_2322009 "
                + "join matakuliah_2322009 on KDMKLHS_2322009 = kodeMK_2322009"
                + " where NIMMHS_2322009 ='"+fdCariMK.getText()+"' or namaMHS_2322009='"+fdCariMK.getText()+"'"
                + " or semLHS_2322009='"+fdCariMK.getText()+"' or namaMK_2322009='"+fdCariMK.getText()+"'";
        rs = cn.executeQuery(sql);
        while(rs.next())
        {
        tabelnyo.addRow(new Object[]{
        rs.getString(1),
        rs.getString(2),
        rs.getInt(3),
        rs.getString(4),
        rs.getInt(5),
        rs.getInt(6),
        rs.getString(7)
        });
        }
        tblCariMK.setModel(tabelnyo);
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Ada Kesalahan");
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
        jLabel1 = new javax.swing.JLabel();
        fdCariMK = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnCetakPDF = new javax.swing.JButton();
        btnTSData = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCariMK = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Cari NIM / Nama Mahasiswa / Semester / Mata Kuliah");

        fdCariMK.setName("Cari Mata Kuliah"); // NOI18N
        fdCariMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdCariMKActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnCetakPDF.setText("Cetak PDF");
        btnCetakPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakPDFActionPerformed(evt);
            }
        });

        btnTSData.setText("Tampil Seluruh Data");
        btnTSData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTSDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fdCariMK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCetakPDF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTSData)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fdCariMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(btnCetakPDF)
                    .addComponent(btnTSData))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        tblCariMK.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCariMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCariMKMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCariMK);

        jLabel2.setText("LAPORAN NILAI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*private void fdCariMKActionPerformed1() {
    // TODO add your handling code here:
        DefaultTableModel tabelnyo = new DefaultTableModel();
        tabelnyo.addColumn("NIM");
        tabelnyo.addColumn("NAMA MAHASISWA");
        tabelnyo.addColumn("SEMESTER");
        try{
        koneksi();
        String sql = "Select * from mahasiswa_2322009 where NIMMHS_2322009 ='" + fdCariMK.getText() + "' or " +
        "namaMHS_2322009 like '%" + fdCariMK.getText() + "%'";
        rs = cn.executeQuery(sql);
        while (rs.next()) {
        tabelnyo.addRow(new Object[]{
        rs.getString(1),
        rs.getString(2),
        rs.getInt(11)
        });
        }
        tblCariMK.setModel(tabelnyo);
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null,"Terjadi Kesalahan " + e);
        }
        }*/
    
    private void fdCariMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdCariMKActionPerformed
        // TODO add your handling code here:
        //fdCariMKActionPerformed1();
        tampildata();
    }//GEN-LAST:event_fdCariMKActionPerformed

    private void tblCariMKMouseClicked1() {
        // TODO add your handling code here:
        int row;
        row=tblCariMK.getSelectedRow();
        if(EntriKRS.fdNIM != null || EntriKRS.fdNama != null || EntriKRS.fdSemester != null){
            EntriKRS.fdNIM.setText(tblCariMK.getValueAt(row, 0).toString());
            EntriKRS.fdNama.setText(tblCariMK.getValueAt(row, 1).toString());
            EntriKRS.fdSemester.setText(tblCariMK.getValueAt(row, 2).toString());
        } else if (LHS.fieldNIM != null || LHS.fieldNama != null || LHS.fieldSemester != null) {
            LHS.fieldNIM.setText(tblCariMK.getValueAt(row, 0).toString());
            LHS.fieldNama.setText(tblCariMK.getValueAt(row, 1).toString());
            LHS.fieldSemester.setText(tblCariMK.getValueAt(row, 2).toString());
        }
        this.dispose();
        }
    
    private void tblCariMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCariMKMouseClicked
        // TODO add your handling code here:
        tblCariMKMouseClicked1();
    }//GEN-LAST:event_tblCariMKMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        tampildata();
    }//GEN-LAST:event_btnCariActionPerformed

    public void printPDF(String Laporan, JTable table) throws IOException, IOException {
        // Tentukan lokasi penyimpanan PDF
        String filePath = "C:/Users/3cc/Documents/NetBeansProjects/PDF/"+ Laporan +".pdf"; // Ganti dengan path yang sesuai

        // Membuat PdfWriter untuk menulis ke file PDF
        try {
            // Menulis ke file PDF menggunakan iText
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);

            // Membuat objek Document untuk menambahkan konten ke PDF, A4 Landscape
            try (Document document = new Document(pdf, PageSize.A4.rotate())) {
                // Menambahkan header
                document.add(new Paragraph(Laporan)
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
    
    // sesuai nama variabel button cetak pdf
    private void btnCetakPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakPDFActionPerformed
        // TODO add your handling code here:
        // parameter pertama judul laporan
        // parameter kedua, tabel yang mau dijadikan pdf
        printPDF("Laporan Nilai", tblCariMK);
    }//GEN-LAST:event_btnCetakPDFActionPerformed

    void eventBuka(){
        JOptionPane.showMessageDialog(null,
                """
                Anda bisa menentukan data yang akan disimpan di file PDF.
                Langsung Cetak PDF untuk menyimpan PDF berisi seluruh data. 
                Input di kolom pencarian untuk mencari salah satu dari
                NIM Mahasiswa, Nama Mahasiswa, Semester dan Mata Kuliah.
                Jangan input lebih dari satu jenis pencarian!
                """, 
        "Membuka Jendela", JOptionPane.INFORMATION_MESSAGE);
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        eventBuka();
    }//GEN-LAST:event_formWindowOpened

    private void btnTSDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTSDataActionPerformed
        // TODO add your handling code here:
        tampildataa();
    }//GEN-LAST:event_btnTSDataActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(CetakLaporanNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CetakLaporanNilai().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCetakPDF;
    private javax.swing.JButton btnTSData;
    private javax.swing.JTextField fdCariMK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCariMK;
    // End of variables declaration//GEN-END:variables
}

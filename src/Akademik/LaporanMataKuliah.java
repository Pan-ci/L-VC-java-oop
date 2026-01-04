/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Akademik;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 3cc
 */
public class LaporanMataKuliah {
    public Connection conn;
public Statement cn;
public ResultSet rs;
public PreparedStatement stmt;
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
    private javax.swing.table.DefaultTableModel getDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel (
                new Object[][]{},
                new String[]{
                    "Kode","Mata Kuliah","SKS"
                }
        );/*{
            boolean canEdit[] = new boolean[]{false, false, false, false};
            public boolean isCellEditable(int rowInt, int colInt){
                return canEdit[colInt];
            }
        };*/
                }
    private final javax.swing.table.DefaultTableModel model = getDefaultTableModel();
    
    public void printPDF() throws IOException, IOException {
        try{
            koneksi();
            //buttonEdit.setEnabled(false);
            //buttonHapus.setEnabled(false);
            String sql = "Select * from matakuliah_2322009";
            rs = cn.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getString("kodeMK_2322009"),
                    rs.getString("namaMK_2322009"),
                    rs.getInt("sksMK_2322009")
                });
            }
            //jTable1.setModel(tebelData);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan\n" + e);
        }
        String file = "Laporan Mata Kuliah";
        // Tentukan lokasi penyimpanan PDF
        String filePath = "C:/Users/3cc/Documents/NetBeansProjects/PDF/" + file + ".pdf"; // Ganti dengan path yang sesuai

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
                Table pdfTable = new Table(model.getColumnCount()); // Sesuaikan jumlah kolom dengan JTable
                //DefaultTableModel model = (DefaultTableModel) table.getModel();
                int rowCount = model.getRowCount();

                // Menambahkan header tabel ke PDF
                for (int i = 0; i < model.getColumnCount(); i++) {
                    pdfTable.addCell(new Cell().add(new Paragraph(model.getColumnName(i)))); // Nama kolom
                }

                // Menambahkan data baris tabel ke PDF
                for (int i = 0; i < rowCount; i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        pdfTable.addCell(new Cell().add(new Paragraph(model.getValueAt(i, j).toString()))); // Data tabel
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
                JOptionPane.showMessageDialog(null,
                """
                Berhasil menyimpan PDF seluruh data Laporan Mata Kuliah!
                
                Nama File:
                """ + file + "\n\n" +
                "Lokasi Penyimpanan:\n" + filePath + "\n\n" +
                "Jika ingin menyimpan PDF Laporan Mata Kuliah tertentu saja,\n" +
                "Silahkan membuka jendela Mata Kuliah!\n", 
                "PDF Berhasil Disimpan", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(MataKuliah.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException | IOException e) {
        }
    }
    
    // Main method untuk menjalankan program
    public static void main(String[] args) {
        // Membuat objek dari LaporanMataKuliah dan memanggil method printPDF
        LaporanMataKuliah laporan = new LaporanMataKuliah();

        // Misalkan DefaultTableModel model sudah ada dan terisi dengan data
        //DefaultTableModel model = laporan.getDefaultTableModel();
        
        // Menampilkan PDF berdasarkan model yang telah disiapkan
        try {
            laporan.printPDF();
        } catch (IOException e) {
        }
    }
}

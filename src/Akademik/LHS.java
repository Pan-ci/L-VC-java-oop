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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 3cc
 */
public class LHS extends javax.swing.JFrame {
    public Connection conn;
public Statement cn;
public ResultSet rs;
public PreparedStatement stmt;
    /**
     * Creates new form LHS
     */
    public LHS() {
        initComponents();
        //tampil();
        tabelLHS.setModel(tblmodel);
    }

    //String data[] = new String [8];
    //int baris = 0;
    private final javax.swing.table.DefaultTableModel tblmodel=getDefaultTableModel();
    private javax.swing.table.DefaultTableModel getDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel (
                new Object[][]{},
                new String[]{
                    "Kode","Mata kuliah","SKS","Nilai Angka",
                    "Nilai Huruf","Bobot","Mutu"
                }
        ){
            boolean canEdit[] = new boolean[]{false, false, false,
                false, false, false, false
            };
            @Override
            public boolean isCellEditable(int rowInt, int colInt){
                return canEdit[colInt];
            }
        };
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
    /*
    private void bersih() {
        fieldNama.setText("");
        fieldNIM.setText("");
        fieldSKS.setText("");
        fieldSemester.setText("");
        fieldMataKuliah.setText("");
        fieldKode.setText("");
    }
    
    boolean canEdit[] = new boolean[]{
        false, false, false, false, false, false, false, false};
    /*
    private void tampil(){
        // Override isCellEditable untuk mengembalikan nilai yang sesuai dari array canEdit
    DefaultTableModel tebelData = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return canEdit[column];  // Menentukan kolom mana yang bisa diedit (false = tidak bisa diedit)
    }
    };
        tebelData.addColumn("Kode Mata Kuliah");
        tebelData.addColumn("Mata Kuliah");
        tebelData.addColumn("SKS");
        tebelData.addColumn("Nilai Angka");
        tebelData.addColumn("Nilai Huruf");
        tebelData.addColumn("Bobot");
        tebelData.addColumn("Mutu");
        
        try{
            koneksi();
            /*buttonUpdate.setEnabled(false);
            buttonHapus.setEnabled(false);
            String sql = "Select * from lhs_2322009";
            rs = cn.executeQuery(sql);
            
            while(rs.next()){
                tebelData.addRow(new Object[]{
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getInt(9),
                });
            }
            tabelLHS.setModel(tebelData);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan\n" + e);
        }
    }
*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCariMHS = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        fieldTahun = new javax.swing.JTextField();
        fieldSemester = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        fieldNIM = new javax.swing.JTextField();
        fieldNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelLHS = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnCetakPDF = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        fieldMaxSKS = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        fieldTotalSKS = new javax.swing.JTextField();
        fieldTotalMutu = new javax.swing.JTextField();
        fieldIP = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCariMHS.setText("Cari Mahasiswa");
        btnCariMHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMHSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCariMHS)
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCariMHS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Tahun Akademik");

        jLabel3.setText("Semester");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fieldNIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNIMActionPerformed(evt);
            }
        });

        jLabel4.setText("N I M");

        jLabel5.setText("Nama");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(fieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(fieldNIM, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldNIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelLHS.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelLHS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelLHSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelLHS);

        btnCetakPDF.setText("Cetak PDF");
        btnCetakPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCetakPDF)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCetakPDF)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fieldMaxSKS.setEditable(false);
        fieldMaxSKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldMaxSKSActionPerformed(evt);
            }
        });

        jLabel14.setText("Maksimal SKS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(fieldMaxSKS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldMaxSKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fieldTotalSKS.setEditable(false);
        fieldTotalSKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTotalSKSActionPerformed(evt);
            }
        });

        fieldTotalMutu.setEditable(false);
        fieldTotalMutu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTotalMutuActionPerformed(evt);
            }
        });

        fieldIP.setEditable(false);

        jLabel15.setText("Total SKS");

        jLabel16.setText("Total Mutu");

        jLabel17.setText("IP");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldTotalSKS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(fieldTotalMutu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldIP, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldTotalSKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldTotalMutu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)))
        );

        jLabel1.setText("LAPORAN HASIL STUDI / CETAK LHS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 97, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(260, 260, 260))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldMaxSKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldMaxSKSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldMaxSKSActionPerformed

    private void fieldTotalMutuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTotalMutuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTotalMutuActionPerformed

    public void totalSKS(DefaultTableModel tabel){
        double totalSks = 0;
        int jumlahRecord = tabelLHS.getRowCount();
        for (int k = 0; k < jumlahRecord; k++){
            totalSks = totalSks + Double.parseDouble(tabel.getValueAt(k, 2).toString());
        }
        fieldTotalSKS.setText(String.valueOf(totalSks));
    }
    
    public void totalMUTU(DefaultTableModel tabel){
        double totalMutu = 0;
        int jumlahRecord = tabelLHS.getRowCount();
        for (int l = 0; l < jumlahRecord; l++){
            totalMutu = totalMutu + Double.parseDouble(tabel.getValueAt(l, 6).toString());
        }
        fieldTotalMutu.setText(String.valueOf(totalMutu));
    }
    
    public void hitungIP() {
    try {
        // Mengganti koma dengan titik jika ada
        String totalSKsText = fieldTotalSKS.getText().replace(",", ".");
        String totalMutuText = fieldTotalMutu.getText().replace(",", ".");

        float totalSKs = Float.parseFloat(totalSKsText);
        float totalMUtu = Float.parseFloat(totalMutuText);
        float IP;
        IP = totalMUtu / totalSKs;

        // Format IP untuk menampilkan hanya 2 angka setelah koma
        String formattedIP = String.format("%.2f", IP);

        // Set hasil ke fieldIP dengan 2 angka setelah koma
        fieldIP.setText(formattedIP);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Input nilai SKS atau Mutu tidak valid!", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}

public void maxSKS() {
    try {
        // Mengganti koma dengan titik jika ada sebelum parsing
        String ipText = fieldIP.getText().replace(",", ".");
        float Ip = Float.parseFloat(ipText);

        String maxSKS;
        if (Ip >= 3)
            maxSKS = "24 SKS";
        else if (Ip >= 2.5)
            maxSKS = "22 SKS";
        else if (Ip >= 2)
            maxSKS = "22 SKS";
        else if (Ip >= 1.5)
            maxSKS = "22 SKS";
        else if (Ip > 1.5 && Ip > 0)
            maxSKS = "22 SKS";
        else
            maxSKS = "Input salah!";

        // Set nilai maxSKS ke fieldMaxSKS
        fieldMaxSKS.setText(maxSKS);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Input IP tidak valid!", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}


    
    /*private void simpanLHS(){
        //String nama = fdNama.getText();
        String nim = fieldNIM.getText();
        int sem = Integer.parseInt(fieldSemester.getText());
        //String tglIsiKRS = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()); // Mengambil tanggal hari ini
        
        try{
            koneksi();
            String sqlKRS = "INSERT INTO lhs_2322009 (semLHS_2322009, "
                    + "NIMMHSLHS_2322009, KDMKLHS_2322009, SKSMKLHS_2322009, NAngkaLHS_2322009,"
                    + "NHurufLHS_2322009, bobotMKLHS_2322009, mutuMKLHS_2322009) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sqlKRS);
            int rowCount = tabelLHS.getRowCount();
            
            // Proses semua baris data yang ada di tabel mata kuliah
            for (int i = 0; i < rowCount; i++) {
                String kodeMK = (String) tabelLHS.getValueAt(i, 0);  // Ambil kode mata kuliah dari kolom pertama
                //String namaMK = (String) tblmodel.getValueAt(i, 1);  // Ambil nama mata kuliah dari kolom kedua
                int sks = Integer.parseInt((String) tabelLHS.getValueAt(i, 2));            // Ambil SKS mata kuliah dari kolom ketiga
                int nAngka = Integer.parseInt((String) tabelLHS.getValueAt(i, 3));
                String nHuruf = (String) tabelLHS.getValueAt(i, 4);
                int bMK = Integer.parseInt((String) tabelLHS.getValueAt(i, 5));
                int mMK = Integer.parseInt((String) tabelLHS.getValueAt(i, 6));
                // Set nilai untuk query KRS
                //stmt.setString(1, tglIsiKRS);  // Set tanggal isi KRS
                stmt.setInt(1, sem);   // Set semester KRS
                stmt.setString(2, nim);        // Set NIM mahasiswa
                stmt.setString(3, kodeMK);     // Set kode mata kuliah
                stmt.setInt(4, sks);
                stmt.setInt(5, nAngka);
                stmt.setString(6, nHuruf);
                stmt.setInt(7, bMK);// Set SKS sebagai nilai
                stmt.setInt(8, mMK);    
                // Eksekusi query untuk menyimpan data KRS
                stmt.addBatch();  // Menambahkan query ini ke batch
            }

            // Menjalankan batch untuk menyimpan semua data KRS
            stmt.executeBatch();
            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(null, "Data LHS berhasil disimpan");
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
    }*/
    
    private void btnCariMHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMHSActionPerformed
        // TODO add your handling code here:
        new CariMHS().show();
    }//GEN-LAST:event_btnCariMHSActionPerformed

    /*private void btnTambahActionPerformed1() {
    // TODO add your handling code here:
        String k = fieldKode.getText();
        if (k.equals("")){
        JOptionPane.showMessageDialog(null, "Kode Matakuliah Belum Diisi");
        }
        else{
        data[0]= fieldKode.getText();
        data[1]= fieldMataKuliah.getText();
        data[2]= fieldSKS.getText();
        data[3]= fieldAngka.getText();
        data[4]= fieldHuruf.getText();
        data[5]= fieldBobot.getText();
        data[6]= fieldMutu.getText();
        tabelLHS.setModel(tblmodel);
        tblmodel.insertRow(baris, data);
        fieldKode.setText("");
        fieldMataKuliah.setText("");
        fieldSKS.setText("");
        fieldAngka.setText("");
        fieldHuruf.setText("");
        fieldBobot.setText("");
        fieldMutu.setText("");
        totalSKS(tblmodel);
            totalMUTU(tblmodel);
            hitungIP();
            maxSKS();
        }
        }
    private void btnKurangActionPerformed1() {
        // TODO add your handling code here:
            tblmodel.removeRow(baris);
            fieldKode.setText("");
        fieldMataKuliah.setText("");
        fieldSKS.setText("");
        fieldAngka.setText("");
        fieldHuruf.setText("");
        fieldBobot.setText("");
        fieldMutu.setText("");
            }*/
    
    private void tblKRSMouseClicked1() {
    /*/ TODO add your handling code here:
        baris = tabelLHS.getSelectedRow();
        fieldKode.setText(tblmodel.getValueAt(baris, 0).toString());
        fieldMataKuliah.setText(tblmodel.getValueAt(baris, 1).toString());
        fieldSKS.setText(tblmodel.getValueAt(baris, 2).toString());
        fieldAngka.setText(tblmodel.getValueAt(baris, 3).toString());
        fieldHuruf.setText(tblmodel.getValueAt(baris, 4).toString());
        fieldBobot.setText(tblmodel.getValueAt(baris, 5).toString());
        fieldMutu.setText(tblmodel.getValueAt(baris, 6).toString());
        */}
    private void tabelLHSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelLHSMouseClicked
        // TODO add your handling code here:
        tblKRSMouseClicked1();
    }//GEN-LAST:event_tabelLHSMouseClicked

    /*"Kode","Mata kuliah","SKS","Nilai Angka",
                    "Nilai Huruf","Bobot","Mutu"*/
    void ceklhs(){
        DefaultTableModel tabelnyo = new DefaultTableModel();
        tabelnyo.addColumn("Kode");
        tabelnyo.addColumn("Nama Matakuliah");
        tabelnyo.addColumn("SKS");
        tabelnyo.addColumn("Nilai Angka");
        tabelnyo.addColumn("Nilai Huruf");
        tabelnyo.addColumn("Bobot");
        tabelnyo.addColumn("Mutu");
        try{
        koneksi();
        String sql = "SELECT KDMKLHS_2322009, namaMK_2322009,"
        + "SKSMKLHS_2322009, NAngkaLHS_2322009, NHurufLHS_2322009,"
                + "bobotMKLHS_2322009, mutuMKLHS_2322009 FROM lhs_2322009 JOIN matakuliah_2322009 ON KDMKLHS_2322009=kodeMK_2322009"
        + " WHERE semLHS_2322009='"+ fieldSemester.getText() +"' AND NIMMHSLHS_2322009='"+fieldNIM.getText() +"'";
        rs = cn.executeQuery(sql);
        while (rs.next()) {
        tabelnyo.addRow(new Object[]{
        rs.getString(1),
        rs.getString(2),
        rs.getInt(3),
        rs.getInt(4),
        rs.getString(5),
        rs.getInt(6),
        rs.getInt(7)
        });
        }
        tabelLHS.setModel(tabelnyo);
        totalSKS(tabelnyo);
            totalMUTU(tabelnyo);
            hitungIP();
            maxSKS();
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null,"Kesalahan Menampilkan Data"+ e);
        }
        }
    private void fieldNIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNIMActionPerformed
        // TODO add your handling code here:
        ceklhs();
    }//GEN-LAST:event_fieldNIMActionPerformed

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
                document.add(new Paragraph("NIM: " + fieldNIM.getText())
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));

                // Menambahkan paragraf pengantar
                document.add(new Paragraph("Nama: " + fieldNama.getText())
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));
                
                // Menambahkan paragraf pengantar
                document.add(new Paragraph("Semester: " + fieldSemester.getText())
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
                // Menambahkan paragraf pengantar
                document.add(new Paragraph("Total SKS: " + fieldTotalSKS.getText())
                        .setFontSize(12));

                // Menambahkan paragraf kosong untuk spasi
                document.add(new Paragraph(" "));
                
                // Menambahkan paragraf pengantar
                document.add(new Paragraph("Total Mutu: " + fieldTotalMutu.getText())
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
                // Menutup dokumen PDF setelah selesai
                document.close();
                System.out.println("PDF berhasil dibuat dan disimpan di: " + filePath);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException | IOException e) {
        }
    }
    private void btnCetakPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakPDFActionPerformed
         // TODO add your handling code here:
        printPDF("Laporan Hasil Studi "+ fieldNIM.getText() + " Semester " + fieldSemester.getText(), tabelLHS);
    }//GEN-LAST:event_btnCetakPDFActionPerformed

    private void fieldTotalSKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTotalSKSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTotalSKSActionPerformed

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
            java.util.logging.Logger.getLogger(LHS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LHS().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariMHS;
    private javax.swing.JButton btnCetakPDF;
    private javax.swing.JTextField fieldIP;
    private javax.swing.JTextField fieldMaxSKS;
    public static javax.swing.JTextField fieldNIM;
    public static javax.swing.JTextField fieldNama;
    public static javax.swing.JTextField fieldSemester;
    private javax.swing.JTextField fieldTahun;
    private javax.swing.JTextField fieldTotalMutu;
    private javax.swing.JTextField fieldTotalSKS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelLHS;
    // End of variables declaration//GEN-END:variables
}

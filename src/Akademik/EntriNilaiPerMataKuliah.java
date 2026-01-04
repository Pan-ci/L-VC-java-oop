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
public class EntriNilaiPerMataKuliah extends javax.swing.JFrame {
    public Connection conn;
public Statement cn;
public ResultSet rs;
public PreparedStatement stmt;
    /**
     * Creates new form LHS
     */
    public EntriNilaiPerMataKuliah() {
        initComponents();
        //tampil();
        tabelLHS.setModel(tblmodel);
    }

    String data[] = new String [8];
    int baris = 0;
    private final javax.swing.table.DefaultTableModel tblmodel=getDefaultTableModel();
    private final javax.swing.table.DefaultTableModel tblModel=getDefaultTableModel();
    private javax.swing.table.DefaultTableModel getDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel (
                new Object[][]{},
                new String[]{
                    "NIM","Nama","Semester","Nilai Angka",
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
    
    private void bersih() {
        //DefaultTableModel model = new DefaultTableModel();
        fieldTahun.setText("");
        fieldNama.setText("");
        fieldNIM.setText("");
        fieldSKS.setText("");
        fieldSemester.setText("");
        fieldMataKuliah.setText("");
        fieldKode.setText("");
        fieldAngka.setText("");
        fieldHuruf.setText("");
        fieldBobot.setText("");
        fieldMutu.setText("");
        /*tabelLHS.setModel(model);
        totalSKS(model);
        totalMUTU(model);
        hitungIP();
        maxSKS();*/
    }
    /*
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
        btnCariMK = new javax.swing.JButton();
        btnEntriNiliaiPerMHS = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        fieldTahun = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fieldSKS = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        fieldKode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fieldMataKuliah = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        fieldBobot = new javax.swing.JTextField();
        fieldMutu = new javax.swing.JTextField();
        fieldAngka = new javax.swing.JTextField();
        fieldHuruf = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnKurang = new javax.swing.JButton();
        fieldNIM = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fieldNama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fieldSemester = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelLHS = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        buttonSimpan = new javax.swing.JButton();
        btnCetakPDF = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCariMHS.setText("Cari Mahasiswa");
        btnCariMHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMHSActionPerformed(evt);
            }
        });

        btnCariMK.setText("Cari Mata Kuliah");
        btnCariMK.setName(""); // NOI18N
        btnCariMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMKActionPerformed(evt);
            }
        });

        btnEntriNiliaiPerMHS.setText("Entri Per Mahasiswa");
        btnEntriNiliaiPerMHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntriNiliaiPerMHSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCariMK)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCariMHS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEntriNiliaiPerMHS)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCariMK)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCariMHS)
                    .addComponent(btnEntriNiliaiPerMHS))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel2.setText("Tahun Akademik");

        jLabel9.setText("SKS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(fieldTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldSKS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fieldSKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Kode");

        fieldKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldKodeActionPerformed(evt);
            }
        });

        jLabel7.setText("Mata Kuliah");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldKode, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldMataKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fieldMataKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        fieldBobot.setEditable(false);

        fieldMutu.setEditable(false);

        fieldAngka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldAngkaActionPerformed(evt);
            }
        });

        fieldHuruf.setEditable(false);

        jLabel8.setText("Nilai Angka");

        jLabel10.setText("Nilai Huruf");

        jLabel11.setText("Bobot");

        jLabel12.setText("Mutu");

        btnTambah.setText("+");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnKurang.setText("-");
        btnKurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKurangActionPerformed(evt);
            }
        });

        fieldNIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNIMActionPerformed(evt);
            }
        });

        jLabel4.setText("N I M");

        jLabel5.setText("Nama");

        jLabel3.setText("Semester");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addComponent(fieldNIM, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(fieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(fieldSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(fieldAngka, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(fieldHuruf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel11))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(fieldBobot, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel12))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(fieldMutu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKurang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldAngka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldHuruf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldBobot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldMutu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah)
                    .addComponent(btnKurang)
                    .addComponent(fieldNIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        buttonSimpan.setText("SIMPAN");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

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
                .addComponent(buttonSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCetakPDF)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSimpan)
                    .addComponent(btnCetakPDF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Entri Nilai Per Mata Kuliah");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(260, 260, 260))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /*
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
    
    public void hitungIP(){
    float totalSKs = 0;
    float totalMutu = 0;
    float IP = 0;
    
    try {
        // Mengecek apakah field tidak kosong atau null
        String sksText = fieldTotalSKS.getText().trim();
        String mutuText = fieldTotalMutu.getText().trim();
        
        // Jika field kosong, set totalSKs dan totalMutu ke 0
        if (!sksText.isEmpty() && !mutuText.isEmpty()) {
            totalSKs = Float.parseFloat(sksText);
            totalMutu = Float.parseFloat(mutuText);
        }
        
        // Jika totalSKs tidak 0, hitung IP
        if (totalSKs > 0) {
            IP = totalMutu / totalSKs;
        }
        
    } catch (NumberFormatException e) {
        // Jika ada kesalahan parsing (misalnya tidak bisa diubah ke angka), set IP ke 0
        IP = 0;
    }
    
    // Set hasil ke fieldIP
    fieldIP.setText(String.valueOf(IP));
}

    
    public void maxSKS(){
        float Ip = Float.parseFloat(fieldIP.getText());
        String maxSKS;
        if(Ip >= 3)
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
            maxSKS = "0 SKS";
        
        fieldMaxSKS.setText(String.valueOf(maxSKS));
    }
    
    /*public void tampilkan(){
        baris = tabelLHS.getSelectedRow();
        fieldKode.setText(tableModel.getValueAt(baris, 0).toString());
        fieldMataKuliah.setText(tableModel.getValueAt(baris, 1).toString());
        fieldSKS.setText(tableModel.getValueAt(baris, 2).toString());
        fieldAngka.setText(tableModel.getValueAt(baris, 3).toString());
        fieldHuruf.setText(tableModel.getValueAt(baris, 4).toString());
        fieldBobot.setText(tableModel.getValueAt(baris, 5).toString());
        fieldMutu.setText(tableModel.getValueAt(baris, 6).toString());
        //fieldKeterangan.setText(tableModel.getValueAt(baris, 7).toString());
    }*/
    /*    */
    private void fieldAngkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAngkaActionPerformed
        // TODO add your handling code here:
        int nilaiAngka = Integer.parseInt(fieldAngka.getText());
        int SKS = Integer.parseInt(fieldSKS.getText());
        String nilaiHuruf;
        int mutu, bobot;
        
        if (nilaiAngka > 80){
            nilaiHuruf = "A";
            bobot = 4;
        } else if (nilaiAngka > 65){
            nilaiHuruf = "B";
            bobot = 3;
        } else if (nilaiAngka > 55){
            nilaiHuruf = "C";
            bobot = 2;
        } else if (nilaiAngka > 45){
            nilaiHuruf = "D";
            bobot = 1;
        } else if (nilaiAngka >= 45 && nilaiAngka > 0){
            nilaiHuruf = "B";
            bobot = 0;
        } else {
            nilaiHuruf = "Input salah";
            bobot = 0;
        }
        
        mutu = SKS * bobot;
        
        fieldHuruf.setText(String.valueOf(nilaiHuruf));
        fieldBobot.setText(String.valueOf(bobot));
        fieldMutu.setText(String.valueOf(mutu));
        //fieldKeterangan.setText(String.valueOf(keterangan));
    }//GEN-LAST:event_fieldAngkaActionPerformed

    private void simpanLHS(){
        //String nama = fdNama.getText();
        //String nim = fieldNIM.getText();
        //int sem = Integer.parseInt(fieldSemester.getText());
        //String tglIsiKRS = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()); // Mengambil tanggal hari ini
        String kd = fieldKode.getText();
        int sks = Integer.parseInt(fieldSKS.getText());
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
                String NIM = (String) tabelLHS.getValueAt(i, 0);  // Ambil kode mata kuliah dari kolom pertama
                //String namaMK = (String) tblmodel.getValueAt(i, 1);  // Ambil nama mata kuliah dari kolom kedua
                int sem = Integer.parseInt((String) tabelLHS.getValueAt(i, 2));            // Ambil SKS mata kuliah dari kolom ketiga
                int nAngka = Integer.parseInt((String) tabelLHS.getValueAt(i, 3));
                String nHuruf = (String) tabelLHS.getValueAt(i, 4);
                int bMK = Integer.parseInt((String) tabelLHS.getValueAt(i, 5));
                int mMK = Integer.parseInt((String) tabelLHS.getValueAt(i, 6));
                // Set nilai untuk query KRS
                //stmt.setString(1, tglIsiKRS);  // Set tanggal isi KRS
                stmt.setInt(1, sem);   // Set semester KRS
                stmt.setString(2, NIM);        // Set NIM mahasiswa
                stmt.setString(3, kd);     // Set kode mata kuliah
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
            
            tabelLHS.setModel(tblModel);
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
    }
    
    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        simpanLHS();
        bersih();
        btnCariMHS.setEnabled(true);
        /*/ TODO add your handling code here:
        data[0] = fieldKode.getText();
        data[1] = fieldMataKuliah.getText();
        data[2] = fieldSKS.getText();
        data[3] = fieldAngka.getText();
        data[4] = fieldHuruf.getText();
        data[5] = fieldBobot.getText();
        data[6] = fieldMutu.getText();
        data[7] = fieldKeterangan.getText();
        
        tableModel.insertRow(baris, data);*/
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void btnCariMHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMHSActionPerformed
        /*/ TODO add your handling code here:
        tabelLHS.setModel(tblmodel);
        /*totalSKS(tblmodel);
        totalMUTU(tblmodel);
        hitungIP();
        maxSKS();*/
        btnCariMK.setEnabled(true);
        btnTambah.setEnabled(true);
        btnKurang.setEnabled(true);
        buttonSimpan.setEnabled(true);
        new CariMHS().show();
    }//GEN-LAST:event_btnCariMHSActionPerformed

    private void btnCariMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMKActionPerformed
        // TODO add your handling code here:
        //btnCariMHS.setEnabled(false);
        tabelLHS.setModel(tblmodel);
        btnCariMHS.setEnabled(true);
        btnTambah.setEnabled(true);
        btnKurang.setEnabled(true);
        buttonSimpan.setEnabled(true);
        new CariMK().show();
    }//GEN-LAST:event_btnCariMKActionPerformed

    private void btnTambahActionPerformed1() {
    // TODO add your handling code here:
        String k = fieldKode.getText();
        if (k.equals("")){
        JOptionPane.showMessageDialog(null, "Kode Matakuliah Belum Diisi");
        }
        else{
        data[0]= fieldNIM.getText();
        data[1]= fieldNama.getText();
        data[2]= fieldSemester.getText();
        data[3]= fieldAngka.getText();
        data[4]= fieldHuruf.getText();
        data[5]= fieldBobot.getText();
        data[6]= fieldMutu.getText();
        tabelLHS.setModel(tblmodel);
        tblmodel.insertRow(baris, data);
        fieldNIM.setText("");
        fieldNama.setText("");
        fieldSemester.setText("");
        fieldAngka.setText("");
        fieldHuruf.setText("");
        fieldBobot.setText("");
        fieldMutu.setText("");
        /*totalSKS(tblmodel);
        totalMUTU(tblmodel);
        hitungIP();
        maxSKS();*/
        }
        }
    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
         // TODO add your handling code here:
        btnTambahActionPerformed1();
    }//GEN-LAST:event_btnTambahActionPerformed

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
            }
    
    private void tblKRSMouseClicked1() {
    // TODO add your handling code here:
        baris = tabelLHS.getSelectedRow();
        fieldNIM.setText(tblmodel.getValueAt(baris, 0).toString());
        fieldNama.setText(tblmodel.getValueAt(baris, 1).toString());
        fieldSemester.setText(tblmodel.getValueAt(baris, 2).toString());
        fieldAngka.setText(tblmodel.getValueAt(baris, 3).toString());
        fieldHuruf.setText(tblmodel.getValueAt(baris, 4).toString());
        fieldBobot.setText(tblmodel.getValueAt(baris, 5).toString());
        fieldMutu.setText(tblmodel.getValueAt(baris, 6).toString());
        }
    private void btnKurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKurangActionPerformed
        // TODO add your handling code here:
        btnKurangActionPerformed1();
    }//GEN-LAST:event_btnKurangActionPerformed

    private void tabelLHSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelLHSMouseClicked
        // TODO add your handling code here:
        tblKRSMouseClicked1();
    }//GEN-LAST:event_tabelLHSMouseClicked

    /*"Kode","Mata kuliah","SKS","Nilai Angka",
                    "Nilai Huruf","Bobot","Mutu"*/
    void ceklhs(){
        DefaultTableModel tabelnyo = new DefaultTableModel();
        tabelnyo.addColumn("NIM");
        tabelnyo.addColumn("Nama");
        tabelnyo.addColumn("Semester");
        tabelnyo.addColumn("Nilai Angka");
        tabelnyo.addColumn("Nilai Huruf");
        tabelnyo.addColumn("Bobot");
        tabelnyo.addColumn("Mutu");
        try{
        koneksi();
        String sql = "SELECT NIMMHSLHS_2322009, namaMHS_2322009,"
        + "semLHS_2322009, NAngkaLHS_2322009, NHurufLHS_2322009,"
                + "bobotMKLHS_2322009, mutuMKLHS_2322009 FROM lhs_2322009 JOIN mahasiswa_2322009 ON NIMMHSLHS_2322009 = NIMMHS_2322009"
        + " WHERE SKSMKLHS_2322009='"+ fieldSKS.getText() +"' AND KDMKLHS_2322009='"+fieldKode.getText() +"'";
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
        /*totalSKS(tabelnyo);
            totalMUTU(tabelnyo);
            hitungIP();
            maxSKS();*/
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null,"Kesalahan Menampilkan Data"+ e);
        }
        }
    private void fieldNIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNIMActionPerformed
        /*/ TODO add your handling code here:
        ceklhs();
        btnCariMK.setEnabled(false);
        btnTambah.setEnabled(false);
        btnKurang.setEnabled(false);
        buttonSimpan.setEnabled(false);*/
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
                document.add(new Paragraph("Terima kasih telah menggunakan aplikasi."));
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
        printPDF("Laporan Hasil Studi "+ fieldNIM.getText() + " Semester " + fieldSemester.getText(), tabelLHS);
    }//GEN-LAST:event_btnCetakPDFActionPerformed

    private void fieldKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldKodeActionPerformed
        // TODO add your handling code here:
        ceklhs();
        btnCariMHS.setEnabled(false);
        btnTambah.setEnabled(false);
        btnKurang.setEnabled(false);
        buttonSimpan.setEnabled(false);
    }//GEN-LAST:event_fieldKodeActionPerformed

    private void btnEntriNiliaiPerMHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntriNiliaiPerMHSActionPerformed
        // TODO add your handling code here:
        new EntriNilai().show();
        this.dispose();
    }//GEN-LAST:event_btnEntriNiliaiPerMHSActionPerformed

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
            java.util.logging.Logger.getLogger(EntriNilaiPerMataKuliah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EntriNilaiPerMataKuliah().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariMHS;
    private javax.swing.JButton btnCariMK;
    private javax.swing.JButton btnCetakPDF;
    private javax.swing.JButton btnEntriNiliaiPerMHS;
    private javax.swing.JButton btnKurang;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JTextField fieldAngka;
    private javax.swing.JTextField fieldBobot;
    private javax.swing.JTextField fieldHuruf;
    public static javax.swing.JTextField fieldKode;
    public static javax.swing.JTextField fieldMataKuliah;
    private javax.swing.JTextField fieldMutu;
    public static javax.swing.JTextField fieldNIM;
    public static javax.swing.JTextField fieldNama;
    public static javax.swing.JTextField fieldSKS;
    public static javax.swing.JTextField fieldSemester;
    private javax.swing.JTextField fieldTahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelLHS;
    // End of variables declaration//GEN-END:variables
}

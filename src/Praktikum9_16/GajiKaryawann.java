/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Praktikum9_16;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author 3cc
 */
public final class GajiKaryawann extends javax.swing.JFrame {

    public Connection koneksi = null;
    public static Statement kn =  null;
    public static ResultSet rs = null;
    /**
     * Creates new form GajiKaryawann
     */
    public GajiKaryawann() {
        initComponents();
        koneksii();
        tampil();
        
        UIManager.put("OptionPane.okButtonText", "Okeh");
        
        buttonKeluar.addActionListener((ActionEvent e) -> {
            WindowEvent windowEvent = new WindowEvent(GajiKaryawann.this, WindowEvent.WINDOW_CLOSING);
            formWindowClosing(windowEvent);
        });
        
        this.addWindowListener(new WindowAdapter() {
            public void tutupJendela(WindowEvent evt) {
                formWindowClosing(evt);
            }
            
            public void sudahTutup (WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private boolean cekNoSlip(String noSlip){
        boolean duplikat = false;
        
        try{
            String kueri = "select count(*) from gaji where noSlip = ?";
            try (PreparedStatement ns = koneksi.prepareStatement(kueri)) {
                ns.setString(1, noSlip);
                rs = ns.executeQuery();
                
                if(rs.next()){
                    int hitung = rs.getInt(1);
                    if(hitung > 0){
                        duplikat = true;
                    }
                }
            }
        }catch(SQLException e){
            /*JOptionPane.showMessageDialog(null, "Field " + field.getName() + 
                    " tidak boleh kurang dari satu!", 
                    "input tidak valid!", JOptionPane.ERROR_MESSAGE);*/
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memeriksa duplikat:\n"
                            + e.getMessage(), "Masalah Pemeriksaan Duplikat", JOptionPane.ERROR_MESSAGE);
        }
        return duplikat;
    }
    public void koneksii() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/oop3genap","root","");
            kn = koneksi.createStatement();
            System.out.println("Koneksi dan statement berhasil dibuat!");
        }catch(SQLException e){
            /*JOptionPane.showMessageDialog(null, "Field " + field.getName() + 
                    " tidak boleh kurang dari satu!", 
                    "input tidak valid!", JOptionPane.ERROR_MESSAGE);*/
            JOptionPane.showMessageDialog(null, "Kesalahan koneksi atau pembuatan statement\n" + e.getMessage(),
                                            "Masalah Koneksi-Statement", JOptionPane.ERROR_MESSAGE);
            System.exit(0); //seharusnya 1, kalau 0 berarti tidak ada maslah, sedangkan ini ada masalah
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Kesalahan penggunaan driver\n" + e.getMessage(),
                                            "Masalah Driver", JOptionPane.ERROR_MESSAGE);
            System.exit(0); //seharusnya 1, kalau 0 berarti tidak ada maslah, sedangkan ini ada masalah
        }   
    }
    // Array untuk menentukan kolom mana yang tidak bisa diedit
    boolean canEdit[] = new boolean[]{
        false, false, false, false, false, false, false}; // Semua kolom tidak bisa diedit
    
    private void tampil(){
        // Override isCellEditable untuk mengembalikan nilai yang sesuai dari array canEdit
    DefaultTableModel tebelData = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return canEdit[column];  // Menentukan kolom mana yang bisa diedit (false = tidak bisa diedit)
    }
    };
        tebelData.addColumn("No Slip");
        tebelData.addColumn("Nama Karyawan");
        tebelData.addColumn("Gaji Pokok");
        tebelData.addColumn("Tunjangan");
        tebelData.addColumn("Jumlah Lembur");
        tebelData.addColumn("Upah Lembur per Jam");
        tebelData.addColumn("Total Gaji");
        
        try{
            koneksii();
            buttonUpdate.setEnabled(false);
            buttonHapus.setEnabled(false);
            String sql = "Select * from gaji";
            
                rs = kn.executeQuery(sql);
                while(rs.next()){
                tebelData.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                });
            }
                tabel.setModel(tebelData);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan" + e);
        }
    }
     
    private Object[] dariData() {
        return new Object[]{
            fieldNoSlip.getText().trim(),
            fieldNamaKaryawan.getText().trim(),
            fieldGajiPokok.getText().trim(),
            fieldTunjangan.getText().trim(),
            fieldLembur.getText().trim(),
            fieldUpahLembur.getText().trim(),
            fieldTotalGaji.getText().trim()
        };
    }
    
    private void hapusField() {
        fieldNoSlip.setText("");
        fieldNamaKaryawan.setText("");
        fieldGajiPokok.setText("");
        fieldTunjangan.setText("");
        fieldLembur.setText("");
        fieldUpahLembur.setText("");
        fieldTotalGaji.setText("");
        fieldCariNama.setText("");
    }
    
    private boolean validasiField(JTextField field) {
    if (field.getText() == null) {
        JOptionPane.showMessageDialog(null, "Field " + field.getName() + " tidak boleh kosong!", 
                "Masalah Input", JOptionPane.ERROR_MESSAGE);
        return false;  // Jika field null, langsung kembalikan false
    }
    
    // Cek apakah field kosong atau hanya berisi spasi
    if (field.getText().trim().isBlank()) {
        JOptionPane.showMessageDialog(null, "Field " + field.getName() + " tidak boleh kosong!", 
                "Masalah Input", JOptionPane.ERROR_MESSAGE);
        return false;  // Jika kosong, kembalikan false
    }
    
    return true;  // Field valid, kembalikan true
}

    
    private boolean validasiInput(JTextField field) {
    // Ambil teks dari field dan hilangkan spasi di kedua ujungnya
    String input = field.getText().trim();

    // Cek jika input kosong
    if (input.isEmpty() || "".equals(input)) {
        JOptionPane.showMessageDialog(null, "Field " + field.getName() + " tidak boleh kosong!",
                "Input tidak valid!", JOptionPane.ERROR_MESSAGE);
        return false;  // Jika kosong, kembalikan false
    }

    // Cek apakah input berupa angka bulat (integer)
    try {
        int angka = Integer.parseInt(input);  // Coba konversi input ke Integer
        // Cek apakah angka negatif
        if (angka < 0) {
            JOptionPane.showMessageDialog(null, "Field " + field.getName() + " tidak boleh negatif!",
                    "Input tidak valid!", JOptionPane.ERROR_MESSAGE);
            return false;  // Jika angka negatif, kembalikan false
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Field " + field.getName() + " harus berupa angka bulat (integer)!",
                "Input tidak valid!", JOptionPane.ERROR_MESSAGE);
        return false;  // Jika gagal konversi, berarti input bukan angka
    }

    return true;  // Input valid
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fieldNoSlip = new javax.swing.JTextField();
        fieldTotalGaji = new javax.swing.JTextField();
        fieldGajiPokok = new javax.swing.JTextField();
        fieldTunjangan = new javax.swing.JTextField();
        fieldLembur = new javax.swing.JTextField();
        fieldUpahLembur = new javax.swing.JTextField();
        fieldCariNama = new javax.swing.JTextField();
        fieldNamaKaryawan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        buttonSimpan = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonKeluar = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gaji Karyawan");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("No Slip");

        jLabel2.setText("Tunjangan");

        jLabel3.setText("Nama Karyawan");

        jLabel4.setText("Gaji Pokok");

        jLabel5.setText("Jumlah Lembur");

        jLabel6.setText("Upah Lembur/Jam");

        jLabel7.setText("Total Gaji");

        jLabel8.setText("Cari Nama");

        fieldNoSlip.setName("Nomor Slip"); // NOI18N

        fieldTotalGaji.setEditable(false);
        fieldTotalGaji.setName("Total Gaji"); // NOI18N
        fieldTotalGaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldTotalGajiMouseClicked(evt);
            }
        });
        fieldTotalGaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTotalGajiActionPerformed(evt);
            }
        });

        fieldGajiPokok.setName("Gaji Pokok"); // NOI18N

        fieldTunjangan.setName("Tunjangan"); // NOI18N

        fieldLembur.setName("Jumlah Lembur"); // NOI18N

        fieldUpahLembur.setName("Upah Lembur Perjam"); // NOI18N
        fieldUpahLembur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldUpahLemburActionPerformed(evt);
            }
        });

        fieldCariNama.setName("Cari Nama"); // NOI18N
        fieldCariNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCariNamaActionPerformed(evt);
            }
        });

        fieldNamaKaryawan.setName("Nama Karyawan"); // NOI18N

        jLabel9.setText("Jam");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldTotalGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldGajiPokok, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldTunjangan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldUpahLembur, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(fieldLembur, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fieldNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldNoSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldNoSlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldGajiPokok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldTunjangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldLembur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldUpahLembur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fieldTotalGaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(fieldCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        buttonSimpan.setText("SIMPAN");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        buttonUpdate.setText("UPDATE");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonHapus.setText("HAPUS");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });

        buttonKeluar.setText("KELUAR");

        buttonReset.setText("RESET");
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSimpan)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonHapus)
                    .addComponent(buttonKeluar)
                    .addComponent(buttonReset))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonKeluar))
        );

        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(389, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void eventHapus(){
        try{
            koneksii();
            String sql = "delete from gaji where noSlip = '"+fieldNoSlip.getText()+"'";
            kn.executeUpdate(sql);
            tampil();
            hapusField();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            buttonSimpan.setEnabled(true);
            koneksi.close();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Kesalahan penghapusan data\n" + e.getMessage(),
                                            "Masalah Koneksi-Statement", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void eventUpah(){
        if (!validasiInput(fieldGajiPokok) || !validasiInput(fieldTunjangan)
                || !validasiInput(fieldLembur) || !validasiInput(fieldUpahLembur)) 
        {return;}
        
        double totalGaji;
        try {
            double gajiPokok = Double.parseDouble(fieldGajiPokok.getText());
            double tunjangan = Double.parseDouble(fieldTunjangan.getText());
            double jumlahLembur = Double.parseDouble(fieldLembur.getText());
            double upahLembur = Double.parseDouble(fieldUpahLembur.getText());

            totalGaji = gajiPokok + tunjangan + (jumlahLembur * upahLembur);
            fieldTotalGaji.setText(String.valueOf(totalGaji));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input tidak valid, pastikan semua kolom berisi angka.",
            "Kesalahan Format Angka", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void eventSimpan(){
        koneksii();
        if (!validasiField(fieldNoSlip) || !validasiField(fieldNamaKaryawan)) {return;}
        if (!validasiInput(fieldGajiPokok) || !validasiInput(fieldTunjangan)
                || !validasiInput(fieldLembur) || !validasiInput(fieldUpahLembur)) 
        {return;}
        
        double totalGaji;
        try {
            double gajiPokok = Double.parseDouble(fieldGajiPokok.getText());
            double tunjangan = Double.parseDouble(fieldTunjangan.getText());
            double jumlahLembur = Double.parseDouble(fieldLembur.getText());
            double upahLembur = Double.parseDouble(fieldUpahLembur.getText());

            totalGaji = gajiPokok + tunjangan + (jumlahLembur * upahLembur);
            fieldTotalGaji.setText(String.valueOf(totalGaji));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input tidak valid, pastikan semua kolom berisi angka.",
            "Kesalahan Format Angka", JOptionPane.ERROR_MESSAGE);
        }
        
        String noSlip = fieldNoSlip.getText();
        Object[] dariDataa = dariData();
    
        // Memeriksa apakah nomor slip sudah ada
        if (cekNoSlip(noSlip)){
        JOptionPane.showMessageDialog(null, "Nomor Slip Gaji sudah ada di database, ganti yang lain" ,
                "Masalah Duplikasi", JOptionPane.ERROR_MESSAGE);
        return;  // keluar jika duplikasi ditemukan
        }
    
        String kueri = "insert into gaji (noSlip, namaKaryawan, gajiPokok,"
            + "tunjangan, jumlahLembur, upahLembur, totalGaji)"
            + " values (?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = koneksi.prepareStatement(kueri)) {
        // Menyiapkan data untuk disimpan
        stmt.setString(1, (String) dariDataa[0]);
        stmt.setString(2, (String) dariDataa[1]);
        //stmt.setInt(3, Integer.parseInt((String) dariDataa[2]));
        stmt.setInt(3, Integer.parseInt((String) dariDataa[2]));
        stmt.setInt(4, Integer.parseInt((String) dariDataa[3]));
        stmt.setInt(5, Integer.parseInt((String) dariDataa[4]));
        stmt.setInt(6, Integer.parseInt((String) dariDataa[5]));
        stmt.setString(7, (String) dariDataa[6]);

        // Eksekusi perintah SQL
        int barisEdit = stmt.executeUpdate();
        if (barisEdit > 0) {
            tampil();
            hapusField();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        }   
    } catch (HeadlessException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Proses penyimpanan gagal\n" + e,
                "Masalah Penyimpanan", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (koneksi != null && !koneksi.isClosed()) {
                koneksi.close();
            }
        } catch (SQLException e) {
        }
    }
    }
    
    void eventUpdate(){
        koneksii();
        if (!validasiField(fieldNoSlip)) {return;}
        if (!validasiField(fieldNamaKaryawan)) {return;}
        if (!validasiInput(fieldGajiPokok) || !validasiInput(fieldTunjangan)
                || !validasiInput(fieldLembur) || !validasiInput(fieldUpahLembur)) 
        {return;}
        
        double totalGaji;
        try {
            double gajiPokok = Double.parseDouble(fieldGajiPokok.getText());
            double tunjangan = Double.parseDouble(fieldTunjangan.getText());
            double jumlahLembur = Double.parseDouble(fieldLembur.getText());
            double upahLembur = Double.parseDouble(fieldUpahLembur.getText());

            totalGaji = gajiPokok + tunjangan + (jumlahLembur * upahLembur);
            fieldTotalGaji.setText(String.valueOf(totalGaji));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input tidak valid, pastikan semua kolom berisi angka.",
            "Kesalahan Format Angka", JOptionPane.ERROR_MESSAGE);
        }
        
        String kueri = "update gaji set namaKaryawan = ?, "
                + "gajiPokok = ?, tunjangan = ?, jumlahLembur = ?,"
                + "UpahLembur = ?, totalGaji = ? where noSlip = ?";
        try(PreparedStatement stmt = koneksi.prepareStatement(kueri)){
            
            
        
        
        Object[] dariDataa = dariData();
        
            stmt.setString(1, (String) dariDataa[1]);
            stmt.setString(2, (String) dariDataa[2]);
            stmt.setString(3, (String) dariDataa[3]);
            stmt.setString(4, (String) dariDataa[4]);
            stmt.setString(5, (String) dariDataa[5]);
            stmt.setString(6, (String) dariDataa[6]);
            stmt.setInt(7, Integer.parseInt((String) dariDataa[0]));
            
            int barisEdit = stmt.executeUpdate();
            if (barisEdit > 0) {
            tampil();
            hapusField();
            buttonSimpan.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Update");
            koneksi.close();
            }
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Proses Update Gagal " + e);
        }
    }
    
    void eventTabel(java.awt.event.MouseEvent evt){
        hapusField();
        if(evt.getClickCount()==1){
        int baris = tabel.getSelectedRow();    
        
        
        fieldNoSlip.setText(tabel.getValueAt(baris, 0).toString());
        fieldNamaKaryawan.setText(tabel.getValueAt(baris, 1).toString());
        fieldGajiPokok.setText(tabel.getValueAt(baris, 2).toString());
        fieldTunjangan.setText(tabel.getValueAt(baris, 3).toString());
        fieldLembur.setText(tabel.getValueAt(baris, 4).toString());
        fieldUpahLembur.setText(tabel.getValueAt(baris, 5).toString());
        fieldTotalGaji.setText(tabel.getValueAt(baris, 6).toString());
        
        buttonSimpan.setEnabled(false);
        buttonUpdate.setEnabled(true);
        buttonHapus.setEnabled(true);
        }
    }
    
    void eventSebelumTutup(java.awt.event.WindowEvent evt){
        Object[] pilhans = {"Ya, Keluar", "Tak, masih pakai"};
        
        int pilihan1; 
        pilihan1 = JOptionPane.showOptionDialog(null, 
                "Anda ingin keluar?",
                "Konfirmasi Keluar", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, pilhans, pilhans[1]);
        if (pilihan1 == JOptionPane.YES_OPTION) {
            dispose();
        } else if (pilihan1 == JOptionPane.NO_OPTION) {
            evt.getWindow().setVisible(true);
        }
    }
    
    void eventSetelahTutup(){
        JOptionPane.showMessageDialog(null, 
                "Terima Kasih telah menggunakan aplikasi", 
                "Aplikasi Ditutup", JOptionPane.PLAIN_MESSAGE);
    }
    
    void eventBuka(){
        JOptionPane.showMessageDialog(null,
                """
                Selamat menggunakan aplikasi!
                Lengkapi data!
                Edit data atau cari data,
                Lakukan satu-satu!
                JANGAN KORUPSI!
                Jangan mengedit total gaji!
                """, 
        "Membuka Aplikasi", JOptionPane.INFORMATION_MESSAGE);
    }
    
    void cariNama(){
        // Override isCellEditable untuk mengembalikan nilai yang sesuai dari array canEdit
        DefaultTableModel tebelCari = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return canEdit[column];  // Menentukan kolom mana yang bisa diedit (false = tidak bisa diedit)
    }
    };
        tebelCari.addColumn("NO SLIP");
        tebelCari.addColumn("NAMA KARYAWAN");
        tebelCari.addColumn("GAJI POKOK");
        tebelCari.addColumn("TUNJANGAN");
        tebelCari.addColumn("JUMLAH LEMBUR");
        tebelCari.addColumn("UPAH LEMBUR");
        tebelCari.addColumn("TOTAL GAJI");
        
        try{
            koneksii();
            String sql = "Select * from gaji where namaKaryawan like '%"+ fieldCariNama.getText() +"%'";
            rs = kn.executeQuery(sql);
            
            while(rs.next()){
                tebelCari.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                });
            }
            hapusField();
            tabel.setModel(tebelCari);
        }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan " + e);
        }
    }
    
    void eventTotal(){
        JOptionPane.showMessageDialog(null, "Tidak dapat mengedit total gaji!",
                "Peringatan", JOptionPane.ERROR_MESSAGE);
    }
    
    void klikTotal(java.awt.event.MouseEvent evt){
        if (evt.getClickCount() == 1) {
            JOptionPane.showMessageDialog(null, "Tidak dapat mengedit total gaji!", "Peringatan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void reset(){
        hapusField();
        tampil();
        buttonSimpan.setEnabled(true);
    }
    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        // TODO add your handling code here:
        eventHapus();
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void fieldUpahLemburActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldUpahLemburActionPerformed
        // TODO add your handling code here:
        eventUpah();
    }//GEN-LAST:event_fieldUpahLemburActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        // TODO add your handling code here:
        eventSimpan();
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        // TODO add your handling code here:
        eventUpdate();
    }//GEN-LAST:event_buttonUpdateActionPerformed
    
    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        eventTabel(evt);
    }//GEN-LAST:event_tabelMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        eventSebelumTutup(evt);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        eventSetelahTutup();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        eventBuka();
    }//GEN-LAST:event_formWindowOpened
    
    private void fieldCariNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCariNamaActionPerformed
        // TODO add your handling code here:
        cariNama();
    }//GEN-LAST:event_fieldCariNamaActionPerformed

    private void fieldTotalGajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTotalGajiActionPerformed
        // TODO add your handling code here:
        eventTotal();
    }//GEN-LAST:event_fieldTotalGajiActionPerformed

    private void fieldTotalGajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldTotalGajiMouseClicked
        // TODO add your handling code here:
        klikTotal(evt);
    }//GEN-LAST:event_fieldTotalGajiMouseClicked

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_buttonResetActionPerformed

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
            java.util.logging.Logger.getLogger(GajiKaryawann.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
            
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(() -> {
            new GajiKaryawann().setVisible(true);
        });
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonKeluar;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JTextField fieldCariNama;
    private javax.swing.JTextField fieldGajiPokok;
    private javax.swing.JTextField fieldLembur;
    private javax.swing.JTextField fieldNamaKaryawan;
    private javax.swing.JTextField fieldNoSlip;
    private javax.swing.JTextField fieldTotalGaji;
    private javax.swing.JTextField fieldTunjangan;
    private javax.swing.JTextField fieldUpahLembur;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    // End of variables declaration//GEN-END:variables
}

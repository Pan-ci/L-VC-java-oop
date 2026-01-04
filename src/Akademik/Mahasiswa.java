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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 3cc
 */
public class Mahasiswa extends javax.swing.JFrame {
    public Connection koneksi = null;
    public Statement kn =  null;
    public ResultSet rs = null;
    /**
     * Creates new form Students
     */
    public Mahasiswa() {
        initComponents();
        KMBJK();
        KMBFakultas();
        tampil();
        kmbFakultas.addActionListener(this::kmbFakultasActionPerformed);
    }
    
    private void koneksii() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/akademik2322009","root","");
            kn = koneksi.createStatement();
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
    
    // Array untuk menentukan kolom mana yang tidak bisa diedit
    boolean canEdit[] = new boolean[]{
        false, false, false, false, false, false, false, false}; // Semua kolom tidak bisa diedit
    
    private void tampil(){
        // Membuat objek Calendar dan mengatur tanggal
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.DECEMBER, 1); // Tahun 2024, Desember (bulan ke-11)
        java.util.Date tglPinjamm = calendar.getTime();
        dtTglLahir.setDate(tglPinjamm);
        // Override isCellEditable untuk mengembalikan nilai yang sesuai dari array canEdit
    DefaultTableModel tebelData = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return canEdit[column];  // Menentukan kolom mana yang bisa diedit (false = tidak bisa diedit)
    }
    };
        tebelData.addColumn("NIM");
        tebelData.addColumn("Nama Mahasiswa");
        tebelData.addColumn("Tempat Lahir");
        tebelData.addColumn("Tanggal Lahir");
        tebelData.addColumn("Nomor Telepon");
        tebelData.addColumn("Alamat");
        tebelData.addColumn("Jenis Kelamin");
        tebelData.addColumn("Nama Orang Tua");
        tebelData.addColumn("Fakultas");
        tebelData.addColumn("Program Studi");
        tebelData.addColumn("Semester");
        
        try{
            koneksii();
            btnEdit.setEnabled(false);
            btnHapus.setEnabled(false);
            String sql = "Select * from mahasiswa_2322009";
            rs = kn.executeQuery(sql);
            
            while(rs.next()){
                tebelData.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getInt(11)
                });
            }
            tblInput.setModel(tebelData);
            fdNIM.setEnabled(true);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan" + e);
        }
    }
    
    private void cariNama(){
    if(!validasiField(fdCari) || !isVldNama(fdCari)){return;}
        // Override isCellEditable untuk mengembalikan nilai yang sesuai dari array canEdit
    DefaultTableModel tebelData = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return canEdit[column];  // Menentukan kolom mana yang bisa diedit (false = tidak bisa diedit)
    }
    };
        tebelData.addColumn("NIM");
        tebelData.addColumn("Nama Mahasiswa");
        tebelData.addColumn("Tempat Lahir");
        tebelData.addColumn("Tanggal Lahir");
        tebelData.addColumn("Nomor Telepon");
        tebelData.addColumn("Alamat");
        tebelData.addColumn("Jenis Kelamin");
        tebelData.addColumn("Nama Ortu");
        tebelData.addColumn("Fakultas");
        tebelData.addColumn("Program Studi");
        tebelData.addColumn("Semester");
        
        try{
            koneksii();
            String sql = "Select * from mahasiswa_2322009 where namaMHS_2322009 like '%"+fdCari.getText()+"%'";
            rs = kn.executeQuery(sql);
            
            while(rs.next()){
                tebelData.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getInt(11)
                });
            }
            tblInput.setModel(tebelData);
            btnEdit.setEnabled(true);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan" + e);
        }
    }
    
    private boolean cekNIM(String noPinjam){
        boolean duplikat = false;
        
        try{
            String kueri = "select count(*) from mahasiswa_2322009 where NIMMHS_2322009 = ?";
            try (PreparedStatement ns = koneksi.prepareStatement(kueri)) {
                ns.setString(1, noPinjam);
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
    
    private void KMBJK(){
    // Daftar item dalam array
    String[] jenisMobill = {"Pilih Jenis Kelamin", "Laki-laki", "Perempuan"};
    
    // Membuat model baru dengan array item
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(jenisMobill);
    
    // Menetapkan model ke JComboBox
    kmbJK.setModel(model);
    }
    
    private void KMBFakultas(){
    // Daftar item dalam array
    String[] jenisMobill = {"Pilih Fakultas", "Ekonomi dan Bisnis", "Teknik", "Kedokteran"};
    
    // Membuat model baru dengan array item
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(jenisMobill);
    
    // Menetapkan model ke JComboBox
    kmbFakultas.setModel(model);
    }
    
    private Object[] dariData() {
        return new Object[]{
            fdNIM.getText().trim(),
            fdNama.getText().trim(),
            fdTptLahir.getText().trim(),
            ((JTextField)dtTglLahir.getDateEditor().getUiComponent()).getText(),
            fdNoHP.getText().trim(),
            fdAlamat.getText().trim(),
            kmbJK.getSelectedItem(),
            fdOrtu.getText().trim(),
            kmbFakultas.getSelectedItem(),
            kmbPStudi.getSelectedItem(),
            fdSemester.getText().trim()
        };
    }
    
    Object[] dariDataa;
    String nimMHS;
    Date tglLahir;
    String namaMHS;
    String jk;
    int noHP;
    String ortu;
    String tptLahir;
    String alamat;
    String fakultas;
    String prodi;
    int semester;
    
    private void hapusField() {
        fdNIM.setText("");
        //dtTglLahir.setDateFormatString("2024-01-01");
        fdNama.setText("");
        kmbJK.setSelectedItem("Pilih Jenis Kelamin");
        kmbFakultas.setSelectedItem("Pilih Fakultas");
        kmbPStudi.setSelectedItem("Pilih Fakultas Dahulu");
        fdTptLahir.setText("");
        fdNoHP.setText("");
        fdSemester.setText("");
        fdAlamat.setText("");
        fdOrtu.setText("");
        fdCari.setText("");
    }
    
    private boolean validasiField(JTextField field) {
        if (field.getText() == null) {
        JOptionPane.showMessageDialog(null, "Field " + field.getName() + " tidak boleh kosong!", 
                "Masalah Input", JOptionPane.ERROR_MESSAGE);
        return false;  // Jika field null, langsung kembalikan false
        }
    
        // Cek apakah field kosong atau hanya berisi spasi
        if (field.getText().trim().isBlank()) {
            JOptionPane.showMessageDialog(null, "Kolom " + field.getName() + " tidak boleh kosong!", 
                    "Masalah Input", JOptionPane.ERROR_MESSAGE);
            return false;  // Jika kosong, kembalikan false
        }
        return true;  // Field valid, kembalikan true
    }
    
    private boolean isVldNama(JTextField field) {
        String input = field.getText().trim();
        // Cek apakah field kosong atau hanya berisi spasi
        if (!input.matches("[a-zA-Z\\s]+")) { //boleh huruf dan spasi
            JOptionPane.showMessageDialog(null, "Kolom " + field.getName() + " tidak boleh mengandung angka!", 
                    "Masalah Input", JOptionPane.ERROR_MESSAGE);
            return false;  // Jika kosong, kembalikan false
        } //(!input.matches("[a-zA-Z]+")) 
        return true;  // Field valid, kembalikan true
    }
    /*private boolean isVldNIM(JTextField field) {
        String input = field.getText().trim();
        if (!input.contains("MHS")) {
            JOptionPane.showMessageDialog(null, "Kolom " + field.getName() + " tidak boleh kosong!", 
                "Masalah Input", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!input.matches(".*MHS\\d{2}$")) {
            return false;
        }
        // Cek apakah input mengandung "MHS" dan memiliki dua digit angka setelah "MHS"
        return true;
    }*/
    
    private boolean validasiAngka(JTextField field) {
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
    
    private boolean validasiTanggal(com.toedter.calendar.JDateChooser pilihTanggal){
        if (pilihTanggal.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Harap pilih tanggal\n",
                                            "Masalah Pemilihan Tanggal", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean VLD_KMB(javax.swing.JComboBox pilihJenis){
        String itemPilih = (String) pilihJenis.getSelectedItem();
        if (itemPilih.contains("Pilih")) {
            JOptionPane.showMessageDialog(null, "Harap pilih " + pilihJenis.getName() + "\n",
                                            "Masalah Pemilihan", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void KMBF(javax.swing.JComboBox pilihJenis){
        if (pilihJenis.getSelectedItem().equals("Ekonomi dan Bisnis")) {
            KMBPSEkoBis();
        }
        else if (pilihJenis.getSelectedItem().equals("Teknik")) {
            KMBPSTeknik();
        }
        else if (pilihJenis.getSelectedItem().equals("Kedokteran")) {
            KMBPSDokter();
        }
        else if (pilihJenis.getSelectedItem().equals("Pilih Fakultas")) {
            KMBPSDefault();
        }
    }
    
    private void KMBPSEkoBis(){
    // Daftar item dalam array
    String[] jenisMobill = {"Pilih Prodi", "Ekonomi Pembangunan", "Akuntansi", "Manajemen"};
    
    // Membuat model baru dengan array item
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(jenisMobill);
    
    // Menetapkan model ke JComboBox
    kmbPStudi.setModel(model);
    }
    
    private void KMBPSDokter(){
    // Daftar item dalam array
    String[] jenisMobill = {"Pilih Prodi", "Pendidikan Dokter", "Kedokteran Gigi", "Farmasi"};
    
    // Membuat model baru dengan array item
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(jenisMobill);
    
    // Menetapkan model ke JComboBox
    kmbPStudi.setModel(model);
    }
    
    private void KMBPSTeknik(){
    // Daftar item dalam array
    String[] jenisMobill = {"Pilih Prodi", "Teknik Informatika", "Teknik Sipil", "Teknik Mesin"};
    
    // Membuat model baru dengan array item
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(jenisMobill);
    
    // Menetapkan model ke JComboBox
    kmbPStudi.setModel(model);
    }
    
    private void KMBPSDefault(){
    // Daftar item dalam array
    String[] jenisMobill = {"Pilih Fakultas Dahulu"};
    
    // Membuat model baru dengan array item
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(jenisMobill);
    
    // Menetapkan model ke JComboBox
    kmbPStudi.setModel(model);
    }
    
    void eventSimpan(){
        koneksii();
        if ( !validasiAngka(fdNIM) || !validasiField(fdNama) || !isVldNama(fdNama)
                || !validasiField(fdTptLahir) || !validasiTanggal(dtTglLahir)
                || !validasiAngka(fdNoHP)|| !validasiField(fdAlamat)
                || !VLD_KMB(kmbJK) || !validasiField(fdOrtu) || !isVldNama(fdOrtu)
                || !VLD_KMB(kmbFakultas) || !VLD_KMB(kmbPStudi) || !validasiAngka(fdSemester)) 
        {return;}
        
        dariDataa = dariData();
        nimMHS = (String) dariDataa[0];
        namaMHS = (String) dariDataa[1];
        tptLahir = (String) dariDataa[2];
        tglLahir = Date.valueOf((String) dariDataa[3]);
        noHP = Integer.parseInt((String) dariDataa[4]);
        alamat = (String) dariDataa[5];
        jk = (String) dariDataa[6];
        ortu = (String) dariDataa[7];
        fakultas = (String) dariDataa[8];
        prodi = (String) dariDataa[9];
        semester = Integer.parseInt((String) dariDataa[10]); 
        
        // Memeriksa apakah nomor slip sudah ada
        if (cekNIM(nimMHS)){
        JOptionPane.showMessageDialog(null, "NIM sudah ada di database, ganti yang lain" ,
                "Masalah Duplikasi", JOptionPane.ERROR_MESSAGE);
        return;  // keluar jika duplikasi ditemukan
        }
    
        String kueri = "insert into mahasiswa_2322009 (NIMMHS_2322009,"
                + "namaMHS_2322009, tptLahir_2322009,"
                + "tglLahir_2322009, noHP_2322009, alamatMHS_2322009,"
                + "JK_2322009, namaOrtu_2322009, fakultasMHS_2322009,"
                + "prodi_2322009, semester_2322009)"
            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = koneksi.prepareStatement(kueri)) {
        // Menyiapkan data untuk disimpan
        stmt.setString(1, nimMHS);
        stmt.setString(2, namaMHS);
        stmt.setString(3, tptLahir);
        // bikin objek modal objek awokaowkaowak
        stmt.setDate(4, tglLahir);
        //stmt.setInt(3, Integer.parseInt((String) dariDataa[2]));
        stmt.setInt(5, noHP);
        stmt.setString(6, alamat);
        stmt.setString(7, jk);
        stmt.setString(8, ortu);
        stmt.setString(9, fakultas);
        stmt.setString(10, prodi);
        stmt.setInt(11, semester);

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
        } catch (SQLException e) {}
    }
    }
    
    void evtNIMMHS(){
        try{
            koneksii();
            String sql = "Select * from mahasiswa_2322009 where NIMMHS_2322009 ='" + String.valueOf(fdNIM.getText()) +"'";
            rs = kn.executeQuery(sql);
            if (rs.next())
            {
            fdNIM.setText(rs.getString(1));
            fdNama.setText(rs.getString(2));
            fdTptLahir.setText(rs.getString(3));
            dtTglLahir.setDate(rs.getDate(4));
            fdNoHP.setText(rs.getString(5));
            fdAlamat.setText(rs.getString(6));
            kmbJK.setSelectedItem(rs.getString(7));
            fdOrtu.setText(rs.getString(8));
            kmbFakultas.setSelectedItem(rs.getString(9));
            kmbPStudi.setSelectedItem(rs.getString(10));
            fdSemester.setText(rs.getString(11));
            }
            fdNIM.setEnabled(false);
            btnEdit.setEnabled(true);
            btnHapus.setEnabled(true);
            } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan " + e);
            }
    }
    
    void eventEdit(){
        koneksii();
        if ( !validasiAngka(fdNIM) || !validasiField(fdNama)
                || !validasiField(fdTptLahir) || !validasiTanggal(dtTglLahir)
                || !validasiAngka(fdNoHP)|| !validasiField(fdAlamat)
                || !VLD_KMB(kmbJK) || !validasiField(fdOrtu)
                || !VLD_KMB(kmbFakultas) || !VLD_KMB(kmbPStudi) || !validasiAngka(fdSemester)) 
        {return;}
        
        dariDataa = dariData();
        nimMHS = (String) dariDataa[0];
        tglLahir = Date.valueOf((String) dariDataa[3]);
        namaMHS = (String) dariDataa[1];
        tptLahir = (String) dariDataa[2];
        noHP = Integer.parseInt((String) dariDataa[4]);
        alamat = (String) dariDataa[5];
        jk = (String) dariDataa[6];
        ortu = (String) dariDataa[7];
        fakultas = (String) dariDataa[8];
        prodi = (String) dariDataa[9];
        semester = Integer.parseInt((String) dariDataa[10]);
        
        /* Memeriksa apakah nomor slip sudah ada
        if (cekNIM(nimMHS)){
        JOptionPane.showMessageDialog(null, "NIM sudah ada di database, ganti yang lain" ,
                "Masalah Duplikasi", JOptionPane.ERROR_MESSAGE);
        return;  // keluar jika duplikasi ditemukan
        }
    /*String kueri = "update pinjammobil set tanggalPinjam = ?, "
                + " namaPeminjam = ?, jenisMobil = ?, sewaPerhari = ?,"
                + "noPolisi = ?, lamaPinjam = ?, totalBayar = ? where noPinjam = ?";*/
        String kueri = "update mahasiswa_2322009 set namaMHS_2322009= ?, tptLahir_2322009= ?,"
                + "tglLahir_2322009= ?, noHP_2322009= ?,"
                + "alamatMHS_2322009= ?, JK_2322009= ?,"
                + "namaOrtu_2322009= ?, fakultasMHS_2322009= ?,"
                + "prodi_2322009= ?, semester_2322009 = ? where NIMMHS_2322009= ?";
    
    try (PreparedStatement stmt = koneksi.prepareStatement(kueri)) {
        // Menyiapkan data untuk disimpan
        
        stmt.setString(1, namaMHS);
        stmt.setString(2, tptLahir);
        // bikin objek modal objek awokaowkaowak
        stmt.setDate(3, tglLahir);
        //stmt.setInt(3, Integer.parseInt((String) dariDataa[2]));
        stmt.setInt(4, noHP);
        stmt.setString(5, alamat);
        stmt.setString(6, jk);
        stmt.setString(7, ortu);
        stmt.setString(8, fakultas);
        stmt.setString(9, prodi);
        stmt.setInt(10, semester);
        stmt.setString(11, nimMHS);
        // Eksekusi perintah SQL
        int barisEdit = stmt.executeUpdate();
        if (barisEdit > 0) {
            tampil();
            hapusField();
            btnSimpan.setEnabled(true);
            btnEdit.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        }
    } catch (HeadlessException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Proses edit gagal\n" + e,
                "Masalah Pengeditan", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (koneksi != null && !koneksi.isClosed()) {
                koneksi.close();
            }
        } catch (SQLException e) {}
    }
    
    }
    
    void eventHapus(){
        try{
            koneksii();
            String sql = "delete from mahasiswa_2322009 where NIMMHS_2322009 = '"+fdNIM.getText()+"'";
            kn.executeUpdate(sql);
            tampil();
            hapusField();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            btnSimpan.setEnabled(true);
            koneksi.close();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Kesalahan penghapusan data\n" + e.getMessage(),
                                            "Masalah Penghapusan Data", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInput = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnCetakPDF = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        kmbFakultas = new javax.swing.JComboBox<>();
        kmbJK = new javax.swing.JComboBox<>();
        kmbPStudi = new javax.swing.JComboBox<>();
        dtTglLahir = new com.toedter.calendar.JDateChooser();
        fdOrtu = new javax.swing.JTextField();
        fdAlamat = new javax.swing.JTextField();
        fdNoHP = new javax.swing.JTextField();
        fdTptLahir = new javax.swing.JTextField();
        fdNIM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        fdNama = new javax.swing.JTextField();
        fdSemester = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        fdCari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tblInput.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblInput);

        jLabel1.setText("INPUT / CETAK DATA MAHASISWA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnKeluar.setText("KELUAR");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnCetakPDF.setText("Cetak PDF");
        btnCetakPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKeluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCetakPDF)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnKeluar)
                    .addComponent(btnHapus)
                    .addComponent(btnEdit)
                    .addComponent(btnCetakPDF))
                .addContainerGap())
        );

        kmbFakultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        kmbFakultas.setName("Fakultas"); // NOI18N
        kmbFakultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmbFakultasActionPerformed(evt);
            }
        });

        kmbJK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        kmbJK.setName("Jenis Kelamin"); // NOI18N
        kmbJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmbJKActionPerformed(evt);
            }
        });

        kmbPStudi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Fakultas Dahulu" }));
        kmbPStudi.setName("Program Studi"); // NOI18N

        dtTglLahir.setDateFormatString("yyyy-MM-dd");
        dtTglLahir.setName("Tanggal Lahir"); // NOI18N

        fdOrtu.setName("Nama Orang Tua"); // NOI18N

        fdAlamat.setName("Alamat"); // NOI18N

        fdNoHP.setName("Nomor Telepon"); // NOI18N

        fdTptLahir.setName("Tempat Lahir"); // NOI18N
        fdTptLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdTptLahirActionPerformed(evt);
            }
        });

        fdNIM.setName("NIM"); // NOI18N
        fdNIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdNIMActionPerformed(evt);
            }
        });

        jLabel3.setText("NIM MAHASISWA");

        jLabel4.setText("NAMA MAHASISWA");

        jLabel5.setText("TANGGAL LAHIR MAHASISWA");

        jLabel6.setText("TEMPAT LAHIR MAHASISWA");

        jLabel7.setText("NO TELP MAHASISWA");

        jLabel8.setText("ALAMAT MAHASISWA");

        jLabel9.setText("JENIS KELAMIN");

        jLabel10.setText("NAMA ORTU");

        jLabel11.setText("FAKULTAS");

        jLabel12.setText("PROGRAM STUDI");

        fdNama.setName("Nama Mahasiswa"); // NOI18N

        fdSemester.setName("Semester"); // NOI18N

        jLabel13.setText("SEMESTER");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fdNIM, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kmbPStudi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kmbFakultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fdOrtu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kmbJK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fdAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fdNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fdTptLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dtTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fdNama, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fdSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fdNIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fdNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fdTptLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(fdNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fdAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(kmbJK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(fdOrtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kmbFakultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kmbPStudi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fdSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        fdCari.setName("Cari Nama"); // NOI18N
        fdCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdCariActionPerformed(evt);
            }
        });

        jLabel2.setText("Cari nama mahasiswa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fdCari, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fdCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        eventSimpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void kmbJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmbJKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kmbJKActionPerformed

    private void kmbFakultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmbFakultasActionPerformed
        // TODO add your handling code here:
        KMBF(kmbFakultas);
    }//GEN-LAST:event_kmbFakultasActionPerformed

    private void fdCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdCariActionPerformed
        // TODO add your handling code here:
        cariNama();
    }//GEN-LAST:event_fdCariActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        eventEdit();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        eventHapus();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void fdTptLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdTptLahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fdTptLahirActionPerformed

    private void fdNIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdNIMActionPerformed
        // TODO add your handling code here:
        evtNIMMHS();
    }//GEN-LAST:event_fdNIMActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKeluarActionPerformed

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
    
    private void btnCetakPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakPDFActionPerformed
        // TODO add your handling code here:
        printPDF("Laporan Mahasiswa", tblInput);
    }//GEN-LAST:event_btnCetakPDFActionPerformed

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
            java.util.logging.Logger.getLogger(Mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Mahasiswa().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetakPDF;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private com.toedter.calendar.JDateChooser dtTglLahir;
    private javax.swing.JTextField fdAlamat;
    private javax.swing.JTextField fdCari;
    private javax.swing.JTextField fdNIM;
    private javax.swing.JTextField fdNama;
    private javax.swing.JTextField fdNoHP;
    private javax.swing.JTextField fdOrtu;
    private javax.swing.JTextField fdSemester;
    private javax.swing.JTextField fdTptLahir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kmbFakultas;
    private javax.swing.JComboBox<String> kmbJK;
    private javax.swing.JComboBox<String> kmbPStudi;
    private javax.swing.JTable tblInput;
    // End of variables declaration//GEN-END:variables
}

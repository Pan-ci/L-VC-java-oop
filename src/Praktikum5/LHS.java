/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Praktikum5;

import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author 3cc
 */
public class LHS extends javax.swing.JFrame {

    /**
     * Creates new form LHS
     */
    private final Border angkaDefaultBorder;
    private final Border sksDefaultBorder;
    public LHS() {
        initComponents();
        tabelLHS.setModel(tableModel);
        angkaDefaultBorder = fieldAngka.getBorder();
        sksDefaultBorder = fieldSKS.getBorder();
        buttonUpdate.setEnabled(false);
        buttonHapus.setEnabled(false);
        
        fieldAngka.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { onChange(); }
            @Override
            public void removeUpdate(DocumentEvent e) { onChange(); }
            @Override
            public void changedUpdate(DocumentEvent e) { onChange(); }

            private void onChange() {
                validateAngka(); hitung();        // hanya field ini
                // if (allValid()) hitung();
            }
        });

        fieldSKS.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { onChange(); }
            @Override
            public void removeUpdate(DocumentEvent e) { onChange(); }
            @Override
            public void changedUpdate(DocumentEvent e) { onChange(); }

            private void onChange() {
                validateSKS(); hitung();          // hanya field ini
                // if (allValid()) hitung();
            }
        });
    }

    private final javax.swing.table.DefaultTableModel tableModel = getDefaultTableModel();
    int baris = 0;
    
    private javax.swing.table.DefaultTableModel getDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel (
                new Object[][]{},
                new String[]{
                    "Kode","Mata kuliah","SKS","Nilai Angka",
                    "Nilai Huruf","Bobot","Mutu","Keterangan"
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
    
    private class mataKuliah {
        String kode;
        String matkul;
        String sks;
        String nilaiAngka;
        String nilaiHuruf;
        String bobot;
        String mutu;
        String keterangan;
    }
    
    private boolean validateAngka() {
        try {
            int angka = Integer.parseInt(fieldAngka.getText());
            if (angka >= 100 || angka < 0) {
                error(fieldAngka);
                return false;
            }
            resetError(fieldAngka, angkaDefaultBorder);
            return true;
        } catch (NumberFormatException e) {
            error(fieldAngka);
            return false;
        }
    }

    private boolean validateSKS() {
        try {
            int sks = Integer.parseInt(fieldSKS.getText());
            if (sks >= 5 || sks <= 0) {
                error(fieldSKS);
                return false;
            }
            resetError(fieldSKS, sksDefaultBorder);
            return true;
        } catch (NumberFormatException e) {
            error(fieldSKS);
            return false;
        }
    }
    
    /*
    private boolean isValidNumber(
        JTextField field,
        Predicate<Double> rule
    ) {
        try {
            double value = Double.parseDouble(field.getText());
            return rule == null || rule.test(value);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    private boolean allValidate() {
        boolean validAngka = isValidNumber(fieldAngka, null);
        boolean validSKS   = isValidNumber(fieldSKS, v -> v < 5);

        if (!validAngka) {
            error(fieldAngka);
            labelError.setText("(Input angka salah)");
            return false;
        }

        if (!validSKS) {
            error(fieldSKS);
            labelError.setText("(Input SKS salah)");
            return false;
        }

        resetError(fieldAngka, angkaDefaultBorder);
        resetError(fieldSKS, sksDefaultBorder);
        labelError.setText("");

        return true;
    }
    */
    
    private boolean allValid() {
        boolean angkaValid = validateAngka();
        boolean sksValid   = validateSKS();

        if (!angkaValid || !sksValid) {
            labelError.setText("(Input salah)");
            return false;
        }

        labelError.setText("");
        return true;
    }
    
    /*
    private boolean validateSKS(int SKS){
        return SKS < 5;
    }
    */
    
    private void resetError(JTextField field, Border defaultBorder) {
        field.setBorder(defaultBorder);
        labelError.setText("");
    }
    
    private void error(JTextField field) {
        field.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelError.setText("(Input salah)");
    }
    
    private void totalSKS(){
        double totalSks = 0;
        int jumlahRecord = tabelLHS.getRowCount();
        for (int k = 0; k < jumlahRecord; k++){
            totalSks = totalSks + Double.parseDouble(tableModel.getValueAt(k, 2).toString());
        }
        DecimalFormat df = new DecimalFormat("#");
        fieldTotalSKS.setText(df.format(totalSks));
    }
    
    private void totalMUTU(){
        double totalMutu = 0; // double totalMutu = 0;
        int jumlahRecord = tabelLHS.getRowCount();
        for (int l = 0; l < jumlahRecord; l++){
            totalMutu = totalMutu + Double.parseDouble(tableModel.getValueAt(l, 6).toString());
        }
        DecimalFormat df = new DecimalFormat("#");
        fieldTotalMutu.setText(df.format(totalMutu));
    }
    
    private float ip;
    
    private void hitungIP(){
        float totalSKs = Float.parseFloat(fieldTotalSKS.getText());
        float totalMUtu = Float.parseFloat(fieldTotalMutu.getText());

        DecimalFormat df = new DecimalFormat("#.00");

        if (totalSKs == 0) {
            fieldIP.setText("0.00"); // atau ""
            ip = 0;
        } else {
            float IP = totalMUtu / totalSKs;
            fieldIP.setText(df.format(IP));
            ip = IP;
        }
    }
    
    private void maxSKS(){
        float Ip = ip; // float Ip = Float.parseFloat(fieldIP.getText());
        String maxSKS;
        if(Ip >= 3)
            maxSKS = "24 SKS";
        else if (Ip >= 2.5)
            maxSKS = "23 SKS";
        else if (Ip >= 2)
            maxSKS = "20 SKS";
        else if (Ip < 2 && Ip >= 0)
            maxSKS = "15 SKS";
        else
            maxSKS = "0 SKS";
        
        /*
        IPK ≥ 3,00	24 SKS (Beban studi penuh/maksimal)
        IPK 2,50 – 2,99	21 – 23 SKS
        IPK 2,00 – 2,49	18 – 20 SKS
        IPK < 2,00	12 – 15 SKS
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
            maxSKS = "Input salah!";
        */
        
        fieldMaxSKS.setText(String.valueOf(maxSKS));
    }
    
    private void resetForm() {
        fieldKode.setText("");
        fieldNama.setText("");
        fieldMataKuliah.setText("");
        fieldSKS.setText("");
        fieldAngka.setText("");
        fieldHuruf.setText("");
        fieldBobot.setText("");
        fieldMutu.setText("");
        fieldKeterangan.setText("");
        
        resetError(fieldSKS, sksDefaultBorder);
        resetError(fieldAngka, angkaDefaultBorder);
    }
    
    private void setModeTambah() {
        buttonSimpan.setEnabled(true);
        buttonUpdate.setEnabled(false);
        buttonHapus.setEnabled(false);
    }

    private void setModeEdit() {
        buttonSimpan.setEnabled(false);
        buttonUpdate.setEnabled(true);
        buttonHapus.setEnabled(true);
    }
    
    private void tampilkan(){
        resetForm();
        
        baris = tabelLHS.getSelectedRow();
        fieldKode.setText(tableModel.getValueAt(baris, 0).toString());
        fieldMataKuliah.setText(tableModel.getValueAt(baris, 1).toString());
        fieldSKS.setText(tableModel.getValueAt(baris, 2).toString());
        fieldAngka.setText(tableModel.getValueAt(baris, 3).toString());
        fieldHuruf.setText(tableModel.getValueAt(baris, 4).toString());
        fieldBobot.setText(tableModel.getValueAt(baris, 5).toString());
        fieldMutu.setText(tableModel.getValueAt(baris, 6).toString());
        fieldKeterangan.setText(tableModel.getValueAt(baris, 7).toString());
        
        setModeEdit();
    }
    
    private void hitung(){
        String nilaiHuruf, keterangan;
        int mutu, bobot;
        
        if(allValid()){
            int nilaiAngka = Integer.parseInt(fieldAngka.getText());
            int SKS = Integer.parseInt(fieldSKS.getText());

            if (nilaiAngka > 80){
                nilaiHuruf = "A";
                bobot = 4;
                keterangan = "LULUS";
            } else if (nilaiAngka > 65){
                nilaiHuruf = "B";
                bobot = 3;
                keterangan = "LULUS";
            } else if (nilaiAngka > 55){
                nilaiHuruf = "C";
                bobot = 2;
                keterangan = "LULUS"; // LULUS
            } else if (nilaiAngka > 45){
                nilaiHuruf = "D";
                bobot = 1;
                keterangan = "GAGAL"; // LULUS
            } else { // nilaiAngka >= 45 && nilaiAngka > 0
                nilaiHuruf = "E";
                bobot = 0;
                keterangan = "GAGAL"; // LULUS
            }

            mutu = SKS * bobot;
        } else {
            labelError.setText("(Input salah)");
            nilaiHuruf = "Input salah";
            bobot = 0;
            keterangan = "Input salah";
            mutu = 0;
        }
        
        fieldHuruf.setText(String.valueOf(nilaiHuruf));
        fieldBobot.setText(String.valueOf(bobot));
        fieldMutu.setText(String.valueOf(mutu));
        fieldKeterangan.setText(String.valueOf(keterangan));
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
        fieldTahun = new javax.swing.JTextField();
        fieldSemester = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        fieldNIM = new javax.swing.JTextField();
        fieldNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        fieldKode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fieldMataKuliah = new javax.swing.JTextField();
        fieldKeterangan = new javax.swing.JTextField();
        fieldBobot = new javax.swing.JTextField();
        fieldMutu = new javax.swing.JTextField();
        fieldSKS = new javax.swing.JTextField();
        fieldAngka = new javax.swing.JTextField();
        fieldHuruf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelLHS = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        buttonSimpan = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();
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
        labelError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
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

        jLabel6.setText("Kode");

        fieldKeterangan.setEditable(false);
        fieldKeterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldKeteranganActionPerformed(evt);
            }
        });

        fieldBobot.setEditable(false);

        fieldMutu.setEditable(false);

        fieldAngka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldAngkaActionPerformed(evt);
            }
        });

        fieldHuruf.setEditable(false);

        jLabel7.setText("Mata Kuliah");

        jLabel8.setText("Nilai Angka");

        jLabel9.setText("SKS");

        jLabel10.setText("Nilai Huruf");

        jLabel11.setText("Bobot");

        jLabel12.setText("Mutu");

        jLabel13.setText("Keterangan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldKode, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldMataKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldSKS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldAngka, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldHuruf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldBobot, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldMutu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(fieldKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldMataKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldHuruf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldBobot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldMutu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fieldSKS)
                    .addComponent(fieldAngka))
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

        buttonReset.setText("RESET");
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(buttonSimpan)
                .addGap(18, 18, 18)
                .addComponent(buttonUpdate)
                .addGap(18, 18, 18)
                .addComponent(buttonHapus)
                .addGap(18, 18, 18)
                .addComponent(buttonReset))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSimpan)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonHapus)
                    .addComponent(buttonReset))
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

        jLabel1.setText("LAPORAN HASIL STUDI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(labelError)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldKeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldKeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldKeteranganActionPerformed

    private void fieldMaxSKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldMaxSKSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldMaxSKSActionPerformed

    private void fieldTotalMutuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTotalMutuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTotalMutuActionPerformed

    private void perbaruiLHS(){
        totalSKS();
        totalMUTU();
        hitungIP();
        maxSKS();
    }
    
    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        // TODO add your handling code here:
        tableModel.removeRow(baris);
        
        if (tabelLHS.getRowCount() == 0) {
            fieldTotalSKS.setText("0");
            fieldTotalMutu.setText("0");
            fieldIP.setText("0.00");
            ip = 0;
        }
        
        resetForm();
        setModeTambah();
        perbaruiLHS();
    }//GEN-LAST:event_buttonHapusActionPerformed
    
    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        // TODO add your handling code here:
       simpanUpdate(true);
    }//GEN-LAST:event_buttonUpdateActionPerformed
       
    private void simpanUpdate(boolean update){
        if(allValid()){
            mataKuliah m = new mataKuliah();
            m.kode = fieldKode.getText();
            m.matkul = fieldMataKuliah.getText();        
            m.sks = fieldSKS.getText();
            m.nilaiAngka = fieldAngka.getText();
            m.nilaiHuruf = fieldHuruf.getText();
            m.bobot = fieldBobot.getText();
            m.mutu = fieldMutu.getText();
            m.keterangan = fieldKeterangan.getText();

            Object[] row = {
                m.kode, m.matkul, m.sks,
                m.nilaiAngka, m.nilaiHuruf, m.bobot,
                m.mutu, m.keterangan
            };
            
            if(update){
                tableModel.removeRow(baris);
                tableModel.insertRow(baris, row);
                setModeTambah();
            } else {
                tableModel.insertRow(baris, row);
            }
            
            resetForm();
            perbaruiLHS();
        }
    }
    private void fieldAngkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAngkaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldAngkaActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        // TODO add your handling code here:
        simpanUpdate(false);
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void tabelLHSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelLHSMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1) tampilkan();
    }//GEN-LAST:event_tabelLHSMouseClicked

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        // TODO add your handling code here:
        resetForm();
        setModeTambah();
    }//GEN-LAST:event_buttonResetActionPerformed

    /*
        mataKuliah m = new mataKuliah();
        m.kode = fieldKode.getText();
        m.matkul = fieldMataKuliah.getText();        
        m.sks = fieldSKS.getText();
        m.nilaiAngka = fieldAngka.getText();
        m.nilaiHuruf = fieldHuruf.getText();
        m.bobot = fieldBobot.getText();
        m.mutu = fieldMutu.getText();
        m.keterangan = fieldKeterangan.getText();

        Object[] row = {
            m.kode, m.matkul, m.sks,
            m.nilaiAngka, m.nilaiHuruf, m.bobot,
            m.mutu, m.keterangan
        };

        tableModel.insertRow(baris, row);
        totalSKS();
        totalMUTU();
        hitungIP();
        maxSKS();
    */
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LHS().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JTextField fieldAngka;
    private javax.swing.JTextField fieldBobot;
    private javax.swing.JTextField fieldHuruf;
    private javax.swing.JTextField fieldIP;
    private javax.swing.JTextField fieldKeterangan;
    private javax.swing.JTextField fieldKode;
    private javax.swing.JTextField fieldMataKuliah;
    private javax.swing.JTextField fieldMaxSKS;
    private javax.swing.JTextField fieldMutu;
    private javax.swing.JTextField fieldNIM;
    private javax.swing.JTextField fieldNama;
    private javax.swing.JTextField fieldSKS;
    private javax.swing.JTextField fieldSemester;
    private javax.swing.JTextField fieldTahun;
    private javax.swing.JTextField fieldTotalMutu;
    private javax.swing.JTextField fieldTotalSKS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelError;
    private javax.swing.JTable tabelLHS;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Praktikum4;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author 3cc
 */
public class TableModel extends javax.swing.JFrame {

    /**
     * Creates new form TableModel
     */
    public TableModel() {
        initComponents();
        tabelModel.setModel(tabel);
        fieldAngsuran.setEditable(false);
        fieldTotal1.setEditable(false);
        fieldTotal2.setEditable(false);
        Border jumlahDefaultBorder = fieldJumlah.getBorder();
        Border lamaDefaultBorder = fieldLama.getBorder();
        Border bungaDefaultBorder = fieldBunga.getBorder();
        
        fieldJumlah.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (validateNumberField(
                        fieldJumlah,jumlahDefaultBorder)) {
                    hitung();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (validateNumberField(
                        fieldJumlah,jumlahDefaultBorder)) {
                    hitung();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (validateNumberField(
                        fieldJumlah,jumlahDefaultBorder)) {
                    hitung();
                }
            }
        });
        
        fieldLama.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (validateNumberField(
                        fieldLama,lamaDefaultBorder)) {
                    hitung();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (validateNumberField(
                        fieldLama,lamaDefaultBorder)) {
                    hitung();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (validateNumberField(
                        fieldLama,lamaDefaultBorder)) {
                    hitung();
                }
            }
        });
        
        fieldBunga.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (validateNumberField(
                        fieldBunga,bungaDefaultBorder)) {
                    hitung();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (validateNumberField(
                        fieldBunga,bungaDefaultBorder)) {
                    hitung();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (validateNumberField(
                        fieldBunga,bungaDefaultBorder)) {
                    hitung();
                }
            }
        });
    }
    
    class Pinjaman {
        String no;
        String nama;
        String jumlah;
        int lama;
        BigDecimal bunga;
        BigDecimal angsuran;
        BigDecimal total;
    }
    
    private javax.swing.table.DefaultTableModel getDefaultTableModel() {
        return new javax.swing.table.DefaultTableModel (
        new Object [][]{}, new String []{
            "No.","Nama","Jumlah pinjam","Lama pinjam",
            "Bunga/Bulan","Angsuran/Bulan","Total pinjaman"
        }
        );
    }

    // String data[] = new String[7];
    javax.swing.table.DefaultTableModel tabel = getDefaultTableModel();
    int baris = 0;
    
    void total() {
        BigDecimal total1 = BigDecimal.ZERO;

        for (int i = 0; i < tabelModel.getRowCount(); i++) {
            BigDecimal nilai = (BigDecimal) tabelModel.getValueAt(i, 6);
            total1 = total1.add(nilai);
        }

        fieldTotal2.setText(total1.setScale(2, RoundingMode.HALF_UP).toString());
    }
    
    void setModeTambah() {
        buttonSimpan.setEnabled(true);
        buttonPerbarui.setEnabled(false);
        buttonHapus.setEnabled(false);
    }

    void setModeEdit() {
        buttonSimpan.setEnabled(false);
        buttonPerbarui.setEnabled(true);
        buttonHapus.setEnabled(true);
    }

    static final String ERR_BUNGA_FORMAT =
        "<html>Lengkapi input jumlah,<br>"
        + "lama dan bunga<br>"
        + "dengan angka</html>";
    
    private BigDecimal currentAngsuran = BigDecimal.ZERO;
    private BigDecimal currentTotal    = BigDecimal.ZERO;

    void hitung() {
        try {
            BigDecimal pinjam = new BigDecimal(fieldJumlah.getText());
            BigDecimal lama   = new BigDecimal(fieldLama.getText());
            BigDecimal bunga  = new BigDecimal(fieldBunga.getText());

            BigDecimal seratus = new BigDecimal("100");

            BigDecimal totalBunga = pinjam
                    .multiply(bunga)
                    .divide(seratus, 10, RoundingMode.HALF_UP)
                    .multiply(lama);

            currentAngsuran = pinjam
                    .add(totalBunga)
                    .divide(lama, 10, RoundingMode.HALF_UP)
                    .setScale(2, RoundingMode.HALF_UP);

            currentTotal = currentAngsuran
                    .multiply(lama)
                    .setScale(2, RoundingMode.HALF_UP);

            // UI hanya tampilkan
            DecimalFormat df = new DecimalFormat("#.00");
            fieldAngsuran.setText(df.format(currentAngsuran));
            fieldTotal1.setText(df.format(currentTotal));
            total();
        
        } catch (NumberFormatException e) {
            labelError1.setText(ERR_BUNGA_FORMAT);
        }
    }
    
    boolean validateNumberField(
            JTextField field,
            Border defaultBorder
    ) {
        String text = field.getText();

        if (text.isEmpty()) {
            clearError(field, defaultBorder);
            return false;
        }

        try {
            Double.valueOf(text);
            clearError(field, defaultBorder);
            return true;
        } catch (NumberFormatException e) {
            setError(field);
            return false;
        }
    }
    
    class CurrencyRenderer extends DefaultTableCellRenderer {
        private final DecimalFormat df = new DecimalFormat("#.00");

        @Override
        protected void setValue(Object value) {
            if (value instanceof BigDecimal bd) {
                setText(df.format(bd));
            } else {
                super.setValue(value);
            }
        }
    }

    /*
    tabelModel.getColumnModel()
            .getColumn(6)
            .setCellRenderer(new CurrencyRenderer());
    */
    void allRender(){
        tabelModel.getColumnModel()
            .getColumn(5)
            .setCellRenderer(new CurrencyRenderer());
        tabelModel.getColumnModel()
            .getColumn(6)
            .setCellRenderer(new CurrencyRenderer());
    }
    
    void setError(JTextField field) {
        field.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelError1.setText("Harus angka bulat");
    }

    void clearError(JTextField field, Border defaultBorder) {
        field.setBorder(defaultBorder);
        labelError1.setText("");
        labelError2.setText("");
        labelError3.setText("");
    }
    
    void resetForm() {
        fieldNo.setText("");
        fieldNama.setText("");
        fieldJumlah.setText("");
        fieldLama.setText("");
        fieldBunga.setText("");
        fieldAngsuran.setText("");
        fieldTotal1.setText("");
        fieldTotal2.setText("");
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
        fieldNo = new javax.swing.JTextField();
        fieldNama = new javax.swing.JTextField();
        fieldJumlah = new javax.swing.JTextField();
        fieldLama = new javax.swing.JTextField();
        fieldBunga = new javax.swing.JTextField();
        fieldAngsuran = new javax.swing.JTextField();
        fieldTotal1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelError = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        buttonSimpan = new javax.swing.JButton();
        buttonPerbarui = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelModel = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        fieldTotal2 = new javax.swing.JTextField();
        labelError1 = new javax.swing.JLabel();
        labelError2 = new javax.swing.JLabel();
        labelError3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("No.");

        jLabel2.setText("Total");

        jLabel3.setText("Jumlah");

        jLabel4.setText("Nama");

        jLabel5.setLabelFor(fieldAngsuran);
        jLabel5.setText("Angsuran");

        jLabel6.setText("Lama");

        jLabel7.setText("Bunga");

        fieldBunga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldBungaActionPerformed(evt);
            }
        });

        fieldAngsuran.setEditable(false);

        fieldTotal1.setEditable(false);
        fieldTotal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTotal1ActionPerformed(evt);
            }
        });

        jLabel8.setText("%");

        jLabel9.setText("Bulan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldAngsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fieldBunga, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fieldLama, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addComponent(fieldJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldNo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelError)
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldBunga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(1, 1, 1)
                .addComponent(labelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldAngsuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        buttonSimpan.setText("SIMPAN");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        buttonPerbarui.setText("PERBARUI");
        buttonPerbarui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPerbaruiActionPerformed(evt);
            }
        });

        buttonHapus.setText("HAPUS");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });

        resetButton.setText("RESET");
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetButtonMouseClicked(evt);
            }
        });

        jButton5.setText("KELUAR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSimpan)
                    .addComponent(buttonPerbarui)
                    .addComponent(buttonHapus)
                    .addComponent(resetButton)
                    .addComponent(jButton5))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonSimpan)
                .addGap(18, 18, 18)
                .addComponent(buttonPerbarui)
                .addGap(18, 18, 18)
                .addComponent(buttonHapus)
                .addGap(18, 18, 18)
                .addComponent(resetButton)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelModel.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelModel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelModelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelModel);

        jLabel10.setText("Total");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fieldTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel10)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelError2)
                                    .addComponent(labelError1)
                                    .addComponent(labelError3)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelError1)
                        .addGap(18, 18, 18)
                        .addComponent(labelError2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelError3)
                        .addGap(21, 21, 21)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldBungaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldBungaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldBungaActionPerformed
      
    private void fieldTotal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTotal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTotal1ActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        // TODO add your handling code here:
        Pinjaman p = new Pinjaman();
        p.no = fieldNo.getText();
        p.nama = fieldNama.getText();
        p.jumlah = fieldJumlah.getText();
        p.lama     = Integer.parseInt(fieldLama.getText());
        p.bunga    = new BigDecimal(fieldBunga.getText());
        p.angsuran = currentAngsuran;
        p.total    = currentTotal;
        
        Object[] row = {
            p.no, p.nama, p.jumlah,
            p.lama, p.bunga, p.angsuran, p.total
        };
        tabel.insertRow(baris, row);
        allRender();
        
        resetForm();
        
        total();
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void buttonPerbaruiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPerbaruiActionPerformed
        // TODO add your handling code here:
        hitung();
        
        Pinjaman p = new Pinjaman();
        p.no = fieldNo.getText();
        p.nama = fieldNama.getText();
        p.jumlah = fieldJumlah.getText();
        p.lama     = Integer.parseInt(fieldLama.getText());
        p.bunga    = new BigDecimal(fieldBunga.getText());
        p.angsuran = currentAngsuran;
        p.total    = currentTotal;
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Yakin hapus data?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            tabel.removeRow(baris);
        }
        
        Object[] row = {
            p.no, p.nama, p.jumlah,
            p.lama, p.bunga, p.angsuran, p.total
        };
        tabel.insertRow(baris, row);
        allRender();
        
        resetForm();
        
        setModeTambah();
        total();
    }//GEN-LAST:event_buttonPerbaruiActionPerformed

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Yakin hapus data?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            tabel.removeRow(baris);
            allRender();
        }
        
        resetForm();
        
        total();
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        setModeTambah();
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void tabelModelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelModelMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            
        resetForm();
        
        baris = tabelModel.getSelectedRow();
        
        fieldNo.setText(tabel.getValueAt(baris, 0).toString());
        fieldNama.setText(tabel.getValueAt(baris, 1).toString());
        fieldJumlah.setText(tabel.getValueAt(baris, 2).toString());
        fieldLama.setText(tabel.getValueAt(baris, 3).toString());
        fieldBunga.setText(tabel.getValueAt(baris, 4).toString());
        fieldAngsuran.setText(tabel.getValueAt(baris, 5).toString());
        fieldTotal1.setText(tabel.getValueAt(baris, 6).toString());
        
        setModeEdit();
        }
    }//GEN-LAST:event_tabelModelMouseClicked

    private void resetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseClicked
        // TODO add your handling code here:
        resetForm();
    }//GEN-LAST:event_resetButtonMouseClicked

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
            java.util.logging.Logger.getLogger(TableModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TableModel().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonPerbarui;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JTextField fieldAngsuran;
    private javax.swing.JTextField fieldBunga;
    private javax.swing.JTextField fieldJumlah;
    private javax.swing.JTextField fieldLama;
    private javax.swing.JTextField fieldNama;
    private javax.swing.JTextField fieldNo;
    private javax.swing.JTextField fieldTotal1;
    private javax.swing.JTextField fieldTotal2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelError;
    private javax.swing.JLabel labelError1;
    private javax.swing.JLabel labelError2;
    private javax.swing.JLabel labelError3;
    private javax.swing.JButton resetButton;
    private javax.swing.JTable tabelModel;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pojekakhirbismillah;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author faruq
 */
public class mainView extends javax.swing.JFrame {
    DefaultTableModel model_TabelTransaksi;        
    private void tambahKeTabel() {
    String idObat = ID_ObatTextField.getText();
    String namaObat = (String) jComboBoxNamaObat.getSelectedItem();
    String ketersediaan = JCheckBoxTidaktersedia.isSelected() ? "Tidak Tersedia" : "Tersedia";
    int jumlah = (Integer) jSpinnerBanyakObat.getValue();
    String harga = TextFieldHargaObat.getText();

    DefaultTableModel model_TabelObat = (DefaultTableModel) jTable_Obat.getModel();
    model_TabelObat.addRow(new Object[]{idObat, namaObat, ketersediaan, jumlah, harga});
    }  
    String[] namaObatList = {"Paracetamol", "Amoxicillin", "Ibuprofen", "Cetirizine", "Omeprazole","Salbutamol", "Metformin", "Simvastatin", "Amlodipine", "Ranitidine"};

    private final int[] hargaObatList = {
        5000, 7000, 6000, 4500, 8000,
        7500, 6500, 9000, 8500, 7200
    };
    private void updateHarga() {
    int index = jComboBoxNamaObat.getSelectedIndex();
    int hargaSatuan = hargaObatList[index];
    int jumlah = (Integer) jSpinnerBanyakObat.getValue();
    int totalHarga = hargaSatuan * jumlah;
    TextFieldHargaObat.setText(String.valueOf(totalHarga));
        }
private void cariManagement() {
    String cariID = idRuanganManagementRuangan.getText().trim();
    DefaultTableModel model = (DefaultTableModel) tableManagementRuangan.getModel();
    
    for (int i = 0; i < model.getRowCount(); i++) {
        if (model.getValueAt(i, 0).toString().equalsIgnoreCase(cariID)) {
            tableManagementRuangan.setRowSelectionInterval(i, i);
            return;
        }
    }

    JOptionPane.showMessageDialog(this, "ID Rekam Medis tidak ditemukan.");
}

private void cariRekam() {
    String cariID = IDRekamMedisTextFieldRekamMedis.getText().trim();
    DefaultTableModel model = (DefaultTableModel) tableRekamMedis.getModel();
    
    for (int i = 0; i < model.getRowCount(); i++) {
        if (model.getValueAt(i, 0).toString().equalsIgnoreCase(cariID)) {
            tableRekamMedis.setRowSelectionInterval(i, i);
            return;
        }
    }

    JOptionPane.showMessageDialog(this, "ID Rekam Medis tidak ditemukan.");
}
private void tambahRuangan() {
    String id = idRuanganManagementRuangan.getText().trim();
    String tipe = jComboBoxTipeRuanganManagementRUangan.getSelectedItem().toString();
    String harga = tfHargaRuanganManagementRuangan.getText().trim();

    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(this, "ID Ruangan tidak boleh kosong!");
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tableManagementRuangan.getModel();
    model.addRow(new Object[]{id, tipe, harga});
}
    private void TambahRekam() {
        String id = IDRekamMedisTextFieldRekamMedis.getText().trim();
        String nama = tanggalTextFieldRekamMedis.getText().trim();
        String diagnosa = TextFieldDiagnosaRekamMedis.getText().trim();
        
        DefaultTableModel model = (DefaultTableModel) tableRekamMedis.getModel();
        model.addRow(new Object[]{id, nama, diagnosa});
    }
    public mainView() {
        initComponents();
        model_TabelTransaksi = (DefaultTableModel) JTableTransaksi.getModel();
        for (String nama : namaObatList) {jComboBoxNamaObat.addItem(nama);}
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        jSpinnerBanyakObat.setModel(spinnerModel);
        jSpinnerBanyakObat.addChangeListener(e -> updateHarga());
        btnCariRekamMedis.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        String cariID = IDRekamMedisTextFieldRekamMedis.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tableRekamMedis.getModel();

        boolean ditemukan = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equalsIgnoreCase(cariID)) {
                tableRekamMedis.setRowSelectionInterval(i, i); // pilih baris
                tableRekamMedis.scrollRectToVisible(tableRekamMedis.getCellRect(i +5 , 4, true)); // scroll ke sana
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            JOptionPane.showMessageDialog(null, "ID Ruangan tidak ditemukan.");
        }
        }
});
        DefaultTableModel model_tabel = new DefaultTableModel(
    new Object[]{"ID Rekam Medis", "Tanggal", "Diagnosa"}, 0);
    tableRekamMedis.setModel(model_tabel);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bodyPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menuRuangan = new javax.swing.JButton();
        menuDashboard = new javax.swing.JButton();
        menuDataPasien = new javax.swing.JButton();
        menuDataPegawai = new javax.swing.JButton();
        menuRekamMedis = new javax.swing.JButton();
        menuInventarisObat = new javax.swing.JButton();
        menuTransaksi = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        dashboardPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pasienPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtFieldIDPasien1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtFieldNamaPasien = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        txtFieldNoTelp = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtFieldUmur = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtFieldAlamat = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtFieldTgglMasuk = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtFieldTgglKeluar = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pegawaiPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtFieldIDPasien2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtFieldNamaPasien1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnHapus1 = new javax.swing.JButton();
        btnKembali1 = new javax.swing.JButton();
        rekamMedisPanel = new javax.swing.JPanel();
        NamelLabel7RekamMedis = new javax.swing.JLabel();
        NamelPanel9RekamMedis = new javax.swing.JPanel();
        Namelanel10 = new javax.swing.JPanel();
        idRekamMedisRekamMeidis = new javax.swing.JLabel();
        tanggalRekamMedis = new javax.swing.JLabel();
        diagnosaRekamMedis = new javax.swing.JLabel();
        tanggalTextFieldRekamMedis = new javax.swing.JTextField();
        TextFieldDiagnosaRekamMedis = new javax.swing.JTextField();
        IDRekamMedisTextFieldRekamMedis = new javax.swing.JTextField();
        btnCariRekamMedis = new javax.swing.JButton();
        btnTAMBAHRekamMedis = new javax.swing.JButton();
        btnHAPUSRekamMedis = new javax.swing.JButton();
        btNKEMBALIRekamMedis = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableRekamMedis = new javax.swing.JTable();
        obatPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        javax.swing.JPanel jPanelArkhan = new javax.swing.JPanel();
        javax.swing.JPanel jPanel10 = new javax.swing.JPanel();
        btnTambahObat = new javax.swing.JButton();
        btnHapusObat = new javax.swing.JButton();
        btNKembaliObat = new javax.swing.JButton();
        TextFieldHargaObat = new javax.swing.JTextField();
        ID_ObatTextField = new javax.swing.JTextField();
        javax.swing.JLabel idObat = new javax.swing.JLabel();
        javax.swing.JLabel Nama_Obat = new javax.swing.JLabel();
        javax.swing.JLabel Ketersediaan = new javax.swing.JLabel();
        btnCariObat3 = new javax.swing.JButton();
        javax.swing.JLabel Banyak_Obat = new javax.swing.JLabel();
        javax.swing.JLabel Harga_Obat = new javax.swing.JLabel();
        JCheckBoxTidaktersedia = new javax.swing.JCheckBox();
        jComboBoxNamaObat = new javax.swing.JComboBox<>();
        jSpinnerBanyakObat = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_Obat = new javax.swing.JTable();
        transaksiPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        javax.swing.JPanel jPanelArkhanTransaksi = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        Namelanel11 = new javax.swing.JPanel();
        javax.swing.JLabel idTransaksi = new javax.swing.JLabel();
        javax.swing.JLabel TanggalTransaksi = new javax.swing.JLabel();
        javax.swing.JLabel TotalTransaksi = new javax.swing.JLabel();
        JTextFieldTanggalTransaksi = new javax.swing.JTextField();
        JTextFieldTotalTransaksi = new javax.swing.JTextField();
        JTextFieldIDTransaksi = new javax.swing.JTextField();
        btnCariTransaksi = new javax.swing.JButton();
        btnTAMBAHTransaksi = new javax.swing.JButton();
        btnHAPUSTransaksi = new javax.swing.JButton();
        btNKEMBALITransaksi = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        JTableTransaksi = new javax.swing.JTable();
        ruanganPanel = new javax.swing.JPanel();
        namelLabel10ManagementRuangan = new javax.swing.JLabel();
        ManagementRuangan = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        NamelPanel9ManagementRuangan = new javax.swing.JPanel();
        Namelpanel11ManajementRuangan = new javax.swing.JPanel();
        idRuanganjlabelManagementRuangan = new javax.swing.JLabel();
        tipeRuanganManajementRuangan = new javax.swing.JLabel();
        hargaRuanganManagementRuangan = new javax.swing.JLabel();
        tfHargaRuanganManagementRuangan = new javax.swing.JTextField();
        idRuanganManagementRuangan = new javax.swing.JTextField();
        btnCariManagementRuangan = new javax.swing.JButton();
        btnTAMBAHManagementRuangan = new javax.swing.JButton();
        btnHAPUSManagementRuangan = new javax.swing.JButton();
        btNKEMBALIManagementRUangan = new javax.swing.JButton();
        jComboBoxTipeRuanganManagementRUangan = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableManagementRuangan = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bodyPanel.setBackground(new java.awt.Color(26, 188, 156));

        menuPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        menuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Rockwell Condensed", 1, 34)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo_RS.png"))); // NOI18N
        jLabel1.setText("RS Sehat Selalu");
        jLabel1.setIconTextGap(10);
        menuPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 70));

        jLabel2.setText("Sistem Administrasi");
        menuPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        menuRuangan.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        menuRuangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Ruangan.png"))); // NOI18N
        menuRuangan.setText("Ruangan");
        menuRuangan.setToolTipText("");
        menuRuangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuRuangan.setIconTextGap(20);
        menuRuangan.setVerifyInputWhenFocusTarget(false);
        menuRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRuanganActionPerformed(evt);
            }
        });
        menuPanel.add(menuRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 240, 60));

        menuDashboard.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        menuDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashboard.png"))); // NOI18N
        menuDashboard.setText("Dashboard");
        menuDashboard.setToolTipText("");
        menuDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuDashboard.setIconTextGap(20);
        menuDashboard.setVerifyInputWhenFocusTarget(false);
        menuDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDashboardMouseClicked(evt);
            }
        });
        menuDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDashboardActionPerformed(evt);
            }
        });
        menuPanel.add(menuDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 240, 60));

        menuDataPasien.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        menuDataPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Data_Pasien.png"))); // NOI18N
        menuDataPasien.setText("Data Pasien");
        menuDataPasien.setToolTipText("");
        menuDataPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuDataPasien.setIconTextGap(20);
        menuDataPasien.setVerifyInputWhenFocusTarget(false);
        menuDataPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDataPasienActionPerformed(evt);
            }
        });
        menuPanel.add(menuDataPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 240, 60));

        menuDataPegawai.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        menuDataPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Pegawai.png"))); // NOI18N
        menuDataPegawai.setText("Data Pegawai");
        menuDataPegawai.setToolTipText("");
        menuDataPegawai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuDataPegawai.setIconTextGap(20);
        menuDataPegawai.setVerifyInputWhenFocusTarget(false);
        menuDataPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDataPegawaiActionPerformed(evt);
            }
        });
        menuPanel.add(menuDataPegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 240, 60));

        menuRekamMedis.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        menuRekamMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Rekam Medis.png"))); // NOI18N
        menuRekamMedis.setText("Rekam Medis");
        menuRekamMedis.setToolTipText("");
        menuRekamMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuRekamMedis.setIconTextGap(20);
        menuRekamMedis.setVerifyInputWhenFocusTarget(false);
        menuRekamMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRekamMedisActionPerformed(evt);
            }
        });
        menuPanel.add(menuRekamMedis, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 240, 60));

        menuInventarisObat.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        menuInventarisObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Obat.png"))); // NOI18N
        menuInventarisObat.setText("Inventaris Obat");
        menuInventarisObat.setToolTipText("");
        menuInventarisObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuInventarisObat.setIconTextGap(20);
        menuInventarisObat.setVerifyInputWhenFocusTarget(false);
        menuInventarisObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInventarisObatActionPerformed(evt);
            }
        });
        menuPanel.add(menuInventarisObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 240, 60));

        menuTransaksi.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        menuTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/transaction.png"))); // NOI18N
        menuTransaksi.setText("Transaksi");
        menuTransaksi.setToolTipText("");
        menuTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuTransaksi.setIconTextGap(20);
        menuTransaksi.setVerifyInputWhenFocusTarget(false);
        menuTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTransaksiActionPerformed(evt);
            }
        });
        menuPanel.add(menuTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 240, 60));

        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        mainPanel.setLayout(new java.awt.CardLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel4.setText("Dashboard");
        jLabel4.setIconTextGap(10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 422, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(828, Short.MAX_VALUE))
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(700, Short.MAX_VALUE))
        );

        mainPanel.add(dashboardPanel, "card2");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel5.setText("Data Pasien");
        jLabel5.setIconTextGap(10);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel11.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel11.setText("ID Pasien");

        jLabel12.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel12.setText("Nama Pasien");

        jLabel13.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel13.setText("Jenis Kelamin");

        jRadioButton1.setText("Laki - Laki");

        jRadioButton2.setText("Perempuan");

        jLabel14.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel14.setText("Umur");

        jLabel15.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel15.setText("No. Telepon");

        jLabel16.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel16.setText("Alamat");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Cari");
        jButton1.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel17.setText("Tanggal Masuk");

        jLabel18.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel18.setText("Tanggal Keluar");

        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFieldUmur)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtFieldAlamat)
                                    .addComponent(txtFieldNamaPasien)
                                    .addComponent(txtFieldIDPasien1)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(46, 46, 46)
                                .addComponent(txtFieldNoTelp)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFieldTgglMasuk))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFieldTgglKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 690, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtFieldIDPasien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtFieldNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtFieldUmur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtFieldAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldTgglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtFieldTgglKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pasien", "Nama", "Jenis Kelamin", "Umur", "Alamat", "No. Telepon", "Tanggal Masuk", "Tanggal Keluar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pasienPanelLayout = new javax.swing.GroupLayout(pasienPanel);
        pasienPanel.setLayout(pasienPanelLayout);
        pasienPanelLayout.setHorizontalGroup(
            pasienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pasienPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pasienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pasienPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pasienPanelLayout.setVerticalGroup(
            pasienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pasienPanelLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainPanel.add(pasienPanel, "card3");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel6.setText("Data Pegawai");
        jLabel6.setIconTextGap(10);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel19.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel19.setText("ID Pegawai");

        jLabel20.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel20.setText("Nama Pasien");

        jLabel21.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel21.setText("Bagian Pegawai");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Cari");
        jButton2.setToolTipText("");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Dokter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Admin");

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Perawat");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldNamaPasien1)
                            .addComponent(txtFieldIDPasien2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 710, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtFieldIDPasien2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtFieldNamaPasien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel22.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel22.setText("Bertugas");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(282, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(252, 252, 252))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1106, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        btnHapus1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus1.setText("HAPUS");
        btnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapus1ActionPerformed(evt);
            }
        });

        btnKembali1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKembali1.setText("KEMBALI");
        btnKembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembali1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pegawaiPanelLayout = new javax.swing.GroupLayout(pegawaiPanel);
        pegawaiPanel.setLayout(pegawaiPanelLayout);
        pegawaiPanelLayout.setHorizontalGroup(
            pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pegawaiPanelLayout.createSequentialGroup()
                .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pegawaiPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pegawaiPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pegawaiPanelLayout.createSequentialGroup()
                                .addComponent(btnHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(123, Short.MAX_VALUE))
            .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pegawaiPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pegawaiPanelLayout.setVerticalGroup(
            pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pegawaiPanelLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(163, 163, 163)
                .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 326, Short.MAX_VALUE))
            .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pegawaiPanelLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );

        mainPanel.add(pegawaiPanel, "card4");

        rekamMedisPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        NamelLabel7RekamMedis.setBackground(new java.awt.Color(255, 255, 255));
        NamelLabel7RekamMedis.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        NamelLabel7RekamMedis.setText("Rekam Medis");
        NamelLabel7RekamMedis.setIconTextGap(10);

        NamelPanel9RekamMedis.setBackground(new java.awt.Color(0, 153, 153));

        Namelanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Namelanel10.setForeground(new java.awt.Color(255, 255, 255));

        idRekamMedisRekamMeidis.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        idRekamMedisRekamMeidis.setText("ID Rekam Medis");

        tanggalRekamMedis.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        tanggalRekamMedis.setText("Tanggal");

        diagnosaRekamMedis.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        diagnosaRekamMedis.setText("Diagnosa");

        tanggalTextFieldRekamMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalTextFieldRekamMedisActionPerformed(evt);
            }
        });

        TextFieldDiagnosaRekamMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldDiagnosaRekamMedisActionPerformed(evt);
            }
        });

        IDRekamMedisTextFieldRekamMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDRekamMedisTextFieldRekamMedisActionPerformed(evt);
            }
        });

        btnCariRekamMedis.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCariRekamMedis.setText("cari");
        btnCariRekamMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariRekamMedisActionPerformed(evt);
            }
        });

        btnTAMBAHRekamMedis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTAMBAHRekamMedis.setText("TAMBAH");
        btnTAMBAHRekamMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTAMBAHRekamMedisActionPerformed(evt);
            }
        });

        btnHAPUSRekamMedis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHAPUSRekamMedis.setText("HAPUS");
        btnHAPUSRekamMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHAPUSRekamMedisActionPerformed(evt);
            }
        });

        btNKEMBALIRekamMedis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btNKEMBALIRekamMedis.setText("KEMBALI");
        btNKEMBALIRekamMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNKEMBALIRekamMedisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Namelanel10Layout = new javax.swing.GroupLayout(Namelanel10);
        Namelanel10.setLayout(Namelanel10Layout);
        Namelanel10Layout.setHorizontalGroup(
            Namelanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Namelanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Namelanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Namelanel10Layout.createSequentialGroup()
                        .addGroup(Namelanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idRekamMedisRekamMeidis)
                            .addComponent(tanggalRekamMedis)
                            .addComponent(diagnosaRekamMedis))
                        .addGap(18, 18, 18)
                        .addGroup(Namelanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IDRekamMedisTextFieldRekamMedis)
                            .addComponent(tanggalTextFieldRekamMedis)
                            .addComponent(TextFieldDiagnosaRekamMedis))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariRekamMedis))
                    .addGroup(Namelanel10Layout.createSequentialGroup()
                        .addComponent(btnTAMBAHRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHAPUSRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btNKEMBALIRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Namelanel10Layout.setVerticalGroup(
            Namelanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Namelanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(Namelanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idRekamMedisRekamMeidis)
                    .addComponent(IDRekamMedisTextFieldRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Namelanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tanggalTextFieldRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tanggalRekamMedis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Namelanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diagnosaRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldDiagnosaRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Namelanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTAMBAHRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHAPUSRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNKEMBALIRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableRekamMedis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Rekam Medis", "Tanggal", "Diagnosa"
            }
        ));
        jScrollPane2.setViewportView(tableRekamMedis);

        javax.swing.GroupLayout NamelPanel9RekamMedisLayout = new javax.swing.GroupLayout(NamelPanel9RekamMedis);
        NamelPanel9RekamMedis.setLayout(NamelPanel9RekamMedisLayout);
        NamelPanel9RekamMedisLayout.setHorizontalGroup(
            NamelPanel9RekamMedisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NamelPanel9RekamMedisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NamelPanel9RekamMedisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Namelanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        NamelPanel9RekamMedisLayout.setVerticalGroup(
            NamelPanel9RekamMedisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NamelPanel9RekamMedisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Namelanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout rekamMedisPanelLayout = new javax.swing.GroupLayout(rekamMedisPanel);
        rekamMedisPanel.setLayout(rekamMedisPanelLayout);
        rekamMedisPanelLayout.setHorizontalGroup(
            rekamMedisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rekamMedisPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rekamMedisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rekamMedisPanelLayout.createSequentialGroup()
                        .addComponent(NamelLabel7RekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1007, Short.MAX_VALUE))
                    .addComponent(NamelPanel9RekamMedis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        rekamMedisPanelLayout.setVerticalGroup(
            rekamMedisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rekamMedisPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NamelLabel7RekamMedis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NamelPanel9RekamMedis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(rekamMedisPanel, "card5");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel8.setText("Inventaris Obat");
        jLabel8.setIconTextGap(10);

        jPanelArkhan.setBackground(new java.awt.Color(0, 153, 153));

        btnTambahObat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambahObat.setText("TAMBAH");
        btnTambahObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahObatActionPerformed(evt);
            }
        });

        btnHapusObat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapusObat.setText("HAPUS");
        btnHapusObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusObatActionPerformed(evt);
            }
        });

        btNKembaliObat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btNKembaliObat.setText("KEMBALI");
        btNKembaliObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNKembaliObatActionPerformed(evt);
            }
        });

        TextFieldHargaObat.setEditable(false);
        TextFieldHargaObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldHargaObatActionPerformed(evt);
            }
        });

        ID_ObatTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_ObatTextFieldActionPerformed(evt);
            }
        });

        idObat.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        idObat.setText("ID Obat");

        Nama_Obat.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        Nama_Obat.setText("Nama Obat");

        Ketersediaan.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        Ketersediaan.setText("Ketersediaan");

        btnCariObat3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCariObat3.setText("cari");
        btnCariObat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariObat3ActionPerformed(evt);
            }
        });

        Banyak_Obat.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        Banyak_Obat.setText("Banyak Obat");

        Harga_Obat.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        Harga_Obat.setText("Harga Obat");

        JCheckBoxTidaktersedia.setText("Tidak Tersedia");
        JCheckBoxTidaktersedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCheckBoxTidaktersediaActionPerformed(evt);
            }
        });

        jComboBoxNamaObat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboBoxNamaObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNamaObatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idObat)
                            .addComponent(Nama_Obat)
                            .addComponent(Ketersediaan)
                            .addComponent(Harga_Obat))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(ID_ObatTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariObat3))
                            .addComponent(TextFieldHargaObat)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JCheckBoxTidaktersedia)
                                    .addComponent(jComboBoxNamaObat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(btnTambahObat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapusObat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btNKembaliObat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(Banyak_Obat)
                                .addGap(18, 18, 18)
                                .addComponent(jSpinnerBanyakObat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 710, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idObat)
                    .addComponent(ID_ObatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariObat3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nama_Obat)
                    .addComponent(jComboBoxNamaObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ketersediaan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCheckBoxTidaktersedia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Banyak_Obat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerBanyakObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(Harga_Obat, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambahObat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHapusObat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btNKembaliObat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(TextFieldHargaObat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTable_Obat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Obat", "Nama Obat", "Ketersediaan", "Banyak Obat", "Harga Obat"
            }
        ));
        jScrollPane3.setViewportView(jTable_Obat);
        if (jTable_Obat.getColumnModel().getColumnCount() > 0) {
            jTable_Obat.getColumnModel().getColumn(0).setMinWidth(100);
            jTable_Obat.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable_Obat.getColumnModel().getColumn(0).setMaxWidth(100);
            jTable_Obat.getColumnModel().getColumn(1).setResizable(false);
            jTable_Obat.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable_Obat.getColumnModel().getColumn(2).setResizable(false);
            jTable_Obat.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable_Obat.getColumnModel().getColumn(3).setResizable(false);
            jTable_Obat.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTable_Obat.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanelArkhanLayout = new javax.swing.GroupLayout(jPanelArkhan);
        jPanelArkhan.setLayout(jPanelArkhanLayout);
        jPanelArkhanLayout.setHorizontalGroup(
            jPanelArkhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelArkhanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelArkhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanelArkhanLayout.setVerticalGroup(
            jPanelArkhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelArkhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout obatPanelLayout = new javax.swing.GroupLayout(obatPanel);
        obatPanel.setLayout(obatPanelLayout);
        obatPanelLayout.setHorizontalGroup(
            obatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(obatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(obatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(obatPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelArkhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        obatPanelLayout.setVerticalGroup(
            obatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(obatPanelLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelArkhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainPanel.add(obatPanel, "card6");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel9.setText("Menu Transaksi");
        jLabel9.setIconTextGap(10);

        jPanelArkhanTransaksi.setBackground(new java.awt.Color(0, 153, 153));

        Namelanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Namelanel11.setForeground(new java.awt.Color(255, 255, 255));

        idTransaksi.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        idTransaksi.setText("ID Transaksi");

        TanggalTransaksi.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        TanggalTransaksi.setText("Tanggaltransaksi");

        TotalTransaksi.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        TotalTransaksi.setText("Total Transaksi");

        JTextFieldTanggalTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextFieldTanggalTransaksiActionPerformed(evt);
            }
        });

        JTextFieldTotalTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextFieldTotalTransaksiActionPerformed(evt);
            }
        });

        JTextFieldIDTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextFieldIDTransaksiActionPerformed(evt);
            }
        });

        btnCariTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCariTransaksi.setText("cari");
        btnCariTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariTransaksiActionPerformed(evt);
            }
        });

        btnTAMBAHTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTAMBAHTransaksi.setText("TAMBAH");
        btnTAMBAHTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTAMBAHTransaksiActionPerformed(evt);
            }
        });

        btnHAPUSTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHAPUSTransaksi.setText("HAPUS");
        btnHAPUSTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHAPUSTransaksiActionPerformed(evt);
            }
        });

        btNKEMBALITransaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btNKEMBALITransaksi.setText("KEMBALI");
        btNKEMBALITransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNKEMBALITransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Namelanel11Layout = new javax.swing.GroupLayout(Namelanel11);
        Namelanel11.setLayout(Namelanel11Layout);
        Namelanel11Layout.setHorizontalGroup(
            Namelanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Namelanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Namelanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Namelanel11Layout.createSequentialGroup()
                        .addGroup(Namelanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idTransaksi)
                            .addComponent(TanggalTransaksi)
                            .addComponent(TotalTransaksi))
                        .addGap(18, 18, 18)
                        .addGroup(Namelanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTextFieldIDTransaksi)
                            .addComponent(JTextFieldTanggalTransaksi)
                            .addComponent(JTextFieldTotalTransaksi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariTransaksi))
                    .addGroup(Namelanel11Layout.createSequentialGroup()
                        .addComponent(btnTAMBAHTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHAPUSTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btNKEMBALITransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Namelanel11Layout.setVerticalGroup(
            Namelanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Namelanel11Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(Namelanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idTransaksi)
                    .addComponent(JTextFieldIDTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Namelanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTextFieldTanggalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TanggalTransaksi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Namelanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTextFieldTotalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Namelanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTAMBAHTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHAPUSTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNKEMBALITransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Namelanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Namelanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        JTableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Transaksi", "Tanggal Transaksi", "Total Transaksi"
            }
        ));
        jScrollPane5.setViewportView(JTableTransaksi);
        if (JTableTransaksi.getColumnModel().getColumnCount() > 0) {
            JTableTransaksi.getColumnModel().getColumn(0).setMinWidth(100);
            JTableTransaksi.getColumnModel().getColumn(0).setPreferredWidth(20);
            JTableTransaksi.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanelArkhanTransaksiLayout = new javax.swing.GroupLayout(jPanelArkhanTransaksi);
        jPanelArkhanTransaksi.setLayout(jPanelArkhanTransaksiLayout);
        jPanelArkhanTransaksiLayout.setHorizontalGroup(
            jPanelArkhanTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelArkhanTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelArkhanTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1232, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelArkhanTransaksiLayout.setVerticalGroup(
            jPanelArkhanTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelArkhanTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout transaksiPanelLayout = new javax.swing.GroupLayout(transaksiPanel);
        transaksiPanel.setLayout(transaksiPanelLayout);
        transaksiPanelLayout.setHorizontalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelArkhanTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        transaksiPanelLayout.setVerticalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelArkhanTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainPanel.add(transaksiPanel, "card7");

        namelLabel10ManagementRuangan.setBackground(new java.awt.Color(255, 255, 255));
        namelLabel10ManagementRuangan.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        namelLabel10ManagementRuangan.setText("Management Ruangan");
        namelLabel10ManagementRuangan.setIconTextGap(10);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ManagementRuanganLayout = new javax.swing.GroupLayout(ManagementRuangan);
        ManagementRuangan.setLayout(ManagementRuanganLayout);
        ManagementRuanganLayout.setHorizontalGroup(
            ManagementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ManagementRuanganLayout.setVerticalGroup(
            ManagementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        NamelPanel9ManagementRuangan.setBackground(new java.awt.Color(0, 153, 153));

        idRuanganjlabelManagementRuangan.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        idRuanganjlabelManagementRuangan.setText("ID Ruangan");

        tipeRuanganManajementRuangan.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        tipeRuanganManajementRuangan.setText("Tipe Ruangan");

        hargaRuanganManagementRuangan.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        hargaRuanganManagementRuangan.setText("Harga Ruangan");

        tfHargaRuanganManagementRuangan.setEditable(false);
        tfHargaRuanganManagementRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaRuanganManagementRuanganActionPerformed(evt);
            }
        });

        idRuanganManagementRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idRuanganManagementRuanganActionPerformed(evt);
            }
        });

        btnCariManagementRuangan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCariManagementRuangan.setText("cari");
        btnCariManagementRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariManagementRuanganActionPerformed(evt);
            }
        });

        btnTAMBAHManagementRuangan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTAMBAHManagementRuangan.setText("TAMBAH");
        btnTAMBAHManagementRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTAMBAHManagementRuanganActionPerformed(evt);
            }
        });

        btnHAPUSManagementRuangan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHAPUSManagementRuangan.setText("HAPUS");
        btnHAPUSManagementRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHAPUSManagementRuanganActionPerformed(evt);
            }
        });

        btNKEMBALIManagementRUangan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btNKEMBALIManagementRUangan.setText("KEMBALI");
        btNKEMBALIManagementRUangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNKEMBALIManagementRUanganActionPerformed(evt);
            }
        });

        jComboBoxTipeRuanganManagementRUangan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxTipeRuanganManagementRUangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UMUM", "REGULER", "VIP" }));
        jComboBoxTipeRuanganManagementRUangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipeRuanganManagementRUanganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Namelpanel11ManajementRuanganLayout = new javax.swing.GroupLayout(Namelpanel11ManajementRuangan);
        Namelpanel11ManajementRuangan.setLayout(Namelpanel11ManajementRuanganLayout);
        Namelpanel11ManajementRuanganLayout.setHorizontalGroup(
            Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Namelpanel11ManajementRuanganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Namelpanel11ManajementRuanganLayout.createSequentialGroup()
                        .addGroup(Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idRuanganjlabelManagementRuangan)
                            .addComponent(tipeRuanganManajementRuangan)
                            .addComponent(hargaRuanganManagementRuangan))
                        .addGap(18, 18, 18)
                        .addGroup(Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Namelpanel11ManajementRuanganLayout.createSequentialGroup()
                                .addGroup(Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idRuanganManagementRuangan)
                                    .addGroup(Namelpanel11ManajementRuanganLayout.createSequentialGroup()
                                        .addComponent(tfHargaRuanganManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariManagementRuangan))
                            .addGroup(Namelpanel11ManajementRuanganLayout.createSequentialGroup()
                                .addComponent(jComboBoxTipeRuanganManagementRUangan, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(Namelpanel11ManajementRuanganLayout.createSequentialGroup()
                        .addComponent(btnTAMBAHManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHAPUSManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btNKEMBALIManagementRUangan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 436, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Namelpanel11ManajementRuanganLayout.setVerticalGroup(
            Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Namelpanel11ManajementRuanganLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idRuanganjlabelManagementRuangan)
                    .addComponent(idRuanganManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipeRuanganManajementRuangan)
                    .addComponent(jComboBoxTipeRuanganManagementRUangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hargaRuanganManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfHargaRuanganManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Namelpanel11ManajementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTAMBAHManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHAPUSManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNKEMBALIManagementRUangan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tableManagementRuangan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Ruangan", "Tipe Ruangan", "Harga Ruangan"
            }
        ));
        jScrollPane4.setViewportView(tableManagementRuangan);

        javax.swing.GroupLayout NamelPanel9ManagementRuanganLayout = new javax.swing.GroupLayout(NamelPanel9ManagementRuangan);
        NamelPanel9ManagementRuangan.setLayout(NamelPanel9ManagementRuanganLayout);
        NamelPanel9ManagementRuanganLayout.setHorizontalGroup(
            NamelPanel9ManagementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NamelPanel9ManagementRuanganLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(NamelPanel9ManagementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(Namelpanel11ManajementRuangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        NamelPanel9ManagementRuanganLayout.setVerticalGroup(
            NamelPanel9ManagementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NamelPanel9ManagementRuanganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Namelpanel11ManajementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        javax.swing.GroupLayout ruanganPanelLayout = new javax.swing.GroupLayout(ruanganPanel);
        ruanganPanel.setLayout(ruanganPanelLayout);
        ruanganPanelLayout.setHorizontalGroup(
            ruanganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ruanganPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ruanganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ruanganPanelLayout.createSequentialGroup()
                        .addComponent(namelLabel10ManagementRuangan)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ruanganPanelLayout.createSequentialGroup()
                        .addComponent(NamelPanel9ManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214)
                        .addComponent(ManagementRuangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        ruanganPanelLayout.setVerticalGroup(
            ruanganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(ruanganPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(ManagementRuangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ruanganPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(namelLabel10ManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NamelPanel9ManagementRuangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainPanel.add(ruanganPanel, "card8");

        jLabel3.setFont(new java.awt.Font("Rockwell Condensed", 1, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/call.png"))); // NOI18N
        jLabel3.setText("119");
        mainPanel.add(jLabel3, "card9");

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDashboardActionPerformed
        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        //add panel
        mainPanel.add(dashboardPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_menuDashboardActionPerformed

    private void menuDataPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDataPasienActionPerformed
          //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        //add panel
        mainPanel.add(pasienPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_menuDataPasienActionPerformed

    private void menuDataPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDataPegawaiActionPerformed
         //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        //add panel
        mainPanel.add(pegawaiPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_menuDataPegawaiActionPerformed

    private void menuRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRekamMedisActionPerformed
         //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        //add panel
        mainPanel.add(rekamMedisPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_menuRekamMedisActionPerformed

    private void menuInventarisObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInventarisObatActionPerformed
         //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        //add panel
        mainPanel.add(obatPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_menuInventarisObatActionPerformed

    private void menuTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTransaksiActionPerformed
         //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        //add panel
        mainPanel.add(transaksiPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_menuTransaksiActionPerformed

    private void menuRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRuanganActionPerformed
         //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        //add panel
        mainPanel.add(ruanganPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_menuRuanganActionPerformed

    private void menuDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDashboardMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menuDashboardMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHapus1ActionPerformed

    private void btnKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembali1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKembali1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tanggalTextFieldRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalTextFieldRekamMedisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalTextFieldRekamMedisActionPerformed

    private void TextFieldDiagnosaRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldDiagnosaRekamMedisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldDiagnosaRekamMedisActionPerformed

    private void IDRekamMedisTextFieldRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDRekamMedisTextFieldRekamMedisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDRekamMedisTextFieldRekamMedisActionPerformed

    private void btnCariRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariRekamMedisActionPerformed
    btnCariRekamMedis.addActionListener(e -> cariRekam());        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariRekamMedisActionPerformed

    private void btnTAMBAHRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTAMBAHRekamMedisActionPerformed
        TambahRekam();
            // TODO add your handling code here:
    }//GEN-LAST:event_btnTAMBAHRekamMedisActionPerformed

    private void btnHAPUSRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHAPUSRekamMedisActionPerformed
    int selectedRow = tableRekamMedis.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tableRekamMedis.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
        }
        // TODO add your handling de here:
    }//GEN-LAST:event_btnHAPUSRekamMedisActionPerformed

    private void btNKEMBALIRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNKEMBALIRekamMedisActionPerformed
    mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        mainPanel.add(dashboardPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btNKEMBALIRekamMedisActionPerformed

    private void tfHargaRuanganManagementRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaRuanganManagementRuanganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaRuanganManagementRuanganActionPerformed

    private void idRuanganManagementRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idRuanganManagementRuanganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idRuanganManagementRuanganActionPerformed

    private void btnCariManagementRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariManagementRuanganActionPerformed
        btnCariManagementRuangan.addActionListener(e -> cariManagement());        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariManagementRuanganActionPerformed

    private void btnTAMBAHManagementRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTAMBAHManagementRuanganActionPerformed
    tambahRuangan();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTAMBAHManagementRuanganActionPerformed

    private void btnHAPUSManagementRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHAPUSManagementRuanganActionPerformed
    int selectedRow = tableManagementRuangan.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tableManagementRuangan.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
        }     // TODO add your handling code here:
    }//GEN-LAST:event_btnHAPUSManagementRuanganActionPerformed

    private void btNKEMBALIManagementRUanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNKEMBALIManagementRUanganActionPerformed
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        mainPanel.add(dashboardPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btNKEMBALIManagementRUanganActionPerformed

    private void jComboBoxTipeRuanganManagementRUanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipeRuanganManagementRUanganActionPerformed

    String tipe = jComboBoxTipeRuanganManagementRUangan.getSelectedItem().toString();

    switch (tipe) {
        case "UMUM":
            tfHargaRuanganManagementRuangan.setText("Rp200.000");
            break;
        case "REGULER":
            tfHargaRuanganManagementRuangan.setText("Rp500.000");
            break;
        case "VIP":
            tfHargaRuanganManagementRuangan.setText("Rp1.000.000");
            break;
        default:
            tfHargaRuanganManagementRuangan.setText("");
            break;
    }
    // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipeRuanganManagementRUanganActionPerformed

    private void btnTambahObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahObatActionPerformed
        
        String id = ID_ObatTextField.getText().trim();
        String nama = (String) jComboBoxNamaObat.getSelectedItem();
        boolean tidakTersedia = JCheckBoxTidaktersedia.isSelected();
        int jumlah = tidakTersedia ? 0 : (Integer) jSpinnerBanyakObat.getValue();
        String harga = TextFieldHargaObat.getText().trim();


        DefaultTableModel model_TambahinObat = (DefaultTableModel) jTable_Obat.getModel();
        model_TambahinObat.addRow(new Object[]{id, nama, tidakTersedia ? "Tidak Tersedia" : "Tersedia", jumlah, harga});
        ID_ObatTextField.setText("");
    }//GEN-LAST:event_btnTambahObatActionPerformed

    private void btnHapusObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusObatActionPerformed
        btnHapusObat.addActionListener(e -> {
        int selectedRow = jTable_Obat.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) jTable_Obat.getModel();
                    model.removeRow(selectedRow);
                } else {
                JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus terlebih dahulu.");
            }
        });
    }//GEN-LAST:event_btnHapusObatActionPerformed

    private void btNKembaliObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNKembaliObatActionPerformed
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        mainPanel.add(dashboardPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btNKembaliObatActionPerformed

    private void TextFieldHargaObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldHargaObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldHargaObatActionPerformed

    private void ID_ObatTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_ObatTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID_ObatTextFieldActionPerformed

    private void btnCariObat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariObat3ActionPerformed
        btnCariObat3.addActionListener(e -> {
        String idCari = ID_ObatTextField.getText().trim();
        DefaultTableModel model = (DefaultTableModel) jTable_Obat.getModel();
        boolean ditemukan = false;


        for (int i = 0; i < model.getRowCount(); i++) {
            String idTabel = model.getValueAt(i, 0).toString();
            if (idTabel.equalsIgnoreCase(idCari)) {
                jTable_Obat.setRowSelectionInterval(i, i);
                jTable_Obat.scrollRectToVisible(jTable_Obat.getCellRect(i, 0, true));
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            JOptionPane.showMessageDialog(null, "ID Obat tidak ditemukan di tabel.");
        }
});
    }//GEN-LAST:event_btnCariObat3ActionPerformed

    private void jComboBoxNamaObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNamaObatActionPerformed
        jComboBoxNamaObat.addActionListener(e -> updateHarga());
    }//GEN-LAST:event_jComboBoxNamaObatActionPerformed

    private void JCheckBoxTidaktersediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCheckBoxTidaktersediaActionPerformed
        JCheckBoxTidaktersedia.addActionListener(e -> {
    boolean tidakTersedia = JCheckBoxTidaktersedia.isSelected();
    
    jSpinnerBanyakObat.setEnabled(!tidakTersedia);
    TextFieldHargaObat.setEnabled(!tidakTersedia); // <- blokir harga juga

    if (tidakTersedia) {
        TextFieldHargaObat.setText("0");
    } else {
        updateHarga();
    }
});
    }//GEN-LAST:event_JCheckBoxTidaktersediaActionPerformed

    private void JTextFieldTanggalTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextFieldTanggalTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextFieldTanggalTransaksiActionPerformed

    private void JTextFieldTotalTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextFieldTotalTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextFieldTotalTransaksiActionPerformed

    private void JTextFieldIDTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextFieldIDTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextFieldIDTransaksiActionPerformed

    private void btnCariTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariTransaksiActionPerformed
    String idCari = JTextFieldIDTransaksi.getText().trim();
    if (idCari.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Masukkan ID Transaksi yang ingin dicari.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    boolean ditemukan = false;
    for (int i = 0; i < model_TabelTransaksi.getRowCount(); i++) {
        if (model_TabelTransaksi.getValueAt(i, 0).toString().equalsIgnoreCase(idCari)) {
            JTableTransaksi.setRowSelectionInterval(i, i);
            JTableTransaksi.scrollRectToVisible(JTableTransaksi.getCellRect(i, 0, true));
            ditemukan = true;
            break;
        }
    }

    if (!ditemukan) {
        JOptionPane.showMessageDialog(this, "ID Transaksi tidak ditemukan.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_btnCariTransaksiActionPerformed

    private void btnTAMBAHTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTAMBAHTransaksiActionPerformed
    String id = JTextFieldIDTransaksi.getText().trim();
    String tanggal = JTextFieldTanggalTransaksi.getText().trim();
    String total = JTextFieldTotalTransaksi.getText().trim();

    if (id.isEmpty() || tanggal.isEmpty() || total.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Harap isi semua field.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    model_TabelTransaksi.addRow(new Object[]{id, tanggal, total});

    JTextFieldIDTransaksi.setText("");
    JTextFieldTanggalTransaksi.setText("");
    JTextFieldTotalTransaksi.setText("");
    }//GEN-LAST:event_btnTAMBAHTransaksiActionPerformed

    private void btnHAPUSTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHAPUSTransaksiActionPerformed
    int selectedRow = JTableTransaksi.getSelectedRow();
    if (selectedRow != -1) {
        model_TabelTransaksi.removeRow(selectedRow);
    } else {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnHAPUSTransaksiActionPerformed

    private void btNKEMBALITransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNKEMBALITransaksiActionPerformed
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        mainPanel.add(dashboardPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btNKEMBALITransaksiActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDRekamMedisTextFieldRekamMedis;
    private javax.swing.JTextField ID_ObatTextField;
    private javax.swing.JCheckBox JCheckBoxTidaktersedia;
    private javax.swing.JTable JTableTransaksi;
    private javax.swing.JTextField JTextFieldIDTransaksi;
    private javax.swing.JTextField JTextFieldTanggalTransaksi;
    private javax.swing.JTextField JTextFieldTotalTransaksi;
    private javax.swing.JPanel ManagementRuangan;
    private javax.swing.JLabel NamelLabel7RekamMedis;
    private javax.swing.JPanel NamelPanel9ManagementRuangan;
    private javax.swing.JPanel NamelPanel9RekamMedis;
    private javax.swing.JPanel Namelanel10;
    private javax.swing.JPanel Namelanel11;
    private javax.swing.JPanel Namelpanel11ManajementRuangan;
    private javax.swing.JTextField TextFieldDiagnosaRekamMedis;
    private javax.swing.JTextField TextFieldHargaObat;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btNKEMBALIManagementRUangan;
    private javax.swing.JButton btNKEMBALIRekamMedis;
    private javax.swing.JButton btNKEMBALITransaksi;
    private javax.swing.JButton btNKembaliObat;
    private javax.swing.JButton btnCariManagementRuangan;
    private javax.swing.JButton btnCariObat3;
    private javax.swing.JButton btnCariRekamMedis;
    private javax.swing.JButton btnCariTransaksi;
    private javax.swing.JButton btnHAPUSManagementRuangan;
    private javax.swing.JButton btnHAPUSRekamMedis;
    private javax.swing.JButton btnHAPUSTransaksi;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapus1;
    private javax.swing.JButton btnHapusObat;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnKembali1;
    private javax.swing.JButton btnTAMBAHManagementRuangan;
    private javax.swing.JButton btnTAMBAHRekamMedis;
    private javax.swing.JButton btnTAMBAHTransaksi;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTambahObat;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JLabel diagnosaRekamMedis;
    private javax.swing.JLabel hargaRuanganManagementRuangan;
    private javax.swing.JLabel idRekamMedisRekamMeidis;
    private javax.swing.JTextField idRuanganManagementRuangan;
    private javax.swing.JLabel idRuanganjlabelManagementRuangan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBoxNamaObat;
    private javax.swing.JComboBox<String> jComboBoxTipeRuanganManagementRUangan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinnerBanyakObat;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_Obat;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton menuDashboard;
    private javax.swing.JButton menuDataPasien;
    private javax.swing.JButton menuDataPegawai;
    private javax.swing.JButton menuInventarisObat;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton menuRekamMedis;
    private javax.swing.JButton menuRuangan;
    private javax.swing.JButton menuTransaksi;
    private javax.swing.JLabel namelLabel10ManagementRuangan;
    private javax.swing.JPanel obatPanel;
    private javax.swing.JPanel pasienPanel;
    private javax.swing.JPanel pegawaiPanel;
    private javax.swing.JPanel rekamMedisPanel;
    private javax.swing.JPanel ruanganPanel;
    protected javax.swing.JTable tableManagementRuangan;
    protected javax.swing.JTable tableRekamMedis;
    private javax.swing.JLabel tanggalRekamMedis;
    private javax.swing.JTextField tanggalTextFieldRekamMedis;
    private javax.swing.JTextField tfHargaRuanganManagementRuangan;
    private javax.swing.JLabel tipeRuanganManajementRuangan;
    private javax.swing.JPanel transaksiPanel;
    private javax.swing.JTextField txtFieldAlamat;
    private javax.swing.JTextField txtFieldIDPasien1;
    private javax.swing.JTextField txtFieldIDPasien2;
    private javax.swing.JTextField txtFieldNamaPasien;
    private javax.swing.JTextField txtFieldNamaPasien1;
    private javax.swing.JTextField txtFieldNoTelp;
    private javax.swing.JTextField txtFieldTgglKeluar;
    private javax.swing.JTextField txtFieldTgglMasuk;
    private javax.swing.JTextField txtFieldUmur;
    // End of variables declaration//GEN-END:variables
}

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
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author faruq
 */
public class mainView extends javax.swing.JFrame {

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
        String tanggal = tanggalTextFieldRekamMedis.getText().trim();
        String diagnosa = TextFieldDiagnosaRekamMedis.getText().trim();

        // Add to JTable
        DefaultTableModel model = (DefaultTableModel) tableRekamMedis.getModel();
        model.addRow(new Object[]{id, tanggal, diagnosa});

        // Insert into database
        String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
        String user = "naila01"; // replace this
        String password = "root"; // replace this

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO RekamMedis (ID_REKAM_MEDIS, TANGGAL, DIAGNOSA) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, tanggal);
            stmt.setString(3, diagnosa);
            stmt.executeUpdate();
            System.out.println("Data berhasil disimpan ke database.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menyimpan ke database: " + e.getMessage());
        }

    }

    public mainView() {
        initComponents();
        btnCariRekamMedis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String cariID = IDRekamMedisTextFieldRekamMedis.getText().trim();
                DefaultTableModel model = (DefaultTableModel) tableRekamMedis.getModel();

                boolean ditemukan = false;

                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).toString().equalsIgnoreCase(cariID)) {
                        tableRekamMedis.setRowSelectionInterval(i, i); // pilih baris
                        tableRekamMedis.scrollRectToVisible(tableRekamMedis.getCellRect(i + 5, 4, true)); // scroll ke sana
                        ditemukan = true;
                        break;
                    }
                }

                if (!ditemukan) {
                    JOptionPane.showMessageDialog(null, "ID Ruangan tidak ditemukan.");
                }
            }
        });
        DefaultTableModel model_tabel = new DefaultTableModel(new Object[]{"ID Rekam Medis", "Tanggal", "Diagnosa"}, 0);
        tableRekamMedis.setModel(model_tabel);
    }

    private void tambahPasien() {
        String id = txtFieldIDPasien1.getText().trim();
        String namaPasien = txtFieldJenisKelamin.getText().trim();
        String jenisKelaminPasien = txtFieldJenisKelamin.getText().trim();
        String umurPasien = txtFieldUmurPasien.getText().trim();
        String alamatPasien = txtFieldAlamatPasien.getText().trim();
        String noTelpPasien = txtFieldNoTelpPasien.getText().trim();
        String tanggalMasukPasien = txtFieldTgglMasukPasien.getText().trim();
        String tanggalKeluarPasien = txtFieldTgglKeluarPasien.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tabelDataPasien.getModel();
        model.addRow(new Object[]{id, namaPasien, jenisKelaminPasien, umurPasien, alamatPasien, noTelpPasien, tanggalMasukPasien, tanggalKeluarPasien});
    }

    private void cariPasien() {
        String cariIDPasien = txtFieldIDPasien1.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tabelDataPasien.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equalsIgnoreCase(cariIDPasien)) {
                tabelDataPasien.setRowSelectionInterval(i, i);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "ID Pasien tidak ditemukan.");

    }

    private void tambahDokter() {
        String idDokter = txtFieldIDDokter.getText().trim();
        String namaDokter = txtFieldNamaPasien2.getText().trim();
        String jenisKelaminDokter = txtFieldJenisKelaminDokter.getText().trim();
        String umurDokter = txtFieldUmurDokter.getText().trim();
        String alamatDokter = txtFieldAlamatDokter.getText().trim();
        String noTelpDokter = txtFieldNoTelpDokter.getText().trim();
        String tunjanganSpesialis = txtFieldBonusGajiDokter.getText().trim();
        String spesialisasi = txtFieldSpesialisasiDokter.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tabelPanelDokter.getModel();
        model.addRow(new Object[]{idDokter, namaDokter, jenisKelaminDokter, umurDokter, alamatDokter, noTelpDokter, tunjanganSpesialis, spesialisasi});
    }

    private void cariDokter() {
        String cariIDDokter = txtFieldIDDokter.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tabelPanelDokter.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equalsIgnoreCase(cariIDDokter)) {
                tabelPanelDokter.setRowSelectionInterval(i, i);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "ID Dokter tidak ditemukan.");

    }

    private void tambahPerawat() {
        String idPerawat = txtFieldIDPerawat.getText().trim();
        String namaPerawat = txtFieldNamaPerawat.getText().trim();
        String jenisKelaminPerawat = txtFieldJenisKelaminPerawat.getText().trim();
        String umurPerawat = txtFieldUmurPerawat.getText().trim();
        String alamatPerawat = txtFieldAlamatPerawat.getText().trim();
        String noTelpPerawat = txtFieldNoTelpPerawat.getText().trim();
        String unitBagian = jTextFieldUnitBagianPerawat.getText().trim();
        String shiftPerawat = jTextFieldShiftPerawat.getText().trim();

        DefaultTableModel model = (DefaultTableModel) jTablePerawat.getModel();
        model.addRow(new Object[]{idPerawat, namaPerawat, jenisKelaminPerawat, umurPerawat, alamatPerawat, noTelpPerawat, unitBagian, shiftPerawat});
    }

    private void cariPerawat() {
        String cariIDPerawat = txtFieldIDDokter.getText().trim();
        DefaultTableModel model = (DefaultTableModel) jTablePerawat.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equalsIgnoreCase(cariIDPerawat)) {
                jTablePerawat.setRowSelectionInterval(i, i);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "ID Perawat tidak ditemukan.");

    }

    private void cariPegawai() {
        String cariID = txtFieldIDDokter.getText().trim(); // atau ganti dengan txtFieldIDPegawai jika ada
        DefaultTableModel modelDokter = (DefaultTableModel) tabelPanelDokter.getModel();
        DefaultTableModel modelPerawat = (DefaultTableModel) jTablePerawat.getModel();
        DefaultTableModel modelUtama = (DefaultTableModel) tablePegawai.getModel();

        // Hapus isi tabel pegawai utama dulu
        modelUtama.setRowCount(0);

        // Cari di dokter
        for (int i = 0; i < modelDokter.getRowCount(); i++) {
            if (modelDokter.getValueAt(i, 0).toString().equalsIgnoreCase(cariID)) {
                String nama = modelDokter.getValueAt(i, 1).toString(); // asumsi nama di kolom 1
                modelUtama.addRow(new Object[]{cariID, "Dokter", nama});
                return;
            }
        }

        // Cari di perawat
        for (int i = 0; i < modelPerawat.getRowCount(); i++) {
            if (modelPerawat.getValueAt(i, 0).toString().equalsIgnoreCase(cariID)) {
                String nama = modelPerawat.getValueAt(i, 1).toString(); // asumsi nama di kolom 1
                modelUtama.addRow(new Object[]{cariID, "Perawat", nama});
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "ID Pegawai tidak ditemukan.");
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
        txtFieldJenisKelamin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFieldNoTelpPasien = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtFieldUmurPasien = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtFieldAlamatPasien = new javax.swing.JTextField();
        cariIDPasien = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtFieldTgglKeluarPasien = new javax.swing.JTextField();
        btnTambahPasien = new javax.swing.JButton();
        btnHapusPasien = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        txtFieldTgglMasukPasien = new javax.swing.JTextField();
        txtFieldNamaPasien4 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelDataPasien = new javax.swing.JTable();
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
        btnKePanelDokter = new javax.swing.JButton();
        btnKePanelPerawat = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablePegawai = new javax.swing.JTable();
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
        transaksiPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
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
        dokterPanel = new javax.swing.JPanel();
        dokterPanel1 = new javax.swing.JPanel();
        panelDokterpanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtFieldIDDokter = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtFieldNamaPasien2 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtFieldNoTelpDokter = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtFieldUmurDokter = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtFieldAlamatDokter = new javax.swing.JTextField();
        btnCariIDDokter = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtFieldBonusGajiDokter = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtFieldSpesialisasiDokter = new javax.swing.JTextField();
        btnTambahDokter = new javax.swing.JButton();
        btnHapusDoker = new javax.swing.JButton();
        btnKembaliDokter = new javax.swing.JButton();
        txtFieldJenisKelaminDokter = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPanelDokter = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelPerawat = new javax.swing.JPanel();
        perawatPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtFieldIDPerawat = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtFieldNamaPerawat = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtFieldNoTelpPerawat = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtFieldUmurPerawat = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtFieldAlamatPerawat = new javax.swing.JTextField();
        btnCariIdPerawat = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        btnTambahPerawat = new javax.swing.JButton();
        btnHapusPerawat = new javax.swing.JButton();
        btnKembaliPanelPerawat = new javax.swing.JButton();
        txtFieldJenisKelaminPerawat = new javax.swing.JTextField();
        jTextFieldShiftPerawat = new javax.swing.JTextField();
        jTextFieldUnitBagianPerawat = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablePerawat = new javax.swing.JTable();

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

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(6320, Short.MAX_VALUE))
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
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

        txtFieldIDPasien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldIDPasien1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel13.setText("Jenis Kelamin");

        txtFieldJenisKelamin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldJenisKelaminActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel14.setText("Umur");

        jLabel15.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel15.setText("No. Telepon");

        jLabel16.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel16.setText("Alamat");

        txtFieldAlamatPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldAlamatPasienActionPerformed(evt);
            }
        });

        cariIDPasien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cariIDPasien.setText("Cari");
        cariIDPasien.setToolTipText("");
        cariIDPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariIDPasienActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel17.setText("Tanggal Masuk");

        jLabel18.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel18.setText("Tanggal Keluar");

        btnTambahPasien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambahPasien.setText("TAMBAH");
        btnTambahPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPasienActionPerformed(evt);
            }
        });

        btnHapusPasien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapusPasien.setText("HAPUS");
        btnHapusPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPasienActionPerformed(evt);
            }
        });

        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        txtFieldNamaPasien4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldNamaPasien4ActionPerformed(evt);
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
                        .addComponent(jLabel15)
                        .addGap(46, 46, 46)
                        .addComponent(txtFieldNoTelpPasien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariIDPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFieldUmurPasien)
                                    .addComponent(txtFieldAlamatPasien)
                                    .addComponent(txtFieldIDPasien1))
                                .addGap(90, 90, 90))
                            .addComponent(txtFieldJenisKelamin)
                            .addComponent(txtFieldNamaPasien4)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnTambahPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFieldTgglKeluarPasien))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFieldTgglMasukPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 6479, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtFieldIDPasien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariIDPasien))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtFieldNamaPasien4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtFieldJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtFieldUmurPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtFieldAlamatPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldNoTelpPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtFieldTgglMasukPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtFieldTgglKeluarPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapusPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        tabelDataPasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        jScrollPane1.setViewportView(tabelDataPasien);
        if (tabelDataPasien.getColumnModel().getColumnCount() > 0) {
            tabelDataPasien.getColumnModel().getColumn(0).setResizable(false);
            tabelDataPasien.getColumnModel().getColumn(1).setResizable(false);
            tabelDataPasien.getColumnModel().getColumn(2).setResizable(false);
            tabelDataPasien.getColumnModel().getColumn(3).setResizable(false);
            tabelDataPasien.getColumnModel().getColumn(4).setResizable(false);
            tabelDataPasien.getColumnModel().getColumn(5).setResizable(false);
            tabelDataPasien.getColumnModel().getColumn(6).setResizable(false);
            tabelDataPasien.getColumnModel().getColumn(7).setResizable(false);
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

        btnKePanelDokter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKePanelDokter.setText("Dokter");
        btnKePanelDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKePanelDokterActionPerformed(evt);
            }
        });

        btnKePanelPerawat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKePanelPerawat.setText("Perawat");
        btnKePanelPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKePanelPerawatActionPerformed(evt);
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
                        .addComponent(btnKePanelDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnKePanelPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(btnKePanelDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(btnKePanelPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        tablePegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Jenis Pegawai", "Nama Pegawai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tablePegawai);
        if (tablePegawai.getColumnModel().getColumnCount() > 0) {
            tablePegawai.getColumnModel().getColumn(0).setResizable(false);
            tablePegawai.getColumnModel().getColumn(1).setResizable(false);
            tablePegawai.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addContainerGap())
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
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(6829, Short.MAX_VALUE))
            .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pegawaiPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(5904, Short.MAX_VALUE)))
            .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pegawaiPanelLayout.createSequentialGroup()
                    .addGap(2712, 2712, 2712)
                    .addComponent(btnKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(4167, Short.MAX_VALUE)))
        );
        pegawaiPanelLayout.setVerticalGroup(
            pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pegawaiPanelLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 867, Short.MAX_VALUE))
            .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pegawaiPanelLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pegawaiPanelLayout.createSequentialGroup()
                    .addGap(438, 438, 438)
                    .addComponent(btnKembali1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(438, Short.MAX_VALUE)))
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
        if (tableRekamMedis.getColumnModel().getColumnCount() > 0) {
            tableRekamMedis.getColumnModel().getColumn(0).setResizable(false);
            tableRekamMedis.getColumnModel().getColumn(1).setResizable(false);
            tableRekamMedis.getColumnModel().getColumn(2).setResizable(false);
        }

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
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
                        .addGap(0, 6796, Short.MAX_VALUE))
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

        javax.swing.GroupLayout obatPanelLayout = new javax.swing.GroupLayout(obatPanel);
        obatPanel.setLayout(obatPanelLayout);
        obatPanelLayout.setHorizontalGroup(
            obatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(obatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(6814, Short.MAX_VALUE))
        );
        obatPanelLayout.setVerticalGroup(
            obatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(obatPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(803, Short.MAX_VALUE))
        );

        mainPanel.add(obatPanel, "card6");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel9.setText("Menu Transaksi");
        jLabel9.setIconTextGap(10);

        javax.swing.GroupLayout transaksiPanelLayout = new javax.swing.GroupLayout(transaksiPanel);
        transaksiPanel.setLayout(transaksiPanelLayout);
        transaksiPanelLayout.setHorizontalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(6808, Short.MAX_VALUE))
        );
        transaksiPanelLayout.setVerticalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(803, Short.MAX_VALUE))
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
            .addGap(0, 5289, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ManagementRuanganLayout = new javax.swing.GroupLayout(ManagementRuangan);
        ManagementRuangan.setLayout(ManagementRuanganLayout);
        ManagementRuanganLayout.setHorizontalGroup(
            ManagementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManagementRuanganLayout.createSequentialGroup()
                .addGap(0, 530, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(0, 593, Short.MAX_VALUE)))
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
                .addContainerGap()
                .addGroup(NamelPanel9ManagementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Namelpanel11ManajementRuangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        NamelPanel9ManagementRuanganLayout.setVerticalGroup(
            NamelPanel9ManagementRuanganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NamelPanel9ManagementRuanganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Namelpanel11ManajementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(ManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        ruanganPanelLayout.setVerticalGroup(
            ruanganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(ruanganPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(ManagementRuangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ruanganPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(namelLabel10ManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NamelPanel9ManagementRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainPanel.add(ruanganPanel, "card8");

        jLabel3.setFont(new java.awt.Font("Rockwell Condensed", 1, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/call.png"))); // NOI18N
        jLabel3.setText("119");
        mainPanel.add(jLabel3, "card9");

        panelDokterpanel11.setBackground(new java.awt.Color(0, 153, 153));
        panelDokterpanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel23.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel23.setText("ID Dokter");

        jLabel24.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel24.setText("Nama Dokter");

        txtFieldIDDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldIDDokterActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel25.setText("Jenis Kelamin");

        txtFieldNamaPasien2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldNamaPasien2ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel26.setText("Umur");

        txtFieldNoTelpDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldNoTelpDokterActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel27.setText("No. Telepon");

        jLabel28.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel28.setText("Alamat");

        btnCariIDDokter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCariIDDokter.setText("Cari");
        btnCariIDDokter.setToolTipText("");
        btnCariIDDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariIDDokterActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel29.setText("Bonus");

        txtFieldBonusGajiDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldBonusGajiDokterActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel30.setText("Spesialisasi");

        btnTambahDokter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambahDokter.setText("TAMBAH");
        btnTambahDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahDokterActionPerformed(evt);
            }
        });

        btnHapusDoker.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapusDoker.setText("HAPUS");
        btnHapusDoker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusDokerActionPerformed(evt);
            }
        });

        btnKembaliDokter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKembaliDokter.setText("KEMBALI");
        btnKembaliDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliDokterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel28))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFieldUmurDokter)
                                    .addComponent(txtFieldAlamatDokter)
                                    .addComponent(txtFieldNamaPasien2)
                                    .addComponent(txtFieldIDDokter)
                                    .addComponent(txtFieldJenisKelaminDokter)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(46, 46, 46)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFieldNoTelpDokter)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtFieldBonusGajiDokter, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                            .addComponent(txtFieldSpesialisasiDokter))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariIDDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btnTambahDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusDoker, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKembaliDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 549, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtFieldIDDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariIDDokter))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtFieldNamaPasien2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtFieldJenisKelaminDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtFieldUmurDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtFieldAlamatDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldNoTelpDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtFieldBonusGajiDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtFieldSpesialisasiDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapusDoker, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembaliDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        tabelPanelDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Dokter", "Nama", "Jenis Kelamin", "Umur", "Alamat", "No. Telepon", "Bonus", "Spesialisasi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabelPanelDokter);
        if (tabelPanelDokter.getColumnModel().getColumnCount() > 0) {
            tabelPanelDokter.getColumnModel().getColumn(0).setResizable(false);
            tabelPanelDokter.getColumnModel().getColumn(1).setResizable(false);
            tabelPanelDokter.getColumnModel().getColumn(2).setResizable(false);
            tabelPanelDokter.getColumnModel().getColumn(3).setResizable(false);
            tabelPanelDokter.getColumnModel().getColumn(4).setResizable(false);
            tabelPanelDokter.getColumnModel().getColumn(5).setResizable(false);
            tabelPanelDokter.getColumnModel().getColumn(6).setResizable(false);
            tabelPanelDokter.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelDokterpanel11Layout = new javax.swing.GroupLayout(panelDokterpanel11);
        panelDokterpanel11.setLayout(panelDokterpanel11Layout);
        panelDokterpanel11Layout.setHorizontalGroup(
            panelDokterpanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDokterpanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDokterpanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDokterpanel11Layout.setVerticalGroup(
            panelDokterpanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDokterpanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel22.setFont(new java.awt.Font("Bodoni MT", 1, 36)); // NOI18N
        jLabel22.setText("Data Dokter");

        javax.swing.GroupLayout dokterPanel1Layout = new javax.swing.GroupLayout(dokterPanel1);
        dokterPanel1.setLayout(dokterPanel1Layout);
        dokterPanel1Layout.setHorizontalGroup(
            dokterPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dokterPanel1Layout.createSequentialGroup()
                .addGroup(dokterPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dokterPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelDokterpanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dokterPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel22)))
                .addContainerGap(1987, Short.MAX_VALUE))
        );
        dokterPanel1Layout.setVerticalGroup(
            dokterPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dokterPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDokterpanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel7.setText("Data Dokter");
        jLabel7.setIconTextGap(10);

        javax.swing.GroupLayout dokterPanelLayout = new javax.swing.GroupLayout(dokterPanel);
        dokterPanel.setLayout(dokterPanelLayout);
        dokterPanelLayout.setHorizontalGroup(
            dokterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dokterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dokterPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(3943, Short.MAX_VALUE))
            .addGroup(dokterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dokterPanelLayout.createSequentialGroup()
                    .addGap(2697, 2697, 2697)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(4152, Short.MAX_VALUE)))
        );
        dokterPanelLayout.setVerticalGroup(
            dokterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dokterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dokterPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(dokterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dokterPanelLayout.createSequentialGroup()
                    .addGap(414, 414, 414)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(448, Short.MAX_VALUE)))
        );

        mainPanel.add(dokterPanel, "card10");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel10.setText("Data Perawat");
        jLabel10.setIconTextGap(10);

        jPanel14.setBackground(new java.awt.Color(0, 153, 153));
        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel31.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel31.setText("ID Perawat");

        jLabel32.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel32.setText("Nama Perawat");

        txtFieldIDPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldIDPerawatActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel33.setText("Jenis Kelamin");

        txtFieldNamaPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldNamaPerawatActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel34.setText("Umur");

        jLabel35.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel35.setText("No. Telepon");

        jLabel36.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel36.setText("Alamat");

        btnCariIdPerawat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCariIdPerawat.setText("Cari");
        btnCariIdPerawat.setToolTipText("");
        btnCariIdPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariIdPerawatActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel37.setText("Unit Bagian");

        jLabel38.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel38.setText("Shift");

        btnTambahPerawat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambahPerawat.setText("TAMBAH");
        btnTambahPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPerawatActionPerformed(evt);
            }
        });

        btnHapusPerawat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapusPerawat.setText("HAPUS");
        btnHapusPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPerawatActionPerformed(evt);
            }
        });

        btnKembaliPanelPerawat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKembaliPanelPerawat.setText("KEMBALI");
        btnKembaliPanelPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliPanelPerawatActionPerformed(evt);
            }
        });

        txtFieldJenisKelaminPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldJenisKelaminPerawatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel36))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFieldUmurPerawat)
                                    .addComponent(txtFieldAlamatPerawat)
                                    .addComponent(txtFieldNamaPerawat)
                                    .addComponent(txtFieldIDPerawat)
                                    .addComponent(txtFieldJenisKelaminPerawat)))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel37))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFieldNoTelpPerawat)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldShiftPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldUnitBagianPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariIdPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(btnTambahPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKembaliPanelPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 536, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtFieldIDPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariIdPerawat))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtFieldNamaPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtFieldJenisKelaminPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtFieldUmurPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtFieldAlamatPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldNoTelpPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jTextFieldUnitBagianPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextFieldShiftPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapusPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembaliPanelPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jTablePerawat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Perawat", "Nama", "Jenis Kelamin", "Umur", "Alamat", "No. Telepon", "Unit Bagian", "Shift"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTablePerawat);
        if (jTablePerawat.getColumnModel().getColumnCount() > 0) {
            jTablePerawat.getColumnModel().getColumn(0).setResizable(false);
            jTablePerawat.getColumnModel().getColumn(1).setResizable(false);
            jTablePerawat.getColumnModel().getColumn(2).setResizable(false);
            jTablePerawat.getColumnModel().getColumn(3).setResizable(false);
            jTablePerawat.getColumnModel().getColumn(4).setResizable(false);
            jTablePerawat.getColumnModel().getColumn(5).setResizable(false);
            jTablePerawat.getColumnModel().getColumn(6).setResizable(false);
            jTablePerawat.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout perawatPanel3Layout = new javax.swing.GroupLayout(perawatPanel3);
        perawatPanel3.setLayout(perawatPanel3Layout);
        perawatPanel3Layout.setHorizontalGroup(
            perawatPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perawatPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(4225, Short.MAX_VALUE))
            .addGroup(perawatPanel3Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        perawatPanel3Layout.setVerticalGroup(
            perawatPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perawatPanel3Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelPerawatLayout = new javax.swing.GroupLayout(panelPerawat);
        panelPerawat.setLayout(panelPerawatLayout);
        panelPerawatLayout.setHorizontalGroup(
            panelPerawatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7045, Short.MAX_VALUE)
            .addGroup(panelPerawatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPerawatLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(perawatPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(2612, Short.MAX_VALUE)))
        );
        panelPerawatLayout.setVerticalGroup(
            panelPerawatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(panelPerawatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPerawatLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(perawatPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        mainPanel.add(panelPerawat, "card11");

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
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnTambahPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPasienActionPerformed
        tambahPasien();
    }//GEN-LAST:event_btnTambahPasienActionPerformed

    private void btnHapusPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPasienActionPerformed
        int selectedRow = tabelDataPasien.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tabelDataPasien.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
        }     // TODO add your handling code here:  
    }//GEN-LAST:event_btnHapusPasienActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(dashboardPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembali1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKembali1ActionPerformed

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
        mainPanel.revalidate();        // TODO add your handling code here:
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

    private void btnTambahDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDokterActionPerformed
        tambahDokter();
    }//GEN-LAST:event_btnTambahDokterActionPerformed

    private void btnHapusDokerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusDokerActionPerformed
        int selectedRow = tabelPanelDokter.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tabelPanelDokter.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
        }     // TODO add your handling code here:
    }//GEN-LAST:event_btnHapusDokerActionPerformed

    private void btnKembaliDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliDokterActionPerformed
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(pegawaiPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnKembaliDokterActionPerformed

    private void btnTambahPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPerawatActionPerformed
        tambahPerawat();
    }//GEN-LAST:event_btnTambahPerawatActionPerformed

    private void btnHapusPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPerawatActionPerformed
        int selectedRow = jTablePerawat.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTablePerawat.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
        }     // TODO add your handling code here:
    }//GEN-LAST:event_btnHapusPerawatActionPerformed

    private void btnKembaliPanelPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliPanelPerawatActionPerformed
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(pegawaiPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnKembaliPanelPerawatActionPerformed

    private void txtFieldIDPasien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldIDPasien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldIDPasien1ActionPerformed

    private void txtFieldJenisKelaminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldJenisKelaminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldJenisKelaminActionPerformed

    private void txtFieldNamaPasien4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldNamaPasien4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldNamaPasien4ActionPerformed

    private void txtFieldAlamatPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldAlamatPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldAlamatPasienActionPerformed

    private void cariIDPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariIDPasienActionPerformed
        cariIDPasien.addActionListener(e -> cariPasien());
    }//GEN-LAST:event_cariIDPasienActionPerformed

    private void txtFieldIDDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldIDDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldIDDokterActionPerformed

    private void txtFieldNamaPasien2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldNamaPasien2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldNamaPasien2ActionPerformed

    private void txtFieldNoTelpDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldNoTelpDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldNoTelpDokterActionPerformed

    private void btnCariIDDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariIDDokterActionPerformed
        btnCariIDDokter.addActionListener(e -> cariDokter());
    }//GEN-LAST:event_btnCariIDDokterActionPerformed

    private void txtFieldNamaPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldNamaPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldNamaPerawatActionPerformed

    private void txtFieldJenisKelaminPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldJenisKelaminPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldJenisKelaminPerawatActionPerformed

    private void txtFieldBonusGajiDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldBonusGajiDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldBonusGajiDokterActionPerformed

    private void txtFieldIDPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldIDPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldIDPerawatActionPerformed

    private void btnKePanelPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKePanelPerawatActionPerformed
        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(panelPerawat);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnKePanelPerawatActionPerformed

    private void btnKePanelDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKePanelDokterActionPerformed
        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(dokterPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnKePanelDokterActionPerformed

    private void btnCariIdPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariIdPerawatActionPerformed
        btnCariIdPerawat.addActionListener(e -> cariPerawat());
    }//GEN-LAST:event_btnCariIdPerawatActionPerformed

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
        KoneksiDB.getKoneksiDB();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDRekamMedisTextFieldRekamMedis;
    private javax.swing.JPanel ManagementRuangan;
    private javax.swing.JLabel NamelLabel7RekamMedis;
    private javax.swing.JPanel NamelPanel9ManagementRuangan;
    private javax.swing.JPanel NamelPanel9RekamMedis;
    private javax.swing.JPanel Namelanel10;
    private javax.swing.JPanel Namelpanel11ManajementRuangan;
    private javax.swing.JTextField TextFieldDiagnosaRekamMedis;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btNKEMBALIManagementRUangan;
    private javax.swing.JButton btNKEMBALIRekamMedis;
    private javax.swing.JButton btnCariIDDokter;
    private javax.swing.JButton btnCariIdPerawat;
    private javax.swing.JButton btnCariManagementRuangan;
    private javax.swing.JButton btnCariRekamMedis;
    private javax.swing.JButton btnHAPUSManagementRuangan;
    private javax.swing.JButton btnHAPUSRekamMedis;
    private javax.swing.JButton btnHapusDoker;
    private javax.swing.JButton btnHapusPasien;
    private javax.swing.JButton btnHapusPerawat;
    private javax.swing.JButton btnKePanelDokter;
    private javax.swing.JButton btnKePanelPerawat;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnKembali1;
    private javax.swing.JButton btnKembaliDokter;
    private javax.swing.JButton btnKembaliPanelPerawat;
    private javax.swing.JButton btnTAMBAHManagementRuangan;
    private javax.swing.JButton btnTAMBAHRekamMedis;
    private javax.swing.JButton btnTambahDokter;
    private javax.swing.JButton btnTambahPasien;
    private javax.swing.JButton btnTambahPerawat;
    private javax.swing.JButton cariIDPasien;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JLabel diagnosaRekamMedis;
    private javax.swing.JPanel dokterPanel;
    private javax.swing.JPanel dokterPanel1;
    private javax.swing.JLabel hargaRuanganManagementRuangan;
    private javax.swing.JLabel idRekamMedisRekamMeidis;
    private javax.swing.JTextField idRuanganManagementRuangan;
    private javax.swing.JLabel idRuanganjlabelManagementRuangan;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxTipeRuanganManagementRUangan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTablePerawat;
    private javax.swing.JTextField jTextFieldShiftPerawat;
    private javax.swing.JTextField jTextFieldUnitBagianPerawat;
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
    private javax.swing.JPanel panelDokterpanel11;
    private javax.swing.JPanel panelPerawat;
    private javax.swing.JPanel pasienPanel;
    private javax.swing.JPanel pegawaiPanel;
    private javax.swing.JPanel perawatPanel3;
    private javax.swing.JPanel rekamMedisPanel;
    private javax.swing.JPanel ruanganPanel;
    private javax.swing.JTable tabelDataPasien;
    private javax.swing.JTable tabelPanelDokter;
    protected javax.swing.JTable tableManagementRuangan;
    protected javax.swing.JTable tablePegawai;
    protected javax.swing.JTable tableRekamMedis;
    private javax.swing.JLabel tanggalRekamMedis;
    private javax.swing.JTextField tanggalTextFieldRekamMedis;
    private javax.swing.JTextField tfHargaRuanganManagementRuangan;
    private javax.swing.JLabel tipeRuanganManajementRuangan;
    private javax.swing.JPanel transaksiPanel;
    private javax.swing.JTextField txtFieldAlamatDokter;
    private javax.swing.JTextField txtFieldAlamatPasien;
    private javax.swing.JTextField txtFieldAlamatPerawat;
    private javax.swing.JTextField txtFieldBonusGajiDokter;
    private javax.swing.JTextField txtFieldIDDokter;
    private javax.swing.JTextField txtFieldIDPasien1;
    private javax.swing.JTextField txtFieldIDPasien2;
    private javax.swing.JTextField txtFieldIDPerawat;
    private javax.swing.JTextField txtFieldJenisKelamin;
    private javax.swing.JTextField txtFieldJenisKelaminDokter;
    private javax.swing.JTextField txtFieldJenisKelaminPerawat;
    private javax.swing.JTextField txtFieldNamaPasien1;
    private javax.swing.JTextField txtFieldNamaPasien2;
    private javax.swing.JTextField txtFieldNamaPasien4;
    private javax.swing.JTextField txtFieldNamaPerawat;
    private javax.swing.JTextField txtFieldNoTelpDokter;
    private javax.swing.JTextField txtFieldNoTelpPasien;
    private javax.swing.JTextField txtFieldNoTelpPerawat;
    private javax.swing.JTextField txtFieldSpesialisasiDokter;
    private javax.swing.JTextField txtFieldTgglKeluarPasien;
    private javax.swing.JTextField txtFieldTgglMasukPasien;
    private javax.swing.JTextField txtFieldUmurDokter;
    private javax.swing.JTextField txtFieldUmurPasien;
    private javax.swing.JTextField txtFieldUmurPerawat;
    // End of variables declaration//GEN-END:variables
}

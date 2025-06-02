/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pojekakhirbismillah;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private void loadDataRuangan() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
        String user = "naila01"; // replace this
        String password = "root"; // replace this
        DefaultTableModel model = (DefaultTableModel) tableManagementRuangan.getModel();
        model.setRowCount(0); // clear tabel

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM RUANGAN";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ID_RUANGAN");
                String tipe = rs.getString("TIPE_RUANGAN");
                String harga = rs.getString("HARGA_RUANGAN");

                model.addRow(new Object[]{id, tipe, harga});
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal memuat data ruangan: " + e.getMessage());
        }
    }

    private void loadDataTransaksi() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
        String user = "naila01";
        String password = "root";
        DefaultTableModel model = (DefaultTableModel) tableRekamMedis.getModel();
        model.setRowCount(0); // Clear tabel

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
//        System.out.println("Koneksi berhasil, memuat data Rekam Medis...");
            String sql = "SELECT * FROM Transaksi;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ID_Transaksi");
                String tanggal = rs.getString("TANGGAL");
                String Total = rs.getString("TOTAL");

                model.addRow(new Object[]{id, tanggal, Total});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal memuat data Transaksi: " + e.getMessage());
        }
    }

    private void loadDataRekamMedis() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
        String user = "naila01";
        String password = "root";
        DefaultTableModel model = (DefaultTableModel) tableRekamMedis.getModel();
        model.setRowCount(0); // Clear tabel

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
//        System.out.println("Koneksi berhasil, memuat data Rekam Medis...");
            String sql = "SELECT * FROM RekamMedis;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ID_REKAM_MEDIS");
                String tanggal = rs.getString("TANGGAL");
                String diagnosa = rs.getString("DIAGNOSA");

                model.addRow(new Object[]{id, tanggal, diagnosa});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal memuat data Rekam Medis: " + e.getMessage());
        }
    }

    private void loadDataInventaris() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
        String user = "naila01";
        String password = "root";
        DefaultTableModel model = (DefaultTableModel) jTable_Obat.getModel();
        model.setRowCount(0); // Clear tabel

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
//        System.out.println("Koneksi berhasil, memuat data Rekam Medis...");
            String sql = "SELECT * FROM Inventaris;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ID_OBAT");
                String nama = rs.getString("NAMA_OBAT");
                boolean tidakTersedia = rs.getBoolean("TIDAK_TERSEDIA");
                int jumlah = rs.getInt("JUMLAH");
                String harga = rs.getString("HARGA");

                model.addRow(new Object[]{id, nama, tidakTersedia, jumlah, harga});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal memuat data Inventaris: " + e.getMessage());
        }
    }
    DefaultTableModel model_TabelTransaksi;

    private void tambahKeObat() {
        String idObat = ID_ObatTextField.getText();
        String namaObat = (String) jComboBoxNamaObat.getSelectedItem();
        String ketersediaan = JCheckBoxTidaktersedia.isSelected() ? "Tidak Tersedia" : "Tersedia";
        int jumlah = (Integer) jSpinnerBanyakObat.getValue();
        String harga = TextFieldHargaObat.getText();

        DefaultTableModel model_TabelObat = (DefaultTableModel) jTable_Obat.getModel();
        model_TabelObat.addRow(new Object[]{idObat, namaObat, ketersediaan, jumlah, harga});
    }
    String[] namaObatList = {"Paracetamol", "Amoxicillin", "Ibuprofen", "Cetirizine", "Omeprazole", "Salbutamol", "Metformin", "Simvastatin", "Amlodipine", "Ranitidine"};

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

        String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
        String user = "naila01"; // replace this
        String password = "root"; // replace this

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO Ruangan (ID_RUANGAN, TIPE_RUANGAN, HARGA_RUANGAN) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, tipe);
            stmt.setString(3, harga);
            stmt.executeUpdate();
            System.out.println("Data berhasil disimpan ke database.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menyimpan ke database: " + e.getMessage());
        }
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
        // Untuk Pasien
        jComboBoxJenisKelaminPasien.removeAllItems();
        jComboBoxJenisKelaminPasien.addItem("Laki-Laki");
        jComboBoxJenisKelaminPasien.addItem("Perempuan");

        // Untuk Perawat
        jComboBoxJenisKelaminPerawat.removeAllItems();
        jComboBoxJenisKelaminPerawat.addItem("Laki-Laki");
        jComboBoxJenisKelaminPerawat.addItem("Perempuan");

        //untuk Dokter
        jComboBoxJenisKelaminDokter.removeAllItems();
        jComboBoxJenisKelaminDokter.addItem("Laki-Laki");
        jComboBoxJenisKelaminDokter.addItem("Perempuan");

        loadDataTransaksi();
        loadDataRekamMedis();
        loadDataRuangan();
        loadDataInventaris();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent evt) {
                loadDataRekamMedis();
            }
        });
        model_TabelTransaksi = (DefaultTableModel) JTableTransaksi.getModel();
        for (String nama : namaObatList) {
            jComboBoxNamaObat.addItem(nama);
        }
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
        DefaultTableModel model_tabel = new DefaultTableModel(
                new Object[]{"ID Rekam Medis", "Tanggal", "Diagnosa"}, 0);
        tableRekamMedis.setModel(model_tabel);
    }

    private void tambahPasien() {
        String id = txtFieldIDPasien1.getText().trim();
        String namaPasien = txtFieldNamaPasien.getText().trim();
        String jenisKelaminPasien = jComboBoxJenisKelaminPasien.getSelectedItem().toString();
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

    private void updatePasien() {
        int selectedRow = tabelDataPasien.getSelectedRow();

        if (selectedRow >= 0) {
            // Ambil data dari text field
            String id = txtFieldIDPasien1.getText().trim();
            String namaPasien = txtFieldNamaPasien.getText().trim();
            String jenisKelaminPasien = jComboBoxJenisKelaminPasien.getSelectedItem().toString();
            String umurPasien = txtFieldUmurPasien.getText().trim();
            String alamatPasien = txtFieldAlamatPasien.getText().trim();
            String noTelpPasien = txtFieldNoTelpPasien.getText().trim();
            String tanggalMasukPasien = txtFieldTgglMasukPasien.getText().trim();
            String tanggalKeluarPasien = txtFieldTgglKeluarPasien.getText().trim();

            // Update baris yang dipilih
            DefaultTableModel model = (DefaultTableModel) tabelDataPasien.getModel();
            model.setValueAt(id, selectedRow, 0);
            model.setValueAt(namaPasien, selectedRow, 1);
            model.setValueAt(jenisKelaminPasien, selectedRow, 2);
            model.setValueAt(umurPasien, selectedRow, 3);
            model.setValueAt(alamatPasien, selectedRow, 4);
            model.setValueAt(noTelpPasien, selectedRow, 5);
            model.setValueAt(tanggalMasukPasien, selectedRow, 6);
            model.setValueAt(tanggalKeluarPasien, selectedRow, 7);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin di-update terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void tambahDokter() {
        String id = txtFieldIdDokter.getText().trim();
        String nama = txtFieldNamaDokter.getText().trim();
        String jenisKelamin = jComboBoxJenisKelaminDokter.getSelectedItem().toString();
        String umur = txtFieldUmurPasienDokter.getText().trim();
        String alamat = txtFieldAlamatDokter.getText().trim();
        String noTelepon = txtFieldNoTelpDokter.getText().trim();
        String spesialisasi = jComboBoxSpesialisasiDokter.getSelectedItem().toString();
        String tunjangan = txtFieldTunjanganSpesialisasi.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tabelDataDokter.getModel();
        model.addRow(new Object[]{id, nama, jenisKelamin, umur, alamat, noTelepon, spesialisasi, tunjangan});
    }

    private void cariDokter() {
        String cariIDDokter = txtFieldIdDokter.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tabelDataDokter.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equalsIgnoreCase(cariIDDokter)) {
                tabelDataDokter.setRowSelectionInterval(i, i);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "ID Dokter tidak ditemukan.");

    }

    private void updateDokter() {
        int selectedRow = tabelDataDokter.getSelectedRow();

        if (selectedRow >= 0) {
            // Ambil data dari text field
            String id = txtFieldIdDokter.getText().trim();
            String nama = txtFieldNamaDokter.getText().trim();
            String jenisKelamin = jComboBoxJenisKelaminDokter.getSelectedItem().toString();
            String umur = txtFieldUmurPasienDokter.getText().trim();
            String alamat = txtFieldAlamatDokter.getText().trim();
            String noTelepon = txtFieldNoTelpDokter.getText().trim();
            String spesialisasi = jComboBoxSpesialisasiDokter.getSelectedItem().toString();
            String tunjangan = txtFieldTunjanganSpesialisasi.getText().trim();

            // Update baris yang dipilih di tabel
            DefaultTableModel model = (DefaultTableModel) tabelDataDokter.getModel();
            model.setValueAt(id, selectedRow, 0);
            model.setValueAt(nama, selectedRow, 1);
            model.setValueAt(jenisKelamin, selectedRow, 2);
            model.setValueAt(umur, selectedRow, 3);
            model.setValueAt(alamat, selectedRow, 4);
            model.setValueAt(noTelepon, selectedRow, 5);
            model.setValueAt(spesialisasi, selectedRow, 6);
            model.setValueAt(tunjangan, selectedRow, 7);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin di-update terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void tambahPerawat() {
        String id = txtFieldIDPerawat.getText().trim();
        String nama = txtFieldNamaPerawat.getText().trim();
        String jenisKelamin = jComboBoxJenisKelaminPerawat.getSelectedItem().toString();
        String umur = txtFieldUmurPerawat.getText().trim();
        String alamat = txtFieldAlamatPerawat.getText().trim();
        String noTelepon = txtFieldNoTelpPerawat.getText().trim();
        String unit = jComboBoxUnitBagianPerawat.getSelectedItem().toString();
        String shift = jComboBoxShiftPerawat.getSelectedItem().toString();

        DefaultTableModel model = (DefaultTableModel) tabelDataPerawat.getModel();
        model.addRow(new Object[]{id, nama, jenisKelamin, umur, alamat, noTelepon, unit, shift});
    }

    private void cariPerawat() {
        String cariIDPerawat = txtFieldIDPerawat.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tabelDataPerawat.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equalsIgnoreCase(cariIDPerawat)) {
                tabelDataPerawat.setRowSelectionInterval(i, i);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "ID Perawat tidak ditemukan.");

    }

    private void updatePerawat() {
        int selectedRow = tabelDataPerawat.getSelectedRow(); // Pastikan ambil dari tabel perawat
        if (selectedRow >= 0) {
            // Ambil data dari komponen input untuk Perawat
            String id = txtFieldIDPerawat.getText().trim();
            String nama = txtFieldNamaPerawat.getText().trim();
            String jenisKelamin = jComboBoxJenisKelaminPerawat.getSelectedItem().toString();
            String umur = txtFieldUmurPerawat.getText().trim();
            String alamat = txtFieldAlamatPerawat.getText().trim();
            String noTelepon = txtFieldNoTelpPerawat.getText().trim();
            String unit = jComboBoxUnitBagianPerawat.getSelectedItem().toString();
            String shift = jComboBoxShiftPerawat.getSelectedItem().toString();

            // Update data di tabel
            DefaultTableModel model = (DefaultTableModel) tabelDataPerawat.getModel();
            model.setValueAt(id, selectedRow, 0);
            model.setValueAt(nama, selectedRow, 1);
            model.setValueAt(jenisKelamin, selectedRow, 2);
            model.setValueAt(umur, selectedRow, 3);
            model.setValueAt(alamat, selectedRow, 4);
            model.setValueAt(noTelepon, selectedRow, 5);
            model.setValueAt(unit, selectedRow, 6);
            model.setValueAt(shift, selectedRow, 7);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris perawat yang ingin di-update terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    //kode nampilin seluruh pegawai di panelPegawai
    private void tampilkanSemuaPegawai() {
        DefaultTableModel modelPegawai = new DefaultTableModel();
        modelPegawai.setColumnIdentifiers(new Object[]{"ID Pegawai", "Nama", "Jenis Kelamin", "Sebagai", "Unit/Spesialisasi"});

        // Ambil data dari tabel Dokter
        DefaultTableModel modelDokter = (DefaultTableModel) tabelDataDokter.getModel();
        for (int i = 0; i < modelDokter.getRowCount(); i++) {
            String id = modelDokter.getValueAt(i, 0).toString();            // ID Dokter
            String nama = modelDokter.getValueAt(i, 1).toString();          // Nama Dokter
            String jenisKelamin = modelDokter.getValueAt(i, 2).toString();  // Jenis Kelamin
            String spesialisasi = modelDokter.getValueAt(i, 6).toString();  // Kolom Spesialisasi (pastikan ini benar)

            modelPegawai.addRow(new Object[]{id, nama, jenisKelamin, "Dokter", spesialisasi});
        }

        // Ambil data dari tabel Perawat
        DefaultTableModel modelPerawat = (DefaultTableModel) tabelDataPerawat.getModel();
        for (int i = 0; i < modelPerawat.getRowCount(); i++) {
            String id = modelPerawat.getValueAt(i, 0).toString();           // ID Perawat
            String nama = modelPerawat.getValueAt(i, 1).toString();         // Nama Perawat
            String jenisKelamin = modelPerawat.getValueAt(i, 2).toString(); // Jenis Kelamin
            String unit = modelPerawat.getValueAt(i, 7).toString();         // Kolom Unit Bagian (pastikan ini benar)

            modelPegawai.addRow(new Object[]{id, nama, jenisKelamin, "Perawat", unit});
        }

        // Tampilkan ke tabel pegawai
        jTableDataPegawai.setModel(modelPegawai);
    }

    //untuk cari ID pegawai di panel Pegawai
    private void cariPegawai() {
        String cariID = txtFieldIDPegawaiSEMUA.getText().trim(); // ini TextField pencarian di menu Pegawai
        if (cariID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan ID Pegawai yang ingin dicari.");
            return;
        }

        // Pastikan jTable1 sudah di-refresh dengan data dokter + perawat
        tampilkanSemuaPegawai(); // <-- supaya data terbaru tampil dulu

        DefaultTableModel model = (DefaultTableModel) jTableDataPegawai.getModel();
        boolean ditemukan = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            String id = model.getValueAt(i, 0).toString();
            if (id.equalsIgnoreCase(cariID)) {
                jTableDataPegawai.setRowSelectionInterval(i, i);
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            JOptionPane.showMessageDialog(this, "ID Pegawai tidak ditemukan di data Dokter maupun Perawat.");
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
        jLabel14 = new javax.swing.JLabel();
        txtFieldNoTelpPasien = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtFieldUmurPasien = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtFieldAlamatPasien = new javax.swing.JTextField();
        cariIDPasien = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtFieldTgglMasukPasien = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtFieldTgglKeluarPasien = new javax.swing.JTextField();
        btnTambahPasien = new javax.swing.JButton();
        btnHapusPasien = new javax.swing.JButton();
        btnUpdatePasien = new javax.swing.JButton();
        jComboBoxJenisKelaminPasien = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelDataPasien = new javax.swing.JTable();
        pegawaiPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtFieldIDPegawaiSEMUA = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtFieldNamaPegawai = new javax.swing.JTextField();
        jButtonCariPegawai = new javax.swing.JButton();
        jButtonKePanelDokoter = new javax.swing.JButton();
        jButtonKePanelPerawat = new javax.swing.JButton();
        jButtonRefreshDataPegawai = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableDataPegawai = new javax.swing.JTable();
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
        dokterPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtFieldIdDokter = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtFieldNamaDokter = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtFieldNoTelpDokter = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtFieldUmurPasienDokter = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtFieldAlamatDokter = new javax.swing.JTextField();
        cariIDDokter = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtFieldTunjanganSpesialisasi = new javax.swing.JTextField();
        btnTambahDokter = new javax.swing.JButton();
        btnHapusDokter = new javax.swing.JButton();
        btnUpdateDokter = new javax.swing.JButton();
        jComboBoxSpesialisasiDokter = new javax.swing.JComboBox<>();
        jComboBoxJenisKelaminDokter = new javax.swing.JComboBox<>();
        btnKembaliDokter = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelDataDokter = new javax.swing.JTable();
        perawatPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtFieldIDPerawat = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtFieldNamaPerawat = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtFieldNoTelpPerawat = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtFieldUmurPerawat = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtFieldAlamatPerawat = new javax.swing.JTextField();
        cariIDPerawat = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        btnTambahPerawat = new javax.swing.JButton();
        btnHapusPerawat = new javax.swing.JButton();
        btnUpdatePerawat = new javax.swing.JButton();
        jComboBoxUnitBagianPerawat = new javax.swing.JComboBox<>();
        jComboBoxJenisKelaminPerawat = new javax.swing.JComboBox<>();
        jComboBoxShiftPerawat = new javax.swing.JComboBox<>();
        btnKembaliPerawat = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabelDataPerawat = new javax.swing.JTable();

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
                .addContainerGap(709, Short.MAX_VALUE))
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

        txtFieldIDPasien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldIDPasien1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel13.setText("Jenis Kelamin");

        txtFieldNamaPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldNamaPasienActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel14.setText("Umur");

        jLabel15.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel15.setText("No. Telepon");

        txtFieldUmurPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldUmurPasienActionPerformed(evt);
            }
        });

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

        btnUpdatePasien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdatePasien.setText("UPDATE");
        btnUpdatePasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePasienActionPerformed(evt);
            }
        });

        jComboBoxJenisKelaminPasien.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N
        jComboBoxJenisKelaminPasien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        jComboBoxJenisKelaminPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxJenisKelaminPasienActionPerformed(evt);
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
                                    .addComponent(txtFieldUmurPasien)
                                    .addComponent(txtFieldAlamatPasien)
                                    .addComponent(txtFieldNamaPasien)
                                    .addComponent(txtFieldIDPasien1)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jComboBoxJenisKelaminPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(46, 46, 46)
                                .addComponent(txtFieldNoTelpPasien)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariIDPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnTambahPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdatePasien, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFieldTgglKeluarPasien))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFieldTgglMasukPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 571, Short.MAX_VALUE)))
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
                    .addComponent(txtFieldNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxJenisKelaminPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldTgglMasukPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtFieldTgglKeluarPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapusPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdatePasien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
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
        jLabel20.setText("Nama Pegawai");

        jLabel21.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel21.setText("Bagian Pegawai");

        jButtonCariPegawai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCariPegawai.setText("Cari");
        jButtonCariPegawai.setToolTipText("");
        jButtonCariPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCariPegawaiActionPerformed(evt);
            }
        });

        jButtonKePanelDokoter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonKePanelDokoter.setText("Dokter");
        jButtonKePanelDokoter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKePanelDokoterActionPerformed(evt);
            }
        });

        jButtonKePanelPerawat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonKePanelPerawat.setText("Perawat");
        jButtonKePanelPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKePanelPerawatActionPerformed(evt);
            }
        });

        jButtonRefreshDataPegawai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonRefreshDataPegawai.setText("REFRESH");
        jButtonRefreshDataPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshDataPegawaiActionPerformed(evt);
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
                            .addComponent(txtFieldNamaPegawai)
                            .addComponent(txtFieldIDPegawaiSEMUA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCariPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonKePanelDokoter, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonKePanelPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRefreshDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 591, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtFieldIDPegawaiSEMUA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCariPegawai))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtFieldNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonKePanelDokoter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jButtonKePanelPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRefreshDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jTableDataPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pegawai", "Sebagai", "Nama", "Jenis Kelamin", "Unit/Spesialisasi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTableDataPegawai);
        if (jTableDataPegawai.getColumnModel().getColumnCount() > 0) {
            jTableDataPegawai.getColumnModel().getColumn(0).setResizable(false);
            jTableDataPegawai.getColumnModel().getColumn(1).setResizable(false);
            jTableDataPegawai.getColumnModel().getColumn(2).setResizable(false);
            jTableDataPegawai.getColumnModel().getColumn(3).setResizable(false);
            jTableDataPegawai.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pegawaiPanelLayout = new javax.swing.GroupLayout(pegawaiPanel);
        pegawaiPanel.setLayout(pegawaiPanelLayout);
        pegawaiPanelLayout.setHorizontalGroup(
            pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pegawaiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(921, Short.MAX_VALUE))
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
                .addGap(0, 818, Short.MAX_VALUE))
            .addGroup(pegawaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pegawaiPanelLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
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
                        .addGap(0, 888, Short.MAX_VALUE))
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

        jComboBoxNamaObat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Obat" }));
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
                        .addGap(0, 591, Short.MAX_VALUE)))
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
        TanggalTransaksi.setText("Tanggal Transaksi");

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
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1113, Short.MAX_VALUE))
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

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel7.setText("Data Dokter");
        jLabel7.setIconTextGap(10);

        jPanel13.setBackground(new java.awt.Color(0, 153, 153));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel22.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel22.setText("ID Dokter");

        jLabel23.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel23.setText("Nama Dokter");

        txtFieldIdDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldIdDokterActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel24.setText("Jenis Kelamin");

        txtFieldNamaDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldNamaDokterActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel25.setText("Umur");

        jLabel26.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel26.setText("No. Telepon");

        txtFieldUmurPasienDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldUmurPasienDokterActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel27.setText("Alamat");

        txtFieldAlamatDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldAlamatDokterActionPerformed(evt);
            }
        });

        cariIDDokter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cariIDDokter.setText("Cari");
        cariIDDokter.setToolTipText("");
        cariIDDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariIDDokterActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel28.setText("Spesialisasi");

        jLabel29.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel29.setText("Tunjangan");

        txtFieldTunjanganSpesialisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldTunjanganSpesialisasiActionPerformed(evt);
            }
        });

        btnTambahDokter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambahDokter.setText("TAMBAH");
        btnTambahDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahDokterActionPerformed(evt);
            }
        });

        btnHapusDokter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapusDokter.setText("HAPUS");
        btnHapusDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusDokterActionPerformed(evt);
            }
        });

        btnUpdateDokter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateDokter.setText("UPDATE");
        btnUpdateDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDokterActionPerformed(evt);
            }
        });

        jComboBoxSpesialisasiDokter.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N
        jComboBoxSpesialisasiDokter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UMUM", "BEDAH", "TULANG", "GIGI" }));
        jComboBoxSpesialisasiDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSpesialisasiDokterActionPerformed(evt);
            }
        });

        jComboBoxJenisKelaminDokter.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N
        jComboBoxJenisKelaminDokter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        jComboBoxJenisKelaminDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxJenisKelaminDokterActionPerformed(evt);
            }
        });

        btnKembaliDokter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKembaliDokter.setText("KEMBALI");
        btnKembaliDokter.setToolTipText("");
        btnKembaliDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliDokterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel27))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFieldUmurPasienDokter)
                                    .addComponent(txtFieldAlamatDokter)
                                    .addComponent(txtFieldNamaDokter)
                                    .addComponent(txtFieldIdDokter)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addComponent(jComboBoxJenisKelaminDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(46, 46, 46)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addComponent(jComboBoxSpesialisasiDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtFieldNoTelpDokter))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariIDDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel28))
                                .addGap(50, 50, 50)
                                .addComponent(txtFieldTunjanganSpesialisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(btnTambahDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdateDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKembaliDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 393, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtFieldIdDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariIDDokter))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtFieldNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jComboBoxJenisKelaminDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtFieldUmurPasienDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtFieldAlamatDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldNoTelpDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(12, 12, 12)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jComboBoxSpesialisasiDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldTunjanganSpesialisasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(8, 8, 8)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapusDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembaliDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        tabelDataDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Dokter", "Nama Dokter", "Jenis Kelamin", "Umur", "Alamat", "No. Telepon", "Spesialisasi", "Bonus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tabelDataDokter);
        if (tabelDataDokter.getColumnModel().getColumnCount() > 0) {
            tabelDataDokter.getColumnModel().getColumn(0).setResizable(false);
            tabelDataDokter.getColumnModel().getColumn(1).setResizable(false);
            tabelDataDokter.getColumnModel().getColumn(2).setResizable(false);
            tabelDataDokter.getColumnModel().getColumn(3).setResizable(false);
            tabelDataDokter.getColumnModel().getColumn(4).setResizable(false);
            tabelDataDokter.getColumnModel().getColumn(5).setResizable(false);
            tabelDataDokter.getColumnModel().getColumn(6).setResizable(false);
            tabelDataDokter.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dokterPanelLayout = new javax.swing.GroupLayout(dokterPanel);
        dokterPanel.setLayout(dokterPanelLayout);
        dokterPanelLayout.setHorizontalGroup(
            dokterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dokterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dokterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dokterPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dokterPanelLayout.setVerticalGroup(
            dokterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dokterPanelLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainPanel.add(dokterPanel, "card3");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel10.setText("Data Perawat");
        jLabel10.setIconTextGap(10);

        jPanel16.setBackground(new java.awt.Color(0, 153, 153));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel30.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel30.setText("ID Perawat");

        jLabel31.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel31.setText("Nama Perawat");

        txtFieldIDPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldIDPerawatActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel32.setText("Jenis Kelamin");

        txtFieldNamaPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldNamaPerawatActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel33.setText("Umur");

        jLabel34.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel34.setText("No. Telepon");

        txtFieldUmurPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldUmurPerawatActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel35.setText("Alamat");

        txtFieldAlamatPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldAlamatPerawatActionPerformed(evt);
            }
        });

        cariIDPerawat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cariIDPerawat.setText("Cari");
        cariIDPerawat.setToolTipText("");
        cariIDPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariIDPerawatActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel36.setText("Unit Bagian");

        jLabel37.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        jLabel37.setText("Shift");

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

        btnUpdatePerawat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdatePerawat.setText("UPDATE");
        btnUpdatePerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePerawatActionPerformed(evt);
            }
        });

        jComboBoxUnitBagianPerawat.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N
        jComboBoxUnitBagianPerawat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UMUM", "IGD", "ICU", "RO" }));
        jComboBoxUnitBagianPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUnitBagianPerawatActionPerformed(evt);
            }
        });

        jComboBoxJenisKelaminPerawat.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N
        jComboBoxJenisKelaminPerawat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        jComboBoxJenisKelaminPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxJenisKelaminPerawatActionPerformed(evt);
            }
        });

        jComboBoxShiftPerawat.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N
        jComboBoxShiftPerawat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PAGI", "MALAM" }));
        jComboBoxShiftPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxShiftPerawatActionPerformed(evt);
            }
        });

        btnKembaliPerawat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKembaliPerawat.setText("KEMBALI");
        btnKembaliPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliPerawatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel35))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFieldUmurPerawat)
                                    .addComponent(txtFieldAlamatPerawat)
                                    .addComponent(txtFieldNamaPerawat)
                                    .addComponent(txtFieldIDPerawat)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jComboBoxJenisKelaminPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(46, 46, 46)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFieldNoTelpPerawat)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBoxShiftPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxUnitBagianPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariIDPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(btnTambahPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdatePerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKembaliPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 393, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtFieldIDPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariIDPerawat))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtFieldNamaPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBoxJenisKelaminPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtFieldUmurPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtFieldAlamatPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldNoTelpPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(12, 12, 12)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jComboBoxUnitBagianPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jComboBoxShiftPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapusPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdatePerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembaliPerawat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        tabelDataPerawat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Perawat", "Nama Perawat", "Jenis Kelamin", "Umur", "Alamat", "No. Telepon", "Unit Bagian", "Shift"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tabelDataPerawat);
        if (tabelDataPerawat.getColumnModel().getColumnCount() > 0) {
            tabelDataPerawat.getColumnModel().getColumn(0).setResizable(false);
            tabelDataPerawat.getColumnModel().getColumn(1).setResizable(false);
            tabelDataPerawat.getColumnModel().getColumn(2).setResizable(false);
            tabelDataPerawat.getColumnModel().getColumn(3).setResizable(false);
            tabelDataPerawat.getColumnModel().getColumn(4).setResizable(false);
            tabelDataPerawat.getColumnModel().getColumn(5).setResizable(false);
            tabelDataPerawat.getColumnModel().getColumn(6).setResizable(false);
            tabelDataPerawat.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout perawatPanelLayout = new javax.swing.GroupLayout(perawatPanel);
        perawatPanel.setLayout(perawatPanelLayout);
        perawatPanelLayout.setHorizontalGroup(
            perawatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perawatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(perawatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(perawatPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        perawatPanelLayout.setVerticalGroup(
            perawatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(perawatPanelLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainPanel.add(perawatPanel, "card3");

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnUpdatePasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePasienActionPerformed
        btnUpdatePasien.addActionListener(e -> updatePasien());
    }//GEN-LAST:event_btnUpdatePasienActionPerformed

    private void jButtonKePanelPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKePanelPerawatActionPerformed
        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(perawatPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_jButtonKePanelPerawatActionPerformed

    private void jButtonKePanelDokoterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKePanelDokoterActionPerformed
        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(dokterPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_jButtonKePanelDokoterActionPerformed

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
        loadDataRekamMedis();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTAMBAHRekamMedisActionPerformed

    private void btnHAPUSRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHAPUSRekamMedisActionPerformed
        int selectedRow = tableRekamMedis.getSelectedRow();
        loadDataRekamMedis();
        if (selectedRow != -1) {
            String id = tableRekamMedis.getValueAt(selectedRow, 0).toString();
            System.out.println("ID yang akan dihapus: [" + id + "]");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
            String user = "naila01";
            String password = "root";

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "DELETE FROM RekamMedis WHERE ID_REKAM_MEDIS = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Hapus dari JTable setelah sukses hapus dari DB
                    DefaultTableModel model = (DefaultTableModel) tableRekamMedis.getModel();
                    model.removeRow(selectedRow);
                    System.out.println("Data berhasil dihapus dari database.");
                } else {
                    System.out.println("Data tidak ditemukan di database.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Gagal menghapus dari database: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
        }
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
        loadDataRuangan();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTAMBAHManagementRuanganActionPerformed

    private void btnHAPUSManagementRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHAPUSManagementRuanganActionPerformed
        int selectedRow = tableManagementRuangan.getSelectedRow();

        if (selectedRow != -1) {
            String id = tableManagementRuangan.getValueAt(selectedRow, 0).toString(); // ambil ID dari baris yang dipilih

            // Hapus dari JTable
            DefaultTableModel model = (DefaultTableModel) tableManagementRuangan.getModel();
            model.removeRow(selectedRow);

            String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
            String user = "naila01";
            String password = "root";

            // Hapus dari database
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "DELETE FROM RUANGAN WHERE ID_RUANGAN = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data berhasil dihapus dari database.");
                } else {
                    System.out.println("Data tidak ditemukan di database.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Gagal menghapus dari database: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
        }  // TODO add your handling code here:
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
        loadDataInventaris();
        String id = ID_ObatTextField.getText().trim();
        String nama = (String) jComboBoxNamaObat.getSelectedItem();
        boolean tidakTersedia = JCheckBoxTidaktersedia.isSelected();
        int jumlah = tidakTersedia ? 0 : (Integer) jSpinnerBanyakObat.getValue();
        String harga = TextFieldHargaObat.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID Obat tidak boleh kosong!");
            ID_ObatTextField.requestFocus();
            return;
        }
        if (nama == null || nama.equals("Pilih Obat")) {
            JOptionPane.showMessageDialog(this, "Silakan pilih Nama Obat!");
            jComboBoxNamaObat.requestFocus();
            return;
        }
        DefaultTableModel model_TambahinObat = (DefaultTableModel) jTable_Obat.getModel();
        model_TambahinObat.addRow(new Object[]{
            id,
            nama,
            tidakTersedia ? "Tidak Tersedia" : "Tersedia",
            jumlah,
            harga
        });

        ID_ObatTextField.setText("");
        TextFieldHargaObat.setText("");
        jSpinnerBanyakObat.setValue(0);
        JCheckBoxTidaktersedia.setSelected(false);
        jComboBoxNamaObat.setSelectedIndex(0);

        String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
        String user = "naila01"; // replace this
        String password = "root"; // replace this

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO Inventaris (ID_OBAT, NAMA_OBAT, TIDAK_TERSEDIA, JUMLAH, HARGA) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, nama);
            stmt.setBoolean(3, tidakTersedia);
            stmt.setInt(4, jumlah);
            stmt.setString(5, harga);
            stmt.executeUpdate();
            System.out.println("Data berhasil disimpan ke database.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menyimpan ke database: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahObatActionPerformed

    private void btnHapusObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusObatActionPerformed
        loadDataInventaris();
        btnHapusObat.addActionListener(e -> {
            int selectedRow = jTable_Obat.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) jTable_Obat.getModel();

                String idObat = (String) model.getValueAt(selectedRow, 0);

                String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
                String user = "naila01";
                String password = "root";

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String sql = "DELETE FROM Inventaris WHERE ID_OBAT = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, idObat);
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Obat berhasil dihapus dari database dan tabel.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Data tidak ditemukan di database.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + ex.getMessage());
                }
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
        loadDataTransaksi();
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

        String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
        String user = "naila01";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO Transaksi (ID_TRANSAKSI, TANGGAL, TOTAL) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, tanggal);
            stmt.setString(3, total);
            stmt.executeUpdate();
            System.out.println("Data berhasil disimpan ke database.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menyimpan ke database: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTAMBAHTransaksiActionPerformed

    private void btnHAPUSTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHAPUSTransaksiActionPerformed
        loadDataTransaksi();
        btnHAPUSTransaksi.addActionListener(e -> {
            int selectedRow = JTableTransaksi.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) JTableTransaksi.getModel();

                String IDTransaksi = (String) model.getValueAt(selectedRow, 0);

                String url = "jdbc:sqlserver://localhost:1433;databaseName=bd;encrypt=true;trustServerCertificate=true;";
                String user = "naila01";
                String password = "root";

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String sql = "DELETE FROM Transaksi WHERE ID_TRANSAKSI = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, IDTransaksi);
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Transaksi berhasil dihapus dari database dan tabel.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Data tidak ditemukan di database.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus terlebih dahulu.");
            }
        });
    }//GEN-LAST:event_btnHAPUSTransaksiActionPerformed

    private void btNKEMBALITransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNKEMBALITransaksiActionPerformed
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(dashboardPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btNKEMBALITransaksiActionPerformed

    private void txtFieldIDPasien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldIDPasien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldIDPasien1ActionPerformed

    private void txtFieldNamaPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldNamaPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldNamaPasienActionPerformed

    private void txtFieldUmurPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldUmurPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldUmurPasienActionPerformed

    private void txtFieldAlamatPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldAlamatPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldAlamatPasienActionPerformed

    private void cariIDPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariIDPasienActionPerformed
        cariIDPasien.addActionListener(e -> cariPasien());
    }//GEN-LAST:event_cariIDPasienActionPerformed

    private void btnUpdateDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDokterActionPerformed
        btnUpdateDokter.addActionListener(e -> updateDokter());
    }//GEN-LAST:event_btnUpdateDokterActionPerformed

    private void btnHapusDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusDokterActionPerformed
        int selectedRow = tabelDataDokter.getSelectedRow();

        if (selectedRow != -1) {
            String id = tabelDataDokter.getValueAt(selectedRow, 0).toString(); // ambil ID dari baris yang dipilih

            // Hapus dari JTable
            DefaultTableModel model = (DefaultTableModel) tabelDataDokter.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
        }  // TODO add your handling code here:     
    }//GEN-LAST:event_btnHapusDokterActionPerformed

    private void btnTambahDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDokterActionPerformed
        tambahDokter();
    }//GEN-LAST:event_btnTambahDokterActionPerformed

    private void cariIDDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariIDDokterActionPerformed
        cariIDDokter.addActionListener(e -> cariDokter());
    }//GEN-LAST:event_cariIDDokterActionPerformed

    private void txtFieldAlamatDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldAlamatDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldAlamatDokterActionPerformed

    private void txtFieldUmurPasienDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldUmurPasienDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldUmurPasienDokterActionPerformed

    private void txtFieldNamaDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldNamaDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldNamaDokterActionPerformed

    private void txtFieldIdDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldIdDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldIdDokterActionPerformed

    private void txtFieldTunjanganSpesialisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldTunjanganSpesialisasiActionPerformed

    }//GEN-LAST:event_txtFieldTunjanganSpesialisasiActionPerformed

    private void jComboBoxSpesialisasiDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSpesialisasiDokterActionPerformed
        String spesialisasi = jComboBoxSpesialisasiDokter.getSelectedItem().toString();

        switch (spesialisasi) {
            case "UMUM":
                txtFieldTunjanganSpesialisasi.setText("Rp500.000");
                break;
            case "BEDAH":
                txtFieldTunjanganSpesialisasi.setText("Rp80.000.000");
                break;
            case "TULANG":
                txtFieldTunjanganSpesialisasi.setText("Rp60.000.000");
                break;
            case "GIGI":
                txtFieldTunjanganSpesialisasi.setText("Rp40.000.000");
                break;
            default:
                txtFieldTunjanganSpesialisasi.setText("Rp0");
                break;
        }
    }//GEN-LAST:event_jComboBoxSpesialisasiDokterActionPerformed

    private void jComboBoxJenisKelaminDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxJenisKelaminDokterActionPerformed

    }//GEN-LAST:event_jComboBoxJenisKelaminDokterActionPerformed

    private void jComboBoxJenisKelaminPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxJenisKelaminPasienActionPerformed

    }//GEN-LAST:event_jComboBoxJenisKelaminPasienActionPerformed

    private void jComboBoxJenisKelaminPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxJenisKelaminPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxJenisKelaminPerawatActionPerformed

    private void jComboBoxUnitBagianPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUnitBagianPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUnitBagianPerawatActionPerformed

    private void btnUpdatePerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePerawatActionPerformed
        btnUpdatePerawat.addActionListener(e -> updatePerawat());
    }//GEN-LAST:event_btnUpdatePerawatActionPerformed

    private void btnHapusPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPerawatActionPerformed
        int selectedRow = tabelDataPerawat.getSelectedRow();

        if (selectedRow != -1) {
            String id = tabelDataPerawat.getValueAt(selectedRow, 0).toString(); // ambil ID dari baris yang dipilih

            // Hapus dari JTable
            DefaultTableModel model = (DefaultTableModel) tabelDataPerawat.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
        }  // TODO add your handling code here:     
    }//GEN-LAST:event_btnHapusPerawatActionPerformed

    private void btnTambahPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPerawatActionPerformed
        tambahPerawat();
    }//GEN-LAST:event_btnTambahPerawatActionPerformed

    private void cariIDPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariIDPerawatActionPerformed
        cariIDPerawat.addActionListener(e -> cariPerawat());
    }//GEN-LAST:event_cariIDPerawatActionPerformed

    private void txtFieldAlamatPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldAlamatPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldAlamatPerawatActionPerformed

    private void txtFieldUmurPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldUmurPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldUmurPerawatActionPerformed

    private void txtFieldNamaPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldNamaPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldNamaPerawatActionPerformed

    private void txtFieldIDPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldIDPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldIDPerawatActionPerformed

    private void jComboBoxShiftPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxShiftPerawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxShiftPerawatActionPerformed

    private void btnKembaliDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliDokterActionPerformed
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(pegawaiPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnKembaliDokterActionPerformed

    private void btnKembaliPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliPerawatActionPerformed
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(pegawaiPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnKembaliPerawatActionPerformed

    private void jButtonRefreshDataPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshDataPegawaiActionPerformed
        tampilkanSemuaPegawai();
    }//GEN-LAST:event_jButtonRefreshDataPegawaiActionPerformed

    private void jButtonCariPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCariPegawaiActionPerformed
        cariPegawai();
    }//GEN-LAST:event_jButtonCariPegawaiActionPerformed

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
    private javax.swing.JButton btnHapusDokter;
    private javax.swing.JButton btnHapusObat;
    private javax.swing.JButton btnHapusPasien;
    private javax.swing.JButton btnHapusPerawat;
    private javax.swing.JButton btnKembaliDokter;
    private javax.swing.JButton btnKembaliPerawat;
    private javax.swing.JButton btnTAMBAHManagementRuangan;
    private javax.swing.JButton btnTAMBAHRekamMedis;
    private javax.swing.JButton btnTAMBAHTransaksi;
    private javax.swing.JButton btnTambahDokter;
    private javax.swing.JButton btnTambahObat;
    private javax.swing.JButton btnTambahPasien;
    private javax.swing.JButton btnTambahPerawat;
    private javax.swing.JButton btnUpdateDokter;
    private javax.swing.JButton btnUpdatePasien;
    private javax.swing.JButton btnUpdatePerawat;
    private javax.swing.JButton cariIDDokter;
    private javax.swing.JButton cariIDPasien;
    private javax.swing.JButton cariIDPerawat;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JLabel diagnosaRekamMedis;
    private javax.swing.JPanel dokterPanel;
    private javax.swing.JLabel hargaRuanganManagementRuangan;
    private javax.swing.JLabel idRekamMedisRekamMeidis;
    private javax.swing.JTextField idRuanganManagementRuangan;
    private javax.swing.JLabel idRuanganjlabelManagementRuangan;
    private javax.swing.JButton jButtonCariPegawai;
    private javax.swing.JButton jButtonKePanelDokoter;
    private javax.swing.JButton jButtonKePanelPerawat;
    private javax.swing.JButton jButtonRefreshDataPegawai;
    private javax.swing.JComboBox<String> jComboBoxJenisKelaminDokter;
    private javax.swing.JComboBox<String> jComboBoxJenisKelaminPasien;
    private javax.swing.JComboBox<String> jComboBoxJenisKelaminPerawat;
    private javax.swing.JComboBox<String> jComboBoxNamaObat;
    private javax.swing.JComboBox<String> jComboBoxShiftPerawat;
    private javax.swing.JComboBox<String> jComboBoxSpesialisasiDokter;
    private javax.swing.JComboBox<String> jComboBoxTipeRuanganManagementRUangan;
    private javax.swing.JComboBox<String> jComboBoxUnitBagianPerawat;
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
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
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
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSpinner jSpinnerBanyakObat;
    private javax.swing.JTable jTableDataPegawai;
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
    private javax.swing.JPanel perawatPanel;
    private javax.swing.JPanel rekamMedisPanel;
    private javax.swing.JPanel ruanganPanel;
    private javax.swing.JTable tabelDataDokter;
    private javax.swing.JTable tabelDataPasien;
    private javax.swing.JTable tabelDataPerawat;
    protected javax.swing.JTable tableManagementRuangan;
    private javax.swing.JTable tableRekamMedis;
    private javax.swing.JLabel tanggalRekamMedis;
    private javax.swing.JTextField tanggalTextFieldRekamMedis;
    private javax.swing.JTextField tfHargaRuanganManagementRuangan;
    private javax.swing.JLabel tipeRuanganManajementRuangan;
    private javax.swing.JPanel transaksiPanel;
    private javax.swing.JTextField txtFieldAlamatDokter;
    private javax.swing.JTextField txtFieldAlamatPasien;
    private javax.swing.JTextField txtFieldAlamatPerawat;
    private javax.swing.JTextField txtFieldIDPasien1;
    private javax.swing.JTextField txtFieldIDPegawaiSEMUA;
    private javax.swing.JTextField txtFieldIDPerawat;
    private javax.swing.JTextField txtFieldIdDokter;
    private javax.swing.JTextField txtFieldNamaDokter;
    private javax.swing.JTextField txtFieldNamaPasien;
    private javax.swing.JTextField txtFieldNamaPegawai;
    private javax.swing.JTextField txtFieldNamaPerawat;
    private javax.swing.JTextField txtFieldNoTelpDokter;
    private javax.swing.JTextField txtFieldNoTelpPasien;
    private javax.swing.JTextField txtFieldNoTelpPerawat;
    private javax.swing.JTextField txtFieldTgglKeluarPasien;
    private javax.swing.JTextField txtFieldTgglMasukPasien;
    private javax.swing.JTextField txtFieldTunjanganSpesialisasi;
    private javax.swing.JTextField txtFieldUmurPasien;
    private javax.swing.JTextField txtFieldUmurPasienDokter;
    private javax.swing.JTextField txtFieldUmurPerawat;
    // End of variables declaration//GEN-END:variables
}

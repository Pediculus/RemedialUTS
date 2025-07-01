package com.mycompany.padelrentalform;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
 

public class PadelRentalForm extends JFrame{
   private JTable rentalTable;
   private DefaultTableModel tableModel;
   private ArrayList<PadelRental> rental ;
   private JTextField tfNama, tfNoHP, tfTanggal, tfJamMulai, tfJamSelesai;
   private JComboBox<String> lapanganOptions;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
   private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
   private JSpinner timeSpinner;
   private JSpinner timeSpinnerEnd;
   
   
   public PadelRentalForm() {
       rental = new ArrayList<>();
       setTitle("Form Sewa Lapangan Padel");
       setSize(400, 350);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLayout(new GridLayout(10, 2, 5, 5));
       setLocationRelativeTo(null);
       
       String[] columnNames = {"Nama", "No.Hp", "Tanggal", "Jam Mulai", "Jam Selesai", "Lapangan"};
       tableModel = new DefaultTableModel(columnNames, 0);
       rentalTable = new JTable(tableModel);
       
       tfNama = new JTextField();
       tfNoHP = new JTextField();
       JXDatePicker datePicker = new JXDatePicker();
       datePicker.setDate(new Date());
       datePicker.setFormats("dd-MM-yyyy");
       
       SpinnerDateModel timeModel = new SpinnerDateModel();
       timeSpinner = new JSpinner(timeModel);
       JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
       timeSpinner.setEditor(timeEditor);
       timeSpinner.setValue(new Date());
       
       SpinnerDateModel timeModelEnd = new SpinnerDateModel();
       timeSpinnerEnd = new JSpinner(timeModelEnd);
       JSpinner.DateEditor timeEditorEnd = new JSpinner.DateEditor(timeSpinnerEnd, "HH:mm");
       timeSpinnerEnd.setEditor(timeEditorEnd);
       timeSpinnerEnd.setValue(new Date());
        
       tfJamMulai = new JTextField("08:00");
       tfJamSelesai = new JTextField("09:00");

       
       lapanganOptions = new JComboBox<>(new String[]{ "Lapangan 1", "Lapangan 2", "Lapangan 3", "Lapangan 4" });

       JButton btnSubmit = new JButton("Simpan");
       JButton btnDelete = new JButton("Delete");
       JButton btnEdit = new JButton("Edit");
       JPanel formPanel = new JPanel();
       add(new JLabel("Nama Penyewa:")); add(tfNama);
       add(new JLabel("No HP:")); add(tfNoHP);
       add(new JLabel("Tanggal Sewa:")); add(datePicker);
       add(new JLabel("Jam Mulai:")); add(timeSpinner);
       add(new JLabel("Jam Selesai:")); add(timeSpinnerEnd);
       add(new JLabel("Lapangan:")); add(lapanganOptions);
       add(btnSubmit); add(new JLabel());
       add(btnDelete); add(new JLabel());
       add(btnEdit); add(new JLabel());
       
       rentalTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = rentalTable.getSelectedRow();
            if (selectedRow != -1) {
                
                tfNama.setText(rentalTable.getValueAt(selectedRow, 0).toString());
                tfNoHP.setText(rentalTable.getValueAt(selectedRow, 1).toString());      
            }
        });
       
       btnSubmit.addActionListener(e -> {
           try {
                String name = tfNama.getText();
                int phone = Integer.parseInt(tfNoHP.getText());
                String lapangan = (String) lapanganOptions.getSelectedItem();
                Date date = datePicker.getDate();
                String formattedDate = sdf.format(date);
                Date timeDate = (Date) timeSpinner.getValue();
                LocalTime time = timeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                
                Date timeDateEnd = (Date) timeSpinnerEnd.getValue();
                LocalTime timeEnd = timeDateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                
                PadelRental rentals = new PadelRental(name, phone, timeDate, time, timeEnd, lapangan);
                rental.add(rentals);
                for(int i = 0 ; i < 1; i++){
                    System.out.println(rentals.getNama() + rentals.getNoHp() + rentals.getLapangan() + rentals.getTanggalSewa() + rentals.getJamMulai() + rentals.getJamSelesai());
                }
                tableModel.addRow(new Object[]{name, phone, timeDate, time, timeEnd, lapangan});
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Data tidak valid!");
            }
           
            
      });
       
       btnDelete.addActionListener(e ->{
           int selectedRow = rentalTable.getSelectedRow();
            if (selectedRow != -1) {
                rental.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                tfNama.setText("");
                tfNoHP.setText("");
                
            } else {
                JOptionPane.showMessageDialog(this, "Pilih produk yang ingin dihapus!");
            }
       });
       
       btnEdit.addActionListener(e ->{
          int selectedRow = rentalTable.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    String name = tfNama.getText();
                    int phone = Integer.parseInt(tfNoHP.getText());
                    String lapangan = (String) lapanganOptions.getSelectedItem();
                    Date date = datePicker.getDate();
                    String formattedDate = sdf.format(date);
                    Date timeDate = (Date) timeSpinner.getValue();
                    LocalTime time = timeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                
                    Date timeDateEnd = (Date) timeSpinnerEnd.getValue();
                    LocalTime timeEnd = timeDateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

                    PadelRental selectedProduct = rental.get(selectedRow);
                    selectedProduct.setNama(name);
                    selectedProduct.setNoHp(phone);
                    selectedProduct.setTanggalSewa(date);
                    selectedProduct.setJamMulai(time);
                    selectedProduct.setJamSelesai(timeEnd);

                    // Update table row
                    tableModel.setValueAt(name, selectedRow, 0);
                    tableModel.setValueAt(phone, selectedRow, 1);
                    tableModel.setValueAt(formattedDate, selectedRow, 2);
                    tableModel.setValueAt(time, selectedRow, 3);
                    tableModel.setValueAt(timeEnd, selectedRow, 4);
                    tableModel.setValueAt(lapangan, selectedRow, 5);


                    tfNama.setText("");
                    tfNoHP.setText("");
                    

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "No.Telp harus berupa angka!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih produk yang ingin diubah!");
            } 
       });
    add(new JScrollPane(rentalTable), BorderLayout.SOUTH);
   }

   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PadelRentalForm().setVisible(true));
   }
}

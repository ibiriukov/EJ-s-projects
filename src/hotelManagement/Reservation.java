/**
 *
 * @author Ievgen Biriukov
 */

package hotelManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Reservation extends javax.swing.JFrame {

    /**
     * Creates new form reservation
     */
    public Reservation() {
        initComponents();
        connect();
        autoID();
        roomType();
        roomNumber();
        bedType();
        loadReservation();
    }
    
    Connection con;
    PreparedStatement pst;
    DefaultTableModel d;
    
     public void connect(){
        try{
            
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagement", "root", "");                
            
           } catch (ClassNotFoundException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {                    
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }                    
        
    }
     
     public void autoID(){
        
        try {
            Statement s  = con.createStatement();
            ResultSet rs = s.executeQuery("select MAX(reid) from reservation");
            rs.next();
            rs.getString("MAX(reid)");
            
            if(rs.getString("MAX(reid)") == null){
                jLabel12.setText("RE0001");
            }else{
                long id = Long.parseLong(rs.getString("MAX(reid)").substring(2,rs.getString("MAX(reid)").length()));
                id++;
                jLabel12.setText("RE" + String.format("%04d", id) );
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     
     public void roomType(){
        try {
            pst = con.prepareStatement("select Distinct rtype from room");
        
         ResultSet rs = pst.executeQuery();
         txtrtype.removeAllItems();
         
         while(rs.next()){
             txtrtype.addItem(rs.getString("rtype"));
                
         }
         
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void roomNumber(){
        try {
            pst = con.prepareStatement("select Distinct rid from room");
        
         ResultSet rs = pst.executeQuery();
         txtrnumber.removeAllItems();
         
         while(rs.next()){
             txtrnumber.addItem(rs.getString("rid"));
                
         }
         
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void bedType(){
        try {
            pst = con.prepareStatement("select Distinct btype from room");
        
         ResultSet rs = pst.executeQuery();
         txtbtype.removeAllItems();
         
         while(rs.next()){
             txtbtype.addItem(rs.getString("btype"));
                
         }
         
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void loadReservation(){
        int c;
        try {
            pst = con.prepareStatement("select * from reservation");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            c = rsmd.getColumnCount();
            
            d = (DefaultTableModel)jTable1.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
                Vector v2 = new Vector();
                for (int i =1; i<=c; i++){
                    v2.add(rs.getString("reid"));
                    v2.add(rs.getString("name"));
                    v2.add(rs.getString("address"));
                    v2.add(rs.getString("phone"));
                    
                    v2.add(rs.getString("checkin"));
                    v2.add(rs.getString("checkout"));
                    v2.add(rs.getString("roomtype"));
                    v2.add(rs.getString("roomno"));
                    v2.add(rs.getString("bedtype"));
                    v2.add(rs.getString("amount"));
                } 
                d.addRow(v2);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        txtphone = new javax.swing.JTextField();
        txtcheckin = new com.toedter.calendar.JDateChooser();
        txtcheckout = new com.toedter.calendar.JDateChooser();
        txtrtype = new javax.swing.JComboBox<>();
        txtrnumber = new javax.swing.JComboBox<>();
        txtbtype = new javax.swing.JComboBox<>();
        txtamount = new javax.swing.JTextField();
        jButton_save_ = new javax.swing.JButton();
        jButton2_edit_ = new javax.swing.JButton();
        jButton3_delete_ = new javax.swing.JButton();
        jButton4_clear_ = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Reservation");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Reservation #");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Address");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Phone #");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Check In");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Check Out");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Room Type");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("Room #");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setText("Bed Type");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("Amount");

        jButton_save_.setText("Save");
        jButton_save_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_save_ActionPerformed(evt);
            }
        });

        jButton2_edit_.setText("Edit");
        jButton2_edit_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_edit_ActionPerformed(evt);
            }
        });

        jButton3_delete_.setText("Delete");
        jButton3_delete_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3_delete_ActionPerformed(evt);
            }
        });

        jButton4_clear_.setText("Clear");
        jButton4_clear_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4_clear_ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RID", "Name", "address", "Phone#", "CheckIN", "CheckOUT", "RoomType", "Room#", "BedType", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 27)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("jLabel12");

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(533, 533, 533)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel2))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtrnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtrtype, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbtype, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton_save_, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2_edit_, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3_delete_)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton4_clear_, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel12))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel6))
                                    .addComponent(txtcheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addComponent(jLabel7))
                            .addComponent(txtcheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtrtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtrnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtbtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_save_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2_edit_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3_delete_)
                            .addComponent(jButton4_clear_)
                            .addComponent(jButton1))
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_save_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_save_ActionPerformed
            
            String resN = jLabel12.getText();
            String name = txtname.getText();
            String address = txtaddress.getText();
            String phone = txtphone.getText();
            
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = df1.format(txtcheckin.getDate());
            
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = df2.format(txtcheckout.getDate());
            
            String roomT = txtrtype.getSelectedItem().toString();
            String roomN = txtrnumber.getSelectedItem().toString();
            String bedT =  txtbtype.getSelectedItem().toString();
            
            String amount = txtamount.getText();
        try {  
            pst = con.prepareStatement("insert into reservation(reid, name, address, phone, checkin, "
                    + "checkout, bedtype, roomno, roomtype, amount ) values(?,?,?,?,?,?,?,?,?,?) ");
            pst.setString(1, resN);
            pst.setString(2, name);
            pst.setString(3, address);
            pst.setString(4, phone);    
           
            pst.setString(5, startDate);
            pst.setString(6, endDate);
            pst.setString(7, bedT);
            pst.setString(8, roomN);
            pst.setString(9, roomT);
            pst.setString(10, amount);
             
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Reservation added successfully");
            
            txtname.setText("");
            txtaddress.setText("");
            txtphone.setText("");
            
            txtcheckin.setCalendar(null);
            txtcheckout.setCalendar(null); 
            
            
            txtrtype.setSelectedIndex(-1);
            txtrnumber.setSelectedIndex(-1);
            txtbtype.setSelectedIndex(-1);
            txtamount.setText("");
            
            autoID();
           loadReservation();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }          
        
    }//GEN-LAST:event_jButton_save_ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        d = (DefaultTableModel)jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        
        jLabel12.setText(d.getValueAt(selectIndex, 0).toString());
        txtname.setText(d.getValueAt(selectIndex, 1).toString());
        txtaddress.setText(d.getValueAt(selectIndex, 2).toString());
        txtphone.setText(d.getValueAt(selectIndex, 3).toString());
       
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

            try {
                cal.setTime(df1.parse(d.getValueAt(selectIndex, 4).toString()));
                cal2.setTime(df1.parse(d.getValueAt(selectIndex, 5).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
            }                        
            txtcheckin.setCalendar(cal);
            txtcheckout.setCalendar(cal2);
            
         //
        txtrtype.setSelectedItem(d.getValueAt(selectIndex, 6));
        txtrnumber.setSelectedItem(d.getValueAt(selectIndex, 7)); 
        txtbtype.setSelectedItem(d.getValueAt(selectIndex, 8));        
        txtamount.setText(d.getValueAt(selectIndex, 9).toString()); 
        
        jButton_save_.setEnabled(false);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2_edit_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_edit_ActionPerformed
            String resN = jLabel12.getText();
            String name = txtname.getText();
            String address = txtaddress.getText();
            String phone = txtphone.getText();
            
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = df1.format(txtcheckin.getDate());
            
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = df2.format(txtcheckout.getDate());
            
            String roomT = txtrtype.getSelectedItem().toString();
            String roomN = txtrnumber.getSelectedItem().toString();
            String bedT =  txtbtype.getSelectedItem().toString();
            
            String amount = txtamount.getText();
        try {  
            pst = con.prepareStatement("update reservation set name = ?, address = ?, phone = ?, checkin =?,"
                    + "checkout = ?, bedtype = ?, roomno = ?, roomtype = ?, amount = ? where reid =?");
           // pst.setString(1, resN);
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setString(3, phone);    
           
            pst.setString(4, startDate);
            pst.setString(5, endDate);
            pst.setString(6, bedT);
            pst.setString(7, roomN);
            pst.setString(8, roomT);
            pst.setString(9, amount);
            pst.setString(10, resN);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Reservation edited successfully");
            
            txtname.setText("");
            txtaddress.setText("");
            txtphone.setText("");
            
            txtcheckin.setCalendar(null);
            txtcheckout.setCalendar(null); 
            
            txtrtype.setSelectedIndex(-1);
            txtrnumber.setSelectedIndex(-1);
            txtbtype.setSelectedIndex(-1);
            txtamount.setText("");
            
            jButton_save_.setEnabled(true);
            autoID();
            loadReservation();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }          
        
    }//GEN-LAST:event_jButton2_edit_ActionPerformed

    private void jButton4_clear_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4_clear_ActionPerformed
            txtname.setText("");
            txtaddress.setText("");
            txtphone.setText("");                                    
            
             
              
                                  
            txtcheckin.setCalendar(null);
            txtcheckout.setCalendar(null);    
            
            
            
            txtrtype.setSelectedIndex(-1);
            txtrnumber.setSelectedIndex(-1);
            txtbtype.setSelectedIndex(-1);
            txtamount.setText("");
            
            autoID();
            jButton_save_.setEnabled(true);
           // loadRoom();
    }//GEN-LAST:event_jButton4_clear_ActionPerformed

    private void jButton3_delete_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3_delete_ActionPerformed
        String resN = jLabel12.getText();
             
        try {  
            pst = con.prepareStatement("delete from reservation where reid =?");
            
            
            pst.setString(1, resN);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Reservation deleted successfully");
            
            txtname.setText("");
            txtaddress.setText("");
            txtphone.setText("");
            
            txtcheckin.setCalendar(null);
            txtcheckout.setCalendar(null); 
            
            txtrtype.setSelectedIndex(-1);
            txtrnumber.setSelectedIndex(-1);
            txtbtype.setSelectedIndex(-1);
            txtamount.setText("");
            
            jButton_save_.setEnabled(true);
            autoID();
            loadReservation();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }//GEN-LAST:event_jButton3_delete_ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reservation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2_edit_;
    private javax.swing.JButton jButton3_delete_;
    private javax.swing.JButton jButton4_clear_;
    private javax.swing.JButton jButton_save_;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtamount;
    private javax.swing.JComboBox<String> txtbtype;
    private com.toedter.calendar.JDateChooser txtcheckin;
    private com.toedter.calendar.JDateChooser txtcheckout;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtphone;
    private javax.swing.JComboBox<String> txtrnumber;
    private javax.swing.JComboBox<String> txtrtype;
    // End of variables declaration//GEN-END:variables
}

import javax.swing.*;
import java.awt.*;
import java.sql.*;
class EditRecord1 extends JFrame {
    EditRecord1(String Class)
    {
        Font f = new Font("Futura", Font.BOLD, 30);
        Font f2 = new Font("Calibri", Font.BOLD, 18);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        JLabel head=new JLabel("Edit Record" ,JLabel.CENTER);
        head.setFont(f);

        JLabel l1 = new JLabel("Student Name");
        JTextField t1 = new JTextField(50);

        JLabel l2 = new JLabel("Class:");
        JTextField t2=new JTextField(Class);

        JLabel l4 = new JLabel("Phone:");
        JTextField t4 = new JTextField(15);

        JLabel l5 = new JLabel("Field:");
        JComboBox<String> settings=new JComboBox<>(new String[]{"Student Name","Class","Student Address","Phone","Roll No.","Fee Details","Gender"});

        JButton b1 = new JButton("Edit");
        JButton b2 = new JButton("Back");



        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        l4.setFont(f2);
        t4.setFont(f2);
        l5.setFont(f2);
        settings.setFont(f2);
        b1.setFont(f3);
        b2.setFont(f3);

        Container c = getContentPane();
        c.setLayout(null);

        int labelX = 100, fieldX = 300, yStart = 80, width = 150, height = 30, gap = 40;

        head.setBounds(200, 10, 200, 40);

        l1.setBounds(labelX, yStart, width, height);
        t1.setBounds(fieldX, yStart, width, height);

        l2.setBounds(labelX, yStart + gap, width, height);
        t2.setBounds(fieldX, yStart + gap, width, height);

        l4.setBounds(labelX, yStart + 2 * gap, width, height);
        t4.setBounds(fieldX, yStart + 2 * gap, width, height);

        l5.setBounds(labelX, yStart + 3 * gap, width, height);
        settings.setBounds(fieldX,yStart+3*gap,width,height);

        b1.setBounds(150, yStart + 5 * gap, 120, 40);
        b2.setBounds(300, yStart + 5 * gap, 120, 40);

        b1.setBackground(new Color(70,21,102));
        b2.setBackground(new Color(70,21,102));

        b1.setForeground(new Color(249,249,249));
        b2.setForeground(new Color(249,249,249));

        // Create text fields


        JComboBox<String> combobox = new JComboBox<>(new String[]{"Five","Six","Seven","Eight","Nine","Ten"});
        // Create a panel and add components
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select a class:"));
        panel.add(combobox);

//        panel for genderpopup
        String gender1[]=new String[]{"Male","Female","Other"};
        JComboBox<String> genderBox=new JComboBox<>(gender1);
        JPanel panel1=new JPanel();
        panel1.add(new JLabel("Select a gender:"));
        panel1.add(genderBox);


        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));



        b1.addActionListener(
                a->{
                    if(t1.getText()==""||t4.getText()=="")
                    {
                        JOptionPane.showMessageDialog(null,"Please enter details");
                        return;
                    }

                    String url1= "jdbc:mysql://localhost:3306/rdclasses";
                    try (Connection con = DriverManager.getConnection(url1, "root", "R1a2j3#*")) {
                        String sql="select * from "+t2.getText()+ "th_std where name=? and phone=?";
                        try(PreparedStatement pst = con.prepareStatement(sql))
                        {
                            pst.setString(1,t1.getText());
                            pst.setInt(2,Integer.parseInt(t4.getText()));

                            ResultSet rs=pst.executeQuery();

                            if(!rs.next())
                            {
                                JOptionPane.showMessageDialog(null,"Record doesn't exist.");
                                return;
                            }
                        }
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }


                        // Dynamically add new components based on selected setting
                    if (settings.getSelectedItem().toString().equals("Student Name"))
                    {

                        String newname=JOptionPane.showInputDialog("Enter new name:","");

                        // Validate new name text field
                        if (!newname.isEmpty())
                        {
                            // SQL code to update student name
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "UPDATE " + t2.getText() + "th_std SET name=? WHERE phone=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(1, newname);
                                    pst.setString(2, t4.getText());
                                    pst.executeUpdate();
                                    t1.setText("");
                                    t4.setText("");
                                    JOptionPane.showMessageDialog(null,"Name changed Successfully");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Please enter name.");
                        }
                    }
                    // Repeat similar approach for other dynamic components based on selected setting

                    if(settings.getSelectedItem().toString()=="Class")
                    {
                            String address="";
                            String gender="";
                            String admit_date="";
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                        try(Connection con= DriverManager.getConnection(url,"root","R1a2j3#*"))
                        {
                            String sql = "select * from " + t2.getText() + "th_std where phone=?";
                            try (PreparedStatement pst = con.prepareStatement(sql))
                            {
                                pst.setString(1, t4.getText());
                                ResultSet rs = pst.executeQuery();
                                while (rs.next())
                                {
                                    address = rs.getString("address");
                                    gender = rs.getString("gender");
                                    admit_date = rs.getString("admit_date");
                                    JOptionPane.showMessageDialog(null,"details fetched");
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            e.getMessage();
                        }

                        try(Connection con= DriverManager.getConnection(url,"root","R1a2j3#*"))
                        {
                            String sql = "delete from " + t2.getText() + "th_std where phone=?";
                            try (PreparedStatement pst = con.prepareStatement(sql))
                            {
                                pst.setString(1, t4.getText());
                                pst.executeUpdate();
                                JOptionPane.showMessageDialog(null,"Details deleted from existing class.");
                            }
                        }
                        catch(Exception e)
                        {
                            e.getMessage();
                        }


                        int result=JOptionPane.showInternalConfirmDialog(null,panel,"Choose a class:",
                                JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(result==JOptionPane.OK_OPTION)
                        {
                            String newClass =  combobox.getSelectedItem().toString();

                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql2 = "insert into " + newClass.toLowerCase() + "th_std(name,admit_date,address,phone,gender) values(?,?,?,?,?)";
                                try (PreparedStatement pst2 = con.prepareStatement(sql2)) {
                                    pst2.setString(1, t1.getText());
                                    pst2.setString(2, admit_date);
                                    pst2.setString(3, address);
                                    pst2.setString(4, t4.getText());
                                    pst2.setString(5, gender);
                                    pst2.executeUpdate();
                                    t1.setText("");
                                    t4.setText("");
                                    JOptionPane.showMessageDialog(null,"Class changed successfully");

                                }


                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                                e.printStackTrace();
                            }


                        }
                    }
                    if (settings.getSelectedItem().toString().equals("Student Address"))
                    {

                        String newAddress=JOptionPane.showInputDialog("Enter new address:","");

                        // Validate new name text field
                        if (!newAddress.isEmpty())
                        {
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*"))
                            {
                                String sql="Update "+t2.getText()+"th_std set address=? where phone=?";
                                try(PreparedStatement pst=con.prepareStatement(sql))
                                {
                                    pst.setString(1,newAddress);
                                    pst.setString(2,t4.getText());
                                    pst.executeUpdate();
                                    t1.setText("");
                                    t4.setText("");
                                    JOptionPane.showMessageDialog(null,"Address changed successfully.");
                                }
                            }
                            catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Please enter an address.");
                        }
                    }

                    if (settings.getSelectedItem().toString().equals("Phone"))
                    {

                        String newPhone=JOptionPane.showInputDialog("Enter new phone:","");

                        // Validate new name text field
                        if (!newPhone.isEmpty())
                        {
                            int newphone=Integer.parseInt(newPhone);
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "Update " + t2.getText() + "th_std set phone=? where phone=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setInt(1, newphone);
                                    pst.setString(2, t4.getText());
                                    pst.executeUpdate();
                                    t1.setText("");
                                    t4.setText("");
                                    JOptionPane.showMessageDialog(null,"Phone number changed successfully.");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Please enter new Phone no.");
                        }
                    }

                    if(settings.getSelectedItem().toString()=="Fee Details") {
                        String paidfees = JOptionPane.showInputDialog("Enter fee paid:", "");
                        if (!paidfees.isEmpty()) {

                            int total_fees = 0;
                            int remaining = 0;
                            int fees_paid = 0;

                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "Select class, total_fees ,fees_paid from fee_details where phone=? and name=? and class=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setInt(1, Integer.parseInt(t4.getText()));
                                    pst.setString(2, t1.getText());
                                    pst.setString(3, Class);
                                    ResultSet rs = pst.executeQuery();
                                    while (rs.next()) {

                                        total_fees = rs.getInt("total_fees");
                                        fees_paid = rs.getInt("fees_paid");
                                    }
                                    remaining = total_fees - (Integer.parseInt(paidfees) + fees_paid);
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "insert into fee_details(class,total_fees,fees_paid,fees_remaining,phone,name) values (?,?,?,?,?,?)";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(6, t1.getText());
                                    pst.setString(5, t4.getText());
                                    pst.setInt(3, fees_paid + Integer.parseInt(paidfees));
                                    pst.setInt(4, remaining);
                                    pst.setString(1, Class);
                                    pst.setInt(2, total_fees);
                                    pst.executeUpdate();
                                    JOptionPane.showMessageDialog(null, "Fees Updated successfully");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Please enter details.");
                        }
                    }

                    if(t2.getText().equals("Gender")) {
                        int result=JOptionPane.showInternalConfirmDialog(null,panel1,"Choose a class:",
                                JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(result==JOptionPane.OK_OPTION)
                        {
                            String newGender =  genderBox.getSelectedItem().toString();
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "Update " + t2.getText() + " set gender=? where phone=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(1, newGender);
                                    pst.setString(2, t4.getText());
                                    pst.executeUpdate();
                                    t1.setText("");
                                    t4.setText("");
                                    JOptionPane.showMessageDialog(null,"Gender changed successfully.");

                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                        }
                    }

                    if(settings.getSelectedItem().toString()=="Roll no.")
                    {
                        String newroll=JOptionPane.showInputDialog("Enter new Roll No.:","");

                        // Validate new name text field
                        if (!newroll.isEmpty())
                        {
                            // SQL code to update student name
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "UPDATE " + t2.getText() + "th_std SET roll=? WHERE phone=? and name=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(1, newroll);
                                    pst.setString(2, t4.getText());
                                    pst.setString(3,t1.getText());
                                    pst.executeUpdate();
                                    t1.setText("");
                                    t4.setText("");
                                    JOptionPane.showMessageDialog(null,"Name changed Successfully");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Please enter name.");
                        }
                    }
                }
        );

        b2.addActionListener(
                a->{
                    new ManageStudentRecords1();
                    dispose();
                }
        );

        c.add(head);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(l4);
        c.add(t4);
        c.add(l5);
        c.add(settings);
        c.add(b1);
        c.add(b2);
        c.setLayout(new BorderLayout(20, 20));
        c.add(sidepanel,BorderLayout.EAST);




        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800,500);
        setLocationRelativeTo(null);

        setTitle("Edit Record");
    }
}
class EditRecord{
    public static void main(String[] args) {
        EditRecord1 obj=new EditRecord1("Five");
    }
}


import javax.swing.*;
import java.awt.*;
import java.sql.*;
class teachereditRecord1 extends JFrame {
    teachereditRecord1()
    {
        Font f = new Font("Futura", Font.BOLD, 30);
        Font f2 = new Font("Calibri", Font.BOLD, 20);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        JLabel head=new JLabel("Edit Records" ,JLabel.CENTER);
        head.setFont(f);

        JLabel l1 = new JLabel("Teacher Name:");
        JTextField t1 = new JTextField(50);

        JLabel l2 = new JLabel("Email:");
        JTextField t2= new JTextField(35);

        JLabel l4 = new JLabel("Phone:");
        JTextField t4 = new JTextField(15);

        JLabel field =new JLabel("Field:");
        JComboBox<String> settings=new JComboBox<>(new String[]{"Teacher Name","Email","Teacher Address","Phone","Salary","Gender","Class","Subject"});

        JButton b1 = new JButton("Edit");
        JButton b2 = new JButton("Back");

        JComboBox<String> Class=new JComboBox<>(new String[]{"Five","Six","Seven","Eight","Nine","Ten"});
        JPanel classs=new JPanel();
        classs.add(new JLabel("Select a Class:"));
        classs.add(Class);

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        l4.setFont(f2);
        t4.setFont(f2);
        field.setFont(f2);
        settings.setFont(f2);
        b1.setFont(f3);
        b2.setFont(f3);

        b1.setForeground(new Color(249,249,249));
        b2.setForeground(new Color(249,249,249));

        b1.setBackground(new Color(70,21,107));
        b2.setBackground(new Color(70,21,107));

        Container c = getContentPane();
        c.setLayout(null);

        int labelX = 100, fieldX = 300, yStart = 80, width = 150, height = 30, gap = 40;

        head.setBounds(150, 10, 200, 40);

        l1.setBounds(labelX, yStart, width, height);
        t1.setBounds(fieldX, yStart, width, height);

        l2.setBounds(labelX, yStart + gap, width, height);
        t2.setBounds(fieldX, yStart + gap, width, height);

        l4.setBounds(labelX, yStart + 2 * gap, width, height);
        t4.setBounds(fieldX, yStart + 2 * gap, width, height);

        settings.setBounds(fieldX,yStart+3*gap,width,height);
        field.setBounds(labelX,yStart+3*gap,width,height);

        b1.setBounds(150, yStart + 5 * gap, 120, 40);
        b2.setBounds(300, yStart + 5 * gap, 120, 40);

//        panel for genderpopup
        String gender1[]=new String[]{"Male","Female","Other"};
        JComboBox<String> genderBox=new JComboBox<>(gender1);
        JPanel panel1=new JPanel();
        panel1.add(new JLabel("Select a gender:"));
        panel1.add(genderBox);




        b1.addActionListener(
                a->{
                    if(t1.getText()==""||t4.getText()=="")
                    {
                        JOptionPane.showMessageDialog(null,"Please enter details");
                        return;
                    }


                    // Dynamically add new components based on selected setting
                    if (settings.getSelectedItem().toString().equals("Teacher Name"))
                    {

                        String newname=JOptionPane.showInputDialog("Enter new name:","");

                        // Validate new name text field
                        if (!newname.isEmpty())
                        {
                            // SQL code to update student name
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "UPDATE teacher_details SET name=? WHERE phone=?";
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

                    if(settings.getSelectedItem().toString().equals("Email"))
                    {
                        String newemail=JOptionPane.showInputDialog("Enter new email:","");
                        if(!newemail.isEmpty())
                        {
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "UPDATE teacher_details SET email=? WHERE phone=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(2, t4.getText());
                                    pst.setString(1,newemail);
                                    pst.executeUpdate();

                                    JOptionPane.showMessageDialog(null, "Email updated Successfully");

                                }
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Please enter an email");
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
                                String sql="Update teacher_details set address=? where phone=?";
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

                        String newPhone=JOptionPane.showInputDialog("Enter new address:","");

                        // Validate new name text field
                        if (!newPhone.isEmpty())
                        {
                            int newphone=Integer.parseInt(newPhone);
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "Update teacher_details set phone=? where phone=?";
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

                    if(settings.getSelectedItem().toString().equals("Salary"))
                    {
                        String newsalary=JOptionPane.showInputDialog("Enter new salary:","");
                        if (!newsalary.isEmpty())
                        {
                            int newSalary=Integer.parseInt(newsalary);
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "Update teacher_details set salary=? where phone=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setInt(1, newSalary);
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
                            JOptionPane.showMessageDialog(null,"Please enter some Salary");
                        }
                    }

                    if(settings.getSelectedItem().toString().equals("Gender")) {
                        int result=JOptionPane.showInternalConfirmDialog(null,panel1,"Choose a class:",
                                JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(result==JOptionPane.OK_OPTION)
                        {
                            String newGender =  genderBox.getSelectedItem().toString();
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "Update teacher_details set gender=? where phone=?";
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
                    if(settings.getSelectedItem().toString().equals("Class"))
                    {
                        String oldClass=JOptionPane.showInputDialog("Enter Class to be changed:","");
                        dispose();
                    }
                    if(settings.getSelectedItem().toString().equals("Subject"))
                    {
                        int result=JOptionPane.showInternalConfirmDialog(null,classs,"Choose a class:",
                                JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(result==JOptionPane.OK_OPTION)
                        {
                            String grade = Class.getSelectedItem().toString();
                            int result1 = JOptionPane.showInternalConfirmDialog(null, classs, "Choose new class:",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            String newgrade = Class.getSelectedItem().toString();
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "UPdate subject_class_allocated set class=? where class=grade phone=" + t4.getText();
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(1, newgrade);
                                    pst.executeUpdate();
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null,e.getMessage());
                            }
                        }
                    }

                    if(settings.getSelectedItem().toString().equals("Subject"))
                    {

                    }
                }
        );

        b2.addActionListener(
                a->{
                    new ManageTeacherRecords1();
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
        c.add(settings);
        c.add(b1);
        c.add(b2);
        c.add(field);
        c.setLayout(new BorderLayout(10, 10));
        c.add(sidepanel,BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(800,500);
        setTitle("New Admission");
    }
}
class teachereditRecord{
    public static void main(String[] args) {
        teachereditRecord1 obj=new teachereditRecord1();
    }
}



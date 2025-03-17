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
                        if (!newname.isEmpty()) {
                            String url = "jdbc:mysql://localhost:3306/rdclasses";

                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "SELECT * FROM teacher_details WHERE phone=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(1, t4.getText());

                                    ResultSet rs = pst.executeQuery();
                                    if (!rs.next()) {
                                        JOptionPane.showMessageDialog(null, "Record does not exists.");
                                    } else {
                                        String sql1 = "UPDATE teacher_details SET name=? WHERE phone=?";
                                        try (PreparedStatement pst1 = con.prepareStatement(sql1)) {
                                            pst1.setString(1, newname);
                                            pst1.setString(2, t4.getText());
                                            pst1.executeUpdate();
                                            t1.setText("");
                                            t4.setText("");
                                            t2.setText("");
                                            JOptionPane.showMessageDialog(null, "Name changed Successfully");
                                        }
                                    }
                                }
                            }
                            catch (Exception e) {
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
                                    t1.setText("");
                                    t4.setText("");
                                    t2.setText("");

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
                    if (settings.getSelectedItem().toString().equals("Teacher Address"))
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
                                    t2.setText("");
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
                                    t2.setText("");
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
                                    t2.setText("");
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
                                    t2.setText("");
                                    JOptionPane.showMessageDialog(null,"Gender changed successfully.");

                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                        }
                    }

                    if(settings.getSelectedItem().toString().equals("Class"))
                    {
                        int result=JOptionPane.showInternalConfirmDialog(null,classs,"Choose a class to be changed:",
                                JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(result==JOptionPane.OK_OPTION)
                        {
                            String grade = Class.getSelectedItem().toString();

                            String url = "jdbc:mysql://localhost:3306/rdclasses";

                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "SELECT * FROM subject_class_allocated WHERE phone=? AND class=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(1, t4.getText());
                                    pst.setString(2, grade);

                                    ResultSet rs=pst.executeQuery();
                                    if(!rs.next())
                                    {
                                        JOptionPane.showMessageDialog(null,"Record does not exists.");
                                    }
                                    else{
                                        int result1 = JOptionPane.showInternalConfirmDialog(null, classs, "Choose new class:",
                                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                        String newgrade = Class.getSelectedItem().toString();
                                        String sql1 = "UPdate subject_class_allocated set class=? where class=? and phone=?";
                                        try (PreparedStatement pst1 = con.prepareStatement(sql1)) {
                                            pst1.setString(1, newgrade);
                                            pst1.setString(2, grade);
                                            pst1.setString(3, t4.getText());
                                            pst1.executeUpdate();
                                            t1.setText("");
                                            t4.setText("");
                                            t2.setText("");
                                            JOptionPane.showMessageDialog(null,"Class updated succesfully");
                                        }
                                    }
                                }
                            }
                                catch (Exception e)
                                {
                                    JOptionPane.showMessageDialog(null,e.getMessage());
                                }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Please select a class.");
                        }
                    }

                    if(settings.getSelectedItem().toString().equals("Subject"))
                    {
                        int result = JOptionPane.showInternalConfirmDialog(
                                null, classs, "Choose a subject to be changed:",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String grade = Class.getSelectedItem().toString();
                            String url = "jdbc:mysql://localhost:3306/rdclasses";

                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "SELECT * FROM subject_class_allocated WHERE phone=? AND class=?";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(1, t4.getText());
                                    pst.setString(2, grade);

                                    ResultSet rs = pst.executeQuery();

                                    if (rs.next()) { // Ensure there's data before accessing
                                        String s1 = rs.getString("Subject1");
                                        String s2 = rs.getString("Subject2");
                                        String s3 = rs.getString("Subject3");
                                        String s4 = rs.getString("Subject4");
                                        String s5 = rs.getString("Subject5");

                                        // Ask user if they want to update or add a new subject
                                        String[] options = {"Update Subject", "Add New Subject", "Cancel"};
                                        int choice = JOptionPane.showOptionDialog(
                                                null, "Do you want to update or add a new subject?",
                                                "Subject Modification", JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, options, options[0]
                                        );

                                        if (choice == 1) { // User chose "Add New Subject"
                                            // Check if all subject slots are filled
                                            if (s1 != null && s2 != null && s3 != null && s4 != null && s5 != null) {
                                                JOptionPane.showMessageDialog(null, "All 5 subject slots are already filled!");
                                            } else {
                                                String newSubject = JOptionPane.showInputDialog("Enter new subject name:");

                                                // Determine the next available subject slot
                                                String updateQuery = null;
                                                if (s1 == null) updateQuery = "UPDATE subject_class_allocated SET Subject1=? WHERE phone=? AND class=?";
                                                else if (s2 == null) updateQuery = "UPDATE subject_class_allocated SET Subject2=? WHERE phone=? AND class=?";
                                                else if (s3 == null) updateQuery = "UPDATE subject_class_allocated SET Subject3=? WHERE phone=? AND class=?";
                                                else if (s4 == null) updateQuery = "UPDATE subject_class_allocated SET Subject4=? WHERE phone=? AND class=?";
                                                else if (s5 == null) updateQuery = "UPDATE subject_class_allocated SET Subject5=? WHERE phone=? AND class=?";

                                                if (updateQuery != null) {
                                                    try (PreparedStatement updatePst = con.prepareStatement(updateQuery)) {
                                                        updatePst.setString(1, newSubject);
                                                        updatePst.setString(2, t4.getText());
                                                        updatePst.setString(3, grade);
                                                        updatePst.executeUpdate();
                                                        t1.setText("");
                                                        t4.setText("");
                                                        t2.setText("");
                                                        JOptionPane.showMessageDialog(null, "Subject added successfully!");
                                                    }
                                                }
                                            }
                                        } else if (choice == 0) { // User chose "Update Subject"
                                            String oldSubject = JOptionPane.showInputDialog("Enter subject name to update:");
                                            String newSubject = JOptionPane.showInputDialog("Enter new subject name:");

                                            String updateQuery = "UPDATE subject_class_allocated SET " +
                                                    "Subject1 = CASE WHEN Subject1 = ? THEN ? ELSE Subject1 END, " +
                                                    "Subject2 = CASE WHEN Subject2 = ? THEN ? ELSE Subject2 END, " +
                                                    "Subject3 = CASE WHEN Subject3 = ? THEN ? ELSE Subject3 END, " +
                                                    "Subject4 = CASE WHEN Subject4 = ? THEN ? ELSE Subject4 END, " +
                                                    "Subject5 = CASE WHEN Subject5 = ? THEN ? ELSE Subject5 END " +
                                                    "WHERE phone = ? AND class = ?";
                                            try (PreparedStatement updatePst = con.prepareStatement(updateQuery)) {
                                                updatePst.setString(1, oldSubject);
                                                updatePst.setString(2, newSubject);
                                                updatePst.setString(3, oldSubject);
                                                updatePst.setString(4, newSubject);
                                                updatePst.setString(5, oldSubject);
                                                updatePst.setString(6, newSubject);
                                                updatePst.setString(7, oldSubject);
                                                updatePst.setString(8, newSubject);
                                                updatePst.setString(9, oldSubject);
                                                updatePst.setString(10, newSubject);
                                                updatePst.setString(11, t4.getText());
                                                updatePst.setString(12, grade);

                                                int rowsUpdated = updatePst.executeUpdate();
                                                if (rowsUpdated > 0) {
                                                    t1.setText("");
                                                    t4.setText("");
                                                    t2.setText("");
                                                    JOptionPane.showMessageDialog(null, "Subject updated successfully!");
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Subject not found for update!");
                                                }
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No such record exist.");
                                    }
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                            }
                        }
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
        setSize(800,500);
        setLocationRelativeTo(null);
        setTitle("Teacher Edit Record");
    }
}
class teachereditRecord{
    public static void main(String[] args) {
        teachereditRecord1 obj=new teachereditRecord1();
    }
}



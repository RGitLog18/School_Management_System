import javax.swing.*;
import java.awt.*;
import java.sql.*;

class NewAdmission1 extends JFrame{
    NewAdmission1(String Class)
    {
        Font f = new Font("Futura", Font.BOLD, 18);
        Font f2 = new Font("Calibri", Font.BOLD, 18);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        JLabel l1 = new JLabel("Student Name:");
        JTextField t1 = new JTextField(50);

        JLabel l2 = new JLabel("Class:");
        JTextField t2=new JTextField(Class);

        JLabel l9 =new JLabel("Roll No.:");
        JTextField t9=new JTextField(12);

        JLabel l3 = new JLabel("Student Address:");
        JTextField t3 = new JTextField(100);

        JLabel l4 = new JLabel("Phone:");
        JTextField t4 = new JTextField(100);

        JLabel l6 = new JLabel("Email");
        JTextField t6 = new JTextField(10);

        JLabel l8 = new JLabel("Fee Details:");
        JTextField t8 = new JTextField(10);

        JLabel l7 = new JLabel("Gender:");
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});

        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Back");

        JLabel title = new JLabel("Add Records", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        b1.setBackground(new Color(70,21,102));
        b2.setBackground(new Color(70,21,102));

        b1.setForeground(new Color(249,249,249));
        b2.setForeground(new Color(249,249,249));

        l1.setFont(f);
        t1.setFont(f2);
        l2.setFont(f);
        t2.setFont(f2);
        l3.setFont(f);
        t3.setFont(f2);
        l4.setFont(f);
        t4.setFont(f2);
        l6.setFont(f);
        t6.setFont(f2);
        l7.setFont(f);
        genderBox.setFont(f2);
        l8.setFont(f);
        t8.setFont(f2);
        l9.setFont(f);
        t9.setFont(f2);
        b1.setFont(f3);
        b2.setFont(f3);

        Container c = getContentPane();
        c.setLayout(null);

        int labelX = 80, fieldX = 250, yStart = 80, width = 250, height = 50, gap = 50;

        title.setBounds(300, 10, 200, 40);

        l1.setBounds(labelX, yStart, width, height);
        t1.setBounds(fieldX, yStart, width, height);

        l2.setBounds(labelX, yStart + gap, width, height);
        t2.setBounds(fieldX, yStart + gap, width, height);

        l3.setBounds(labelX, yStart + 2 * gap, width, height);
        t3.setBounds(fieldX, yStart + 2 * gap, width, height);

        l9.setBounds(labelX, yStart + 3 * gap, width, height);
        t9.setBounds(fieldX, yStart + 3 * gap, width, height);

        l4.setBounds(labelX, yStart + 4 * gap, width, height);
        t4.setBounds(fieldX, yStart + 4 * gap, width, height);

        l6.setBounds(labelX, yStart + 5 * gap, width, height);
        t6.setBounds(fieldX, yStart + 5 * gap, width, height);

        l8.setBounds(labelX, yStart + 6 * gap, width, height);
        t8.setBounds(fieldX, yStart + 6 * gap, width, height);

        l7.setBounds(labelX, yStart + 7 * gap, width, height);
        genderBox.setBounds(fieldX, yStart + 7 * gap, width, height);


        b1.setBounds(150, 510, 120, 40);
        b2.setBounds(300, 510, 120, 40);

        b1.addActionListener(
                a-> {

                    if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty() ||
                            t6.getText().isEmpty() || genderBox.getSelectedItem().toString().isEmpty() || t8.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter complete details");
                        return;
                    }

                    String url = "jdbc:mysql://localhost:3306/rdclasses";
                    try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                        String sql="Select * from "+t2.getText()+"th_std where name=? and phone=?" ;
                        try(PreparedStatement pst=con.prepareStatement(sql))
                        {
                            pst.setString(1,t1.getText());
                            pst.setInt(2,Integer.parseInt(t4.getText()));

                            ResultSet rs=pst.executeQuery();

                            if(rs.next())
                            {
                                JOptionPane.showMessageDialog(null,"Record exists.");
                            }
                            else{
                                try (Connection con1 = DriverManager.getConnection(url, "root", "R1a2j3#*"))
                                {
                                    String sql1 = "insert into " + t2.getText() + "th_std(name,address,phone,gender,email,roll) values(?,?,?,?,?,?)";
                                    try (PreparedStatement pst1 = con1.prepareStatement(sql1)) {
                                        pst1.setString(1, t1.getText());
                                        pst1.setString(2, t3.getText());
                                        pst1.setString(3, t4.getText());
                                        pst1.setString(4, (String) genderBox.getSelectedItem());
                                        pst1.setString(5, t6.getText());
                                        pst1.setString(6, t9.getText());
                                        pst1.execute();

                                    }
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                    e.printStackTrace();
                                }


                                try (Connection con1 = DriverManager.getConnection(url, "root", "R1a2j3#*")) {

                                    String sql1 = "insert into fee_details(phone,total_fees,fees_paid,fees_remaining,class,name) values(?,?,?,?,?,?)";
                                    try (PreparedStatement pst2 = con1.prepareStatement(sql1)) {
                                        pst2.setString(1, t4.getText());  // Phone number
                                        pst2.setString(5, t2.getText());// Class
                                        pst2.setString(6, t1.getText());// name


                                        int totalFees = 0;

                                        switch (t2.getText()) {
                                            case "One":
                                                totalFees = 1000;
                                                break;
                                            case "Two":
                                                totalFees = 2000;
                                                break;
                                            case "Three":
                                                totalFees = 3000;
                                                break;
                                            case "Four":
                                                totalFees = 4000;
                                                break;
                                            case "Five":
                                                totalFees = 5000;
                                                break;
                                            case "Six":
                                                totalFees = 6000;
                                                break;
                                            case "Seven":
                                                totalFees = 7000;
                                                break;
                                            case "Eighth":
                                                totalFees = 8000;
                                                break;
                                            case "Nine":
                                                totalFees = 9000;
                                                break;
                                            case "Ten":
                                                totalFees = 10000;
                                                break;
                                            default:
                                                JOptionPane.showMessageDialog(null, "Invalid Class Entry");

                                        }

                                        int feesPaid = Integer.parseInt(t8.getText());
                                        int feesRemaining = totalFees - feesPaid;
                                        pst2.setInt(3,feesPaid);
                                        pst2.setInt(2, totalFees);         // Total Fees
                                        pst2.setInt(4, feesRemaining);

                                        pst2.execute();
                                        t1.setText("");
                                        t2.setText("");
                                        t3.setText("");
                                        t4.setText("");
                                        t6.setText("");
                                        t8.setText("");
                                        t9.setText("");

                                        JOptionPane.showMessageDialog(null, "Admission Successfull");


                                    }
                                }
                                catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                    e.printStackTrace();
                                }
                        }

                        }
                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                        e.printStackTrace();
                    }
                }
        );

        b2.addActionListener(
                a->{
                    new ManageStudentRecords1();
                    dispose();
                }
        );

        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(l3);
        c.add(t3);
        c.add(l4);
        c.add(t4);
        c.add(l6);
        c.add(t6);
        c.add(l7);
        c.add(genderBox);
        c.add(l8);
        c.add(t8);
        c.add(l9);
        c.add(t9);
        c.add(b1);
        c.add(b2);
        c.setLayout(new BorderLayout(20, 20));
        c.add(sidepanel,BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800,600);
        setLocationRelativeTo(null);
        setTitle("New Admission");
    }
}
class NewAdmission{
    public static void main(String[] args) {
        NewAdmission1 obj=new NewAdmission1("Five");
    }
}


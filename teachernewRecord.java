import java.sql.*;
import javax.swing.*;
import java.awt.*;

class teachernewRecord1 extends JFrame{
    teachernewRecord1()
    {
        Font f = new Font("Futura", Font.BOLD, 30);
        Font f2 = new Font("Calibri", Font.BOLD, 20);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        JLabel l1 = new JLabel("Teacher Name:");
        JTextField t1 = new JTextField(50);

        JLabel l2 = new JLabel("Email:");

        JTextField t2 = new JTextField(35);

        JLabel l3 = new JLabel("Teacher Address:");
        JTextField t3 = new JTextField(100);

        JLabel l4 = new JLabel("Phone:");
        JTextField t4 = new JTextField(15);

        JLabel l6 = new JLabel("Salary:");
        JTextField t6 = new JTextField(10);

        JLabel l7 = new JLabel("Gender:");
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"male", "female", "other"});

        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Back");

        JLabel title = new JLabel("New Record of Teacher", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        l3.setFont(f2);
        t3.setFont(f2);
        l4.setFont(f2);
        t4.setFont(f2);
        l6.setFont(f2);
        t6.setFont(f2);
        l7.setFont(f2);
        genderBox.setFont(f2);
        b1.setFont(f3);
        b2.setFont(f3);

        b1.setForeground(new Color(249,249,249));
        b2.setForeground(new Color(249,249,249));

        b1.setBackground(new Color(70,21,107));
        b2.setBackground(new Color(70,21,107));

        Container c = getContentPane();
        c.setLayout(null);

        int labelX = 100, fieldX = 300, yStart = 80, width = 150, height = 30, gap = 40;

        title.setBounds(100, 10, 350, 40);

        l1.setBounds(labelX, yStart, width, height);
        t1.setBounds(fieldX, yStart, width, height);

        l2.setBounds(labelX, yStart + gap, width, height);
        t2.setBounds(fieldX, yStart + gap, width, height);

        l3.setBounds(labelX, yStart + 2 * gap, width, height);
        t3.setBounds(fieldX, yStart + 2 * gap, width, height);

        l4.setBounds(labelX, yStart + 3 * gap, width, height);
        t4.setBounds(fieldX, yStart + 3 * gap, width, height);

        l6.setBounds(labelX, yStart + 4 * gap, width, height);
        t6.setBounds(fieldX, yStart + 4 * gap, width, height);

        l7.setBounds(labelX, yStart + 5 * gap, width, height);
        genderBox.setBounds(fieldX, yStart + 5 * gap, width, height);

        b1.setBounds(150, yStart + 6 * gap, 120, 40);
        b2.setBounds(300, yStart + 6 * gap, 120, 40);

        b1.addActionListener(
                a->{
                    String url="jdbc:mysql://localhost:3306/rdclasses";
                    try(Connection con=DriverManager.getConnection(url,"root","R1a2j3#*"))
                    {

                        String sql="insert into teacher_details(name,address,phone,email,gender,salary) values(?,?,?,?,?,?)";
                        try(PreparedStatement pst=con.prepareStatement(sql))
                        {
                            pst.setString(1,t1.getText());
                            pst.setString(2,t3.getText());
                            pst.setString(3,t4.getText());
                            pst.setString(4,t2.getText());
                            pst.setString(5,(String)genderBox.getSelectedItem());
                            pst.setString(6,t6.getText());
                            pst.execute();
                            JOptionPane.showMessageDialog(null,"Record Added");

                        }
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                        e.printStackTrace();
                    }
                }
        );

        b2.addActionListener(
                a->{
                    new ManageTeacherRecords1();
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
        c.add(b1);
        c.add(b2);
        c.setLayout(new BorderLayout(10, 10));
        c.add(sidepanel,BorderLayout.EAST);






        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(800,550);
        setTitle("New Admission");
    }
}

class teachernewRecord{
    public static void main(String[] args) {
        teachernewRecord1 obj=new teachernewRecord1();
    }
}
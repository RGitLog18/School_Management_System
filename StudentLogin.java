import javax.swing.*;
import java.awt.*;
import java.sql.*;

class StudentLogin1 extends JFrame{
    StudentLogin1 ()
    {
        Font f1=new Font("Calibri",Font.BOLD,20);
        Font f2=new Font("Calibri",Font.PLAIN,20);
        Font f3=new Font("Calibri",Font.BOLD,25);

        JLabel l1=new JLabel("Enter Name:");
        JTextField t1=new JTextField(20);

        JLabel l2=new JLabel("Enter Password.:");
        JPasswordField t2=new JPasswordField(20);

        JLabel l3=new JLabel("Enter Std:");
        JComboBox<String> t3=new JComboBox<>(new String[]{"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"});

        JButton b1=new JButton("Back");

        JButton b2=new JButton("Submit");
        ImageIcon image=new ImageIcon("C:/myprojects/rdclasses/logo1.png");
        Image imageIcon = image.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageIcon);

        JLabel label=new JLabel(image);

        JLabel sidelabel1=new JLabel("Learning");
        JLabel sidelabel2=new JLabel("today");
        JLabel sidelabel3=new JLabel("is");
        JLabel sidelabel4=new JLabel("Leading");
        JLabel sidelabel5=new JLabel("tomorrow.");

        sidelabel1.setForeground(new Color(249,249,249));
        sidelabel2.setForeground(new Color(249,249,249));
        sidelabel3.setForeground(new Color(249,249,249));
        sidelabel4.setForeground(new Color(249,249,249));
        sidelabel5.setForeground(new Color(249,249,249));
        sidelabel4.setBackground(new Color(70,21,102));
        sidelabel3.setBackground(new Color(70,21,102));
        sidelabel2.setBackground(new Color(70,21,102));
        sidelabel1.setBackground(new Color(70,21,102));
        sidelabel5.setBackground(new Color(70,21,102));
        t3.setBackground(new Color(249,249,249));
        b1.setForeground(new Color(249,249,249));
        b2.setForeground(new Color(249,249,249));
        b1.setBackground(new Color(70,21,102));
        b2.setBackground(new Color(70,21,102));

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));
        sidepanel.add(sidelabel5);
        sidepanel.add(sidelabel4);
        sidepanel.add(sidelabel3);
        sidepanel.add(sidelabel2);
        sidepanel.add(sidelabel1);

        sidelabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidelabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidelabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidelabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidelabel5.setAlignmentX(Component.CENTER_ALIGNMENT);

        l1.setBounds(100,50,250, 50);
        t1.setBounds(100,100,250,50);
        l2.setBounds(100,150,250,50);
        t2.setBounds(100,200,250,50);
        l3.setBounds(100,250,250,50);
        t3.setBounds(100,300,250,50);
        b2.setBounds(80,400,150,50);
        b1.setBounds(240,400,150,50);
        label.setBounds(610,50,90,90);
        sidelabel1.setBounds(610,160,150,50);
        sidelabel2.setBounds(610,230,150,50);
        sidelabel3.setBounds(610,300,150,50);
        sidelabel4.setBounds(610,370,150,50);
        sidelabel5.setBounds(610,440,150,50);

        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        l1.setFont(f1);
        t1.setFont(f2);
        l2.setFont(f1);
        l3.setFont(f1);
        t3.setFont(f2);
        b1.setFont(f3);
        b2.setFont(f3);
        t2.setFont(f2);
        sidelabel1.setFont(f3);
        sidelabel2.setFont(f3);
        sidelabel3.setFont(f3);
        sidelabel4.setFont(f3);
        sidelabel5.setFont(f3);

        b1.addActionListener(a->{
            new start1();
            dispose();

        });

        b2.addActionListener(
                a->{
                    if(t1.getText().isEmpty()&&t2.getText().toString().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null,"Please enter username");
                        return;
                    }
                    else
                    {
                        if(!t1.getText().isEmpty() && !t2.getText().toString().isEmpty()) {
                            String name = "";
                          int phone=0;
                            String url = "jdbc:mysql://localhost:3306/rdclasses";
                            try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                                String sql = "Select * from " + t3.getSelectedItem().toString() + "th_std where name=? and phone=? ";
                                try (PreparedStatement pst = con.prepareStatement(sql)) {
                                    pst.setString(1, t1.getText());
                                    pst.setString(2, t2.getText().toString());
                                    ResultSet rs = pst.executeQuery();

                                    if (rs.next()) {
                                        name = rs.getString("name");
                                        phone=rs.getInt("phone");
                                        t1.setText("");
                                        t2.setText("");
                                        JOptionPane.showMessageDialog(null, "Login Successfull");
                                    new student1(name, t3.getSelectedItem().toString(),phone);
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Enter valid details");
                                    }
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                        }
                    }
                }
        );





        Container c=getContentPane();
        c.setLayout(null);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(l3);
        c.add(t3);
        c.add(b1);
        c.add(b2);
        c.add(label);
        c.add(sidelabel1);
        c.add(sidelabel2);
        c.add(sidelabel3);
        c.add(sidelabel4);
        c.add(sidelabel5);
        c.setLayout(new BorderLayout(10, 10));
        c.add(sidepanel,BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800,600);
        setLocationRelativeTo(null);
        setTitle("Student Login");
    }
}

class StudentLogin {
    public static void main(String[] args) {
        StudentLogin1 obj=new StudentLogin1();
    }

}


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class ManageNotice1 extends JFrame {
    private JPanel cardContainer;  // Instance variable for holding notices

    ManageNotice1() {
        double balance = 0.0;
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        ImageIcon profile = new ImageIcon("C:/myprojects/rdclasses/profileicon.png");
        Image imageIcon1 = profile.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        profile = new ImageIcon(imageIcon1);

        JLabel prof = new JLabel(profile);

        JPanel sidepanel = new JPanel();
        sidepanel.setPreferredSize(new Dimension(250, 0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel, BoxLayout.Y_AXIS));

        JButton type = new JButton("Student Records");
        JButton type1 = new JButton("Teacher Records");

        JButton b5 = new JButton("Profile");
        JButton b10 = new JButton("Add");
        JButton b11 = new JButton("Log Out");

        b5.setFont(f2);
        b10.setFont(f2);
        b11.setFont(f2);
        type.setFont(f2);
        type1.setFont(f2);
        sidepanel.add(type);
        sidepanel.add(type1);


        prof.setBounds(70, 30, 90, 90);
        b10.setBounds(10, 150, 220, 45);
        type.setBounds(10, 205, 220, 45);
        type1.setBounds(10, 265, 220, 45);
        b5.setBounds(10, 320, 220, 45);
        b11.setBounds(10, 375, 220, 45);

        b5.setBackground(new Color(249, 249, 249));
        b10.setBackground(new Color(249, 249, 249));
        b11.setBackground(new Color(168, 7, 33));
        b11.setForeground(new Color(249, 249, 249));
        type.setBackground(new Color(249, 249, 249));
        type1.setBackground(new Color(249, 249, 249));

        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b10.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b11.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type1.setCursor(new Cursor(Cursor.HAND_CURSOR));


        Container c = getContentPane();
        c.setLayout(null);

        c.add(b5);
        c.add(b10);
        c.add(b11);
        c.add(type);
        c.add(type1);
        c.add(prof);


        c.setLayout(new BorderLayout(20, 20));
        c.add(sidepanel, BorderLayout.WEST);

        cardContainer = new JPanel();
        cardContainer.setLayout(new BoxLayout(cardContainer, BoxLayout.Y_AXIS));
        cardContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(cardContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        c.add(scrollPane, BorderLayout.CENTER);

        type.addActionListener(
                a -> {
                    new ManageStudentRecords1();
                    dispose();
                }
        );

        type1.addActionListener(
                a -> {
                    new ManageTeacherRecords1();
                    dispose();
                }
        );

        b5.addActionListener(
                a -> {
                    new AdmiManager1();
                    dispose();
                }
        );

        b11.addActionListener(
                a -> {
                    new AdmiManaLog1();
                    dispose();
                }
        );

        b10.addActionListener(
                a -> {
                    addNoticeToDatabase();

                }
        );

        String url = "jdbc:mysql://localhost:3306/rdclasses";
        try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
            String sql = "SELECT * FROM notices";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();

                if (!rs.next()) {
                    addCard("No notices yet.","System");
                } else {
                    do {
                        addCard(rs.getString("notices"),rs.getString("role"));
                    } while (rs.next());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        setVisible(true);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Admistration Manager");
    }

    private void addNoticeToDatabase() {
        // Ask for notice message
        String message = JOptionPane.showInputDialog(this, "Enter Notice:", "Add Notice", JOptionPane.PLAIN_MESSAGE);
        if (message == null || message.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Notice cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ask for role selection
        String[] roles = {"Student", "Teacher"};
        String role = (String) JOptionPane.showInputDialog(this, "Select Role:", "Role Selection",
                JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);

        if (role == null) {
            return; // User canceled
        }

        // Insert into database
        String url = "jdbc:mysql://localhost:3306/rdclasses";
        try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
            String sql = "INSERT INTO notices (role, notices) VALUES (?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, role);
                pst.setString(2, message);
                pst.executeUpdate();
                addCard(message, role); // Update UI
                JOptionPane.showMessageDialog(this, "Notice Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to add notice cards dynamically
    public void addCard(String description, String role) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.BLACK, 2, true)); // 2px black border with rounded corners
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(400, 80));

        JLabel titleLabel = new JLabel("Role: " + role);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel paragraph = new JLabel("<html><p style='width:380px;'>" + description + "</p></html>");
        paragraph.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(paragraph, BorderLayout.CENTER);

        cardContainer.add(Box.createVerticalStrut(15)); // Spacing
        cardContainer.add(card);
        cardContainer.revalidate();
        cardContainer.repaint();
    }


    }
class ManageNotice{
    public static void main(String[] args) {
        ManageNotice1 obj =new ManageNotice1();
    }
}
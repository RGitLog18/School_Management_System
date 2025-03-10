import javax.swing.*;
import java.sql.*;
import java.awt.*;

class teacher_notices1 extends JFrame {
    private JPanel cardContainer;  // Instance variable for holding notices

    teacher_notices1(String name, int phone) {
        Font f1 = new Font("Calibri", Font.BOLD, 20);
        Font f2 = new Font("Calibri", Font.PLAIN, 20);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        JPanel sidepanel = new JPanel();
        sidepanel.setPreferredSize(new Dimension(250, 0)); // height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel, BoxLayout.Y_AXIS));

        ImageIcon image = new ImageIcon("C:/myprojects/rdclasses/rdlogo.jpg");
        Image imageIcon = image.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageIcon);

        JLabel label = new JLabel(image);

        ImageIcon profile = new ImageIcon("C:/myprojects/rdclasses/profileicon.png");
        Image imageIcon1 = profile.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profile = new ImageIcon(imageIcon1);

        JLabel prof = new JLabel(profile);

        JButton b1 = new JButton("Assignments");
        JButton b2 = new JButton("Profile");
        JButton b3 = new JButton("Back");
        JButton b4 = new JButton("Log Out");

        // Panel to hold notice cards
        cardContainer = new JPanel();
        cardContainer.setLayout(new BoxLayout(cardContainer, BoxLayout.Y_AXIS)); // Vertical layout

        JScrollPane scrollPane = new JScrollPane(cardContainer);
        scrollPane.setBounds(270, 50, 600, 350); // Adjust position and size

        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);

        b1.setBackground(new Color(249, 249, 249));
        b2.setBackground(new Color(249, 249, 249));
        b3.setBackground(new Color(249, 249, 249));
        b4.setForeground(new Color(249, 249, 249));
        b4.setBackground(new Color(179, 22, 22));

        b1.setFont(f3);
        b2.setFont(f3);
        b3.setFont(f3);
        b4.setFont(f3);

//        label.setBounds(25, 0, 90, 90);
        prof.setBounds(80, 50, 80, 80);

        b1.setBounds(10, 150, 220, 50);
        b2.setBounds(10, 220, 220, 50);
        b4.setBounds(10, 290, 220, 50);


        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        b1.addActionListener(a -> {
            new teacher_assignment1(name,phone);
            dispose();
        });

        b2.addActionListener(a -> {
            new teacherprof1(name, phone);
            dispose();
        });

        b4.addActionListener(a -> {
            new teacherLogin1();
            dispose();
        });

        Container c = getContentPane();
        c.setBackground(new Color(249, 249, 249));
        c.setLayout(null);
        c.add(label);
        c.add(prof);
        c.add(b1);
        c.add(b2);
//        c.add(b3);
        c.add(b4);
        c.add(scrollPane);
        c.setLayout(new BorderLayout(20,20));// Add scroll pane for notices
        c.add(sidepanel, BorderLayout.WEST);

        // Fetch notices from the database and display them
        String url = "jdbc:mysql://localhost:3306/rdclasses";
        try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
            String sql = "SELECT * FROM notices WHERE role = 'teacher'";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();

                if (!rs.next()) {
                    addCard("No notices yet.");
                } else {
                    do {
                        addCard(rs.getString("notices"));
                    } while (rs.next());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Teacher Notices");
        setLocationRelativeTo(null);
    }

    // Method to add notice cards dynamically
    public void addCard(String description) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw shadow effect
                g2d.setColor(new Color(0, 0, 0, 50)); // Transparent black shadow
                g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 15, 15);
            }
        };
        card.setBackground(Color.WHITE);
//        card.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));
        card.setLayout(new BorderLayout());
        card.setOpaque(true);
        card.setPreferredSize(new Dimension(400, 80)); // Reduced size

        JLabel paragraph = new JLabel("<html><p style='width:380px;'>" + description + "</p></html>");
        paragraph.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(paragraph, BorderLayout.CENTER);

        // Add spacing and refresh UI
        cardContainer.add(Box.createVerticalStrut(15)); // Increased spacing
        cardContainer.add(card);
        cardContainer.revalidate();
        cardContainer.repaint();

    }

    public static void main(String[] args) {
        new teacher_notices1("Rema",1234);
    }
}

import javax.swing.*;
import java.sql.*;
import java.awt.*;

class adminNotice1 extends JFrame {
    private JPanel cardContainer;  // Instance variable for holding notices

    adminNotice1() {
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

        JButton type = new JButton("Student Records");
        JButton type1 = new JButton("Teacher Records");

        JButton b5 = new JButton("Profile");
        JButton b11 = new JButton("Log Out");

        // Panel to hold notice cards
        cardContainer = new JPanel();
        cardContainer.setLayout(new BoxLayout(cardContainer, BoxLayout.Y_AXIS)); // Vertical layout

        JScrollPane scrollPane = new JScrollPane(cardContainer);
        scrollPane.setBounds(270, 50, 600, 350); // Adjust position and size

        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b11.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        prof.setBounds(70, 30, 90, 90);
        type.setBounds(10, 265, 220, 45);
        type1.setBounds(10, 205, 220, 45);
        b5.setBounds(10, 150, 220, 45);
        b11.setBounds(10, 320, 220, 45);

        b5.setFont(f2);
        b11.setFont(f2);
        type.setFont(f2);
        type1.setFont(f2);

        prof.setBounds(80, 50, 80, 80);

        b5.setBackground(new Color(249, 249, 249));
        b11.setBackground(new Color(168, 7, 33));
        b11.setForeground(new Color(249, 249, 249));
        type.setBackground(new Color(249, 249, 249));
        type1.setBackground(new Color(249, 249, 249));

        Timer timer = new Timer(500, e -> fetchCardfromDb());
        timer.start();

        type.addActionListener(
                a -> {
                    new AdminStudentRecords1();
                    dispose();
                }
        );

        type1.addActionListener(
                a -> {
                    new adminTecRec1();
                    dispose();
                }
        );

        b5.addActionListener(
                a -> {
                    new Admin1();
                    dispose();
                }
        );

        b11.addActionListener(
                a -> {
                    new AdminLogin1();
                    dispose();
                }
        );

        Container c = getContentPane();
        c.setBackground(new Color(249, 249, 249));
        c.setLayout(null);
        c.add(label);
        c.add(b5);
        c.add(b11);
        c.add(type);
        c.add(type1);
        c.add(prof);
        c.add(scrollPane);
        c.setLayout(new BorderLayout(20,20));// Add scroll pane for notices
        c.add(sidepanel, BorderLayout.WEST);

        // Fetch notices from the database and display them
        fetchCardfromDb();

        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Admin Notice");
        setLocationRelativeTo(null);
    }

    public void fetchCardfromDb()
    {
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

                cardContainer.revalidate();
                cardContainer.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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
        new adminNotice1();
    }
}

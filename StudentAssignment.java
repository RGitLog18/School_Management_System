import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.File;
import java.sql.*;

class StudentAssignment extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;
    String name;
    String Class;

    StudentAssignment(String name, String Class, int phone) {
        setTitle("Student Assignments");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));

        Font f1 = new Font("Calibri", Font.BOLD, 20);
        Font f2 = new Font("Calibri", Font.PLAIN, 20);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        ImageIcon image = new ImageIcon("C:/myprojects/rdclasses/rdlogo.jpg");
        Image imageIcon = image.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageIcon);

        JLabel label = new JLabel(image);

        ImageIcon profile = new ImageIcon("C:/myprojects/rdclasses/profileicon.png");
        Image imageIcon1 = profile.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profile = new ImageIcon(imageIcon1);

        JLabel prof = new JLabel(profile);

        this.name=name;
        this.Class=Class;

        // Sidebar Panel
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(250, 0));
        sidePanel.setBackground(new Color(70, 21, 107));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        JButton b1 = new JButton("Profile");
        JButton b2 = new JButton("Notice");
        JButton b3 = new JButton("Fee Details");
        JButton b4 = new JButton("Log Out");
        JButton b5 = new JButton("Add Assignment");

        b1.setBackground(new Color(249, 249, 249));
        b2.setBackground(new Color(249, 249, 249));
        b3.setBackground(new Color(249, 249, 249));
        b5.setBackground(new Color(249, 249, 249));
        b4.setForeground(new Color(249, 249, 249));
        b4.setBackground(new Color(201, 19, 19));

        b1.setFont(f3);
        b2.setFont(f3);
        b3.setFont(f3);
        b4.setFont(f3);


        prof.setBounds(80, 50, 80, 80);

        b1.setBounds(10, 150, 220, 50);
        b2.setBounds(10, 220, 220, 50);
        b3.setBounds(10, 290, 220, 50);
        b4.setBounds(10, 360, 220, 50);
//        b5.setBounds(10, 430, 220, 50);

        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(201, 19, 19));

        // Table Model with Columns
        tableModel = new DefaultTableModel(new Object[]{"Subject", "Assignment", "Due Date"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only the submit button is editable
            }
        };

        table = new JTable(tableModel);
        table.getColumnModel().getColumn(1).setCellRenderer(new FileRenderer()); // Icon for Assignment
//        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
//        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor());
        table.setRowHeight(50);
        table.getTableHeader().setFont(f1);


        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.WEST);

        b5.addActionListener(e -> addAssignment());

        connectToDatabase();
        fetchAssignmentsFromDB(); // Fetch assignments when the GUI opens

        Timer timer = new Timer(500, e -> fetchAssignmentsFromDB());
        timer.start();

        b1.addActionListener(
                a->{
                  new student1(name,Class,phone);
                  dispose();
        }
        );

        b2.addActionListener(
                a->{
                    new student_notice1(name,Class,phone);
                    dispose();
                }
        );

        b3.addActionListener(
                a->{
                    new student_feedetails1(name,Class,phone);

                }
        );

        b4.addActionListener(
                a->{
                    new StudentLogin1();
                    dispose();
                }
        );

        Container c = getContentPane();
        c.setBackground(new Color(249, 249, 249));
        c.setLayout(null);

        c.add(prof);
        c.add(label);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);

        c.setLayout(new BorderLayout(20, 20));// Add scroll pane for notices
        c.add(sidePanel, BorderLayout.WEST);
        c.add(scrollPane);

        setVisible(true);
    }

    // Database Connection
    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdclasses", "root", "R1a2j3#*");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Fetch Assignments from Database and Add to Table
    private void fetchAssignmentsFromDB() {
        String url = "jdbc:mysql://localhost:3306/rdclasses";
        try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
            String query = "SELECT subject, file_path, due_date FROM assignments where class=?";
            try( PreparedStatement stmt = con.prepareStatement(query)){
                stmt.setString(1,Class);
//                stmt.setString(2,name);
                ResultSet rs = stmt.executeQuery();

                tableModel.setRowCount(0); // Clear table before adding new data
                while (rs.next()) {
                    String subject = rs.getString("subject");
                    String filePath = rs.getString("file_path"); // File path stored in DB
                    String dueDate = rs.getString("due_date");

                    File file = new File(filePath); // Convert path to File object
                    if (file.exists() && (file.getName().endsWith(".doc") || file.getName().endsWith(".docx"))) {
                        tableModel.addRow(new Object[]{subject, file, dueDate});
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to fetch assignments!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Add Assignment Manually
    private void addAssignment() {
        JTextField subjectField = new JTextField();
        JTextField dueDateField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Subject:"));
        panel.add(subjectField);
        panel.add(new JLabel("Due Date (YYYY-MM-DD):"));
        panel.add(dueDateField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Assignment Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Word Documents", "doc", "docx"));
            int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                tableModel.addRow(new Object[]{subjectField.getText(), selectedFile, dueDateField.getText(), "Submit"});
                saveAssignmentToDB(subjectField.getText(), selectedFile.getAbsolutePath(), dueDateField.getText());
            }
        }
    }

    // Save Assignment to Database
    private void saveAssignmentToDB(String subject, String filePath, String dueDate) {
        String url = "jdbc:mysql://localhost:3306/rdclasses";
        try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
            String query = "select subject, file_path, due_date from assignments where teacher_name=? and class=? ";
            try (PreparedStatement stmt =con.prepareStatement(query)){;
                stmt.setString(1, name);
                stmt.setString(2, Class);

                stmt.executeUpdate();
            }
        }

             catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to save assignment!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // File Renderer to Show Icons
    static class FileRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            if (value instanceof File) {
                File file = (File) value;
                setIcon(UIManager.getIcon("FileView.fileIcon"));
                setText(file.getName());
            } else {
                super.setValue(value);
            }
        }
    }

    // Button Renderer for Submit Button
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Submit");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Button Editor for Submit Button
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int selectedRow;

        public ButtonEditor() {
            super(new JTextField());
            button = new JButton("Submit");
//            button.addActionListener(e -> submitAssignment(selectedRow));
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            selectedRow = row;
            return button;
        }
    }

    // Handle Submission
    private void submitAssignment(int row) {
        JOptionPane.showMessageDialog(this, "Assignment Submitted: " + table.getValueAt(row, 1));
    }

    public static void main(String[] args) {
        new StudentAssignment("Divya Deshmukh", "Five", 41256378);
    }
}

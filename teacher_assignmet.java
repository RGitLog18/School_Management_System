import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class teacher_assignment1 extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    String name;
    int phone;
    teacher_assignment1(String name,int phone) {
        this.name=name;
        this.phone=phone;
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

        JButton b1 = new JButton("Profile");
        JButton b2 = new JButton("Notices");
        JButton b3 = new JButton("Add");
        JButton b4 = new JButton("Log Out");

        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);
        b4.setFont(f2);

        // Table model with "Assignments" column
        tableModel = new DefaultTableModel(new Object[]{"Class","Subject","Assignments","Due_Date"}, 0);
        // JTable with custom renderer
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(2).setCellRenderer(new FileRenderer());
        table.setRowHeight(50);
        table.getTableHeader().setFont(f1);

        JScrollPane scrollPane = new JScrollPane(table);

        // Enable drag and drop on the table
        new DropTarget(table, new FileDropListener());


        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);

        b1.setBackground(new Color(249, 249, 249));
        b2.setBackground(new Color(249, 249, 249));
        b3.setBackground(new Color(249, 249, 249));
        b4.setForeground(new Color(249, 249, 249));
        b4.setBackground(new Color(209, 15, 15));

//        label.setBounds(25, 0, 90, 90);
        prof.setBounds(80, 50, 80, 80);

        b1.setBounds(10, 150, 220, 50);
        b2.setBounds(10, 220, 220, 50);
        b3.setBounds(10, 290, 220, 50);
        b4.setBounds(10, 360, 220, 50);

        b1.setFont(f3);
        b2.setFont(f3);
        b3.setFont(f3);
        b4.setFont(f3);

        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        b1.addActionListener(a -> {
            new teacherprof1(name,phone);
            dispose();
        });

        b2.addActionListener(a -> {
            new teacher_notices1(name,phone);
            dispose();
        });

        b3.addActionListener(
                a->{
                    ArrayList<String> classOptions=new ArrayList<>();
                    ArrayList<String> subjectOptions=new ArrayList<>() ;
                    String url = "jdbc:mysql://localhost:3306/rdclasses";
                    try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                        String sql = "Select * from subject_class_allocated where phone=? ";
                        try (PreparedStatement pst = con.prepareStatement(sql)) {
                            pst.setInt(1, phone);
                            ResultSet rs = pst.executeQuery();

                            while (rs.next())
                            {
                                String classs=rs.getString("class");
                                String subj1=rs.getString("Subject1");
                                String subj2=rs.getString("Subject2");
                                String subj3=rs.getString("Subject3");
                                String subj4=rs.getString("Subject4");
                                String subj5=rs.getString("Subject5");

                                String[] row={subj1,subj2,subj3,subj4,subj5};
                                classOptions.add(classs);
                                subjectOptions.addAll(java.util.Arrays.asList(row));
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }

                    // Ensure the first item is not a placeholder
                    if (classOptions.isEmpty() || subjectOptions.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No classes or subjects found!", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Stop execution if no data is available
                    }
                    // Dropdown for class
                    DefaultComboBoxModel<String> model=new DefaultComboBoxModel(classOptions.toArray());
                    JComboBox<String> classDropdown=new JComboBox<>(model);


                    // Dropdown for subject
                    DefaultComboBoxModel<String> subjs=new DefaultComboBoxModel(subjectOptions.toArray());
                    JComboBox<String> subjectDropdown=new JComboBox<>(subjs);
                    subjectDropdown.setBackground(new Color(249,249,249));
                    classDropdown.setBackground(new Color(249,249,249));

                    // TextField for Due Date
                    JTextField dueDateField = new JTextField();
                    dueDateField.setPreferredSize(new Dimension(100, 22)); // Reduced width

                    // File chooser for selecting Word document
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Word Documents", "doc", "docx"));

                    // Panel for inputs with compact layout
                    JPanel panel = new JPanel(); // Reduced spacing
                    panel.setLayout(null);

                    JLabel classLabel = new JLabel("Class:");
                    classLabel.setFont(f3);
                    JLabel subjectLabel = new JLabel("Subject:");
                    subjectLabel.setFont(f3);
                    JLabel dateLabel = new JLabel("Due:");
                    dateLabel.setFont(f3);
                    JLabel fileLabel = new JLabel("File:");
                    classDropdown.setFont(f3);
                    subjectDropdown.setFont(f3);
                    dueDateField.setFont(f3);

                    JPanel sidepanel1 = new JPanel();
                    sidepanel1.setPreferredSize(new Dimension(20, 0)); // height auto adjust
                    sidepanel1.setBackground(new Color(70, 21, 107));
                    sidepanel1.setLayout(new BoxLayout(sidepanel, BoxLayout.Y_AXIS));

                    classLabel.setBounds(100,50,100,50);
                    classDropdown.setBounds(210,50,150,50);
                    subjectLabel.setBounds(100,120,100,50);
                    subjectDropdown.setBounds(210,120,150,50);
                    dateLabel.setBounds(100,190,100,50);
                    dueDateField.setBounds(210,190,150,50);



                    panel.add(classLabel);
                    panel.add(classDropdown);
                    panel.add(subjectLabel);
                    panel.add(subjectDropdown);
                    panel.add(dateLabel);
                    panel.add(dueDateField);
                    panel.add(fileChooser);

                    panel.setLayout(new BorderLayout(50,50));// Add scroll pane for notices
                    panel.add(sidepanel, BorderLayout.EAST);
                    panel.setPreferredSize(new Dimension(550,300));

                    int returnValue = JOptionPane.showConfirmDialog(this, panel, "Add Assignment", JOptionPane.OK_CANCEL_OPTION);

                    if (returnValue == JOptionPane.OK_OPTION) {
                        if (classDropdown.getSelectedItem() == null || subjectDropdown.getSelectedItem() == null) {
                            JOptionPane.showMessageDialog(this, "Please select a valid Class and Subject!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            addFileToTable((String) classDropdown.getSelectedItem(), (String) subjectDropdown.getSelectedItem(), selectedFile, dueDateField.getText());

                        }
                    }
                });

        b4.addActionListener(a -> {
            new teacherLogin1();
            dispose();
        });

        String url = "jdbc:mysql://localhost:3306/rdclasses";
        try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
            String query = "SELECT class, subject, file_path, due_date FROM assignments WHERE phone=?";

            try( PreparedStatement stmt = con.prepareStatement(query)){
                stmt.setInt(1,phone);
//                stmt.setString(2,name);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String className = rs.getString("class");
                    String subject = rs.getString("subject");
                    String filePath = rs.getString("file_path");
                    String dueDate = rs.getString("due_date");

                    File file = new File(filePath);

                    if (file.exists() && (file.getName().endsWith(".doc") || file.getName().endsWith(".docx"))) {
                        tableModel.addRow(new Object[]{className, subject, file, dueDate}); // Store as File object
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to fetch assignments!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Center align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);


        Container c = getContentPane();
        c.setBackground(new Color(249, 249, 249));
        c.setLayout(null);

        c.add(prof);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);

        c.setLayout(new BorderLayout(20,20));// Add scroll pane for notices
        c.add(sidepanel, BorderLayout.WEST);
        c.add(scrollPane, BorderLayout.CENTER);


        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Teacher Assignment");
        setLocationRelativeTo(null);
                }



    private void addFileToTable(String className, String subject, File file, String dueDate) {
        if (file.getName().endsWith(".doc") || file.getName().endsWith(".docx")) {
            tableModel.addRow(new Object[]{className, subject, file, dueDate}); // Store File, not String
            saveFileToDatabase(className, subject, file, dueDate);
        } else {
            JOptionPane.showMessageDialog(this, "Only .doc and .docx files are allowed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFileToDatabase(String className, String subject, File file, String dueDate) {
        String url = "jdbc:mysql://localhost:3306/rdclasses";
        String user = "root";
        String password = "R1a2j3#*";

        // Directory to save the file
        File destFolder = new File("C:/myprojects/rdclasses/assignments");
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        // Copy file to directory
        File destFile = new File(destFolder, file.getName());
        try {
            Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save file path in database
        String sql = "INSERT INTO assignments (class, subject, due_date, file_name, file_path, phone) VALUES (?, ?, ?, ?, ?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, className);
            pst.setString(2, subject);
            pst.setString(3, dueDate);
//            pst.setString(6, phone);
            pst.setInt(6, phone);
            pst.setString(4, file.getName());
            pst.setString(5, destFile.getAbsolutePath());

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "File successfully saved .");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    class FileRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            if (value instanceof File) {
                File file = (File) value;
                setFileIconAndText(file);
            } else if (value instanceof String) {
                File file = new File((String) value);
                setFileIconAndText(file);
            } else {
                super.setValue(value);
            }
        }

        private void setFileIconAndText(File file) {
            if (file.exists()) {
                Icon fileIcon = FileSystemView.getFileSystemView().getSystemIcon(file);
                setIcon(fileIcon);
                setText(file.getName());
            } else {
                setIcon(UIManager.getIcon("FileView.fileIcon"));
                setText(file.getName());
            }
        }
    }




    private class FileDropListener extends DropTargetAdapter {
        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                List<File> droppedFiles = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                // Prompt user for Class, Subject, and Due Date
                JTextField classField = new JTextField();
                JTextField subjectField = new JTextField();
                JTextField dueDateField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(3, 2));
                panel.add(new JLabel("Class:"));
                panel.add(classField);
                panel.add(new JLabel("Subject:"));
                panel.add(subjectField);
                panel.add(new JLabel("Due Date (YYYY-MM-DD):"));
                panel.add(dueDateField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Enter Details", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    for (File file : droppedFiles) {
                        addFileToTable(classField.getText(), subjectField.getText(), file, dueDateField.getText());
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error processing dropped files!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new teacher_assignment1("Rema",1234);
    }
}


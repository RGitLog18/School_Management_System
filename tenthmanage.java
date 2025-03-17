import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.*;

class Tenth1 extends JFrame {


    Tenth1() {
        Font titleFont = new Font("Futura", Font.BOLD, 40);
        Font tableFont = new Font("Calibri", Font.PLAIN, 18);
        Font buttonFont = new Font("Calibri", Font.BOLD, 20);

        JLabel title = new JLabel("10th STD", JLabel.CENTER);
        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(70, 21, 107));

        JButton b1 = new JButton("Back");
        b1.setFont(buttonFont);

        String[] columnNames = {"Name", "Admission Date", "Contact Number", "Email", "Address", "Gender", "Fee Details"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        JTable table = new JTable(tableModel);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(tableFont);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(70, 21, 107));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(196, 13, 13));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply the renderer to all columns
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Optional: Center align the header text
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


        table.getColumn("Fee Details").setCellRenderer(new ButtonRendererten());
        table.getColumn("Fee Details").setCellEditor(new ButtonEditorten(tableModel, table));

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(new Color(224, 224, 224));
        filterPanel.add(searchField,BorderLayout.WEST);
        filterPanel.add(searchButton,BorderLayout.WEST);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel topPanel = new JPanel(new BorderLayout());
//        topPanel.setBackground(new Color(0, 3, 6));
        topPanel.add(title, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
//        bottomPanel.setBackground(new Color(220, 48, 220));
        bottomPanel.add(b1);

        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.add(filterPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener()
                                                      {
                                                          public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
                                                          public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
                                                          public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }

                                                          private void filter() {
                                                              String text = searchField.getText();
                                                              if (text.trim().length() == 0) {
                                                                  sorter.setRowFilter(null);
                                                              } else {
                                                                  sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0)); // Filtering by Name column


                                                              }
                                                          }
                                                      }
        );

        searchButton.addActionListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
            }
        });


        b1.addActionListener(a -> {
            dispose();
        });

        String url = "jdbc:mysql://localhost:3306/rdclasses";
        try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
            String sql = "SELECT * FROM tenth_std";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    String s2 = rs.getString("name");
                    String s3 = rs.getString("admit_date");
                    int s4 = rs.getInt("phone");
                    String s5 = rs.getString("email");
                    String s6 = rs.getString("address");
                    String s7 = rs.getString("gender");
                    tableModel.addRow(new Object[]{s2, s3, s4, s5, s6, s7, "Fee Details"});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Container c = getContentPane();
        c.setLayout(new BorderLayout(20, 20));
        c.add(topPanel, BorderLayout.NORTH);
        c.add(centerPanel, BorderLayout.CENTER);
        c.add(bottomPanel, BorderLayout.SOUTH);

        setTitle("Tenth Standard Records");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

// Button Renderer
class ButtonRendererten extends JButton implements TableCellRenderer {
    public ButtonRendererten() {
        setText("Check");
        setBackground(new Color(68, 179, 49));
        setForeground(new Color(249,249,249));
        setFont(new Font("Calibri", Font.BOLD, 18));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}

// Button Editor
class ButtonEditorten extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private DefaultTableModel model;
    private int selectedRow;

    public ButtonEditorten(DefaultTableModel model, JTable table) {
        super(new JTextField());
        this.table = table;
        this.model = model;
        button = new JButton("Check");
        button.addActionListener(e -> openNewPage());
    }

    private void openNewPage() {
        String name = (String) model.getValueAt(selectedRow, 0);
        int contactNumber =(Integer) model.getValueAt(selectedRow, 2);
//        int phone=Integer.parseInt(contactNumber);
        String Class="Ten";
        new student_feedetails1(name, Class, contactNumber);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selectedRow = row;
        return button;
    }
}

class Tenth {
    public static void main(String[] args) {
        new Tenth1();
    }
}


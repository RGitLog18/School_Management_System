import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.*;

class teacherrecords1 extends JFrame {
    teacherrecords1() {
        Font titleFont = new Font("Futura", Font.BOLD, 40);
        Font tableFont = new Font("Calibri", Font.PLAIN, 18);
        Font buttonFont = new Font("Calibri", Font.BOLD, 25);

        JLabel title = new JLabel("Teacher Records ", JLabel.CENTER);

        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(70,21, 107));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Name", "Date of Joining", "Address", "Email", "Phone", "Salary"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
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
        table.setGridColor(new Color(224, 224, 224));



        JScrollPane scrollPane = new JScrollPane(table);

        JTextField t1 = new JTextField(10);
        JTextField t2 = new JTextField(10);

        JButton b1 = new JButton("Filter");
        b1.setFont(buttonFont);
        b1.setBackground(new Color(0, 153, 76));
        b1.setForeground(Color.WHITE);

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(new Color(224, 224, 224));
        filterPanel.add(searchField);
        filterPanel.add(searchButton);
        filterPanel.add(new JLabel("Min Salary:"));
        filterPanel.add(t1);
        filterPanel.add(new JLabel("Max Salary:"));
        filterPanel.add(t2);
        filterPanel.add(b1);

        JButton b2 = new JButton("Back");
        b2.setFont(buttonFont);
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(255, 51, 51));
        b2.setFocusPainted(false);
        b2.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0, 102, 204));
        topPanel.add(title, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(224, 224, 224));
        bottomPanel.add(b2);

        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.add(filterPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(20, 20));
        c.add(topPanel, BorderLayout.NORTH);
        c.add(centerPanel, BorderLayout.CENTER);
        c.add(bottomPanel, BorderLayout.SOUTH);

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

        b1.addActionListener(
                a->{

                    tableModel.setRowCount(0);
                    String str1=t1.getText();
                    String str2=t2.getText();

                    double min,max;

                    if(str1.isEmpty())
                    {
                        min=0.0;
                    }
                    else
                    {
                        min=Double.parseDouble(str1);
                    }

                    if(str2.isEmpty())
                    {
                        max=Double.MAX_VALUE;
                    }
                    else
                    {
                        max=Double.parseDouble(str2);
                    }

                    String url= "jdbc:mysql://localhost:3306/rdclasses";
                    try(Connection con= DriverManager.getConnection(url,"root","R1a2j3#*")) {
                        String sql = "Select * from teacher_details where salary between "+min+" and "+max;
                        try (PreparedStatement pst = con.prepareStatement(sql))
                        {
                            ResultSet rs = pst.executeQuery();
                            while(rs.next())
                            {
                                String s1=rs.getString("name");
                                String d1=rs.getString("doj");
                                String s2=rs.getString("address");
                                String s3=rs.getString("email");
                                int s4=rs.getInt("phone");
                                Double d2=rs.getDouble("salary");

                                tableModel.addRow(new Object[]{s1,d1,s2,s3,s4,d2});
                            }
                        }
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }

                }
        );

        b2.addActionListener(e -> {
            new ManageTeacherRecords1();
            dispose();
        });

        String url= "jdbc:mysql://localhost:3306/rdclasses";
        try(Connection con= DriverManager.getConnection(url,"root","R1a2j3#*")) {
            String sql = "Select * from teacher_details";
            try (PreparedStatement pst = con.prepareStatement(sql))
            {
                ResultSet rs = pst.executeQuery();
                while(rs.next())
                {
                    String s1=rs.getString("name");
                    String d1=rs.getString("doj");
                    String s2=rs.getString("address");
                    String s3=rs.getString("email");
                    int s4=rs.getInt("phone");
                    Double d2=rs.getDouble("salary");

                    tableModel.addRow(new Object[]{s1,d1,s2,s3,s4,d2});

                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }



        setTitle("Admin Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new teacherrecords1();
    }
}

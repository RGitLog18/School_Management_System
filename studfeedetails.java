import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.*;

class fee_details1 extends JFrame {
    fee_details1(String name,String Class, int phone) {
        Font titleFont = new Font("Futura", Font.BOLD, 40);
        Font tableFont = new Font("Calibri", Font.PLAIN, 18);
        Font buttonFont = new Font("Calibri", Font.BOLD, 20);

        JLabel title = new JLabel("Student Fee Details ", JLabel.CENTER);

        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(0, 102, 204));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Class", "Total Fees", "Fees Paid", "Fees Remaining","Date and Time"};
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
        table.getTableHeader().setBackground(new Color(70, 21, 102));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(224, 224, 224));



        JScrollPane scrollPane = new JScrollPane(table);

        JTextField t1 = new JTextField(10);
        JTextField t2 = new JTextField(10);

//        JButton b1 = new JButton("Filter");
//        b1.setFont(buttonFont);
//        b1.setBackground(new Color(0, 153, 76));
//        b1.setForeground(Color.WHITE);

//        JTextField searchField = new JTextField(20);
//        JButton searchButton = new JButton("Search");
//        JPanel filterPanel = new JPanel();
//        filterPanel.setBackground(new Color(224, 224, 224));
//        filterPanel.add(searchField);
//        filterPanel.add(searchButton);
//        filterPanel.add(new JLabel("Min Balance:"));
//        filterPanel.add(t1);
//        filterPanel.add(new JLabel("Max Balance:"));
//        filterPanel.add(t2);
//        filterPanel.add(b1);

        JButton b2 = new JButton("Back");
        b2.setFont(buttonFont);
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(255, 51, 51));
        b2.setFocusPainted(false);
        b2.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(70, 21, 102));
        topPanel.add(title, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(224, 224, 224));
        bottomPanel.add(b2);

        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
//        centerPanel.add(filterPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(20, 20));
        c.add(topPanel, BorderLayout.NORTH);
        c.add(centerPanel, BorderLayout.CENTER);
        c.add(bottomPanel, BorderLayout.SOUTH);


        String url= "jdbc:mysql://localhost:3306/rdclasses";
        try(Connection con= DriverManager.getConnection(url,"root","R1a2j3#*")) {
            String sql = "Select * from fee_details where phone=? ";
            try (PreparedStatement pst = con.prepareStatement(sql))
            {
                pst.setInt(1,phone);
                ResultSet rs = pst.executeQuery();
                tableModel.setRowCount(0); // Clear table before adding new data
                while(rs.next())
                {
                    int s1=rs.getInt("class");
                    int d1=rs.getInt("total_fees");
                    int s2=rs.getInt("fees_paid");
                    int s3=rs.getInt("fees_remaining");
                    String s4=rs.getString("date_and_time");

                    tableModel.addRow(new Object[]{s1,d1,s2,s3,s4});
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        b2.addActionListener(
                a->
                {
                    new student1(name,Class,phone);
                    dispose();
                }
        );

        setTitle("Student Fee Details");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new fee_details1("Rajshree","Eight",12345);
    }
}


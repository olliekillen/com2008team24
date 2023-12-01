package com.sheffield;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

/**
 * This class is the JFrame that holds all of the manager page UI components. This page allows a mananger to promote
 * or demote users depending on if they are a staff or customer user. The manager user cannot demote themselves
 *
 * @author Luke Parry
 */
public class ManagerPageUI extends JFrame {
    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    private Boolean isStaffPage;
    private int currentUserId;

    JPanel managerPanel = new JPanel(null);
    JLabel header = new JLabel();
    JButton leaveButton = new JButton();
    JLabel sidebar = new JLabel();
    JButton staffOnlyButton = new JButton();
    JButton customerOnlyButton = new JButton();
    JButton allButton = new JButton();
    JButton searchButton = new JButton();
    JTextField search = new JTextField();
    JLabel filterBar =  new JLabel();
    JLabel tableTitle = new JLabel();
    String[] tableHeadings = {"Name","User ID","Email","Staff User?","Promote/Demote"};
    DefaultTableModel tableModel = new DefaultTableModel(tableHeadings,0);
    JTable userTable = new JTable(tableModel);
    JScrollPane tableScrollPane = new JScrollPane(userTable);
    JPanel areaBorder = new JPanel(null);
    JLabel background = new JLabel();

    public void initFrame(Boolean isStaffPage, int userId) {
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((Math.round(xSize)),9000);
        this.setIsStaffPage(isStaffPage);
        initPanel(userId);
        this.add(managerPanel);
        this.setVisible(true);
    }

    public void initPanel(int userId) {
        /* For colours of each of the components:
         * Purple: 11854529
         * Red: 2743738
         * Light Green: 8741250
         * Dark Green: 14995422
         * Blue: 15440650
         * White: 1
         * Black: Don't enter anything (default).
         * Transparent?: 15658734
         */

        try {
            this.setCurrentUserId(userId);
            TableOperations top = new TableOperations();
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            dch.openConnection();
            top.addToTable(tableModel, dch.getConnection());
            dch.closeConnection();
        } catch (SQLException e) { e.printStackTrace(); }
        //userTable.add(tableModel);

        header.setLocation(0, 0);
        header.setSize((Math.round(xSize)), (int) (Math.round(ySize * 0.1)));
        header.setForeground(new Color(-1));
        header.setFont(new Font("Merryweather", Font.BOLD, 50));
        header.setOpaque(true);
        header.setBackground(new Color(-11854529));
        header.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        header.setText("Trains of Sheffield");
        header.setHorizontalAlignment(SwingConstants.CENTER);
        managerPanel.add(header);

        leaveButton.setLocation(0, (int) (Math.round(ySize * 0.096)));
        leaveButton.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.127)));
        leaveButton.setForeground(new Color(-1));
        leaveButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        leaveButton.addActionListener(e -> leaveButton_Click());
        leaveButton.setBackground(new Color(-15440650));
        leaveButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        leaveButton.setText("   Leave Manager Page");
        leaveButton.setHorizontalAlignment(SwingConstants.LEFT);
        managerPanel.add(leaveButton);

        sidebar.setLocation(0, (int) (Math.round(ySize * 0.1)));
        sidebar.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.9)));
        sidebar.setOpaque(true);
        sidebar.setBackground(new Color(-11854529));
        managerPanel.add(sidebar);

        staffOnlyButton.setLocation((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.097)));
        staffOnlyButton.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.121)));
        staffOnlyButton.setForeground( new Color(-1) );
        staffOnlyButton.setOpaque(true);
        staffOnlyButton.setFont(new Font("Merriweather", Font.BOLD, 14));
        staffOnlyButton.addActionListener(e -> staffOnlyButton_Click());
        staffOnlyButton.setBackground( new Color(0xFFD62246) );
        staffOnlyButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        staffOnlyButton.setText("Staff Only");
        managerPanel.add(staffOnlyButton);

        customerOnlyButton.setLocation((int) (Math.round(xSize * 0.3)),(int) (Math.round(ySize * 0.097)));
        customerOnlyButton.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.121)));
        customerOnlyButton.setForeground( new Color(-1) );
        customerOnlyButton.setOpaque(true);
        customerOnlyButton.setFont(new Font("Merriweather", Font.BOLD, 14));
        customerOnlyButton.addActionListener(e -> customerOnlyButton_Click());
        customerOnlyButton.setBackground( new Color(0xFFD62246) );
        customerOnlyButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        customerOnlyButton.setText("Customers Only");
        managerPanel.add(customerOnlyButton);

        allButton.setLocation((int) (Math.round(xSize * 0.44)),(int) (Math.round(ySize * 0.097)));
        allButton.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.121)));
        allButton.setForeground( new Color(-1) );
        allButton.setOpaque(true);
        allButton.setFont(new Font("Merriweather", Font.BOLD, 14));
        allButton.addActionListener(e -> allButton_Click());
        allButton.setBackground( new Color(0xFFD62246) );
        allButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        allButton.setText("All Users");
        managerPanel.add(allButton);

        searchButton.setLocation((int) (Math.round(xSize * 0.86)),(int) (Math.round(ySize * 0.14)));
        searchButton.setSize((int) (Math.round(xSize * 0.1)),(int) (Math.round(ySize * 0.037)));
        searchButton.setForeground( new Color(-1) );
        searchButton.setOpaque(true);
        searchButton.setFont(new Font("Merriweather", Font.BOLD, 14));
        searchButton.addActionListener(e -> searchButton_Click());
        searchButton.setBackground( new Color(-2743738) );
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 3));
        searchButton.setText("Search");
        managerPanel.add(searchButton);

        search.setLocation((int) (Math.round(xSize * 0.6)),(int) (Math.round(ySize * 0.14)));
        search.setSize((int) (Math.round(xSize * 0.26)),(int) (Math.round(ySize * 0.037)));
        search.setFont(new Font("Merriweather", Font.BOLD, 14));
        search.setBackground( new Color(0xFFFFFF) );
        search.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        search.setText("Search By Name...");
        managerPanel.add(search);

        filterBar.setLocation((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.097)));
        filterBar.setSize((int) (Math.round(xSize * 0.84)),(int) (Math.round(ySize * 0.126)));
        filterBar.setForeground( new Color(-1) );
        filterBar.setOpaque(true);
        filterBar.setBackground( new Color(-14995422) );
        filterBar.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        managerPanel.add(filterBar);

        tableTitle.setLocation((int) (Math.round(xSize * 0.54)), (int) (Math.round(ySize * 0.23)));
        tableTitle.setSize((int) (Math.round(xSize * 0.18)), (int) (Math.round(ySize * 0.08)));
        tableTitle.setForeground( new Color(-1) );
        tableTitle.setFont(new Font("Merriweather", Font.BOLD, 40));
        tableTitle.setBackground(new Color(-11854529));
        tableTitle.setText("Users");
        managerPanel.add(tableTitle);

        tableScrollPane.setLocation((int) (Math.round(xSize * 0.21)),(int) (Math.round(ySize * 0.31)));
        tableScrollPane.setSize((int) (Math.round(xSize * 0.75)),(int) (Math.round(ySize * 0.555)));
        tableScrollPane.setOpaque(true);
        tableScrollPane.setBackground( new Color(-2743738) );
        managerPanel.add(tableScrollPane);

        userTable.setLocation((int) (Math.round(xSize * 0.21)),(int) (Math.round(ySize * 0.31)));
        userTable.setSize((int) (Math.round(xSize * 0.75)),(int) (Math.round(ySize * 0.555)));
        userTable.setOpaque(true);
        userTable.setForeground( new Color(-1) );
        userTable.setFont(new Font("Merriweather", Font.BOLD, 14));
        userTable.setBackground( new Color(-2743738) );
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.getTableHeader().setResizingAllowed(false);
        userTable.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent m) {}
            public void mousePressed(MouseEvent m) {}
            public void mouseExited(MouseEvent m) {}
            public void mouseEntered(MouseEvent m) {}
            public void mouseClicked(MouseEvent m) {
                try {
                    TableOperations top = new TableOperations();
                    DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
                    dch.openConnection();
                    int selectedRowValue = userTable.getSelectedRow();
                    int selectedColumnValue = userTable.getSelectedColumn();
                    String promoteOrDemote = userTable.getValueAt(selectedRowValue,4).toString();
                    String userID = userTable.getValueAt(selectedRowValue,1).toString();
                    if (selectedColumnValue == 4) {
                        top.promoteOrDemote(promoteOrDemote.equals("Click to Promote"), Integer.parseInt(userID), dch.getConnection());
                        top.removeFromTable(tableModel);
                        top.addToTable(tableModel, dch.getConnection());
                    }
                    dch.closeConnection();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        });

        areaBorder.setLocation((int) (Math.round(xSize * 0.18)),(int) (Math.round(ySize * 0.24)));
        areaBorder.setSize((int) (Math.round(xSize * 0.8)),(int) (Math.round(ySize * 0.655)));
        areaBorder.setOpaque(true);
        areaBorder.setBackground( new Color(-14995422) );
        managerPanel.add(areaBorder);

        background.setLocation(0,0);
        background.setSize((Math.round(xSize)),9000);
        background.setOpaque(true);
        background.setBackground( new Color(-8741250) );
        managerPanel.add(background);

        managerPanel.setVisible(true);
    }


    public Boolean getIsStaffPage() { return this.isStaffPage; }
    public void setIsStaffPage(Boolean isStaffPage) { this.isStaffPage = isStaffPage; }
    public int getCurrentUserId() { return this.currentUserId; }
    public void setCurrentUserId(int currentUserId) { this.currentUserId = currentUserId; }

    public void leaveButton_Click() {
        ProductPageUI productPageUI = new ProductPageUI();
        productPageUI.initFrame(getIsStaffPage(), getCurrentUserId());
        this.dispose();
    }
    public void searchButton_Click() {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            TableOperations top = new TableOperations();
            dch.openConnection();
            top.userSearch(tableModel, search, dch.getConnection());
            dch.closeConnection();
        } catch(SQLException e) { e.printStackTrace(); }
    }
    public void staffOnlyButton_Click() {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            TableOperations top = new TableOperations();
            dch.openConnection();
            top.removeFromTable(tableModel);
            top.addStaffToTable(tableModel, dch.getConnection());
            dch.closeConnection();
        } catch(SQLException e) { e.printStackTrace(); }
    }
    public void customerOnlyButton_Click() {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            TableOperations top = new TableOperations();
            dch.openConnection();
            top.removeFromTable(tableModel);
            top.addCustomersToTable(tableModel, dch.getConnection());
            dch.closeConnection();
        } catch(SQLException e) { e.printStackTrace(); }
    }
    public void allButton_Click() {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            TableOperations top = new TableOperations();
            dch.openConnection();
            top.removeFromTable(tableModel);
            top.addToTable(tableModel, dch.getConnection());
            dch.closeConnection();
        } catch(SQLException e) { e.printStackTrace(); }
    }
}

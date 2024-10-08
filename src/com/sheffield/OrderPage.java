/**
 * A Class that extends JFrame to create the order page
 *
 * @author Ollie Killen
 */

package com.sheffield;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class OrderPage extends JFrame {

	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	private Boolean isStaffPage;
	private Boolean isStaff;
	private Boolean isManager;
	private int currentUserId;

	JPanel orderPagePanel = new JPanel(null);

	JLabel pageTitle = new JLabel();
	JButton customerButton = new JButton();
    JButton orderButton = new JButton();
	JButton orderButton1 = new JButton();
	JButton fulfilButton = new JButton();
    JButton deleteButton = new JButton();
	JLabel orderSidebar = new JLabel();
	JLabel orderTitleText = new JLabel();

	JLabel orderDetailBackGround = new JLabel();
	JLabel acountPageBackground = new JLabel();

	JLabel padding = new JLabel("");
	JTextArea textArea = new JTextArea(100, 100);

    ArrayList<Order> orders = new ArrayList<Order>();
	OrderTable orderDetails = null;

	//Sets layout and invisibility, sets whether the page is a staff or user page
	public void initFrame(Boolean isStaffPage, int userId, DatabaseConnectionHandler con)
	{
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((Math.round(xSize)),9000);
		this.setIsStaffPage(isStaffPage);
		this.setCurrentUserId(userId);
		//initPanel(con);
		this.add(orderPagePanel);
		this.setVisible(true);
	}

	//Initialises the view of all orders
	public void initPanel(Connection con, int userId) throws SQLException

	{
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

		System.out.println("UserID: " + userId);
		this.orders = OrderDatabaseOperations.GetOrders(con);


		if (getIsStaffPage()) {

			ArrayList<Order> confirmedOrders = new ArrayList<>();
			for (Order order:orders) {
				if (order.orderStatus.equals("confirmed")) {
					confirmedOrders.add(order);
				}
			}

			this.orders = confirmedOrders;

		this.setCurrentUserId(userId);
		try {
			DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
			AccountDataOperations dop = new AccountDataOperations();
			dch.openConnection();
			isManager = dop.getManagerByUserID(dch.getConnection(), currentUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
			AccountDataOperations dop = new AccountDataOperations();
			dch.openConnection();
			isStaff = dop.getStaffByUserID(dch.getConnection(), currentUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

			// Row indentation
			textArea.setFont(new Font("SansSerif", Font.PLAIN, 12) );
			textArea.setEditable(false);
			textArea.setLineWrap(false);
			textArea.append("test");

			pageTitle.setLocation(0,0);
			pageTitle.setSize((Math.round(xSize)),70);
			pageTitle.setForeground( new Color(-1) );
			pageTitle.setFont(new Font("Merryweather", Font.BOLD, 50));
			pageTitle.setOpaque(true);
			pageTitle.setBackground( new Color(-11854529) );
			pageTitle.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			pageTitle.setText("Trains of Sheffield");
			pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
			orderPagePanel.add(pageTitle);

			customerButton.setLocation(0,70);
			customerButton.setSize((int) (Math.round(xSize * 0.16)),87);
			customerButton.setForeground( new Color(-1) );
			customerButton.setFont(new Font("Merriweather", Font.BOLD, 21));
			customerButton.addActionListener(e->customerButton_Click());
			customerButton.setBackground( new Color(-15440650) );
			customerButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			customerButton.setText("   To Customer Page");
			customerButton.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(customerButton);

			orderButton.setLocation(0,157);
			orderButton.setSize((int) (Math.round(xSize * 0.16)),87);
			orderButton.setForeground( new Color(-1) );
			orderButton.setFont(new Font("Merriweather", Font.BOLD, 21));
			orderButton.addActionListener(e->orderButton_Click());
			orderButton.setBackground( new Color(-2743738) );
			orderButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			orderButton.setText("   View Top Order");
			orderButton.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(orderButton);



			orderSidebar.setLocation(0,70);
			orderSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
			orderSidebar.setOpaque(true);
			orderSidebar.setBackground( new Color(-11854529) );
			orderPagePanel.add(orderSidebar);


			orderTitleText.setLocation((int) (Math.round(xSize * 0.20)),179);
			orderTitleText.setSize((int) (Math.round(xSize * 0.22)),44);
			orderTitleText.setForeground( new Color(-1) );
			orderTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
			orderTitleText.setBackground( new Color(-14995422) );
			orderTitleText.setText("Orders ");
			orderPagePanel.add(orderTitleText);

			OrderTable orderTable = new OrderTable(orders);

			orderTable.setLocation((int) (Math.round(xSize * 0.20)),230);
			orderTable.setSize((int) (Math.round(xSize * 0.72)),750);
			orderTable.setFont(new Font("Merriweather", Font.BOLD, 32));
			orderTable.setOpaque(true);
			orderTable.setBackground( new Color(-1) );
			orderPagePanel.add(orderTable);


			orderDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),175);
			orderDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),850); // Green background
			orderDetailBackGround.setForeground( new Color(-1) );
			orderDetailBackGround.setOpaque(true);
			orderDetailBackGround.setBackground( new Color(-14995422) );
			orderPagePanel.add(orderDetailBackGround);

			acountPageBackground.setLocation(0,0);
			acountPageBackground.setSize((Math.round(xSize)),9000);
			acountPageBackground.setOpaque(true);
			acountPageBackground.setBackground( new Color(-8741250) );
			orderPagePanel.add(acountPageBackground);

			orderPagePanel.setVisible(true);
		}
		else {
			ArrayList<Order> userOrders = new ArrayList<Order>();

			for (Order order:orders) {
				if (order.userId == userId) {
					userOrders.add(order);
				};
			}

			this.orders = userOrders;

			// Row indentation
			textArea.setFont(new Font("SansSerif", Font.PLAIN, 12) );
			textArea.setEditable(false);
			textArea.setLineWrap(false);
			textArea.append("test");

			pageTitle.setLocation(0,0);
			pageTitle.setSize((Math.round(xSize)),70);
			pageTitle.setForeground( new Color(-1) );
			pageTitle.setFont(new Font("Merryweather", Font.BOLD, 50));
			pageTitle.setOpaque(true);
			pageTitle.setBackground( new Color(-11854529) );
			pageTitle.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			pageTitle.setText("Trains of Sheffield");
			pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
			orderPagePanel.add(pageTitle);

			customerButton.setLocation(0,70);
			customerButton.setSize((int) (Math.round(xSize * 0.16)),87);
			customerButton.setForeground( new Color(-1) );
			customerButton.setFont(new Font("Merriweather", Font.BOLD, 21));
			customerButton.addActionListener(e->customerButton_Click());
			customerButton.setBackground( new Color(-15440650) );
			customerButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			customerButton.setText("   To Customer Page");
			customerButton.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(customerButton);

			orderButton.setLocation(0,157);
			orderButton.setSize((int) (Math.round(xSize * 0.16)),87);
			orderButton.setForeground( new Color(-1) );
			orderButton.setFont(new Font("Merriweather", Font.BOLD, 21));
			orderButton.addActionListener(e->userButton_Click());
			orderButton.setBackground( new Color(-2743738) );
			orderButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			orderButton.setText("   View Order");
			orderButton.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(orderButton);

			orderSidebar.setLocation(0,70);
			orderSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
			orderSidebar.setOpaque(true);
			orderSidebar.setBackground( new Color(-11854529) );
			orderPagePanel.add(orderSidebar);


			orderTitleText.setLocation((int) (Math.round(xSize * 0.20)),179);
			orderTitleText.setSize((int) (Math.round(xSize * 0.22)),44);
			orderTitleText.setForeground( new Color(-1) );
			orderTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
			orderTitleText.setBackground( new Color(-14995422) );
			orderTitleText.setText("Orders ");
			orderPagePanel.add(orderTitleText);
			OrderTable orderTable = new OrderTable(orders);

			orderTable.setLocation((int) (Math.round(xSize * 0.20)),230);
			orderTable.setSize((int) (Math.round(xSize * 0.72)),750);
			orderTable.setFont(new Font("Merriweather", Font.BOLD, 32));
			orderTable.setOpaque(true);
			orderTable.setBackground( new Color(-1) );
			orderPagePanel.add(orderTable);


			orderDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),175);
			orderDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),850); // Green background
			orderDetailBackGround.setForeground( new Color(-1) );
			orderDetailBackGround.setOpaque(true);
			orderDetailBackGround.setBackground( new Color(-14995422) );
			orderPagePanel.add(orderDetailBackGround);

			acountPageBackground.setLocation(0,0);
			acountPageBackground.setSize((Math.round(xSize)),9000);
			acountPageBackground.setOpaque(true);
			acountPageBackground.setBackground( new Color(-8741250) );
			orderPagePanel.add(acountPageBackground);

			orderPagePanel.setVisible(true);
		}
	}

	//Initialises view of one order’s order lines
	public void initPanel(Order order, Connection con)
	{
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

		// Row indentation
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 12) );
		textArea.setEditable(false);
		textArea.setLineWrap(false);
		textArea.append("test");

		pageTitle.setLocation(0,0);
		pageTitle.setSize((Math.round(xSize)),70);
		pageTitle.setForeground( new Color(-1) );
		pageTitle.setFont(new Font("Merryweather", Font.BOLD, 50));
		pageTitle.setOpaque(true);
		pageTitle.setBackground( new Color(-11854529) );
		pageTitle.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		pageTitle.setText("Trains of Sheffield");
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		orderPagePanel.add(pageTitle);

		customerButton.setLocation(0,70);
		customerButton.setSize((int) (Math.round(xSize * 0.16)),87);
		customerButton.setForeground( new Color(-1) );
		customerButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		customerButton.addActionListener(e->customerButton_Click());
		customerButton.setBackground( new Color(-15440650) );
		customerButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		customerButton.setText("   To Customer Page");
		customerButton.setHorizontalAlignment(SwingConstants.LEFT);
		orderPagePanel.add(customerButton);


		if (getIsStaffPage()) {
			orderButton.setLocation(0,157);
			orderButton.setSize((int) (Math.round(xSize * 0.16)),87);
			orderButton.setForeground( new Color(-1) );
			orderButton.setFont(new Font("Merriweather", Font.BOLD, 21));
			orderButton.addActionListener(e->orderTableButton_Click());
			orderButton.setBackground( new Color(-2743738) );
			orderButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			orderButton.setText("   View All Orders");
			orderButton.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(orderButton);

			fulfilButton.setLocation(0,244);
			fulfilButton.setSize((int) (Math.round(xSize * 0.16)),87);
			fulfilButton.setForeground( new Color(-1) );
			fulfilButton.setFont(new Font("Merriweather", Font.BOLD, 21));
			fulfilButton.addActionListener(e->fulfilButton_Click(order));
			fulfilButton.setBackground( new Color(-2743738) );
			fulfilButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			fulfilButton.setText("   Fulfil Order");
			fulfilButton.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(fulfilButton);

			deleteButton.setLocation(0,331);
			deleteButton.setSize((int) (Math.round(xSize * 0.16)),87);
			deleteButton.setForeground( new Color(-1) );
			deleteButton.setFont(new Font("Merriweather", Font.BOLD, 21));
			deleteButton.addActionListener(e->deleteButton_Click(order));
			deleteButton.setBackground( new Color(-2743738) );
			deleteButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			deleteButton.setText("   Delete Order");
			deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(deleteButton);
		}
		else {
			orderButton1.setLocation(0,157);
			orderButton1.setSize((int) (Math.round(xSize * 0.16)),87);
			orderButton1.setForeground( new Color(-1) );
			orderButton1.setFont(new Font("Merriweather", Font.BOLD, 21));
			orderButton1.addActionListener(e->userOrderTableButton_Click(order.userId));
			orderButton1.setBackground( new Color(-2743738) );
			orderButton1.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			orderButton1.setText("   View All Orders");
			orderButton1.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(orderButton1);
		
			fulfilButton.setLocation(0,244);
			fulfilButton.setSize((int) (Math.round(xSize * 0.16)),87);
			fulfilButton.setForeground( new Color(-1) );
			fulfilButton.setFont(new Font("Merriweather", Font.BOLD, 21));
			fulfilButton.addActionListener(e->confirmButton_Click(order));
			fulfilButton.setBackground( new Color(-2743738) );
			fulfilButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			fulfilButton.setText("   Confirm Order");
			fulfilButton.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(fulfilButton);

			deleteButton.setLocation(0,331);
			deleteButton.setSize((int) (Math.round(xSize * 0.16)),87);
			deleteButton.setForeground( new Color(-1) );
			deleteButton.setFont(new Font("Merriweather", Font.BOLD, 21));
			deleteButton.addActionListener(e->deleteButton_Click(order));
			deleteButton.setBackground( new Color(-2743738) );
			deleteButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
			deleteButton.setText("   Delete Order");
			deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
			orderPagePanel.add(deleteButton);
		}


		orderSidebar.setLocation(0,70);
		orderSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
		orderSidebar.setOpaque(true);
		orderSidebar.setBackground( new Color(-11854529) );
		orderPagePanel.add(orderSidebar);


		orderTitleText.setLocation((int) (Math.round(xSize * 0.20)),179);
		orderTitleText.setSize((int) (Math.round(xSize * 0.22)),44);
		orderTitleText.setForeground( new Color(-1) );
		orderTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
		orderTitleText.setBackground( new Color(-14995422) );
		orderTitleText.setText("Orders ");
		orderPagePanel.add(orderTitleText);

        OrderTable orderTable = new OrderTable(order, con);

        orderTable.setLocation((int) (Math.round(xSize * 0.20)),230);
        orderTable.setSize((int) (Math.round(xSize * 0.72)),750);
		orderTable.setFont(new Font("Merriweather", Font.BOLD, 32));
        orderTable.setOpaque(true);
		orderTable.setBackground( new Color(-1) );
        orderPagePanel.add(orderTable);


		orderDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),175);
		orderDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),850); // Green background
		orderDetailBackGround.setForeground( new Color(-1) );
		orderDetailBackGround.setOpaque(true);
		orderDetailBackGround.setBackground( new Color(-14995422) );
		orderPagePanel.add(orderDetailBackGround);

		acountPageBackground.setLocation(0,0);
		acountPageBackground.setSize((Math.round(xSize)),9000);
		acountPageBackground.setOpaque(true);
		acountPageBackground.setBackground( new Color(-8741250) );
		orderPagePanel.add(acountPageBackground);

		orderPagePanel.setVisible(true);
	}

	//When customer button is clicked, redirect to product page
	public void customerButton_Click()
	{
		ProductPageUI productPage = new ProductPageUI();
		productPage.initFrame(isStaffPage, currentUserId);
		System.out.println(currentUserId);
		this.dispose();
	}
	//When the view top order button on the staff page is clicked, opens a new window with that order’s order lines
	public void orderButton_Click()
	{	
		try {
            final OrderPage window = new OrderPage();
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            OrderDatabaseOperations dop = new OrderDatabaseOperations();
            con.openConnection();
            Order order = dop.getCurrentOrderByUserID(con.getConnection(), orders.get(0).getUserId());
            window.initFrame(getIsStaffPage(), getCurrentUserId(), con);
            window.initPanel(order, con.getConnection());
            con.closeConnection();
            dispose();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}

	//When the view orders button is pressed on the customer page,
	// open a popup window asking which order they would like to view, then opens a new window with that order’s order lines.
	public void userButton_Click() {
		Integer orderIds[] = new Integer[orders.size()];
		Integer i = 0;
		for (Order order:this.orders) {
			orderIds[i] = order.orderNumber;
			i++;
		}
		if (orderIds.length == 0) {
			JOptionPane.showMessageDialog(this, "There are no orders to view.");
		} else {
			int orderId = (Integer) JOptionPane.showInputDialog(this, "Select an Order ID", "Input", JOptionPane.QUESTION_MESSAGE, null, orderIds, orderIds[0]);


			Order selectedOrder = null;
			for (Order order : this.orders) {
				if (order.orderNumber == orderId) {
					selectedOrder = order;
				}
			}
			System.out.println(selectedOrder.orderNumber);

			try {
				final OrderPage window = new OrderPage();
				DatabaseConnectionHandler con = new DatabaseConnectionHandler();
				con.openConnection();
				window.initFrame(getIsStaffPage(), getCurrentUserId(), con);
				window.initPanel(selectedOrder, con.getConnection());
				con.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dispose();
		}
	}

	//When the view all orders button is pressed on the staff page for viewing one order, opens a new window showing all orders
	public void orderTableButton_Click()
	{	
		try {
            final OrderPage window = new OrderPage();
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            con.openConnection();
			window.initFrame(getIsStaffPage(), getCurrentUserId(), con);
            window.initPanel(con.getConnection(), getCurrentUserId());
            con.closeConnection();
        } 

        catch (SQLException e) {
            e.printStackTrace();
        }
		dispose();
	}
	//when the view all orders button is pressed on the user page for viewing one order, opens a new window showing all of that user’s orders
	public void userOrderTableButton_Click(Integer userId)
	{	
		try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            dch.openConnection();
            OrderPage orderPage = new OrderPage();
            orderPage.initFrame(getIsStaffPage(), getCurrentUserId(), dch);
            orderPage.initPanel(dch.getConnection(), getCurrentUserId());
            this.dispose();
            dch.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	public Boolean getIsStaffPage() { return this.isStaffPage; }

	public void setIsStaffPage(Boolean isStaffPage) { this.isStaffPage = isStaffPage; }

	public Boolean getIsStaff() { return this.isStaff; }

	public void setIsStaff(Boolean isStaff) { this.isStaff = isStaff; }

	public Boolean getIsManager() { return this.isManager; }

	public void setIsManager(Boolean isManager) { this.isManager = isManager; }

	public int getCurrentUserId() { return this.currentUserId; }

	public void setCurrentUserId(int currentUserId) { this.currentUserId = currentUserId; }

	//When staff fulfill an order, mark as fulfilled in the database
	public void fulfilButton_Click(Order order)
	{	
		try {
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
			OrderDatabaseOperations dop = new OrderDatabaseOperations();
            con.openConnection();
			if (dop.canOrderBeConfirmed(order, con.getConnection())) {
				OrderDatabaseOperations.fulfilOrder(order, con.getConnection());
				OrderPage window = new OrderPage();
				window.initFrame(getIsStaffPage(), getCurrentUserId(), con);
				window.initPanel(con.getConnection(), getCurrentUserId());
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "There is not enough stock to complete " +
				"your order. Please try again when stocks refill, or start a new order.");
			}
            con.closeConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

	}

	//When customer confirms an order, mark as confirmed in the database
	public void confirmButton_Click(Order order)
	{	
		try {
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            OrderDatabaseOperations dop = new OrderDatabaseOperations();
            AccountDataOperations dop2 = new AccountDataOperations();
            con.openConnection();
            if (dop2.doBankDetailsExist(getCurrentUserId(), con.getConnection())) {
				if (dop.canOrderBeConfirmed(order, con.getConnection())) {
					OrderDatabaseOperations.ConfirmOrder(order, con.getConnection());
					OrderPage window = new OrderPage();
					window.initFrame(getIsStaffPage(), getCurrentUserId(), con);
					window.initPanel(con.getConnection(), getCurrentUserId());
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "There is not enough stock to complete " +
					"your order. Please try again when stocks refill, or start a new order.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "You have not currently registered a bank " +
				"card to your account. Please do so before you confirm your order.");
				AccountPage accountPage = new AccountPage();
				accountPage.initFrame(getIsStaffPage(),getCurrentUserId());
				this.dispose();
			}
			con.closeConnection();
        } 

        catch (SQLException e) {
            e.printStackTrace();
        }

	}


	//When staff or customers delete an order, mark as deleted in the database
	public void deleteButton_Click(Order order)
	{	
		try {
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            con.openConnection();
            OrderDatabaseOperations.DeleteOrder(order, con.getConnection());
			final OrderPage window = new OrderPage();
			window.initFrame(getIsStaffPage(), getCurrentUserId(), con);
			window.initPanel(con.getConnection(), getCurrentUserId());
			con.closeConnection();

			dispose();


        } 

        catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
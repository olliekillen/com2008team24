package orders;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Database {
    public static ArrayList<Order> GetOrders (String filename) {
        ArrayList<Order> orders = new ArrayList<Order>();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                Integer number = myReader.nextInt();
                String date = myReader.next();
                String status = myReader.next();
                Float cost = myReader.nextFloat();
                Boolean blocked = myReader.nextBoolean();
                String blockedDate = myReader.next();
                Integer userId = myReader.nextInt();
                Order order = new Order(number, date, status, cost, blocked, blockedDate, userId);
                orders.add(order);
                myReader.nextLine();
              }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return orders;
    }

    public static ArrayList<OrderLine> GetOrderLine (String filename, Order order) {
        ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String productCode = myReader.next();
                Integer orderLineNumber = myReader.nextInt();
                Integer orderNumber = myReader.nextInt(); 
                if (orderNumber == order.orderNumber) {
                    OrderLine orderLine= new OrderLine(productCode, orderLineNumber, orderNumber);
                    orderLines.add(orderLine);
                }
                myReader.nextLine();
              }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return orderLines;
    }

    public static User GetUser (Integer userId) {
    /*
        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection(
                “jdbc:mysql://stusql.dcs.shef.ac.uk/team024”, "team024”, “ahKaePi9A”);

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                int count = stmt.executeUpdate(
                ResultSet res = 
                    stmt.executeQuery(“SELECT * FROM Users WHERE userID = userId");
                while (res.next()) {
                    Integer id = res.getInt(1); // col 1 as int
                    String email = res.getString(2); // col 2 as string
                    String pass = res.getString(3);
                    String forename = res.getString(4);
                    String surname = res.getString(5);
                    String postcode = res.getString(6);
                    Integer houseNum = res.getInt(7);

                    User user = new User(id, email, pass, forename, surname, postcode, houseNum);
                    // do something with teacher
                    // eg: store object in a list
                }
                res.close(); 

            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (stmt != null) 
                stmt.close();
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (con != null) con.close();
        }
    */
        
        User user = new User(userId, "Email", "Test", "Forename", "Surname", "Postcode", 1);
        return user;
    }
    public static void FulfilOrder (Order order) {
    /*
        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection(
                “jdbc:mysql://stusql.dcs.shef.ac.uk/team024”, "team024”, “ahKaePi9A”);

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                int count = stmt.executeUpdate(
                “UPDATE orders SET statusField = "fulfilled""
                + “ WHERE orderNumber = order.orderNumber”);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (stmt != null) 
                stmt.close();
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (con != null) con.close();
        }
    */
    }

    public static void DeleteOrder (Order order) {
    /*
        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection(
                “jdbc:mysql://stusql.dcs.shef.ac.uk/team024”, "team024”, “ahKaePi9A”);
            
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                int count = stmt.executeUpdate(
                "DELETE FROM orders"
                + “ WHERE orderNumber = order.orderNumber”);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (stmt != null) 
                stmt.close();
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (con != null) con.close();
        }
    */
    }
}


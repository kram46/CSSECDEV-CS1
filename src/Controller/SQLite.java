package Controller;

import Model.History;
import Model.Logs;
import Model.Product;
import Model.User;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;
public class SQLite {
    
    public int DEBUG_MODE = 0;
    String driverURL = "jdbc:sqlite:" + "database.db";
    
    public void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(driverURL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Database database.db created.");
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void createHistoryTable() {
        String sql = "CREATE TABLE IF NOT EXISTS history (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL,\n"
            + " name TEXT NOT NULL,\n"
            + " stock INTEGER DEFAULT 0,\n"
            + " timestamp TEXT NOT NULL\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void createLogsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS logs (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " event TEXT NOT NULL,\n"
            + " username TEXT NOT NULL,\n"
            + " desc TEXT NOT NULL,\n"
            + " timestamp TEXT NOT NULL\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
     
    public void createProductTable() {
        String sql = "CREATE TABLE IF NOT EXISTS product (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " name TEXT NOT NULL UNIQUE,\n"
            + " stock INTEGER DEFAULT 0,\n"
            + " price REAL DEFAULT 0.00,\n"
            + " visible INTEGER DEFAULT 1\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
     
    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL UNIQUE,\n"
            + " password TEXT NOT NULL,\n"
            + " role INTEGER DEFAULT 2,\n"
            + " locked INTEGER DEFAULT 0\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropHistoryTable() {
        String sql = "DROP TABLE IF EXISTS history;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropLogsTable() {
        String sql = "DROP TABLE IF EXISTS logs;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropProductTable() {
        String sql = "DROP TABLE IF EXISTS product;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS users;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void addHistory(String username, String name, int stock, String timestamp) {
        String sql = "INSERT INTO history(username,name,stock,timestamp) VALUES(?,?,?,?)";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, name);
            pstmt.setInt(3, stock);
            pstmt.setString(4, timestamp);
            
            int rs = pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void addLogs(String event, String username, String desc, String timestamp) {
        String sql = "INSERT INTO logs(event,username,desc,timestamp) VALUES(?,?,?,?)";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, event);
            pstmt.setString(2, username);
            pstmt.setString(3, desc);
            pstmt.setString(4, timestamp);
            
            int rs = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void addProduct(String name, int stock, double price) {
        String sql = "INSERT INTO product(name,stock,price,visible) VALUES(?,?,?,1)";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, stock);
            pstmt.setDouble(3, price);
            
            int rs = pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void addUser(String username, String password) {
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            int rs = pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    
    public ArrayList<History> getHistory(){
        System.out.print("GET HISTORY NO PARAMS");
        String sql = "SELECT id, username, name, stock, timestamp FROM history";
        ArrayList<History> histories = new ArrayList<History>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                histories.add(new History(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("name"),
                                   rs.getInt("stock"),
                                   rs.getString("timestamp")));
            }
        } catch (Exception ex) {
            System.out.print(ex);
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return histories;
    }
    
    public ArrayList<History> getHistory(String username){
        String sql = "SELECT id, username, name, stock, timestamp FROM history where username = ?";
        ArrayList<History> histories = new ArrayList<History>();
        Connection conn = null;
        System.out.print("GET HISTORY USER");
        try {
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                histories.add(new History(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("name"),
                                   rs.getInt("stock"),
                                   rs.getString("timestamp")));
            }
        } catch (Exception ex) {
            System.out.print(ex);
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return histories;
    }
    
    public ArrayList<Logs> getLogs(){
        String sql = "SELECT id, event, username, desc, timestamp FROM logs";
        ArrayList<Logs> logs = new ArrayList<Logs>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                logs.add(new Logs(rs.getInt("id"),
                                   rs.getString("event"),
                                   rs.getString("username"),
                                   rs.getString("desc"),
                                   rs.getString("timestamp")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return logs;
    }
    
    public ArrayList<Product> getProduct(){
        String sql = "SELECT id, name, stock, price , visible FROM product";
        ArrayList<Product> products = new ArrayList<Product>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"),
                                   rs.getString("name"),
                                   rs.getInt("stock"),
                                   rs.getFloat("price"),
                                 rs.getInt("visible")));
            }
        } catch (Exception ex) {
            System.out.print(ex);
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return products;
    }
    
    public ArrayList<User> getUsers(){
        String sql = "SELECT id, username, password, role, locked FROM users";
        ArrayList<User> users = new ArrayList<User>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("password"),
                                   rs.getInt("role"),
                                   rs.getInt("locked")));
            }
        } catch (Exception ex) {ex.printStackTrace();}
        finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return users;
    }
    
    public boolean checkUser(String username, String password){
        String sql = "SELECT id, username, password, role, locked FROM users WHERE username=? AND password=?";
        User user = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role"),
                        rs.getInt("locked"));
            }
            if(user != null){
                conn.close();
                return true;
            }
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean checkUsernameExist(String username){
        String sql = "SELECT id, username, password, role, locked FROM users WHERE username=?";
        User user = null;
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role"),
                        rs.getInt("locked"));
            }
            if(user != null)
                return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false;
    }
    public boolean checkLockUser(String username){
        String sql = "SELECT id, username, password, role, locked FROM users WHERE username=?";
        User user = null;
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role"),
                        rs.getInt("locked"));
            }
            if(user != null){
                if(user.getLocked() == 1){
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false; 
    }
    public boolean updatePassword(String username, String newPassword, String adminPassword){
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        String adminCheck = "SELECT id FROM users WHERE password = ? AND role = 5";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement adminpstmt = conn.prepareStatement(adminCheck);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            adminpstmt.setString(1, adminPassword);
            ResultSet rs = adminpstmt.executeQuery();
            if(rs.next()){
                pstmt.executeUpdate();
                conn.close();
                return true;
            }
                
            else{
                System.out.println("ADMIN PASSWORD INCORRECT.");
                conn.close();
              
            }
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false;
    }

     public int checkRole(String username){
        String sql = "SELECT id, username, password, role, locked FROM users WHERE username=?";
        User user = null;
        int role = 0;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role"),
                        rs.getInt("locked"));
                role = user.getRole();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return role;
    }

    public int editRole(String username, int role){
        String sql = "UPDATE users SET role = ? WHERE username = ?";
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, role);
            pstmt.setString(2, username);
       
            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return 0;
    }
    public boolean lockUser(String username){
        String sql = "UPDATE users SET locked = 1 WHERE username = ?";
        int res;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            res = pstmt.executeUpdate();
            
            if(res > 0){
                conn.close();
                addLogs("NOTICE", username, "Login Successful",  new Timestamp(new Date().getTime()).toString());
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        
        return false;
    }
    public boolean unlockUser(String username){
        String sql = "UPDATE users SET locked = 0 WHERE username = ?";
        int res;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            res = pstmt.executeUpdate();
            
            if(res > 0){
                conn.close();
                return true;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false;
    }
    public void addUser(String username, String password, int role) {
        String sql = "INSERT INTO users(username,password,role) VALUES(?,?,?)";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, role);        
            int rs = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void removeUser(String username) {
        String sql = "DELETE FROM users WHERE username=?";        
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            
            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public Product getProduct(String name){
        String sql = "SELECT name, stock, price, visible FROM product WHERE name=?";
        Product product = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                product = new Product(rs.getString("name"),
                                    rs.getInt("stock"),
                                    rs.getFloat("price"),
                                    rs.getInt("visible"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return product;
    }
    
    public boolean updateProduct(String old, String name, int stock, double price){
        
        String sql;
        boolean diffName;
        int res;
        Connection conn = null;
        if(old.equalsIgnoreCase(name)){
            sql = "UPDATE product SET stock = ?, price = ? WHERE name = ?";
            diffName = false;
        }else{
            sql = "UPDATE product SET stock = ?, price = ?, name = ? WHERE name = ?";
            diffName = true;
        }
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
  
            pstmt.setInt(1, stock);
            pstmt.setDouble(2, price);
            pstmt.setString(3, name);
            if(diffName) pstmt.setString(4, old);
            res = pstmt.executeUpdate();
            
            if(res > 0){
                return true;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        
        return false;
    }
    
    public boolean purchaseProduct(String name, int stockRemaining){
        String sql = "UPDATE product SET stock = ? WHERE name = ?";
        int res;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
  
            pstmt.setInt(1, stockRemaining);
            pstmt.setString(2, name);
            res = pstmt.executeUpdate();
            
            if(res > 0){
                return true;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        
        return false;
    }
    
    public boolean removeProduct(String name){
                
        String sql;
        boolean diffName;
        int res;
        Connection conn = null;
        sql = "UPDATE product SET visible = 0 WHERE name = ?";
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
  
            pstmt.setString(1, name);
            res = pstmt.executeUpdate();
            if(res > 0){
                return true;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        
        return false;
    }
    
    public void deleteProduct(String name){
        String sql = "DELETE FROM product WHERE name=?";        
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            int rs = pstmt.executeUpdate();
           
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        
        this.deleteProductFromHistory(name);

    }
    
    public void deleteProductFromHistory(String name){
        String sql = "DELETE FROM history WHERE name=?";        
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            int rs = pstmt.executeUpdate();
           
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public void clearLogs(){
        
        String sql = "DELETE FROM logs";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
       
    }
    
    
}
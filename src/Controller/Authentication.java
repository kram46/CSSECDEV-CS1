/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;



/**
 *
 * @author kethe
 */
public class Authentication {
    
    SQLite sqlite = new SQLite();
    
    public boolean loginAuth(String username, String password){
        return sqlite.checkUser(username, password);
    }
    
    public boolean registerAuth(String username, String password){
        if(!sqlite.checkUsernameExist(username)){
            sqlite.addUser(username, password);
            return true;
        }
        return false;
    }
    
    public boolean unametest(String username){
        return sqlite.checkUsernameExist(username);
    }
    
    
}

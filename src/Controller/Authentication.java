/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author kethe
 */
public class Authentication {
    
    SQLite sqlite = new SQLite();

    
    public boolean loginAuth(String username, String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            if (sqlite.checkUser(username, new String(md.digest(password.getBytes())))){
                sqlite.addLogs("NOTICE", username, "Login Successful",  new Timestamp(new Date().getTime()).toString());
                return true;
            }
                
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public int roleAuth(String username){
  
        return (sqlite.checkRole(username));
    }
    
    public boolean registerAuth(String username, String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            if(!sqlite.checkUsernameExist(username)){
                byte[] bytePassword = password.getBytes();
                bytePassword = md.digest(bytePassword);
                sqlite.addUser(username, new String(bytePassword));
                sqlite.addLogs("NOTICE", username, "User creation successful",  new Timestamp(new Date().getTime()).toString());
                return true;
            }
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean lockUser(String username){
        return (sqlite.lockUser(username));
    }
    
    public boolean isLocked(String username){
        return sqlite.checkLockUser(username);
    }
    
    
}

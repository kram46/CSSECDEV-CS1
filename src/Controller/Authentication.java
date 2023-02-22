/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author kethe
 */
public class Authentication {
    
    SQLite sqlite = new SQLite();

    
    public boolean loginAuth(String username, String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return sqlite.checkUser(username, new String(md.digest(password.getBytes())));
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean registerAuth(String username, String password){
        try{
            System.out.println(password);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            if(!sqlite.checkUsernameExist(username)){
                byte[] bytePassword = password.getBytes();
                bytePassword = md.digest(bytePassword);
                sqlite.addUser(username, new String(bytePassword));
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

package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kethe
 */
import java.sql.Timestamp;
import java.util.Date;

public class Utils {
    public static Timestamp getCurrTime(){
        return new Timestamp(new Date().getTime());
    }
}

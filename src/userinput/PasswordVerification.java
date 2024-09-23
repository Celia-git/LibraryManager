/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Celia
 */
public class PasswordVerification {
    
    private static String password;

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        PasswordVerification.password = password;
    }
    
    public PasswordVerification(){}
    
    public static void readPassword(){
        try (Scanner reader = new Scanner(new File("files/password.txt"))){
            PasswordVerification.password = reader.nextLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PasswordVerification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void writePassword(){
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("files/password.txt", false);
            PrintWriter out = new PrintWriter(fos);
            out.print(PasswordVerification.password);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PasswordVerification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean isPasswordValid(String enteredPass){
        return PasswordVerification.password.equals(enteredPass);
    }
}

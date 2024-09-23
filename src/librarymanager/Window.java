/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanager;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import userinput.*;

/**
 *
 * @author Celia
 */
public class Window extends JFrame{
    private Font titleFont = new Font("Helvetica", Font.PLAIN, 18);
    private Font labelFont = new Font("Consolas", Font.PLAIN, 12);
    private Font defaultFont = new Font("Helvetica", Font.PLAIN, 14);
    private Font labelTitleFont = new Font("Consolas", Font.BOLD, 12);
    private User user;
    private Admin admin;
    
    public void Window(){}
    
    public void configureWindow(){
        setTitle("Library System");
        refresh();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        this.user = new User(this);
        this.admin = new Admin(this);
        user.configureUserMenu();
        admin.configureAdminMenu();
        addUser();
    }
    
    public void addUser(){
        if (this.admin != null){
            remove(this.admin);
        }
        add(user);
    }
    
    public void addAdmin(){
        if (this.user != null){
            remove(this.user);
        }
        add(admin);
    }
    
    public void refresh(){
        pack();
        setSize(800, 900);
        setLocationRelativeTo(null);
    }
    public Font getTitleFont(){
        return this.titleFont;
    }
    public Font getDefaultFont(){
        return this.defaultFont;
    }
    public Font getLabelFont(){
        return this.labelFont;
    }
    public Font getLabelTitleFont(){
        return this.labelTitleFont;
    }
}
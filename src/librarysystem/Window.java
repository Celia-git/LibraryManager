/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import userinput.*;

/**
 *
 * @author Celia
 */
public class Window extends JFrame{
    public void Window(){
    }
    public void configureWindow(){
        setTitle("Library System");
        refresh();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        User user = new User(this);
        user.configureUserMenu();
        add(user);
    
    }
    public void refresh(){
        pack();
        setSize(800, 900);
        setLocationRelativeTo(null);
    }
}
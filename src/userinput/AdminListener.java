/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinput;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Celia
 */
public class AdminListener implements ActionListener{
    
    private Admin admin;
    public AdminListener(Admin admin){
        this.admin = admin;
    }

    public void actionPerformed(ActionEvent e) {
        
        // Action: Button Click

        if (e.getSource() instanceof JButton){
            JButton sourceButton = (JButton)e.getSource();
            Color[] colors = {new Color(173, 216, 230), Color.LIGHT_GRAY};
            
            if (sourceButton.equals(admin.getSearchBook())||sourceButton.equals(admin.getSearchStudent())){
                admin.getBookSearchKeys().setEnabled(admin.getSearchBook().isSelected());
                admin.getStudentSearchKeys().setEnabled(!admin.getSearchBook().isSelected());
                
            }
        }
    }
}


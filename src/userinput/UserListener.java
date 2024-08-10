/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinput;

import datastorage.Book;
import exceptions.InvalidAction;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import statics.Search;

/**
 *
 * @author Celia
 */
public class UserListener implements ActionListener {
    
    private User u;
    private Color[] colors = {new Color(173, 216, 230), Color.LIGHT_GRAY};
    
    public UserListener(User u){
        this.u = u;
    }
    
    public void actionPerformed(ActionEvent e) {

        // Action: Button Click
        if (e.getSource() instanceof JButton){
            JButton sourceButton = (JButton)e.getSource();
            u.getViewOutputPanel().removeAll();

            // View All Clicked
            if (sourceButton.equals(u.getViewButton())){

                viewAllBooks();

            }

            // Search "GO" clicked
            else if (sourceButton.equals(u.getSearchButton())){
                
                searchBooks();

            }
            
            // Admin Login Button clicked
            else if (sourceButton.equals(u.getAdminLoginButton())){
                PasswordVerification.readPassword();
                String enteredPass = String.valueOf(u.getAdminLoginEntry().getPassword());
                
                // Valid Password: Open Admin Menu
                if (PasswordVerification.isPasswordValid(enteredPass)){
                    u.getAdminLoginEntry().setBackground(Color.WHITE);
                    u.getAdminLoginEntry().setForeground(Color.BLACK);
                    u.loadAdminMenu();
                } 
                
                // Invalid Password
                else{
                    u.getAdminLoginEntry().setBackground(Color.RED);
                    u.getAdminLoginEntry().setForeground(Color.WHITE);
                }
            }
            
            u.getWindow().refresh();
        }

    }
    
    public void viewAllBooks(){
        try {
            String[] displayStrings = Search.view("book");
            int i = 0;
            int c = 0;
            String metadata="";
            for (String str : displayStrings){
                if (i%2==0){
                    metadata = str;
                }
                else{
                    // Create a new collapsable panel and add it to viewOutputText
                    JPanel panel = new JCollapsiblePanel(str, colors[c%2], Search.viewVerbose("book", metadata), u.getWindow());
                    u.getViewOutputPanel().add(panel);
                    c +=1;
                }  
                i += 1;
            }

        } catch (InvalidAction ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        u.getResultsLabel().setText("All Books");
        u.getSearchEntry().setText("");
    }
    
    public void searchBooks(){
        String searchKey = (String) u.getBookSearchKeys().getSelectedItem();
        String searchValue = (String) u.getSearchEntry().getText();

        try {
            ArrayList<Book> books = Search.getBook(searchKey, searchValue);
            if (books.size()==0){
                u.getResultsLabel().setText(String.format("No resuls found for books with %s in the %s", searchKey, searchValue));
            } else {
                u.getResultsLabel().setText(String.format("Resuls found for books with %s in the %s", searchValue, searchKey));
                int c = 0;
                for(Book book: books){
                    String id = book.getId();
                    String name = book.getName();
                    JPanel panel = new JCollapsiblePanel(name, colors[c%2], Search.viewVerbose("book", id), u.getWindow());
                    u.getViewOutputPanel().add(panel);
                    c += 1;
                }
            }
        } catch (InvalidAction ex) {
            u.getResultsLabel().setText(String.format("Invalid search term %s for key %s", searchValue, searchKey));
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Color[] getColors(){
        return this.colors;
    }


}

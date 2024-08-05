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
 * @author cofho
 */
public class UserListener implements ActionListener {
    
    private User u;
    
    public UserListener(User u){
        this.u = u;
    }
    
    public void actionPerformed(ActionEvent e) {

        // Action: Button Click
        if (e.getSource() instanceof JButton){
            JButton sourceButton = (JButton)e.getSource();
            Color[] colors = {new Color(173, 216, 230), Color.LIGHT_GRAY};
            u.getViewOutputPanel().removeAll();

            // View All Clicked
            if (e.getSource().equals(u.getViewButton())){

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

            // Search "GO" clicked
            else if (e.getSource().equals(u.getSearchButton())){
                String searchKey = (String) u.getSearchKeys().getSelectedItem();
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
            u.getWindow().refresh();
        }

    }


}

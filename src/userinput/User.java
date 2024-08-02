/*
 * Handle user commands: any level clearance
 */
package userinput;

import datastorage.Book;
import exceptions.InvalidAction;
import exceptions.InvalidType;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import statics.Search;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import librarysystem.Window;

/**
 *
 * @author Celia
 */
public class User extends JPanel {

    /**
     * Default Constructor
     */
    
    private JTextField searchEntry;
    private JButton searchButton;
    private JButton viewButton;
    private JPanel entryPanel;
    private JPanel viewPanel;
    private JScrollPane viewOutput;
    private JPanel viewOutputPanel;
    private JLabel resultsLabel;
    private Window window;
    
    public User(Window window) {
        this.window = window;
    }
    
    public void configureUserMenu(){
        this.setLayout(null);
        
        entryPanel = new JPanel();
        entryPanel.setBounds(0, 30, 850, 50);
        entryPanel.setBackground(Color.red);
        searchEntry = new JTextField(30);
        searchEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton = new JButton("Go");
        searchButton.setBounds(150, 0, 20, 20);
        entryPanel.add(searchEntry); entryPanel.add(searchButton);
        entryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        entryPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        
        viewPanel = new JPanel();   
        viewPanel.setBounds(0, 150, 800, 600);
        viewPanel.setBackground(Color.blue);
        resultsLabel = new JLabel("", JLabel.CENTER);
        resultsLabel.setSize(300, 20);
        resultsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewButton = new JButton("View All");
        viewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewOutputPanel = new JPanel();
        viewOutputPanel.setLayout(new BoxLayout(viewOutputPanel, BoxLayout.Y_AXIS));
        viewOutput = new JScrollPane(viewOutputPanel);
        viewOutput.setBounds(0, 0, 800, 500);
        viewOutput.setBackground(Color.black);
        
        viewPanel.add(resultsLabel);
        viewPanel.add(viewButton);
        viewPanel.add(viewOutput);
        
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
        viewPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(entryPanel); add(viewPanel);
        
        
        // Add functionality to search button
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){            
                resultsLabel.setText(searchEntry.getText());
                
            }
        });
        
        // Action Listener for User buttons
        viewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                // Action: Button Click
                if (e.getSource() instanceof JButton){
                    JButton sourceButton = (JButton)e.getSource();
                    
                    // View All Clicked
                    if (e.getSource().equals(viewButton)){
                        viewOutputPanel.removeAll();
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
                                Color[] colors = {new Color(102, 255, 102), Color.LIGHT_GRAY};
                                JPanel panel = new JCollapsiblePanel(str, colors[c%2], Search.viewVerbose("book", metadata), window);
                                viewOutputPanel.add(panel);
                                c +=1;
                            }  
                            i += 1;
                        }
                        
                    } catch (InvalidAction ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    resultsLabel.setText("All Books");
                    searchEntry.setText("");
                    window.refresh();
                    }
                }
                
            }
            
        });

    }
    
   

    /**
     * User class can only search books and not student files
     *
     * @throws InvalidType
     * @throws FileNotFoundException
     * @throws InvalidAction
     */
    /*
    public void search() throws InvalidType, FileNotFoundException, InvalidAction, IOException {
        Book book = new Book("");
        String showKeys[] = book.getKeys();
        System.out.println("Search by which key?: ");
        for (String k : showKeys) {
            System.out.println(k);
        }
        Scanner in = new Scanner(System.in);
        key = in.nextLine();
        System.out.println("Enter a value to match: ");
        value = in.nextLine();
        ArrayList<Book> books = Search.getBook(key, value);
        System.out.println(String.format("Matches Found: %d", books.size()));
        books.forEach(b -> {
            System.out.println(Arrays.toString(b.format()));
        });

    }
*/
}

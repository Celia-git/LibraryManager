/*
 * Handle user commands: any level clearance
 */
package userinput;

import datastorage.Book;
import exceptions.InvalidAction;
import exceptions.InvalidType;
import java.awt.Color;
import statics.Search;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

/**
 *
 * @author Celia
 */
public class User extends JPanel {

    /**
     * Default Constructor
     */
    public User() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private String function;
    private String key;
    private String value;

    /**
     * Draw User pane
     */
    
    
        /*
        try {
            // Show the options for User

            /*
            System.out.println("Enter a command to 'display' or 'search': \n");
            ("Command options are 'display', 'search', \nEnter desired command: ");
            Scanner in = new Scanner(System.in);
            function = in.next();
            function = function.toLowerCase();

            //search(category);   
            switch (function) {
                case "display":
                    display();
                    break;
                case "search":
                    search();
                    break;
                default:
                    throw new InvalidAction(function, "Command does not exist");
            }
        } catch (InvalidType | InvalidAction | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            getInput();
        }
            */
    
    
    /**
     * Method for displaying all data relating to books. overwritten in admin
     * class to allow display all of type book or student.
     *
     * @throws InvalidType
     * @throws FileNotFoundException
     */
    public void display() throws InvalidType, FileNotFoundException, InvalidAction, IOException {
        System.out.println("View all details?");
        Scanner in = new Scanner(System.in);
        String y = in.next();
        if (y.equalsIgnoreCase("y") || y.equalsIgnoreCase("yes")) {
            System.out.println(Search.viewVerbose("book"));
        } else {
            System.out.println(Search.view("book"));
        }

    }

    /**
     * User class can only search books and not student files
     *
     * @throws InvalidType
     * @throws FileNotFoundException
     * @throws InvalidAction
     */
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

}
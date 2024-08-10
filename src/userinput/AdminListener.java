/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinput;

import datastorage.Book;
import datastorage.Student;
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
import javax.swing.JRadioButton;
import statics.Search;

/**
 *
 * @author Celia
 */
public class AdminListener extends UserListener implements ActionListener{
    
    private Admin admin;
    private String typeSelected = "Book";
        
    public AdminListener(Admin admin){
        super(admin);
        this.admin = admin;
    }

    public void actionPerformed(ActionEvent e) {
        
        // Action: Radio button selection
        if (e.getSource() instanceof JRadioButton){
            String command = e.getActionCommand();
            this.typeSelected = command;
            // Book/Student radio button select
            if (command.equals("Book")||command.equals("Student")){
                admin.getBookSearchKeys().setEnabled(admin.getSearchBook().isSelected());
                admin.getStudentSearchKeys().setEnabled(!admin.getSearchBook().isSelected());
                
            }
        }
        // Action: Button selection
        else if (e.getSource() instanceof JButton){
            JButton button = (JButton) e.getSource();
            admin.getViewOutputPanel().removeAll();
            // View All button pressed
            if (button.equals(admin.getViewButton())){
                if (this.typeSelected=="Book"){
                    viewAllBooks();
                } else if (this.typeSelected=="Student"){
                    viewAllStudents();
                }
            }
            
            // Search Button pressed
            else if (button.equals(admin.getSearchButton())){
                if (this.typeSelected=="Book"){
                    searchBooks();
                } else if (this.typeSelected=="Student"){
                    searchStudents();
                }
            }
        }
        admin.getWindow().refresh();
    }

    public void searchStudents(){
        String searchKey = (String) admin.getStudentSearchKeys().getSelectedItem();
        String searchValue = (String) admin.getSearchEntry().getText();

        try {
            ArrayList<Student> students = Search.getStudent(searchKey, searchValue);
            if (students.size()==0){
                admin.getResultsLabel().setText(String.format("No resuls found for students with %s in the %s", searchKey, searchValue));
            } else {
                admin.getResultsLabel().setText(String.format("Resuls found for students with %s in the %s", searchValue, searchKey));
                int c = 0;
                for(Student student: students){
                    String id = student.getId();
                    String name = student.getName();
                    JPanel panel = new JCollapsiblePanel(name, getColors()[c%2], Search.viewVerbose("student", id), admin.getWindow());
                    admin.getViewOutputPanel().add(panel);
                    c += 1;
                }
            }
        } catch (InvalidAction ex) {
            admin.getResultsLabel().setText(String.format("Invalid search term %s for key %s", searchValue, searchKey));
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewAllStudents(){
        try {
            String[] displayStrings = Search.view("student");
            int i = 0;
            int c = 0;
            String metadata="";
            for (String str : displayStrings){
                if (i%2==0){
                    metadata = str;
                }
                else{
                    // Create a new collapsable panel and add it to viewOutputText
                    JPanel panel = new JCollapsiblePanel(str, getColors()[c%2], Search.viewVerbose("student", metadata), admin.getWindow());
                    admin.getViewOutputPanel().add(panel);
                    c +=1;
                }  
                i += 1;
            }

        } catch (InvalidAction ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        admin.getResultsLabel().setText("All Students");
        admin.getSearchEntry().setText("");
    }
}

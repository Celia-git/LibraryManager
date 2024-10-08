

/*
 * Handle User Commands: Admin level clearance
 */
package userinput;

import datastorage.*;
import exceptions.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import statics.*;
import userinput.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import librarysystem.Window;

/**
 *
 * @author Celia
 */
public class Admin extends User{

    // Admin Entry Strings
    private JComboBox studentSearchKeys;
    private ButtonGroup searchType;
    private JRadioButton searchBook;
    private JRadioButton searchStudent;
    private JPanel entrySubPanel;
    
    public Admin(Window window) {
        super(window);
    }
    
    public void configureAdminMenu(){
        this.setLayout(null);
        
        ActionListener listener = (ActionListener) new AdminListener(this);
        
        createEntryPanel();
        createSearchEntry();
        createBookSearchKeys();
        createSearchButton();
        getSearchButton().addActionListener(listener);
        studentSearchKeys = new JComboBox(new Student("").getKeys());
        studentSearchKeys.setFont(getDefaultFont());
        studentSearchKeys.setEnabled(false);
        searchBook = new JRadioButton("Book");
        searchBook.setFont(getDefaultFont());
        searchBook.setBounds(450, 80, 850, 50);
        searchBook.setSelected(true);
        searchBook.addActionListener(listener);
        searchStudent = new JRadioButton("Student");
        searchStudent.setFont(getDefaultFont());
        searchStudent.setBounds(600, 80, 850, 50);
        searchStudent.addActionListener(listener);
        searchType = new ButtonGroup();
        searchType.add(searchBook); searchType.add(searchStudent);
        entrySubPanel = new JPanel();
        entrySubPanel.setLayout(new BoxLayout(entrySubPanel, BoxLayout.X_AXIS));
        entrySubPanel.setBounds(450, 80, 200, 50);
        entrySubPanel.add(searchBook);
        entrySubPanel.add(searchStudent);
        
        getEntryPanel().add(getSearchEntry()); 
        getEntryPanel().add(getBookSearchKeys());
        getEntryPanel().add(studentSearchKeys); 
        getEntryPanel().add(getSearchButton());
        
        createViewPanel();
        createResultsLabel();
        createViewButton();
        createViewOutputPanel();
        
        getViewButton().addActionListener(listener);
        getSearchButton().addActionListener(listener);
        
        getViewPanel().add(getResultsLabel()); getViewPanel().add(getViewButton()); getViewPanel().add(getViewOutput());
        
        add(getEntryPanel()); add (getViewPanel());
        add(entrySubPanel);
    }
    
    public void loadUserMenu(){
        getWindow().addUser();
    }

    public JComboBox getStudentSearchKeys() {
        return studentSearchKeys;
    }

    public void setStudentSearchKeys(JComboBox studentSearchKeys) {
        this.studentSearchKeys = studentSearchKeys;
    }

    public ButtonGroup getSearchType() {
        return searchType;
    }

    public void setSearchType(ButtonGroup searchType) {
        this.searchType = searchType;
    }

    public JRadioButton getSearchBook() {
        return searchBook;
    }

    public void setSearchBook(JRadioButton searchBook) {
        this.searchBook = searchBook;
    }

    public JRadioButton getSearchStudent() {
        return searchStudent;
    }

    public void setSearchStudent(JRadioButton searchStudent) {
        this.searchStudent = searchStudent;
    }

    public JPanel getEntrySubPanel() {
        return entrySubPanel;
    }

    public void setEntrySubPanel(JPanel entrySubPanel) {
        this.entrySubPanel = entrySubPanel;
    }

    
    // set up admin login, create protected text field for admin password
    
    
    /**
    public void getInput() {

        try {
            // Show the options for User
            System.out.println("Command options are: ");
            for (String s : funcOptions) {
                System.out.println(s);
            }
            // Get User command
            System.out.println("Enter desired command: ");
            Scanner in = new Scanner(System.in);
            function = in.next();
            function = function.toLowerCase();

            // Get Book or Student
            if (function.equals("display") || function.equals("search")
                    || function.equals("add") || function.equals("delete")) {
                System.out.println(String.format("Enter type of file to %s. 'book' or 'student': ", function));
                category = in.next().toLowerCase();
            }

            //search(category);   
            switch (function) {
                case "display":
                    display(category);
                    break;
                case "search":
                    search(category);
                    break;
                case "add":
                    addEntry(category);
                    break;
                case "delete":
                    deleteEntry(category);
                    break;
                case "modify":
                    modEntry();
                    break;
                case "withdraw":
                    withdrawBook();
                    break;
                case "return":
                    returnBook();
                    break;
                case "printbill":
                    printBill();
                    break;
                case "payfines":
                    payFines();
                    break;
                default:
                    throw new InvalidAction(function, "Command does not exist");
            }
        } catch (InvalidAction | FileOverwrite | InvalidType | FileNotFoundException | InputMismatchException ex) {
            System.out.println(ex.getMessage());
            getInput();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Search for book or student
     *
     * @param category: student or book
     * @throws InvalidType
     * @throws FileNotFoundException
     * @throws InvalidAction
     
    public void search(String category) throws InvalidType, FileNotFoundException, InvalidAction, IOException {
        Scanner in = new Scanner(System.in);
        String showKeys[];

        // Create a new dataStorage object, cast as type, save keys values in showKeys
        switch (category) {
            case "student":
                Student studentObject = new Student("");
                showKeys = studentObject.getKeys();

                // Display values in showKeys
                for (String k : showKeys) {
                    System.out.println(k);
                }

                // Get Inputs for key and value
                System.out.println("Search by which key?");
                key = in.nextLine();
                System.out.println("Enter a value to match: ");
                value = in.nextLine();

                // Search method returns all student objects whose attributes match given value
                ArrayList<Student> students = Search.getStudent(key, value);
                System.out.println(String.format("Matches Found: %d", students.size()));
                // Use format method to show all student info
                students.forEach(s -> {
                    System.out.println(Arrays.toString(s.format()));
                });
                break;

            case "book":
                //super.search();
                break;

            default:
                // Throw Invalid Type Error if user input is not "student" or "book"
                throw new InvalidType(category);
        }

    }

    /**
     * Display info about all students or books
     *
     * @param category: student or book
     * @throws InvalidType
     * @throws FileNotFoundException
     */
    public void display(String category) throws InvalidType, FileNotFoundException, InvalidAction, IOException {

        switch (category) {

            case "student":

                // getAll returns all of given category
                DataStorage[] students = Data.getAll(category);
                // Use format method to show all student info
                for (DataStorage s : students) {
                    System.out.println(Arrays.toString(s.format()));
                }
                break;

            case "book":
                //super.display();
                break;

            default:
                throw new InvalidType(category);
        }
    }

    /**
     * add a new entry in file
     *
     * @param category: student or book
     * @throws InvalidType
     * @throws FileNotFoundException
     * @throws FileOverwrite
     
    public void addEntry(String category) throws InvalidType, FileNotFoundException, FileOverwrite, IOException {
        Scanner in = new Scanner(System.in);

        switch (category) {
            case "book":
                System.out.println("Enter an ID for new book: ");
                Book b = new Book(in.next());
                in.nextLine();
                System.out.println("Enter book author: ");
                b.setAuthor(in.nextLine());
                System.out.println("Enter book name: ");
                b.setName(in.nextLine());
                System.out.println("Enter date published: ");
                b.setDatePublished(in.nextLine());
                System.out.println("Enter book publisher: ");
                b.setPublisher(in.nextLine());
                System.out.println("Enter book description: ");
                b.setDescription(in.nextLine());
                // after getting all necessary input for book object, write it to file
                Data.addNewEntry(b);
                System.out.println("New book added: "+ b.getName());
                break;
            case "student":
                System.out.println("Enter an ID for new student: ");
                Student s = new Student(in.next());
                in.nextLine();
                System.out.println("Enter student name: ");
                s.setName(in.nextLine());
                //after getting input for student object, write to file
                Data.addNewEntry(s);
                System.out.println("New student added: "+ s.getName());
                break;
            default:
                throw new InvalidType(category);
        }

    }

    /**
     * Modify a book entry in file
     *
     * @throws InvalidType
     * @throws FileNotFoundException
     * @throws FileOverwrite
     
    public void modEntry() throws InvalidType, InvalidAction, FileOverwrite, IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter ID of book to modify: ");
        String id = in.next();
        in.nextLine();
        try {
            Book book = (Book) Data.writeToObject("book", id);

            System.out.println("Book item fields are 'author', 'name', 'datepub', 'publisher', 'desc'");
            System.out.println("Enter field to change: ");
            String field = in.nextLine().toLowerCase();
            System.out.println("Enter new value for this field: ");
            value = in.nextLine();
            switch (field) {
                case "author":
                    book.setAuthor(value);
                    Data.overwrite(book);
                    break;
                case "name":
                    book.setName(value);
                    Data.overwrite(book);
                    break;
                case "datepub":
                    book.setDatePublished(value);
                    Data.overwrite(book);
                    break;
                case "publisher":
                    book.setPublisher(value);
                    Data.overwrite(book);
                    break;
                case "desc":
                    book.setDescription(value);
                    Data.overwrite(book);
                    break;
                default:
                    throw new InvalidType(field);
        
            }
            System.out.println(String.format("New %s: %s for book %s", field, value, book.getName()));
        }
        catch (FileNotFoundException f) {
            throw new InvalidAction("Modify", String.format("Book with %s %s not found", "ID", id));
        }
        
        
    }

    /**
     * Delete a file
     *
     * @param category: student or book
     * @throws InvalidType
     * @throws FileNotFoundException
     */
    public void deleteEntry(String category) throws InvalidType, FileNotFoundException, FileOverwrite, IOException {
        Scanner in = new Scanner(System.in);

        System.out.println(String.format("Enter ID of %s to be deleted: ", category));
        String id = in.next();
        switch (category) {
            case "student":
                try{                
                    DataStorage sdata = Data.writeToObject(category, id);
                    Data.delete(sdata);
                } catch (FileNotFoundException f) {
                    System.out.println(String.format("%s with ID: %s not found", category, id));
                }

                break;
            case "book":
                try {
                    DataStorage bdata = Data.writeToObject(category, id);
                    Data.delete(bdata);
                } catch (FileNotFoundException f) {
                    System.out.println(String.format("%s with ID: %s not found", category, id));
                }
                
                break;
            default:
                // Throw Invalid Type Error if user input is not "student" or "book"
                throw new InvalidType(category);
        }
        System.out.println(String.format("%s with ID %s has been removed from the system", category, id));
    }

    /**
     * withdraw a book for a student
     *
     *
     * @throws InvalidType
     * @throws FileNotFoundException
     * @throws InvalidAction
     * @throws FileOverwrite
     
    public void withdrawBook() throws InvalidType, FileNotFoundException, InvalidAction, FileOverwrite, IOException {
        
        Scanner in = new Scanner(System.in);
        System.out.println("Enter withdrawing student's A Number (Starting with A): ");
        String sID = in.next();
        Student student = Search.getStudent("A Number", sID).get(0);
        
        System.out.println("Enter ID of book to be withdrawn: ");
        String bID = in.next();
        
        Book book = Search.getBook("ID", bID).get(0);
        
        if (student.canWithdraw() && book.canWithdraw()) {
            student.withdraw(book);
            System.out.println(String.format("%s withdrew %s by %s today, %s.  It is due by %s", student.getName(), book.getName(), book.getAuthor(), book.getDateWithdrawn(), book.getDueDate()));
        } else if (!student.canWithdraw()){
            System.out.println(String.format("No withdraw for %s. They owe %s in fines", student.getName(), student.getFines()+student.getBackFines()));
        } else if (!book.canWithdraw()){
            System.out.println(String.format("%s was withdrawn on %s", book.getName(), book.getDateWithdrawn()));
        }
        ArrayList<Book> studentBooks = student.getWithdrawnBooks();
        System.out.println(String.format("All %s's withdrawn books", student.getName()));
        System.out.println("ID\tTitle\t\tAuthor\t\tDate Withdrawn\t\tFees Owed");
        studentBooks.forEach(bk -> {
            System.out.println(String.format("%s\t%s\t%s\t%s\t%s", bk.getId(), bk.getName(), bk.getAuthor(), bk.getDateWithdrawn(), bk.getFine()));
        });
        // Commit changes to file
        Data.overwrite(student);
        Data.overwrite(book);
        
        
    }

    /**
     * return a book for a student
     *
     * @throws FileNotFoundException
     * @throws InvalidAction
     * @throws FileOverwrite
     
    public void returnBook() throws FileNotFoundException, InvalidAction, FileOverwrite, IOException {
        Scanner in = new Scanner(System.in);
        //Entering student id to create student object
        System.out.println("Enter ID of student returning book: ");
        String sid = in.next();
        //try
        Student student = (Student) Data.writeToObject("student", sid);
        //catch
        
        //Book id for book object
        System.out.println("Enter ID of book being returned: ");
        String bid = in.next();
        
        //try
        Book book = (Book) Data.writeToObject("book", bid);
        //catch
        
        //Returning book from student account
        student.returnBook(book);

        // Commit Changes to file
        Data.overwrite(student);
        Data.overwrite(book);
    }

    /**
     *
     * @throws FileNotFoundException
     */
    public void printBill() throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter ID of student to print bill for: ");
        // Create student object using student ID to access student methods
        String sid = in.next();
        Student s = (Student) Data.writeToObject("student", sid);
        ArrayList<Book> studentBooks = s.getWithdrawnBooks();
        studentBooks.forEach(bk -> {
            System.out.println(String.format("%s has withdrawn: %s on: %s", s.getName(), bk.getName(), bk.getDateWithdrawn()));
            if (bk.getFine()>0){
                System.out.println(String.format("%s owes $%.2f in late fines on %s",s.getName(), bk.getFine(),bk.getName()));
            }
        });
        if (s.getBackFines()>0){
            System.out.println(String.format("%s owes $%.2f in back fines on previously returned books", s.getName(),s.getBackFines()));
        }
        System.out.println("End of "+s.getName()+"'s bill");
    }

    /**
     * pay student fines on returned books
     *
     * @throws FileNotFoundException
     * @throws InputMismatchException
     * @throws InvalidAction
     * @throws FileOverwrite
     
    public void payFines() throws FileNotFoundException, InputMismatchException, InvalidAction, FileOverwrite, IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Pay fines for which student? (Enter ID number)");
        String sid = in.next();
 
        Student s = (Student) Data.writeToObject("student", sid);
        if(s.getBackFines()>0){
            System.out.println("Enter amount to pay in fines");
            float entry = in.nextFloat();
            s.setBackFines(s.getBackFines() - entry);
            System.out.println(String.format("%s now owes $%.2f", s.getName(), s.getBackFines()));
            Data.overwrite(s);
        } else if (s.getBackFines()==0&&s.getFines()>0){
            System.out.println(String.format("%s owes nothing on returned books and %s on withdrawn books. Return withdrawn books before paying fines", s.getName(), s.getFines()));
        } else if (s.getBackFines()==0 && s.getFines()==0){
            System.out.println(String.format("%s owes no money on returned or withdrawn books", s.getName()));
        }

    }
*/

}
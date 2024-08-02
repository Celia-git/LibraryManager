/*
 * Test Interaction through the Command Line Interface
 */
package librarysystem;

import javax.swing.SwingUtilities;
/**
 *
 * @author Celia
 */
public class LibrarySystem{

    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        

        // Create and Show GUI
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Window().configureWindow();
                
            }
        });
    }

}   



        /*


        System.out.println("\nEnter 'c' to continue to program\nEnter 'late' to test the late fee application\nEnter admin pass 'admin' to access the rest of the functions\nEnter 'r' to reset the book and student file data\nEnter 'q' to quit");
        Scanner in = new Scanner(System.in);
        String entry = in.next();
        switch (entry) {
            case "c":   // Open User Program for inputs
                u = new User();
                u.getInput();
            case "late": 
                // Get random book and student to test late fees on
                try {
                    Book testBook = Search.getBook("random", "").get(0);
                    Student testStudent = Search.getStudent("random", "").get(0);
                     // Prompt user for days late
                    System.out.println(String.format("Random book %s and student %s chosen for the late fees demo.\nEnter the amount of days late", testBook.getName(), testStudent.getName()));
                    int daysLate = in.nextInt();
                    LateFeeTester late = new LateFeeTester(daysLate, testStudent, testBook);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidAction ex) {
                    Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileOverwrite ex) {
                Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                }

            case "r":   // Rewrite student and book file entries
                try {
                    CreateTestFiles.newStudents();
                    CreateTestFiles.newBooks();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            default:    // Try admin login with password
                u = new Admin();
                try {
                    adminLogin((Admin) u, entry);
                } catch (InvalidAction ex) {
                    System.out.println(ex.getMessage());
                }
        }

     * @param a: Admin object
     * @param password: user entered password
     * @throws InvalidAction if password is invalid
     
    public static void adminLogin(Admin a, String password) throws InvalidAction {
        if (password.equals(a.getPassword())) {
            a.getInput();
        } else {
            throw new InvalidAction("Admin Login", String.format("%s not recognized", password));
        }
    }
*/


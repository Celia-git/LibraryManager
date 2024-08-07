/*
 * Handle user commands: any level clearance
 */
package userinput;

import datastorage.Book;
import exceptions.InvalidAction;
import exceptions.InvalidType;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
    
    private JPanel adminLoginPanel;
    private JLabel adminLoginLabel;
    private JPasswordField adminLoginEntry;
    private JButton adminLoginButton;
    private JTextField searchEntry;
    private JButton searchButton;
    private JButton viewButton;
    private JComboBox bookSearchKeys;
    private JPanel entryPanel;
    private JPanel viewPanel;
    private JScrollPane viewOutput;
    private JPanel viewOutputPanel;
    private JLabel resultsLabel;
    private Window window;
    private Font defaultFont;
    private Font titleFont; 
     

    public User(Window window) {
        this.window = window;
        this.defaultFont = window.getDefaultFont();
        this.titleFont = window.getTitleFont();
    }
    
    public void configureUserMenu(){
        this.setLayout(null);
        
        createEntryPanel();
        createSearchEntry();
        createSearchButton();
        createBookSearchKeys();
        
       
        entryPanel.add(searchEntry); entryPanel.add(bookSearchKeys); entryPanel.add(searchButton);
        
        
        createViewPanel();
        createResultsLabel();
        createViewButton();
        createViewOutputPanel();
        
        
        viewPanel.add(resultsLabel); viewPanel.add(viewButton); viewPanel.add(viewOutput);
        
        createAdminLoginWidgets();

        // Add Default user widgets
        add(entryPanel); add(viewPanel); add(adminLoginPanel);
        
        // Add functionality to search button, view button, and admin login button
        searchButton.addActionListener((ActionListener) new UserListener(this));
        viewButton.addActionListener((ActionListener) new UserListener(this));
        adminLoginButton.addActionListener((ActionListener) new UserListener(this));
        
        
    }
    
    public void loadAdminMenu(){
        window.addAdmin();
    }
    
    /// Create Widgets
    
    
    public void createEntryPanel(){
        entryPanel = new JPanel();
        entryPanel.setBounds(0, 30, 850, 50);
        entryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        entryPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        
    }
    
    public void createSearchEntry(){
        searchEntry = new JTextField(30);
        searchEntry.setFont(this.defaultFont);
        searchEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    public void createBookSearchKeys(){
        bookSearchKeys = new JComboBox<String>(new Book("").getKeys());

    }
    
    public void createSearchButton(){
        searchButton = new JButton("Go");
        searchButton.setFont(this.defaultFont);
        searchButton.setBounds(150, 0, 20, 20);
    }
    
    public void createViewPanel(){
        viewPanel = new JPanel();   
        viewPanel.setBounds(100, 150, 600, 600);
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
        viewPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
    }
    
    public void createResultsLabel(){
        resultsLabel = new JLabel("", JLabel.CENTER);
        resultsLabel.setFont(this.titleFont);
        resultsLabel.setSize(300, 50);
        resultsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
    }
    public void createViewButton(){        
        viewButton = new JButton("View All");
        viewButton.setFont(this.defaultFont);
        viewButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    }
    
    public void createViewOutputPanel(){
        viewOutputPanel = new JPanel();
        viewOutputPanel.setLayout(new BoxLayout(viewOutputPanel, BoxLayout.Y_AXIS));
        viewOutput = new JScrollPane(viewOutputPanel);
    }
    public void createAdminLoginWidgets(){
        adminLoginPanel = new JPanel();
        adminLoginPanel.setBounds(100, 775, 600, 25);
        adminLoginPanel.setLayout(new BoxLayout(adminLoginPanel, BoxLayout.X_AXIS));
        adminLoginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);        
        adminLoginPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        adminLoginLabel = new JLabel("Enter admin password: ");
        adminLoginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminLoginLabel.setFont(this.defaultFont);
        adminLoginEntry = new JPasswordField(15);
        adminLoginEntry.setEchoChar('*');
        adminLoginButton = new JButton("Go");
        adminLoginPanel.add(adminLoginLabel);
        adminLoginPanel.add(adminLoginEntry);
        adminLoginPanel.add(adminLoginButton);
        
    }
    
    // Getters and Setters
    public JTextField getSearchEntry() {
        return searchEntry;
    }

    public void setSearchEntry(JTextField searchEntry) {
        this.searchEntry = searchEntry;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public void setViewButton(JButton viewButton) {
        this.viewButton = viewButton;
    }

    public JComboBox getBookSearchKeys() {
        return bookSearchKeys;
    }

    public void setBookSearchKeys(JComboBox bookSearchKeys) {
        this.bookSearchKeys = bookSearchKeys;
    }

    public JPanel getEntryPanel() {
        return entryPanel;
    }

    public void setEntryPanel(JPanel entryPanel) {
        this.entryPanel = entryPanel;
    }

    public JPanel getViewPanel() {
        return viewPanel;
    }

    public void setViewPanel(JPanel viewPanel) {
        this.viewPanel = viewPanel;
    }

    public JScrollPane getViewOutput() {
        return viewOutput;
    }

    public void setViewOutput(JScrollPane viewOutput) {
        this.viewOutput = viewOutput;
    }

    public JPanel getViewOutputPanel() {
        return viewOutputPanel;
    }

    public void setViewOutputPanel(JPanel viewOutputPanel) {
        this.viewOutputPanel = viewOutputPanel;
    }

    public JLabel getResultsLabel() {
        return resultsLabel;
    }

    public void setResultsLabel(JLabel resultsLabel) {
        this.resultsLabel = resultsLabel;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Font getDefaultFont() {
        return defaultFont;
    }

    public void setDefaultFont(Font defaultFont) {
        this.defaultFont = defaultFont;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }
    
    public JPanel getAdminLoginPanel() {
        return adminLoginPanel;
    }

    public void setAdminLoginPanel(JPanel adminLoginPanel) {
        this.adminLoginPanel = adminLoginPanel;
    }

    public JLabel getAdminLoginLabel() {
        return adminLoginLabel;
    }

    public void setAdminLoginLabel(JLabel adminLoginLabel) {
        this.adminLoginLabel = adminLoginLabel;
    }

    public JPasswordField getAdminLoginEntry() {
        return adminLoginEntry;
    }

    public void setAdminLoginEntry(JPasswordField adminLoginEntry) {
        this.adminLoginEntry = adminLoginEntry;
    }

    public JButton getAdminLoginButton() {
        return adminLoginButton;
    }

    public void setAdminLoginButton(JButton adminLoginButton) {
        this.adminLoginButton = adminLoginButton;
    }
}

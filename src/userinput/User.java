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
    private JComboBox searchKeys;
    private JPanel entryPanel;
    private JPanel viewPanel;
    private JScrollPane viewOutput;
    private JPanel viewOutputPanel;
    private JLabel resultsLabel;
    private Window window;
    private Font defaultFont;
    private Font titleFont; 
    
    
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

    public JComboBox getSearchKeys() {
        return searchKeys;
    }

    public void setSearchKeys(JComboBox searchKeys) {
        this.searchKeys = searchKeys;
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
    public User(Window window) {
        this.window = window;
    }
    
    public void configureUserMenu(){
        this.setLayout(null);
        
        defaultFont = window.getDefaultFont();
        titleFont = window.getTitleFont();
        
        entryPanel = new JPanel();
        entryPanel.setBounds(0, 30, 850, 50);
        searchEntry = new JTextField(30);
        searchEntry.setFont(defaultFont);
        searchEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchKeys = new JComboBox<String>(new Book("").getKeys());
        searchButton = new JButton("Go");
        searchButton.setFont(defaultFont);
        searchButton.setBounds(150, 0, 20, 20);
        entryPanel.add(searchEntry); entryPanel.add(searchKeys); entryPanel.add(searchButton);
        entryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        entryPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        
        viewPanel = new JPanel();   
        viewPanel.setBounds(100, 150, 600, 600);
        resultsLabel = new JLabel("", JLabel.CENTER);
        resultsLabel.setFont(titleFont);
        resultsLabel.setSize(300, 50);
        resultsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewButton = new JButton("View All");
        viewButton.setFont(defaultFont);
        viewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewOutputPanel = new JPanel();
        viewOutputPanel.setLayout(new BoxLayout(viewOutputPanel, BoxLayout.Y_AXIS));
        viewOutput = new JScrollPane(viewOutputPanel);
        
        viewPanel.add(resultsLabel);
        viewPanel.add(viewButton);
        viewPanel.add(viewOutput);
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
        viewPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        adminLoginPanel = new JPanel();
        adminLoginPanel.setBounds(100, 775, 600, 25);
        adminLoginPanel.setLayout(new BoxLayout(adminLoginPanel, BoxLayout.X_AXIS));
        adminLoginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);        
        adminLoginPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        
        adminLoginLabel = new JLabel("Enter admin password: ");
        adminLoginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminLoginLabel.setFont(defaultFont);
        adminLoginEntry = new JPasswordField("admin", 15);
        adminLoginEntry.setEchoChar('*');
        adminLoginButton = new JButton("Go");
        adminLoginPanel.add(adminLoginLabel);
        adminLoginPanel.add(adminLoginEntry);
        adminLoginPanel.add(adminLoginButton);
        
        

        add(entryPanel); add(viewPanel); add(adminLoginPanel);
        
        // Add functionality to search button
        searchButton.addActionListener((ActionListener) new UserListener(this));
        
        
        // Action Listener for View button
        viewButton.addActionListener((ActionListener) new UserListener(this));

        
    }
    

}

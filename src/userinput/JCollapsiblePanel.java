package userinput;
import librarysystem.Window;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author Celia
 */
public class JCollapsiblePanel extends JPanel {
  private JLabel titleLabel;
  private boolean collapsed;
  private JPanel subPanel;

  public JCollapsiblePanel(String title, Color titleCol, String[] content, Window window) {
    super();

    this.collapsed = true;
    setBackground(titleCol);
    JLabel titleLabel = new JLabel(String.format("                        %1$-62s", title));
    titleLabel.setFont(window.getLabelTitleFont());
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(titleLabel);
    
    // subPanel will contain all details
    subPanel = new JPanel();
    subPanel.setBackground(titleCol);
    subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
    for (String cont: content){
        JLabel label = new JLabel(String.format("                        %1$-62s", cont));
        label.setFont(window.getLabelFont());
        subPanel.add(label);
    }
    
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // Expand
        if (getCollapsed()==true) {
            add(subPanel);
        }
        // Collapse
        else {
            remove(subPanel);
        }
        setCollapsed(!collapsed);
        window.refresh();
      }
    });
  }

  public boolean getCollapsed(){
      return this.collapsed;
  }
  
  public void setCollapsed(boolean collapsed) {
    this.collapsed = collapsed;
  }

  public void setTitleLabel(String titleLabel) {
    this.titleLabel.setText(titleLabel); 
  }
}
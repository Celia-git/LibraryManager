package userinput;
import librarysystem.Window;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JCollapsiblePanel extends JPanel {
  private JLabel titleLabel;
  private boolean collapsed;
  private JPanel subPanel;

  public JCollapsiblePanel(String title, Color titleCol, String[] content, Window window) {
    super();

    this.collapsed = true;
    setBackground(titleCol);
    JLabel titleLabel = new JLabel(title);
    titleLabel.setPreferredSize(new Dimension(600, 20));
    
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(titleLabel);
    
    // subPanel will contain all details
    subPanel = new JPanel();
    subPanel.setBackground(titleCol);
    subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
    for (String cont: content){
        JLabel label = new JLabel(cont);
        subPanel.add(label);
    }
    

    // as Titleborder has no access to the Label we fake the size data ;)
    final JLabel l = new JLabel(title);
    
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // Expand
        if (getCollapsed()==true) {
            add(subPanel);
            setMinimumSize(new Dimension((int) titleLabel.getPreferredSize().getWidth(), 200));   
        }
        // Collapse
        else {
            remove(subPanel);
            setMinimumSize(new Dimension(titleLabel.getPreferredSize()));
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
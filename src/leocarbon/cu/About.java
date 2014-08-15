package leocarbon.cu;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import static leocarbon.cu.ColorUtility.CU;
import static leocarbon.cu.ColorUtility.Monaco18;
import static leocarbon.cu.ColorUtility.RB;
import static leocarbon.cu.ColorUtility.cc;

public class About extends JFrame {
    JLabel story;
    JLabel license;
    JPanel Background;
    
    public About() {
        setTitle(RB.getString("About.Title"));
        Background = new JPanel();
        story = new JLabel("Loading...", JLabel.CENTER);
        license = new JLabel(new ImageIcon(getClass().getResource("/leocarbon/cu/assets/img/license.png")));
        
        story.setFont(Monaco18);
        story.setText("<html>"
                + RB.getString("About.story.text")
                + "<br><br>v 0.γ5.0.1"
                + "<br><br>https://github.com/leocarbon/cu"
                + "<br><br>http://leocarbon.github.io"
                + "</html>");
        story.setSize(CU.getSize().width-40, ColorUtility.Ev.getSize().height-10);
        
        Background.add(story);
        //Background.add(license);
        Background.setBorder(new EmptyBorder(5,20,5,20));
        add(Background);
        
        Background.setPreferredSize(new Dimension(CU.getSize().width,ColorUtility.Ev.getSize().height));
        
        Background.setBackground(cc.getColor());
        story.setForeground(Easyview.evr.getForeground());
        
        setSize(CU.getSize().width,ColorUtility.Ev.getSize().width);
        pack();
        setLocation(CU.getLocation());
        setVisible(true);
        setResizable(false);
    }
    public void setColors() {
        Background.setBackground(cc.getColor());
        story.setForeground(Easyview.evr.getForeground());
    }
}

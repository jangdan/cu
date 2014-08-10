package leocarbon.cu;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import static leocarbon.cu.ColorUtility.A;
import static leocarbon.cu.ColorUtility.CU;
import static leocarbon.cu.ColorUtility.Ev;
import static leocarbon.cu.ColorUtility.cc;
import static leocarbon.cu.GUI.Monaco18;

public class About extends JFrame {
    JLabel story;
    JLabel license;
    JPanel Background;
    
    public About() {
        setTitle("About Color Utility");
        Background = new JPanel();
        story = new JLabel("Loading...", JLabel.CENTER);
        license = new JLabel(new ImageIcon(getClass().getResource("/com/leocarbonate/cu/assets/img/license.png")));
        
        story.setFont(Monaco18);
        story.setText("<html>"
                + "<b>Color is too, an art.</b><br>"
                + "This application makes it easier to choose colors. "
                + "<br>It might have some bugs... It's not perfect. "
                + "<br>Just send me some screenshots and information,"
                + "<br>and I'll try to fix it. "
                + "<br><br>v 0.γ5.0.1"
                + "<br><br>https://github.com/leocarbon/cu"
                + "<br><br>http://leocarbon.github.io"
                + "</html>");
        story.setSize(CU.getSize().width-40, ColorUtility.evpanel.getSize().height-10);
        
        Background.add(story);
        //Background.add(license);
        Background.setBorder(new EmptyBorder(5,20,5,20));
        add(Background);
        
        Background.setPreferredSize(new Dimension(CU.getSize().width,ColorUtility.evpanel.getSize().height));
        
        Background.setBackground(cc.getColor());
        story.setForeground(Easyview.evr.getForeground());
        
        setSize(CU.getSize().width,ColorUtility.evpanel.getSize().width);
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

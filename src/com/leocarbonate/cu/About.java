package com.leocarbonate.cu;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JPanel{
    JLabel story;
    JLabel license;
    public About(){
        setBackground(ColorUtility.cc.getColor());
        Font georgia = new Font("Georgia", Font.PLAIN, 18);
        story = new JLabel();
        
        getForegroundColor();
        
        story.setFont(georgia);
        story.setText("<html><br><br><br>"
                + "<b>Color is an art.</b><br>"
                + "This application makes it easier to choose colors. "
                + "<br>It might have some bugs... It's not perfect. "
                + "<br>Just send me some screenshots and information, and we'll try to fix it. "
                + "<br><br>Color Utility is licensed under a "
                + "<br><b>Creative Commons Attribution-NonCommercial 3.0 Unported license</b>"
                + "<br>which is good I guess"
                + "<br><br>v 0.b2.2.0"
                + "<br><br> https://github.com/leocarbon/cu"
                + "<br><br> http://leocarbonate.com"
                + "</html>");
        story.setSize(550,300);
        add(story);
    }
    private void getForegroundColor(){
        int r = ColorUtility.cc.getColor().getRed();
        int g = ColorUtility.cc.getColor().getGreen();
        int b = ColorUtility.cc.getColor().getBlue();
        if(r >= 192 || g >= 192 || b >= 192 ){
            story.setForeground(ColorUtility.cc.getColor().darker().darker().darker());
        } else if((r == 0 && g == 0 && b == 0)
                ||(r <= 64 && g == 0 && b == 0)
                ||(g <= 64 && b == 0 && r == 0)
                ||(b <= 64 && r == 0 && g == 0)
                ||(r <= 64 && g <= 64 && b == 0)
                ||(g <= 64 && b <= 64 && r == 0)
                ||(b <= 64 && r <= 64 && g == 0)){
            story.setForeground(Color.WHITE);
        } else{
            story.setForeground(ColorUtility.cc.getColor().brighter().brighter().brighter());
        }
    }
}

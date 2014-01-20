package com.leocarbonate.cu;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;

public class Easyview extends JLabel{
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    public static void updateev(Color input){
        int r = input.getRed();
        int g = input.getGreen();
        int b = input.getBlue();
        
        String hex = Integer.toHexString(input.getRGB());
        hex = hex.substring(2, hex.length());
        hex = hex.toUpperCase();
        
        String ahex = Integer.toHexString(input.getRGB());
        ahex = ahex.toUpperCase();
        
        ColorUtility.evr.setText("<html>Red<br>" + r + "</html>");
        ColorUtility.evb.setText("<html>Green<br>" + g + "</html>");
        ColorUtility.evg.setText("<html>Blue<br>" + b + "</html>");
        ColorUtility.evh.setText("<html>Hexadecimal [RGB] <br>#" + hex + "</html>");
        ColorUtility.evha.setText("<html>Hexadecimal [aRGB] <br>0x" + ahex + "</html>");
        
        ColorUtility.evr.setBackground(input);
        ColorUtility.evg.setBackground(input);
        ColorUtility.evb.setBackground(input);
        ColorUtility.evh.setBackground(input);
        ColorUtility.evha.setBackground(input);
            
        if(r >= 192 || g >= 192 || b >= 192 ){
            ColorUtility.evr.setForeground(input.darker().darker().darker());
            ColorUtility.evg.setForeground(input.darker().darker().darker());
            ColorUtility.evb.setForeground(input.darker().darker().darker());
            ColorUtility.evh.setForeground(input.darker().darker().darker());
            ColorUtility.evha.setForeground(input.darker().darker().darker());
            
        } else if((r == 0 && g == 0 && b == 0)
                ||(r <= 64 && g == 0 && b == 0)
                ||(g <= 64 && b == 0 && r == 0)
                ||(b <= 64 && r == 0 && g == 0)
                ||(r <= 64 && g <= 64 && b == 0)
                ||(g <= 64 && b <= 64 && r == 0)
                ||(b <= 64 && r <= 64 && g == 0)
                ||(r <= 32 && b <=32 && g <= 32)){
            ColorUtility.evr.setForeground(Color.WHITE);
            ColorUtility.evg.setForeground(Color.WHITE);
            ColorUtility.evb.setForeground(Color.WHITE);
            ColorUtility.evh.setForeground(Color.WHITE);
            ColorUtility.evha.setForeground(Color.WHITE);
        } else{
            ColorUtility.evr.setForeground(input.brighter().brighter().brighter());
            ColorUtility.evg.setForeground(input.brighter().brighter().brighter());
            ColorUtility.evb.setForeground(input.brighter().brighter().brighter());
            ColorUtility.evh.setForeground(input.brighter().brighter().brighter());
            ColorUtility.evha.setForeground(input.brighter().brighter().brighter());
        }
    }
    
    public static void initGridBagConstraints(){
        Font monaco = new Font("Monaco", Font.PLAIN, 18);
        
        GridBagConstraints c = new GridBagConstraints();
        if (RIGHT_TO_LEFT) {
            ColorUtility.evpanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        if(shouldFill) {
            c.fill = GridBagConstraints.BOTH;
        }
        
        ColorUtility.evr.setFont(monaco);
        ColorUtility.evr.setOpaque(true);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        ColorUtility.evpanel.add(ColorUtility.evr,c);
        
        ColorUtility.evg.setFont(monaco);
        ColorUtility.evg.setOpaque(true);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        ColorUtility.evpanel.add(ColorUtility.evg,c);
        
        ColorUtility.evb.setFont(monaco);
        ColorUtility.evb.setOpaque(true);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        ColorUtility.evpanel.add(ColorUtility.evb,c);
        
        ColorUtility.evh.setFont(monaco);
        ColorUtility.evh.setOpaque(true);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weightx = 0.75;
        c.weighty = 0.75;
        ColorUtility.evpanel.add(ColorUtility.evh,c);
        
        ColorUtility.evha.setFont(monaco);
        ColorUtility.evha.setOpaque(true);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.weightx = 0.8;
        c.weighty = 0.8;
        ColorUtility.evpanel.add(ColorUtility.evha,c);
        
    }
}

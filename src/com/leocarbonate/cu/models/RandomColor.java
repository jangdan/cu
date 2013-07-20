package com.leocarbonate.cu.models;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class RandomColor extends AbstractColorChooserPanel implements ActionListener{
    JButton random;
    Random randomGenerator;
    JTextField randomSeed;
    JLabel currentRandomSeed;
    JLabel randomSeedLabel;
    JPanel randomPanel;
    
    int r, g, b;
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    @Override
    public void updateChooser() {
        
    }

    @Override
    protected void buildChooser() {
        GridBagConstraints c = new GridBagConstraints();
        if (RIGHT_TO_LEFT) {
            randomPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        if(shouldFill) {
            c.fill = GridBagConstraints.BOTH;
        }
        
        randomPanel = new JPanel(new GridBagLayout());
        //scrollPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Scroll Color"));
        
        random = new JButton("Random Color");
        random.setActionCommand("random");
        random.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        randomPanel.add(random,c);
        
        randomSeed = new JTextField("Random seed?", 16);
        randomSeed.setActionCommand("seed");
        randomSeed.addActionListener(this);
        randomSeed.selectAll();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        randomPanel.add(randomSeed,c);
        
        randomSeedLabel = new JLabel("Random Seed: ",JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        randomPanel.add(randomSeedLabel,c);
        
        currentRandomSeed = new JLabel("Current seed: null", JLabel.CENTER);
        currentRandomSeed.setHorizontalTextPosition(JLabel.CENTER);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        randomPanel.add(currentRandomSeed,c);
        
        add(randomPanel);
    }
    
    @Override
    public String getDisplayName() {
        return "Random";
    }

    @Override
    public Icon getSmallDisplayIcon() {
        return null;
    }

    @Override
    public Icon getLargeDisplayIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        r = com.leocarbonate.cu.ColorUtility.cc.getColor().getRed();
        g = com.leocarbonate.cu.ColorUtility.cc.getColor().getGreen();
        b = com.leocarbonate.cu.ColorUtility.cc.getColor().getBlue();
        
        randomGenerator = new Random();
        if("random".equals(e.getActionCommand())){
            System.out.print("Requested random color: ");
            
             com.leocarbonate.cu.ColorUtility.cc.setColor(new Color((randomGenerator.nextInt(255)),randomGenerator.nextInt(255),randomGenerator.nextInt(255)));
            
            System.out.println("Done");
        } else if("seed".equals(e.getActionCommand())){
            if("random".equals(randomSeed.getText())){
                long l = randomGenerator.nextLong();
                randomGenerator.setSeed(l);
                currentRandomSeed.setText("Current seed: " + l);
                System.out.println("Set seed to: " + l);
            } else{
                randomGenerator.setSeed(toAscii(randomSeed.getText()));
                currentRandomSeed.setText("Current seed: " + toAscii(randomSeed.getText()));
                System.out.println("Set seed to: " + toAscii(randomSeed.getText()));
            }
        }
    }
    public static long toAscii(String s){
        StringBuilder sb = new StringBuilder();
        String ascString = null;
        long asciiInt;
        for (int i = 0; i < s.length(); i++){
            sb.append((int)s.charAt(i));
            char c = s.charAt(i);
        }
        ascString = sb.toString();
        if(ascString.length() >= 17) {
            ascString = ascString.substring(0,17);
        }
        asciiInt = Long.parseLong(ascString);
        return asciiInt;
    } 
}

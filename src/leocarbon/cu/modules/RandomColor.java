package leocarbon.cu.modules;

import java.awt.Color;
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
import static leocarbon.cu.ColorUtility.Ev;
import static leocarbon.cu.ColorUtility.RB;
import static leocarbon.cu.ColorUtility.cc;
import org.apache.log4j.Logger;

public class RandomColor extends AbstractColorChooserPanel implements ActionListener{
    JButton random;
    Random randomGenerator;
    JTextField randomSeed;
    JLabel currentRandomSeed;
    JLabel randomSeedLabel;
    JPanel randomPanel;
    
    int r, g, b;
    
    @Override
    public void updateChooser() {
        
    }

    @Override
    protected void buildChooser() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        randomPanel = new JPanel(new GridBagLayout());
        //scrollPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Scroll Color"));
        
        random = new JButton(RB.getString("RC.start"));
        random.setActionCommand("random");
        random.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        randomPanel.add(random,c);
        
        randomSeed = new JTextField(RB.getString("RC.seed"), 16);
        randomSeed.setActionCommand("seed");
        randomSeed.addActionListener(this);
        randomSeed.selectAll();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        randomPanel.add(randomSeed,c);
        
        randomSeedLabel = new JLabel(RB.getString("RC.seed.label"),JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        randomPanel.add(randomSeedLabel,c);
        
        currentRandomSeed = new JLabel(RB.getString("RC.seed.current")+RB.getString("null"), JLabel.CENTER);
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
        return RB.getString("RC");
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
        r = cc.getColor().getRed();
        g = cc.getColor().getGreen();
        b = cc.getColor().getBlue();
        
        randomGenerator = new Random();
        if("random".equals(e.getActionCommand())){
             cc.setColor(new Color((randomGenerator.nextInt(255)),randomGenerator.nextInt(255),randomGenerator.nextInt(255),Ev.a));
            Logger.getLogger(RandomColor.class.getName()).trace("Randomized Color");
        } else if("seed".equals(e.getActionCommand())){
            if("random".equals(randomSeed.getText())){
                long l = randomGenerator.nextLong();
                randomGenerator.setSeed(l);
                currentRandomSeed.setText(RB.getString("RC.seed.current") + l);
                Logger.getLogger(RandomColor.class.getName()).trace("Set seed to: " + l);
            } else if(randomSeed.getText().matches("[0-9]+")){
                randomGenerator.setSeed(Long.parseLong(randomSeed.getText()));
                currentRandomSeed.setText(RB.getString("RC.seed.current") + Long.parseLong(randomSeed.getText()));
                Logger.getLogger(RandomColor.class.getName()).info("Set seed to: " + Long.parseLong(randomSeed.getText()));
            } else {
                randomGenerator.setSeed(toAscii(randomSeed.getText()));
                currentRandomSeed.setText(RB.getString("RC.seed.current") + toAscii(randomSeed.getText()));
                Logger.getLogger(RandomColor.class.getName()).info("Set seed to: " + toAscii(randomSeed.getText()));
            }
        }
    }
    public static long toAscii(String s) {
        StringBuilder sb = new StringBuilder();
        String ascString;
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

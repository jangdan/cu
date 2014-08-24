/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leocarbon.cu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import static leocarbon.cu.ColorUtility.CU;
import static leocarbon.cu.ColorUtility.Ev;
import static leocarbon.cu.ColorUtility.O;
import static leocarbon.cu.ColorUtility.OptionsLAFChange;
import static leocarbon.cu.ColorUtility.RB;
import static leocarbon.cu.ColorUtility.cc;
import static leocarbon.cu.ColorUtility.ccc;
import org.apache.log4j.Logger;

public class Options extends JFrame implements ActionListener {
    JCheckBox texttoggle;
    private class Visibility extends JPanel { private Visibility() {
        Border controlborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Component Visibility");
        setBorder(controlborder);
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel ccmpanel = new JPanel();
        ccmpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Functions"));
        ccmpanel.add(CU.ccmtoggle);
        c.gridx = 0; c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(ccmpanel,c);

        JPanel evpanel = new JPanel(new GridBagLayout());
        evpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Easy View"));
        c.fill = GridBagConstraints.NONE;
        evpanel.add(CU.evtoggle,c);
        
        texttoggle = new JCheckBox("Show Color Values", true);
        texttoggle.addActionListener(O);
        texttoggle.setActionCommand("Ev");
        c.gridy = 1;
        evpanel.add(texttoggle,c);
        
        JPanel Evtoggle = new JPanel(new GridBagLayout()); {
            JCheckBox Redtoggle = new JCheckBox("Red", true);
            c.gridx = 0; c.gridy = 0;
            Evtoggle.add(Redtoggle,c);
            
            JCheckBox Greentoggle = new JCheckBox("Show Color Values", true);
            JCheckBox Bluetoggle = new JCheckBox("Show Color Values", true);
            JCheckBox Hextoggle = new JCheckBox("Show Color Values", true);
            JCheckBox aHextoggle = new JCheckBox("Show Color Values", true);
        }
        c.gridy = 2;
        evpanel.add(Evtoggle,c);
        
        c.gridx = 0; c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(evpanel,c);

        JPanel cmpanel = new JPanel();
        cmpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Picker"));
        cmpanel.add(CU.cmtoggle);
        c.gridx = 0; c.gridy = 2;
        add(cmpanel,c);
    }}
    private class Logging extends JPanel { public Logging() {
        
    }}
    private class Background extends JPanel {
        /* Funciton by 843806(https://community.oracle.com/people/843806?customTheme=otn): https://community.oracle.com/thread/1354255?start=0&tstart=0
         * I modified it a bit.
         */ private void changeBackGround(Container iC, Color Background) {
            iC.setBackground(Background);
            for(Component C : iC.getComponents()){
                if(C instanceof Container
                &&!(C instanceof javax.swing.JTextField)
                &&!(C instanceof javax.swing.JSpinner)) changeBackGround((Container)C, Background);
            }
        } public Background() {
            setLayout(new GridBagLayout());

            GridBagConstraints c = new GridBagConstraints();
            //c.fill = GridBagConstraints.BOTH;

            final JLabel Displaybrightness = new JLabel("Current brightness: "+UIManager.getColor("Panel.background").getRed());
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.09;
            c.weighty = 0.09;
            add(Displaybrightness,c);
            final JSlider brightness = new JSlider(91,255,UIManager.getColor("Panel.background").getRed());
            brightness.setMajorTickSpacing(41);
            brightness.setPaintTicks(true);
            brightness.setPaintLabels(true);
            brightness.addChangeListener((ChangeEvent CE) -> {
                Displaybrightness.setText("Current brightness: " + brightness.getValue());
                Color Background1 = new Color(brightness.getValue(),brightness.getValue(),brightness.getValue());
                CU.getContentPane().setBackground(Background1);
                changeBackGround(cc.getParent(), Background1);
                changeBackGround(ccc.getParent(), Background1);
                if(!brightness.getValueIsAdjusting()) Logger.getLogger(Options.class).trace("Changed brightness to: " + brightness.getValue());
            });
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 2;
            c.gridheight = 1;
            c.weightx = 1;
            c.weighty = 1;
            add(brightness,c);

            JButton brightnessReset = new JButton("Reset background color");
            brightnessReset.addActionListener((ActionEvent AE) -> {
                brightness.setValue(UIManager.getColor("Panel.background").getRed());
            });
            c.gridx = 1;
            c.gridy = 2;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.09;
            c.weighty = 0.09;
            add(brightnessReset,c);

            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            c.gridheight = 1;
            c.weightx = 0.1;
            c.weighty = 0.1;
            //add(new JLabel("<html><font color = 'red'><b>Note: </b></font><i>This function might not work for some Look and Feels.</i></html>"),c);
            Border brightnessBorder = BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Background");
            setBorder(brightnessBorder);
        }
    }
    private static class LookAndFeel extends JPanel { public LookAndFeel() {
        setLayout(new GridLayout(2,0));

        Border plafBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Look and Feel");
        setBorder(plafBorder);

        final UIManager.LookAndFeelInfo[] ILAFs = UIManager.getInstalledLookAndFeels();
        String[] ILAFNames = new String[ILAFs.length];

        for(int a = 0; a < ILAFs.length; ++a) ILAFNames[a] = ILAFs[a].getName();
        final JComboBox LAFJC = new JComboBox(ILAFNames);

        add(LAFJC);

        final JCheckBox pack = new JCheckBox("JFrame.pack() on change", true);
        add(pack);

        LAFJC.addActionListener((ActionEvent AE) -> {
            int index = LAFJC.getSelectedIndex();
            try {
                UIManager.setLookAndFeel(ILAFs[index].getClassName());
                SwingUtilities.updateComponentTreeUI(CU);
                
                CU.setVisible(false);
                CU.dispose();
                CU = null;
                System.gc();
                
                OptionsLAFChange = true;
                CU = new ColorUtility();
                if(pack.isSelected()) CU.pack();
                
                if(UIManager.getLookAndFeel().getClass().getName().equals(ILAFs[index].getClassName())) Logger.getLogger(ColorUtility.class.getName()).info("Changed Look and Feel to: " + UIManager.getLookAndFeel());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException E){
                Logger.getLogger(ColorUtility.class.getName()).error(E);
                Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+ILAFs[index].getClassName());
            }
        });
    }}
    
    
    public static boolean isEasyViewTextVisible = true;
    @Override
    public void actionPerformed(ActionEvent AE) {
        switch(AE.getActionCommand()){
            case "Ev":
                System.out.println("as");
                CU.setResizable(true);
                if(texttoggle.isSelected()){
                    isEasyViewTextVisible = true;
                    Ev.update(cc.getColor());
                } else {
                    isEasyViewTextVisible = false;
                    Ev.update(cc.getColor());
                }
                pack();
                CU.setResizable(false);
                break;
            case "Evred":
                break;
            case "Evgreen":
                break;
            case "Evblue":
                break;
            case "Evhex":
                break;
            case "Evahex":
                break;
        }
    }
    
    JPanel Background;
    public Options() {
        setTitle(RB.getString("Options.Title"));
        Background = new JPanel(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        c.gridx = 0;
        c.gridy = 0;
        Background.add(new Visibility(),c);
        
        c.gridx = 1;
        Background.add(new Logging());
        
        c.gridx = 0;
        c.gridy = 1;
        Background.add(new Background(),c);
        
        c.gridx = 1;
        Background.add(new LookAndFeel(),c);

        Background.setBorder(new EmptyBorder(4,4,4,4));
        
        add(Background);
        
        setAlwaysOnTop(true);
        setSize(cc.getWidth(),cc.getHeight()+ccc.getHeight());
        pack();
        setLocation(Ev.getLocationOnScreen().x, Ev.getLocationOnScreen().y+Ev.getHeight());
        setVisible(true);
        setResizable(false);
    }
}
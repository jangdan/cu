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
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static leocarbon.cu.ColorUtility.CU;
import static leocarbon.cu.ColorUtility.cc;
import static leocarbon.cu.ColorUtility.ccc;
import org.apache.log4j.Logger;

/**
 *
 * @author Leocarbon
 */
public class Options extends JFrame {
    
    /* Funciton by 843806: https://community.oracle.com/thread/1354255?start=0&tstart=0
     * I modified it a bit.
     */
    private static void changeBackGround(Container iC, Color Background) {
     iC.setBackground(Background);
     for(Component C : iC.getComponents()){
          if(C instanceof Container
          &&!(C instanceof javax.swing.JTextField)
          &&!(C instanceof javax.swing.JSpinner)
          &&!(C instanceof javax.swing.JTabbedPane)) changeBackGround((Container)C, Background);
     }
}

//... then call the method with color chooser as the parameter

    public static JPanel newBrightnessController() {
        GridBagConstraints c = GUI.initGridBagConstraints();
        
        JPanel brightnessPanel = new JPanel(new GridBagLayout());
        
        final JLabel Displaybrightness = new JLabel("Current brightness: "+UIManager.getColor("Panel.background").getRed());
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.09;
        c.weighty = 0.09;
        brightnessPanel.add(Displaybrightness,c);
        final JSlider brightness = new JSlider(91,255,UIManager.getColor("Panel.background").getRed());
        //brightness.setMajorTickSpacing(24);
        brightness.setMinorTickSpacing(41);
        brightness.setPaintTicks(true);
        brightness.setPaintLabels(true);
        brightness.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent CE) {
                Logger.getLogger(Options.class).trace("Changed background color to: " + brightness.getValue());
                Displaybrightness.setText("Current brightness: " + brightness.getValue());
                Color Background = new Color(brightness.getValue(),brightness.getValue(),brightness.getValue());
                CU.getContentPane().setBackground(Background);
                /*
                cc.setBackground(Background);
                cc.getParent().setBackground(Background);
                ccc.setBackground(Background);
                cc.setForeground(Background);
                ccc.getParent().setBackground(Background);
                ccc.setForeground(Background);*/
                changeBackGround(cc.getParent(), UIManager.getColor("Panel.background"));
                changeBackGround(ccc.getParent(), UIManager.getColor("Panel.background"));
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        brightnessPanel.add(brightness,c);
        
        JButton brightnessReset = new JButton("Reset background color");
        brightnessReset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent AE){
                brightness.setValue(UIManager.getColor("Panel.background").getRed());
            }
        });
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.09;
        c.weighty = 0.09;
        brightnessPanel.add(brightnessReset,c);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 0.1;
        c.weighty = 0.1;
        brightnessPanel.add(new JLabel("<html><font color = 'red'><b>Note: </b></font><i>This function might not work for some Look and Feels.</i></html>"),c);
        Border brightnessBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Background");
        brightnessPanel.setBorder(brightnessBorder);
        
        return brightnessPanel; 
    }
    
    public static JPanel visibility(){
        Border controlborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Component Visibility");
        final JPanel controls = new JPanel(new GridLayout(0,1));
        controls.setBorder(controlborder);
        
        JPanel ccmpanel = new JPanel();
        ccmpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Functions"));
        ccmpanel.add(CU.ccmtoggle);
        controls.add(ccmpanel);
        
        JPanel evpanel = new JPanel();
        evpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Easy View"));
        
        evpanel.add(CU.evtoggle);
        
        JCheckBox texttoggle = new JCheckBox("Show Color Values", true);
        ActionHandler.createEasyViewTextController(texttoggle);
        evpanel.add(texttoggle);
        
        controls.add(evpanel);
        
        JPanel cmpanel = new JPanel();
        cmpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Picker"));
        cmpanel.add(CU.cmtoggle);
        controls.add(cmpanel);
        
        return controls;
    }
    
    public static JPanel LookAndFeel(){
        JPanel LAF = new JPanel();
        LAF.setLayout(new GridLayout(2,0));
        
        Border plafBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Look and Feel");
        LAF.setBorder(plafBorder);
        
        final UIManager.LookAndFeelInfo[] ILAFs = UIManager.getInstalledLookAndFeels();
        String[] ILAFNames = new String[ILAFs.length];
        
        for(int a = 0; a < ILAFs.length; ++a) ILAFNames[a] = ILAFs[a].getName();
        final JComboBox LAFJC = new JComboBox(ILAFNames);
        
        LAF.add(LAFJC);
        
        final JCheckBox pack = new JCheckBox("JFrame.pack() on change", true);
        LAF.add(pack);
        
        LAFJC.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent AE) {
                int index = LAFJC.getSelectedIndex();
                try {
                    UIManager.setLookAndFeel(ILAFs[index].getClassName());
                    SwingUtilities.updateComponentTreeUI(CU);
                    
                    CU.setVisible(false);
                    CU.dispose();
                    CU = null;
                    System.gc();
                    
                    ColorUtility.CU = new ColorUtility();
                    if(pack.isSelected()) CU.pack();
                    
                    if(UIManager.getLookAndFeel().getClass().getName().equals(ILAFs[index].getClassName())) Logger.getLogger(ColorUtility.class.getName()).info("Changed Look and Feel to: " + UIManager.getLookAndFeel());
                } catch (ClassNotFoundException CNFE){
                    Logger.getLogger(ColorUtility.class.getName()).error(CNFE);
                    Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+ILAFs[index].getClassName());
                } catch (InstantiationException  IE){
                    Logger.getLogger(ColorUtility.class.getName()).error(IE);
                    Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+ILAFs[index].getClassName());
                } catch (IllegalAccessException IAE){
                    Logger.getLogger(ColorUtility.class.getName()).error(IAE);
                    Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+ILAFs[index].getClassName());
                } catch (UnsupportedLookAndFeelException ULAFE){
                    Logger.getLogger(ColorUtility.class.getName()).error(ULAFE);
                    Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+ILAFs[index].getClassName());
                }
            }
        });
        return LAF;
    }
    
    public static JPanel logging() {
        JPanel L = new JPanel();
        
        return L;
    }
    
    public Options(){
        setTitle("Color Utility Options");
        setLayout(new GridBagLayout());
        //Initialize GridBagConstraints
        GridBagConstraints c = GUI.initGridBagConstraints();

        
        //Add plafComponents
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.2;
        c.weighty = 0.2;
        add(LookAndFeel(),c);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.ipadx = 20;
        c.ipady = 20;
        add(visibility(),c);
        
        c.gridx = 0;
        c.gridy = 3;
        add(newBrightnessController(),c);
        
        setAlwaysOnTop(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}

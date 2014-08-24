package leocarbon.cu.modules;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static leocarbon.cu.ColorUtility.RB;
import static leocarbon.cu.ColorUtility.cc;
import org.apache.log4j.Logger;

public class ScrollColor extends AbstractColorChooserPanel implements ActionListener, ChangeListener{
    public JToggleButton scroll;
    JToggleButton brightnessScroll;
    JToggleButton saturationScroll;
    JSlider scrollSpeed;
    JSlider brightnessScrollSpeed;
    JSlider saturationScrollSpeed;
    JPanel scrollPanel;
    
    scrolljob job;
    bscrolljob bjob;
    sscrolljob sjob;
    
    int hr, hg, hb;
    int sr, sg, sb;
    int br, bg, bb;
    float[] hsb = new float[3];
    float[] shsb = new float[3];
    float[] bhsb = new float[3];
    int hdelay, sdelay, bdelay;
 
    
    @Override
    public void updateChooser() {}

    @Override
    protected void buildChooser() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        scrollPanel = new JPanel(new GridBagLayout());
        //scrollPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Scroll Color"));
        
        scroll = new JToggleButton(RB.getString("SC.start.hue"));
        scroll.setActionCommand("hscroll");
        scroll.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        scrollPanel.add(scroll,c);
        
        brightnessScroll = new JToggleButton(RB.getString("SC.start.brightness"));
        brightnessScroll.setActionCommand("bscroll");
        brightnessScroll.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        scrollPanel.add(brightnessScroll,c);
        brightnessScroll.setEnabled(false); //Doesn't function as expected
        
        saturationScroll = new JToggleButton(RB.getString("SC.start.saturation"));
        saturationScroll.setActionCommand("sscroll");
        saturationScroll.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        scrollPanel.add(saturationScroll,c);
        
        scrollSpeed = new JSlider(0,500,100);
        scrollSpeed.addChangeListener(this);
        scrollSpeed.setMajorTickSpacing(100);
        scrollSpeed.setMinorTickSpacing(20);
        scrollSpeed.setPaintTicks(true);
        scrollSpeed.setPaintLabels(true);
        scrollSpeed.setSnapToTicks(true);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        scrollPanel.add(scrollSpeed,c);
        
        brightnessScrollSpeed = new JSlider(0,500,100);
        brightnessScrollSpeed.addChangeListener(this);
        brightnessScrollSpeed.setMajorTickSpacing(100);
        brightnessScrollSpeed.setMinorTickSpacing(20);
        brightnessScrollSpeed.setPaintTicks(true);
        brightnessScrollSpeed.setPaintLabels(true);
        brightnessScrollSpeed.setSnapToTicks(true);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        scrollPanel.add(brightnessScrollSpeed,c);
        
        saturationScrollSpeed = new JSlider(0,500,100);
        saturationScrollSpeed.addChangeListener(this);
        saturationScrollSpeed.setMajorTickSpacing(100);
        saturationScrollSpeed.setMinorTickSpacing(20);
        saturationScrollSpeed.setPaintTicks(true);
        saturationScrollSpeed.setPaintLabels(true);
        saturationScrollSpeed.setSnapToTicks(true);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        scrollPanel.add(saturationScrollSpeed,c);
        
        brightnessScroll.setEnabled(false); brightnessScrollSpeed.setEnabled(false); //Doesn't function as expected
        
        add(scrollPanel);
    }

    @Override
    public String getDisplayName() {
        return RB.getString("SC");
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
    public void actionPerformed(ActionEvent AE) {
        switch(AE.getActionCommand()){
            case "hscroll":
                if(scroll.isSelected()){
                    (job = new scrolljob()).execute();
                    Logger.getLogger(ScrollColor.class.getName()).trace("Hue fading has started");
                } else {
                    job.cancel(true);
                    job = null;
                    Logger.getLogger(ScrollColor.class.getName()).trace("Hue fading has ended");
                }
                break;
            case "bscroll":
                if(brightnessScroll.isSelected()){
                    (bjob = new bscrolljob()).execute();
                    saturationScroll.setEnabled(false);
                    Logger.getLogger(ScrollColor.class.getName()).trace("Brightness fading has started");
                } else {
                    bjob.cancel(true);
                    bjob = null;
                    saturationScroll.setEnabled(true);
                    Logger.getLogger(ScrollColor.class.getName()).trace("Brightness fading has ended");
                }
                break;
            case "sscroll":
                if(saturationScroll.isSelected()){
                    (sjob = new sscrolljob()).execute();
                    //brightnessScroll.setEnabled(false);
                    Logger.getLogger(ScrollColor.class.getName()).trace("Saturation fading has started");
                } else {
                    sjob.cancel(true);
                    sjob = null;
                    //brightnessScroll.setEnabled(true);
                    Logger.getLogger(ScrollColor.class.getName()).trace("Saturation fading has ended");
                }
                break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent CE) {
        JSlider source = (JSlider)CE.getSource();
        if (!source.getValueIsAdjusting()) {
            if(source == scrollSpeed){
                hdelay = (int)source.getValue();
            } else if(source == brightnessScrollSpeed){
                bdelay = (int)source.getValue();
            } else if(source == saturationScrollSpeed){
                sdelay = (int)source.getValue();
            }
        }    
    }
    
    

    public class scrolljob extends SwingWorker<Integer, Integer> {
        @Override
        protected Integer doInBackground() throws Exception {
            int i = 0;
            while (!isCancelled()) {
                i = getColor(cc.getColor());
                publish(i);
                Thread.sleep(hdelay);
            }
            return i;
        }
        @Override
        protected void process(List<Integer> i) {
            cc.setColor(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
        }

        public int getColor(Color currentColor) {
            hr = currentColor.getRed();
            hg = currentColor.getGreen();
            hb = currentColor.getBlue();
            Color.RGBtoHSB(hr, hg, hb, hsb);
            hsb[0] += (float)1/360;
            return Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        }
    }
    
    
    
    public class bscrolljob extends SwingWorker<Integer, Integer>{
        @Override
        protected Integer doInBackground() throws Exception {
            int i = 0;
            while (!isCancelled()) {
                i = getColor(cc.getColor());
                publish(i);
                Thread.sleep(bdelay);
            }
            return i;
        }
        @Override
        protected void process(List<Integer> i) {
            cc.setColor(Color.HSBtoRGB(bhsb[0], bhsb[1], bhsb[2]));
        }

        public int getColor(Color currentColor) {
            br = currentColor.getRed();
            bg = currentColor.getGreen();
            bb = currentColor.getBlue();
            Color.RGBtoHSB(br, bg, bb, bhsb);
            bhsb[2] += (float)1/100;
            return Color.HSBtoRGB(bhsb[0], bhsb[1], bhsb[2]);
        }
    }
    
    
    
    public class sscrolljob extends SwingWorker<Integer, Integer>{
        @Override
        protected Integer doInBackground() throws Exception {
            int i = 0;
            while (!isCancelled()) {
                i = getColor(cc.getColor());
                publish(i);
                Thread.sleep(sdelay);
            }
            return i;
        }
        @Override
        protected void process(List<Integer> i) {
            cc.setColor(Color.HSBtoRGB(shsb[0], shsb[1], shsb[2]));
        }

        public int getColor(Color currentColor) {
            sr = currentColor.getRed();
            sg = currentColor.getGreen();
            sb = currentColor.getBlue();
            Color.RGBtoHSB(sr, sg, sb, shsb);
            shsb[1] += (float)1/100;
            return Color.HSBtoRGB(shsb[0], shsb[1], shsb[2]);
        }
    }
}

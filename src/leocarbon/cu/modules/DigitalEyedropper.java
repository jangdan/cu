package leocarbon.cu.modules;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import static leocarbon.cu.ColorUtility.CU;
import static leocarbon.cu.GUI.initGridBagConstraints;
import org.apache.log4j.Logger;

public class DigitalEyedropper extends AbstractColorChooserPanel implements ActionListener {
    public JToggleButton deyedstart;
    
    int x,y;
    pickJob jp;
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    void execute() {
        (jp = new pickJob()).execute();
    }

    void cancel() {
        jp.cancel(true);
        jp = null;
    }

    @Override
    public void updateChooser() {
        
    }

    @Override
    protected void buildChooser() {
        GridBagConstraints c = initGridBagConstraints();
        
        deyedstart = new JToggleButton("Start DigitalEyedropper");
        deyedstart.addActionListener(this);
        JPanel deyed = new JPanel();
        
        deyed.add(deyedstart,BorderLayout.CENTER);
        
        add(deyed);
    }

    @Override
    public String getDisplayName() {
        return "Eyedropper";
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
        if(deyedstart.isSelected()){
            (jp = new pickJob()).execute();
            Logger.getLogger(DigitalEyedropper.class.getName()).info("DigitalEyedropper initialized.");
        } else {
            jp.cancel(true);
            jp = null;
            Logger.getLogger(DigitalEyedropper.class.getName()).info("DigitalEyedropper deinitialized.");
        }
    }
    
    public class pickJob extends SwingWorker<Integer, Integer> {
        Color color;
        Robot picker;
        int intColor;
        
        @Override
        protected Integer doInBackground() throws Exception {
            intColor = 0;
            picker = new Robot();
            while (!isCancelled()){
                intColor = getColor();
                publish(intColor);
            }
            return intColor;
        }
        @Override
        protected void process(List<Integer> i) {
            CU.cc.setColor(intColor);
        }

        public int getColor() {
            Color yaycolor = picker.getPixelColor(
                    (int) MouseInfo.getPointerInfo().getLocation().getX(),
                    (int) MouseInfo.getPointerInfo().getLocation().getY()
                    );
            return yaycolor.getRGB();
        }
    }
}

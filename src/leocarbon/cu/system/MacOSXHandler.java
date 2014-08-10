package leocarbon.cu.system;

import javax.swing.JPanel;
import leocarbon.cu.About;
import leocarbon.cu.Options;
import static leocarbon.cu.ColorUtility.A;
import static leocarbon.cu.ColorUtility.O;
import org.simplericity.macify.eawt.ApplicationEvent;
import org.simplericity.macify.eawt.ApplicationListener;

public class MacOSXHandler implements ApplicationListener {
    JPanel options;
    private Object instance;
    
    @Override
    public void handleAbout(ApplicationEvent event) {
        A = new About();
    }

    @Override
    public void handleOpenApplication(ApplicationEvent event) {
        
    }

    @Override
    public void handleOpenFile(ApplicationEvent event) {
        
    }

    @Override
    public void handlePreferences(ApplicationEvent event) {
        O = new Options();
    }

    @Override
    public void handlePrintFile(ApplicationEvent event) {
        
    }

    @Override
    public void handleQuit(ApplicationEvent event) {
        instance = null;
        System.gc();
        System.exit(0);
    }

    @Override
    public void handleReOpenApplication(ApplicationEvent event) {
        
    }
}

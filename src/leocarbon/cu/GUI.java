package leocarbon.cu;

import java.awt.Font;
import leocarbon.cu.handlers.ActionHandler;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import static leocarbon.cu.ColorUtility.menu;
import static leocarbon.cu.ColorUtility.menuitem;

public class GUI {
    public static Font Monaco18;
    public static void constructFonts() {
        Monaco18 = new Font("Monaco", Font.PLAIN, 18);
    }
    
    public static GridBagConstraints initGridBagConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        return c;
    }
    
    public static JMenu createmenu(String description, String[] menuitemNames, int[] accelerator) {
        for(int a = 0; a < menuitemNames.length; ++a){
            if("/".equals(menuitemNames[a])) menu.addSeparator();
            else {
                menuitem = new JMenuItem(menuitemNames[a]);
                if(accelerator[a] == -1){
                    menuitem.setAccelerator(null);
                } else {
                    menuitem.setAccelerator(KeyStroke.getKeyStroke(accelerator[a], Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
                }
                menuitem.addActionListener(ActionHandler.ActionListener);
                menuitem.setActionCommand(description+".menu."+menuitemNames[a]);
                menu.add(menuitem);
            }
        }
        menu.getAccessibleContext().setAccessibleDescription(description);
        
        return menu;
    }
}

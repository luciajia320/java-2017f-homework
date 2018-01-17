package tangwy.nju.edu.cn.huluwasvsdemons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class FunctionButton extends JButton {
    FunctionButton(String name, int x, int y, ActionListener listener, boolean enable){
        super(name);
        setBounds(x,y,240,200);
        addActionListener(listener);
        setFont(new Font("宋体", Font.BOLD,30));
        setFocusPainted(false);
        setFocusable(false);
        setEnabled(enable);
    }
}

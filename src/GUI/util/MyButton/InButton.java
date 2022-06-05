package GUI.util.MyButton;

import javax.swing.*;

public class InButton extends JButton {
        public InButton() {
            this.setText("In PDF");
            this.setIcon(new ImageIcon(this.getClass().getResource("/images/icon/button_print.png")));
        }
    }

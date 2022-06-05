/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.util.MyButton;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class XoaButton extends JButton {
    public XoaButton() {
        this.setText("Xóa");
        this.setIcon(new ImageIcon(this.getClass().getResource("/images/icon/button_delete.png")));
    }
}

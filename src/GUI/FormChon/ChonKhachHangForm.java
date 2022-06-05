package GUI.FormChon;

import GUI.FormHienThi.HienThiKhachHang;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChonKhachHangForm extends JFrame {

    HienThiKhachHang formHienThi = new HienThiKhachHang();

    JButton btnOK = new JButton("Chọn");
    JButton btnCancel = new JButton("Thoát");
    JTextField txTarget;

    public ChonKhachHangForm(JTextField _txTarget) {
        this.setTitle("Chọn khách hàng");
        this.setLayout(new BorderLayout());
        this.setSize(1200 - 200, 600);
        this.setLocationRelativeTo(null);
        this.txTarget = _txTarget;

        // ======= Buttons Panel ===========
        btnCancel.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/icon/icons8_cancel.png"))));
        btnOK.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/icon/icons8_ok.png"))));
        
        JPanel plBtns = new JPanel();
        plBtns.add(btnOK);
        plBtns.add(btnCancel);
        
        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);

        // actionlistener
        btnOK.addActionListener((ActionEvent ae) -> {
            String makh = formHienThi.getSelectedRow(1);
            if (makh != null) {
                this.txTarget.setText(makh);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng nào!");
            }
        });
        
        btnCancel.addActionListener((ae) -> this.dispose());
    }
}

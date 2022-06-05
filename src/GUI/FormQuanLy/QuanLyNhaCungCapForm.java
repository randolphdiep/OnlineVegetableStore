package GUI.FormQuanLy;

import BUS.NhaCungCapBUS;
import GUI.FormHienThi.HienThiNhaCungCap;
import GUI.FormThemSua.ThemSuaNhaCungCapForm;
import GUI.LoginForm;
//import GUI.util.MyButton.ExportExcelButton;
//import GUI.util.MyButton.ImportExcelButton;
import GUI.util.MyButton.SuaButton;
import GUI.util.MyButton.ThemButton;
import GUI.util.MyButton.XoaButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class QuanLyNhaCungCapForm extends JPanel {

    HienThiNhaCungCap formHienThi = new HienThiNhaCungCap();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
//    ExportExcelButton btnXuatExcel = new ExportExcelButton();
//    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLyNhaCungCapForm() {
        setLayout(new BorderLayout());

        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlNCC")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
          //  btnNhapExcel.setEnabled(false);
        }

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);
//        plBtn.add(btnXuatExcel);
//        plBtn.add(btnNhapExcel);

        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtn, BorderLayout.NORTH);

        btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
            formHienThi.refresh();
        });
//        btnNhapExcel.addActionListener((ActionEvent ae) -> {
//            new DocExcel().docFileExcelNhaCungCap();
//        });
//        btnXuatExcel.addActionListener((ActionEvent ae) -> {
//            new XuatExcel().xuatFileExcelNhaCungCap();
//        });
    }

    private void btnSuaMouseClicked() {
        String mancc = formHienThi.getSelectedRow(1);
        if (mancc != null) {
            ThemSuaNhaCungCapForm suancc = new ThemSuaNhaCungCapForm("Sửa", mancc);

            suancc.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhà cung cấp nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String mancc = formHienThi.getSelectedRow(1);
        if (mancc != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhà cung cấp " + mancc + " ?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                new NhaCungCapBUS().delete(mancc);
                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhà cung cấp nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaNhaCungCapForm themncc = new ThemSuaNhaCungCapForm("Thêm", "");
        themncc.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}

package GUI.FormQuanLy;

import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import GUI.util.MyButton.InButton;
import util.WritePDF.WritePDF;
import GUI.FormHienThi.HienThiHoaDon;
import GUI.FormThemSua.ThemSuaHoaDonForm;
import GUI.LoginForm;
import GUI.util.MyTable;
//import GUI.util.MyButton.ExportExcelButton;
//import GUI.util.MyButton.ImportExcelButton;
import GUI.util.MyButton.SuaButton;
import GUI.util.MyButton.ThemButton;
import GUI.util.MyButton.XoaButton;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

public class QuanLyHoaDonForm extends JPanel {

    HienThiHoaDon formHienThi = new HienThiHoaDon();
    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    //ImportExcelButton btnNhapExcel = new ImportExcelButton();
    //ExportExcelButton btnXuatExcel = new ExportExcelButton();
    InButton btnPrintPDF =new InButton();

    public QuanLyHoaDonForm() {
        setLayout(new BorderLayout());

        // buttons
       if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlHoaDon")) {
//            btnNhapExcel.setEnabled(false);
        //btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
     //   btnSua.setEnabled(false);
       }
        

        JPanel plBtn = new JPanel();
        //plBtn.add(btnThem);
        plBtn.add(btnXoa);
       // plBtn.add(btnSua);
     //   plBtn.add(btnXuatExcel);
       // plBtn.add(btnNhapExcel);
        plBtn.add(btnPrintPDF);

        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtn, BorderLayout.NORTH);

        // actionlistener
        btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
        });
//        btnXuatExcel.addActionListener((ActionEvent ae) -> {
//            new XuatExcel().xuatFileExcelHoaDon();
//        });
//        btnNhapExcel.addActionListener((ActionEvent ae) -> {
//            JOptionPane.showMessageDialog(this, "Chức năng chưa hoàn thành!");
//        });
        btnPrintPDF.addActionListener((ae) -> {
            if (formHienThi.getSelectedRow(0) != null) {
                MyTable mtb = formHienThi.getTable();
                new WritePDF().writeHoaDon(String.valueOf(mtb.getTable().getValueAt(mtb.getTable().getSelectedRow(), 1)));
            } else {
                JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn nào để in");
            }
        });
    }

    private void btnSuaMouseClicked() {
        String mahd = formHienThi.getSelectedRow(1);
        if (mahd != null) {
            ThemSuaHoaDonForm tshd = new ThemSuaHoaDonForm("Sửa", mahd);
            tshd.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    formHienThi.refresh();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String mahd = formHienThi.getSelectedRow(1);
        if (mahd != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hóa đơn " + mahd
                    + " ? Mọi chi tiết trong hóa đơn sẽ bị xóa theo",
                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                new ChiTietHoaDonBUS().deleteAll(mahd);
                new HoaDonBUS().delete(mahd);

                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaHoaDonForm themhd = new ThemSuaHoaDonForm("Thêm", "");
        themhd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                formHienThi.refresh();
            }
        });
    }
}

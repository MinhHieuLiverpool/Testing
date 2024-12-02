package GUI.Component;

import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import javax.swing.*;

import java.util.Date;

import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;

public class SearchDaytoDay extends JPanel {

    public SearchDaytoDay() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jDateChooser1 = new JDateChooser();
        jDateChooser2 = new JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(221, 221, 221)));
        setPreferredSize(new java.awt.Dimension(630, 100));

        jLabel1.setText("Ngày Bắt đầu");
        jLabel2.setText("Ngày Kết thúc");

        // Lắng nghe sự kiện thay đổi ngày trên jDateChooser1
        jDateChooser1.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    validateAndFilter();
                }
            }
        });

        // Lắng nghe sự kiện thay đổi ngày trên jDateChooser2
        jDateChooser2.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    validateAndFilter();
                }
            }
        });

        // Thiết lập layout giống SearchTongTien
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addGap(50, 50, 50)
                                .addComponent(jDateChooser1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addComponent(jDateChooser2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDateChooser1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public JDateChooser tu;

    public JDateChooser giatritu() {
        tu = jDateChooser1;
        return tu;
    }
    public JDateChooser den;

    public JDateChooser giatriden() {
        den = jDateChooser2;
        return den;
    }

    // Hàm kiểm tra và lọc dữ liệu
    private void validateAndFilter() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date startDateObj = jDateChooser1.getDate();
            Date endDateObj = jDateChooser2.getDate();

            Date maxDate = new Date();

            if (endDateObj == null) {
                endDateObj = maxDate;
            }

            if (startDateObj != null && endDateObj == maxDate) {
//                System.out.println("Chỉ có ngày bắt đầu: " + sdf.format(startDateObj));
            }

            // Kiểm tra nếu ngày bắt đầu lớn hơn ngày kết thúc
            if (startDateObj != null && startDateObj.after(endDateObj)) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể lớn hơn ngày kết thúc.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String startDate = startDateObj != null ? sdf.format(startDateObj) : "Không có ngày bắt đầu";
            String endDate = sdf.format(endDateObj);
//            System.out.println("Tìm kiếm ngày từ: " + startDate + " đến " + endDate);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng chọn ngày hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify                     
    private JDateChooser jDateChooser1;
    private JDateChooser jDateChooser2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    // End of variables declaration                   
}

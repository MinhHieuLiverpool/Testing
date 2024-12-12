package TestAPI;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateNhanVienHandler implements HttpHandler {
    private final NhanVienBUS nvBUS = new NhanVienBUS();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("PUT".equals(exchange.getRequestMethod())) {
            // Đọc dữ liệu từ body
            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            System.out.println("Request body: " + requestBody);

            try {
                // Parse JSON
                JSONObject json = new JSONObject(requestBody);

            
            // Kiểm tra và validate từng trường
            if (!json.has("ho") || json.getString("ho").trim().isEmpty()) {
                sendErrorResponse(exchange, 400, "Trường 'Họ' không được để trống!");
                return;
            }

            if (!json.has("ten") || json.getString("ten").trim().isEmpty()) {
                sendErrorResponse(exchange, 400, "Trường 'Tên' không được để trống!");
                return;
            }

            if (!json.has("gioiTinh") || json.getString("gioiTinh").trim().isEmpty()) {
                sendErrorResponse(exchange, 400, "Trường 'Giới tính' không được để trống!");
                return;
            }

            if (!json.has("soDienThoai") || json.getString("soDienThoai").trim().isEmpty()) {
                sendErrorResponse(exchange, 400, "Trường 'Số điện thoại' không được để trống!");
                return;
            }

            if (!json.has("email") || json.getString("email").trim().isEmpty()) {
                sendErrorResponse(exchange, 400, "Trường 'Email' không được để trống!");
                return;
            }

            // Validate độ dài của họ và tên
            String ho = json.getString("ho").trim();
            if ( ho.length() > 25 || ho.length() <2 ) {
                sendErrorResponse(exchange, 400, "Trường 'Họ' phải có độ dài bé hơn 25 ký tự!");
                return;
            }

            String ten = json.getString("ten").trim();
            if ( ten.length() > 25 || ten.length() <2) {
                sendErrorResponse(exchange, 400, "Trường 'Tên' phải có độ dài bé hơn 25 ký tự!");
                return;
            }

            // Validate giới tính (phải là Nam hoặc Nữ)
            String gioiTinh = json.getString("gioiTinh").trim();
            if (!"Nam".equalsIgnoreCase(gioiTinh) && !"Nữ".equalsIgnoreCase(gioiTinh)) {
                sendErrorResponse(exchange, 400, "Trường 'Giới tính' phải là 'Nam' hoặc 'Nữ'!");
                return;
            }

            // Validate email
            String email = json.getString("email").trim();
            if (!isValidEmail(email)) {
                sendErrorResponse(exchange, 400, "Trường 'Email' không hợp lệ!");
                return;
            }
            

            // Validate số điện thoại
            String soDienThoai = json.getString("soDienThoai").trim();
            if (!isValidPhoneNumber(soDienThoai)) {
                sendErrorResponse(exchange, 400, "Trường 'Số điện thoại' không hợp lệ!");
                return;
            }
            
            if (isDuplicate(email, soDienThoai,json.getInt("id"))) {
                    sendErrorResponse(exchange, 409, "Email hoặc số điện thoại đã tồn tại!");
                    return;
                }

                // Create NhanVienDTO
                NhanVienDTO nhanVien = new NhanVienDTO();
                nhanVien.setId(json.getInt("id"));
                nhanVien.setHo(json.getString("ho"));
                nhanVien.setTen(json.getString("ten"));
                nhanVien.setGioiTinh(gioiTinh);
                nhanVien.setSoDienThoai(soDienThoai);
                nhanVien.setEmail(email);
                // Thực hiện cập nhật nhân viên
                boolean result = nvBUS.update(nhanVien);

                // Phản hồi
                if (result) {
                    String successResponse = "Cập nhật nhân viên thành công!";
                    exchange.sendResponseHeaders(200, successResponse.getBytes().length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(successResponse.getBytes());
                    }
                } else {
                    sendErrorResponse(exchange, 500, "Cập nhật nhân viên thất bại!");
                }

            } catch (Exception e) {
                sendErrorResponse(exchange, 500, "Lỗi xử lý: " + e.getMessage());
            }
        } else {
            // Phản hồi nếu không phải PUT
            sendErrorResponse(exchange, 405, "Chỉ hỗ trợ phương thức PUT!");
        }
    }

    // Validate email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) return false;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validate phone number (10-11 digits)
    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^0\\d{9,10}$";
        return phoneNumber.matches(phoneRegex);
    }

    // Phương thức hỗ trợ gửi lỗi
    private void sendErrorResponse(HttpExchange exchange, int statusCode, String errorMessage) throws IOException {
        exchange.sendResponseHeaders(statusCode, errorMessage.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errorMessage.getBytes());
        }
    }
    
    private boolean isDuplicate(String email, String soDienThoai, int id) {
    // Duyệt qua tất cả nhân viên để kiểm tra trùng email hoặc số điện thoại
    for (NhanVienDTO nhanVien : nvBUS.getAll()) {
        // Bỏ qua nhân viên có id giống với nhân viên hiện tại đang xét (đang cập nhật)
        if (nhanVien.getId() == id) {
            continue;  // Bỏ qua vòng lặp này nếu id trùng
        }
        
        // Kiểm tra trùng email (so sánh không phân biệt chữ hoa chữ thường)
        if (nhanVien.getEmail().equalsIgnoreCase(email)) {
            return true;  // Trùng email
        }

        // Kiểm tra trùng số điện thoại
        if (nhanVien.getSoDienThoai().equals(soDienThoai)) {
            return true;  // Trùng số điện thoại
        }
    }
    return false;  // Không trùng email hay số điện thoại
}
}
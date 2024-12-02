package TestAPI;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNhanVienHandler implements HttpHandler {
    private final NhanVienBUS nvBUS = new NhanVienBUS();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            // Đọc dữ liệu từ body
            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            System.out.println("Request body: " + requestBody);

         try {
            // Parse JSON từ request body
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
            if ( ho.length() > 25) {
                sendErrorResponse(exchange, 400, "Trường 'Họ' phải có độ dài bé hơn 25 ký tự!");
                return;
            }

            String ten = json.getString("ten").trim();
            if ( ten.length() > 25) {
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
            
            if (isDuplicate(email, soDienThoai)) {
                    sendErrorResponse(exchange, 409, "Email hoặc số điện thoại đã tồn tại!");
                    return;
                }

            // Tạo NhanVienDTO
            NhanVienDTO nhanVien = new NhanVienDTO();
            nhanVien.setHo(ho);
            nhanVien.setTen(ten);
            nhanVien.setGioiTinh(gioiTinh);
            nhanVien.setSoDienThoai(soDienThoai);
            nhanVien.setEmail(email);

            // Thêm nhân viên thông qua BUS
            boolean result = nvBUS.add(nhanVien);

            if (result) {
                String successResponse = "Thêm nhân viên thành công!";
                exchange.sendResponseHeaders(200, successResponse.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(successResponse.getBytes());
                }
            } else {
                sendErrorResponse(exchange, 500, "Thêm nhân viên thất bại!");
            }

        } catch (Exception e) {
            sendErrorResponse(exchange, 500, "Lỗi xử lý: " + e.getMessage());
        }
    } else {
        sendErrorResponse(exchange, 405, "Chỉ hỗ trợ phương thức POST!");
    }
}

    
    
    
    // Phương thức hỗ trợ gửi lỗi
    private void sendErrorResponse(HttpExchange exchange, int statusCode, String errorMessage) throws IOException {
        exchange.sendResponseHeaders(statusCode, errorMessage.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errorMessage.getBytes());
        }
    }

    // Validate email
    private boolean isValidEmail(String email) {
    // Biểu thức chính quy kiểm tra email hợp lệ theo chuẩn RFC 5322
    String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$";

    if (email == null) return false; // Kiểm tra email không được null

    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches(); // Trả về true nếu email hợp lệ
}


    // Validate phone number (10-11 digits)
    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^0\\d{9}$"; // Chỉ cho phép số điện thoại bắt đầu bằng '0' và có đúng 10 chữ số
        return phoneNumber.matches(phoneRegex);
        }
    
    private boolean isDuplicate(String email, String soDienThoai) {
        for (NhanVienDTO nhanVien : nvBUS.getAll()) {
            if (nhanVien.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
            if (nhanVien.getSoDienThoai().equals(soDienThoai)) {
                return true;
            }
        }
        return false;
    }
}


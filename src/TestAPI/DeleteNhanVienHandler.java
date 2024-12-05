package TestAPI;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.OutputStream;


public class DeleteNhanVienHandler implements HttpHandler {
    private final NhanVienBUS nvBUS = new NhanVienBUS();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
    if ("DELETE".equalsIgnoreCase(exchange.getRequestMethod())) {
        try {
            // Lấy tham số query từ URL
            String query = exchange.getRequestURI().getQuery();

            // Kiểm tra query có hợp lệ không
            if (query == null || !query.startsWith("id=")) {
                sendErrorResponse(exchange, 400, "Thiếu tham số ID nhân viên hoặc không đúng định dạng! (id=<value>)");
                return;
            }

            // Lấy giá trị ID từ query
            String idParam = query.substring(3).trim();
            if (idParam.isEmpty()) {
                sendErrorResponse(exchange, 400, "ID nhân viên không được để trống!");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                sendErrorResponse(exchange, 400, "ID nhân viên phải là một số nguyên hợp lệ!");
                return;
            }

            // Kiểm tra xem nhân viên có tồn tại không
            NhanVienDTO nhanVien = nvBUS.getObjectById(id);
            if (nhanVien == null) {
                sendErrorResponse(exchange, 404, "Không tìm thấy nhân viên với ID: " + id);
                return;
            }

            // Xóa nhân viên
            boolean isDeleted = nvBUS.delete(nhanVien);

            if (isDeleted) {
                String successResponse = "Cho nghỉ việc nhân viên với ID " + id + " thành công!";
                exchange.sendResponseHeaders(200, successResponse.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(successResponse.getBytes());
                }
            } else {
                sendErrorResponse(exchange, 500, "Xóa nhân viên thất bại. Vui lòng thử lại sau!");
            }

        } catch (Exception e) {
            sendErrorResponse(exchange, 500, "Lỗi xử lý: " + e.getMessage());
        }
    } else {
        // Phản hồi nếu không phải phương thức DELETE
        sendErrorResponse(exchange, 405, "Phương thức không được hỗ trợ. Vui lòng sử dụng DELETE.");
    }
}

    // Phương thức hỗ trợ gửi lỗi
    private void sendErrorResponse(HttpExchange exchange, int statusCode, String errorMessage) throws IOException {
        exchange.sendResponseHeaders(statusCode, errorMessage.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errorMessage.getBytes());
        }
    }
}
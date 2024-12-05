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
import java.util.List;

public class GetNhanVienHandler implements HttpHandler {
    private final NhanVienBUS nvBUS = new NhanVienBUS();

    @Override
//    public void handle(HttpExchange exchange) throws IOException {
//        if ("GET".equals(exchange.getRequestMethod())) {
//            try {
//                // Lấy tham số truy vấn từ URL
//                String query = exchange.getRequestURI().getQuery();
//                String ho = null, ten = null, gioiTinh = null, soDienThoai = null, email = null;
//                Integer id = null;  // Initialize id as null
//
//                // Phân tích các tham số truy vấn từ chuỗi query
//                if (query != null && !query.isEmpty()) {
//                    String[] queryParams = query.split("&");
//                    for (String param : queryParams) {
//                        String[] keyValue = param.split("=");
//                        if (keyValue.length == 2) {
//                            String key = keyValue[0];
//                            String value = keyValue[1].toLowerCase();
//
//                            switch (key) {
//                                case "id":
//                                    try {
//                                        id = Integer.parseInt(value); // Parse id value
//                                    } catch (NumberFormatException e) {
//                                        sendErrorResponse(exchange, 400, "ID không hợp lệ!");
//                                        return;
//                                    }
//                                    break;
//                                case "ho":
//                                    ho = value;
//                                    break;
//                                case "ten":
//                                    ten = value;
//                                    break;
//                                case "gioiTinh":
//                                    gioiTinh = value;
//                                    break;
//                                case "soDienThoai":
//                                    soDienThoai = value;
//                                    break;
//                                case "email":
//                                    email = value;
//                                    break;
//                                default:
//                                    break;
//                            }
//                        }
//                    }
//                }
//
//                // Lấy danh sách nhân viên từ cơ sở dữ liệu
//                ArrayList<NhanVienDTO> nhanVienList = nvBUS.getAll();
//
//                // Lọc theo các tham số nếu có
//                List<NhanVienDTO> filteredList;
//                if (id != null) {
//                    // If id is provided, filter by id
//                    filteredList = new ArrayList<>();
//                    for (NhanVienDTO nhanVien : nhanVienList) {
//                        if (nhanVien.getId() == id) {
//                            filteredList.add(nhanVien);
//                            break; // Assuming IDs are unique, no need to continue searching
//                        }
//                    }
//                } else {
//                    // If no id is provided, filter by other parameters
//                    filteredList = filterNhanVien(nhanVienList, ho, ten, gioiTinh, soDienThoai, email);
//                }
//
//                // Chuyển danh sách đã lọc thành JSON
//                JSONArray jsonArray = new JSONArray();
//                for (NhanVienDTO nhanVien : filteredList) {
//                    JSONObject jsonNhanVien = new JSONObject();
//                    jsonNhanVien.put("id", nhanVien.getId());
//                    jsonNhanVien.put("ho", nhanVien.getHo());
//                    jsonNhanVien.put("ten", nhanVien.getTen());
//                    jsonNhanVien.put("gioiTinh", nhanVien.getGioiTinh());
//                    jsonNhanVien.put("soDienThoai", nhanVien.getSoDienThoai());
//                    jsonNhanVien.put("email", nhanVien.getEmail());
//                    jsonArray.put(jsonNhanVien);
//                }
//
//                // Gửi phản hồi
//                String response = jsonArray.toString();
//                exchange.sendResponseHeaders(200, response.getBytes().length);
//                try (OutputStream os = exchange.getResponseBody()) {
//                    os.write(response.getBytes());
//                }
//            } catch (Exception e) {
//                sendErrorResponse(exchange, 500, "Lỗi lấy danh sách nhân viên: " + e.getMessage());
//            }
//        } else {
//            sendErrorResponse(exchange, 405, "Chỉ hỗ trợ phương thức GET!");
//        }
//    }
//
//    // Phương thức hỗ trợ lọc danh sách nhân viên
//    private List<NhanVienDTO> filterNhanVien(List<NhanVienDTO> nhanVienList, String ho, String ten, String gioiTinh, String soDienThoai, String email) {
//        List<NhanVienDTO> filteredList = new ArrayList<>();
//
//        for (NhanVienDTO nhanVien : nhanVienList) {
//            boolean match = true;
//
//            // Lọc theo các tham số nếu có
//            if (ho != null && !nhanVien.getHo().toLowerCase().contains(ho)) {
//                match = false;
//            }
//            if (ten != null && !nhanVien.getTen().toLowerCase().contains(ten)) {
//                match = false;
//            }
//            if (gioiTinh != null && !nhanVien.getGioiTinh().toLowerCase().equals(gioiTinh)) {
//                match = false;
//            }
//            if (soDienThoai != null && !nhanVien.getSoDienThoai().contains(soDienThoai)) {
//                match = false;
//            }
//            if (email != null && !nhanVien.getEmail().toLowerCase().contains(email)) {
//                match = false;
//            }
//
//            // Nếu nhân viên này khớp với tất cả các tham số, thêm vào danh sách kết quả
//            if (match) {
//                filteredList.add(nhanVien);
//            }
//        }
//
//        return filteredList;
//    }

    
    public void handle(HttpExchange exchange) throws IOException {
    if ("GET".equals(exchange.getRequestMethod())) {
        try {
            // Lấy tham số truy vấn từ URL
            String query = exchange.getRequestURI().getQuery();
            String ho = null, ten = null, gioiTinh = null, soDienThoai = null, email = null;
            Integer id = null;  // Initialize id as null

            // Phân tích các tham số truy vấn từ chuỗi query
            if (query != null && !query.isEmpty()) {
                String[] queryParams = query.split("&");
                for (String param : queryParams) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0];
                        String value = keyValue[1].toLowerCase();

                        switch (key) {
                            case "id":
                                try {
                                    id = Integer.parseInt(value); // Parse id value
                                } catch (NumberFormatException e) {
                                    sendErrorResponse(exchange, 400, "ID không hợp lệ!");
                                    return;
                                }
                                break;
                            case "ho":
                                ho = value;
                                break;
                            case "ten":
                                ten = value;
                                break;
                            case "gioiTinh":
                                gioiTinh = value;
                                break;
                            case "soDienThoai":
                                soDienThoai = value;
                                break;
                            case "email":
                                email = value;
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

            // Lấy danh sách nhân viên từ cơ sở dữ liệu
            ArrayList<NhanVienDTO> nhanVienList = nvBUS.getAll();

            // Nếu có id, lọc theo id
            List<NhanVienDTO> filteredList;
            if (id != null) {
                // If id is provided, filter by id
                filteredList = new ArrayList<>();
                for (NhanVienDTO nhanVien : nhanVienList) {
                    if (nhanVien.getId() == id) {
                        filteredList.add(nhanVien);
                        break; // Assuming IDs are unique, no need to continue searching
                    }
                }

                // Nếu không tìm thấy nhân viên với id này
                if (filteredList.isEmpty()) {
                    sendErrorResponse(exchange, 404, "Không tìm thấy nhân viên với ID " + id);
                    return;
                }
            } else {
                // Nếu không có id, lọc theo các tham số khác
                filteredList = filterNhanVien(nhanVienList, ho, ten, gioiTinh, soDienThoai, email);
            }

            // Nếu không tìm thấy nhân viên nào sau khi lọc
            if (filteredList.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder("Không tìm thấy nhân viên với các tham số sau: ");

                // Cập nhật thông báo lỗi cho từng tham số không khớp
                if (ho != null && ho.isEmpty()) {
                    errorMessage.append("Không tìm thấy họ: ").append(ho).append(" ");
                }
                if (ten != null && ten.isEmpty()) {
                    errorMessage.append("Không tìm thấy tên: ").append(ten).append(" ");
                }
                if (gioiTinh != null && gioiTinh.isEmpty()) {
                    errorMessage.append("Không tìm thấy giới tính: ").append(gioiTinh).append(" ");
                }
                if (soDienThoai != null && soDienThoai.isEmpty()) {
                    errorMessage.append("Không tìm thấy số điện thoại: ").append(soDienThoai).append(" ");
                }
                if (email != null && email.isEmpty()) {
                    errorMessage.append("Không tìm thấy email: ").append(email).append(" ");
                }

                sendErrorResponse(exchange, 404, errorMessage.toString().trim());
                return;
            }

            // Chuyển danh sách đã lọc thành JSON
            JSONArray jsonArray = new JSONArray();
            for (NhanVienDTO nhanVien : filteredList) {
                JSONObject jsonNhanVien = new JSONObject();
                jsonNhanVien.put("id", nhanVien.getId());
                jsonNhanVien.put("ho", nhanVien.getHo());
                jsonNhanVien.put("ten", nhanVien.getTen());
                jsonNhanVien.put("gioiTinh", nhanVien.getGioiTinh());
                jsonNhanVien.put("soDienThoai", nhanVien.getSoDienThoai());
                jsonNhanVien.put("email", nhanVien.getEmail());
                jsonArray.put(jsonNhanVien);
            }

            // Gửi phản hồi
            String response = jsonArray.toString();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        } catch (Exception e) {
            sendErrorResponse(exchange, 500, "Lỗi lấy danh sách nhân viên: " + e.getMessage());
        }
    } else {
        sendErrorResponse(exchange, 405, "Chỉ hỗ trợ phương thức GET!");
    }
}

// Phương thức hỗ trợ lọc danh sách nhân viên
private List<NhanVienDTO> filterNhanVien(List<NhanVienDTO> nhanVienList, String ho, String ten, String gioiTinh, String soDienThoai, String email) {
    List<NhanVienDTO> filteredList = new ArrayList<>();

    for (NhanVienDTO nhanVien : nhanVienList) {
        boolean match = true;

        // Lọc theo các tham số nếu có
        if (ho != null && !nhanVien.getHo().toLowerCase().contains(ho)) {
            match = false;
        }
        if (ten != null && !nhanVien.getTen().toLowerCase().contains(ten)) {
            match = false;
        }
        if (gioiTinh != null && !nhanVien.getGioiTinh().toLowerCase().equals(gioiTinh)) {
            match = false;
        }
        if (soDienThoai != null && !nhanVien.getSoDienThoai().contains(soDienThoai)) {
            match = false;
        }
        if (email != null && !nhanVien.getEmail().toLowerCase().contains(email)) {
            match = false;
        }

        // Nếu nhân viên này khớp với tất cả các tham số, thêm vào danh sách kết quả
        if (match) {
            filteredList.add(nhanVien);
        }
    }

    return filteredList;
}
    
    // Phương thức hỗ trợ gửi lỗi
    private void sendErrorResponse(HttpExchange exchange, int statusCode, String errorMessage) throws IOException {
        exchange.sendResponseHeaders(statusCode, errorMessage.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errorMessage.getBytes());
        }
    }
}

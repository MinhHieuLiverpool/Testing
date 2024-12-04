/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BUS;

import DTO.TaiKhoanDTO;
import DTO.TaiKhoanDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ACER
 */
public class TaiKhoanBUSTest {
    
  
    @Test
    public void SuaTaiKhoanThanhCong() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"doanminhduc","123",1);
        boolean result = bus.update(tk);
        boolean exp = true;
        System.out.println("Sửa tài khoản thành công");
        System.out.println("Dự kiến (exp): " + exp + " || Kết quả (result): " + result+ "\n");
        assertEquals(result,exp);
    }
    
    @Test
    public void TaiKhoandeTrong() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"","123",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        System.out.println("Tên tài khoản không được để trống");
        System.out.println("Dự kiến (exp): " + exp + " || Kết quả (result): " + result+ "\n");
        assertEquals(result,exp);
    }
    @Test
    public void MatKhaudeTrong() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"doanminhduc","",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        System.out.println("Mật khẩu không được để trống");
        System.out.println("Dự kiến (exp): " + exp + " || Kết quả (result): " + result+ "\n");
        assertEquals(result,exp);
    }
    
    @Test
    public void TaiKhoanchua25kytu() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"ducemducemducemducemducemducem","123",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        System.out.println("tên tài khoản chứa tối đa 25 ký tự");
        System.out.println("Dự kiến (exp): " + exp + " || Kết quả (result): " + result+ "\n");
        assertEquals(result,exp);
    }
    
    @Test
    public void Matkhauchua25kytu() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"ducem","123451234512345123451234512345",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        System.out.println("Mật khẩu chứa tối đa 25 ký tự");
        System.out.println("Dự kiến (exp): " + exp + " || Kết quả (result): " + result+ "\n");
        assertEquals(result,exp);
    }
    
    @Test
    public void TaiKhoanTrungvoihethong() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"ducem","12345",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        System.out.println("Tên tài khoản đã tồn tại trên hệ thống");
        System.out.println("Dự kiến (exp): " + exp + " || Kết quả (result): " + result+ "\n");
        assertEquals(result,exp);
    }
    
//    @Test
//    public void ThayDoiQuyenthanhQuanli() {
//        TaiKhoanBUS bus = new TaiKhoanBUS();
//        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,1,"ducem","12345",1);
//        boolean result = bus.update(tk);
//        boolean exp = false;
//        System.out.println("Dự kiến (exp): " + exp + " || Kết quả (result): " + result+ "\n");
//        assertEquals(result,exp);
//    }
//    
  
}

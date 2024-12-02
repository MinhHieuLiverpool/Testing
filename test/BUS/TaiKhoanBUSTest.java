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
        assertEquals(result,exp);
    }
    
    @Test
    public void TaiKhoandeTrong() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"","123",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        assertEquals(result,exp);
    }
    @Test
    public void MatKhaudeTrong() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"doanminhduc","",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        assertEquals(result,exp);
    }
    
    @Test
    public void TaiKhoanchua25kytu() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"ducemducemducemducemducemducem","123",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        assertEquals(result,exp);
    }
    
    @Test
    public void Matkhauchua25kytu() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"ducem","123451234512345123451234512345",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        assertEquals(result,exp);
    }
    
    @Test
    public void TaiKhoanTrungvoihethong() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,3,"ducem","12345",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        assertEquals(result,exp);
    }
    
    @Test
    public void ThayDoiQuyenthanhQuanli() {
        TaiKhoanBUS bus = new TaiKhoanBUS();
        TaiKhoanDTO tk = new TaiKhoanDTO(4,4,1,"ducem","12345",1);
        boolean result = bus.update(tk);
        boolean exp = false;
        assertEquals(result,exp);
    }
    
}

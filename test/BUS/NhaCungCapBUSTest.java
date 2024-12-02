/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BUS;

import DTO.NhaCungCapDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ACER
 */
public class NhaCungCapBUSTest {
    
    @Test
    public void testTatCaDeuRong() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"", "", "","",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    @Test
    public void testTenNhaCungCapRong() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"", "102 Hoa Hung", "0123456789","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    @Test
    public void testDiaChiNhaCungCapRong() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Bui Quang Minh Hieu", "", "0123456789","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    @Test
    public void testSoDienThoaiRong() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Bui Quang Minh Hieu", "102 Hoa Hung", "","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    @Test
    public void testEmailRong() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Bui Quang Minh Hieu", "102 Hoa Hung", "0123456789","",1);
        boolean result = bus.add(kh);
       boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    @Test
    public void testSoDienThoaiChiCo11so() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Bui Quang Minh Hieu", "102 Hoa Hung", "01234567890","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    public void testSoDienThoaiChiCo9So() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Bui Quang Minh Hieu", "102 Hoa Hung", "012345678","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    @Test
     public void testSoDienThoaiBatDauKhonglaSo0() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Bui Quang Minh Hieu", "102 Hoa Hung", "1234567890","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
     @Test
    public void testEmailKhongDungDinhDang() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Bui Quang Minh Hieu", "102 Hoa Hung", "0123456789","123456",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    @Test
    public void ThemNhaCungCapThanhCong() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Bui Quang Minh Hieu", "102 Hoa Hung", "0123456789","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = true;
        assertEquals(result,exp);
        
    }
    
    @Test
    public void testTenNhaCungCapChua2KyTu() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Hi", "102 Hoa Hung", "0123456789","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    @Test
    public void testTenNhaCungCapChua55KyTu() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"ssssssssssssssssssssssssssssssssssssssssssssssssssaaaaa", "102 Hoa Hung", "0123456789","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    @Test
    public void testDiaChiNhaCungCapChua4KyTu() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Bui Quang Minh Hieu", "Hiii", "0123456789","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    @Test
    public void testDiaChiNhaCungCapChua55KyTu() {
        NhaCungCapBUS bus = new NhaCungCapBUS();
            NhaCungCapDTO kh = new NhaCungCapDTO(100,"Hi", "ssssssssssssssssssssssssssssssssssssssssssssssssssaaaaa", "0123456789","123456@gmail.com",1);
        boolean result = bus.add(kh);
        boolean exp = false;
        assertEquals(result,exp);
        
    }
    
    
}

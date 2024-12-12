/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BUS;

import DTO.PhienBanSanPhamDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ACER
 */
public class PhienBanSanPhamBUSTest {
    
 
    @Test
    public void ThemThanhCong() {
        PhienBanSanPhamBUS pbsbBUS = new PhienBanSanPhamBUS();
        PhienBanSanPhamDTO pbsb = new PhienBanSanPhamDTO(1, 3 , 8 , 256, " Titan tự nhiên 19 ", 3, 1 );
        boolean expected = true;
        boolean result = pbsbBUS.addNewPBSP(pbsb);
          System.out.println("Dự kiến (expected): " + expected + " || Kết quả (result): " + result+ "\n");
        assertEquals(expected,result);
        
    
    }

    
    @Test
    public void Ramlasoam() {
        PhienBanSanPhamBUS pbsbBUS = new PhienBanSanPhamBUS();
        PhienBanSanPhamDTO pbsb = new PhienBanSanPhamDTO(1, 3 , -8 , 256, " Titan tự nhiên 12 ", 3, 1 );
        boolean expected = false;
        boolean result = pbsbBUS.addNewPBSP(pbsb);
          System.out.println("Dự kiến (expected): " + expected + " || Kết quả (result): " + result+ "\n");
        assertEquals(expected,result);
        
    }
    
     @Test
    public void Romlasoam() {
        PhienBanSanPhamBUS pbsbBUS = new PhienBanSanPhamBUS();
        PhienBanSanPhamDTO pbsb = new PhienBanSanPhamDTO(1, 3 , 8 , -256, " Titan tự nhiên 12 ", 3, 1 );
        boolean expected = false;
        boolean result = pbsbBUS.addNewPBSP(pbsb);
          System.out.println("Dự kiến (expected): " + expected + " || Kết quả (result): " + result+ "\n");
        assertEquals(expected,result);
        
    
    }
    
    @Test
    public void MauSacDeTrong() {
        PhienBanSanPhamBUS pbsbBUS = new PhienBanSanPhamBUS();
        PhienBanSanPhamDTO pbsb = new PhienBanSanPhamDTO(1, 3 , 8 , 256, " ", 3, 1 );
        boolean expected = false;
        boolean result = pbsbBUS.addNewPBSP(pbsb);
          System.out.println("Dự kiến (expected): " + expected + " || Kết quả (result): " + result+ "\n");
        assertEquals(expected,result);
        
    
    }
    
    @Test
    public void PhienBanSanPhamTonTai() {
        PhienBanSanPhamBUS pbsbBUS = new PhienBanSanPhamBUS();
        PhienBanSanPhamDTO pbsb = new PhienBanSanPhamDTO(1, 3 , 8 , 256, " Titan tự nhiên  ", 2, 1 );
        boolean expected = false;
        boolean result = pbsbBUS.addNewPBSP(pbsb);
          System.out.println("Dự kiến (expected): " + expected + " || Kết quả (result): " + result+ "\n");
        assertEquals(expected,result);
        
    
    }
  
}

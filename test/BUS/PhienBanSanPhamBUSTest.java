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
        PhienBanSanPhamBUS pbspBUS = new PhienBanSanPhamBUS();
        int size = pbspBUS.getAll().size();
        PhienBanSanPhamDTO pbsp = new PhienBanSanPhamDTO(1, 3 , 8 , 256, " Titan tự nhiên 78 ", 3, 1 );
        assertTrue(pbspBUS.addNewPBSP(pbsp));
        int result = pbspBUS.getAll().size();
        int exp = size+1;
        System.out.println("Dự kiến (expected): " + exp + " || Kết quả (result): " + result + "\n");
        
    
    }

    
    @Test
    public void Ramlasoam() {
        
        PhienBanSanPhamBUS pbspBUS = new PhienBanSanPhamBUS();
        int size = pbspBUS.getAll().size();
        PhienBanSanPhamDTO pbsp = new PhienBanSanPhamDTO(1, 3 , -8 , 256, " Titan tự nhiên 12 ", 3, 1 );
        assertFalse(pbspBUS.addNewPBSP(pbsp));
        int result = pbspBUS.getAll().size();
        int exp = size;
        System.out.println("Dự kiến (expected): " + exp + " || Kết quả (result): " + result + "\n");
        
    }
    
     @Test
    public void Romlasoam() {
        
 
         PhienBanSanPhamBUS pbspBUS = new PhienBanSanPhamBUS();
        int size = pbspBUS.getAll().size();
        PhienBanSanPhamDTO pbsp = new PhienBanSanPhamDTO(1, 3 , 8 , -256, " Titan tự nhiên 12 ", 3, 1 );
        assertFalse(pbspBUS.addNewPBSP(pbsp));
        int result = pbspBUS.getAll().size();
        int exp = size;
        System.out.println("Dự kiến (expected): " + exp + " || Kết quả (result): " + result + "\n");
    }
    
    @Test
    public void MauSacDeTrong() {
        
        PhienBanSanPhamBUS pbspBUS = new PhienBanSanPhamBUS();
        int size = pbspBUS.getAll().size();
        PhienBanSanPhamDTO pbsp = new PhienBanSanPhamDTO(1, 3 , 8 , 256, " ", 3, 1 );
        assertFalse(pbspBUS.addNewPBSP(pbsp));
        int result = pbspBUS.getAll().size();
        int exp = size;
        System.out.println("Dự kiến (expected): " + exp + " || Kết quả (result): " + result + "\n");
    
    }
    
    @Test
    public void PhienBanSanPhamTonTai() {
        
        PhienBanSanPhamBUS pbspBUS = new PhienBanSanPhamBUS();
        int size = pbspBUS.getAll().size();
        PhienBanSanPhamDTO pbsp = new PhienBanSanPhamDTO(1, 3 , 8 , 256, " Titan tự nhiên  ", 2, 1 );
        assertFalse(pbspBUS.addNewPBSP(pbsp));
        int result = pbspBUS.getAll().size();
        int exp = size;
        System.out.println("Dự kiến (expected): " + exp + " || Kết quả (result): " + result + "\n");
    
    }
  
}

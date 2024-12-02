/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ACER
 */
public class NhanVienDAOTest {
    
   @Test
    public void testNhanVien() {
        NhanVienDAO dao = new NhanVienDAO();
        NhanVienDTO kh = new NhanVienDTO(100,"Nguyen", "Hieu", "Nam","0933476706","thanhhieu16082004@gmail.com",1);
        int result = dao.insert(kh);
        int exp = 1;
        assertEquals(result,exp);
    }
    
}

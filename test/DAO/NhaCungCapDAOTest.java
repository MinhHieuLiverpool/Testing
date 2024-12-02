/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ACER
 */
public class NhaCungCapDAOTest {
    
    @Test
    public void testThanhCong() {
        NhaCungCapDAO dao = new NhaCungCapDAO();
        NhaCungCapDTO kh = new NhaCungCapDTO(100,"Nguyen", "102 Hoa Hung", "0123456789","123456",1);
        int result = dao.insert(kh);
        int exp = 1;
        assertEquals(result,exp);
    }
    @Test
    public void testTenlaKhoangTrang() {
        NhaCungCapDAO dao = new NhaCungCapDAO();
        NhaCungCapDTO kh = new NhaCungCapDTO(101," ", "102 Hoa Hung", "0933476706","123456@gmail.com",1);
        int result = dao.insert(kh);
        int exp = 0;
        assertEquals(result,exp);
    }
    @Test
    public void testKhongNhap() {
        NhaCungCapDAO dao = new NhaCungCapDAO();
        NhaCungCapDTO kh = new NhaCungCapDTO(202,"", "", "","",1);
        int result = dao.insert(kh);
        int exp = 0;
        assertEquals(result,exp);
    }
    @Test
    public void testsodienthoaiTrang() {
        NhaCungCapDAO dao = new NhaCungCapDAO();
        NhaCungCapDTO kh = new NhaCungCapDTO(304,"Minh Hieu", "102 Hoa Hung", " ","123525@gmail.com",1);
        int result = dao.insert(kh);
        int exp = 0;
        assertEquals(result,exp);
    }
}

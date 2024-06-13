package com.example.asm_java5.repository;

import com.example.asm_java5.entity.HoaDonChiTiet;
import com.example.asm_java5.model.response.HoaDonChiTietCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query(value = "select * FROM HoaDonChiTiet hdct where hdct.IdHoaDon =:id", nativeQuery = true)
    ArrayList<HoaDonChiTiet> getHDCTById(@Param("id") UUID id);

    @Query(value = "select * FROM HoaDonChiTiet hdct where hdct.IdHoaDon =:id AND hdct.IdChiTietSP =:id2", nativeQuery = true)
    HoaDonChiTiet getHoaDonChiTietByIdHDAndIdSPCT(@Param("id") UUID id, @Param("id2") UUID id2);

    @Query(value = """
           SELECT new com.example.asm_java5.model.response.HoaDonChiTietCustom
           (hdct.IdHoaDon, sp.Ma, hdct.SoLuong, sp.Ten, ms.Ten, n.Ten, ds.Ten, cts.GiaBan)
           from HoaDonChiTiet hdct
           join HoaDon hd ON hdct.IdHoaDon = hd.Id
           join ChiTietSP cts on hdct.IdChiTietSP = cts.Id
           join SanPham sp on cts.IdSP = sp.Id 
           join DongSP ds ON ds.Id = cts.IdDongSP
           join NSX n ON n.Id = cts.IdNsx
           join MauSac ms ON cts.IdMauSac = ms.Id
           where hd.Id=:idHD
           """, nativeQuery = true)
    ArrayList<HoaDonChiTietCustom> getListHDCTByIdHD(@Param("idHD") UUID idHD);

//    @Query("SELECT hd FROM HoaDonChiTiet hd WHERE hd.id.hdId=:idHD")
//    ArrayList<HoaDonChiTiet> getListHDCTByIdHD(@Param("idHD")UUID idHD);

}

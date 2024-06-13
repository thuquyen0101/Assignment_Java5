package com.example.asm_java5.repository;

import com.example.asm_java5.entity.ChiTietSanPham;
import com.example.asm_java5.entity.ChiTietSanPhamCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {

//    @Query(value = "SELECT cts.Id , sp.Ma , sp.Ten, " +
//            "ms.Ten, ds.Ten, n.Ten, cts.SoLuongTon,cts.GiaNhap , cts.GiaBan " +
//            "FROM MauSac ms JOIN ChiTietSP cts ON ms.Id = cts.IdMauSac " +
//            "join SanPham sp on cts.IdSP = sp.Id " +
//            "join DongSP ds on ds.Id = cts.IdDongSP " +
//            "join NSX n on n.Id = cts.IdNsx " +
//            "where cts.IdSP =:idSP", nativeQuery = true)
//    List<ChiTietSanPhamCustom> findByIdSP(@Param("idSP") UUID idSP);

//    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.sanPham in " +
//            "(select sp from  SanPham sp where sp.id=:id) ")
//    List<ChiTietSanPham> findByIdSP(@Param("id") UUID idSP);

    @Query("SELECT spct FROM ChiTietSanPham spct where spct.id=:id")
    ChiTietSanPham getOneSpct(@Param("id") UUID idSP);
}

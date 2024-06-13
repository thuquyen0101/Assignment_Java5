package com.example.asm_java5.repository;

import com.example.asm_java5.entity.KhachHang;
import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {

    @Query("SELECT ms FROM KhachHang ms WHERE ms.id=:id2")
    KhachHang getOne(@Param("id2") UUID uuid);

    @Query("SELECT ms FROM KhachHang ms WHERE ms.ma like :keyword OR ms.ten like :keyword")
    List<KhachHang> search(@Param("keyword") String keyword);

//    @Query("SELECT ms FROM KhachHang ms WHERE ms.sdt=:sdt")
//    KhachHang getKhachHangBySdt(@Param("sdt") String sdt);

    KhachHang getKhachHangBySdt(String sdt);

    KhachHang getKhachHangByMaAndMatKhau(String ma, String pass);

    Boolean existsKhachHangByMaAndMatKhau(String ma, String pass);

    Optional<KhachHang> getByMa(String ma);

}

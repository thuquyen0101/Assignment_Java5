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
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    @Query("SELECT ms FROM NhanVien ms WHERE ms.id=:id2")
    NhanVien getOne(@Param("id2") UUID uuid);

    @Query("SELECT ms FROM NhanVien ms WHERE ms.ma like :keyword OR ms.ten like :keyword")
    List<NhanVien> search(@Param("keyword") String keyword);

    NhanVien getNhanVienByMaAndMatKhau(String ma, String pass);

    Boolean existsNhanVienByMaAndMatKhau(String ma, String pass);

    Optional<NhanVien> getByMa(String ma);
}

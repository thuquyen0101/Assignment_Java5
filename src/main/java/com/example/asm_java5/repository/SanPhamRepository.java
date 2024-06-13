package com.example.asm_java5.repository;

import com.example.asm_java5.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SanPhamRepository  extends JpaRepository<SanPham, UUID> {

    @Query("SELECT ms FROM SanPham ms WHERE ms.id=:id2")
    SanPham getOne(@Param("id2") UUID uuid);

    @Query("SELECT ms FROM SanPham ms WHERE ms.ma like :keyword OR ms.ten like :keyword")
    List<SanPham> search(@Param("keyword") String keyword);
}

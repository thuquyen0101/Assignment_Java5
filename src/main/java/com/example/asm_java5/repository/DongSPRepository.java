package com.example.asm_java5.repository;

import com.example.asm_java5.entity.DongSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DongSPRepository extends JpaRepository<DongSanPham, UUID> {
    @Query("SELECT ms FROM DongSanPham ms WHERE ms.id=:id2")
    DongSanPham getOne(@Param("id2") UUID uuid);

    @Query("SELECT ms FROM DongSanPham ms WHERE ms.ma like :keyword OR ms.ten like :keyword")
    List<DongSanPham> search(@Param("keyword") String keyword);
}

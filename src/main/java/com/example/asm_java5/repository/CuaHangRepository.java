package com.example.asm_java5.repository;

import com.example.asm_java5.entity.CuaHang;
import com.example.asm_java5.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CuaHangRepository extends JpaRepository<CuaHang, UUID> {

    @Query("SELECT ms FROM CuaHang ms WHERE ms.id=:id2")
    CuaHang getOne(@Param("id2") UUID uuid);

    @Query("SELECT ms FROM CuaHang ms WHERE ms.ma like :keyword OR ms.ten like :keyword")
    List<CuaHang> search(@Param("keyword") String keyword);
}

package com.example.asm_java5.repository;

import com.example.asm_java5.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query("SELECT hd FROM HoaDon hd WHERE hd.tinhTrang = 0")
    List<HoaDon> getAllHoaDon();

    HoaDon getById(UUID id);


    Page<HoaDon> getHoaDonsByIdIsNotNull(Pageable pageable);

}

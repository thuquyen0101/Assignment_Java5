package com.example.asm_java5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietSanPhamCustom {
    private UUID idHD;
    private String maSP;
    private String tenSP;
    private String tenMau;
    private String tenNSX;
    private String tenDongSP;
    private Integer soLuong;
    private Double donGia;
    private Double giaBan;
}

package com.example.asm_java5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChiTietSP")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;
    @Column(name = "NamBH")
    @NotNull(message = "Nam bao hanh khong duoc trong")
    private Integer namBH;
    @Column(name = "MoTa")
    @NotBlank(message = "Mo ta khong duoc trong")
    private String moTa;
    @Column(name = "SoLuongTon")
    @NotNull(message = "So Luong Ton khong duoc trong")
    private Integer soLuongTon;
    @Column(name = "GiaNhap")
    @NotNull(message = "Gia nhap khong duoc trong")
    private BigDecimal giaNhap;
    @Column(name = "GiaBan")
    @NotNull(message = "Gia ban khong duoc trong")
    private Double giaBan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdSP")
    private SanPham sanPham;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNsx")
    private NhaSanXuat nhaSanXuat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDongSP")
    private DongSanPham dongSanPham;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
//            mappedBy = "ChiTietSP")
//    private List<HoaDon> HoaDon;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ChiTietSP", cascade = CascadeType.ALL)
    private List<GioHang> GioHang;
}

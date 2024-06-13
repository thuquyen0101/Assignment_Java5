package com.example.asm_java5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HoaDonChiTiet")
@Entity
public class HoaDonChiTiet {
    @EmbeddedId
    private HoaDonCTId id;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Column(name = "DonGia")
    private Double donGia;

    public Double getThanhTien() {
        Double tongTien;
        tongTien = donGia * soLuong;
        return tongTien;
    }

    @ManyToOne
    @MapsId("hdId")
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;

    @ManyToOne
    @MapsId("spctId")
    @JoinColumn(name = "IdChiTietSP")
    private ChiTietSanPham chiTietSanPham;
}

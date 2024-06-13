package com.example.asm_java5.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTietCustom {
    private UUID idHD;
    private String maSP;
    private String tenSP;
    private Integer soLuong;
    private String tenMau;
    private String tenNSX;
    private String tenDongSP;
    private Double giaBan;

}

package com.example.asm_java5.service;

import com.example.asm_java5.entity.HoaDonChiTiet;
import com.example.asm_java5.model.response.HoaDonChiTietCustom;
import com.example.asm_java5.repository.HoaDonChiTietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HoaDonChiTietService {
    private final HoaDonChiTietRepository hdctRepo;

    public ArrayList<HoaDonChiTiet> getHoaDonChiTietByIdHd(UUID uuid) {
        return hdctRepo.getHDCTById(uuid);
    }

    public void save(HoaDonChiTiet hoaDonChiTiet){
        hdctRepo.save(hoaDonChiTiet);
    }

    public HoaDonChiTiet getHoaDonChiTietByIdHDAndIdSPCT(UUID IdHD, UUID IdSPCT) {
        return hdctRepo.getHoaDonChiTietByIdHDAndIdSPCT(IdHD, IdSPCT);
    }
    public void delete(HoaDonChiTiet hoaDonChiTiet){
        hdctRepo.delete(hoaDonChiTiet);
    }

//    public ArrayList<HoaDonChiTiet> getListHDCTByIdHD(UUID idHD){
//        return hdctRepo.getListHDCTByIdHD(idHD);
//    }

    public ArrayList<HoaDonChiTietCustom> getListHDCTByIdHD(UUID idHD){
        return hdctRepo.getListHDCTByIdHD(idHD);
    }

}

package com.example.asm_java5.service;

import com.example.asm_java5.entity.HoaDon;
import com.example.asm_java5.repository.HoaDonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HoaDonService {
    private final HoaDonRepository repository;

    public static String generateInvoiceCode() {
        String prefix = "HD";
        Random random = new Random();
        int randomNumbers = random.nextInt(500) + 1; // Sinh ra số ngẫu nhiên từ 1 đến 99
        return prefix + randomNumbers;
    }

    public void createHoaDon(HoaDon hoaDon) {
        hoaDon.setMa(generateInvoiceCode());
        hoaDon.setNgayTao(new Date());
        hoaDon.setTinhTrang(0);
        repository.save(hoaDon);
    }

    public List<HoaDon> getAllHoaDon() {
        return repository.getAllHoaDon();
    }

    public Page<HoaDon> getAll(Pageable pageable) {
        return repository.getHoaDonsByIdIsNotNull(pageable);
    }

    public HoaDon getHDById(UUID id) {
        return repository.getById(id);
    }

    public void save(HoaDon hoaDon) {
        repository.save(hoaDon);
    }
}

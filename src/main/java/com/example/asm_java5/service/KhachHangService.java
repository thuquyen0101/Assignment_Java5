package com.example.asm_java5.service;

import com.example.asm_java5.entity.KhachHang;
import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangRepository repository;

    public ArrayList<KhachHang> getAll() {
        return (ArrayList<KhachHang>) repository.findAll();
    }

    public ArrayList<KhachHang> search(String keyword) {
        return (ArrayList<KhachHang>) repository.search(keyword);
    }

    public KhachHang getOne(UUID id) {
        KhachHang ms = repository.getOne(id);
        return ms;
    }

    public Page<KhachHang> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void add(KhachHang mauSac) {
        repository.save(mauSac);
    }

    public void remove(UUID id) {
        repository.deleteById(id);
    }

    public KhachHang searchSdt(String sdt) {
        return repository.getKhachHangBySdt(sdt);
    }

    public void save(KhachHang kh) {
        kh.setTen("Khách lẻ");
        repository.save(kh);
    }

    public static String generateInvoiceCode() {
        String prefix = "KH";
        Random random = new Random();
        int randomNumbers = random.nextInt(200) + 10; // Sinh ra số ngẫu nhiên từ 10 đến 200
        return prefix + randomNumbers;
    }

    public void create(KhachHang kh) {
        kh.setTen("Khách lẻ");
        kh.setMa(generateInvoiceCode());
        repository.save(kh);
    }

    public KhachHang getKhachHangByMaAndMatKhau(String ma, String pass) {
        return repository.getKhachHangByMaAndMatKhau(ma, pass);
    }

    public Boolean  existsKhachHangByMaAndMatKhau(String ma, String pass) {
        return repository.existsKhachHangByMaAndMatKhau(ma, pass);
    }

}

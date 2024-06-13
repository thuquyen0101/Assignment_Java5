package com.example.asm_java5.service;

import com.example.asm_java5.entity.ChiTietSanPham;
import com.example.asm_java5.repository.ChiTietSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChiTietSanPhamSevice {
    @Autowired
    private ChiTietSanPhamRepository repository;

    public Page<ChiTietSanPham> getAllCtsp(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public void saveCtsp(ChiTietSanPham chiTietSanPham) {
        repository.save(chiTietSanPham);
    }

    public void remove(UUID id) {
        repository.deleteById(id);
    }

    public ChiTietSanPham getOne(UUID uuid) {
        return repository.getOne(uuid);
    }


//    public List<ChiTietSanPham> test(UUID id) {
//        return repository.findByIdSP(id);
//    }

//    public static void main(String[] args) {
//        ChiTietSanPhamSevice sanPhamSevice = new ChiTietSanPhamSevice();
//        for (ChiTietSanPham x : sanPhamSevice.test(UUID.fromString("5B6E6315-B2EC-43E7-A87F-1B444C5182BF"))) {
//            System.out.println(x);
//        }
//    }


}

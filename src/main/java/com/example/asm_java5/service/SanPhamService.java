package com.example.asm_java5.service;

import com.example.asm_java5.entity.SanPham;
import com.example.asm_java5.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class SanPhamService {
    @Autowired
    private SanPhamRepository repository;

    public ArrayList<SanPham> getAll() {
        return (ArrayList<SanPham>) repository.findAll();
    }

    public void add(SanPham sanPham){
        repository.save(sanPham);
    }

    public SanPham getOne(UUID id) {
        SanPham sanPham = repository.getOne(id);
        return sanPham;
    }

    public void remove(UUID id) {
        repository.deleteById(id);
    }

    public ArrayList<SanPham> search(String keyword) {
        return (ArrayList<SanPham>) repository.search(keyword);
    }

    public Page<SanPham> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}

package com.example.asm_java5.service;

import com.example.asm_java5.entity.DongSanPham;
import com.example.asm_java5.repository.DongSPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class DongSpService {
    @Autowired
    private DongSPRepository repository;

    public ArrayList<DongSanPham> getAll() {
        return (ArrayList<DongSanPham>) repository.findAll();
    }

   public void add(DongSanPham dongSanPham){
        repository.save(dongSanPham);
   }

    public DongSanPham getOne(UUID id) {
        DongSanPham dongSanPham = repository.getOne(id);
        return dongSanPham;
    }

    public void remove(UUID id) {
        repository.deleteById(id);
    }

    public ArrayList<DongSanPham> search(String keyword) {
        return (ArrayList<DongSanPham>) repository.search(keyword);
    }

    public Page<DongSanPham> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}

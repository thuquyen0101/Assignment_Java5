package com.example.asm_java5.service;

import com.example.asm_java5.entity.ChucVu;
import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.repository.ChucVuRepository;
import com.example.asm_java5.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ChucVuService {
    @Autowired
    private ChucVuRepository repository;

    public ArrayList<ChucVu> getAll() {
        return (ArrayList<ChucVu>) repository.findAll();
    }

    public void add(ChucVu mauSac) {
        repository.save(mauSac);
    }

    public ChucVu getOne(UUID id) {
        ChucVu ms = repository.getOne(id);
        return ms;
    }

    public void remove(UUID id) {
        repository.deleteById(id);
    }

    public ArrayList<ChucVu> search(String keyword) {
        return (ArrayList<ChucVu>) repository.search(keyword);
    }

    public Page<ChucVu> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}

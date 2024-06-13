package com.example.asm_java5.service;

import com.example.asm_java5.entity.CuaHang;
import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.repository.CuaHangRepository;
import com.example.asm_java5.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CuaHangService {
    @Autowired
    private CuaHangRepository repository;

    public ArrayList<CuaHang> getAll() {
        return (ArrayList<CuaHang>) repository.findAll();
    }

    public void add(CuaHang mauSac) {
        repository.save(mauSac);
    }

    public CuaHang getOne(UUID id) {
        CuaHang ms = repository.getOne(id);
        return ms;
    }

    public void remove(UUID id) {
        repository.deleteById(id);
    }

    public ArrayList<CuaHang> search(String keyword) {
        return (ArrayList<CuaHang>) repository.search(keyword);
    }

    public Page<CuaHang> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}

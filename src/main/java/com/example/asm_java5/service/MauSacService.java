package com.example.asm_java5.service;

import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class MauSacService {
    @Autowired
    private MauSacRepository repository;

    public ArrayList<MauSac> getAll() {
        return (ArrayList<MauSac>) repository.findAll();
    }

    public void add(MauSac mauSac) {
        repository.save(mauSac);
    }

    public MauSac getOne(UUID id) {
        MauSac ms = repository.getOne(id);
        return ms;
    }

    public void remove(UUID id) {
        repository.deleteById(id);
    }

    public ArrayList<MauSac> search(String keyword) {
        return (ArrayList<MauSac>) repository.search(keyword);
    }

    public Page<MauSac> getAll(Pageable  pageable){
        return repository.findAll(pageable);
    }
}

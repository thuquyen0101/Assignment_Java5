package com.example.asm_java5.service;

import com.example.asm_java5.entity.NhaSanXuat;
import com.example.asm_java5.repository.NhaSanXuatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service

public class NhaSanXuatService {
    @Autowired
    private NhaSanXuatRepository repository;

    public ArrayList<NhaSanXuat> getAll() {
        return (ArrayList<NhaSanXuat>) repository.findAll();
    }

    public void add(NhaSanXuat mauSac) {
        repository.save(mauSac);
    }

    public NhaSanXuat getOne(UUID id) {
        NhaSanXuat ms = repository.getOne(id);
        return ms;
    }

    public void remove(UUID id) {
        repository.deleteById(id);
    }

    public ArrayList<NhaSanXuat> search(String keyword) {
        return (ArrayList<NhaSanXuat>) repository.search(keyword);
    }

    public Page<NhaSanXuat> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}

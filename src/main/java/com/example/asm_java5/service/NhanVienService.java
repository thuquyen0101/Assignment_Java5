package com.example.asm_java5.service;

import com.example.asm_java5.entity.KhachHang;
import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.entity.NhanVien;
import com.example.asm_java5.repository.MauSacRepository;
import com.example.asm_java5.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository repository;

    public ArrayList<NhanVien> getAll() {
        return (ArrayList<NhanVien>) repository.findAll();
    }

    public void add(NhanVien mauSac) {
        repository.save(mauSac);
    }

    public NhanVien getOne(UUID id) {
        NhanVien ms = repository.getOne(id);
        return ms;
    }

    public void remove(UUID id) {
        repository.deleteById(id);
    }

    public ArrayList<NhanVien> search(String keyword) {
        return (ArrayList<NhanVien>) repository.search(keyword);
    }

    public Page<NhanVien> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public NhanVien getNhanVienByMaAndMatKhau(String ma, String pass) {
        return repository.getNhanVienByMaAndMatKhau(ma, pass);
    }

    public Boolean existsNhanVienByMaAndMatKhau(String ma, String pass) {
        return repository.existsNhanVienByMaAndMatKhau(ma, pass);
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = "123";
        String encoder = passwordEncoder.encode(pass);
        System.out.println(encoder);
    }


}

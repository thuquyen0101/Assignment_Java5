package com.example.asm_java5.service;

import com.example.asm_java5.entity.KhachHang;
import com.example.asm_java5.entity.NhanVien;
import com.example.asm_java5.repository.KhachHangRepository;
import com.example.asm_java5.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements UserDetailsService {
    private final NhanVienRepository nhanVienRepository;
    private final KhachHangRepository khRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<NhanVien> nv = nhanVienRepository.getByMa(username);
        Optional<KhachHang> kh = khRepo.getByMa(username);

        if (nv.isPresent()) {
            return User.withUsername(nv.get().getMa()).password(nv.get().getMatKhau()).roles("ADMIN").build();
        } else if (kh.isPresent()) {
            log.info("kh {}", kh);
            return User.withUsername(kh.get().getMa()).password(kh.get().getMatKhau()).roles("USER").build();
        } else {
            try {
                throw new Exception("USER NOT FOUND");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


}

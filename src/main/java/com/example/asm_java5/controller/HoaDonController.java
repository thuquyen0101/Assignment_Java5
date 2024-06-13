package com.example.asm_java5.controller;

import com.example.asm_java5.entity.HoaDon;
import com.example.asm_java5.entity.NhaSanXuat;
import com.example.asm_java5.service.HoaDonChiTietService;
import com.example.asm_java5.service.HoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/hoa-don")
@RequiredArgsConstructor
public class HoaDonController {
    private final HoaDonService service;
    private final HoaDonChiTietService hdctService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                          @RequestParam(defaultValue = "5", required = false) Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<HoaDon> list = service.getAll(pageable);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("listHD", list.getContent());
        return "/hoa-don/index";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam UUID idHD, Model model) {
        model.addAttribute("listHDCT", hdctService.getListHDCTByIdHD(idHD));
        return "/hoa-don/detail";
    }
}

package com.example.asm_java5.controller;

import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.entity.NhanVien;
import com.example.asm_java5.service.ChucVuService;
import com.example.asm_java5.service.CuaHangService;
import com.example.asm_java5.service.MauSacService;
import com.example.asm_java5.service.NhanVienService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/nhan-vien")
@RequiredArgsConstructor
public class NhanVienController {
    private final NhanVienService service;
    private final ChucVuService cvService;
    private final CuaHangService chService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                          @RequestParam(defaultValue = "5", required = false) Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<NhanVien> list = service.getAll(pageable);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("list", list.getContent());
        return "/nhan-vien/index";
    }

    @GetMapping("/form-add")
    public String formAdd(@ModelAttribute("ms") NhanVien ms, Model model) {
        model.addAttribute("listCV", cvService.getAll());
        model.addAttribute("listCH", chService.getAll());
        return "/nhan-vien/add";
    }

    @PostMapping("/add")
    public String add(NhanVien mauSac, Model model) {
        service.add(mauSac);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable UUID id) {
        service.remove(id);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/update")
    public String formUpdate(Model model, @RequestParam UUID id) {
        model.addAttribute("listCV", cvService.getAll());
        model.addAttribute("listCH", chService.getAll());
        model.addAttribute("ms", service.getOne(id));
        return "/nhan-vien/update";
    }

    @PostMapping("/update")
    public String update(NhanVien mauSac) {
        service.add(mauSac);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        keyword = keyword.trim().isEmpty() || keyword == null ? "" : keyword.trim();
        model.addAttribute("list", service.search("%" + keyword + "%"));
        return "/nhan-vien/index";
    }

}

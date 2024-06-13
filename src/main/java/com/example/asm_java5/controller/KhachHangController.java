package com.example.asm_java5.controller;

import com.example.asm_java5.entity.KhachHang;
import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.service.KhachHangService;
import com.example.asm_java5.service.MauSacService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/khach-hang")
@RequiredArgsConstructor
public class KhachHangController {

    private final KhachHangService service;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                          @RequestParam(defaultValue = "5", required = false) Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<KhachHang> list = service.getAll(pageable);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("list", list.getContent());
        return "/khach-hang/index";
    }
    @GetMapping("/form-add")
    public String formAdd( KhachHang ms) {
        return "/khach-hang/add";
    }

    @PostMapping("/add")
    public String add(KhachHang khachHang, Model model) {
        service.add(khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable UUID id) {
        service.remove(id);
        return "redirect:/khach-hang/hien-thi";
    }

    @GetMapping("/update")
    public String formUpdate(Model model, @RequestParam UUID id) {
        model.addAttribute("ms", service.getOne(id));
        return "/khach-hang/update";
    }

    @PostMapping("/update")
    public String update(KhachHang khachHang) {
        service.add(khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        keyword = keyword.trim().isEmpty() || keyword == null ? "" : keyword.trim();
        model.addAttribute("list", service.search("%" + keyword + "%"));
        return "/khach-hang/index";
    }
}

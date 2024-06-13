package com.example.asm_java5.controller;

import com.example.asm_java5.entity.CuaHang;
import com.example.asm_java5.service.CuaHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
@RequiredArgsConstructor
public class CuaHangController {
    private final CuaHangService service;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                          @RequestParam(defaultValue = "5", required = false) Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<CuaHang> list = service.getAll(pageable);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("list", list.getContent());
        return "/cua-hang/index";
    }

    @GetMapping("/form-add")
    public String formAdd(@ModelAttribute("ms") CuaHang ms) {
        return "/cua-hang/add";
    }

    @PostMapping("/add")
    public String add( CuaHang mauSac, Model model) {
        service.add(mauSac);
        return "redirect:/cua-hang/hien-thi";
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable UUID id) {
        service.remove(id);
        return "redirect:/cua-hang/hien-thi";
    }

    @GetMapping("/update")
    public String formUpdate(Model model, @RequestParam UUID id) {
        model.addAttribute("ms", service.getOne(id));
        return "/cua-hang/update";
    }

    @PostMapping("/update")
    public String update(CuaHang mauSac) {
        service.add(mauSac);
        return "redirect:/cua-hang/hien-thi";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        keyword = keyword.trim().isEmpty() || keyword == null ? "" : keyword.trim();
        model.addAttribute("list", service.search("%" + keyword + "%"));
        return "/cua-hang/index";
    }

}

package com.example.asm_java5.controller;

import com.example.asm_java5.entity.DongSanPham;
import com.example.asm_java5.service.DongSpService;
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
@RequestMapping("/dong-sp")
@RequiredArgsConstructor
public class DongSpController {
    private final DongSpService service;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                          @RequestParam(defaultValue = "5", required = false) Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<DongSanPham> list = service.getAll(pageable);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("list", list.getContent());
        return "/dong-sp/index";
    }

    @GetMapping("/form-add")
    public String formAdd(@ModelAttribute("ms") DongSanPham ms) {
        return "/dong-sp/add";
    }

    @PostMapping("/add")
    public String add(@Valid DongSanPham dongSanPham, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", getErrors(bindingResult));
            return "/dong-sp/add";
        }
        service.add(dongSanPham);
        return "redirect:/dong-sp/hien-thi";
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable UUID id) {
        service.remove(id);
        return "redirect:/dong-sp/hien-thi";
    }

    @GetMapping("/update")
    public String formUpdate(Model model, @RequestParam UUID id) {
        model.addAttribute("ms", service.getOne(id));
        return "/dong-sp/update";
    }

    @PostMapping("/update")
    public String update(DongSanPham dongSanPham) {
        service.add(dongSanPham);
        return "redirect:/dong-sp/hien-thi";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        keyword = keyword.trim().isEmpty() || keyword == null ? "" : keyword.trim();
        model.addAttribute("list", service.search("%" + keyword + "%"));
        return "/dong-sp/index";
    }

    public static Map<String, String> getErrors(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError:result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}

package com.example.asm_java5.controller;

import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.service.MauSacService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/mau-sac")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MauSacController {
    private final MauSacService service;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                          @RequestParam(defaultValue = "5", required = false) Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<MauSac> list = service.getAll(pageable);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("list", list.getContent());
        return "/mau_sac/index";
    }

    @GetMapping("/form-add")
    public String formAdd(@ModelAttribute("ms") MauSac ms) {
        return "/mau_sac/add";
    }

    @PostMapping("/add")
    public String add(@Valid MauSac mauSac, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", getErrors(bindingResult));
            return "/mau_sac/add";
        }
        service.add(mauSac);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable UUID id) {
        service.remove(id);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/update")
    public String formUpdate(Model model, @RequestParam UUID id) {
        model.addAttribute("ms", service.getOne(id));
        return "/mau_sac/update";
    }

    @PostMapping("/update")
    public String update(MauSac mauSac) {
        service.add(mauSac);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        keyword = keyword.trim().isEmpty() || keyword == null ? "" : keyword.trim();
        model.addAttribute("list", service.search("%" + keyword + "%"));
        return "/mau_sac/index";
    }

    public static Map<String, String> getErrors(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError:result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}

package com.example.asm_java5.controller;

import com.example.asm_java5.entity.ChucVu;
import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.service.ChucVuService;
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
@RequestMapping("/chuc-vu")
@RequiredArgsConstructor
public class ChucVuController {
    private final ChucVuService service;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                          @RequestParam(defaultValue = "5", required = false) Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<ChucVu> list = service.getAll(pageable);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("list", list.getContent());
        return "/chuc-vu/index";
    }

    @GetMapping("/form-add")
    public String formAdd(@ModelAttribute("ms") ChucVu chucVu) {
        return "/chuc-vu/add";
    }

    @PostMapping("/add")
    public String add(@Valid ChucVu chucVu, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", getErrors(bindingResult));
            return "/chuc-vu/add";
        }
        service.add(chucVu);
        return "redirect:/chuc-vu/hien-thi";
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable UUID id) {
        service.remove(id);
        return "redirect:/chuc-vu/hien-thi";
    }

    @GetMapping("/update")
    public String formUpdate(Model model, @RequestParam UUID id) {
        model.addAttribute("ms", service.getOne(id));
        return "/chuc-vu/update";
    }

    @PostMapping("/update")
    public String update(ChucVu chucVu) {
        service.add(chucVu);
        return "redirect:/chuc-vu/hien-thi";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        keyword = keyword.trim().isEmpty() || keyword == null ? "" : keyword.trim();
        model.addAttribute("list", service.search("%" + keyword + "%"));
        return "/chuc-vu/index";
    }

    public static Map<String, String> getErrors(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError:result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}

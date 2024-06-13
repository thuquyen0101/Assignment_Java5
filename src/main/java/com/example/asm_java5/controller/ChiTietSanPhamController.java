package com.example.asm_java5.controller;

import com.example.asm_java5.entity.ChiTietSanPham;
import com.example.asm_java5.entity.DongSanPham;
import com.example.asm_java5.entity.MauSac;
import com.example.asm_java5.entity.NhaSanXuat;
import com.example.asm_java5.entity.SanPham;
import com.example.asm_java5.service.ChiTietSanPhamSevice;
import com.example.asm_java5.service.DongSpService;
import com.example.asm_java5.service.MauSacService;
import com.example.asm_java5.service.NhaSanXuatService;
import com.example.asm_java5.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/san-pham-ct")
@RequiredArgsConstructor
@Slf4j
public class ChiTietSanPhamController {
    private final ChiTietSanPhamSevice ctService;
    private final SanPhamService sanPhamService;
    private final NhaSanXuatService nsxService;
    private final MauSacService msService;
    private final DongSpService dongSpService;
    List<MauSac> listMS;
    ArrayList<DongSanPham> listDsp;
    ArrayList<NhaSanXuat> listNsx;
    ArrayList<SanPham> listSp;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                          @RequestParam(defaultValue = "5", required = false) Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<ChiTietSanPham> list = ctService.getAllCtsp(pageable);

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("listCtsp", list.getContent());

        return "/san-pham-ct/index";
    }

    @GetMapping("/form-add")
    public String hienThi(@ModelAttribute("ms") ChiTietSanPham ms, Model model) {
        listSp = sanPhamService.getAll();
        listDsp = dongSpService.getAll();
        listNsx = nsxService.getAll();
        listMS = msService.getAll();
        model.addAttribute("listSp",listSp);
        model.addAttribute("listDsp",listDsp);
        model.addAttribute("listNsx",listNsx);
        model.addAttribute("listMS",listMS);

        return "/san-pham-ct/add";
    }

    @PostMapping("/add")
    public String addProductDetails(@Valid ChiTietSanPham ctsp, BindingResult bindingResult, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            model.addFlashAttribute("errors", getErrors(bindingResult));
            return "redirect:/san-pham-ct/form-add";
        }
        ctService.saveCtsp(ctsp);
        return "redirect:/san-pham-ct/hien-thi";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable UUID id) {
        ctService.remove(id);
        return "redirect:/san-pham-ct/hien-thi";
    }
    @GetMapping("/update")
    public String formUpdate(Model model, @RequestParam UUID id) {
        listSp = sanPhamService.getAll();
        listDsp = dongSpService.getAll();
        listNsx = nsxService.getAll();
        listMS = msService.getAll();
        model.addAttribute("listSp",listSp);
        model.addAttribute("listDsp",listDsp);
        model.addAttribute("listNsx",listNsx);
        model.addAttribute("listMS",listMS);

        model.addAttribute("ms", ctService.getOne(id));
        return "/san-pham-ct/update";
    }

    @PostMapping("/update")
    public String update(ChiTietSanPham mauSac) {
        ctService.saveCtsp(mauSac);
        return "redirect:/san-pham-ct/hien-thi";
    }
    public static Map<String, String> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}

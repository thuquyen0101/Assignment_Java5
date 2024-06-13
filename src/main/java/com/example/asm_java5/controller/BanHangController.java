package com.example.asm_java5.controller;


import com.example.asm_java5.entity.ChiTietSanPham;
import com.example.asm_java5.entity.HoaDon;
import com.example.asm_java5.entity.HoaDonCTId;
import com.example.asm_java5.entity.HoaDonChiTiet;
import com.example.asm_java5.entity.KhachHang;
import com.example.asm_java5.repository.HoaDonChiTietRepository;
import com.example.asm_java5.service.ChiTietSanPhamSevice;
import com.example.asm_java5.service.HoaDonChiTietService;
import com.example.asm_java5.service.HoaDonService;
import com.example.asm_java5.service.KhachHangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ban-hang")
//@RequiredArgsConstructor
@Slf4j
public class BanHangController {
    @Autowired
    private KhachHangService khService;
    @Autowired
    private HoaDonService hdService;
    @Autowired
    private HoaDonChiTietService hdctService;
    @Autowired
    private ChiTietSanPhamSevice ctspService;
    @Autowired
    private HoaDonChiTietRepository hdctRepo;
    Double tongTien;
    ArrayList<HoaDon> listHD;
    List<HoaDonChiTiet> listHDCT;
    Page<ChiTietSanPham> listCtsp;

    UUID idHoaDon;
    Integer soLuongMua;
    Double thanhTien;

    public BanHangController() {
        listHD = new ArrayList<>();
        listHDCT = new ArrayList<>();

        tongTien = (double) 0;
        soLuongMua = 1;
        thanhTien = (double) 0;
    }

    @GetMapping
    public ModelAndView giaoDien() {
        ModelAndView modelAndView = new ModelAndView("ban-hang/index");

        listHD = (ArrayList<HoaDon>) hdService.getAllHoaDon();
        modelAndView.addObject("listHD", listHD);
        modelAndView.addObject("tongTien", tongTien);

        return modelAndView;
    }

    @PostMapping("/search-kh")
    public String timKhachHang(@RequestParam String sdt, RedirectAttributes model) {
        KhachHang kh = khService.searchSdt(sdt);
        if (kh == null || kh.getMa() == null) {
            KhachHang khachHang = new KhachHang();
            khService.create(khachHang);
            model.addAttribute("kh", khachHang);
            return "redirect:/ban-hang";
        }
        model.addFlashAttribute("kh", kh);
        return "redirect:/ban-hang";
    }

    @PostMapping("/create-hoa-don")
    public String taoHoaDon(HoaDon hoaDon, @RequestParam String sdtKH) {
        KhachHang khachHang = khService.searchSdt(sdtKH);
        hoaDon.setKhachHang(khachHang);
        log.info("kh {}", khachHang);
        log.info("sdt kh {}", sdtKH);
        hdService.createHoaDon(hoaDon);
        return "redirect:/ban-hang";
    }

    @GetMapping("/hoa-don")
    public String getHoaDon(@RequestParam UUID idHD, Model model) {
        model.addAttribute("hoaDon", hdService.getHDById(idHD));
        model.addAttribute("listHDCT", hdctService.getHoaDonChiTietByIdHd(idHD));
        tongTien = (double) 0;
        listHD = (ArrayList<HoaDon>) hdService.getAllHoaDon();
        idHoaDon = idHD;
        listHDCT = hdctService.getHoaDonChiTietByIdHd(idHD);
        for (HoaDonChiTiet x : listHDCT) {
            tongTien += x.getThanhTien();
        }
        model.addAttribute("listHD", listHD);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("idHoaDon", idHoaDon);

        log.info("list hdct {}", hdctService.getHoaDonChiTietByIdHd(idHD));
        return "ban-hang/index";
    }

    @GetMapping("/view-chon-spct")
    public String chonSpct(Model model
            , @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                           @RequestParam(defaultValue = "5", required = false) Integer size
//                           , @RequestParam UUID idHoaDon
    ) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        listCtsp = ctspService.getAllCtsp(pageable);
        log.info("idHoaDon {}", idHoaDon);

        model.addAttribute("idHoaDon", idHoaDon);
        model.addAttribute("listCtsp", listCtsp.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPage", listCtsp.getTotalPages());
        return "ban-hang/list_spct";
    }

    @PostMapping("/add-to-hd")
    public String addCTSPToHD(@RequestParam UUID idCtsp, @RequestParam UUID idHoaDon, RedirectAttributes attributes) {
        log.info("id hoa don {}", idHoaDon);
        log.info("idCtsp {}", idCtsp);

        HoaDonCTId hoaDonCTId = new HoaDonCTId(idCtsp, idHoaDon);
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();

        for (HoaDonChiTiet x : listHDCT) {
            if (x.getId().getSpctId() == idCtsp && x.getId().getHdId() == idHoaDon) {
                soLuongMua = x.getSoLuong() + 1;
                log.info("soLuongMua {}", soLuongMua);
                log.info("hoaDonCTId {}", hoaDonCTId.getHdId() + " " + hoaDonCTId.getSpctId());

                x.setSoLuong(soLuongMua);
            }
        }

        ChiTietSanPham ctsp = ctspService.getOne(idCtsp);
        Integer soLuongTon = ctsp.getSoLuongTon() - soLuongMua;
        ctsp.setSoLuongTon(soLuongTon);

        HoaDon hoaDon = hdService.getHDById(idHoaDon);

        hoaDonChiTiet.setId(hoaDonCTId);
        hoaDonChiTiet.setDonGia(ctsp.getGiaBan());
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setChiTietSanPham(ctsp);
        hoaDonChiTiet.setSoLuong(soLuongMua);

        thanhTien = hoaDonChiTiet.getDonGia() * soLuongMua;

        hdctService.save(hoaDonChiTiet);
        tongTien += hoaDonChiTiet.getThanhTien();
        listHDCT = hdctService.getHoaDonChiTietByIdHd(idHoaDon);


        attributes.addFlashAttribute("listHDCT", listHDCT);
        attributes.addFlashAttribute("thanhTien", thanhTien);
        attributes.addFlashAttribute("soLuongTon", soLuongTon);

        return "redirect:/ban-hang";
    }

    @GetMapping("/remove-from-hdct")
    public String xoaKhoiHD(@RequestParam UUID idHD, @RequestParam UUID idSPCT, RedirectAttributes model) {
        HoaDonChiTiet hoaDonChiTiet = hdctService.getHoaDonChiTietByIdHDAndIdSPCT(idHD, idSPCT);
        log.info("id hd, idspct {}", idHD + " "+ idSPCT);

        ChiTietSanPham spct = ctspService.getOne(idSPCT);
        spct.setSoLuongTon(spct.getSoLuongTon() + hoaDonChiTiet.getSoLuong());
        ctspService.saveCtsp(spct);

        hdctService.delete(hoaDonChiTiet);
        listHDCT = hdctService.getHoaDonChiTietByIdHd(idHD);

        log.info("listHDCT {}", listHDCT);
        tongTien = tongTien - hoaDonChiTiet.getThanhTien();

        model.addFlashAttribute("listHDCT", listHDCT);
        model.addFlashAttribute("tongTien", tongTien);
        model.addFlashAttribute("hoaDon", hdService.getHDById(idHD));
        return "redirect:/ban-hang";
    }

    @GetMapping("/thanh-toan")
    public String xacNhanThanhToan(@RequestParam UUID idHD) {
        HoaDon hoaDon = hdService.getHDById(idHD);
        hoaDon.setTinhTrang(1);
        hoaDon.setNgayThanhToan(new Date());
        hdService.save(hoaDon);
        tongTien = (double) 0;
        return "redirect:/ban-hang";
    }
}

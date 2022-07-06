package com.changsu.project.changsushop.controller;

import com.changsu.project.changsushop.controller.form.AlbumSaveForm;
import com.changsu.project.changsushop.controller.form.AlbumUpdateForm;
import com.changsu.project.changsushop.domain.item.Album;
import com.changsu.project.changsushop.repository.item.AlbumRepository;
import com.changsu.project.changsushop.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @desc 상품 - 엘범 저장 수정 조회 컨트롤러
 * @author ChangSu, Ham
 * @version 1.0
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumRepository albumRepository;

    /**
     * @desc 엘범 조회 메소드
     * @param albumId
     * @param model
     * @return
     */
    @GetMapping("/items/albums/{albumId}")
    public String album(@PathVariable("albumId") Long albumId, Model model){
        Album album = albumService.findById(albumId);
        AlbumUpdateForm albumUpdateForm = new AlbumUpdateForm(album);
        model.addAttribute("album", albumUpdateForm);
        return "items/albums/album";
    }

    /**
     * @desc 앨범 등록 페이지 이동 메소드
     * @param model
     * @return
     */
    @GetMapping("/items/albums/new")
    public String createAlbumForm(Model model) {
        model.addAttribute("albumForm", new AlbumSaveForm());
        return "items/albums/createAlbumForm";
    }

    /**
     * @desc 엘범 등록 메소드
     * @param form 엘범 정보 임력값
     * @param bindingResult 검증 객체
     * @param redirectAttributes 리다이랙트 데이터 매핑
     * @return
     */
    @PostMapping("/items/albums/new")
    public String createAlbum(@Validated @ModelAttribute("albumForm") AlbumSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            log.info("error{}", bindingResult);
            return "items/albums/createAlbumForm";
        }

        Long albumId = albumService.save(form);

        redirectAttributes.addAttribute("albumId", albumId);

        return "redirect:/items/albums/{albumId}";
    }

    /**
     * @desc 엘범 수정 페이지 이동
     * @param albumId 엘범 아이디
     * @param model
     * @return
     */
    @GetMapping("/items/albums/{albumId}/update")
    public String updateAlbumForm(@PathVariable("albumId") Long albumId, Model model) {

        AlbumUpdateForm albumUpdateForm = albumService.findByIdAUF(albumId);

        model.addAttribute("albumForm", albumUpdateForm);
        return "items/albums/updateAlbumForm";
    }

    /**
     * @desc 엘범 수정
     * @param albumId 수정할 앨범 아이디
     * @param form 수정 앨범 객체
     * @param bindingResult 검증 객체
     * @param redirectAttributes 리다이렉트 데이터 매핑
     * @return
     */
    @PostMapping("/items/albums/{albumId}/update")
    public String updateAlbum(@PathVariable("albumId") Long albumId, @ModelAttribute("albumForm") AlbumUpdateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        model.addAttribute("albumForm", new AlbumSaveForm());

        if(bindingResult.hasErrors()){
            log.info("error{}", bindingResult);
            return "items/albums/updateAlbumForm";
        }
        System.out.println("albumId = " + albumId);
        System.out.println("form.getId() = " + form.getId());
        Long updateAlbumId = albumService.update(form);

        redirectAttributes.addAttribute("albumId", updateAlbumId);
        return "redirect:/items/albums/{albumId}";
    }
}

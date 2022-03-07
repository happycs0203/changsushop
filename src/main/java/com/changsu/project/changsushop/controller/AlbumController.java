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

@Slf4j
@Controller
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumRepository albumRepository;

    @GetMapping("/items/albums/{albumId}")
    public String album(@PathVariable("albumId") Long albumId, Model model){
        Album album = albumService.findById(albumId);
        AlbumUpdateForm albumUpdateForm = new AlbumUpdateForm(album);
        model.addAttribute("album", albumUpdateForm);
        return "items/albums/album";
    }

    @GetMapping("/items/albums/new")
    public String createAlbumForm(Model model) {
        model.addAttribute("albumForm", new AlbumSaveForm());
        return "items/albums/createAlbumForm";
    }

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

    @GetMapping("/items/albums/{albumId}/update")
    public String updateAlbumForm(@PathVariable("albumId") Long albumId, Model model) {

        AlbumUpdateForm albumUpdateForm = albumService.findByIdAUF(albumId);

        model.addAttribute("albumForm", albumUpdateForm);
        return "items/albums/updateAlbumForm";
    }

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

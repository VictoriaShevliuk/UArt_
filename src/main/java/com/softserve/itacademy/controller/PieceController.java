package com.softserve.itacademy.controller;

import com.softserve.itacademy.dto.PieceDto;
import com.softserve.itacademy.dto.PieceTransformer;
import com.softserve.itacademy.model.Genre;
import com.softserve.itacademy.model.Piece;
import com.softserve.itacademy.service.DescriptionService;
import com.softserve.itacademy.service.PieceService;
import com.softserve.itacademy.service.ExhibitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pieces")
public class PieceController {
    private final PieceService pieceService;
    private final ExhibitionService exhibitionService;
    private final DescriptionService descriptionService;

    public PieceController(PieceService pieceService, ExhibitionService exhibitionService, DescriptionService descriptionService) {
        this.pieceService = pieceService;
        this.exhibitionService = exhibitionService;
        this.descriptionService = descriptionService;
    }

    @GetMapping("/create/exhibitions/{exhibition_id}")
    public String create(@PathVariable("exhibition_id") long exhibitionId, Model model) {
        model.addAttribute("piece", new PieceDto());
        model.addAttribute("exhibition", exhibitionService.readById(exhibitionId));
        model.addAttribute("genres", Genre.values());
        return "create-piece";
    }

    @PostMapping("/create/exhibitions/{exhibition_id}")
    public String create(@PathVariable("exhibition_id") long exhibitionId, Model model,
                         @Validated @ModelAttribute("piece") PieceDto pieceDto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("exhibition", exhibitionService.readById(exhibitionId));
            model.addAttribute("genre", Genre.values());
            return "create-piece";
        }
        Piece piece = PieceTransformer.convertToEntity(
                pieceDto,
                exhibitionService.readById(pieceDto.getExhibitionId()),
                descriptionService.getByName("New")
        );
        pieceService.create(piece);
        //return "redirect:/exhibitions/" + exhibitionId + "/pieces";
        return "redirect:/exhibitions/all/users/";
    }

    @GetMapping("/{piece_id}/update/exhibitions/{exhibition_id}")
    public String update(@PathVariable("piece_id") long pieceId, @PathVariable("exhibition_id") long exhibitionId, Model model) {
        PieceDto pieceDto = PieceTransformer.convertToDto(pieceService.readById(pieceId));
        model.addAttribute("piece", pieceDto);
        model.addAttribute("genres", Genre.values());
        model.addAttribute("description", descriptionService.getAll());
        return "update-piece";
    }

    @PostMapping("/{piece_id}/update/exhibitions/{exhibition_id}")
    public String update(@PathVariable("piece_id") long taskId, @PathVariable("exhibition_id") long exhibitionId, Model model,
                         @Validated @ModelAttribute("piece")PieceDto pieceDto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("genres", Genre.values());
            model.addAttribute("types", descriptionService.getAll());
            return "update-piece";
        }
        Piece piece = PieceTransformer.convertToEntity(
                pieceDto,
                exhibitionService.readById(pieceDto.getExhibitionId()),
                descriptionService.readById(pieceDto.getTypeId())
        );
        pieceService.update(piece);
        return "redirect:/exhibitions/" + exhibitionId + "/pieces";
    }

    @GetMapping("/{piece_id}/delete/exhibitions/{exhibition_id}")
    public String delete(@PathVariable("piece_id") long pieceId, @PathVariable("exhibition_id") long exhibitionId) {
        pieceService.delete(pieceId);
        return "redirect:/exhibitions/" + exhibitionId + "/pieces";
    }
}

package com.softserve.itacademy.controller;

import com.softserve.itacademy.dto.PieceDto;
import com.softserve.itacademy.dto.PieceTransformer;
import com.softserve.itacademy.model.Genre;
//import com.softserve.itacademy.model.ImageData;
import com.softserve.itacademy.model.Piece;

import com.softserve.itacademy.service.PieceService;
import com.softserve.itacademy.service.ExhibitionService;
//import com.softserve.itacademy.service.impl.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/pieces")
public class PieceController {
    private final PieceService pieceService;
    private final ExhibitionService exhibitionService;
    //private final ImageService imageService;


    public PieceController(PieceService pieceService, ExhibitionService exhibitionService) {
        this.pieceService = pieceService;
        this.exhibitionService = exhibitionService;
        //this.imageService = imageService;
    }

    @GetMapping("/create/exhibitions/{exhibition_id}")
    public String create(@PathVariable("exhibition_id") long exhibitionId,  Model model) {
        model.addAttribute("piece", new PieceDto());
        model.addAttribute("exhibition", exhibitionService.readById(exhibitionId));
        model.addAttribute("genres", Genre.values());


        return "create-piece";
    }

    @PostMapping("/create/exhibitions/{exhibition_id}")
    public String create(@PathVariable("exhibition_id") long exhibitionId, Model model,
                         @Validated @ModelAttribute("piece") PieceDto pieceDto, BindingResult result) throws IOException {
        if (result.hasErrors()) {


            pieceDto.setExhibitionId(exhibitionId);
            pieceDto.setGenre(pieceDto.getGenre());
            pieceDto.setImageUrl(pieceDto.getImageUrl());
            return "create-piece";
        }
        Piece piece = PieceTransformer.convertToEntity(
                pieceDto,
                exhibitionService.readById(pieceDto.getExhibitionId())
        );
        pieceService.create(piece);
        return "redirect:/exhibitions/" + exhibitionId + "/pieces";
       // return "redirect:/exhibitions/all/users/";
    }

    @GetMapping("/{piece_id}/update/exhibitions/{exhibition_id}")
    public String update(@PathVariable("piece_id") long pieceId, @PathVariable("exhibition_id") long exhibitionId, Model model) {
        PieceDto pieceDto = PieceTransformer.convertToDto(pieceService.readById(pieceId));
        model.addAttribute("piece", pieceDto);
        model.addAttribute("genres", Genre.values());
       // byte[] imageData= imageService.downloadImage(fileName);
       // model.addAttribute("images", imageService.downloadImage(fileName));
       /* ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);*/
        return "update-piece";
    }

    @PostMapping("/{piece_id}/update/exhibitions/{exhibition_id}")
    public String update(@PathVariable("piece_id") long pieceId, @PathVariable("exhibition_id") long exhibitionId, Model model,
                         @Validated @ModelAttribute("piece")PieceDto pieceDto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("genres", Genre.values());
            model.addAttribute("imageUrl", pieceDto.getImageUrl());
           // model.addAttribute("images", imageService.downloadImage(fileName));
            return "update-piece";
        }
        Piece piece = PieceTransformer.convertToEntity(
                pieceDto,
                exhibitionService.readById(pieceDto.getExhibitionId())

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

package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Exhibition;
import com.softserve.itacademy.model.Piece;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.PieceService;
import com.softserve.itacademy.service.ExhibitionService;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/exhibitions")
public class ExhibitionController {

    private final ExhibitionService exhibitionService;
    private final PieceService pieceService;
    private final UserService userService;

    public ExhibitionController(ExhibitionService exhibitionService, PieceService pieceService, UserService userService) {
        this.exhibitionService = exhibitionService;
        this.pieceService = pieceService;
        this.userService = userService;
    }

    @GetMapping("/create/users/{owner_id}")
    public String create(@PathVariable("owner_id") long ownerId, Model model) {
        model.addAttribute("exhibition", new Exhibition());
        model.addAttribute("ownerId", ownerId);
        return "create-exhibition";
    }

    @PostMapping("/create/users/{owner_id}")
    public String create(@PathVariable("owner_id") long ownerId, @Validated @ModelAttribute("exhibition") Exhibition exhibition, BindingResult result) {
        if (result.hasErrors()) {
            return "create-exhibition";
        }
        exhibition.setCreatedAt(LocalDateTime.now());
        exhibition.setOwner(userService.readById(ownerId));
        exhibitionService.create(exhibition);
        return "redirect:/exhibitions/all/users/" + ownerId;
    }

    @GetMapping("/{id}/pieces")
    public String read(@PathVariable long id, Model model) {
        Exhibition exhibition = exhibitionService.readById(id);
        List<Piece> pieces = pieceService.getByExhibitionId(id);
        List<User> users = userService.getAll().stream()
                .filter(user -> user.getId() != exhibition.getOwner().getId()).collect(Collectors.toList());
        model.addAttribute("exhibition", exhibition);
        model.addAttribute("pieces", pieces);
        model.addAttribute("users", users);
        return "exhibition-pieces";
    }

    @GetMapping("/{exhibition_id}/update/users/{owner_id}")
    public String update(@PathVariable("exhibition_id") long exhibitionId, @PathVariable("owner_id") long ownerId, Model model) {
        Exhibition exhibition = exhibitionService.readById(exhibitionId);
        model.addAttribute("exhibition", exhibition);
        return "update-exhibition";
    }

    @PostMapping("/{exhibition_id}/update/users/{owner_id}")
    public String update(@PathVariable("exhibition_id") long exhibitionId, @PathVariable("owner_id") long ownerId,
                         @Validated @ModelAttribute("exhibition") Exhibition exhibition, BindingResult result) {
        if (result.hasErrors()) {
            exhibition.setOwner(userService.readById(ownerId));
            return "update-exhibition";
        }
        Exhibition oldExhibition = exhibitionService.readById(exhibitionId);
        exhibition.setOwner(oldExhibition.getOwner());
        exhibition.setCollaborators(oldExhibition.getCollaborators());
        exhibitionService.update(exhibition);
        return "redirect:/exhibitions/all/users/" + ownerId;
    }

    @GetMapping("/{exhibition_id}/delete/users/{owner_id}")
    public String delete(@PathVariable("exhibition_id") long exhibitionId, @PathVariable("owner_id") long ownerId) {
        exhibitionService.delete(exhibitionId);
        return "redirect:/exhibitions/all/users/" + ownerId;
    }

    @GetMapping("/all/users/{user_id}")
    public String getAll(@PathVariable("user_id") long userId, Model model) {
        List<Exhibition> exhibitions = exhibitionService.getByUserId(userId);
        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("user", userService.readById(userId));
        return "exhibitions-user";
    }

    @GetMapping("/{id}/add")
    public String addCollaborator(@PathVariable long id, @RequestParam("user_id") long userId) {
        Exhibition exhibition = exhibitionService.readById(id);
        List<User> collaborators = exhibition.getCollaborators();
        collaborators.add(userService.readById(userId));
        exhibition.setCollaborators(collaborators);
        exhibitionService.update(exhibition);
        return "redirect:/exhibitions/" + id + "/pieces";
    }

    @GetMapping("/{id}/remove")
    public String removeCollaborator(@PathVariable long id, @RequestParam("user_id") long userId) {
        Exhibition exhibition = exhibitionService.readById(id);
        List<User> collaborators = exhibition.getCollaborators();
        collaborators.remove(userService.readById(userId));
        exhibition.setCollaborators(collaborators);
        exhibitionService.update(exhibition);
        return "redirect:/exhibitions/" + id + "/pieces";
    }
}

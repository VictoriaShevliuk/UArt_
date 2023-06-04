package com.softserve.itacademy.controller;

import com.softserve.itacademy.service.PieceService;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserService userService;
    private final PieceService pieceService;
    public HomeController(UserService userService, PieceService pieceService) {
        this.userService = userService;
        this.pieceService = pieceService;
    }

    @GetMapping({"/", "home"})
    public String home(Model model) {
        model.addAttribute("pieces", pieceService.getAll());
        model.addAttribute("users", userService.getAll());
        return "home";
    }
}

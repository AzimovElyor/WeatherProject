package com.example.weatherproject.controller;

import com.example.weatherproject.dto.UserResponseDto;
import com.example.weatherproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false,defaultValue = "10") Integer size,
            Model model
    ){
        List<UserResponseDto> all = userService.getAll(page, size);
        model.addAttribute("allUsers", all);
        return "/admin/getAllUsers";
    }

}

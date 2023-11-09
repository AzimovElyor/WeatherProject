package com.example.weatherproject.controller;


import com.example.weatherproject.dto.LoginDto;
import com.example.weatherproject.dto.UserCreatDto;
import com.example.weatherproject.dto.UserResponseDto;
import com.example.weatherproject.enums.UserRole;
import com.example.weatherproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String getLoginPage(){
        return "/auth/login";
    }
    @GetMapping("/register")
    public String getRegisterPage(){
        return "/auth/register";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, Model model){
      try {
          UserResponseDto login = userService.login(loginDto);
          model.addAttribute("user",login);
          if(login.getRole() == UserRole.USER)
              return "/user/menu";
          return "/admin/menu";

      }catch (RuntimeException e){
          model.addAttribute("error",e.getMessage());
          return "/auth/login";
      }

    }
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserCreatDto registerDto, Model model){
        try {
            ModelAndView view = new ModelAndView("/user/menu");
            UserResponseDto register = userService.register(registerDto);
            view.addObject("user",register);
            return view;
        }catch (RuntimeException e){
            model.addAttribute("error",e.getMessage());
            return new ModelAndView("/auth/register");
        }
    }
}

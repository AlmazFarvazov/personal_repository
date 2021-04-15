package ru.itis.javalab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.UserForm;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success_signup";
    }

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("userForm", form);
            return "sign_up_page";
        }
        System.out.println(form);
        return "redirect:/success";
    }


}

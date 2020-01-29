package com.codeup.rogerspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String guessForm() {
        return "dice";
    }

    @PostMapping("/roll-dice")
    public String userGuess(@RequestParam String input, Model model) {
        int guess = Integer.parseInt(input);
        Random randomNum = new Random();
        String answer = "";
        int num = 1 + randomNum.nextInt(6);
        if (num == guess){
            answer = "You guessed correct!!!";
        } else {
            answer = "You guessed INcorrect!!!";
        }
        model.addAttribute("input", input);
        model.addAttribute("num", num);
        model.addAttribute("answer", answer);

        return "dice";

    }
}

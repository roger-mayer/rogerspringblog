
package com.codeup.rogerspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
class DeimosController {

    @GetMapping("/deimos")
    public String countdown(){
        return "countdown";
    }

    @GetMapping("/deimos/{days}")
    public String countdown(@PathVariable String days, Model model){

        model.addAttribute("days", days);
        return "countdown";
    }
}

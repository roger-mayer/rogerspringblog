
package com.codeup.rogerspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class DeimosController {

    @GetMapping("/deimos/{days}")
    @ResponseBody
    public String deimos(@PathVariable String days){
        return "There are " + days +  "days left";
    }

}

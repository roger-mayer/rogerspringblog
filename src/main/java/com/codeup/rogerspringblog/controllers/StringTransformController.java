package com.codeup.rogerspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StringTransformController {

    @GetMapping("/string/reverse/{string}")
    @ResponseBody
    public StringBuilder reverse(@PathVariable String string){
        StringBuilder input1 = new StringBuilder();
        // append a string into StringBuilder input1
        input1.append(string);
        // reverse StringBuilder input1
        input1 = input1.reverse();
        // print reversed String
        return input1;
    }

    @GetMapping("string/uppercase/{string}")
    @ResponseBody
    public String toUpper(@PathVariable String string){
        return string.toUpperCase();
    }

    @GetMapping("/string/both/{string}")
    @ResponseBody
    public StringBuilder upperReverse(@PathVariable String string) {
        string = string.toUpperCase();
        return reverse(string);
    }


}

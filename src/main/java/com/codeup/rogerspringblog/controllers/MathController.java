package com.codeup.rogerspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @GetMapping("/add/{x}/and/{y}")
    @ResponseBody
    public String add(@PathVariable int x, @PathVariable int y) {
        return x + " plus " + y + " = " + (x+y);
    }

    @GetMapping("/subtract/{x}/from/{y}")
    @ResponseBody
    public String subtract(@PathVariable int x, @PathVariable int y){
        return y + " minus " + x + " = " + (y-x);
    }

    @GetMapping("/multiply/{x}/and/{y}")
    @ResponseBody
    public String multiply(@PathVariable int x, @PathVariable int y){
        return x + " * " + y + " = " + (x*y);
    }
    @GetMapping("/divide/{x}/and/{y}")
    @ResponseBody
    public String divide(@PathVariable int x, @PathVariable int y){
        try {
            return x + " / " + y + " = " + (x/y);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        return ("cannot divide by zero");
    }



}

package com.codeup.rogerspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StringTransformController {

    @GetMapping("/string/reverse/{string}")
    @ResponseBody
    public String reverse(@PathVariable String string){
        char[] in = string.toCharArray();
        int begin=0;
        int end=in.length-1;
        char temp;
        while(end>begin){
            temp = in[begin];
            in[begin]=in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }


    @GetMapping("string/uppercase/{string}")
    @ResponseBody
    public String uppercase(@PathVariable String string){
        return string.toUpperCase();
    }

    @GetMapping("/string/both/{string}")
    @ResponseBody
    public String upperReverse(@PathVariable String string) {
        string = string.toUpperCase();
        return reverse(string);
    }

    @RequestMapping(path = "/string/{string}", method = RequestMethod.GET)
    @ResponseBody
    public String query(
        @PathVariable String string,
        @RequestParam(value="reverse", required=false) boolean reverse,
        @RequestParam(value="uppercase", required = false) boolean uppercase){
        if (reverse && uppercase){
            return upperReverse(string);
        } else if (reverse){
            return reverse(string);
        } else if (uppercase){
            return uppercase(string);
        } else {
            return string;
        }
    }

}

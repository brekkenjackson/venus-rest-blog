package bjack.venusrestblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value = "/", produces = "application/json")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        // returns and is interpreted as HTML
        return new String("<h1>Hello world</h1>");
    }

        @GetMapping("/hello/{name}")
        @ResponseBody
        public String hello(@PathVariable String name) {
            return "Hello, " + name + "!";
        }

}

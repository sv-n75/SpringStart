package startSpring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String firstSpringApplication() {
        return "It’s my first spring boot application";
    }
}

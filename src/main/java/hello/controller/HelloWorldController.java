package hello.controller;


import hello.form.ValidationBean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/hello")
public class HelloWorldController {

    @PostMapping("/validate")
    public @ResponseBody ValidationBean hello(@Valid @RequestBody ValidationBean bean){
        return bean;
    }
}

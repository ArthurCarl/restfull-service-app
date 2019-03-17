package hello.controller;


import hello.form.ValidationBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(("/hello"))
public class HelloWorldController {

    @PostMapping("/validate")
    public ResponseEntity<ValidationBean> hello(@Valid @RequestBody ValidationBean bean){
        return ResponseEntity.ok(bean);
    }
}

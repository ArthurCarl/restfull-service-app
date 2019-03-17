package hello.controller;


import hello.form.ValidationBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(("/hello"))
public class HelloWorldController {
//    @PostMapping("/validate")
//    public ResponseEntity<ValidationBean> hello(@RequestBody @Valid ValidationBean bean) {
//        return ResponseEntity.ok(bean);
//    }
    @RequestMapping("/validate")
    public @ResponseBody
    ResponseEntity<ValidationBean> hello(@Valid ValidationBean bean) {
        return ResponseEntity.ok(bean);
    }
}

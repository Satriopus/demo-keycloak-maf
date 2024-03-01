package br.com.almarsantana.demokeycloakmfa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/helloworld")
@PreAuthorize("hasRole('favela')")
public class HelloWorldController {

    @GetMapping
    public String getMethodName() {
        return "Olá MFA";
    }

}

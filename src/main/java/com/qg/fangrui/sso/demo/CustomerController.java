package com.qg.fangrui.sso.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 控制层
 * @author FunriLy
 * Created by FunriLy on 2017/12/23.
 * From small beginnings comes great things.
 */
@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repository;
    private final KeycloakSecurityContext securityContext;
    private final AccessToken accessToken;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        // 重定向到 home 页面
        return "redirect:/";
    }

    @GetMapping("/customers/{id}")
    public String customer(@PathVariable("id") int id, Model model) {
        model.addAttribute(repository.findOne(id));
        return "customer";
    }

    @GetMapping("/customers")
    public String customers(Model model, Principal principal) {
        log.info("AccessToken: " + securityContext.getTokenString());
        log.info("User: {} / {}", accessToken.getPreferredUsername(), accessToken.getName());
        log.info("Principal: {}", principal.getName());
        model.addAttribute(repository.findAll());
        return "customers";
    }
}

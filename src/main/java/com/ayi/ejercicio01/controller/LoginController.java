package com.ayi.ejercicio01.controller;

import com.ayi.ejercicio01.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final IUsuarioService iUsuarioService;

    @Autowired
    public LoginController(IUsuarioService iUsuarioService){
        this.iUsuarioService = iUsuarioService;
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login"; // Vista Thymeleaf para el login
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String nombre, @RequestParam String passwordUsuario, Model model) {
        boolean esValido = iUsuarioService.validarUsuario(nombre, passwordUsuario);

        if (esValido) {
            return "redirect:/listado"; // Redirige
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }
}

package com.appLogin.appLogin.controller;

import com.appLogin.appLogin.entity.Usuario;
import com.appLogin.appLogin.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@NoArgsConstructor
@AllArgsConstructor
@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository ur;

    @GetMapping("/login")
    public String login() {
        return "login"; // Vai procurar por templates/login.html
    }

    @GetMapping("/index")
    public String dashbord(){
        return "index";
    }

    @PostMapping("/login")
    public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response){
        Usuario usuarioLogado = this.ur.login(usuario.getEmail(), usuario.getSenha());
        if(usuarioLogado != null){
            return "redirect:/index";
        }

        model.addAttribute("erro", "Usuário Inválido");
        return "login";
    }

    @GetMapping("/")
    public String redirecionarParaLogin() {
        return "redirect:/login"; // ou "login" se quiser renderizar direto o template
    }

}
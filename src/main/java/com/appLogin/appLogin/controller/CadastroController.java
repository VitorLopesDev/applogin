package com.appLogin.appLogin.controller;

import com.appLogin.appLogin.entity.Usuario;
import com.appLogin.appLogin.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class CadastroController {

    private final UsuarioRepository usuarioRepository;

    // Exibe a página de cadastro
    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    // Recebe os dados e cadastra
    @PostMapping("/cadastro")
    public String cadastrarUsuario(Usuario usuario, Model model) {

        // Verifica se já existe usuário com o mesmo email
        Usuario existente = usuarioRepository.findByEmail(usuario.getEmail());
        if (existente != null) {
            model.addAttribute("Erro", "Já existe um usuário com este e-mail.");
            return "cadastro";
        }

        usuarioRepository.save(usuario);
        model.addAttribute("Sucesso", "Usuário cadastrado com sucesso!");

        return "redirect:/login";
    }
}

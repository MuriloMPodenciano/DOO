package ifsp.doo.atas.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsp.doo.atas.domain.usecases.usuario.CadastrarUsuarioUseCase;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
    @Autowired
    private CadastrarUsuarioUseCase cadastrarUsuario;

    @GetMapping
    public boolean estaCadastrado() {
        return cadastrarUsuario.estaCadastrado();
    }

    @PostMapping
    public void setUsuario(@RequestBody String nome) {
        cadastrarUsuario.cadastrarUsuario(nome);
    }
}

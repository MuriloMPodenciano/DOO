package ifsp.doo.atas.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsp.doo.atas.domain.usecases.usuario.CadastrarUsuarioUseCase;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private CadastrarUsuarioUseCase cadastrarUsuario;

    @PostMapping
    public void setUsuario(@RequestBody String nome) {
        cadastrarUsuario.cadastrarUsuario(nome);
    }
}

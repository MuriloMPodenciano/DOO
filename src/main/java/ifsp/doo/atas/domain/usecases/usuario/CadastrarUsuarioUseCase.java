package ifsp.doo.atas.domain.usecases.usuario;

import org.springframework.stereotype.Component;

import ifsp.doo.atas.domain.model.Usuario;

@Component
public class CadastrarUsuarioUseCase {
    public void cadastrarUsuario(String usuario) {
        Usuario.setNome(usuario);
    }

    public boolean estaCadastrado() {
        return Usuario.isValid();
    }
}

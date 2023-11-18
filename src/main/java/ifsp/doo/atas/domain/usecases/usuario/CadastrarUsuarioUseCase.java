package ifsp.doo.atas.domain.usecases.usuario;

import ifsp.doo.atas.domain.model.Usuario;

public class CadastrarUsuarioUseCase {
    public void cadastrarUsuario(String usuario) {
        Usuario.setNome(usuario);
    }
}

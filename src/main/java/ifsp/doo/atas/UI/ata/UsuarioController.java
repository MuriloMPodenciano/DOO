package ifsp.doo.atas.UI.ata;

import java.io.IOException;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ifsp.doo.atas.UI.AtasReuniaoApplication;
import ifsp.doo.atas.domain.usecases.usuario.CadastrarUsuarioUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
public class UsuarioController {
    @FXML
    private TextField nome;

    // @Autowired
    private CadastrarUsuarioUseCase cadastrarUseCase = new CadastrarUsuarioUseCase();

    @FXML
    public void entrar(ActionEvent event) throws IOException {
        cadastrarUseCase.cadastrarUsuario(nome.getText());

        AtasReuniaoApplication.changeScene("menu/menu.fxml");
    }
}

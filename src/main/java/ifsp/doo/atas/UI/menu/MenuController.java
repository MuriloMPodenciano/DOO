package ifsp.doo.atas.UI.menu;

import java.io.IOException;

import org.springframework.stereotype.Component;

import ifsp.doo.atas.UI.AtasReuniaoApplication;
import javafx.fxml.FXML;

@Component
public class MenuController {
    @FXML
    public void ButtonListPessoa() throws IOException {
        AtasReuniaoApplication.changeScene("pessoa/ListarPessoa.fxml");
    }

    @FXML
    public void ButtonListGrupo() throws IOException {
        AtasReuniaoApplication.changeScene("grupo/ListarAllGrupo.fxml");
    }

    @FXML
    public void ButtonListAta() throws IOException {
        
    }
}

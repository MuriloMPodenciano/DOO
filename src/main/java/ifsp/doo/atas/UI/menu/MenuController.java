package ifsp.doo.atas.UI.menu;

import ifsp.doo.atas.UI.utils.ControllerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {
    @FXML
    private Button ButtonExitUsuario;
    @FXML
    private Button ButtonListPessoa;
    @FXML
    private Button ButtonListGrupo;
    @FXML
    private Button ButtonListAta;

    @FXML
    private void ButtonExitUsuario(ActionEvent event){
        ControllerUtil.changeScene(event, "java/ifsp/doo/atas/UI/ata/redator.fxml");
    }

    @FXML
    private void ButtonListPessoa(ActionEvent event){
        ControllerUtil.changeScene(event, "java/ifsp/doo/atas/UI/pessoa/ListarPessoa.fxml");
    }

    @FXML
    private void ButtonListGrupo(ActionEvent event){
        ControllerUtil.changeScene(event, "java/ifsp/doo/atas/UI/grupo/ListAllGrupo.fxml");
    }

    @FXML
    private void ButtonListAta(ActionEvent event){
        ControllerUtil.changeScene(event, "");
    }
}

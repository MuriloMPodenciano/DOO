package ifsp.doo.atas.UI.grupo;

import ifsp.doo.atas.UI.utils.ControllerUtil;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.usecases.grupo.BuscarGrupoUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ListAllGrupoController {
    @FXML
    private Button ButtonDetalhes;

    @FXML
    private Button ButtonVoltar;

    @FXML
    private Label LabelNomeGrupo;

    @FXML
    private ListView<GrupoGetPersistDTO> ListViewGrupos;

    @Autowired
    private BuscarGrupoUseCase buscarGrupo;

    @FXML
    public void initialize(){
        ObservableList<GrupoGetPersistDTO> observableList = FXCollections.observableList(buscarGrupo.getAll());
        ListViewGrupos.setItems(observableList);
    }

    @FXML
    void detalhes(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("java/ifsp/doo/atas/UI/grupo/DetalhesGrupo.fxml"));
        DetalhesGrupoController detalhesGrupoController = fxmlLoader.getController();
        detalhesGrupoController.setGrupo(new Grupo(ListViewGrupos.getSelectionModel().getSelectedItem()));

        ControllerUtil.changeScene(event, "java/ifsp/doo/atas/UI/grupo/DetalhesGrupo.fxml");
    }

    @FXML
    void voltar(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }
}

package ifsp.doo.atas.domain.controller.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.usecases.pessoa.BuscarPessoaUseCase;
import ifsp.doo.atas.domain.utils.ControllerUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;


public class ListarPessoaController {
    @FXML
    private Button ButtonDetalhesPessoa;
    @FXML
    private Button ButtonVoltar;
    @FXML
    private Button ButtonNovaPessoa;
    @FXML
    private ListView<PessoaGetPersistDTO> listViewPessoas;

    private BuscarPessoaUseCase buscarPessoaUseCase;

    @FXML
    void initialize() {
        updateListView();
    }
    private void updateListView() {
        List<PessoaGetPersistDTO> pessoas = buscarPessoaUseCase.getAll();

        ObservableList<PessoaGetPersistDTO> observableList = FXCollections.observableArrayList(pessoas);

        listViewPessoas.setItems(observableList);
    }

    @FXML
    void ButtonVoltar(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }

    @FXML
    void ButtonNovaPessoa(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("java/ifsp/doo/atas/interface/local-javafx/pessoa/Criarpessoa.fxml"));
        Parent root = fxmlLoader.load();

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setScene(new Scene(root));

        dialogStage.showAndWait();
    }

    @FXML
    void ButtonDetalhesPessoa(ActionEvent event) throws IOException {
        PessoaGetPersistDTO selectedPessoa = listViewPessoas.getSelectionModel().getSelectedItem();

        if (selectedPessoa != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("java/ifsp/doo/atas/interface/local-javafx/pessoa/DetalhesPessoa.fxml"));
            Parent root = fxmlLoader.load();

            DetalhesPessoaController detalhesPessoaController = fxmlLoader.getController();
            detalhesPessoaController.setPessoa(selectedPessoa);

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initStyle(StageStyle.UNDECORATED);
            dialogStage.setScene(new Scene(root));

            dialogStage.showAndWait();
        }
    }
}

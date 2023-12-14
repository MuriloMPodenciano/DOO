package ifsp.doo.atas.UI.pessoa;

import ifsp.doo.atas.UI.utils.ControllerUtil;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.usecases.pessoa.BuscarPessoaUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ListarPessoaController {
    @FXML
    private Button ButtonDetalhesPessoa;
    @FXML
    private Button ButtonVoltar;
    @FXML
    private Button ButtonNovaPessoa;
    @FXML
    private ListView<PessoaGetPersistDTO> ListViewPessoas;

    private BuscarPessoaUseCase buscarPessoaUseCase = new BuscarPessoaUseCase();

    @FXML
    void initialize() {
        updateListView();
    }
    private void updateListView() {
        List<PessoaGetPersistDTO> pessoas = buscarPessoaUseCase.getAll();

        ObservableList<PessoaGetPersistDTO> observableList = FXCollections.observableArrayList(pessoas);

        ListViewPessoas.setItems(observableList);
    }

    @FXML
    void ButtonVoltar(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }

    @FXML
    void ButtonNovaPessoa(ActionEvent event) {
        ControllerUtil.changeScene(event,"java/ifsp/doo/atas/UI/pessoa/CriarPessoa.fxml" );
    }

    @FXML
    void ButtonDetalhesPessoa(ActionEvent event) throws IOException {
        PessoaGetPersistDTO selectedPessoa = ListViewPessoas.getSelectionModel().getSelectedItem();

        if (selectedPessoa != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("java/ifsp/doo/atas/UI/pessoa/DetalhesPessoa.fxml"));

            DetalhesPessoaController detalhesPessoaController = fxmlLoader.getController();
            detalhesPessoaController.setPessoa(selectedPessoa);

            ControllerUtil.changeScene(event, "java/ifsp/doo/atas/UI/pessoa/DetalhesPessoa.fxml");
        }
    }
}

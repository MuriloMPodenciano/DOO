package ifsp.doo.atas.UI.grupo;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.usecases.grupo.EditarGrupoUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.BuscarPessoaUseCase;
import ifsp.doo.atas.domain.utils.ControllerUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdicionarPessoaController {
    @FXML
    private Button ButtonAdicionar;

    @FXML
    private Button ButtonCancelar;

    @FXML
    private ListView<PessoaGetPersistDTO> ListViewPessoas;

    private Long grupoId;
    @Autowired
    private BuscarPessoaUseCase buscarPessoas;
    @Autowired
    private EditarGrupoUseCase editarGrupo;

    @FXML
    public void initialize(){
        ObservableList<PessoaGetPersistDTO> observableList = FXCollections.observableList(buscarPessoas.getAll());
        ListViewPessoas.setItems(observableList);
    }

    @FXML
    void adicionar(ActionEvent event) throws IOException {
        ObservableList<PessoaGetPersistDTO> selecionados = ListViewPessoas.getSelectionModel().getSelectedItems();
        editarGrupo.addFuncionarios(grupoId, selecionados.stream().toList());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ifsp.doo.atas.domain.UI.grupo.DetalhesGrupo"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cancelar(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }
}

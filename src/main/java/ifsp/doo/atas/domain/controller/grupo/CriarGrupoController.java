package ifsp.doo.atas.domain.controller.grupo;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.usecases.grupo.CadastrarGrupoUseCase;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class CriarGrupoController {
    @FXML
    private Button ButtonAdicionarPessoa;

    @FXML
    private Button ButtonCriar;

    @FXML
    private Button ButtonRemoverPessoa;

    @FXML
    private Button ButtonVoltar;

    @FXML
    private ListView<PessoaGetPersistDTO> ListViewMembros;

    @FXML
    private ListView<PessoaGetPersistDTO> ListViewPessoas;

    @FXML
    private TextField TextFieldNome;

    @Autowired
    private BuscarPessoaUseCase buscarPessoa;
    @Autowired
    private CadastrarGrupoUseCase criarGrupo;

    @FXML
    public void initialize(){
        ObservableList<PessoaGetPersistDTO> observableList = FXCollections.observableList(buscarPessoa.getAll());
        ListViewPessoas.setItems(observableList);
    }

    @FXML
    void adicionar(ActionEvent event) {
        ListViewMembros.getItems().addAll(ListViewPessoas.getSelectionModel().getSelectedItems());
        ListViewPessoas.getSelectionModel().getSelectedItems().removeAll();
    }

    @FXML
    void criar(ActionEvent event) throws IOException {
        ObservableList<PessoaGetPersistDTO> membros = FXCollections.observableList(ListViewMembros.getItems());
        GrupoPostRequestDTO grupo = new GrupoPostRequestDTO(TextFieldNome.getText(), membros.stream().toList());
        criarGrupo.cadastrarGrupo(grupo);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ifsp.doo.atas.domain.UI.grupo.ListAllGrupo"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void remover(ActionEvent event) {
        ListViewPessoas.getItems().addAll(ListViewMembros.getSelectionModel().getSelectedItems());
        ListViewMembros.getSelectionModel().getSelectedItems().removeAll();
    }

    @FXML
    void voltar(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }
}

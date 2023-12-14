package ifsp.doo.atas.UI.grupo;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.Pessoa;
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

import java.io.IOException;

public class DetalhesGrupoController {
    @FXML
    private Button ButtonAdicionarPessoa;

    @FXML
    private Button ButtonEditar;

    @FXML
    private Button ButtonVoltar;

    @FXML
    private Label LabelNomeGrupo;

    @FXML
    private ListView<PessoaGetPersistDTO> ListViewPessoas;
    private Grupo grupo;

    @FXML
    public void initialize(){
        GrupoGetPersistDTO grupoPersist = new GrupoGetPersistDTO(grupo);
        LabelNomeGrupo.setText(grupoPersist.nome());
        ObservableList<PessoaGetPersistDTO> pessoas = FXCollections.observableList(grupoPersist.funcionarios());
        ListViewPessoas.setItems(pessoas);
    }

    @FXML
    void adicionarPessoa(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ifsp.doo.atas.domain.UI.grupo.ListAllGrupo"));
        Scene scene = new Scene(fxmlLoader.load());
        AdicionarPessoaController controller = fxmlLoader.getController();
        GrupoGetPersistDTO grupoPersist = new GrupoGetPersistDTO(grupo);
        controller.setGrupoId(grupoPersist.id());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ifsp.doo.atas.domain.UI.grupo.EditarGrupo"));
        Scene scene = new Scene(fxmlLoader.load());
        EditarGrupoController controller = fxmlLoader.getController();
        controller.setGrupo(grupo);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ifsp.doo.atas.domain.UI.grupo.ListAllGrupo"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}

package ifsp.doo.atas.domain.controller.grupo;

import ifsp.doo.atas.domain.model.Grupo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EditarGrupoController {
    @FXML
    private Button ButtonAdicionarPessoa;

    @FXML
    private Button ButtonEditar;

    @FXML
    private Button ButtonRemoverPessoa;

    @FXML
    private Button ButtonVoltar;

    @FXML
    private CheckBox CheckboxAtivo;

    @FXML
    private ListView<?> ListViewMembros;

    @FXML
    private ListView<?> ListViewPessoas;

    @FXML
    private TextField TextFieldNome;

    private Grupo grupo;

    @FXML
    void adicionar(ActionEvent event) {

    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void remover(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {

    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}

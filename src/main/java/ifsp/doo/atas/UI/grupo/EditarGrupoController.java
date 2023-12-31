package ifsp.doo.atas.UI.grupo;

import ifsp.doo.atas.UI.utils.ControllerUtil;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPutRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.usecases.grupo.EditarGrupoUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.BuscarPessoaUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class EditarGrupoController {
    @FXML
    private Button ButtonEditar;


    @FXML
    private Button ButtonVoltar;

    @FXML
    private CheckBox CheckboxAtivo;

    @FXML
    private ListView<PessoaGetPersistDTO> ListViewMembros;


    @FXML
    private TextField TextFieldNome;

    private Grupo grupo;
    // @Autowired
    // private BuscarPessoaUseCase buscarPessoa;
    @Autowired
    private EditarGrupoUseCase editarGrupo;

    @FXML
    public void initialize(){
        GrupoGetPersistDTO grupoPersist = new GrupoGetPersistDTO(grupo);

        ObservableList<PessoaGetPersistDTO> membros = FXCollections.observableList(grupoPersist.funcionarios());
        ListViewMembros.setItems(membros);

        CheckboxAtivo.setSelected(grupoPersist.status());

        TextFieldNome.setText(grupoPersist.nome());
    }

    @FXML
    void editar(ActionEvent event) throws IOException {
         GrupoGetPersistDTO grupoPersist = new GrupoGetPersistDTO(grupo);
         GrupoPutRequestDTO grupoModificado = new GrupoPutRequestDTO(grupoPersist.id(), CheckboxAtivo.isSelected());
         editarGrupo.editarGrupo(grupoModificado);

         voltar(event);
    }

    @FXML
    void voltar(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}

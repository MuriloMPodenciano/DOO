package ifsp.doo.atas.domain.controller.grupo;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.usecases.grupo.EditarGrupoUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.BuscarPessoaUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;

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
    void adicionar(ActionEvent event) {
        ObservableList<PessoaGetPersistDTO> selecionados = ListViewPessoas.getSelectionModel().getSelectedItems();
        editarGrupo.addFuncionarios(grupoId, selecionados.stream().toList());
//        adicionar mensagem de sucesso de fechar tela
    }

    @FXML
    void cancelar(ActionEvent event) {

    }
}

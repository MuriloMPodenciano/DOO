package ifsp.doo.atas.UI.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPutRequestDTO;
import ifsp.doo.atas.domain.usecases.pessoa.EditarPessoaUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditarPessoaController {
    @FXML
    private Button ButtonVoltar;
    @FXML
    private Button ButtonSalvarPessoa;

    @FXML
    private TextField CargoPessoa;

    @FXML
    private TextField EmailPessoa;

    @FXML
    private TextField NomePessoa;

    @FXML
    private CheckBox CheckboxAtivo;

    private EditarPessoaUseCase editarPessoaUseCase;

    private PessoaGetPersistDTO pessoa;

    public void setPessoa(PessoaGetPersistDTO pessoa) {
        this.pessoa = pessoa;
        initializeFields();
    }

    private void initializeFields() {
        if (pessoa != null) {
            NomePessoa.setText(pessoa.getNome());
            EmailPessoa.setText(pessoa.getEmail());
            CargoPessoa.setText(pessoa.getCargo());
            CheckboxAtivo.setSelected(pessoa.getStatus());
        }
    }
    @FXML
    void ButtonVoltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    private void ButtonSalvarPessoa(ActionEvent event) throws IOException {
        PessoaPutRequestDTO putDTO = new PessoaPutRequestDTO(
                pessoa.getId(),
                pessoa.getCargo(),
                pessoa.getStatus()
        );

        editarPessoaUseCase.editarPessoa(putDTO);

        ButtonVoltar(event);
    }
}

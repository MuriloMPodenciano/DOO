package ifsp.doo.atas.UI.pessoa;

import ifsp.doo.atas.UI.utils.ControllerUtil;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class DetalhesPessoaController {
    @FXML
    private Button ButtonEditarPessoa;
    @FXML
    private Button ButtonVoltar;
    @FXML
    private Label NomePessoa;
    @FXML
    private Label EmailPessoa;
    @FXML
    private Label CargoPessoa;
    @FXML
    private Label StatusPessoa;


    private PessoaGetPersistDTO selectedPessoa;

    public void setPessoa(PessoaGetPersistDTO pessoa) {
        this.selectedPessoa = pessoa;
        updateLabels();
    }
    private void updateLabels() {
        if (selectedPessoa != null) {
            NomePessoa.setText(selectedPessoa.getNome());
            EmailPessoa.setText(selectedPessoa.getEmail());
            CargoPessoa.setText(selectedPessoa.getCargo());
            StatusPessoa.setText(selectedPessoa.getStatus().toString());
        }
    }
    @FXML
    void ButtonVoltar(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }
    @FXML
    private void ButtonEditarPessoa(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("java/ifsp/doo/atas/UI/pessoa/EditarPessoa.fxml"));

        EditarPessoaController editarPessoaController = fxmlLoader.getController();
        editarPessoaController.setPessoa(selectedPessoa);

        ControllerUtil.changeScene(event, "java/ifsp/doo/atas/UI/pessoa/EditarPessoa.fxml");
    }

}

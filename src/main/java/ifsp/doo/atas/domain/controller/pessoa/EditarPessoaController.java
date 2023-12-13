package ifsp.doo.atas.domain.controller.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("java/ifsp/doo/atas/interface/local-javafx/pessoa/DetalhesPessoa.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

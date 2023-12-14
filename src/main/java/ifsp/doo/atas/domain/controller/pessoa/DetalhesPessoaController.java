package ifsp.doo.atas.domain.controller.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    void ButtonVoltar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("java/ifsp/doo/atas/interface/local-javafx/pessoa/ListarPessoa.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

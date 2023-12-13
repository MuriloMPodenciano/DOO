package ifsp.doo.atas.domain.controller.pessoa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DetalhesPessoaController {
    @FXML
    private Button ButtonEditarPessoa;
    @FXML
    private Button ButtonVoltar;
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

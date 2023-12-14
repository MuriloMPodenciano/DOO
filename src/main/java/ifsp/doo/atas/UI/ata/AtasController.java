package ifsp.doo.atas.UI.ata;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ifsp.doo.atas.UI.AtasReuniaoApplication;
import ifsp.doo.atas.UI.ata.VerAtaController;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.usecases.ata.BuscarAtaUseCase;
import ifsp.doo.atas.domain.usecases.ata.EditarAtaUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

@Component
public class AtasController {
    @FXML
    private TableView<AtaGetResponseDTO> atas;

    @FXML
    private TableCell<AtaGetResponseDTO, String> titulo;

    @FXML
    private TableCell<AtaGetResponseDTO, String> local;

    @FXML
    private TableCell<AtaGetResponseDTO, String> data;

    @FXML
    private TableCell<AtaGetResponseDTO, String> grupo;

    private ObservableList<AtaGetResponseDTO> ataList;

    @Autowired
    private BuscarAtaUseCase buscarAta;

    @Autowired
    private EditarAtaUseCase editarAta;

    public void initialize() {
        ataList = FXCollections.observableList(buscarAta.getAll());
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("menu/menu.fxml");
    }

    @FXML
    public void ver(ActionEvent event) throws IOException {
        AtaGetResponseDTO ata = atas.getSelectionModel().getSelectedItem();

        if (ata == null)
            return;

        AtasReuniaoApplication.changeScene("ata/verAta.fxml");

        VerAtaController controller = (VerAtaController) AtasReuniaoApplication.getController();

        controller.setAta(ata);
    }

    @FXML
    public void novo(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("ata/novaAta.fxml");
    }

    @FXML
    public void deletar(ActionEvent event) {
        AtaGetResponseDTO ata = atas.getSelectionModel().getSelectedItem();

        if (ata == null)
            return;

        editarAta.deletar(ata.id());

        ataList.remove(ata);
    }
}

package ifsp.doo.atas.UI.ata;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.TextField;

import ifsp.doo.atas.UI.AtasReuniaoApplication;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.informe.InformeGetResponseDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePostRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.usecases.ata.EditarInformeUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

@Component
public class AdicionarInformeController {
    @FXML
    private TableView<InformeGetResponseDTO> informes;

    @FXML
    private TableCell<InformeGetResponseDTO, String> InformeTexto;

    @FXML
    private TableCell<InformeGetResponseDTO, String> pessoaInforme;

    @FXML
    private TextField informe;

    @FXML
    private ComboBox<PessoaGetResponseDTO> pessoa;

    private AtaGetResponseDTO ata;

    private ObservableList<InformeGetResponseDTO> informeList;

    @Autowired
    private EditarInformeUseCase editarInforme;
    
    public void initialize() {
        informeList = FXCollections.observableList(ata.informes());
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("ata/verAta.fxml");
    }

    @FXML
    public void adicionar(ActionEvent event) {
        InformePostRequestDTO request = new InformePostRequestDTO(informe.getText(), pessoa.getSelectionModel().getSelectedItem());

        InformeGetResponseDTO pauta = editarInforme.add(ata.id(), request);

        informeList.add(pauta);
    }

    public void setAta(AtaGetResponseDTO ata) {
        this.ata = ata;
    }
}

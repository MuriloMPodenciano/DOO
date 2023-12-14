package ifsp.doo.atas.UI.ata;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.TextField;

import ifsp.doo.atas.UI.AtasReuniaoApplication;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.usecases.ata.EditarPautaUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

@Component
public class AdicionarPautaController {
    @FXML
    private TableView<PautaGetResponseDTO> pautas;

    @FXML
    private TableCell<PautaGetResponseDTO, String> pautaTexto;

    @FXML
    private TableCell<PautaGetResponseDTO, String> pessoaPauta;

    @FXML
    private TextField pauta;

    @FXML
    private ComboBox<PessoaGetResponseDTO> pessoa;

    private AtaGetResponseDTO ata;

    private ObservableList<PautaGetResponseDTO> pautaList;

    @Autowired
    private EditarPautaUseCase editarPauta;
    
    public void initialize() {
        pautaList = FXCollections.observableList(ata.pautas());
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("ata/verAta.fxml");
    }

    @FXML
    public void adicionar(ActionEvent event) {
        PautaPostRequestDTO request = new PautaPostRequestDTO(pauta.getText(), pessoa.getSelectionModel().getSelectedItem());

        PautaGetResponseDTO pauta = editarPauta.add(ata.id(), request);

        pautaList.add(pauta);
    }

    public void setAta(AtaGetResponseDTO ata) {
        this.ata = ata;
    }
}

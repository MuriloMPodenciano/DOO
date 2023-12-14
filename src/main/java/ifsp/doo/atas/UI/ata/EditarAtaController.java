package ifsp.doo.atas.UI.ata;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.TextField;

import ifsp.doo.atas.UI.AtasReuniaoApplication;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPutRequestDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.model.AtaState;
import ifsp.doo.atas.domain.usecases.ata.EditarAtaUseCase;
import ifsp.doo.atas.domain.usecases.grupo.BuscarGrupoUseCase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

@Component
public class EditarAtaController {
    @FXML
    private TextField titulo;
    @FXML
    private TextField descricao;
    @FXML
    private TextField textoAbertura;
    @FXML
    private ComboBox<PessoaGetResponseDTO> pessoa;
    @FXML
    private DatePicker dataInicio;
    @FXML
    private DatePicker fimPrevisto;
    @FXML
    private TextField local;
    @FXML
    private ComboBox<GrupoGetResponseDTO> grupo;
    @FXML
    private TextField textoEncerramento;
    @FXML
    private DatePicker horaEncerramento;
    @FXML
    private TextField nomeRedator;
    @FXML
    private Label estado;
    @FXML
    private Button mudarEstado;

    private AtaGetResponseDTO ata;

    @Autowired
    private EditarAtaUseCase editarAta;

    @Autowired
    private BuscarGrupoUseCase buscarGrupo;

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("ata/verAta.fxml");
    }

    @FXML
    public void mudarEstado(ActionEvent event) {
        if (ata.estado() == AtaState.PRE_ATA)
            editarAta.begin(ata.id());
        else
            editarAta.finish(ata.id());
    }

    @FXML
    public void confirmar(ActionEvent event) throws IOException {
        AtaPutRequestDTO request = new AtaPutRequestDTO(
            ata.id(),
            titulo.getText(),
            descricao.getText(),
            textoAbertura.getText(),
            pessoa.getSelectionModel().getSelectedItem(),
            LocalDateTime.from(dataInicio.getValue()),
            LocalDateTime.from(fimPrevisto.getValue()),
            local.getText(),
            grupo.getSelectionModel().getSelectedItem(),
            textoEncerramento.getText(),
            LocalDateTime.from(horaEncerramento.getValue())
        );

        editarAta.update(request);

        voltar(event);
    }

    @FXML
    public void mudarGrupo(ActionEvent event) {
        GrupoGetResponseDTO g = grupo.getSelectionModel().getSelectedItem();

        if (g == null)
            return;

        pessoa.setItems(FXCollections.observableList(g.funcionarios()));
    }

    public void setAta(AtaGetResponseDTO ata) {
        this.ata = ata;

        titulo.setText(ata.titulo());
        descricao.setText(ata.descricao());
        textoAbertura.setText(ata.textoAbertura());
        if (ata.grupo() != null)
            pessoa.setItems(FXCollections.observableList(ata.grupo().funcionarios()));
        dataInicio.setValue(LocalDate.from(ata.dataInicio()));
        fimPrevisto.setValue(LocalDate.from(ata.fimPrevisto()));
        local.setText(ata.local());
        grupo.setItems(
            FXCollections.observableList(
                buscarGrupo.getAll()
                    .stream()
                    .map(GrupoGetResponseDTO::new)
                    .collect(Collectors.toList())
            )
        );
        textoEncerramento.setText(ata.textoEncerramento());
        horaEncerramento.setValue(LocalDate.from(ata.horaEncerramento()));
        nomeRedator.setText(ata.nomeRedator());
        estado.setText(ata.estado().name());

        if (ata.estado() == AtaState.PRE_ATA)
            mudarEstado.setText("Comerçar");
        else if (ata.estado() == AtaState.INITIATED)
            mudarEstado.setText("Finalizar");
        else
            mudarEstado.setText("");
    }
}

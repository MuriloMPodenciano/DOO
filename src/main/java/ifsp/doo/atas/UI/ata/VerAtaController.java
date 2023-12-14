package ifsp.doo.atas.UI.ata;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import aj.org.objectweb.asm.Label;
import ifsp.doo.atas.UI.AtasReuniaoApplication;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.informe.InformeGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.usecases.ata.EditarAtaUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

public class VerAtaController {
    @FXML
    private Label titulo;
    @FXML
    private Label descricao;
    @FXML
    private Label textoAbertura;
    @FXML
    private Label pessoa;
    @FXML
    private Label dataInicio;
    @FXML
    private Label fimPrevisto;
    @FXML
    private Label local;
    @FXML
    private Label grupo;
    @FXML
    private Label textoEncerramento;
    @FXML
    private Label horaEncerramento;
    @FXML
    private Label nomeRedator;
    @FXML
    private Label estado;
    @FXML
    private TableView<PessoaGetResponseDTO> listaPresenca;
    @FXML
    private TableCell<PessoaGetResponseDTO, String> nomePessoa;
    @FXML
    private TableCell<PessoaGetResponseDTO, String> emailPessoa;
    @FXML
    private TableView<PautaGetResponseDTO> pautas;
    @FXML
    private TableCell<PautaGetResponseDTO, String> pauta;
    @FXML
    private TableCell<PautaGetResponseDTO, String> pessoaPauta;
    @FXML
    private TableView<InformeGetResponseDTO> informes;
    @FXML
    private TableCell<InformeGetResponseDTO, String> informe;
    @FXML
    private TableCell<InformeGetResponseDTO, String> pessoaInforme;

    private AtaGetResponseDTO ata;

    @Autowired
    private EditarAtaUseCase editarAta;

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("ata/ListarAtas.fxml");
    }

    @FXML
    public void editar(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("ata/editarAta.fxml");

        EditarAtaController controller = (EditarAtaController) AtasReuniaoApplication.getController();

        controller.setAta(ata);
    }

    @FXML
    public void adicionarPessoa(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("ata/editarAta.fxml");

        AdicionarPautaController controller = (AdicionarPautaController) AtasReuniaoApplication.getController();

        controller.setAta(ata);
    }

    @FXML
    public void adicionarPauta(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("ata/editarAta.fxml");

        AdicionarPautaController controller = (AdicionarPautaController) AtasReuniaoApplication.getController();

        controller.setAta(ata);
    }

    @FXML
    public void adicionarInforme(ActionEvent event) throws IOException {
        AtasReuniaoApplication.changeScene("ata/editarAta.fxml");

        AdicionarInformeController controller = (AdicionarInformeController) AtasReuniaoApplication.getController();

        controller.setAta(ata);
    }

    @FXML
    public void deletar(ActionEvent event) throws IOException {
        editarAta.deletar(ata.id());

        voltar(event);
    }

    public void setAta(AtaGetResponseDTO ata) {
        this.ata = ata;
    }
}

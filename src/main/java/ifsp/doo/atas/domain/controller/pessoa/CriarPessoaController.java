package ifsp.doo.atas.domain.controller.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaPostRequestDTO;
import ifsp.doo.atas.domain.usecases.pessoa.CadastrarPessoaUseCase;
import ifsp.doo.atas.domain.utils.ControllerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CriarPessoaController {
    @FXML
    private Button ButtonSalvarPessoa;
    @FXML
    private Button ButtonVoltar;
    @FXML
    private TextField NomePessoa;
    @FXML
    private TextField EmailPessoa;
    @FXML
    private TextField CargoPessoa;

    private final CadastrarPessoaUseCase cadastrarPessoaUseCase;

    public CriarPessoaController(CadastrarPessoaUseCase cadastrarPessoaUseCase) {
        this.cadastrarPessoaUseCase = cadastrarPessoaUseCase;
    }

    @FXML
    void ButtonVoltar(ActionEvent event) {
        ControllerUtil.closeWindow(event);
    }

    @FXML
    void ButtonSalvarPessoa(ActionEvent event) {
        String nome = NomePessoa.getText();
        String email = EmailPessoa.getText();
        String cargo = CargoPessoa.getText();

        PessoaPostRequestDTO pessoaDTO = new PessoaPostRequestDTO(nome, email, cargo, true);
        cadastrarPessoaUseCase.cadastrarPessoa(pessoaDTO);
        System.out.println("Nova Pessoa: " + pessoaDTO);
    }


}

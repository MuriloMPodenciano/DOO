package ifsp.doo.atas.domain.model;

import ifsp.doo.atas.domain.DTO.informe.InformeGetPersistDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePostRequestDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePutRequestDTO;
import ifsp.doo.atas.domain.utils.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Informe {
    private Long id;
    private String informe;
    private Pessoa pessoa;
    private Boolean informado;

    public Informe(String informe, Pessoa pessoa) {
        this(null, informe, pessoa, false);
    }

    public Informe(InformePostRequestDTO dto) {
        this(dto.informe(), new Pessoa(dto.pessoa()));
    }

    public Informe(InformeGetPersistDTO dto) {
        this(dto.getId(), dto.getInforme(), new Pessoa(dto.getPessoa()), dto.getInformado());
    }

    public void atualizarInforme(InformePutRequestDTO informe) {
        //verificar se pessoa 
        if (informado)
            throw new IllegalStateException("Cannot change Informe after being informed");

        Notification notificacao = new Notification();

        Pessoa parsedPessoa = Pessoa.parsePessoa(informe.pessoa());

        if (informe.pessoa() != null && parsedPessoa == null)
            notificacao.addError("pessoa must be valid");

        if (informe.informe() != null && informe.informe().isBlank())
            notificacao.addError("informe cannot be empty");

        if (notificacao.hasErrors())
            throw new IllegalArgumentException(notificacao.errorMessage());

        pessoa = parsedPessoa == null ? pessoa : parsedPessoa;
        this.informe = informe.informe() == null ? this.informe : informe.informe();
    }

    public void informar() {
        informado = true;
    }
}

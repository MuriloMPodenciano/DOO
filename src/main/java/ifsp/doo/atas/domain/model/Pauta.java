package ifsp.doo.atas.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.pauta.PautaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPutRequestDTO;
import ifsp.doo.atas.domain.utils.Notification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pauta {
    private Long id;
    private String pauta;
    private Pessoa pessoa;
    private List<Discussao> discussoes;
    private String decisao;
    private PautaState estado;

    public Pauta(String pauta, Pessoa pessoa) {
        this(null, pauta, pessoa, new ArrayList<Discussao>(), null, PautaState.UNBASED);
    }

    public Pauta(PautaPostRequestDTO dto) {
        this(dto.pauta(), new Pessoa(dto.pessoa()));
    }

    public Pauta(PautaGetPersistDTO dto) {
        this(
            dto.getId(),
            dto.getPauta(),
            dto.getPessoa(),
            dto.getDiscussoes()
                .stream()
                .map(Discussao::new)
                .collect(Collectors.toList()),
            dto.getDecisao(),
            dto.getEstado()
        );
    }

    public void atualizarPauta(PautaPutRequestDTO pauta) {
        if (estado != PautaState.UNBASED)
            throw new IllegalStateException("Cannot change Pauta after initiate discussion or being based");

        Notification notificacao = new Notification();

        Pessoa parsedPessoa = Pessoa.parsePessoa(pauta.pessoa());

        if (pauta.pessoa() != null && parsedPessoa == null)
            notificacao.addError("pessoa must be valid");

        if (pauta.pauta() != null && pauta.pauta().isBlank())
            notificacao.addError("pauta cannot be empty");

        if (pauta.decisao() != null && pauta.decisao().isBlank())
            notificacao.addError("decisao cannot be empty");

        if (notificacao.hasErrors())
            throw new IllegalArgumentException(notificacao.errorMessage());

        pessoa = parsedPessoa == null ? pessoa : parsedPessoa;
        this.pauta = pauta.pauta() == null ? this.pauta : pauta.pauta();
        decisao = pauta.decisao() == null ? decisao : pauta.decisao();
    }

    public void addDiscussao(Discussao discussao) {
        if (estado == PautaState.BASED)
            throw new IllegalStateException("Cannot change Pauta after being based");

        if (estado == PautaState.UNBASED)
            estado = PautaState.UNDER_DISCUSSION;

        if (!discussoes.contains(discussao))
            throw new EntityNotFoundException("Pauta " + id + "not have a Discussao " + discussao.getId());

        discussoes.add(discussao);
    }

    public void removeDiscussao(Discussao discussao) {
        if (estado != PautaState.UNDER_DISCUSSION)
            throw new IllegalStateException("Discussao cannot be removed if the Pauta is not on discuss");

        discussoes.remove(discussao);
    }

    public void colocarEmPauta() {
        if (estado == PautaState.BASED)
            throw new IllegalStateException("cannot change Pauta to under discussion state after being based");

        estado = PautaState.UNDER_DISCUSSION;
    }

    public void pautar() {
        if (estado == PautaState.UNBASED)
            throw new IllegalStateException("cannot base Pauta before discuss it");

        estado = PautaState.BASED;
    }
}

package ifsp.doo.atas.domain.model;

import ifsp.doo.atas.domain.DTO.discussao.DiscussaoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.discussao.DiscussaoPostRequestDTO;
import ifsp.doo.atas.domain.DTO.discussao.DiscussaoPutRequestDTO;
import ifsp.doo.atas.domain.utils.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Discussao {
    private Long id;
    private String discussao;
    private Pessoa pessoa;

    public Discussao(String discussao, Pessoa pessoa) {
        this(null, discussao, pessoa);
    }

    public Discussao(DiscussaoPostRequestDTO dto) {
        this(dto.discussao(), new Pessoa(dto.pessoa()));
    }

    public Discussao(DiscussaoGetPersistDTO dto) {
        this(dto.getId(), dto.getDiscussao(), new Pessoa(dto.getPessoa()));
    }

    public void updateDiscussao(DiscussaoPutRequestDTO discussao) {
        //verificar se ata pode receber essa modificação em use cases
        //verificar se dto.pessoa pertence a ata

        Notification notificacao = new Notification();

        Pessoa parsedPessoa = Pessoa.parsePessoa(discussao.pessoa());

        if (discussao.pessoa() != null && parsedPessoa == null)
            notificacao.addError("pessoa must be valid");

        if (discussao.discussao() != null && discussao.discussao().isBlank())
            notificacao.addError("discussao cannot be empty");

        if (notificacao.hasErrors())
            throw new IllegalArgumentException(notificacao.errorMessage());

        pessoa = parsedPessoa == null ? pessoa : parsedPessoa;
        this.discussao = discussao.discussao() == null ? this.discussao : discussao.discussao();
    }
}

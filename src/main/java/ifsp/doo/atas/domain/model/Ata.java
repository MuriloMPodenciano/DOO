package ifsp.doo.atas.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPutRequestDTO;
import ifsp.doo.atas.domain.DTO.ata.PreAtaPostRequestDTO;
import ifsp.doo.atas.domain.utils.Notification;

import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Ata {
    private Long id;
    private String titulo;
    private String discussao;
    private String textoAbertura;
    private LocalDateTime dataInicio;
    private LocalDateTime fimPrevisto;
    private String local;

    @ManyToOne
    private Grupo grupo;

    @Embedded
    @OneToOne
    private Encerramento encerramento;

    @OneToMany
    private List<Pessoa> listaPresenca;

    @OneToMany
    private List<Pauta> pautas;

    @OneToMany
    private List<Informe> informes;

    public Ata(PreAtaPostRequestDTO ataDTO) {
        Notification notificacao = new Notification();

        if (!ataDTO.grupo().status())
            notificacao.addError("Cannot use a disabled grupo");

        if (ataDTO.titulo() == null)
            notificacao.addError("titulo cannot be a empty string");

        if (ataDTO.titulo() != null && ataDTO.titulo().isBlank())
            notificacao.addError("titulo must contain caracteres");

        if (ataDTO.local() == null)
            notificacao.addError("local cannot be a empty string");

        if (ataDTO.local() != null && ataDTO.local().isBlank())
            notificacao.addError("local must contain caracteres");

        Grupo grupo;

        try {
            grupo = new Grupo(ataDTO.grupo());
        } catch (IllegalArgumentException e) {
            notificacao.addError(e.getMessage());
        }

        if (notificacao.hasErrors())
            throw new IllegalArgumentException(notificacao.errorMessage());

        titulo = ataDTO.titulo();
        dataInicio = ataDTO.dataInicio();
        local = ataDTO.local();
        this.grupo = grupo;
        listaPresenca = new ArrayList<Pessoa>();
        pautas = new ArrayList<Pauta>();
        informes = new ArrayList<Informe>();
    }

    public Ata(AtaPostRequestDTO ataDTO) {
        this(
            ataDTO.id(),
            ataDTO.titulo(),
            ataDTO.discussao(),
            ataDTO.textoAbertura(),
            ataDTO.dataInicio(),
            ataDTO.fimPrevisto(),
            ataDTO.local(),
            new Grupo(ataDTO.grupo()),
            new Encerramento(ataDTO.encerramento()),
            ataDTO.listaPresenca().stream().map(Pessoa::new).collect(Collectors.toList()),
            ataDTO.pautas().stream().map(Pauta::new).collect(Collectors.toList()),
            ataDTO.informes().stream().map(Informe::new).collect(Collectors.toList())
        );
    }

    public Ata(AtaGetPersistDTO ataBanco) {
        this(
            ataBanco.id(),
            ataBanco.titulo(),
            ataBanco.discussao(),
            ataBanco.textoAbertura(),
            ataBanco.dataInicio(),
            ataBanco.fimPrevisto(),
            ataBanco.local(),
            new Grupo(ataBanco.grupo()),
            new Encerramento(ataBanco.encerramento()),
            ataBanco.listaPresenca().stream().map(Pessoa::new).collect(Collectors.toList()),
            ataBanco.pautas().stream().map(Pauta::new).collect(Collectors.toList()),
            ataBanco.informes().stream().map(Informe::new).collect(Collectors.toList())
        );
    }

    public void adicionarInforme(Informe informe) {
        if (estaFechado())
            throw new IllegalStateException("ata is already closed");

        informes.add(informe);
    }

    public void removerInforme(Informe informe) {
        if (estaFechado())
            throw new IllegalStateException("ata is already closed");

        informes.remove(informe);
    }

    public void adicionarPauta(Pauta pauta) {
        if (estaFechado())
            throw new IllegalStateException("ata is already closed");

        pautas.add(pauta);
    }

    public void removerPauta(Pauta pauta) {
        if (estaFechado())
            throw new IllegalStateException("ata is already closed");

        pautas.remove(pauta);
    }

    public void marcarPresenca(Pessoa pessoa) {
        if (estaFechado())
            throw new IllegalStateException("ata is already closed");

        if (!grupo.temFuncionario(pessoa))
            throw new IllegalStateException("pessoa does not belong to the group");

        listaPresenca.add(pessoa);
    }

    public void atualizarAta(AtaPutRequestDTO ataDTO) {
        if (estaFechado())
            throw new IllegalStateException("ata is already closed");

        Notification notificacao = new Notification();

        if (ataDTO.local() == null)
            notificacao.addError("local cannot be empty");

        if (ataDTO.local() != null && ataDTO.local().isBlank())
            notificacao.addError("local cannot be empty");

        if (ataDTO.discussao() == null)
            notificacao.addError("discussao cannot be empty");

        if (ataDTO.discussao() != null && ataDTO.discussao().isBlank())
            notificacao.addError("discussao cannot be empty");

        if (ataDTO.textoAbertura() == null)
            notificacao.addError("textoAbertura cannot be empty");

        if (ataDTO.textoAbertura() != null && ataDTO.textoAbertura().isBlank())
            notificacao.addError("texto abertura cannot be empty");

        Encerramento encerramento;

        try {
            encerramento = new Encerramento(ataDTO.encerramento());
        } catch (IllegalArgumentException e) {
            notificacao.addError(e.getMessage());
        }

        if (notificacao.hasErrors())
            throw new IllegalArgumentException(notificacao.errorMessage());

        dataInicio = ataDTO.dataInicio();
        local = ataDTO.local();
        discussao = ataDTO.discussao();
        textoAbertura = ataDTO.textoAbertura();
        this.encerramento = encerramento;
    }

    public boolean estaFechado() {
        return encerramento == null;
    }
}

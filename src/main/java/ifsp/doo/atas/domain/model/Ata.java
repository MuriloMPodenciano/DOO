package ifsp.doo.atas.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPutRequestDTO;
import ifsp.doo.atas.domain.DTO.ata.PreAtaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePostRequestDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;
import ifsp.doo.atas.domain.utils.Notification;

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
    private String descricao;
    private String textoAbertura;
    private Pessoa pessoa;
    private LocalDateTime dataInicio;
    private LocalDateTime fimPrevisto;
    private String local;
    private Grupo grupo;
    private String textoEncerramento;
    private LocalDateTime horaEncerramento;
    private String nomeRedator;
    private Set<Pessoa> listaPresenca;
    private List<Pauta> pautas;
    private List<Informe> informes;
    private AtaState estado;

    public Ata(PreAtaPostRequestDTO ata) {
        Notification notificacao = new Notification();

        if (ata.titulo() == null || ata.titulo().isBlank())
            notificacao.addError("titulo cannot be empty");

        if (ata.descricao() == null || ata.descricao().isBlank())
            notificacao.addError("descricao cannot be empty");

        if (ata.dataInicio() == null)
            notificacao.addError("data inicio must be a valid date");

        if (ata.local() == null || ata.local().isBlank())
            notificacao.addError("local cannot be empty");

        Grupo parsedGrupo = Grupo.parseGrupo(ata.grupo());

        if (parsedGrupo == null || !parsedGrupo.getStatus())
            notificacao.addError("grupo must be valid and not disabled");

        List<Pauta> parsedPautas = parsePautas(ata.pautas());

        if (parsedPautas == null)
            notificacao.addError("all Pauta must be valid");

        List<Informe> parsedInformes = parseInformes(ata.informes());

        if (parsedInformes == null)
            notificacao.addError("all Informe must be valid");

        if (notificacao.hasErrors())
            throw new IllegalArgumentException(notificacao.errorMessage());

        titulo = ata.titulo();
        descricao = ata.descricao();
        dataInicio = ata.dataInicio();
        local = ata.local();
        grupo = parsedGrupo;
        listaPresenca = new HashSet<Pessoa>();
        pautas = parsedPautas;
        informes = parsedInformes;
        estado = AtaState.PRE_ATA;
    }

    public Ata(AtaGetPersistDTO ataBanco) {
        this(
            ataBanco.getId(),
            ataBanco.getTitulo(),
            ataBanco.getDescricao(),
            ataBanco.getTextoAbertura(),
            new Pessoa(ataBanco.getPessoa()),
            ataBanco.getDataInicio(),
            ataBanco.getFimPrevisto(),
            ataBanco.getLocal(),
            new Grupo(ataBanco.getGrupo()),
            ataBanco.getTextoEncerramento(),
            ataBanco.getHoraEncerramento(),
            ataBanco.getNomeRedator(),
            ataBanco.getListaPresenca()
                .stream()
                .map(Pessoa::new)
                .collect(Collectors.toSet()),
            ataBanco.getPautas()
                .stream()
                .map(Pauta::new)
                .collect(Collectors.toList()),
            ataBanco.getInformes()
                .stream()
                .map(Informe::new)
                .collect(Collectors.toList()),
            ataBanco.getEstado()
        );
    }

    public void adicionarInforme(Informe informe) {
        if (estado == AtaState.PRE_ATA)
            throw new IllegalStateException("Could not add Informe in non initiated Ata");

        if (estado == AtaState.FINISHED)
            throw new IllegalStateException("Could not add Informe in closed Ata");

        informes.add(informe);
    }

    public void adicionarPauta(Pauta pauta) {
        if (estado == AtaState.PRE_ATA)
            throw new IllegalStateException("Could not add Pauta in non initiated Ata");

        if (estado == AtaState.FINISHED)
            throw new IllegalStateException("Could not add Pauta in closed Ata");

        pautas.add(pauta);
    }

    public void marcarPresenca(Pessoa pessoa) {
        if (estado == AtaState.PRE_ATA)
            throw new IllegalStateException("Could not add Pessoa in non initiated Ata");

        if (estado == AtaState.FINISHED)
            throw new IllegalStateException("Could not add Pessoa in closed Ata");

        if (!grupo.temFuncionario(pessoa))
            throw new IllegalStateException("pessoa does not belong to the group");

        if (!pessoa.getStatus())
            throw new IllegalStateException("Cannot add a disabled pessoa");

        listaPresenca.add(pessoa);
    }

    public void atualizarAta(AtaPutRequestDTO ata) {
        if (estado == AtaState.FINISHED)
            throw new IllegalStateException("Could not update closed Ata");

        Notification notificacao = new Notification();

        if (ata.titulo() != null && ata.titulo().isBlank()) {
            notificacao.addError("titulo may not be empty");
        }

        if (ata.descricao() != null && ata.descricao().isBlank())
            notificacao.addError("descricao may not be empty");
        
        if (estado == AtaState.PRE_ATA && ata.textoAbertura() != null)    
            notificacao.addError("cannot modify texto abertura in non initiated Ata");
        else if (ata.textoAbertura() != null && ata.textoAbertura().isBlank())
            notificacao.addError("texto abertura may not be empty");

        Pessoa parsedPessoa = Pessoa.parsePessoa(ata.pessoa());

        if (estado == AtaState.PRE_ATA && ata.pessoa() != null)
            notificacao.addError("cannot modify pessoa in non initiated Ata");
        else if (parsedPessoa != null && (!grupo.temFuncionario(parsedPessoa) || !parsedPessoa.getStatus()))
            notificacao.addError("pessoa must be valid and not disabled");

        if (!isRangeCorrect(ata.dataInicio(), ata.fimPrevisto()))
            notificacao.addError("the range of dates data inicio and fim previsto must be valid");

        if (ata.local() != null && ata.local().isBlank())
            notificacao.addError("local may not be empty");

        Grupo parsedGrupo = Grupo.parseGrupo(ata.grupo());

        if (parsedGrupo != null && !parsedGrupo.getStatus())
            notificacao.addError("grupo must be valid and not disabled");

        if (estado == AtaState.PRE_ATA && ata.textoEncerramento() != null)
            notificacao.addError("cannot modify texto encerramento in non initiated Ata");
        else if (ata.textoEncerramento() != null && ata.textoEncerramento().isBlank())
            notificacao.addError("texto encerramento may not be empty");

        if (estado == AtaState.PRE_ATA && ata.horaEncerramento() != null)
            notificacao.addError("cannot modify hora encerramento in non initiated Ata");

        if (notificacao.hasErrors())
            throw new IllegalArgumentException(notificacao.errorMessage());

        titulo = ata.titulo() == null ? titulo : ata.titulo();
        descricao = ata.descricao() == null ? descricao : ata.descricao();
        textoAbertura = ata.textoAbertura() == null ? textoAbertura : ata.textoAbertura();
        pessoa = parsedPessoa == null ? pessoa : parsedPessoa;
        dataInicio = ata.dataInicio() == null ? dataInicio : ata.dataInicio();
        fimPrevisto = ata.fimPrevisto() == null ? fimPrevisto : ata.fimPrevisto();
        local = ata.local() == null ? local : ata.local();
        grupo = parsedGrupo == null ? grupo : parsedGrupo;
        textoEncerramento = ata.textoEncerramento() == null ? textoEncerramento : ata.textoEncerramento();
        horaEncerramento = ata.horaEncerramento() == null ? horaEncerramento : ata.horaEncerramento();
    }

    public void iniciarAta() {
        if (estado == AtaState.FINISHED)
            throw new IllegalStateException("cannot initiate Ata after finish it");

        estado = AtaState.INITIATED;
        nomeRedator = Usuario.getNome();
    }

    public void finalizarAta() {
        if (estado == AtaState.PRE_ATA)
            throw new IllegalStateException("cannot finish Ata before initiate it");

        estado = AtaState.FINISHED;
    }

    private boolean isRangeCorrect(LocalDateTime dataInicio, LocalDateTime fimPrevisto) {
        if (dataInicio == null && fimPrevisto == null)
            return true;

        if (dataInicio == null)
            return this.dataInicio.compareTo(fimPrevisto) <= 0;

        if (fimPrevisto == null)
            return dataInicio.compareTo(this.fimPrevisto) <= 0;

        return dataInicio.compareTo(fimPrevisto) <= 0;
    }

    private List<Pauta> parsePautas(List<PautaPostRequestDTO> pautas) {
        try {
            return pautas
                .stream()
                .map(Pauta::new)
                .collect(Collectors.toList());
        } catch(NullPointerException e) {
            return new ArrayList<>();
        } catch(Exception e) {
            return null;
        }
    }

    private List<Informe> parseInformes(List<InformePostRequestDTO> informes) {
        try {
            
            return informes
                .stream()
                .map(Informe::new)
                .collect(Collectors.toList());
        } catch(NullPointerException e) {
            return new ArrayList<>();
        } catch(Exception e) {
            return null;
        }
    }
}

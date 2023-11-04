package ifsp.doo.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ata {
    private Long id;
    private String titulo;
    private String discussao;
    private String textoAbertura;
    private LocalDateTime dataInicio;
    private LocalDateTime fimPrevisto;
    private String local;
    private Grupo grupo;
    private Encerramento encerramento;
    private List<Pessoa> listaPresenca;
    private List<Pauta> pautas;
    private List<Informe> informes;

    public Ata(Long id, String titulo, String discussao, String textoAbertura, String local, LocalDateTime dataInicio, LocalDateTime fimPrevisto, Grupo grupo) {
        this.id = id;
        this.titulo = titulo;
        this.discussao = discussao;
        this.textoAbertura = textoAbertura;
        this.local = local;
        this.dataInicio = dataInicio;
        this.fimPrevisto = fimPrevisto;
        this.grupo = grupo;
        encerramento = null;
        listaPresenca = new ArrayList<Pessoa>();
        pautas = new ArrayList<Pauta>();
        informes = new ArrayList<Informe>();
    }

    public Ata(String titulo, LocalDateTime dataInicio, String local, Grupo grupo) {
        this(null, titulo, null, null, local, dataInicio, null, grupo);
    }

    public void adicionarInforme(Informe informe) {
        if (encerramento != null)   
            return;

        informes.add(informe);
    }

    public void removerInforme(Informe informe) {
        if (encerramento != null)   
            return;

        informes.remove(informe);
    }

    public void adicionarPauta(Pauta pauta) {
        if (encerramento != null)   
            return;

        pautas.add(pauta);
    }

    public void removerPauta(Pauta pauta) {
        if (encerramento != null)   
            return;

        pautas.remove(pauta);
    }

    public void marcarPresenca(Pessoa pessoa) {
        if (encerramento != null)   
            return;

        listaPresenca.add(pessoa);
    }

    public void modificarMomentoInicio(LocalDateTime momento) {
        if (encerramento != null)   
            return;

        dataInicio = momento;
    }

    public void modificarLocal(String local) {
        if (encerramento != null)   
            return;

        this.local = local;
    }

    public void adicionarDiscussao(String discussao) {
        if (encerramento != null)   
            return;

        this.discussao = discussao;
    }

    public void adicionarTextoAbertura(String textoAbertura) {
        if (encerramento != null)   
            return;

        this.textoAbertura = textoAbertura;
    }

    public void encerrarAta(Encerramento encerramento) {
        this.encerramento = encerramento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
        result = prime * result + ((local == null) ? 0 : local.hashCode());
        result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ata other = (Ata) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (dataInicio == null) {
            if (other.dataInicio != null)
                return false;
        } else if (!dataInicio.equals(other.dataInicio))
            return false;
        if (local == null) {
            if (other.local != null)
                return false;
        } else if (!local.equals(other.local))
            return false;
        if (grupo == null) {
            if (other.grupo != null)
                return false;
        } else if (!grupo.equals(other.grupo))
            return false;
        return true;
    }
}

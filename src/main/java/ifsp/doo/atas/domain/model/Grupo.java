package ifsp.doo.atas.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private Long id;
    private String nome;
    private boolean status;
    private List<Pessoa> funcionarios;

    public Grupo(Long id, String nome, List<Pessoa> funcionarios, boolean status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.funcionarios = funcionarios;
    }

    public Grupo(Long id, String nome, List<Pessoa> funcionarios) {
        this(id, nome, funcionarios, true);
    }

    public Grupo(String nome, List<Pessoa> funcionarios) {
        this(null, nome, new ArrayList<Pessoa>());
    }

    public void inserirFuncionario(Pessoa pessoa) throws NullPointerException {
        funcionarios.add(pessoa);
    }

    public void removerFuncionario(Pessoa pessoa) throws NullPointerException {
        funcionarios.remove(pessoa);
    }

    public void mudarStatus() {
        status = !status;
    }

    public void atualizarGrupo(DadosAtualizacaoGrupo dados) throws NullPointerException {
        if (dados == null)
            throw new NullPointerException("grupo must not be null");

        if (dados.nome() != null && !dados.nome().isBlank())
            nome = dados.nome();

        status = dados.status();

        if (dados.funcionarios() != null)
            funcionarios = dados.funcionarios();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isActive() {
        return status;
    }

    public List<Pessoa> getFuncionarios() {
        return new ArrayList<Pessoa>(funcionarios);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Grupo other = (Grupo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

package ifsp.doo.atas.domain.model;

public class Pessoa {
    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private boolean status;

    public Pessoa(Long id, String nome, String email, String cargo, boolean status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.status = status;
    }

    public Pessoa(Long id, String nome, String email, String cargo) {
        this(id, nome, email, cargo, true);
    }

    public Pessoa(String nome, String email, String cargo) {
        this(null, nome, email, cargo);
    }

    public void mudarStatus() {
        status = !status;
    }

    public void atualizarPessoa(Pessoa pessoa) throws NullPointerException {
        if (pessoa == null)
            throw new NullPointerException("pessoa must not be null");

        if (pessoa.nome != null && !pessoa.nome.isBlank())
            nome = pessoa.nome;

        if (pessoa.email != null && !pessoa.email.isBlank())
            email = pessoa.email;

        if (pessoa.cargo != null && !pessoa.cargo.isBlank())
            cargo = pessoa.cargo;

        status = pessoa.status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public boolean isActive() {
        return status;
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
        Pessoa other = (Pessoa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

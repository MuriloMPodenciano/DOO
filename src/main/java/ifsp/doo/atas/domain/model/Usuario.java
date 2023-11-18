package ifsp.doo.atas.domain.model;

public class Usuario {
    private static String nome;

    private Usuario() {}

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        if (Usuario.nome != null)
            throw new IllegalStateException("usuario already exists");

        Usuario.nome = nome;
    }
}

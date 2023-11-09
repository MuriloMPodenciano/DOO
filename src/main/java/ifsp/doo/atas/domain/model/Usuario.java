package ifsp.doo.atas.domain.model;

public class Usuario {
    static private String nome;

    private Usuario(String nome) {}

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        if (Usuario.nome != null)
            return;

        Usuario.nome = nome;
    }
}

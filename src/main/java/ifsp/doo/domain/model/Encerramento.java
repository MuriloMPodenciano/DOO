package ifsp.doo.domain.model;

import java.time.LocalDateTime;

public class Encerramento {
    private String textoEncerramento;
    private String local;
    private LocalDateTime horaEncerramento;
    private Usuario redator;

    public Encerramento(String textoEncerramento, String local, LocalDateTime horaEncerramento, Usuario redator) {
        this.textoEncerramento = textoEncerramento;
        this.local = local;
        this.horaEncerramento = horaEncerramento;
        this.redator = redator;
    }

    public Encerramento(String textoEncerramento, String local, LocalDateTime horaEncerramento) {
        this(textoEncerramento, local, horaEncerramento, null);
    }
}

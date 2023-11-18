package ifsp.doo.atas.domain.model;

public class Email {
    private String email;

    public Email(String email) {
        if (!email.matches(""))
            throw new IllegalArgumentException("invalid email: " + email);

        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }
}

package com.github.marcoscoutozup.ecommerce.usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NamedQuery(name = "findUsuarioByEmail", query = "select u from Usuario u where email = :email")
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @PastOrPresent
    @CreationTimestamp
    private LocalDateTime created_at;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.email = login;
        this.senha = encriptarSenha(senha);
    }

    public String prepararDadosDoUsuarioParaEmail(){
        return "\n\nUsuário: " + email + "\n\n";
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + email + '\'' +
                '}';
    }

    public String encriptarSenha(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }
}

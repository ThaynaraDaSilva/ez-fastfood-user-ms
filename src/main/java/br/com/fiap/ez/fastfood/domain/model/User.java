package br.com.fiap.ez.fastfood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")  // Define a collection no MongoDB
public class User {
    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo; // CLIENTE ou FUNCIONARIO

    public enum TipoUsuario {
        CLIENTE, FUNCIONARIO
    }
}

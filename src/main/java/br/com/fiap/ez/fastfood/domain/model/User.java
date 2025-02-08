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
    private String cpf;
    private String name;
    private String email;
    private String password;
    private UserType userType; // CLIENTE ou FUNCIONARIO

    public enum UserType {
        CLIENTE, FUNCIONARIO
    }
}

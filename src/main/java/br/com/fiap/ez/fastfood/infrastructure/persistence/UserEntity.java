package br.com.fiap.ez.fastfood.infrastructure.persistence;

import br.com.fiap.ez.fastfood.domain.model.User.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private UserType userType;
}

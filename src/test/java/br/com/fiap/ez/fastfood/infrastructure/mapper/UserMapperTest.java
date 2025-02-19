//package br.com.fiap.ez.fastfood.infrastructure.mapper;
//
//import br.com.fiap.ez.fastfood.domain.model.User;
//import br.com.fiap.ez.fastfood.infrastructure.persistence.UserEntity;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class UserMapperTest {
//
//    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
//
//    @Test
//    public void testToDomain() {
//        // Cria uma instância de UserEntity
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId("1");
//        userEntity.setCpf("123.456.789-10");
//        userEntity.setName("John Doe");
//        userEntity.setEmail("john.doe@example.com");
//        userEntity.setPassword("securePassword123");
//        userEntity.setUserType(User.UserType.CLIENTE);
//
//        // Mapeia UserEntity para User
//        User user = userMapper.toDomain(userEntity);
//
//        // Verifica se o mapeamento está correto
//        assertEquals(userEntity.getId(), user.getId());
//        assertEquals(userEntity.getCpf(), user.getCpf());
//        assertEquals(userEntity.getName(), user.getName());
//        assertEquals(userEntity.getEmail(), user.getEmail());
//        assertEquals(userEntity.getPassword(), user.getPassword());
//        assertEquals(userEntity.getUserType(), user.getUserType());
//    }
//
//    @Test
//    public void testToEntity() {
//        // Cria uma instância de User
//        User user = new User();
//        user.setId("1");
//        user.setCpf("123.456.789-10");
//        user.setName("John Doe");
//        user.setEmail("john.doe@example.com");
//        user.setPassword("securePassword123");
//        user.setUserType(User.UserType.CLIENTE);
//
//        // Mapeia User para UserEntity
//        UserEntity userEntity = userMapper.toEntity(user);
//
//        // Verifica se o mapeamento está correto
//        assertEquals(user.getId(), userEntity.getId());
//        assertEquals(user.getCpf(), userEntity.getCpf());
//        assertEquals(user.getName(), userEntity.getName());
//        assertEquals(user.getEmail(), userEntity.getEmail());
//        assertEquals(user.getPassword(), userEntity.getPassword());
//        assertEquals(user.getUserType(), userEntity.getUserType());
//    }
//}
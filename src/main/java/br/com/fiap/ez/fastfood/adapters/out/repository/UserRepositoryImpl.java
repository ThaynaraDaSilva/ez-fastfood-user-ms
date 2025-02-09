package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.domain.model.User;
import br.com.fiap.ez.fastfood.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final MongoUserRepository mongoUserRepository;

    @Override
    public User save(User user) {
        return mongoUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return mongoUserRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return mongoUserRepository.findByCpf(cpf);
    }

    @Override
    public List<User> findAll() {
        return mongoUserRepository.findAll();
    }
}

package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.domain.model.User;
import br.com.fiap.ez.fastfood.infrastructure.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User toDomain(UserEntity entity);
	UserEntity toEntity(User domain);
}

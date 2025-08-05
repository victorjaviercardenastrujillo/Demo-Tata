package com.tata.demo.application.struct;

import com.tata.demo.application.dto.PersonDto;
import com.tata.demo.infrastructure.entities.ClientEntity;
import com.tata.demo.infrastructure.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "dni", source = "dto.dni")
    @Mapping(target = "names", source = "dto.names")
    @Mapping(target = "surnames", source = "dto.surnames")
    @Mapping(target = "birthday", source = "dto.birthday")
    @Mapping(target = "address", source = "dto.address")
    @Mapping(target = "phone", source = "dto.phone")
    @Mapping(target = "gender", source = "dto.gender")
    PersonEntity dtoToEntity(PersonDto dto);

    @Mapping(target = "id", source = "ent.id")
    @Mapping(target = "dni", source = "ent.dni")
    @Mapping(target = "names", source = "ent.names")
    @Mapping(target = "surnames", source = "ent.surnames")
    @Mapping(target = "birthday", source = "ent.birthday")
    @Mapping(target = "address", source = "ent.address")
    @Mapping(target = "phone", source = "ent.phone")
    @Mapping(target = "gender", source = "ent.gender")
    PersonDto entityToDto(PersonEntity ent);

    @Mapping(target = "id", source = "ent.id")
    @Mapping(target = "dni", source = "ent.dni")
    @Mapping(target = "names", source = "ent.names")
    @Mapping(target = "surnames", source = "ent.surnames")
    @Mapping(target = "birthday", source = "ent.birthday")
    @Mapping(target = "address", source = "ent.address")
    @Mapping(target = "phone", source = "ent.phone")
    @Mapping(target = "gender", source = "ent.gender")
    PersonDto entityToDto(ClientEntity ent);

}

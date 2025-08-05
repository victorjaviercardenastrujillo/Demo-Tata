package com.tata.demo.application.struct;

import com.tata.demo.application.dto.PersonDto;
import com.tata.demo.core.model.PersonClientModel;
import com.tata.demo.infrastructure.entities.ClientEntity;
import com.tata.demo.infrastructure.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonClientMapper {
    PersonClientMapper INSTANCE = Mappers.getMapper(PersonClientMapper.class);

    @Mapping(target = "id", source = "ent.id")
    @Mapping(target = "dni", source = "ent.dni")
    @Mapping(target = "names", source = "ent.names")
    @Mapping(target = "surnames", source = "ent.surnames")
    @Mapping(target = "birthday", source = "ent.birthday")
    @Mapping(target = "address", source = "ent.address")
    @Mapping(target = "phone", source = "ent.phone")
    @Mapping(target = "gender", source = "ent.gender")
    @Mapping(target = "password", source = "ent.password")
    @Mapping(target = "state", source = "ent.state")
    PersonClientModel entityToDto(ClientEntity ent);
}

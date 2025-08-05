package com.tata.demo.application.struct;

import com.tata.demo.core.model.AccountModel;
import com.tata.demo.infrastructure.entities.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "id", source = "model.id")
    @Mapping(target = "accountNumber", source = "model.accountNumber")
    @Mapping(target = "accountType", source = "model.accountType")
    @Mapping(target = "initialBalance", source = "model.initialBalance")
    @Mapping(target = "state", source = "model.state")
    AccountEntity modelToEntity(AccountModel model);

    @Mapping(target = "id", source = "ent.id")
    @Mapping(target = "accountNumber", source = "ent.accountNumber")
    @Mapping(target = "accountType", source = "ent.accountType")
    @Mapping(target = "initialBalance", source = "ent.initialBalance")
    @Mapping(target = "state", source = "ent.state")
    AccountModel entityToModel(AccountEntity ent);


}

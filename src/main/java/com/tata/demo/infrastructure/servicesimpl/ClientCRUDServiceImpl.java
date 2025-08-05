package com.tata.demo.infrastructure.servicesimpl;

import com.tata.demo.application.dto.PersonClientDto;
import com.tata.demo.application.struct.PersonClientMapper;
import com.tata.demo.application.struct.PersonMapper;
import com.tata.demo.application.utils.BasicResponse;
import com.tata.demo.application.utils.ObjectsResponse;
import com.tata.demo.core.model.PersonClientModel;
import com.tata.demo.core.services.ClientCRUDService;
import com.tata.demo.infrastructure.entities.ClientEntity;
import com.tata.demo.infrastructure.entities.PersonEntity;
import com.tata.demo.infrastructure.repositories.ClientRepository;
import com.tata.demo.infrastructure.repositories.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ClientCRUDServiceImpl<T> implements ClientCRUDService {

    @Autowired
    private ClientRepository clRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonClientMapper perClMapper;

    @Override
    public T findByDNI(String dni) {
        Optional<ClientEntity> clEnt = Optional.empty();

        try {
            clEnt = clRepository.findByClientDni(dni);
            if (clEnt.isEmpty()) {
                return (T) BasicResponse.builder()
                        .code(404)
                        .status("Not found")
                        .message("Registro no encontrado...")
                        .build();
            }
            return (T) ObjectsResponse.builder()
                    .code(200)
                    .status("Ok")
                    .message("Registro encontrado...")
                    .object(perClMapper.entityToDto(clEnt.get()))
                    .build();
        } catch (Exception e) {
            log.error("Error al buscar registro...");
            return (T) BasicResponse.builder()
                    .code(400)
                    .status("Error")
                    .message("Error al buscar registro...")
                    .build();
        }


    }

    @Override
    public T save(PersonClientDto model) {

        Optional<ClientEntity> clEntity = Optional.empty();

        try {
            clEntity = Optional.ofNullable(ClientEntity.builder()
                        .dni(model.getDni())
                        .names(model.getNames())
                        .surnames(model.getSurnames())
                        .birthday(model.getBirthday())
                        .address(model.getAddress())
                        .phone(model.getPhone())
                        .gender(model.getGender())
                        .password(model.getPassword())
                        .state(model.getState())
                        .build());

            clEntity = Optional.of(clRepository.save(clEntity.get()));
                return  (T) ObjectsResponse.builder()
                        .code(201)
                        .status("Created")
                        .message("Registro creado correctamente...")
                        .object(personMapper.entityToDto(clEntity.get()))
                        .build();

        } catch (Exception e) {
            log.error("Error al crear registro...");
            return (T) BasicResponse.builder()
                    .code(401)
                    .status("Error")
                    .message("Error al crear registro...")
                    .build();
        }


    }

    @Override
    public T update(PersonClientModel model) {
        Optional<ClientEntity> clEntity = Optional.empty();
         try {
             clEntity = clRepository.findById(model.getId());

             if (clEntity.isEmpty()) {
                 log.error("Error al actualizar registro...");
                 return (T) BasicResponse.builder()
                         .code(404)
                         .status("Not found")
                         .message("Cliente no encontrado...")
                         .build();
             }
             clEntity.get().setNames(model.getNames());
             clEntity.get().setDni(model.getDni());
             clEntity.get().setSurnames(model.getSurnames());
             clEntity.get().setBirthday(model.getBirthday());
             clEntity.get().setAddress(model.getAddress());
             clEntity.get().setPhone(model.getPhone());
             clEntity.get().setGender(model.getGender());
             clEntity.get().setPassword(model.getPassword());
             clEntity.get().setState(model.getState());

             clRepository.save(clEntity.get());

             return  (T) ObjectsResponse.builder()
                     .code(201)
                     .status("Created")
                     .message("Registro creado correctamente...")
                     .object(personMapper.entityToDto(clEntity.get()))
                     .build();

         } catch (Exception e) {
             log.error("Error al actualizar registro...");
             return (T) BasicResponse.builder()
                     .code(400)
                     .status("Error")
                     .message("Error al actualizar registro...")
                     .build();
         }
    }

    @Override
    public T deleteById(Long id) {
        Optional<ClientEntity> clEntity = Optional.empty();

        try {
            clEntity = clRepository.findById(id);
            if (clEntity.isEmpty()) {
                log.error("Error al actualizar registro...");
                return (T) BasicResponse.builder()
                        .code(404)
                        .status("Not found")
                        .message("Cliente no encontrado...")
                        .build();
            }

            clEntity.get().setState(Boolean.FALSE);
            clRepository.save(clEntity.get());
            return (T) BasicResponse.builder()
                    .code(200)
                    .status("Ok")
                    .message("Cliente eliminado...")
                    .build();

        } catch (Exception e) {
            log.error("Error al actualizar registro...");
            return (T) BasicResponse.builder()
                    .code(400)
                    .status("Error")
                    .message("Error al actualizar registro...")
                    .build();
        }
    }
}

package com.mateo.ec.miprimerapirest.controller;

import com.mateo.ec.miprimerapirest.model.dto.ClientDTO;
import com.mateo.ec.miprimerapirest.model.entity.Client;
import com.mateo.ec.miprimerapirest.model.payload.ResponseMessage;
import com.mateo.ec.miprimerapirest.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("clients")
    public ResponseEntity<?> showAll(){
        List<Client> getList = clientService.listAll();

        if(getList == null){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }else{
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message("")
                            .object(getList)
                            .build()
                    , HttpStatus.OK);
        }
    }

    @PostMapping("client")
    public ResponseEntity<?> create(@RequestBody ClientDTO clientDto){
        Client clientSave;
        try {
            clientSave= clientService.save(clientDto);
            clientDto = ClientDTO.builder()
                    .idCliente(clientSave.getIdCliente())
                    .nombre(clientSave.getNombre())
                    .apellido(clientSave.getApellido())
                    .correo(clientSave.getCorreo())
                    .fechaRegistro(clientSave.getFechaRegistro())
                    .build();

            return new ResponseEntity<>(
                    ResponseMessage.builder()
                    .message("Guardado correctamente")
                    .object(clientDto)
                    .build(), HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("client/{id}")
    public ResponseEntity<?> update(@RequestBody ClientDTO clientDto, @PathVariable Integer id){
        Client clientUpdate;
        try {
            if(clientService.existById(id)){
                clientDto.setIdCliente(id);
                clientUpdate = clientService.save(clientDto);
                clientDto = ClientDTO.builder()
                        .idCliente(clientUpdate.getIdCliente())
                        .nombre(clientUpdate.getNombre())
                        .apellido(clientUpdate.getApellido())
                        .correo(clientUpdate.getCorreo())
                        .fechaRegistro(clientUpdate.getFechaRegistro())
                        .build();
                return new ResponseEntity<>(
                        ResponseMessage.builder()
                                .message("Acualizado correctamente")
                                .object(clientDto)
                                .build(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(
                        ResponseMessage.builder()
                                .message("No se encontró el cliente")
                                .object(null)
                                .build(), HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Client clientDelete = clientService.findById(id);
            clientService.delete(clientDelete);
            return new ResponseEntity<>(clientDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                    .message(exDt.getMessage())
                    .object(null)
                    .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Client client = clientService.findById(id);

        if(client == null){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message("El registro que intenta buscar no existe")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message("")
                            .object(ClientDTO.builder()
                                    .idCliente(client.getIdCliente())
                                    .nombre(client.getNombre())
                                    .apellido(client.getApellido())
                                    .correo(client.getCorreo())
                                    .fechaRegistro(client.getFechaRegistro())
                                    .build())
                            .build()
                    , HttpStatus.OK);
        }
    }
}

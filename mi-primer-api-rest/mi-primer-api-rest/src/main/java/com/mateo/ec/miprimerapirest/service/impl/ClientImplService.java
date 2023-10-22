package com.mateo.ec.miprimerapirest.service.impl;

import com.mateo.ec.miprimerapirest.model.dao.ClientDAO;
import com.mateo.ec.miprimerapirest.model.dto.ClientDTO;
import com.mateo.ec.miprimerapirest.model.entity.Client;
import com.mateo.ec.miprimerapirest.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientImplService implements IClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Override
    public List<Client> listAll() {
        return (List) clientDAO.findAll();
    }

    @Transactional
    @Override
    public Client save(ClientDTO clientDto) {
        Client client = Client.builder()
                .idCliente(clientDto.getIdCliente())
                .nombre(clientDto.getNombre())
                .apellido(clientDto.getApellido())
                .correo(clientDto.getCorreo())
                .fechaRegistro(clientDto.getFechaRegistro())
                .build();
        return clientDAO.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Integer id) {
        return clientDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientDAO.delete(client);
    }

    @Override
    public boolean existById(Integer id) {
        return clientDAO.existsById(id);
    }
}

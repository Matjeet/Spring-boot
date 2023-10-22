package com.mateo.ec.miprimerapirest.service;

import com.mateo.ec.miprimerapirest.model.dto.ClientDTO;
import com.mateo.ec.miprimerapirest.model.entity.Client;

import java.util.List;

public interface IClientService {

    List<Client> listAll();
    Client save(ClientDTO client); //Guarda y actualiza
    Client findById(Integer id);
    void delete(Client client);
    boolean existById(Integer id);
}

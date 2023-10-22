package com.mateo.ec.miprimerapirest.model.dao;

import com.mateo.ec.miprimerapirest.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDAO extends CrudRepository<Client, Integer> {
}

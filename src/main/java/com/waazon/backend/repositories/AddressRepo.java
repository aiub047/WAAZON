package com.waazon.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waazon.backend.domains.Address;

@Repository
public interface AddressRepo extends CrudRepository<Address,Long> {

}

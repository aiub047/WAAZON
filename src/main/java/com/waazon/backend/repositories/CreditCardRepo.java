package com.waazon.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.waazon.backend.domains.CreditCard;

public interface CreditCardRepo extends CrudRepository<CreditCard,Long> {
}

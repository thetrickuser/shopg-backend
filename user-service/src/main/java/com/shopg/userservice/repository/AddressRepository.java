package com.shopg.userservice.repository;

import com.shopg.userservice.entity.Address;
import com.shopg.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByUserId(User user);
}

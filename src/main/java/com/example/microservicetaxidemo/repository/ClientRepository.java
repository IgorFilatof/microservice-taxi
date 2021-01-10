package com.example.microservicetaxidemo.repository;

import com.example.microservicetaxidemo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

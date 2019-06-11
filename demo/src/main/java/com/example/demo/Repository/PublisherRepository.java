package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}

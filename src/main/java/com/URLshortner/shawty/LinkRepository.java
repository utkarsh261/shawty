package com.URLshortner.shawty;
import com.URLshortner.shawty.Link;

import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LinkRepository extends CrudRepository<Link, Integer> {
    boolean existsByK(String foo);
    Link findByK(String foo);
}
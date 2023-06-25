package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.springboot.model.PriceRecord;

@Repository
public interface PriceRespository extends JpaRepository<PriceRecord, Integer> {

    // custom query to search to blog post by title or content
    List<PriceRecord> findByISINContainingOrDateContaining(String text, String textAgain);   
    List<PriceRecord> findByISINContainingAndDateContaining(String text, String textAgain);
    void deleteById(Integer id);
    
}
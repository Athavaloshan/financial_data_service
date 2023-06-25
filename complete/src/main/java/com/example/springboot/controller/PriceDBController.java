package com.example.springboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import java.lang.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.springboot.repository.PriceRespository;
import com.example.springboot.model.PriceRecord;


@RestController
@RequestMapping("/api")
public class PriceDBController {

    @Autowired
    PriceRespository PriceRespository;

    private Integer key = 10;
    
	@GetMapping("/")
	public String index() {
		return "Greetings from price controller!";
	}

    @PostMapping(value="/search/ISINOrDate", consumes={"application/json"})
    public List<PriceRecord> searchByISINOrDate(String ISIN, String date){
        return PriceRespository.findByISINContainingOrDateContaining(ISIN, date);
    }

    @PostMapping(value="/search/ISINAndDate", consumes={"application/json"})
    public List<PriceRecord> searchByISINAndDate(String ISIN, String date){
        return PriceRespository.findByISINContainingAndDateContaining(ISIN, date);
    }

    @PostMapping("/write")
    public ResponseEntity<String> addData(@RequestBody PriceRecord priceRecord){
        PriceRespository.save(priceRecord);
        return new ResponseEntity<>("Write Successful", HttpStatus.OK);
    }

    @PostMapping("/delete/id")
    public void deleteDataById(@RequestParam Integer id){
        try{
            PriceRespository.deleteById(id);
        } catch (Exception ex) {
            System.out.println("Error: There is no record with id " + id);
        }
    }
}
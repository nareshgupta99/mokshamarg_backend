package com.example.MokshaMarg.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.example.MokshaMarg.service.FoodTypeService;

import jakarta.annotation.PostConstruct;

@Service
public class DataLoader implements ApplicationRunner {

    @Autowired
    private FoodTypeService foodTypeService;


    @Override
    public void run(ApplicationArguments args) {
        foodTypeService.saveAllFoodType();
    }

}

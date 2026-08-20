package com.jewellery.backend.controller;

import com.jewellery.backend.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired 
    private DataImportService dataImportService;

    @PostMapping("/api/admin/import-products")
    public String importProducts() {
        System.out.println("CONTROLLER HIT!"); // this will prove it's working
        return dataImportService.importProducts();
    }
}
package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/table")
@RestController
public class TableController {
    @Autowired
    private IService<Table> tableService;

    @GetMapping("/allCustomers")
    public List<Table> getAllTables() {
        return tableService.getAllElements();
    }

    @PostMapping
    public Table createTable(@RequestBody Table table) {
        return tableService.createElement(table);
    }

    @PutMapping("/{id}")
    public Table updateTable(@PathVariable String id, @RequestBody Table tableDetails) {
        return tableService.updateElement(id, tableDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable String id) {
        tableService.deleteElement(id);
    }
}

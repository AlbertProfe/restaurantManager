package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/table")
@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping("/tables")
    public List<Table> getAllTables(){
        return tableService.getAllTables();
    }

    @PostMapping
    public Table createTable(Table table){
        return tableService.createTable(table);
    }

    @PutMapping("/{id}")
    public Table updateTable(@PathVariable String id,Table tableDetails){
        return tableService.updateTable(id,tableDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable String id){
        tableService.deleteTable(id);
    }


    @GetMapping("/{id}")
    public Table getTableById(@PathVariable String id){
        return tableService.getTableById(id);
    }
}

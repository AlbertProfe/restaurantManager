package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.service.TableService;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/table")
@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    // manage request by ResponseEntity with all customers
    @GetMapping("/allTables")
    public ResponseEntity<List<Table>> getAllTables( ) {
        List<Table> tables = tableService.getAllTables();
        HttpHeaders headers = getCommonHeaders("Get all tables");
        return tables != null && !tables.isEmpty()
                ? new ResponseEntity<>(tables, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Table> createTable(@RequestBody Table table) {
        Table createdTable = tableService.createTable(table);
        HttpHeaders headers = getCommonHeaders("Create a new table");

        return createdTable != null
                ? new ResponseEntity<>(createdtable, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Table> updateTable(@PathVariable String id, @RequestBody Table tableDetails) {
        Customer updatedTable = tableService.updateTable(id, tableDetails);
        HttpHeaders headers = getCommonHeaders("Update a table");

        return updatedTable != null
                ? new ResponseEntity<>(updatedTable, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable String id) {
        boolean deleted = tableService.deleteTable(id);
        HttpHeaders headers = getCommonHeaders("Delete a table");
        headers.add("deleted", String.valueOf(deleted));


        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);
        HttpHeaders headers = getCommonHeaders("Get a customer by Id");

        return customer != null
                ? new ResponseEntity<>(customer, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }
    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("customer-count", String.valueOf(customerService.countCustomers()));
        headers.add("object", "customers");
        return headers;
    }

# PRA01

## Things Done

- [x] Update Entity Classes.
 
    > Added **Id** in both of them. Solved problem on DB creation of Table "Table" with annotation @jakarta.persistence.Table(name = "RestaurantTable" )

- [x] Create Faker Classes
- [x] Develop Service Layer
- [x] Implement JPA Repositories
- [x] Design REST Controllers
- [x] Test with Swagger API

## Test with Swagger

All test done by hand

### **Table** REST API

| Status | Type | URL                                                |   
|--------|------|----------------------------------------------------|
| OK     | GET  | /api/v1/table/59834b18-4d4c-4983-82e6-495a10c5c07f |
| OK     | GET  | /api/v1/table/non-existent-id                      |
| OK     | GET  | /api/v1/table/tables                               |
| OK     | POST | /api/v1/table/                                     |
| OK     | PUT  | /api/v1/table/                                     |
| OK     | DEL  | /api/v1/table/non-existent-id                      |
| OK     | DEL  | /api/v1/table/59834b18-4d4c-4983-82e6-495a10c5c07f |


### **Menu** REST API

| Status | Type | URL                                               |
|--------|------|---------------------------------------------------|
| OK     | GET  | /api/v1/menu/81ee4c87-d3c2-42e9-91f7-c8835a165da2 |
| OK     | GET  | /api/v1/menu/non-existent-id                      |
| OK     | GET  | /api/v1/menu/menus                                |
| OK     | POST | /api/v1/menu/                                     |
| OK     | PUT  | /api/v1/menu/                                     |
| OK     | DEL  | /api/v1/menu/non-existent-id                      |
| OK     | DEL  | /api/v1/menu/81ee4c87-d3c2-42e9-91f7-c8835a165da2 |

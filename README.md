### Preparing

Configure application.properties file.
- Open folder `src/main/resources/`
- Rename file `application.properties_example` to `application.properties`
- Change value in `application.properties` file.

### GetAll Employees

**Endpoint:** GET `http://<your_host>:<your_port>/api/v1/employees/all`

**Description:**
This endpoint retrieves all employees from the system.

**Parameters:**
- `sortedBy=<Sorted value Key>` optional, string. Sorted value key. Allowed values: ['id', 'employeeId', 'firstName', 'lastName', 'email', 'phoneNumber', 'position', 'isWork']. Default value: 'id'.
- `page=<Page number>` optional, integer number. Min value 1 or greater Default value: 1.
- `size=<One page size>` optional, integer number. Min value greater or equal 10, max value lees or equal 100.  Default value: 10.
- `direction=<Sort direction>` optional, string. Sort direction. Allowed values: ['asc', 'desc']. Default value: 'desc'.

**Response:**
- `200 OK`: Successfully retrieved all customers.
- `400 BAD_REQUEST`: Failed to retrieve data due to bad request.
- `409 CONFLICT`: Failed to retrieve data due to conflict.
- `500 Internal Server Error`: Failed to retrieve data due to an internal server error.

### Search Employees

**Endpoint:** GET `http://<your_host>:<your_port>/api/v1/employees/search`

**Description:**
This endpoint retrieves all employees from the system by search value.

**Parameters(Query):**
- `valueData=<Value string>` required, string. Min size 1 character or greater, max size 256 character.
- `searchBy=<Search value Key>` required, string. Search value key. Allowed values: ['employeeId', 'firstName', 'lastName', 'email', 'phoneNumber', 'position', 'isWork'].
- `page=<Page number>` optional, integer number. Min value 1 or greater Default value: 1.
- `size=<One page size>` optional, integer number. Min value greater or equal 10, max value lees or equal 100.  Default value: 10.
- `direction=<Sort direction>` optional, string. Sort direction. Allowed values: ['asc', 'desc']. Default value: 'desc'.

**Response:**
- `200 OK`: Successfully search employees.
- `400 BAD_REQUEST`: Failed to retrieve data due to bad request.
- `409 CONFLICT`: Failed to retrieve data due to conflict.
- `500 Internal Server Error`: Failed to retrieve data due to an internal server error.

### Create employee

**Endpoint:** POST `http://<your_host>:<your_port>/api/v1/employees/create`

**Description:**
This endpoint creates a new employee in the system.

**Body:**
```json
{
    "firstName": "required, string. Customer first name",
    "lastName": "required, string. Customer last name",
    "email": "required, string. Customer email",
    "phoneNumber": "required, string. Customer phone number. E.164 format",
    "position": "required, string. Employee position",
    "isWork": "required, boolean. Employee work status"
}
```

**Response:**
- `201 Create`: Successfully create employee.
- `409 Conflict`: Failed to create employee due to conflict.
- `500 Internal Server Error`: Failed to create employee due to an internal server error.

### Update Customer

**Endpoint:** PUT `http://<your_host>:<your_port>/api/v1/employees/update?id=<customer_id>`

**Description:**
This endpoint update exists employee in the system.

**Parameters(Query):**
- `id=<customer_id>` required, number. Customer id.

**Body:**
```json
{
    "firstName": "optional, string. Customer first name",
    "lastName": "optional, string. Customer last name",
    "email": "optional, string. Customer email",
    "phoneNumber": "optional, string. Customer phone number. E.164 format",
    "position": "optional, string. Employee position",
    "isWork": "optional, boolean. Employee work status"
}
```

**Response:**
- `202 Accepted`: Successfully update employee.
- `400 Bad Request`: Failed to update employee due to bad request.
- `409 Conflict`: Failed to update employee due to conflict.
- `404 Not Found`: Failed to update employee due to not found.
- `500 Internal Server Error`: Failed to update employee due to an internal server error.

### Delete Employee

**Endpoint:** DELETE `http://<your_host>:<your_port>/api/v1/employees/delete?id=<customer_id>`

**Description:**
This endpoint delete exists employee in the system.

**Parameters(Query):**
- `id=<customer_id>` required, number. Employee id.

**Response:**
- `204 NO_CONTENT`: Successfully delete employee.
- `400 Bad Request`: Failed to delete employee due to bad request.
- `409 Conflict`: Failed to delete employee due to conflict.
- `404 Not Found`: Failed to delete employee due to not found.
- `500 Internal Server Error`: Failed to delete employee due to an internal server error.
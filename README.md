# camunda-demo
Small demonstration on Camunda

# To create a booking
HTTP Request
```http
POST /booking HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 159

{
    "firstName" : "Madhawa",
    "lastname" : "Gunasekara",
    "email" : "sk.gunasekara@gmail.com",
    "countryFrom" : "Sri Lanka",
    "countryTo" : "Germany",
    "date" : "2019-01-27"
}
```
# To confirm booking
HTTP Request
```http
GET /booking-confirmation/08d8e394-9595-11ec-b8f0-9cb6d091b089 HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded
IRU_SESSION: Marc_12345
Content-Length: 217
```

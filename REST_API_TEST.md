Test methods with Postman

Get meals by id, method GET:
http://localhost:8080/topjava/rest/meals/get/100003

Delete meals by id, method DELETE:
http://localhost:8080/topjava/rest/meals/delete/100003

Get all, method GET:
http://localhost:8080/topjava/rest/meals

Get between datetime, method GET:
http://localhost:8080/topjava/rest/meals/between?startDate=2020-01-30&startTime=01:00&endDate=2020-01-30&endTime=23:59

Update meal, method PUT:
http://localhost:8080/topjava/rest/meals/update/100003

Create meal, method POST:
http://localhost:8080/topjava/rest/meals/create
Test methods with command line #curl:

Get meals by id, method GET:
curl -v http://localhost:8080/topjava/rest/meals/100003

Get all, method GET:
curl -v http://localhost:8080/topjava/rest/meals

Delete meals by id, method DELETE:
curl -X DELETE http://localhost:8080/topjava/rest/meals/100003

Get between datetime, method GET:
curl -v "http://localhost:8080/topjava/rest/meals/between?startDate=2020-01-30&startTime=01:00&endDate=2020-01-30&endTime=23:59"

Update meal, method PUT:
curl -X PUT -d '{"dateTime":"2020-01-30T10:00:00","description":"Завтрак обновлен","calories":500}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/meals/100005

Create meal, method POST:
curl -X POST -d '{"dateTime":"2020-07-01T12:00","description":"Новая еда","calories":300}' -H 'contentType:'application/json'' http://localhost:8080/topjava/rest/meals

Test methods with Postman

Get meals by id, method GET:
http://localhost:8080/topjava/rest/meals/100003

Delete meals by id, method DELETE:
http://localhost:8080/topjava/rest/meals/100003

Get all, method GET:
http://localhost:8080/topjava/rest/meals

Get between datetime, method GET:
http://localhost:8080/topjava/rest/meals/between?startDate=2020-01-30&startTime=01:00&endDate=2020-01-30&endTime=23:59

Update meal, method PUT:
http://localhost:8080/topjava/rest/meals/100003

Create meal, method POST:
http://localhost:8080/topjava/rest/meals
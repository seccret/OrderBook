# OrderBook
A simple order book microservice.

### Set up mysql
In resource/application.properties update username and password to your mysql user:
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD

#### Create MySQL db
mysql –u USER -p
write your password when prompted
CREATE DATABASE order_db;

### Run server
mvn spring-boot:run

### Example curl
//curl http://localhost:8080/get-order?id=1
//curl -X POST -H "Content-Type: application/json" http://localhost:8080/create-order -d '{"ticker":"SAVE", "orderSide":"BUY", "price": 32.3, "currency": "SEK", "volume": 2}'

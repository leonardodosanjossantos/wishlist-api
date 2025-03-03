# Wishlist API

<div>

![Action Status](https://img.shields.io/badge/build-passing-ff69b4)
![Action Status](https://img.shields.io/badge/test-passing-blueviolet)
![Action Status](https://img.shields.io/badge/coverage-100%25-ff69b4)
![Dependencies Status](https://img.shields.io/badge/dependencies-up%20to%20date-blueviolet.svg)

</div>

## Tools

![Action Status](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Action Status](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Action Status](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)

## Requirements
* [JAVA 17+](https://www.java.com)
* [Maven 3.8+](https://maven.apache.org)

## Getting started
```bash
  https://github.com/leonardodosanjossantos/wishlist-api.git
  cd wishlist-api
```

## Build
```bash
  mvn clean install
```

## Swagger
```bash
  http://localhost:8080/swagger-ui/index.html
```

## Testing

### Add a product
#### POST /api/wishlist/{customerId}/products
```bash

curl -X POST "http://localhost:8080/api/wishlist/1234/add" \
     -H "Content-Type: application/json" \
     -d '{
           "id": "p1",
           "name": "Product 1",
           "price": 99.99
         }'
```

### Remove a product from the Wishlist
#### DELETE /api/wishlist/{customerId}/remove?productId=p1
```bash
  
curl -X DELETE "http://localhost:8080/api/wishlist/1234/remove?productId=p1"
```

### Search for Customer Wishlist
#### GET /api/wishlist/{customerId}
```bash
  
curl -X GET "http://localhost:8080/api/wishlist/1234" \
     -H "Accept: application/json"

```

### Verify a product exists in a Wishlist
#### GET /api/wishlist/check/{customerId}/{productId}
```bash
  
curl -X GET "http://localhost:8080/api/wishlist/check/1234/p1"

```
# ez-fastfood-user-ms
ez-fastfood-user-ms




## mongodb - container

docker run -d --name mongodb  -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=secret mongo:latest

docker exec -it mongodb mongosh -u admin -p secret

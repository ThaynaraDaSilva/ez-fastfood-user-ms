db = db.getSiblingDB('ez-fastfood');

db.createCollection('users');

db.users.insertMany([
    {
        "name": "João Cliente",
        "email": "joao@cliente.com",
        "password": "123456",
        "userType": "CLIENTE"
    },
    {
        "name": "Maria Funcionária da Silva",
        "email": "maria@teste.com",
        "password": "654321",
        "userType": "FUNCIONARIO"
    }
]);

db = db.getSiblingDB('ez_fastfood_user');

db.createCollection('user');

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

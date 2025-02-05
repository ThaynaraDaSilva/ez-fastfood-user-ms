db = db.getSiblingDB('ez-fastfood');

db.createCollection('users');

db.users.insertMany([
    {
        "nome": "João Cliente",
        "email": "joao@cliente.com",
        "senha": "123456",
        "tipo": "CLIENTE"
    },
    {
        "nome": "Maria Funcionária",
        "email": "maria@empresa.com",
        "senha": "654321",
        "tipo": "FUNCIONARIO"
    }
]);

Dropwizard User API Example
================================

----------------------
Usage
----------------------

Create database tables
-------------------------
```
    $mvn clean compile flyway:migrate
```

Generate jar Package
-------------------------
```
    $mvn clean install package
```

Create admin user
---------------------

```
  $java -jar target/user-rest-1.0-SNAPSHOT.jar create_user config.yml
    Creating user:

    type your username: maigfrga

   type your email: maigfrga@gmail.com

    User successfully created:

    {"id":1,"email":"maigfrga@gmail.com",
        "username":"maigfrga","token":"e1d04578-88f0-45a5-8bd2-456c061113e9","active":false,"random_uuid":"8e948641-a71f-4bd9-90a6-92302300556e"}
```

List all admin users
--------------------

```
    $java -jar target/user-rest-1.0-SNAPSHOT.jar list_users config.yml
    Admin User List 
    [{"id":2,"email":"maigfrga@gmail.com","username":"maigfrga@gmail.com","token":"166611c0-8f7e-4e1f-8b6b-5e6f420ea124","active":false}]
```


Start App
-------------------------
```
    $java -jar target/user-rest-1.0-SNAPSHOT.jar server config.yml 
```


API REST Authentication
------------------------

Basic Authentication is required, using user token as password, remember if you use curl to query the rest, you must
pass credential base64 encoded, by example if the username is maigfrga and token 1986e220-90c6-4839-b1b5-f766f4dd519f
you must encode the string maigfrga:, the encoding will generate the string     bWFpZ2ZyZ2E6MTk4NmUyMjAtOTBjNi00ODM5LWIxYjUtZjc2NmY0ZGQ1MTlm



Getting a list of persons
------------------------------------

```
    curl -v -X GET localhost:8888/persons  -H 'Authorization: Basic bWFpZ2ZyZ2E6MTk4NmUyMjAtOTBjNi00ODM5LWIxYjUtZjc2NmY0ZGQ1MTlm'
```
Will return:

```
[{"id":1,"email":"user1@mail.com","username":"user_test1","token":null,"first_name":"first name common","last_name":"last name common","active":false},{"id":2,"email":"user2@mail.com","username":"user_test2","token":null,"first_name":"first name common","last_name":"last name common","active":false},{"id":3,"email":"user3@mail.com","username":"user_test3","token":null,"first_name":"first name common","last_name":"last name common","active":false},{"id":4,"email":"user3@mail.com","username":"user_test3","token":"7956d2ae-6b5b-4656-8049-667e9acd8f17","first_name":"first name common","last_name":"last name common","active":true}]
```


Getting a person by id
---------------------------

```
    curl -v -X GET localhost:8888/persons/1  -H 'Authorization: Basic bWFpZ2ZyZ2E6MTk4NmUyMjAtOTBjNi00ODM5LWIxYjUtZjc2NmY0ZGQ1MTlm'
```


Will return:

```
  {"id":1,"email":"user1@mail.com","username":"user_test1","token":null,"first_name":"first name common","last_name":"last name common","active":false}
```


Creating a person
-----------------------

```
    curl -v -X POST localhost:8888/persons  -H 'Authorization: Basic bWFpZ2ZyZ2E6MTk4NmUyMjAtOTBjNi00ODM5LWIxYjUtZjc2NmY0ZGQ1MTlm'  -H 'Content-Type: application/json' -d '{"email": "user3@mail.com", "username": "user_test3", "first_name": "first name common", "last_name": "last name common"}'
```

Will return:
```
    {"person":{"id":4,"email":"user3@mail.com","username":"user_test3","token":"7956d2ae-6b5b-4656-8049-667e9acd8f17","first_name":"first name common","last_name":"last name common","active":true}}
```


Updating a person
-----------------------

```
    curl -v -X PUT localhost:8888/persons/1  -H 'Authorization: Basic bWFpZ2ZyZ2E6MTk4NmUyMjAtOTBjNi00ODM5LWIxYjUtZjc2NmY0ZGQ1MTlm'  -H 'Content-Type: application/json' -d '{"email": "user3@mail.com", "username": "user_test3", "first_name": "first name common updated", "last_name": "last name common update","active":true}'
```

Will return:

```
  {"id":5,"email":"user3@mail.com","username":"user_test3","token":null,"first_name":"first name common updated","last_name":"last name common update","active":true}
```


Deleting a person
-----------------------

```
    curl -v -X DELETE localhost:8888/persons/1  -H 'Authorization: Basic bWFpZ2ZyZ2E6MTk4NmUyMjAtOTBjNi00ODM5LWIxYjUtZjc2NmY0ZGQ1MTlm'
```

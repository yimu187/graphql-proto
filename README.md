GraphQL prototype project.

Simply run the project , add data with addPerson post request and send the graph queries below

localhost:8070/addPerson
{
"name": "Murat",
"surname": "YILMAZ",
"email": "yimu187@gmail.com"
}

localhost:8070/findAll
{
findAllPerson {
name
surname
email
    }
}


localhost:8070/findPersonByEmail
{
findPerson(email:"yimu187@gmail.com"){
id,
name
surname
}
}

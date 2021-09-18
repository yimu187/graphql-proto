package tech.yilmaz.graphqlproto.contoller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.yilmaz.graphqlproto.model.Person;
import tech.yilmaz.graphqlproto.serivice.PersoneService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersoneService personeService;

    @Value("classpath:person.graphqls")
    private Resource schemaResource;

    private GraphQL graphQL;

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {

        DataFetcher<List<Person>> fetcher1 = data -> personeService.findAll();

        DataFetcher<Person> fetcher2 = data -> personeService.findByEmail(data.getArgument("email"));

        return RuntimeWiring.newRuntimeWiring().type("Query",
                typeWriting -> typeWriting.dataFetcher("findAllPerson", fetcher1).dataFetcher("findPerson", fetcher2))
                .build();
    }

    @PostMapping("/findAll")
    public ResponseEntity<Object> findAll(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/findPersonByEmail")
    public ResponseEntity<Object> findPersonByEmail(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @PostMapping("addPerson")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        person = personeService.savePerson(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

}

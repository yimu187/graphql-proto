package tech.yilmaz.graphqlproto.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "PERSON")
@Table(schema = "GRAPHQL", name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;
}

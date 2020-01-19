package com.orest.rest_recap.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController
{





    @GetMapping(value = "v1/person", params = "version=1")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
    }

 // person/param?version=2
    @GetMapping(value = "v2/person", params = "version=2")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }





    // jako header podac X-API i wartosc 1
    @GetMapping(value = "v1/header", headers = "X-API-VERSION=1")
    public PersonV1 personV1Header() {
        return new PersonV1("Bob Charlie");
    }


    @GetMapping(value = "v2/header", headers = "X-API-VERSION=2")
    public PersonV1 personV1Headerv2() {
        return new PersonV1("Bob ");
    }




    @GetMapping(value = "v2/header", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personProducesv1() {
        return new PersonV1("Bob ");
    }


    // Accept -> value -> application/vnd.company.app-v2+json
    // MIME type versioning
    @GetMapping(value = "v2/header", produces = "application/vnd.company.app-v2+json")
    public PersonV1 personProducesv2() {
        return new PersonV1("Bob ");
    }







}




























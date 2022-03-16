package org.systems.dipe.srs.platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.systems.dipe.srs.platform.external.PeopleFacade;
import org.systems.dipe.srs.platform.people.PersonInDto;
import org.systems.dipe.srs.platform.people.PersonOutDto;
import org.systems.dipe.srs.platform.people.RoleDto;

import java.util.List;

@RestController
@AllArgsConstructor
public class PeopleController {

    // TODO config controller advice
    // TODO config validation step

    private final PeopleFacade peopleFacade;

    @PostMapping("/api/person")
    public PersonOutDto register(@RequestBody PersonInDto person) {
        return peopleFacade.create(person);
    }

    @GetMapping("/api/person/roles")
    public List<RoleDto> rolesDictionary() {
        return peopleFacade.roles();
    }

}

package org.systems.dipe.srs.platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.systems.dipe.srs.platform.external.PeopleFacade;
import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;

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
    public List<RoleOutDto> rolesDictionary() {
        return peopleFacade.roles();
    }

}

package com.jpa.examples.location.town;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/town")
public class TownController {

    private final TownService townService;

    public TownController(TownService townService) {
        this.townService = townService;
    }

    @GetMapping
    public Iterable<TownResponse> getAll() {
        return townService.findAll();
    }

    @GetMapping(value="/{id}")
    public TownResponse findById(@PathVariable("id") long id) {
        return townService.findById(id);
    }
}

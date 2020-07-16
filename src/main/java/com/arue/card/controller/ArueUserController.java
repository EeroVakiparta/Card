package com.arue.card.controller;

import com.arue.card.model.ArueUser;
import com.arue.card.repositories.ArueUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/arueuser")
public class ArueUserController {

    @Autowired
    private ArueUserRepository arueUserRepository;

    @GetMapping
    public List<ArueUser> list(){
        return arueUserRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ArueUser get(@PathVariable Long id) {
        return arueUserRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArueUser create(@RequestBody final ArueUser arueUser) {
        return arueUserRepository.saveAndFlush(arueUser);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) //request http delete
    public void delete(@PathVariable Long id) {
        //cascade? cards, works only with lonely arueuser with no cards
        arueUserRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ArueUser update(@PathVariable Long id, @RequestBody ArueUser newArueUser) {
        //want all attributes because of put, put PATCH on game parts
        //maybe should validate all, otherwise return 400
        ArueUser oldArueUser = arueUserRepository.getOne(id);
        BeanUtils.copyProperties(newArueUser,oldArueUser,"arueuser_id");
        return arueUserRepository.saveAndFlush(oldArueUser);
    }
}

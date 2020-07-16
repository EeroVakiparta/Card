package com.arue.card.controller;

import com.arue.card.model.Card;
import com.arue.card.model.Card;
import com.arue.card.repositories.CardRepository;
import com.arue.card.repositories.CardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/card")
@RestController
public class CardController {
    
    @Autowired
    private CardRepository cardRepository;

    @GetMapping
    public List<Card> list(){
        return cardRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Card get(@PathVariable Long id) {
        return cardRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Card create(@RequestBody final Card card) {
        return cardRepository.saveAndFlush(card);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) //request http delete
    public void delete(@PathVariable Long id) {
        cardRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Card update(@PathVariable Long id, @RequestBody Card newCard) {
        Card oldCard = cardRepository.getOne(id);
        BeanUtils.copyProperties(newCard,oldCard,"card_id");
        return cardRepository.saveAndFlush(oldCard);
    }
}

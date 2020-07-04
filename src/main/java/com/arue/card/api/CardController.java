package com.arue.card.api;

import com.arue.card.model.Card;
import com.arue.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/card")
@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public void addCard(@Valid @NotNull @RequestBody Card card) {
        cardService.addCard(card);
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping(path = "{id}")
    public Card getCardById(@PathVariable("id") UUID id) {
        return cardService.getCardById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCardById(@PathVariable("id") UUID uuid) {
        cardService.deleteCardById(uuid);
    }

    @PutMapping(path = "{id}")
    public void updateCardById(@PathVariable("id") UUID id,@Valid @NotNull @RequestBody Card card) {
        cardService.updateCard(id, card);
    }
}

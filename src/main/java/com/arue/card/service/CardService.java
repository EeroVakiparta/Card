package com.arue.card.service;


import com.arue.card.dao.CardDao;
import com.arue.card.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    private final CardDao cardDao;

    @Autowired
    public CardService(@Qualifier("postgres") CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public int addCard(Card card) {
        return cardDao.insertCard(card);
    }

    public List<Card> getAllCards() {
        return cardDao.selectAllCards();
    }

    public Optional<Card> getCardById(UUID id) {
        return cardDao.selectCardById(id);
    }

    public int deleteCardById(UUID id) {
        return cardDao.deleteCardById(id);
    }

    public int updateCard(UUID id, Card card) {
        return cardDao.updateCardById(id, card);
    }

}

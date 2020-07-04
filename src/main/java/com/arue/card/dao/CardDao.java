package com.arue.card.dao;

import com.arue.card.model.Card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardDao {

    int insertCard(UUID id, Card card);

    default int insertCard(Card card) {
        UUID id = UUID.randomUUID();
        return insertCard(id, card);
    }

    List<Card> selectAllCards();

    Optional<Card> selectCardById(UUID uuid);

    int deleteCardById(UUID uuid);

    int updateCardById(UUID uuid, Card card);
}

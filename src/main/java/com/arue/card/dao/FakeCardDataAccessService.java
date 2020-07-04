package com.arue.card.dao;

import com.arue.card.model.Card;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeCardDataAccessService implements CardDao {

    private static List<Card> DB = new ArrayList<>();

    @Override
    public int insertCard(UUID id, Card card) {
        DB.add(new Card(id, card.getName()));
        return 1;
    }

    @Override
    public List<Card> selectAllCards() {
        return DB;
    }

    @Override
    public Optional<Card> selectCardById(UUID id) {
        return DB.stream()
                .filter(card -> card.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteCardById(UUID uuid) {
        Optional<Card> card = selectCardById(uuid);
        if (card.isEmpty()) {
            return 0;
        }
        DB.remove(card.get());
        return 1;
    }

    @Override
    public int updateCardById(UUID uuid, Card cardToUpdate) {
        return selectCardById(uuid)
                .map(cardWeFound -> {
                    int indexOfCardToUpdate = DB.indexOf(cardWeFound);
                    if (indexOfCardToUpdate >= 0) {
                        DB.set(indexOfCardToUpdate, new Card(uuid,cardToUpdate.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}

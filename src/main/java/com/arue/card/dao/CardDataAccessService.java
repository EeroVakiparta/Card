package com.arue.card.dao;

import com.arue.card.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class CardDataAccessService implements CardDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CardDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static List<Card> DB = new ArrayList<>();
/*
    @Override
    public int insertCard(UUID id, Card card) {
        String sql = "INSERT INTO CARD (id, name) values (? , ?)";
        jdbcTemplate.update(sql, id , card.getName());
        return 1;
    }
*/
    @Override
    public int insertCard(Card card) {
        String sql = "INSERT INTO CARD (name) values ( ?)";
        jdbcTemplate.update(sql, card.getName());
        return 1;
    }

    @Override
    public List<Card> selectAllCards() {
        String sql = "SELECT id, name FROM card";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Card(id, name);
        }));

    }

    @Override
    public Optional<Card> selectCardById(UUID id) {
        String sql = "SELECT id, name FROM card WHERE id = ?";
        Card card = jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> {
            UUID cardId = UUID.fromString(resultSet.getString("id"));
            String cardName = resultSet.getString("name");
            return new Card(cardId, cardName);
        }));
        return Optional.ofNullable(card);
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

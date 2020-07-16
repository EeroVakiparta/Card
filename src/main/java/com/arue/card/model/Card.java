package com.arue.card.model;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "card")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long card_id;

    @NotBlank
    @Column(name = "name")
    private String card_name;

    @Lob // Large Object
    @Type(type="org.hibernate.type.BinaryType") // Hibernate binary data helper
    private byte[] card_image;

    public Card() {
    }

    public Long getCard_id() {
        return card_id;
    }

    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public byte[] getCard_image() {
        return card_image;
    }

    public void setCard_image(byte[] card_image) {
        this.card_image = card_image;
    }
}

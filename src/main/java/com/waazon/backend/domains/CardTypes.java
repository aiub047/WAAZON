package com.waazon.backend.domains;

@SuppressWarnings("unused")
public enum CardTypes {
    Visa("Visa"),
    MasterCard("MasterCard"),
    Discoverer("Discoverer");

    private final String CardType;

    CardTypes(String status) {
        this.CardType = status;
    }

    public String getCardType() {
        return this.CardType;
    }
}

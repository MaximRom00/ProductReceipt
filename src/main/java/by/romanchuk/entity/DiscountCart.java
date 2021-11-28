package by.romanchuk.entity;

public class DiscountCart {
    private String name;
    private int cardNumber;

    public DiscountCart(String name, int cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return "DiscountCart: " + name +
                ", cardNumber: " + cardNumber;
    }
}

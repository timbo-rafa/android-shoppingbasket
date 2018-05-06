package com.rafaeltimbo.shoppingbasket;

public enum Status {
    Processing, Delivery;

    @Override
    public String toString() {
        if (this == Delivery) return "Delivered";
        else                  return "Processing";
    }

    public static Status fromString(String string) {
        if (string.toLowerCase().equals("delivered")) return Delivery;
        else                                        return Processing;

    }
}

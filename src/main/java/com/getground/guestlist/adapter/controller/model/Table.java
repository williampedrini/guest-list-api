package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import static java.util.Objects.requireNonNull;

public class Table {
    @JsonProperty("number")
    private final Integer number;

    @JsonProperty("number_of_seats")
    private final Integer numberOfSeats;

    @JsonProperty("guest_name")
    private final String guestName;

    public Table(@NonNull final com.getground.guestlist.usecase.model.Table table) {
        this(table.getNumber(), table.getNumberOfSeats(), table.getGuestName());
    }

    public Table(final int number,
                 final int numberOfSeats,
                 @NonNull final String guestName) {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
        this.guestName = requireNonNull(guestName, "The name of the guest.");
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    @NonNull
    public String getGuestName() {
        return guestName;
    }
}
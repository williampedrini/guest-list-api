package com.getground.guestlist.usecase.model;

import com.getground.guestlist.domain.entity.GuestEntity;
import com.getground.guestlist.domain.entity.TableEntity;
import org.springframework.lang.NonNull;

import static java.util.Objects.requireNonNull;

public class Table {
    private final Integer number;
    private final Integer numberOfSeats;
    private final String guestName;

    public Table(final TableEntity table) {
        this(table.getNumber(), table.getNumberOfSeats(), table.getGuest().map(GuestEntity::getName).orElse(""));
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
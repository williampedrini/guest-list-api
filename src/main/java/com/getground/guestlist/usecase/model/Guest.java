package com.getground.guestlist.usecase.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.getground.guestlist.domain.entity.GuestEntity;
import com.getground.guestlist.domain.entity.TableEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class Guest {
    private final String name;
    private final long partyId;
    private final int tableNumber;
    private final int accompanyingGuestsNumber;
    private final LocalDateTime arrivalTime;

    public Guest(final long partyId, @NonNull final GuestEntity guest) {
        this(guest.getName(), partyId, guest.getTable().getNumber(), guest.getEntourageQuantity(), guest.getArrivalTime().orElse(null));
    }

    @JsonCreator
    public Guest(@NonNull @JsonProperty("name") final String name,
                 @JsonProperty("partyId") final long partyId,
                 @JsonProperty("tableNumber") int tableNumber,
                 @JsonProperty("accompanyingGuestsNumber") int accompanyingGuestsNumber,
                 @Nullable @JsonProperty("arrivalTime") final LocalDateTime arrivalTime) {
        this.name = requireNonNull(name, "The guest name is mandatory.");
        this.partyId = partyId;
        this.tableNumber = tableNumber;
        this.accompanyingGuestsNumber = accompanyingGuestsNumber;
        this.arrivalTime = arrivalTime;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public long getPartyId() {
        return partyId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getAccompanyingGuestsNumber() {
        return accompanyingGuestsNumber;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Converts the current {@link Guest} into {@link GuestEntity}.
     *
     * @param tableEntity The object representing the table which will be associated to the guest.
     * @return The converted object.
     */
    @NonNull
    public GuestEntity toEntity(final TableEntity tableEntity) {
        return new GuestEntity(null, name, null, null, accompanyingGuestsNumber, tableEntity);
    }
}

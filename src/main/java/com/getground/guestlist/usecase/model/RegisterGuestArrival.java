package com.getground.guestlist.usecase.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import static java.util.Objects.requireNonNull;

public class RegisterGuestArrival {
    private final String name;
    private final long partyId;
    private final int accompanyingGuestsNumber;

    @JsonCreator
    public RegisterGuestArrival(@JsonProperty("name") @NonNull final String name,
                                @JsonProperty("partyId") final long partyId,
                                @JsonProperty("accompanyingGuestsNumber") final int accompanyingGuestsNumber) {
        this.name = requireNonNull(name, "The name is mandatory.");
        this.partyId = partyId;
        this.accompanyingGuestsNumber = accompanyingGuestsNumber;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public long getPartyId() {
        return partyId;
    }

    public int getAccompanyingGuestsNumber() {
        return accompanyingGuestsNumber;
    }
}

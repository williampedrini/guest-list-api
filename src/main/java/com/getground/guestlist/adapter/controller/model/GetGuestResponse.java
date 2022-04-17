package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.getground.guestlist.usecase.model.Guest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@ApiModel(description = "Represents an object used to indicate the response for a guest search.")
public class GetGuestResponse {

    @JsonProperty("guests")
    @ApiModelProperty(position = 1, value = "The list of guests.", required = true)
    private final Collection<GetGuestResponseGuest> guests;

    @JsonCreator
    public GetGuestResponse(@NonNull @JsonProperty("guests") final List<GetGuestResponseGuest> guests) {
        this.guests = requireNonNull(guests, "The guests are mandatory.");
    }

    public GetGuestResponse(final Collection<Guest> guests) {
        this.guests = guests
                .stream()
                .map(guest -> new GetGuestResponseGuest(guest.getName(), guest.getTableNumber(), guest.getAccompanyingGuestsNumber(), guest.getArrivalTime()))
                .collect(toList());
    }

    @NonNull
    public Collection<GetGuestResponseGuest> getGuests() {
        return new ArrayList<>(guests);
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof GetGuestResponse) {
            final var otherGetGuestResponse = (GetGuestResponse) other;
            return Objects.equals(guests, otherGetGuestResponse.guests);
        }
        return false;
    }

}

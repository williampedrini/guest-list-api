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

@ApiModel(description = "Represents an object used as response to show all existing guests for a certain party.")
public class GetGuestListResponse {

    @JsonProperty("guests")
    @ApiModelProperty(position = 1, value = "The list of guests.", required = true)
    private final Collection<GetGuestListResponseGuest> guests;

    @JsonCreator
    public GetGuestListResponse(@NonNull @JsonProperty("guests") final List<GetGuestListResponseGuest> guests) {
        this.guests = requireNonNull(guests, "The guests are mandatory.");
    }

    public GetGuestListResponse(final Collection<Guest> guests) {
        this.guests = guests
                .stream()
                .map(guest -> new GetGuestListResponseGuest(guest.getName(), guest.getTableNumber(), guest.getAccompanyingGuestsNumber()))
                .collect(toList());
    }

    @NonNull
    public Collection<GetGuestListResponseGuest> getGuests() {
        return new ArrayList<>(guests);
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof GetGuestListResponse) {
            final var otherGetGuestListResponse = (GetGuestListResponse) other;
            return Objects.equals(guests, otherGetGuestListResponse.guests);
        }
        return false;
    }

}

package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "Represents an object used as response to show the empty seats.")
public class GetEmptySeatResponse {

    @JsonProperty("seats_empty")
    @ApiModelProperty(position = 1, value = "The amount of empty seats.", required = true, example = "1")
    private final int emptySeats;

    @JsonCreator
    public GetEmptySeatResponse(@JsonProperty("seats_empty") final int emptySeats) {
        this.emptySeats = emptySeats;
    }

    public int getEmptySeats() {
        return emptySeats;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof GetEmptySeatResponse) {
            final var otherGetGuestListResponse = (GetEmptySeatResponse) other;
            return Objects.equals(emptySeats, otherGetGuestListResponse.emptySeats);
        }
        return false;
    }
}

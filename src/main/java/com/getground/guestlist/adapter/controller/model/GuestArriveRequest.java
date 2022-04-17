package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.getground.guestlist.usecase.model.RegisterGuestArrival;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@ApiModel(description = "Represents an object used to indicate that a guest arrived.")
public class GuestArriveRequest {

    @JsonProperty("accompanying_guests")
    @Min(message = "Negative numbers are not accepted for the number of accompanying guests.", value = 0)
    @NotNull(message = "The number of accompanying guests is mandatory.")
    @ApiModelProperty(position = 1, value = "The number of accompanying guests.", required = true, example = "10")
    private final Integer accompanyingGuestsNumber;

    @JsonCreator
    public GuestArriveRequest(@NonNull @JsonProperty("accompanying_guests") final Integer accompanyingGuestsNumber) {
        this.accompanyingGuestsNumber = accompanyingGuestsNumber;
    }

    public int getAccompanyingGuestsNumber() {
        return accompanyingGuestsNumber;
    }

    @NonNull
    public RegisterGuestArrival toRegisterGuestArrival(final long partyId, @NonNull final String guestName) {
        requireNonNull(guestName, "The guest name is mandatory");
        return new RegisterGuestArrival(guestName, partyId, accompanyingGuestsNumber);
    }
}

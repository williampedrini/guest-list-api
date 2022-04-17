package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.getground.guestlist.usecase.model.Guest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@ApiModel(description = "Represents an object used to add a guest to a table.")
public class AddGuestToListRequest {

    @JsonProperty("table")
    @Min(message = "Negative numbers are not accepted for the table number.", value = 0)
    @NotNull(message = "The table number is mandatory.")
    @ApiModelProperty(position = 1, value = "The table number.", required = true, example = "10")
    private final Integer tableNumber;

    @JsonProperty("accompanying_guests")
    @Min(message = "Negative numbers are not accepted for the number of accompanying guests.", value = 0)
    @NotNull(message = "The number of accompanying guests is mandatory.")
    @ApiModelProperty(position = 2, value = "The number of accompanying guests.", required = true, example = "10")
    private final Integer accompanyingGuestsNumber;

    @JsonCreator
    public AddGuestToListRequest(@NonNull @JsonProperty("table") final Integer tableNumber,
                                 @NonNull @JsonProperty("accompanying_guests") final Integer accompanyingGuestsNumber) {
        this.tableNumber = tableNumber;
        this.accompanyingGuestsNumber = accompanyingGuestsNumber;
    }

    @NonNull
    public Integer getTableNumber() {
        return tableNumber;
    }

    @NonNull
    public Integer getAccompanyingGuestsNumber() {
        return accompanyingGuestsNumber;
    }

    @NonNull
    public Guest toGuest(final long partyId, @NonNull final String name) {
        requireNonNull(name, "The guest name is mandatory to build a guest.");
        return new Guest(name, partyId, tableNumber, accompanyingGuestsNumber, null);
    }
}

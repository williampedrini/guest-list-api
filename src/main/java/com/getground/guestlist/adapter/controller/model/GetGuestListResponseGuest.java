package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@ApiModel(description = "Represents an object used to indicate the guest information.")
public class GetGuestListResponseGuest {

    @JsonProperty("name")
    @ApiModelProperty(position = 1, value = "The name of the guest.", required = true, example = "Wanda Hum")
    private final String name;

    @JsonProperty("table")
    @ApiModelProperty(position = 2, value = "The table name associated to the guest.", required = true, example = "10")
    private final Integer table;

    @JsonProperty("accompanying_guests")
    @ApiModelProperty(position = 3, value = "The number of accompanying people.", required = true, example = "5")
    private final Integer accompanyingGuests;

    @JsonCreator
    public GetGuestListResponseGuest(@NonNull @JsonProperty("name") final String name,
                                     @NonNull @JsonProperty("table") final Integer table,
                                     @NonNull @JsonProperty("accompanying_guests") final Integer accompanyingGuests) {
        this.name = requireNonNull(name, "The name is mandatory.");
        this.table = requireNonNull(table, "The table number is mandatory");
        this.accompanyingGuests = requireNonNull(accompanyingGuests, "The number of accompanying is mandatory.");
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public Integer getTable() {
        return table;
    }

    @NonNull
    public Integer getAccompanyingGuests() {
        return accompanyingGuests;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof GetGuestListResponseGuest) {
            final var otherGuest = (GetGuestListResponseGuest) other;
            return Objects.equals(name, otherGuest.name)
                    && Objects.equals(table, otherGuest.table)
                    && Objects.equals(accompanyingGuests, otherGuest.accompanyingGuests);
        }
        return false;
    }
}

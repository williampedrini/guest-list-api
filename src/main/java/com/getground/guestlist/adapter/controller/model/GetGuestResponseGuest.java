package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@ApiModel(description = "Represents an object used to indicate the guest information of a guest search.")
public class GetGuestResponseGuest {

    @JsonProperty("name")
    @ApiModelProperty(position = 1, value = "The name of the guest.", required = true, example = "Wanda Hum")
    private final String name;

    @JsonProperty("table")
    @ApiModelProperty(position = 2, value = "The table name associated to the guest.", required = true, example = "10")
    private final Integer table;

    @JsonProperty("accompanying_guests")
    @ApiModelProperty(position = 3, value = "The number of accompanying people.", required = true, example = "10")
    private final Integer accompanyingGuests;

    @JsonProperty("time_arrived")
    @ApiModelProperty(position = 4, value = "The time that the guest arrived.", required = true, example = "4")
    private final LocalDateTime timeArrived;

    @JsonCreator
    public GetGuestResponseGuest(@NonNull @JsonProperty("name") final String name,
                                 @NonNull @JsonProperty("table") final Integer table,
                                 @NonNull @JsonProperty("accompanying_guests") final Integer accompanyingGuests,
                                 @NonNull @JsonProperty("time_arrived") final LocalDateTime timeArrived) {
        this.name = requireNonNull(name, "The name is mandatory.");
        this.table = requireNonNull(table, "The table number is mandatory");
        this.accompanyingGuests = requireNonNull(accompanyingGuests, "The number of accompanying is mandatory.");
        this.timeArrived = requireNonNull(timeArrived, "The time of arrival is mandatory.");
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

    @NonNull
    public LocalDateTime getTimeArrived() {
        return timeArrived;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof GetGuestResponseGuest) {
            final var otherGuest = (GetGuestResponseGuest) other;
            return Objects.equals(name, otherGuest.name)
                    && Objects.equals(table, otherGuest.table)
                    && Objects.equals(accompanyingGuests, otherGuest.accompanyingGuests)
                    && Objects.equals(timeArrived, otherGuest.timeArrived);
        }
        return false;
    }
}

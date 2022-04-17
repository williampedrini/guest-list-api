package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@ApiModel(description = "Represents an object used to indicate the response for the guest arrived request.")
public class GuestArriveResponse {

    @JsonProperty("name")
    @ApiModelProperty(position = 1, value = "The name of the guest.", required = true, example = "Wanda Hum")
    private final String name;

    @JsonCreator
    public GuestArriveResponse(@NonNull @JsonProperty("name") final String name) {
        this.name = requireNonNull(name, "The name is mandatory.");
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof GuestArriveResponse) {
            final var otherGuestArriveResponse = (GuestArriveResponse) other;
            return Objects.equals(name, otherGuestArriveResponse.name);
        }
        return false;
    }
}

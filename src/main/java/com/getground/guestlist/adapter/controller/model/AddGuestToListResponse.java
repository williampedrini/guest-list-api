package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import java.util.Objects;

import static java.lang.String.format;
import static java.util.Objects.hash;

@ApiModel(description = "Represents an object used as response to the addition of a guest to a table list.")
public class AddGuestToListResponse {

    @JsonProperty("name")
    @ApiModelProperty(position = 1, value = "The name of the guest.", required = true, example = "Wanda Hum")
    private final String name;

    @JsonCreator
    public AddGuestToListResponse(@NonNull @JsonProperty("name") final String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof AddGuestToListResponse) {
            final var otherAddGuestToListResponse = (AddGuestToListResponse) other;
            return Objects.equals(name, otherAddGuestToListResponse.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash(name);
    }

    @Override
    public String toString() {
        return format("AddGuestToListResponse{name='%s'}", name);
    }
}

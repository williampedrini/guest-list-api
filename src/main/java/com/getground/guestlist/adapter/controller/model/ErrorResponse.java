package com.getground.guestlist.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@ApiModel(description = "API error response")
public class ErrorResponse {

    @ApiModelProperty(position = 1, value = "The reason why an error occurred.", required = true, example = "The query was not performed with success.")
    private final String reason;

    @JsonCreator
    public ErrorResponse(@NonNull @JsonProperty("reason") final String reason) {
        this.reason = requireNonNull(reason, "The error reason is mandatory.");
    }

    @NonNull
    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof ErrorResponse) {
            final var otherErrorResponse = (ErrorResponse) other;
            return Objects.equals(reason, otherErrorResponse.reason);
        }
        return false;
    }
}

package com.getground.guestlist.adapter.controller;

import com.getground.guestlist.adapter.controller.model.ErrorResponse;
import com.getground.guestlist.adapter.controller.model.GetGuestListResponse;
import com.getground.guestlist.adapter.controller.model.Party;
import com.getground.guestlist.usecase.FindParty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;

@Api(tags = "Party Controller")
@RestController
class PartyController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PartyController.class);

    private final FindParty findParty;

    PartyController(@NonNull final FindParty findParty) {
        this.findParty = requireNonNull(findParty, "The find party use case is mandatory.");
    }

    @ResponseStatus(OK)
    @GetMapping("/parties")
    @ApiOperation(value = "findAllParty", notes = "Searches for all existing parties.", nickname = "findAllParty")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All existing parties are retrieved.", response = GetGuestListResponse.class),
            @ApiResponse(code = 500, message = "Internal error while processing the request.", response = ErrorResponse.class)
    })
    Collection<Party> findAllParty() {
        LOGGER.debug("Performing search of all existing parties.");
        return findParty.findAll()
                .stream()
                .map(Party::new)
                .collect(toList());
    }
}

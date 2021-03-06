package com.getground.guestlist.adapter.controller;

import com.getground.guestlist.adapter.controller.model.AddGuestToListRequest;
import com.getground.guestlist.adapter.controller.model.AddGuestToListResponse;
import com.getground.guestlist.adapter.controller.model.ErrorResponse;
import com.getground.guestlist.adapter.controller.model.GetEmptySeatResponse;
import com.getground.guestlist.adapter.controller.model.GetGuestListResponse;
import com.getground.guestlist.adapter.controller.model.GetGuestResponse;
import com.getground.guestlist.adapter.controller.model.GuestArriveRequest;
import com.getground.guestlist.adapter.controller.model.GuestArriveResponse;
import com.getground.guestlist.usecase.AddGuestToGuestList;
import com.getground.guestlist.usecase.FindGuest;
import com.getground.guestlist.usecase.RegisterGuestArrival;
import com.getground.guestlist.usecase.RegisterGuestLeave;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Api(tags = "Guest List Controller")
@RestController
class GuestListController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GuestListController.class);

    private final AddGuestToGuestList addGuestToGuestList;
    private final FindGuest findGuest;
    private final RegisterGuestArrival registerGuestArrival;
    private final RegisterGuestLeave registerGuestLeave;

    GuestListController(@NonNull final AddGuestToGuestList addGuestToGuestList,
                        @NonNull final FindGuest findGuest,
                        @NonNull final RegisterGuestArrival registerGuestArrival,
                        @NonNull final RegisterGuestLeave registerGuestLeave) {
        this.addGuestToGuestList = requireNonNull(addGuestToGuestList, "The addGuestToGuestList use case is mandatory.");
        this.findGuest = requireNonNull(findGuest, "The findGuest use case is mandatory.");
        this.registerGuestArrival = requireNonNull(registerGuestArrival, "The registerGuestArrival use case is mandatory.");
        this.registerGuestLeave = requireNonNull(registerGuestLeave, "The registerGuestLeave use case is mandatory.");
    }

    @ResponseStatus(OK)
    @PostMapping("/parties/{partyId}/guest_list/{name}")
    @ApiOperation(value = "addGuestToList", notes = "Adds a certain guest to the guest list", nickname = "addGuestToList")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The guest was successfully added to the list.", response = AddGuestToListResponse.class),
            @ApiResponse(code = 400, message = "Error while performing pre-validations against the guest addition.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal error while processing the request.", response = ErrorResponse.class)
    })
    AddGuestToListResponse addGuestToList(@ApiParam(value = "The identifier of the party. <b>Do not change this value for testing.</b>", required = true, example = "1", defaultValue = "1")
                                          @PathVariable(name = "partyId") final long partyId,

                                          @ApiParam(value = "The name of the guest.", required = true, example = "Wanda Hum")
                                          @PathVariable(name = "name") final String guestName,

                                          @ApiParam(value = "The representation of an addition of guest request.", required = true)
                                          @Valid @RequestBody final AddGuestToListRequest request) {
        LOGGER.debug("Performing addition of guest to list. [PARTY_ID={},GUEST_NAME={}, REQUEST={}]", partyId, guestName, request);
        addGuestToGuestList.add(request.toGuest(partyId, guestName));
        return new AddGuestToListResponse(guestName);
    }

    @ResponseStatus(OK)
    @GetMapping("/parties/{partyId}/guest_list")
    @ApiOperation(value = "findAllGuestsInGuestList", notes = "Searches for all existing guests in the guest list.", nickname = "findAllGuestsInGuestList")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All existing guests are retrieved.", response = GetGuestListResponse.class),
            @ApiResponse(code = 500, message = "Internal error while processing the request.", response = ErrorResponse.class)
    })
    GetGuestListResponse findAllGuestsInGuestList(@ApiParam(value = "The identifier of the party. <b>Do not change this value for testing.</b>", required = true, example = "1", defaultValue = "1")
                                                  @PathVariable(name = "partyId") final long partyId) {
        LOGGER.debug("Performing search of all existing guests. [PARTY_ID={}]", partyId);
        final var guests = findGuest.findAllByPartyId(partyId);
        return new GetGuestListResponse(guests);
    }

    @ResponseStatus(OK)
    @GetMapping("/parties/{partyId}/guests")
    @ApiOperation(value = "findAllArrivedGuests", notes = "Searches for all existing guests in the guest list who arrived at the party.", nickname = "findAllArrivedGuests")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All existing guests are retrieved.", response = GetGuestResponse.class),
            @ApiResponse(code = 500, message = "Internal error while processing the request.", response = ErrorResponse.class)
    })
    GetGuestResponse findAllArrivedGuests(@ApiParam(value = "The identifier of the party. <b>Do not change this value for testing.</b>", required = true, example = "1", defaultValue = "1")
                                          @PathVariable(name = "partyId") final long partyId) {
        LOGGER.debug("Performing search of all arrived guests. [PARTY_ID={}]", partyId);
        final var guests = findGuest.findAllArrivedByPartyId(partyId);
        return new GetGuestResponse(guests);
    }

    @ResponseStatus(OK)
    @GetMapping("/parties/{partyId}/seats_empty")
    @ApiOperation(value = "findAllEmptySeats", notes = "Searches for all existing empty seats.", nickname = "findAllEmptySeats")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All existing guests are retrieved.", response = GetEmptySeatResponse.class),
            @ApiResponse(code = 500, message = "Internal error while processing the request.", response = ErrorResponse.class)
    })
    GetEmptySeatResponse findAllEmptySeats(@ApiParam(value = "The identifier of the party. <b>Do not change this value for testing.</b>", required = true, example = "1", defaultValue = "1")
                                           @PathVariable(name = "partyId") final long partyId) {
        LOGGER.debug("Performing search of all existing empty seats. [PARTY_ID={}]", partyId);
        final var emptySeats = findGuest.findEmptySeats(partyId);
        return new GetEmptySeatResponse(emptySeats);
    }

    @ResponseStatus(OK)
    @PutMapping("/parties/{partyId}/guest_list/{name}")
    @ApiOperation(value = "registerGuestArrival", notes = "Registers a guest arrival.", nickname = "registerGuestArrival")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The guest arrival is successfully registered.", response = GuestArriveResponse.class),
            @ApiResponse(code = 400, message = "Error while performing pre-validations against the guest addition.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal error while processing the request.", response = ErrorResponse.class)
    })
    GuestArriveResponse registerGuestArrival(@ApiParam(value = "The identifier of the party. <b>Do not change this value for testing.</b>", required = true, example = "1", defaultValue = "1")
                                             @PathVariable(name = "partyId") final long partyId,

                                             @ApiParam(value = "The name of the guest.", required = true, example = "Wanda Hum")
                                             @PathVariable(name = "name") final String guestName,

                                             @ApiParam(value = "The representation of guest arrival registration request.", required = true)
                                             @Valid @RequestBody final GuestArriveRequest request) {
        LOGGER.debug("Marking guest as arrived. [PARTY_ID={},GUEST_NAME={}, REQUEST={}]", partyId, guestName, request);
        registerGuestArrival.register(request.toRegisterGuestArrival(partyId, guestName));
        return new GuestArriveResponse(guestName);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/parties/{partyId}/guest_list/{name}")
    @ApiOperation(value = "registerGuestLeave", notes = "Registers a guest leave.", nickname = "registerGuestLeave")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The guest leave is successfully registered."),
            @ApiResponse(code = 500, message = "Internal error while processing the request.", response = ErrorResponse.class)
    })
    void registerGuestLeave(@ApiParam(value = "The identifier of the party. <b>Do not change this value for testing.</b>", required = true, example = "1", defaultValue = "1")
                            @PathVariable(name = "partyId") final long partyId,

                            @ApiParam(value = "The name of the guest.", required = true, example = "Wanda Hum")
                            @PathVariable(name = "name") final String guestName) {
        LOGGER.debug("Marking guest leave. [PARTY_ID={},GUEST_NAME={}]", partyId, guestName);
        registerGuestLeave.register(partyId, guestName);
    }
}

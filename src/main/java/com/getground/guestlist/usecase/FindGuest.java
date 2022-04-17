package com.getground.guestlist.usecase;

import com.getground.guestlist.domain.port.GuestPort;
import com.getground.guestlist.domain.port.TablePort;
import com.getground.guestlist.usecase.model.Guest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Component
public class FindGuest {
    private final GuestPort guestPort;

    public FindGuest(@NonNull final GuestPort guestPort, @NonNull final TablePort tablePort) {
        this.guestPort = requireNonNull(guestPort, "The guest port is mandatory.");
    }

    /**
     * Searches for all existing guests for a certain party.
     *
     * @param partyId The party identifier.
     * @return All found existing guests.
     */
    @NonNull
    public Collection<Guest> findAllByPartyId(final long partyId) {
        return guestPort.findAllByPartyId(partyId)
                .stream()
                .map(guest -> new Guest(partyId, guest))
                .collect(toList());
    }
}

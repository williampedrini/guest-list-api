package com.getground.guestlist.usecase;

import com.getground.guestlist.domain.port.PartyPort;
import com.getground.guestlist.usecase.model.Party;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Component
public class FindParty {
    private final PartyPort partyPort;

    public FindParty(@NonNull final PartyPort partyPort) {
        this.partyPort = requireNonNull(partyPort, "The partyPort is mandatory.");
    }

    /**
     * Searches for all existing parties.
     *
     * @return All found parties.
     */
    @NonNull
    public Collection<Party> findAll() {
        return partyPort.findAll().stream().map(Party::new).collect(toList());
    }
}

package com.getground.guestlist.usecase;

import com.getground.guestlist.domain.port.GuestPort;
import com.getground.guestlist.domain.port.TablePort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;

@Component
public class RegisterGuestLeave {
    private final GuestPort guestPort;
    private final TablePort tablePort;

    public RegisterGuestLeave(@NonNull final GuestPort guestPort, @NonNull final TablePort tablePort) {
        this.guestPort = requireNonNull(guestPort, "The guest port is mandatory.");
        this.tablePort = requireNonNull(tablePort, "The table port is mandatory.");
    }

    /**
     * Registers the leave of a certain guest for a specific party.
     *
     * @param partyId The party identifier.
     * @param guestName The name of the guest.
     */
    public void register(final long partyId, @NonNull final String guestName) {
        requireNonNull(guestName, "The guest name is mandatory.");

        final var tableEntity = tablePort.findByPartyIdAndGuestName(partyId, guestName)
                .orElseThrow(() -> {
                    final var errorMessage = "There is not any table registered for the guest with name %s.";
                    return new IllegalArgumentException(format(errorMessage, guestName));
                });

        final var guestEntity = guestPort.findByNameAndTableId(guestName, tableEntity.getId().orElse(null))
                .orElseThrow(() -> {
                    final var errorMessage = "The guest with name %s is registered to the table with number %s.";
                    return new IllegalArgumentException(format(errorMessage, guestName, tableEntity.getNumber()));
                });
        guestEntity.setLeaveTime(now());
        guestEntity.setEntourageQuantity(0);
        guestPort.save(guestEntity);
    }
}

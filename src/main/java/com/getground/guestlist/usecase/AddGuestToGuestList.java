package com.getground.guestlist.usecase;

import com.getground.guestlist.domain.port.GuestPort;
import com.getground.guestlist.domain.port.TablePort;
import com.getground.guestlist.usecase.model.Guest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@Component
public class AddGuestToGuestList {
    private final TablePort tablePort;
    private final GuestPort guestPort;

    public AddGuestToGuestList(@NonNull final TablePort tablePort, @NonNull final GuestPort guestPort) {
        this.tablePort = requireNonNull(tablePort, "The table por is mandatory.");
        this.guestPort = requireNonNull(guestPort, "The guest port is mandatory.");
    }

    /**
     * Adds a certain guest into the guests list if possible.
     *
     * @param guest The guest to be added to the list.
     */
    public void add(@NonNull final Guest guest) {
        requireNonNull(guest, "The guest is mandatory when performing the addition to the list.");
        final var tableEntity = tablePort.findByPartyIdAndNumber(guest.getPartyId(), guest.getTableNumber())
                .orElseThrow(() -> {
                    final var errorMessage = "There is not any existing table with number %s.";
                    return new IllegalArgumentException(format(errorMessage, guest.getTableNumber()));
                });

        if (tableEntity.getNumberOfSeats() < guest.getAccompanyingGuestsNumber() + 1) {
            final var errorMessage = new StringBuilder()
                    .append("There are not enough seats for the number of accompanying guests.")
                    .append("The table total seats are %s and the provided number of people is %s.")
                    .toString();
            throw new IllegalArgumentException(format(errorMessage, tableEntity.getNumberOfSeats(), guest.getAccompanyingGuestsNumber() + 1));
        }

        if (tableEntity.getGuest().isEmpty()) {
            final var guestEntity = guest.toEntity(tableEntity);
            guestPort.save(guestEntity);
            return;
        }
        final var errorMessage = "The guest with name %s is already associated to the table number %s.";
        throw new IllegalArgumentException(format(errorMessage, tableEntity.getGuest().get().getName(), guest.getTableNumber()));
    }
}

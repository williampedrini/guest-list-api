package com.getground.guestlist.usecase;

import com.getground.guestlist.domain.port.GuestPort;
import com.getground.guestlist.domain.port.TablePort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;

@Component
public class RegisterGuestArrival {
    private final GuestPort guestPort;
    private final TablePort tablePort;

    public RegisterGuestArrival(@NonNull final GuestPort guestPort, @NonNull final TablePort tablePort) {
        this.guestPort = requireNonNull(guestPort, "The guest port is mandatory.");
        this.tablePort = requireNonNull(tablePort, "The table port is mandatory.");
    }

    /**
     * Performs the registration of the guest arrival.
     *
     * @param registerGuestArrival The object containing the data of arrival.
     */
    public void register(@NonNull final com.getground.guestlist.usecase.model.RegisterGuestArrival registerGuestArrival) {
        requireNonNull(registerGuestArrival, "The registering data is mandatory.");

        final var tableEntity = tablePort.findByPartyIdAndGuestName(registerGuestArrival.getPartyId(), registerGuestArrival.getName())
                .orElseThrow(() -> {
                    final var errorMessage = "There is not any table registered for the guest with name %s.";
                    return new IllegalArgumentException(format(errorMessage, registerGuestArrival.getName()));
                });

        if (tableEntity.getNumberOfSeats() < registerGuestArrival.getAccompanyingGuestsNumber() + 1) {
            final var errorMessage = new StringBuilder()
                    .append("There are not enough seats for the number of accompanying guests.")
                    .append("The table total seats are %s and the provided number of people is %s.")
                    .toString();
            throw new IllegalArgumentException(format(errorMessage, tableEntity.getNumberOfSeats(), registerGuestArrival.getAccompanyingGuestsNumber() + 1));
        }

        final var guestEntity = guestPort.findByNameAndTableId(registerGuestArrival.getName(), tableEntity.getId().orElse(null))
                .orElseThrow(() -> {
                    final var errorMessage = "The guest with name %s is registered to the table with number %s.";
                    return new IllegalArgumentException(format(errorMessage, registerGuestArrival.getName(), tableEntity.getNumber()));
                });
        guestEntity.setArrivalTime(now());
        guestEntity.setEntourageQuantity(registerGuestArrival.getAccompanyingGuestsNumber());
        guestPort.save(guestEntity);
    }
}

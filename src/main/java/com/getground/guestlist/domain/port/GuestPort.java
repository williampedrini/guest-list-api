package com.getground.guestlist.domain.port;

import com.getground.guestlist.domain.entity.GuestEntity;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Optional;

/**
 * Represents a data access object used to access data related to {@link GuestEntity}.
 *
 * @since 1.0.0
 */
public interface GuestPort {

    /**
     * Persists a guest into the data layer or updated it if it already exists.
     *
     * @param guest The object containing the information of the guest.
     */
    @NonNull
    GuestEntity save(@NonNull GuestEntity guest);

    /**
     * Searches for a certain guest by its name and table identifier.
     *
     * @param name The name of the guest.
     * @param tableId The table identifier.
     * @return The possible found guest.
     */
    @NonNull
    Optional<GuestEntity> findByNameAndTableId(@NonNull String name, @NonNull Long tableId);

    /**
     * Searches for all existing guests for a certain party.
     *
     * @param partyId The party identifier.
     * @return All found existing guests.
     */
    @NonNull
    Collection<GuestEntity> findAllByPartyId(long partyId);

    /**
     * Searches for all existing guests for a certain party who arrived.
     *
     * @param partyId The party identifier.
     * @return All found existing guests.
     */
    @NonNull
    Collection<GuestEntity> findAllArrivedByPartyId(long partyId);
}

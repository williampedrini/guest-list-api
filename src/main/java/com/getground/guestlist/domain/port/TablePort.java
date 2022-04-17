package com.getground.guestlist.domain.port;

import com.getground.guestlist.domain.entity.TableEntity;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Optional;

/**
 * Represents a data access object used to access data related to {@link TableEntity}.
 *
 * @since 1.0.0
 */
public interface TablePort {

    /**
     * Find all existing tables for a certain party identifier.
     *
     * @param partyId The party identifier.
     * @return All found tables.
     */
    @NonNull
    Collection<TableEntity> findAllByPartyId(long partyId);

    /**
     * Searches for a certain table by its numbers.
     *
     * @param partyId The party identifier.
     * @param number The number of the table to be searched.
     * @return The possible found table.
     */
    @NonNull
    Optional<TableEntity> findByPartyIdAndNumber(long partyId, int number);

    /**
     * Searches for a certain table by a party and guest name.
     *
     * @param partyId The party identifier.
     * @param guestName The name of the guest.
     * @return The possible found table.
     */
    @NonNull
    Optional<TableEntity> findByPartyIdAndGuestName(long partyId, @NonNull String guestName);
}

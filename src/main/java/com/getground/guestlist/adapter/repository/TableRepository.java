package com.getground.guestlist.adapter.repository;

import com.getground.guestlist.domain.entity.TableEntity;
import com.getground.guestlist.domain.port.TablePort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Optional;

interface TableRepository extends JpaRepository<TableEntity, Long>, TablePort {

    /**
     * Searches for a certain table by a party and guest name.
     *
     * @param partyId The party identifier.
     * @param guestName The name of the guest.
     * @return The possible found table.
     */
    @NonNull
    Optional<TableEntity> findByParty_IdAndGuest_Name(long partyId, @NonNull String guestName);

    /**
     * Find all existing tables for a certain party identifier.
     *
     * @param partyId The party identifier.
     * @return All found tables.
     */
    @NonNull
    Collection<TableEntity> findAllByParty_Id(long partyId);

    /**
     * @see TableRepository#findByParty_IdAndGuest_Name(long, String).
     */
    @NonNull
    default Optional<TableEntity> findByPartyIdAndGuestName(final long partyId, @NonNull final String guestName) {
        return this.findByParty_IdAndGuest_Name(partyId, guestName);
    }

    /**
     * @see TableRepository#findAllByParty_Id(long).
     */
    @NonNull
    default Collection<TableEntity> findAllByPartyId(long partyId) {
        return this.findAllByParty_Id(partyId);
    }
}

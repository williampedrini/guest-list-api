package com.getground.guestlist.adapter.repository;

import com.getground.guestlist.domain.entity.GuestEntity;
import com.getground.guestlist.domain.port.GuestPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Optional;

interface GuestRepository extends JpaRepository<GuestEntity, Long>, GuestPort {

    /**
     * Searches for all existing guests for a certain party.
     *
     * @param partyId The party identifier.
     * @return All found existing guests.
     */
    @NonNull
    @Query("select g from PartyEntity e inner join e.tables t inner join t.guest g where e.id = :partyId")
    Collection<GuestEntity> findAllByPartyId(@Param("partyId") long partyId);

    /**
     * Searches for all existing guests for a certain party.
     *
     * @param partyId The party identifier.
     * @return All found existing guests.
     */
    @NonNull
    @Query("select g from PartyEntity e inner join e.tables t inner join t.guest g where e.id = :partyId and g.arrivalTime is not null and g.leaveTime is null")
    Collection<GuestEntity> findAllArrivedByPartyId(@Param("partyId") long partyId);

    /**
     * Searches for a certain guest by its name and table identifier.
     *
     * @param name The name of the guest.
     * @param tableId The table identifier.
     * @return The possible found guest.
     */
    @NonNull
    Optional<GuestEntity> findByNameAndTable_Id(@NonNull String name, long tableId);

    /**
     * @see GuestRepository#findByNameAndTable_Id(String, long).
     */
    @NonNull
    default Optional<GuestEntity> findByNameAndTableId(@NonNull String name, long tableId) {
        return this.findByNameAndTable_Id(name, tableId);
    }
}
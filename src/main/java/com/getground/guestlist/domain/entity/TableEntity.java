package com.getground.guestlist.domain.entity;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "party_table")
public class TableEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "number_of_seats", nullable = false)
    private Integer numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private PartyEntity party;

    @OneToOne(mappedBy = "table")
    private GuestEntity guest;

    public TableEntity() {
    }

    public TableEntity(@Nullable final Long id,
                       final int number,
                       final int numberOfSeats,
                       @NonNull final PartyEntity party,
                       @Nullable final GuestEntity guest) {
        this.id = id;
        this.number = number;
        this.numberOfSeats = numberOfSeats;
        this.party = requireNonNull(party, "The party is mandatory.");
        this.guest = guest;
    }

    @NonNull
    public Optional<Long> getId() {
        return ofNullable(id);
    }

    public void setId(@Nullable final Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(final int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @NonNull
    public PartyEntity getParty() {
        return party;
    }

    public void setParty(@NonNull final PartyEntity party) {
        this.party = party;
    }

    @NonNull
    public Optional<GuestEntity> getGuest() {
        return ofNullable(guest);
    }

    public void setGuest(@Nullable final GuestEntity guest) {
        this.guest = guest;
    }
}

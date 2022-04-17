package com.getground.guestlist.domain.entity;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "party")
public class PartyEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "place", nullable = false)
    private String place;

    @OneToMany(mappedBy = "party")
    private Collection<TableEntity> tables;

    public PartyEntity() {
    }

    public PartyEntity(@Nullable final Long id,
                       @NonNull final String name,
                       @NonNull final String place,
                       @NonNull final Collection<TableEntity> tables) {
        this.id = id;
        this.name = name;
        this.place = requireNonNull(place, "The place is mandatory");
        this.tables = requireNonNull(tables, "The tables are mandatory.");
    }

    @NonNull
    public Optional<Long> getId() {
        return ofNullable(id);
    }

    public void setId(@Nullable final Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull final String name) {
        this.name = name;
    }

    @NonNull
    public String getPlace() {
        return place;
    }

    public void setPlace(@NonNull final String place) {
        this.place = place;
    }

    @NonNull
    public Collection<TableEntity> getTables() {
        return new ArrayList<>(tables);
    }

    public void setTables(@NonNull final Collection<TableEntity> tables) {
        this.tables = new ArrayList<>(tables);
    }
}

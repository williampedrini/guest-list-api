package com.getground.guestlist.usecase.model;

import com.getground.guestlist.domain.entity.PartyEntity;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class Party {
    private final long id;
    private final String name;
    private final String place;
    private final Collection<Table> tables;

    public Party(@NonNull final PartyEntity party) {
        this(party.getId().orElse(null), party.getName(), party.getPlace(), party.getTables().stream().map(Table::new).collect(toList()));
    }

    public Party(final Long id,
                 @NonNull final String name,
                 @NonNull final String place,
                 @NonNull final Collection<Table> tables) {
        this.id = id;
        this.name = requireNonNull(name, "The name of the party is mandatory.");
        this.place = requireNonNull(place, "The place is mandatory.");
        this.tables = requireNonNull(tables, "The tables are mandatory");
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getPlace() {
        return place;
    }

    @NonNull
    public Collection<Table> getTables() {
        return new ArrayList<>(tables);
    }
}

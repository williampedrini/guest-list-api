package com.getground.guestlist.domain.entity;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "guest")
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "leave_time")
    private LocalDateTime leaveTime;

    @Column(name = "entourage_quantity")
    private int entourageQuantity;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "table_id", referencedColumnName = "id", unique = true)
    private TableEntity table;

    public GuestEntity() {
    }

    public GuestEntity(@Nullable final Long id,
                       @NonNull final String name,
                       @Nullable final LocalDateTime arrivalTime,
                       @Nullable final LocalDateTime leaveTime,
                       final int entourageQuantity,
                       @NonNull final TableEntity table) {
        this.id = id;
        this.name = requireNonNull(name, "The guest name is mandatory.");
        this.arrivalTime = arrivalTime;
        this.leaveTime = leaveTime;
        this.entourageQuantity = entourageQuantity;
        this.table = requireNonNull(table, "The guest table is mandatory.");
    }

    @Nullable
    public Long getId() {
        return id;
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
    public Optional<LocalDateTime> getArrivalTime() {
        return ofNullable(arrivalTime);
    }

    public void setArrivalTime(@Nullable final LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @NonNull
    public Optional<LocalDateTime> getLeaveTime() {
        return ofNullable(leaveTime);
    }

    public void setLeaveTime(@Nullable final LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getEntourageQuantity() {
        return entourageQuantity;
    }

    public void setEntourageQuantity(final int entourageQuantity) {
        this.entourageQuantity = entourageQuantity;
    }

    @NonNull
    public TableEntity getTable() {
        return table;
    }

    public void setTable(@NonNull final TableEntity table) {
        this.table = table;
    }
}

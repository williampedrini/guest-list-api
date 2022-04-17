package com.getground.guestlist.domain.port;

import com.getground.guestlist.domain.entity.PartyEntity;
import org.springframework.lang.NonNull;

import java.util.Collection;

/**
 * Represents a data access object used to access data related to {@link PartyEntity}.
 *
 * @since 1.0.0
 */
public interface PartyPort {

    /**
     * Searches for all existing parties.
     *
     * @return All found parties.
     */
    @NonNull
    Collection<PartyEntity> findAll();
}
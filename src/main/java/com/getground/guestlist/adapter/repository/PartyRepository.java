package com.getground.guestlist.adapter.repository;

import com.getground.guestlist.domain.entity.PartyEntity;
import com.getground.guestlist.domain.port.PartyPort;
import org.springframework.data.jpa.repository.JpaRepository;

interface PartyRepository extends JpaRepository<PartyEntity, Long>, PartyPort {
}

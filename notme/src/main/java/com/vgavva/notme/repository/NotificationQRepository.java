package com.vgavva.notme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgavva.notme.entity.NotificationQ;

@Repository
public interface NotificationQRepository extends JpaRepository<NotificationQ, Long> {



}

package com.vgavva.notme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vgavva.notme.entity.NotmeUser;

public interface NotmeUserRepository extends JpaRepository<NotmeUser, Long> {
	NotmeUser findByUsername(String username);
}

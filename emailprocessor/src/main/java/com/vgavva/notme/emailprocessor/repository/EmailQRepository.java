package com.vgavva.notme.emailprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgavva.notme.emailprocessor.entity.EmailQ;
@Repository
public interface EmailQRepository extends JpaRepository<EmailQ, Long> {

}

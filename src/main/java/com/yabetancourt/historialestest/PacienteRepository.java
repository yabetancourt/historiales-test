package com.yabetancourt.historialestest;

import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long>, RevisionRepository<Paciente, Long, Long> {

}

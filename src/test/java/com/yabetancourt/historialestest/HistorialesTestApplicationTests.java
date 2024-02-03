package com.yabetancourt.historialestest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class HistorialesTestApplicationTests {

	@Autowired
	PacienteRepository repository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TransactionTemplate tx;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		Paciente updated = preparePacienteHistory();
		tx.executeWithoutResult(__ -> {
			Revisions<Long, Paciente> revisions = repository.findRevisions(updated.getId());
			System.out.println("Hay " + revisions.getContent().size() + " cambios");
			for (Revision<Long, Paciente> revision : revisions.getContent()) {
				System.out.println("****************************************************************");
				System.out.println(revision.getMetadata().getRevisionType());
				System.out.println("Estado: " + revision.getEntity().getEstado());
				System.out.println("Fecha del primer cambio: " + revision.getRequiredRevisionInstant());
				System.out.println("Realizado por: " + revision.getEntity().getModificadoPor().getUsername());
			}
		});
	}


	private Paciente preparePacienteHistory() {
		Paciente paciente = new Paciente();
		paciente.setNombre("Yadier");
		paciente.setEstado("ACTIVO");
		Usuario usuario1 = new Usuario("yadier");
		usuarioRepository.save(usuario1);
		paciente.setModificadoPor(usuario1);
		repository.save(paciente);
		assertThat(paciente.getId()).isNotNull();

		paciente.setNombre("Edisbel");
		repository.save(paciente);
		assertThat(paciente).isNotNull();

		Usuario usuario2 = new Usuario("admin");
		usuarioRepository.save(usuario2);
		paciente.setModificadoPor(usuario2);
		paciente.setEstado("SUSPENDIDO");
		repository.save(paciente);

		return paciente;
	}

}

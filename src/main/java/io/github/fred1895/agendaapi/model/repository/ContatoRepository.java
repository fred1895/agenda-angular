package io.github.fred1895.agendaapi.model.repository;

import io.github.fred1895.agendaapi.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}

package io.github.fred1895.agendaapi.model.service;

import io.github.fred1895.agendaapi.model.entity.Contato;
import io.github.fred1895.agendaapi.model.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {
    @Autowired
    public ContatoRepository repository;

    public Contato save(Contato contato) {
        return repository.save(contato);
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }

    public List<Contato> listarContatos() {
        return repository.findAll();
    }

    public void favoritarContato(Long id, Boolean favorito) {
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent( c -> {
            c.setFavorito(favorito);
            repository.save(c);
        });
    }
}

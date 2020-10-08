package io.github.fred1895.agendaapi.model.service;

import io.github.fred1895.agendaapi.model.entity.Contato;
import io.github.fred1895.agendaapi.model.repository.ContatoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

    public void favoritarContato(Long id) {
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent( c -> {
            boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            repository.save(c);
        });
    }

    public byte[] adicionarFoto(Long id, Part arquivo) {
        Optional<Contato> contato = repository.findById(id);
        return contato.map( c -> {
            try {
                InputStream inputStream = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(inputStream, bytes);
                c.setFoto(bytes);
                repository.save(c);
                inputStream.close();
                log.info("Bytes do arquivo " + bytes);
                return bytes;
            } catch (IOException e) {
                return null;
            }
        }).orElse(null);
    }
}

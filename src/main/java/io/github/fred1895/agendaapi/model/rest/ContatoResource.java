package io.github.fred1895.agendaapi.model.rest;

import io.github.fred1895.agendaapi.model.entity.Contato;
import io.github.fred1895.agendaapi.model.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.List;

@RestController
@RequestMapping("/api/contatos")
@CrossOrigin("*")
public class ContatoResource {
    @Autowired
    private ContatoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody Contato contato) {
        return service.save(contato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deletarPorId(id);
    }

    @GetMapping
    public List<Contato> list() {
        return service.listarContatos();
    }

    @PatchMapping("{id}/favorito")
    public void favorite(@PathVariable Long id) {
        service.favoritarContato(id);
    }

    @PutMapping("{id}/foto")
    public byte[] addPhoto(@PathVariable Long id, @RequestParam("foto") Part arquivo) {
        return service.adicionarFoto(id, arquivo);
    }

}

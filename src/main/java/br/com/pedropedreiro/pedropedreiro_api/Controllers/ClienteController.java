package br.com.pedropedreiro.pedropedreiro_api.Controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.pedropedreiro.pedropedreiro_api.Models.Cliente;
import br.com.pedropedreiro.pedropedreiro_api.rdn.ClienteRdn;


@RestController
public class ClienteController {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clientes")
    public List<Cliente> Get() {

        ClienteRdn rdn = new ClienteRdn();
        return rdn.obterTodos();

    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clientes/{id}")
    public Cliente GetById(@PathVariable("id") int id) {

        ClienteRdn rdn = new ClienteRdn();
        return rdn.obterPorId(id);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/clientes")
    public int Post(@RequestBody Cliente pcli) throws SQLException {

        ClienteRdn rdn = new ClienteRdn();
        return rdn.inserir(pcli);

    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("clientes/{id}")
    public int Put(@PathVariable(value = "id") int id, @RequestBody Cliente pCliente) {
        ClienteRdn rdn = new ClienteRdn();
        if (rdn.obterPorId(id).getId() > 0) {
            return rdn.alterar(pCliente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado");
        }
    }
   
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("clientes/{id}")
    public int Delete(@PathVariable(value = "id") int id) {

        ClienteRdn rdn = new ClienteRdn();
        if (rdn.obterPorId(id).getId() > 0) {
            return rdn.deletar(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado");
        }
    }
}
package sn.isi.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.Client;
import sn.isi.service.ClientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {


    private ClientService clientService;

    @GetMapping
    public List<Client> getClient() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable("id") int id) {
        return clientService.getClient(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Client createAppClients(@Valid @RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable("id") int id, @Valid @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") int id)  {clientService.deleteClients(id);}

}

package br.edu.ifpr.controle_de_locadora_vhs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Importe o PasswordEncoder
import org.springframework.stereotype.Service;

import br.edu.ifpr.controle_de_locadora_vhs.entities.Client;
import br.edu.ifpr.controle_de_locadora_vhs.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Client save(Client client) {

        if (client.getPassword() != null && !client.getPassword().isEmpty()) {
            client.setPassword(passwordEncoder.encode(client.getPassword()));
        }
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return clientRepository.findByEmail(email) != null;
    }
}
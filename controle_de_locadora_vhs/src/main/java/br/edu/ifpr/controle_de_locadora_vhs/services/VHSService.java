package br.edu.ifpr.controle_de_locadora_vhs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpr.controle_de_locadora_vhs.entities.VHS;
import br.edu.ifpr.controle_de_locadora_vhs.repositories.VHSRepository;

@Service
public class VHSService {
    @Autowired
    VHSRepository vhsRepository;

    public List<VHS> findAll() {
        return vhsRepository.findAll();
    }

    public VHS save(VHS vhs) {
        return vhsRepository.save(vhs);
    }

    public Optional<VHS> findById(Long id) {
        return vhsRepository.findById(id);
    }

    public void deleteById(Long id) {
        vhsRepository.deleteById(id);
    }
}
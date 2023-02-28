package drampas.springframework.petclinic.services;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet findByLastName(String lastName);
    Vet save(Vet vet);
    Set<Vet> findAll();
}

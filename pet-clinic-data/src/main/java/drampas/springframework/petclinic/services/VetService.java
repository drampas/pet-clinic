package drampas.springframework.petclinic.services;


import drampas.springframework.petclinic.model.Vet;

import java.util.Set;

public interface VetService extends CrudService<Vet,Long> {

    Vet findByLastName(String lastName);
}

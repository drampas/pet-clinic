package drampas.springframework.petclinic.services;


import drampas.springframework.petclinic.model.Vet;


public interface VetService extends CrudService<Vet,Long> {

    Vet findByLastName(String lastName);
}

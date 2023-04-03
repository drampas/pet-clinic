package drampas.springframework.petclinic.services;

import drampas.springframework.petclinic.model.Owner;



public interface OwnerService extends CrudService<Owner,Long> {
    Owner findByLastName(String lastName);
}

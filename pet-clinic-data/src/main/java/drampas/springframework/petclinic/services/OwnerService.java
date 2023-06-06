package drampas.springframework.petclinic.services;

import drampas.springframework.petclinic.model.Owner;

import java.util.List;


public interface OwnerService extends CrudService<Owner,Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}

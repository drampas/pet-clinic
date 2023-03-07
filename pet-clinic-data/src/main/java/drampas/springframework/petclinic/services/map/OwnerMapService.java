package drampas.springframework.petclinic.services.map;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.model.Pet;
import drampas.springframework.petclinic.services.OwnerService;
import drampas.springframework.petclinic.services.PetService;
import drampas.springframework.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
@Service
public class OwnerMapService extends AbstractMapService<Owner,Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }
//making sure that both pet and petType have an id before saving the owner
    @Override
    public Owner save(Owner object) {
        if(object!=null){
            if(object.getPets()!= null){
                object.getPets().forEach(pet -> {
                    if(pet.getPetType()!=null){
                        if(pet.getPetType().getId()==null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }else{
                        throw new RuntimeException("Pet type required");
                    }
                    if(pet.getId()==null){
                        Pet savedPet =petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        }else
            return null;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        // TODO: 2/3/2023
        Collection<Owner> collection=map.values();

        return null;
    }
}

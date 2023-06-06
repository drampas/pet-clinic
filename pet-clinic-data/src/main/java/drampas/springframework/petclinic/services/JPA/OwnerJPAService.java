package drampas.springframework.petclinic.services.JPA;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.repositories.OwnerRepository;
import drampas.springframework.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("jpa")
public class OwnerJPAService implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerJPAService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        ownerRepository.save(object);
        System.out.println("######################");
        System.out.println("######################");
        System.out.println("######################");
        return object;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners=new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
    @Override
    public List<Owner> findAllByLastNameLike(String lastName){
        return ownerRepository.findAllByLastNameLike(lastName);
    }
}

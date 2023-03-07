package drampas.springframework.petclinic.services.map;

import drampas.springframework.petclinic.model.Specialty;
import drampas.springframework.petclinic.model.Vet;
import drampas.springframework.petclinic.services.SpecialtyService;
import drampas.springframework.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class VetMapService extends AbstractMapService<Vet,Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
    //making sure that specialty has an id before saving the vet
    @Override
    public Vet save(Vet object) {
        if(object!=null){
            if(object.getSpecialties()!=null) {
                object.getSpecialties().forEach(specialty -> {
                    if (specialty.getId() == null) {
                        Specialty savedSpecialty = specialtyService.save(specialty);
                        specialty.setId(savedSpecialty.getId());
                    }
                });
            }
            return super.save(object);
        }else
            return null;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet findByLastName(String lastName) {
        // TODO: 2/3/2023  
        return null;
    }
}

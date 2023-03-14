package drampas.springframework.petclinic.services.JPA;

import drampas.springframework.petclinic.model.Specialty;
import drampas.springframework.petclinic.repositories.SpecialtyRepository;
import drampas.springframework.petclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class SpecialtyJPAService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyJPAService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty findById(Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties=new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }
}

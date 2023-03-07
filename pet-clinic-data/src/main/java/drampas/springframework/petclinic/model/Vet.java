package drampas.springframework.petclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {

    private Set<Specialty> Specialties=new HashSet<>();

    public Set<Specialty> getSpecialties() {
        return Specialties;
    }

    public void setSpecialties(Set<Specialty> Specialties) {
        this.Specialties = Specialties;
    }
}

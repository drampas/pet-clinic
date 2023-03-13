package drampas.springframework.petclinic.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet extends Person {
    @ManyToMany
    @JoinTable(name = "vet_specialties",joinColumns = @JoinColumn(name = "vet_id"),
    inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> Specialties=new HashSet<>();

    public Set<Specialty> getSpecialties() {
        return Specialties;
    }

    public void setSpecialties(Set<Specialty> Specialties) {
        this.Specialties = Specialties;
    }
}

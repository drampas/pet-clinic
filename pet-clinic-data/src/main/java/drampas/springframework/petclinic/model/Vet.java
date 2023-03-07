package drampas.springframework.petclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {

    private Set<VetSpecialty> vetSpecialties=new HashSet<>();

    public Set<VetSpecialty> getVetSpecialties() {
        return vetSpecialties;
    }

    public void setVetSpecialties(Set<VetSpecialty> vetSpecialties) {
        this.vetSpecialties = vetSpecialties;
    }
}

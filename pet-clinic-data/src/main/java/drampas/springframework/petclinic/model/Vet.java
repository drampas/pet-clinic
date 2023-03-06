package drampas.springframework.petclinic.model;

public class Vet extends Person{

    public VetSpecialty getVetSpecialty() {
        return vetSpecialty;
    }

    public void setVetSpecialty(VetSpecialty vetSpecialty) {
        this.vetSpecialty = vetSpecialty;
    }

    private VetSpecialty vetSpecialty;
}

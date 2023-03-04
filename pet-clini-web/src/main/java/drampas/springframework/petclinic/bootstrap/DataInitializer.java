package drampas.springframework.petclinic.bootstrap;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.model.Vet;
import drampas.springframework.petclinic.services.OwnerService;
import drampas.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;

    public DataInitializer(VetService vetService, OwnerService ownerService) {
        this.vetService = vetService;
        this.ownerService = ownerService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1=new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Doe");

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setFirstName("Mark");
        owner2.setLastName("Cole");

        ownerService.save(owner2);
        System.out.println("Loaded owners.....");

        Vet vet1=new Vet();
        vet1.setFirstName("Annie");
        vet1.setLastName("Pope");

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("George");
        vet2.setLastName("Baker");

        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }
}

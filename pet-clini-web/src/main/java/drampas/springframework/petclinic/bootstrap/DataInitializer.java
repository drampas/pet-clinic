package drampas.springframework.petclinic.bootstrap;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.model.Pet;
import drampas.springframework.petclinic.model.PetType;
import drampas.springframework.petclinic.model.Vet;
import drampas.springframework.petclinic.services.OwnerService;
import drampas.springframework.petclinic.services.PetTypeService;
import drampas.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public DataInitializer(VetService vetService, OwnerService ownerService,PetTypeService petTypeService) {
        this.vetService = vetService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog=new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat=new PetType();
        dog.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1=new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Doe");
        owner1.setAddress("Lampaki 142");
        owner1.setCity("Thessaloniki");
        owner1.setTelephone("1234567892");

        Pet johnsPet=new Pet();
        johnsPet.setName("Dogo");
        johnsPet.setPetType(savedDogPetType);
        johnsPet.setBirthDate(LocalDate.now());
        johnsPet.setOwner(owner1);
        owner1.getPets().add(johnsPet);

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setFirstName("Mark");
        owner2.setLastName("Cole");
        owner2.setAddress("Lampraki 147");
        owner2.setCity("Thessaloniki");
        owner2.setTelephone("1234567992");

        Pet marksPet=new Pet();
        marksPet.setName("Kit-cat");
        marksPet.setPetType(savedCatPetType);
        marksPet.setBirthDate(LocalDate.now());
        marksPet.setOwner(owner2);
        owner2.getPets().add(marksPet);

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

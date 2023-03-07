package drampas.springframework.petclinic.bootstrap;

import drampas.springframework.petclinic.model.*;
import drampas.springframework.petclinic.services.OwnerService;
import drampas.springframework.petclinic.services.PetTypeService;
import drampas.springframework.petclinic.services.SpecialtyService;
import drampas.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataInitializer(VetService vetService, OwnerService ownerService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.vetService = vetService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count == 0){
            loadData();
        }
    }
    private void loadData() {
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

        Specialty surgery=new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry=new Specialty();
        surgery.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Vet vet1=new Vet();
        vet1.setFirstName("Annie");
        vet1.setLastName("Pope");
        vet1.getSpecialties().add(savedSurgery);

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("George");
        vet2.setLastName("Baker");
        vet2.getSpecialties().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }
}

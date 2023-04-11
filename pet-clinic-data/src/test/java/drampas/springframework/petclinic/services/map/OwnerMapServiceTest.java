package drampas.springframework.petclinic.services.map;


import drampas.springframework.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    PetMapService petMapService;
    PetTypeMapService petTypeMapService;
    OwnerMapService ownerMapService;
    Long id=1L;
    String lastName="Smith";

    @BeforeEach
    void setUp() {
        ownerMapService =new OwnerMapService(petMapService,petTypeMapService);
        Owner owner=new Owner();
        owner.setId(id);
        owner.setLastName(lastName);
        ownerMapService.save(owner);
    }

    @Test
    void findById() {
        Owner savedOwner=ownerMapService.findById(id);
        assertEquals(id,savedOwner.getId());
    }

    @Test
    void saveExistingId() {
        Owner owner2=new Owner();
        owner2.setId(2L);
        Owner savedOwner2 = ownerMapService.save(owner2);
        assertEquals(2L,savedOwner2.getId());
    }
    @Test
    void saveNoId(){
        Owner savedOwner=ownerMapService.save(new Owner());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(id));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(id);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith=ownerMapService.findByLastName(lastName);
        assertNotNull(smith);
        assertEquals(id,smith.getId());
    }
    @Test
    void findByLastNameNotFound() {
        Owner smith=ownerMapService.findByLastName("foo");
        assertNull(smith);
    }
}
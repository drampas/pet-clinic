package drampas.springframework.petclinic.services.JPA;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class OwnerJPAServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerJPAService ownerJPAService;
    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner=new Owner();
        returnOwner.setId(1L);
        returnOwner.setLastName("Smith");
    }

    @Test
    void findById() {
        Mockito.when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner =ownerJPAService.findById(1L);
        assertNotNull(owner);
        assertEquals(1L,owner.getId());
    }
    @Test
    void findByIdNotFound() {
        Mockito.when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Owner owner =ownerJPAService.findById(1L);
        assertNull(owner);
    }
    @Test
    void save() {
        Owner ownerToSave=new Owner();
        ownerToSave.setId(1L);
        Mockito.when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(returnOwner);
        Owner savedOwner = ownerJPAService.save(returnOwner);
        assertNotNull(savedOwner);
        Mockito.verify(ownerRepository).save(ArgumentMatchers.any());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet=new HashSet<>();
        Owner owner1=new Owner();
        Owner owner2=new Owner();
        ownerSet.add(owner1);
        ownerSet.add(owner2);
        Mockito.when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> owners=ownerJPAService.findAll();
        assertNotNull(owners);
        assertEquals(2,owners.size());
    }

    @Test
    void deleteById() {
        ownerJPAService.deleteById(1L);
        Mockito.verify(ownerRepository).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void delete() {
        ownerJPAService.delete(returnOwner);
        Mockito.verify(ownerRepository).delete(ArgumentMatchers.any());
    }

    @Test
    void findByLastName() {
        Mockito.when(ownerRepository.findByLastName(ArgumentMatchers.any())).thenReturn(returnOwner);
        Owner smith = ownerJPAService.findByLastName("Smith");
        assertEquals("Smith",smith.getLastName());
        Mockito.verify(ownerRepository).findByLastName(ArgumentMatchers.any());
    }
}
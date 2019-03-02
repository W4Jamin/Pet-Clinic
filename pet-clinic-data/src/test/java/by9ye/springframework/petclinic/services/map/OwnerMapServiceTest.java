package by9ye.springframework.petclinic.services.map;

import by9ye.springframework.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    String lname = "yin";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapServiceImpl(), new PetMapService());

        ownerMapService.save(Owner.builder().id(1L).lastName(lname).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistId() {
        Long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();

        Owner saveOwner = ownerMapService.save(owner2);

        assertEquals(id, saveOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner3 = ownerMapService.findByLastName(lname);
        assertNotNull(owner3);
    }
}
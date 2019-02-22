package by9ye.springframework.petclinic.bootstrap;

import by9ye.springframework.petclinic.model.*;
import by9ye.springframework.petclinic.services.OwnerService;
import by9ye.springframework.petclinic.services.PetTypeService;
import by9ye.springframework.petclinic.services.SpecialtyService;
import by9ye.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0){
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Bin");
        owner1.setLastName("Yin");
        owner1.setAddress("474 Seymour Rd APT3");
        owner1.setCity("Cville");
        owner1.setTelephone("4342829873");

        Pet binsPet = new Pet();
        binsPet.setPetType(savedDogPetType);
        binsPet.setOwner(owner1);
        binsPet.setBirthDate(LocalDate.now());
        binsPet.setName("baoyuan");
        owner1.getPets().add(binsPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jiamin");
        owner2.setLastName("Cheng");
        owner2.setAddress("474 Seymour Rd APT3");
        owner2.setCity("Cville");
        owner2.setTelephone("4342829873");

        Pet jiaminsPet = new Pet();
        jiaminsPet.setName("yuanbao");
        jiaminsPet.setPetType(savedCatPetType);
        jiaminsPet.setBirthDate(LocalDate.now());
        jiaminsPet.setOwner(owner2);
        owner2.getPets().add(jiaminsPet);

        ownerService.save(owner2);

        System.out.println("Owners loaded!");

        Vet vet1 = new Vet();
        vet1.setFirstName("Doctor");
        vet1.setLastName("Yin");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Doctor");
        vet2.setLastName("Who");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Vets loaded!");
    }
}

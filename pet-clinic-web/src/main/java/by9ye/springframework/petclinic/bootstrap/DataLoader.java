package by9ye.springframework.petclinic.bootstrap;

import by9ye.springframework.petclinic.model.Owner;
import by9ye.springframework.petclinic.model.Vet;
import by9ye.springframework.petclinic.services.OwnerService;
import by9ye.springframework.petclinic.services.VetService;
import by9ye.springframework.petclinic.services.map.OwnerServiceMap;
import by9ye.springframework.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Bin");
        owner1.setLastName("Yin");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Jiamin");
        owner2.setLastName("Cheng");

        ownerService.save(owner2);

        System.out.println("Owners loaded!");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Doctor");
        vet1.setLastName("Yin");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Doctor");
        vet2.setLastName("Who");

        vetService.save(vet2);

        System.out.println("Vets loaded!");

    }
}

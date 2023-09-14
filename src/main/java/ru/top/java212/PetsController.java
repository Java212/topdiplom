package ru.top.java212;

import jakarta.transaction.Transactional;
import org.openapitools.api.PetsApi;
import org.openapitools.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PetsController implements PetsApi {
    @Autowired
    public PetsRepository repo;

    @Override
    @Transactional
    public ResponseEntity<Void> createPets(Pet pet) {
        repo.save(new PetEntity(pet.getId(), pet.getName()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(Integer limit) {
        List<Pet> petList = repo.findAll().stream().map(entity -> {
            Pet p = new Pet();
            p.setId(entity.getId());
            p.setName(entity.getName());
            p.setTag(entity.getTag());
            return p;
        }).toList();
        ResponseEntity<List<Pet>> response = new ResponseEntity<>(petList, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<Pet> showPetById(String petId) {
        PetEntity petEntity = repo.findById(Long.parseLong(petId)).get();
        return new ResponseEntity<>(new Pet(petEntity.getId(), petEntity.getName()), HttpStatus.OK);
    }
}

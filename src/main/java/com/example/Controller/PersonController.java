/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Controller;
import com.example.AppConstants;
import com.example.Movie;
import com.example.Persona;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Gonzalo
 */

@RestController
public class PersonController {
    private List<Persona> personList = new ArrayList<>();
    
    @GetMapping
    public String Hola(){
        return "Hola";
    }
    
    @GetMapping("/list")
    public List<Persona> getAllPersons(){
        Collections.sort(personList, Comparator.comparing(Persona::getLastName).thenComparing(Persona::getFirstName));
    return personList;
    }
    
    @GetMapping("/{id}/movies")
    public List<Movie> getPersonMovies(@PathVariable Long id) {
        Persona persona = personList.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
        if (persona != null) {
        return persona.getFavouriteMovies();
        }
        return null;
    }
    
    @GetMapping("/{id}")
    public Persona getPersonById(@PathVariable Long id){
        return personList.stream().filter(person -> person.getId().equals(id)).findFirst().orElse(null);
    }
    
    @GetMapping("/search")
    public List<Persona> searchPersonByName(@RequestParam String name) {
        return personList.stream()
            .filter(person -> person.getFirstName().equalsIgnoreCase(name) || person.getLastName().equalsIgnoreCase(name))
            .collect(Collectors.toList());
}
    
    @PostMapping
    public Persona addPerson(@RequestBody Persona persona){
        personList.add(persona);
        return persona;
    }
    
    @PostMapping("/{id}/movies")
    public Persona addMovieToPerson(@PathVariable Long id, @RequestBody Movie movie) {
        Persona persona = personList.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
        if (persona != null) {
            List<Movie> favouriteMovies = persona.getFavouriteMovies();
            if (favouriteMovies.size() < AppConstants.MAX_MOVIES_PER_PERSON) {
                favouriteMovies.add(movie);
            }
        }
        return persona;
    }
    
    @PutMapping("/{id}")
    public Persona updatePerson(@PathVariable Long id, @RequestBody Persona updatedPersona){
        Persona persona = personList.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if(persona != null){
            if (updatedPersona.getFirstName() != null) {
            persona.setFirstName(updatedPersona.getFirstName());
        }
        if (updatedPersona.getLastName() != null) {
            persona.setLastName(updatedPersona.getLastName());
        }
        if (updatedPersona.getBirthdate() != null) {
            persona.setBirthdate(updatedPersona.getBirthdate());
        }
        if (updatedPersona.isHasInsurance() != persona.isHasInsurance()) {
            persona.setHasInsurance(updatedPersona.isHasInsurance());
        }
        if (updatedPersona.getFavouriteMovies() != null) {
            persona.setFavouriteMovies(updatedPersona.getFavouriteMovies());
        }
        }
        return persona;
    }
    
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personList.removeIf(person -> person.getId().equals(id));
    }
    
    @DeleteMapping("/{id}/movies/{movieId}")
    public Persona removeMovieFromPerson(@PathVariable Long id, @PathVariable Long movieId) {
        Persona persona = personList.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
        if (persona != null) {
            List<Movie> favouriteMovies = persona.getFavouriteMovies();
            favouriteMovies.removeIf(movie -> movie.getId().equals(movieId));
        }
        return persona;
    }
}

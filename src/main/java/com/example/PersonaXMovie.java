/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

/**
 *
 * @author Gonzalo
 */
public class PersonaXMovie {
    private Long id;
    private Long idPersona;
    private Long idMovie;

    public PersonaXMovie(){
        
    }
    
    public PersonaXMovie(Long id, Long idPersona, Long idMovie) {
        this.id = id;
        this.idPersona = idPersona;
        this.idMovie = idMovie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }
}

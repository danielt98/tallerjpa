package co.edu.unicauca.asae.core.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.core.modelo.Curso;

public interface CursosRepository extends CrudRepository<Curso,Integer> {
    
}

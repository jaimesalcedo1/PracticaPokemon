package repository.pokemon;

import exceptions.PokemonException;
import models.Pokemon;
import repository.crud.CrudRepository;

import java.sql.SQLException;
import java.util.List;

/*
* Interfaz que extiende de la interfaz CrudRepository y añade el método propio
* findByNombre
* */
public interface PokemonRepository extends CrudRepository<Pokemon, Long, PokemonException> {
    // Buscar por nombre
    List<Pokemon> findByNombre(String nombre) throws SQLException;
}

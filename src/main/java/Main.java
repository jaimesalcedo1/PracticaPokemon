import controllers.PokemonController;
import database.DatabaseManager;
import repository.pokemon.PokemonRepositoryImpl;

import java.sql.SQLException;

/**
 * @author Jaime Salcedo
 * @author Raúl Rodríguez
 * clase main que instancia el controlador para filtrar los datos y exportar a csv
 * para posteriormente trabajar en una base de datos con el manejador de la base de
 * datos
 * */
public class Main {
    public static void main(String[] args) throws SQLException {

        var pokemonController = PokemonController.getInstance();
        var dbm = DatabaseManager.getInstance();
        var pokemonRepoImpl = PokemonRepositoryImpl.getInstance(dbm);

        pokemonRepoImpl.createTable();
        System.out.println("\n--- TODOS LOS POKEMON DE LA BASE DE DATOS ---");
        pokemonRepoImpl.findAll().forEach(System.out::println);
        System.out.println("\n--- DATOS DE PIKACHU ---");
        pokemonRepoImpl.findByNombre("Pikachu").forEach(System.out::println);
    }
}
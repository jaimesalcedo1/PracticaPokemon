package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Pokedex;
import models.Pokemon;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.*;

public class PokemonController {
    private static PokemonController instance;
    private Pokedex pokedex;
    private PokemonController(){
        cargarPokedex();
        procesarStreams();
    }
    public static PokemonController getInstance(){
        if(instance == null){
            instance = new PokemonController();
        }
        return instance;
    }

    private void cargarPokedex() {
        Path currentRelativePath = Paths.get("");
        String ruta = currentRelativePath.toAbsolutePath().toString();
        String dir = ruta + File.separator + "data";
        String pokemonFile = dir + File.separator + "pokemon.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(Reader reader = Files.newBufferedReader(Paths.get(pokemonFile))) {

            this.pokedex = gson.fromJson(reader, new TypeToken<Pokedex>(){}.getType());
            System.out.println("Pokedex cargada, hay: "+ pokedex.pokemon.size());
        } catch (Exception e) {
            System.out.println("Error cargando la pokedex");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void procesarStreams(){

        System.out.println("\n--- LISTA DE POKEMONS ---");
        this.pokedex.pokemon
                .forEach(System.out::println);

        System.out.println("\n--- NOMBRE DE LOS 10 PRIMEROS POKEMON ---");
        this.pokedex.pokemon.stream()
                .limit(10)
                .map(Pokemon::getName)
                .forEach(System.out::println);

        System.out.println("\n--- NOMBRE DE LOS 5 ULTIMOS POKEMON ---");
        long count = this.pokedex.pokemon.size();
        this.pokedex.pokemon.stream()
                .skip(count - 5)
                .map(Pokemon::getName)
                .forEach(System.out::println);

        System.out.println("\n--- OBTENER LOS DATOS DE PIKACHU ---");
        this.pokedex.pokemon.stream()
                .filter(x -> x.getName().equals("Pikachu"))
                .forEach(System.out::println);

        System.out.println("\n--- OBTENER LA EVOLUCIÓN DE CHARMANDER ---");
        this.pokedex.pokemon.stream()
                .filter(x -> x.getName().equals("Charmander"))
                .map(Pokemon::getNext_evolution)
                .forEach(System.out::println);

        System.out.println("\n--- OBTENER EL NOMBRE DE LOS POKEMON DE TIPO FUEGO ---");
        this.pokedex.pokemon.stream()
                .filter(x -> x.getType().contains("Fire"))
                .map(Pokemon::getName)
                .forEach(System.out::println);

        System.out.println("\n--- OBTENER EL NOMRE DE LOS POKEMON CON DEBILIDAD AGUA O ELECTRICO ---");
        this.pokedex.pokemon.stream()
                .filter(x -> x.getWeaknesses().contains("Water") || x.getWeaknesses().contains("Electric"))
                .map(Pokemon::getName)
                .forEach(System.out::println);

        System.out.println("\n--- NUMERO DE POKEMONS CON UNA SOLA DEBILIDAD");
        System.out.println(this.pokedex.pokemon.stream()
                .filter(x -> x.getWeaknesses().size() == 1)
                .count());

        //modificar
        System.out.println("\n--- POKEMON CON MAS DEBILIDADES ---");
        IntSummaryStatistics numDebilidades = this.pokedex.pokemon.stream().mapToInt(x -> x.getWeaknesses().size()).summaryStatistics();
        System.out.println("Con un numero de " + numDebilidades.getMax() + " debilidades están los pokemons: ");
        this.pokedex.pokemon.stream()
                .filter(x -> x.getWeaknesses().size() == numDebilidades.getMax())
                .map(Pokemon::getName)
                .forEach(System.out::println);

        System.out.println("\n--- POKEMON CON MENOS EVOLUCIONES ---");
        this.pokedex.pokemon.stream()
                .filter(x -> x.getNext_evolution() == null && x.getPrev_evolution() == null)
                .map(Pokemon::getName)
                .forEach(System.out::println);

        System.out.println("\n--- POKEMON CON UNA EVOLUCIÓN QUE NO ES DE TIPO FIRE ---");
        this.pokedex.pokemon.stream()
                .filter(x -> x.getNext_evolution()!= null && x.getNext_evolution().size() == 1 && !x.getType().contains("Fire"))
                .map(Pokemon::getName)
                .forEach(System.out::println);

        System.out.println("\n--- POKEMON MAS PESADO ---");
        var masPesado = this.pokedex.pokemon.stream()
                .max(Comparator.comparing(x -> Double.parseDouble(x.getWeight().replace("kg",""))))
                .map(Pokemon::getName).orElse("");
        System.out.println(masPesado);

        System.out.println("\n--- POKEMON MAS ALTO ---");
        var masAlto = this.pokedex.pokemon.stream()
                .max(Comparator.comparing(x -> Double.parseDouble(x.getHeight().replace("m", ""))))
                .map(Pokemon::getName).orElse("");
        System.out.println(masAlto);

        System.out.println("\n--- POKEMON CON EL NOMBRE MAS LARGO ---");
        this.pokedex.pokemon.stream()
                .max(Comparator.comparingInt(x -> x.getName().length()))
                .map(Pokemon::getName).ifPresent(System.out::println);

        System.out.println("\n--- MEDIA DE EVOLUCIONES DE LOS POKEMON ---");
        double mediaEv = this.pokedex.pokemon.stream()
                .filter(x -> x.getNext_evolution() != null)
                .mapToDouble(x -> x.getNext_evolution().size())
                .average()
                .orElse(0);
        System.out.println(mediaEv);

        System.out.println("\n--- MEDIA DE DEBILIDADES DE LOS POKEMON ---");
        double mediaDeb = this.pokedex.pokemon.stream()
                .mapToDouble(x -> x.getWeaknesses().size())
                .average()
                .orElse(0);
        System.out.println(mediaDeb);

        System.out.println("\n --- POKEMONS AGRUPADOS POR TIPO ---");
        var res1 = this.pokedex.pokemon.stream()
                .collect(groupingBy(Pokemon::getType, mapping(Pokemon::getName, toList())));
        System.out.println(res1);

        System.out.println("\n --- POKEMONS AGRUPADOS POR DEBILIDAD ---");
        var res2 = this.pokedex.pokemon.stream()
                .collect(groupingBy(Pokemon::getWeaknesses, mapping(Pokemon::getName, toList())));
        System.out.println(res2);

        System.out.println("\n--- POKEMONS AGRUPADOS POR NUMERO DE EVOLUCIONES ---");
        var res3 = this.pokedex.pokemon.stream()
                .filter(x -> x.getNext_evolution() != null)
                .collect(groupingBy(x -> x.getNext_evolution().size(), mapping(Pokemon::getName, toList())));
        System.out.println(res3);

        System.out.println("\n--- SACAR LA DEBILIDAD MAS COMUN ---");
        var values = res2.values();
        var maxValueLength = values.stream().mapToInt(List::size).max().orElse(0);
        for (Map.Entry<ArrayList<String>, List<String>> entry : res2.entrySet()){
            if(entry.getValue().size() == maxValueLength){
                System.out.println(entry.getKey());
            }
        }



    };
}

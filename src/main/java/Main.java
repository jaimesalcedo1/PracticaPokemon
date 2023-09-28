import controllers.PokemonController;
/*
* clase main con una instancia de controlador
* */
public class Main {
    public static void main(String[] args) {

        var pokemonController = PokemonController.getInstance();

        PokemonController csv = new PokemonController();
        csv.escribirCsv("datos.csv");
        csv.leerCsv("datos.csv");
    }
}
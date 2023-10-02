package repository.pokemon;

import database.DatabaseManager;
import exceptions.PokemonException;
import exceptions.PokemonNoAlmacenadoException;
import models.Pokedex;
import models.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*
* esta clase implementa la interfaz PokemonRepository que contiene todos los métodos
* para realizar un CRUD en una base de datos.
* */
public class PokemonRepositoryImpl implements PokemonRepository{
    private static PokemonRepositoryImpl instance;
    private final Logger logger = LoggerFactory.getLogger(PokemonRepositoryImpl.class);
    private final DatabaseManager dbm;
    private Pokedex pokedex;

    private PokemonRepositoryImpl(DatabaseManager dbm){
        this.dbm = dbm;
    }

    public static PokemonRepositoryImpl getInstance(DatabaseManager dbm){
        if(instance == null){
            instance = new PokemonRepositoryImpl(dbm);
        }
        return instance;
    }

    /*
    *
    * Este método lee el csv datos.csv y transforma los datos a filas de una
    * tabla POKEMON dentro de la base de datos mediante una sentencia SQL
    * */
    public void createTable(){
        logger.debug("Guardando los pokemon en la base de datos");
        String insertAllQuery = "INSERT INTO POKEMON(ID, numero, nombre, altura, peso) VALUES(? ,?, ?, ?, ?)";

        try{
            var connection = dbm.getConnection();
            var pstm = connection.prepareStatement(insertAllQuery);
            String csvRuta = Paths.get("").toAbsolutePath() + File.separator + "data" + File.separator + "datos.csv";
            BufferedReader br = new BufferedReader(new FileReader(csvRuta));
            String line;
            int count = 0;
            br.readLine();

            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                String ID = data[0];
                String numero = data[1];
                String nombre = data[2];
                String altura = data[3];
                String peso = data[4];

                pstm.setInt(1, Integer.parseInt(ID));
                pstm.setString(2, numero);
                pstm.setString(3, nombre);
                pstm.setString(4, altura);
                pstm.setString(5, peso);
                pstm.addBatch();
            }
            br.close();
            pstm.executeBatch();
            connection.close();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("base de datos cargada");

    }

    @Override
    public Pokemon save(Pokemon pokemon) throws SQLException, PokemonNoAlmacenadoException {

        return null;
    }

    @Override
    public Pokemon update(Pokemon pokemon) throws SQLException, PokemonException {
        return null;
    }

    @Override
    public Optional<Pokemon> findById(Long aLong) throws SQLException {
        return Optional.empty();
    }

    /*
    *
    * este método realiza un select * de la tabla POKEMON y transforma cada fila
    * en un objeto pokemon que va almacenando en una lista para luego devolverla
    * @return List<Pokemon>
    * */
    @Override
    public List<Pokemon> findAll() throws SQLException {

        logger.debug("Obteniendo todos los pokemon");
        var query = "SELECT * FROM POKEMON";
        try (var connection = dbm.getConnection();
             var pstm = connection.prepareStatement(query)
        ) {
            var rs = pstm.executeQuery();
            var lista = new ArrayList<Pokemon>();
            while (rs.next()) {
                Pokemon pokemon = Pokemon.builder()
                        .id(rs.getInt("ID"))
                        .num(rs.getString("numero"))
                        .name(rs.getString("nombre"))
                        .height(rs.getString("altura"))
                        .weight(rs.getString("peso"))
                        .build();
                lista.add(pokemon);
            }
            return lista;
        }
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public void deleteAll() throws SQLException {

    }

    @Override
    public List<Pokemon> findByNombre(String nombre) throws SQLException {
        logger.debug("Datos del pokemon: " + nombre);
        String query = "SELECT * FROM POKEMON WHERE nombre LIKE ?";
        try (var connection = dbm.getConnection();
             var ptsm = connection.prepareStatement(query)
        ) {
            ptsm.setString(1, "%" + nombre + "%");
            var rs = ptsm.executeQuery();
            var lista = new ArrayList<Pokemon>();
            while (rs.next()) {

                Pokemon pokemon = Pokemon.builder()
                        .id(rs.getInt("ID"))
                        .num(rs.getString("numero"))
                        .name(rs.getString("nombre"))
                        .height(rs.getString("altura"))
                        .weight(rs.getString("peso"))
                        .build();

                lista.add(pokemon);
            }
            return lista;
        }
    }
}

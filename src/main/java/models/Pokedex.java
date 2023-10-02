package models;

import lombok.Data;

import java.util.ArrayList;

/*
* Clase Pokedex que contiene un ArrayList de objetos Pokemon
* */
@Data
public class Pokedex {
	public ArrayList<Pokemon> pokemon;
}

package models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

/*
* Clase POJO Pokemon
* */
@Data
@Builder
public class Pokemon {
	private String img;
	private String egg;
	private String candy;
	private String num;
	private String weight;
	private ArrayList<String> type;
	private ArrayList<String> weaknesses;
	private String name;
	private double avg_spawns;
	private ArrayList<Object> multipliers;
	private int id;
	private String spawn_time;
	private String height;
	private Object spawn_chance;
	private ArrayList<PrevEvolution> prev_evolution;
	private int candy_count;
	private ArrayList<NextEvolution> next_evolution;

	@Override
	public String toString(){
		return "Pokemon{" +
				"id=" + id +
				", num='" + num + '\'' +
				", name='" + name + '\'' +
				", img='" + img + '\'' +
				", type=" + type +
				", height='" + height + '\'' +
				", weight='" + weight + '\'' +
				", candy='" + candy + '\'' +
				", candy_count=" + candy_count +
				", egg='" + egg + '\'' +
				", spawn_chance=" + spawn_chance +
				", avg_spawns=" + avg_spawns +
				", spawn_time='" + spawn_time + '\'' +
				", multipliers=" + multipliers +
				", weaknesses=" + weaknesses +
				", next_evolution=" + next_evolution +
				", prev_evolution=" + prev_evolution +
				'}';
	}
}

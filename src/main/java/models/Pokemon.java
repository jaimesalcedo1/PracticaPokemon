package models;

import lombok.Data;

import java.util.ArrayList;

@Data
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

	public String getImg() {
		return img;
	}

	public String getEgg() {
		return egg;
	}

	public String getCandy() {
		return candy;
	}

	public String getNum() {
		return num;
	}

	public String getWeight() {
		return weight;
	}

	public ArrayList<String> getType() {
		return type;
	}

	public ArrayList<String> getWeaknesses() {
		return weaknesses;
	}

	public String getName() {
		return name;
	}

	public double getAvg_spawns() {
		return avg_spawns;
	}

	public ArrayList<Object> getMultipliers() {
		return multipliers;
	}

	public int getId() {
		return id;
	}

	public String getSpawn_time() {
		return spawn_time;
	}

	public String getHeight() {
		return height;
	}

	public Object getSpawn_chance() {
		return spawn_chance;
	}

	public ArrayList<PrevEvolution> getPrev_evolution() {
		return prev_evolution;
	}

	public int getCandy_count() {
		return candy_count;
	}

	public ArrayList<NextEvolution> getNext_evolution() {
		return next_evolution;
	}

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

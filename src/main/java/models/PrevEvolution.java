package models;

import lombok.Data;

@Data
public class PrevEvolution {
	private String num;
	private String name;

	@Override
	public String toString(){
		return "{" + "num=" + num + ", name=" + name + "}";
	}
}

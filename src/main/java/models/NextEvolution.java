package models;

import lombok.Data;

/*
* Clase POJO NextEvolution
* */
@Data
public class NextEvolution {
	private String num;
	private String name;

	@Override
	public String toString(){
		return "{" + "num=" + num + ", name=" + name + "}";
	}
}


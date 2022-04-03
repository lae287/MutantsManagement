package com.example.mutants.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStats{

	private Integer count_mutant_dna;
	private Integer count_human_dna;
	private float ratio;

	public Integer getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(Integer count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public Integer getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(Integer count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

}

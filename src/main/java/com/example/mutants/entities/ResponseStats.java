package com.example.mutants.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStats extends Response{
	
	private Integer isMutant;
	private Integer isntMutant;
	private float ratio;
	
	public Integer getIsMutant() {
		return isMutant;
	}
	public void setIsMutant(Integer isMutant) {
		this.isMutant = isMutant;
	}
	public Integer getIsntMutant() {
		return isntMutant;
	}
	public void setIsntMutant(Integer isntMutant) {
		this.isntMutant = isntMutant;
	}
	public float getRatio() {
		return ratio;
	}
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
	public ResponseStats (String messageResponse) {
		super(messageResponse);

	}

}

package com.example.mutants.services;

import com.example.mutants.entities.Mutants;
import com.example.mutants.entities.Stats;

public interface MutantsInterface {
	
	public boolean isMutant(Mutants mutant);
	
	public boolean createMutant(Mutants mutant);
	
	public boolean updateStats(boolean isMutant);
	
	public Stats getStats();

}

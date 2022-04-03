package com.example.mutants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mutants.entities.Mutants;
import com.example.mutants.entities.Response;
import com.example.mutants.entities.ResponseStats;
import com.example.mutants.entities.Stats;
import com.example.mutants.services.MutantsInterface;

@RestController
public class MutantManagement {
	
	@Autowired
	MutantsInterface mutantsInterface;
	
	@PostMapping("/mutant")
	ResponseEntity <Response> crearUsuario(@RequestBody Mutants mutant) {
		mutantsInterface.createMutant(mutant);
		if (mutantsInterface.isMutant(mutant)) {
			mutantsInterface.updateStats(true);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}else {
			mutantsInterface.updateStats(false);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	
	@GetMapping("/stats")
	ResponseEntity<Response> consultarUsuario() {
		ResponseStats responseStats = new ResponseStats("Mutant Statistics");
		Stats responseS = mutantsInterface.getStats();
		responseStats.setIsMutant(responseS.getIsMutant());
		responseStats.setIsntMutant(responseS.getIsntMutant());
		float ratio = (float)responseS.getIsMutant() / (float)responseS.getIsntMutant();
		responseStats.setRatio(ratio);
		return ResponseEntity.status(HttpStatus.OK).body(responseStats);
	}
}

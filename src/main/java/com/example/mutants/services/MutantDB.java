package com.example.mutants.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.mutants.entities.Mutants;
import com.example.mutants.entities.Stats;

@Service
public class MutantDB  implements MutantsInterface{
	
	@Autowired
	private MongoOperations mongoOperations;
	
	private Update update = new Update();
	private Query query = new Query();
	
	public boolean isMutant(Mutants mutant) {
		String[] temporal = mutant.getDna();
		
		for (int i = 0 ; i < temporal.length ; i++) {
			
		}
		
		boolean result = true;  
		return result;
	}
	
	public boolean createMutant(Mutants mutant) {
		mongoOperations.save(mutant); 
		return true;
	}
	
	public boolean updateStats(boolean isMutant) {
		String idRegistry = "ABCDE";
		query = new Query();
		query.addCriteria(Criteria.where("key").is(idRegistry));
		Stats temp = mongoOperations.findOne(query, Stats.class);
		if (isMutant) {
			update.set("isMutant",(temp.getIsMutant()+1));
			mongoOperations.updateFirst(query, update, Stats.class);
		}else {
			update.set("isntMutant",(temp.getIsntMutant()+1));
			mongoOperations.updateFirst(query, update, Stats.class);
		}
		return true;
	}
	
	public Stats getStats() {
		String idRegistry = "ABCDE";
		query = new Query();
		query.addCriteria(Criteria.where("key").is(idRegistry));
		return mongoOperations.findOne(query, Stats.class);	
	}
	
}

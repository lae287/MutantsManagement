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
public class MutantDB implements MutantsInterface {

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	MutantsInterface mutantsInterface;

	private Update update = new Update();
	private Query query = new Query();

	public boolean isMutant(Mutants mutant) {
		mutantsInterface.createMutant(mutant);
		char a,b,c,d;
		String[] temp = mutant.getDna();

		for (int rows = 0; rows < temp.length; rows++) {
			for (int columns = 0; columns < temp.length; columns++) {

				if (columns < (temp.length - 3)) {
					a = temp[rows].charAt(columns);
					b = temp[rows].charAt(columns + 1);
					c = temp[rows].charAt(columns + 2);
					d = temp[rows].charAt(columns + 3);
					if (a == b && a == c &&  a == d) {
						return true;
					}
				} else if (rows < (temp.length - 3) && columns < (temp.length - 3)) {
					a = temp[rows].charAt(columns);
					b = temp[rows + 1].charAt(columns + 1);
					c = temp[rows + 2].charAt(columns + 2);
					d = temp[rows + 3].charAt(columns + 3);
					if (a == b && a == c &&  a == d) {
						return true;
					}
				} else if (rows < (temp.length - 3)) {
					a = temp[rows].charAt(columns);
					b = temp[rows + 1].charAt(columns);
					c = temp[rows + 2].charAt(columns);
					d = temp[rows + 3].charAt(columns);
					if (a == b && a == c &&  a == d) {
						return true;
					}
				}

			}
		}

		return false;
	}

	public boolean checkEntry(String[] dna) {

		for (String dnaChain : dna) {
			if (dnaChain.matches(".*[^ATCG].*")) {
				return false;
			}
		}

		return true;
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
			update.set("isMutant", (temp.getIsMutant() + 1));
			mongoOperations.updateFirst(query, update, Stats.class);
		} else {
			update.set("isntMutant", (temp.getIsntMutant() + 1));
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

package no.hvl.dat107.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat107.*;
import no.hvl.dat107.entity.Avdeling;

public class AvdelingDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AnsattPersistenceUnit");
	
	public Avdeling finnAvdelingMedId(int id) {

        EntityManager em = emf.createEntityManager();

        Avdeling avdeling = null;
        try {
            avdeling = em.find(Avdeling.class, id);
        } finally {
            em.close();
        }
        return avdeling;
    }


	
}

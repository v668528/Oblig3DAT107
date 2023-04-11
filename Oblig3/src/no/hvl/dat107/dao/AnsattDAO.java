package no.hvl.dat107.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;
import no.hvl.dat107.entity.ProsjektdeltagelsePK;

public class AnsattDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AnsattPersistenceUnit");
	private AvdelingDAO avde;

	public Ansatt finnAnsattMedId(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Ansatt.class, id);

		} finally {
			em.close();
		}
	}

	public Ansatt finnAnsattMedBrukernavn(String brukerNavn) {
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.ansatt_bn LIKE :brukernavn",
					Ansatt.class);
			query.setParameter("brukernavn", brukerNavn);

			return query.getSingleResult();

		} finally {
			em.close();
		}
	}

	public List<Ansatt> finnAlleAnsatter() {

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);

			return query.getResultList();

		} finally {
			em.close();
		}
	}

	public void oppdaterStilling(int id, String stilling, int maanedslonn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt oppdatering = em.find(Ansatt.class, id);
			oppdatering.setStilling(stilling);
			oppdatering.setMaanedslonn(maanedslonn);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		System.out.println("Oppdatering fullfort for ansatten med ID: " + id);
	}

	public void lagreNyAnsatt(String brukernavn, String fornavn, String etternavn, LocalDate ansattelse_dato,
			String stilling, int maanedslonn, int id) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Avdeling avd = em.find(Avdeling.class, id);

			avd = em.merge(avd);

			Ansatt ny = new Ansatt(brukernavn, fornavn, etternavn, ansattelse_dato, stilling, maanedslonn);

			ny.setAvdeling(avd);
			avd.leggTilAnsatt(ny);
			em.persist(ny);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}

		System.out.println("Gratulerer med en ny kollega! :)");
	}

	public void slettAnsattMedId(int id) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt ansatt = em.find(Ansatt.class, id);
			em.remove(ansatt);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}

	public void oppdatereAvdeling(int ansatt_id, int avdeling_id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Ansatt a = null;
		Avdeling av = null;

		try {
			tx.begin();

			a = em.find(Ansatt.class, ansatt_id);
			av = a.getAvdeling();

			if (a.getAnsattID() == av.getSjef().getAnsattID()) {
				System.out.print("Ansatten er en sjef, så dermed kan ikke byttes!");

			} else {
				a.setAvdeling(em.find(Avdeling.class, avdeling_id));
				em.merge(a);
				tx.commit();

			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Avdeling lagreNyAvdeling(String navnPaaNyAvdeling, Ansatt sjef) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Avdeling nyAvdeling = null;

		try {
			tx.begin();

			Avdeling gammelAvdeling = sjef.getAvdeling();
			gammelAvdeling.fjernTil(sjef);
			em.merge(gammelAvdeling);

			nyAvdeling = new Avdeling();
			nyAvdeling.setAvdeling_navn(navnPaaNyAvdeling);
			nyAvdeling.setSjef(sjef);
			nyAvdeling.leggTilAnsatt(sjef);

			em.persist(nyAvdeling);

			sjef.setAvdeling(nyAvdeling);
			em.merge(sjef);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return nyAvdeling;
	}

	public void lagreNyProsjekt(String navn, String beskrivelse) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Prosjekt prosjekt = null;

		try {
			tx.begin();

			prosjekt = new Prosjekt(navn, beskrivelse);

			em.persist(prosjekt);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		System.out.println("Ny prosjekt boss!");
	}

	public void registrerProsjektdeltagelse(int ansattId, int prosjektId, String rolle) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansattId);
			Prosjekt p = em.find(Prosjekt.class, prosjektId);

			Prosjektdeltagelse pd = new Prosjektdeltagelse(a, p);

			pd.setRolle(rolle);

			em.persist(pd);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}

	}

	public void registrerTimerForProsjekt(int ansattID, int prosjektID, int timer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			ProsjektdeltagelsePK pk = new ProsjektdeltagelsePK(ansattID, prosjektID);
			Prosjektdeltagelse pd = em.find(Prosjektdeltagelse.class, pk);
			
			em.merge(pd);
			
			pd.setTimer(timer);
			

			tx.commit();
		} catch (Throwable e) {
		        e.printStackTrace();
		        if (tx.isActive()) {
		            tx.rollback();
		} 

	} finally {
		em.close();
	}
}
	
	public List<Prosjektdeltagelse> finnAlleDeltaker(int prosjektID) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        String jsqlQuery = "SELECT pd FROM Prosjektdeltagelse as pd WHERE pd.prosjekt.prosjekt_id = :prosjektID";
	        TypedQuery<Prosjektdeltagelse> query = em.createQuery(jsqlQuery, Prosjektdeltagelse.class);
	        query.setParameter("prosjektID", prosjektID);

	        return query.getResultList();
	    } finally {
	        em.close();
	    }
	}

}

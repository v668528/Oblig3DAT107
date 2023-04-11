package no.hvl.dat107.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Avdeling", schema = "oblig3")
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdeling_id;
	private String avdeling_navn;

	@OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
	List<Ansatt> ansatter = new ArrayList<>();

	
	@OneToOne
	@JoinColumn(name = "avdeling_sjef")
	private Ansatt sjef;
	
	


	public Avdeling() {
	}

	public Avdeling(int id, String navn) {
		super();
		this.avdeling_id = id;
		this.avdeling_navn = navn;

	}

	public void leggTilAnsatt(Ansatt a) {
		ansatter.add(a);
	}
	
	public void fjernTil(Ansatt a) {
		ansatter.remove(a);
		a.setAvdeling(this);
	}

	
	public String getAvdeling_navn() {
		return avdeling_navn;
	}

	public void setAvdeling_navn(String avdeling_navn) {
		this.avdeling_navn = avdeling_navn;
	}

	public Ansatt getSjef() {
		return sjef;
	}

	public void setSjef(Ansatt sjef) {
		this.sjef = sjef;
	}
	
	
	public List<Ansatt> getAnsatter () {
		return ansatter;
	}
	
//	@Override
//	public String toString() {
//		String avd = "";
//		for (Ansatt a : ansatter) {
//			avd += "\n\t" + a;
//		}
//		return "Ansatter for avdeling= " + avdeling_id + ", Navn til avdeling =" + avdeling_navn + ", Sjefen="
//				+ sjef.getAnsatt_bn() + ":" + avd;
//
//	}

	   public void skrivUt() {
	        System.out.printf(" Avdeling %d(%s): %d ansatte, sjef = %s%n", 
	                avdeling_id, avdeling_navn, ansatter.size(), sjef.getAnsatt_bn());
	    }
	
//	   public void skrivUtMedAnsatte() {
//	        skrivUt();
//	        
//			ansatter.forEach(Ansatt::skrivUt);
//	    }

	   public void skrivUtMedAnsatte() {
		    System.out.printf("Avdeling %d(%s):%n", avdeling_id, avdeling_navn);
		    System.out.println("Ansatte:");
		    
		    for (Ansatt a: ansatter) {
		    	System.out.println(a);
		    }
		    System.out.printf("Sjef: %s%n", sjef.getFornavn());
		}

	
	   
}

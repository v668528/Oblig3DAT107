package no.hvl.dat107.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Ansatt", schema = "oblig3")
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansatt_id;
	private String ansatt_bn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansattelse_dato;
	private String stilling;
	private int maanedslonn;

	@ManyToOne
	@JoinColumn(name = "avdeling_id")
	private Avdeling avdeling;

	@OneToMany(mappedBy = "Ansatt")
	private List<Prosjektdeltagelse> deltagelser;
	

	
	public Ansatt() {
	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansattelse_dato, String stilling,
			int maanedslonn) {
		super();
		this.ansatt_bn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansattelse_dato = ansattelse_dato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;

	}

	public int getAnsattID() {
		return ansatt_id;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public void setMaanedslonn(int maanedslonn) {
		this.maanedslonn = maanedslonn;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	public String getAnsatt_bn() {
		return ansatt_bn;
	}

	public void setAnsatt_bn(String ansatt_bn) {
		this.ansatt_bn = ansatt_bn;
	}
	

	public Avdeling getAvdeling() {
		return avdeling;
	}
	

	public String getFornavn() {
		return fornavn;
	}
	
	
	 public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
	        deltagelser.add(prosjektdeltagelse);
	    }

	    public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
	        deltagelser.remove(prosjektdeltagelse);
	    }

	@Override
	public int hashCode() {
		return Objects.hash(ansatt_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ansatt other = (Ansatt) obj;
		return ansatt_id == other.ansatt_id;
	}

	@Override
	public String toString() {
		return "Ansatt [ansattID=" + ansatt_id + ", brukernavn=" + ansatt_bn + ", fornavn=" + fornavn + ", etternavn="
				+ etternavn + ", ansattelse_dato=" + ansattelse_dato + ", stilling=" + stilling + ", maanedslonn="
				+ maanedslonn + "]";
	}




//    public void skrivUt() {
//        System.out.printf("   Ansatt %d(%s): Jobber i %s%n", 
//                ansatt_id, ansatt_bn, avdeling.getAvdeling_navn());
//    }
//	
	
	
}

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Prosjekt", schema = "oblig3")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjekt_id;
	private String prosjekt_navn;
	private String prosjekt_beskrivelse;

	@OneToMany(mappedBy = "prosjekt")
	private List<Prosjektdeltagelse> deltagelser;

	public Prosjekt() {
	}

	public Prosjekt(String prosjekt_navn, String prosjekt_beskrivelse) {
		super();
		this.prosjekt_navn = prosjekt_navn;
		this.prosjekt_beskrivelse = prosjekt_beskrivelse;
	}

	public int getProsjekt_id() {
		return prosjekt_id;
	}



	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
		deltagelser.add(prosjektdeltagelse);
	}

	public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
		deltagelser.remove(prosjektdeltagelse);
	}

	@Override
	public String toString() {
		return "Prosjekt [prosjekt_id=" + prosjekt_id + ", prosjekt_navn=" + prosjekt_navn + ", prosjekt_beskrivelse="
				+ prosjekt_beskrivelse + ", deltagelser=" + deltagelser + "]";
	}

	

}

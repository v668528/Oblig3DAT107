package no.hvl.dat107.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Prosjektdeltagelse",schema="oblig3")
@IdClass(ProsjektdeltagelsePK.class)
public class Prosjektdeltagelse {
	
	@Id
    @ManyToOne
    @JoinColumn(name="ansattid")
    private Ansatt ansatt;
    
    @Id
    @ManyToOne
    @JoinColumn(name="prosjektid")
    private Prosjekt prosjekt;

    private int timer;
	private String rolle;
	
	public Prosjektdeltagelse() {}
	
	public Prosjektdeltagelse(Ansatt ansatt,Prosjekt prosjekt) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
		ansatt.leggTilProsjektdeltagelse(this);
		prosjekt.leggTilProsjektdeltagelse(this);
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	@Override
	public String toString() {
		return "Prosjektdeltagelse [ansatt=" + ansatt + ", prosjekt=" + prosjekt + ", timer=" + timer + ", rolle="
				+ rolle + "]";
	}
    
	
    
}

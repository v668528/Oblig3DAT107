package no.hvl.dat107.entity;


@SuppressWarnings("unused")
public class ProsjektdeltagelsePK {

	private int ansatt;
	private int prosjekt;
	
	
	public ProsjektdeltagelsePK() {}


	public ProsjektdeltagelsePK(int ansattID, int prosjektID) {
		
		this.ansatt = ansattID;
		this.prosjekt = prosjektID;
	}
	
	
}


package no.hvl.dat107;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;

public class Meny {

	Ansatt a = new Ansatt();
	AnsattDAO ansatt = new AnsattDAO();
	Avdeling avd = new Avdeling();
	AvdelingDAO av = new AvdelingDAO();

	public Meny() {
	}

	public void startMeny() {

		System.out.println("1: " + "Soker etter en ansatt med hensyn til ID");
		System.out.println("2: " + "Soker etter en ansatt med hensyn til brukernavn");
		System.out.println("3: " + "Liste av alle ansatter");
		System.out.println("4: " + "Oppdater stilling og lønn til en ansatt");
		System.out.println("5: " + "Legge inn en ny ansatt");
		System.out.println("6: " + "Soker etter en avdeling med hensyn til ID");
		System.out.println("7: " + "Exit");

		boolean ferdig = true;

		while (ferdig) {
			int valg = Integer.parseInt(JOptionPane.showInputDialog("Velg mellom 1-7: "));

			switch (valg) {

			case 1:

				int finn = Integer.parseInt(JOptionPane.showInputDialog("ID av ansatt: "));
				System.out.println(ansatt.finnAnsattMedId(finn));
				break;
			case 2:

				String brukernavn = JOptionPane.showInputDialog("Brukernavn: ");
				System.out.println(ansatt.finnAnsattMedBrukernavn(brukernavn));
				break;

			case 3:
				System.out.println(ansatt.finnAlleAnsatter());
				break;

			case 4:
				int id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
				String stilling = JOptionPane.showInputDialog("Stilling: ");
				int maanedslonn = Integer.parseInt(JOptionPane.showInputDialog("Månedslønn: "));

				ansatt.oppdaterStilling(id, stilling, maanedslonn);
				break;

			case 5:

				String nyBrukernavn = JOptionPane.showInputDialog("Brukernavn: ");
				String nyFornavn = JOptionPane.showInputDialog("Fornavn: ");
				String nyEtternavn = JOptionPane.showInputDialog("Etternavn: ");
				LocalDate nyAnsattelse_dato = LocalDate.parse(JOptionPane.showInputDialog("XXXX-XX-XX"));
				String nyStilling = JOptionPane.showInputDialog("Stilling: ");
				int nyMaanedslonn = Integer.parseInt(JOptionPane.showInputDialog("Månedslønn: "));
				int nyAvdeling = Integer.parseInt(JOptionPane.showInputDialog("Avdeling: "));

				ansatt.lagreNyAnsatt(nyBrukernavn, nyFornavn, nyEtternavn, nyAnsattelse_dato, nyStilling, nyMaanedslonn,
						nyAvdeling);
				break;

			case 6:
				int finnAv = Integer.parseInt(JOptionPane.showInputDialog("ID av avdeling: "));
				System.out.println(av.finnAvdelingMedId(finnAv));
				break;

			case 7:
				ferdig = false;

			}
		}
	}
}

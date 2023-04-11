package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;

public class Mainprove {

	public static void main(String[] args) {
//		Meny newMenu = new Meny();
//		
//		
//		newMenu.startMeny();
//		
		
	AnsattDAO a = new AnsattDAO();

	System.out.println(a.finnAlleDeltaker(1));
	
	}
	
	
	
	}


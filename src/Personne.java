

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Personne {
	private String cin;
	private String nom;
	private String prenom;
	private List<Compte> comptes = new ArrayList<>();

	public Personne(String cin, String nom, String prénom) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prénom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrénom() {
		return prenom;
	}

	public void setPrénom(String prénom) {
		this.prenom = prénom;
	}

	public double totalComptes() throws SQLException {

		ResultSet resultSet = ConnectionJDBC.getResults("select sum(solde) from compte where personne_id=" + getCin());
		double sum = 0;
		while (resultSet.next()) {
			sum += resultSet.getDouble(0);
		}

		return sum;
	}

	public void transferTo(int compteId, double montant) {
		int i = ConnectionJDBC.update("update compte set solde=solde+" + montant + " where id=" + compteId);
		if (i > 0) {
			System.out.println("transfert effectué");
		} else {
			System.out.println("transfert non effectué");
		}
	}

	@Override
	public String toString() {
		return "Compte [cin=" + cin + ", nom=" + nom + ", prénom=" + prenom + "]";
	}

}

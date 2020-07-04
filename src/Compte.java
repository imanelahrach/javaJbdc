

import java.sql.ResultSet;
import java.sql.SQLException;

public class Compte {
	public int id;
	private double solde;
	private int PersonneCin;

	public Compte(int PersonneCin, double solde) throws SQLException {
		this.id=this.getAutoCompteId();
		this.PersonneCin = PersonneCin;
		this.solde = solde;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSolde() throws SQLException {
		ResultSet resultSet = ConnectionJDBC.getResults("select sum(solde) from compte where id="+getId());
		double sum=0;
		while (resultSet.next()) {
			 sum =  resultSet.getDouble(0);
		}
		return sum;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public void credit(double montant) {
		int i = ConnectionJDBC.update("update compte set solde=solde-" + montant + " where id=" + getId());
		if (i > 0) {
			System.out.println("credit effectué");
		} else {
			System.out.println("credit non effectué");
		}
	}

	public void debit(double montant) {
		int i = ConnectionJDBC.update("update compte set solde=solde+" + montant + " where id=" + getId());
		if (i > 0) {
			System.out.println("debit effectué");
		} else {
			System.out.println("debit non effectué");
		}
	}

	public int getAutoCompteId() throws SQLException {
		int id = 0;
		ResultSet resultSet = ConnectionJDBC.getResults("select id from compte order by id desc");

		if (resultSet.next()) {
			id = resultSet.getInt(1);
		}
		return ++id;
	}

}

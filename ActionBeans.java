package exercice;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

public class ActionBeans implements Serializable {

protected String nom;
protected double cours;

PropertyChangeSupport changeSupport ;
VetoableChangeSupport veotableSupport;

public ActionBeans() {
	cours =0 ;
	nom="MonAction";
	changeSupport = new PropertyChangeSupport(this);
	veotableSupport = new VetoableChangeSupport(this);

}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public double getCours() {
	return cours;
}

public void setCours(double cours) {
	double oldCours= this.cours;
	this.cours=cours ;
	try {
		veotableSupport.fireVetoableChange("valeur",oldCours,this.cours);

	}
	catch(PropertyVetoException e) {
		System.out.println("Mon Bean , un veto est emis :"+e.getMessage());
		this.cours=oldCours;

	}
	changeSupport.firePropertyChange("valeur",oldCours,this.cours);
}

public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
	changeSupport.addPropertyChangeListener(listener);
}

public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
	changeSupport.removePropertyChangeListener(listener);
}

public synchronized void addVetoableChangeListener(VetoableChangeListener listener) {
	veotableSupport.addVetoableChangeListener(listener);
}





}

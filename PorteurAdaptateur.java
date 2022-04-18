package exercice;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class PorteurAdaptateur {
	private double seuilMin ;
	private double seuilMax ;
	
	

	public PorteurAdaptateur() {
		// TODO Auto-generated constructor stub
	}
	public PorteurAdaptateur(double seuilMin,double seuilMax) {
		this.seuilMin= seuilMin ;
		this.seuilMax = seuilMax ;
		// TODO Auto-generated constructor stub
	}
	public void venteAuto(ActionBeans a, PorteurBeans b)
	{
		a.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out.println("Listener 1 : propertyChange: valeur = " + event.getNewValue());
				if (((Double) event.getNewValue()).doubleValue() > seuilMax) {
					{
						b.vendre(a);
					}
				}
				
			}
			});
			a.addVetoableChangeListener(new VetoableChangeListener() {
				public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
					System.out.println("Vetoable Change: valeur = " + event.getNewValue());
					if (((Double) event.getNewValue()).doubleValue() < seuilMax &&  ((Double) event.getNewValue()).doubleValue() > seuilMin)
					{
						throw new PropertyVetoException("la valeur doit etre supérieur à"+seuilMax +" ou inférieur à "+seuilMin,event) ;
					}
				}
				
			});
			a.setCours(40);
	}
	public void achatAuto(ActionBeans a, PorteurBeans b)
	{
		a.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out.println("Listener 1 : propertyChange: valeur = " + event.getNewValue());
				if (((Double) event.getNewValue()).doubleValue() < seuilMin) {
					{
						b.acheter(a);;
					}
				}
				
			}
			});
			a.addVetoableChangeListener(new VetoableChangeListener() {
				public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
					System.out.println("Vetoable Change: valeur = " + event.getNewValue());
					if (((Double) event.getNewValue()).doubleValue() < seuilMax &&  ((Double) event.getNewValue()).doubleValue() > seuilMin)
					{
						throw new PropertyVetoException("la valeur doit etre supérieur à"+seuilMax +" ou inférieur à "+seuilMin,event) ;
					}
				}
				
			});
			a.setCours(20);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ActionBeans a = new ActionBeans() ;
		PorteurBeans b = new PorteurBeans() ;
		PorteurAdaptateur p =new PorteurAdaptateur(22,42);
		p.venteAuto(a, b);
		p.achatAuto(a, b);
		
		
	}

}

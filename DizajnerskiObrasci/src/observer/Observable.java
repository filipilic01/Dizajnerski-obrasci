package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.JToggleButton;

import geometry.Shape;

public class Observable {
	private JToggleButton delete;
	private JToggleButton modify;


	private PropertyChangeSupport propertyChangeSupport;
	
	public Observable() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	

	public void setDelete(boolean enabled) {
		propertyChangeSupport.firePropertyChange("delete", this.delete.isEnabled(), enabled);
		this.delete.setEnabled(enabled);
		
	}
	
	public void setModify(boolean enabled) {
		propertyChangeSupport.firePropertyChange("modify", this.modify.isEnabled(), enabled);
		this.modify.setEnabled(enabled);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}



}

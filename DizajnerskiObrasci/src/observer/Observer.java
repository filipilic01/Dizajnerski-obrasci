package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JToggleButton;

public class Observer implements PropertyChangeListener{
	private JToggleButton delete;
	private JToggleButton modify;
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("modify")) {
			modify.setEnabled((boolean)evt.getNewValue());
		}
		if(evt.getPropertyName().equals("delete")) {
			delete.setEnabled((boolean)evt.getNewValue());
		}
		
		
	}

}

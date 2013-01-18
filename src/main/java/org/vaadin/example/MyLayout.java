package org.vaadin.example;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MyLayout extends VerticalLayout {
	
	private TextField nameField = new TextField("Name");
	private CheckBox settingField = new CheckBox("Setting");
	
	private VerticalLayout customStuff = new VerticalLayout();
	
	public MyLayout() {
		setSpacing(true);
		setMargin(true);
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setCaption("Generic pojo stuff, fields by formbinder");
		horizontalLayout.addComponent(nameField);
		horizontalLayout.addComponent(settingField);
		
		addComponent(horizontalLayout);
		
		getCustomStuff().setCaption("This part has custom stuff, fields generated on the fly.");
		getCustomStuff().setSpacing(true);
		addComponent(getCustomStuff());
		
	}

	/**
	 * @return the layout into where custom stuff should be added
	 */
	public VerticalLayout getCustomStuff() {
		return customStuff;
	}

}

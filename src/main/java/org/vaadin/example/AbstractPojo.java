package org.vaadin.example;

public class AbstractPojo {
	
	private String name;
	
	private boolean setting = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSetting() {
		return setting;
	}

	public void setSetting(boolean setting) {
		this.setting = setting;
	}

}

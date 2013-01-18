/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.example;

import org.vaadin.addon.formbinder.PreCreatedFieldsHelper;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application {
	private Window window;

	private final MyLayout layout = new MyLayout();

	private Form form = new Form() {

		public void setItemDataSource(Item newDataSource) {
			// clear old stuff from "custom stuff slot"
			layout.getCustomStuff().removeAllComponents();
			super.setItemDataSource(newDataSource);
		};

		@Override
		protected void attachField(Object propertyId, Field field) {
			// NOP as fields are expected to be attached in view or to be
			// attached by the custom fieldfactory
		}

		@Override
		protected void detachField(Field field) {
			// NOP as fields are expected to be attached in view or to be
			// attached by the custom fieldfactory
		}
	};

	PreCreatedFieldsHelper preCreatedFieldsHelper = new PreCreatedFieldsHelper(
			layout) {

		@Override
		public Field createField(Item item, Object propertyId,
				Component uiContext) {
			Field field = super.createField(item, propertyId, uiContext);
			// field now contains relevant field from the layout if one existed
			if (field == null) {
				// No field found from layout, create on in "traditional
				// method and add to the slot reserved for custom fields in
				// the layout. Here defaultfieldfactory is used, but probably
				// some more intelligent in real world
				field = DefaultFieldFactory.get().createField(item, propertyId,
						uiContext);
				layout.getCustomStuff().addComponent(field);

			}
			return field;
		}

	};

	@Override
	public void init() {
		window = new Window("My Vaadin Application");
		setMainWindow(window);

		HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
		horizontalSplitPanel.setSplitPosition(30);
		window.setContent(horizontalSplitPanel);

		VerticalLayout verticalLayout = new VerticalLayout();

		final PojoOne pojoOne = new PojoOne();
		pojoOne.setName("My pojo one");
		Button button = new Button("Edit pojo one");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				editPojo(pojoOne);
			}
		});
		verticalLayout.addComponent(button);

		final PojoTwo pojoTwo = new PojoTwo();
		button = new Button("Edit pojo two");
		pojoTwo.setName("My second pojo");

		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				editPojo(pojoTwo);
			}
		});
		verticalLayout.addComponent(button);

		horizontalSplitPanel.setFirstComponent(verticalLayout);

		form.setLayout(layout);
		form.setFormFieldFactory(preCreatedFieldsHelper);
		form.setVisible(false);
		horizontalSplitPanel.setSecondComponent(form);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void editPojo(Object pojo) {
		form.setItemDataSource(new BeanItem(pojo));
		form.setVisible(true);
	}
}

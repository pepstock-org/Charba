/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.annotation;

import java.util.List;

import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.enums.Event;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptions;

/**
 * This is the {@link AnnotationPlugin#ID} plugin options where to set all configuration items needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationOptions extends AbstractPluginCachedOptions implements IsDefaultOptions {

	/**
	 * Default double click speed in milliseconds, <b>{@value DEFAULT_DOUBLE_CLICK_SPEED}</b>.
	 */
	public static final int DEFAULT_DOUBLE_CLICK_SPEED = 350;

	/**
	 * Default draw time, <b>{@link DrawTime#AFTER_DATASETS_DRAW}</b>.
	 */
	public static final DrawTime DEFAULT_DRAW_TIME = DrawTime.AFTER_DATASETS_DRAW;

	// maintains the list of annotations because needs to preserve the annotation type
	private final ArrayObjectContainerList<AbstractAnnotation> currentAnnotations = new ArrayObjectContainerList<>();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		DRAW_TIME("drawTime"),
		EVENTS("events"),
		DOUBLE_CLICK_SPEED("dblClickSpeed"),
		ANNOTATIONS("annotations");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates new {@link AnnotationPlugin#ID} plugin options.
	 */
	public AnnotationOptions() {
		// creates an empty native object
		super(AnnotationPlugin.ID, AnnotationPlugin.FACTORY, false);
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public void setDrawTime(DrawTime drawTime) {
		setValue(Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	public DrawTime getDrawTime() {
		return getValue(Property.DRAW_TIME, DrawTime.class, IsDefaultOptions.super.getDrawTime());
	}

	/**
	 * Sets the double-click speed in milliseconds used to distinguish single-clicks from double-clicks whenever you need to
	 * capture both.<br>
	 * When listening for both {@link Event#CLICK} and {@link Event#DOUBLE_CLICK}, click events will be delayed by this amount.
	 * 
	 * @param speed the double-click speed in milliseconds
	 */
	public void setDoubleClickSpeed(int speed) {
		setValue(Property.DOUBLE_CLICK_SPEED, speed);
	}

	/**
	 * Returns the double-click speed in milliseconds used to distinguish single-clicks from double-clicks whenever you need to
	 * capture both.<br>
	 * When listening for both {@link Event#CLICK} and {@link Event#DOUBLE_CLICK}, click events will be delayed by this amount.
	 * 
	 * @return the double-click speed in milliseconds
	 */
	public int getDoubleClickSpeed() {
		return getValue(Property.DOUBLE_CLICK_SPEED, IsDefaultOptions.super.getDoubleClickSpeed());
	}

	/**
	 * Sets the browser events to enable on each annotation.
	 * 
	 * @param events the browser events to enable on each annotation.
	 */
	public void setEvents(Event... events) {
		// sets the array of events
		setArrayValue(Property.EVENTS, ArrayString.fromOrNull(events));
	}

	/**
	 * Returns the browser events to enable on each annotation.
	 * 
	 * @return the browser events to enable on each annotation
	 */
	public List<Event> getEvents() {
		// gets array for events
		ArrayString array = getArrayValue(Property.EVENTS);
		// if the arrays is consistent...
		if (array != null && !array.isEmpty()) {
			// ...then returns as list of events
			return ArrayListHelper.list(Event.class, array);
		}
		// ... otherwise returns the default
		return IsDefaultOptions.super.getEvents();
	}

	/**
	 * Sets a set of annotations for plugin. If argument is <code>null</code>, removes all annotations.
	 * 
	 * @param annotations set of anotation. If <code>null</code>, removes all annotations
	 */
	public void setAnnotations(AbstractAnnotation... annotations) {
		// clear buffer
		this.currentAnnotations.clear();
		// checks if arguments is consistent
		if (annotations != null) {
			// adds all annotations
			this.currentAnnotations.addAll(annotations);
			// sets annotations to native object
			setArrayValue(Property.ANNOTATIONS, this.currentAnnotations);
		} else {
			// removes the existing property
			removeIfExists(Property.ANNOTATIONS);
		}
	}

	/**
	 * Returns the list of annotations.
	 * 
	 * @return the list of annotations
	 */
	public List<AbstractAnnotation> getAnnotations() {
		return getAnnotations(false);
	}

	/**
	 * Returns the list of annotations.
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return the list of annotations
	 */
	public List<AbstractAnnotation> getAnnotations(boolean binding) {
		// there is not the property and the binding is requested
		// then adds array to container
		if (!has(Property.ANNOTATIONS) && binding) {
			// sets annotations to native object
			setArrayValue(Property.ANNOTATIONS, this.currentAnnotations);
		}
		// returns annotations
		return this.currentAnnotations;
	}

}

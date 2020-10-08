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

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.annotation.callbacks.ClickCallback;
import org.pepstock.charba.client.annotation.callbacks.EnterCallback;
import org.pepstock.charba.client.annotation.callbacks.LeaveCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Base class to define an annotation into {@link Annotation#ID} plugin.<br>
 * It contains all commons properties to define an annotation ({@link BoxAnnotation} or {@link LineAnnotation}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractAnnotation extends NativeObjectContainer implements IsDefaultsAnnotation {

	/**
	 * Default annotation drawing enabled, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = true;

	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	// exception pattern when the scale or scales methods is invoked and the scale type is not correct
	static final String INVALID_DEFAULTS_VALUES_CLASS = "Defaults options are not invalid because not a {0} annotaion defaults";

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ENABLED("enabled"),
		TYPE("type"),
		ID("id"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		// internal property to set an unique id for cahing
		CHARBA_ANNOTATION_ID("_charbaAnnotationId");

		// name value of property
		private final String value;
		//

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

	// callback instance to handle mouse over event for entering on annotation
	private EnterCallback enterCallback = null;
	// callback instance to handle mouse leave and mouse over event for leaving on annotation
	private LeaveCallback leaveCallback = null;
	// callback instance to handle click event for clicking on annotation
	private ClickCallback clickCallback = null;
	// defaults options
	private final IsDefaultsAnnotation defaultValues;
	// draw time instance set at plugin startup
	private DrawTime defaultDrawTime = null;

	/**
	 * Creates the object with the type of annotation to handle.
	 * 
	 * @param type annotation type
	 * @param id annotation id
	 * @param defaultValues default options instance
	 */
	AbstractAnnotation(AnnotationType type, IsAnnotationId id, IsDefaultsAnnotation defaultValues) {
		this(null, defaultValues);
		// checks if is is consistent
		IsAnnotationId.checkIfValid(id);
		// checks if type is consistent
		Key.checkIfValid(type);
		// stores id
		setValue(Property.ID, id);
		// stores type
		setValue(Property.TYPE, type);
		// stores the internal id for caching
		setValue(Property.CHARBA_ANNOTATION_ID, COUNTER.getAndIncrement());
		// cached it
		Annotation.get().addAnnotation(this);
	}

	/**
	 * Creates the object wrapping an existing native object. <b>PAY ATTENTION</b>: this constructor is invoked from plugin before starting drawing and NOT for configuration.
	 * 
	 * @param nativeObject native object to wrap
	 * @param defaultValues default options instance
	 */
	AbstractAnnotation(NativeObject nativeObject, IsDefaultsAnnotation defaultValues) {
		super(nativeObject);
		// checks if default value is consistent
		if (defaultValues == null) {
			// if not, exception
			throw new IllegalArgumentException("Default values argument is null");
		}
		// stores default options
		this.defaultValues = defaultValues;
	}

	/**
	 * Returns the defaults values for this object.
	 * 
	 * @return the defaults values for this object
	 */
	final IsDefaultsAnnotation getDefaultsValues() {
		return defaultValues;
	}

	/**
	 * Returns the id of annotation for caching (internal).
	 * 
	 * @return the id of annotation for caching (internal)
	 */
	final int getAnnotationId() {
		return getValue(Property.CHARBA_ANNOTATION_ID, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the id of annotation.
	 * 
	 * @return the id of annotation
	 */
	public final IsAnnotationId getId() {
		return IsAnnotationId.create(getValue(Property.ID, UndefinedValues.STRING));
	}

	/**
	 * Returns the type of annotation.
	 * 
	 * @return the type of annotation
	 */
	@Override
	public final AnnotationType getType() {
		return getValue(Property.TYPE, AnnotationType.values(), defaultValues.getType());
	}

	/**
	 * Sets <code>true</code> whether the label is enabled and should be displayed.
	 * 
	 * @param enabled <code>true</code> whether the label is enabled and should be displayed
	 */
	public final void setEnabled(boolean enabled) {
		setValue(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> whether the annotation is enabled and should be displayed.
	 * 
	 * @return <code>true</code> whether the annotation is enabled and should be displayed
	 */
	@Override
	public final boolean isEnabled() {
		return getValue(Property.ENABLED, defaultValues.isEnabled());
	}

	/**
	 * Sets the draw time defined as default into options as top level.
	 * 
	 * @param drawTime the draw time defined as default into options as top level
	 */
	final void setDefaultDrawTime(DrawTime drawTime) {
		this.defaultDrawTime = drawTime;
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public final void setDrawTime(DrawTime drawTime) {
		setValue(AnnotationOptions.Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	@Override
	public final DrawTime getDrawTime() {
		return getValue(AnnotationOptions.Property.DRAW_TIME, DrawTime.values(), defaultDrawTime != null ? defaultDrawTime : defaultValues.getDrawTime());
	}

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	public final void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	public final void setBorderColor(String borderColor) {
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	public final IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the width of the border in pixels.
	 * 
	 * @param borderWidth the width of the border in pixels.
	 */
	public final void setBorderWidth(int borderWidth) {
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the callback called when a mouse event is occurring, entering on annotation element.
	 * 
	 * @return the callback called when a mouse event is occurring, entering on annotation element
	 */
	@Override
	public final EnterCallback getEnterCallback() {
		// checks if is consistent
		// what has been set against this instance
		if (enterCallback == null) {
			// if not set here, checks on the defaults
			return defaultValues.getEnterCallback();
		}
		return enterCallback;
	}

	/**
	 * Sets the callback called when a mouse event is occurring, entering on annotation element.
	 * 
	 * @param leaveCallback the callback called when a mouse event is occurring, entering on annotation element
	 */
	public final void setEnterCallback(EnterCallback leaveCallback) {
		this.enterCallback = leaveCallback;
	}

	/**
	 * Returns the callback called when a mouse event is occurring, leaving the annotation element.
	 * 
	 * @return the callback called when a mouse event is occurring, leaving the annotation element
	 */
	@Override
	public final LeaveCallback getLeaveCallback() {
		// checks if is consistent
		// what has been set against this instance
		if (leaveCallback == null) {
			// if not set here, checks on the defaults
			return defaultValues.getLeaveCallback();
		}
		return leaveCallback;
	}

	/**
	 * Sets the callback called when a mouse event is occurring, leaving the annotation element.
	 * 
	 * @param leaveCallback the callback called when a mouse event is occurring, leaving the annotation element
	 */
	public final void setLeaveCallback(LeaveCallback leaveCallback) {
		this.leaveCallback = leaveCallback;
	}

	/**
	 * Returns the callback called when a mouse event is occurring, clicking on the annotation element.
	 * 
	 * @return the callback called when a mouse event is occurring, clicking on the annotation element
	 */
	@Override
	public final ClickCallback getClickCallback() {
		// checks if is consistent
		// what has been set against this instance
		if (clickCallback == null) {
			// if not set here, checks on the defaults
			return defaultValues.getClickCallback();
		}
		return clickCallback;
	}

	/**
	 * Sets the callback called when a mouse event is occurring, clicking on the annotation element.
	 * 
	 * @param clickCallback the callback called when a mouse event is occurring, clicking on the annotation element
	 */
	public final void setClickCallback(ClickCallback clickCallback) {
		// sets click callback
		this.clickCallback = clickCallback;
	}

}

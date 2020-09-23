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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.callbacks.ClickCallback;
import org.pepstock.charba.client.annotation.callbacks.EnterCallback;
import org.pepstock.charba.client.annotation.callbacks.LeaveCallback;
import org.pepstock.charba.client.annotation.enums.AnnotationType;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Base class to define an annotation into {@link Annotation#ID} plugin.<br>
 * It contains all commons properties to define an annotation ({@link BoxAnnotation} or {@link LineAnnotation}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractAnnotation extends AbstractPluginOptions implements IsDefaultsAnnotation {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		TYPE("type"),
		ID("id"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		// internal property to set name instead of id
		// because the plugin does not reload the options if they have got an id
		// therefore another property must be set to identify better the annotation and
		// the id must be reset every reconfiguration
		CHARBA_NAME("_charbaName");

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

	// type of annotation
	private final AnnotationType type;
	// callback instance to handle mouseover event for entering on annotation
	private EnterCallback enterCallback = null;
	// callback instance to handle mouseleave and mouseover event for leaving on annotation
	private LeaveCallback leaveCallback = null;
	// callback instance to handle click event for clicking on annotation
	private ClickCallback clickCallback = null;
	// defaults options
	private final DefaultsOptions defaultsOptions;
	// draw time instance set at plugin startup
	private DrawTime defaultDrawTime = null;

	/**
	 * Creates the object with the type of annotation to handle.
	 * 
	 * @param type annotation type
	 * @param defaultsOptions default options stored into defaults global
	 */
	AbstractAnnotation(AnnotationType type, DefaultsOptions defaultsOptions) {
		super(Annotation.ID);
		// checks if type is consistent
		Key.checkIfValid(type);
		// stores type
		this.type = type;
		setValue(Property.TYPE, type);
		// checks if defaults options are consistent
		if (defaultsOptions == null) {
			// reads the default default global options
			this.defaultsOptions = loadGlobalsPluginOptions(Annotation.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultsOptions = defaultsOptions;
		}
	}

	/**
	 * Removes the ID property form annotation in order that the options can be reloaded by plugin when the chart will be reconfigured, by {@link IsChart#reconfigure()}.
	 */
	void resetAnnotationId() {
		removeIfExists(Property.ID);
	}

	/**
	 * Returns the type of annotation.
	 * 
	 * @return the type of annotation
	 */
	public final AnnotationType getType() {
		return type;
	}

	/**
	 * Sets the name of annotation.
	 * 
	 * @param name the name of annotation
	 */
	public final void setName(String name) {
		setValue(Property.CHARBA_NAME, name);
	}

	/**
	 * Returns the name of annotation.
	 * 
	 * @return the name of annotation
	 */
	public final String getName() {
		return getValue(Property.CHARBA_NAME, UndefinedValues.STRING);
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
	public final DrawTime getDrawTime() {
		return getValue(AnnotationOptions.Property.DRAW_TIME, DrawTime.values(), defaultDrawTime != null ? defaultDrawTime : defaultsOptions.getDrawTime());
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
	public final EnterCallback getEnterCallback() {
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
	public final LeaveCallback getLeaveCallback() {
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
	public final ClickCallback getClickCallback() {
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

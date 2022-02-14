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

import java.util.Date;

import org.pepstock.charba.client.annotation.callbacks.AdjustScaleRangeCallback;
import org.pepstock.charba.client.annotation.callbacks.DrawTimeCallback;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.listeners.ClickCallback;
import org.pepstock.charba.client.annotation.listeners.DoubleClickCallback;
import org.pepstock.charba.client.annotation.listeners.EnterCallback;
import org.pepstock.charba.client.annotation.listeners.LeaveCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ScaleId;

/**
 * This is the {@link AnnotationPlugin#ID} plugin annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsAnnotation extends IsDefaultsBorderOptionsHandler, IsDefaultsShadowOptionsHandler {

	/**
	 * Returns the type of annotation.
	 * 
	 * @return the type of annotation
	 */
	AnnotationType getType();

	/**
	 * Returns <code>true</code> whether the annotation should be displayed.
	 * 
	 * @return <code>true</code> whether the annotation should be displayed
	 */
	default boolean isDisplay() {
		return AbstractAnnotation.DEFAULT_DISPLAY;
	}

	/**
	 * Returns <code>true</code> whether the scale range should be adjusted if this annotation is out of range.
	 * 
	 * @return <code>true</code> whether the scale range should be adjusted if this annotation is out of range
	 */
	default boolean isAdjustScaleRange() {
		return AbstractAnnotation.DEFAULT_ADJUST_SCALE_RANGE;
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	default DrawTime getDrawTime() {
		return AnnotationOptions.DEFAULT_DRAW_TIME;
	}

	/**
	 * Returns the callback called when a "enter" event is occurring.
	 * 
	 * @return the callback called when a "enter" event is occurring
	 */
	default EnterCallback getEnterCallback() {
		return null;
	}

	/**
	 * Returns the callback called when a "leave" event is occurring.
	 * 
	 * @return the callback called when a "leave" event is occurring
	 */
	default LeaveCallback getLeaveCallback() {
		return null;
	}

	/**
	 * Returns the callback called when a "click" event is occurring.
	 * 
	 * @return the callback called when a "click" event is occurring
	 */
	default ClickCallback getClickCallback() {
		return null;
	}

	/**
	 * Returns the callback called when a "dblclick" event is occurring.
	 * 
	 * @return the callback called when a "dblclick" event is occurring
	 */
	default DoubleClickCallback getDoubleClickCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the draw time which defines when the annotations are drawn.
	 * 
	 * @return the callback called to set the draw time which defines when the annotations are drawn
	 */
	default DrawTimeCallback getDrawTimeCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the display options.
	 * 
	 * @return the callback called to set the display options
	 */
	default DisplayCallback<AnnotationContext> getDisplayCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set whether the scale range should be adjusted if this annotation is out of range.
	 * 
	 * @return the callback called to set whether the scale range should be adjusted if this annotation is out of range
	 */
	default AdjustScaleRangeCallback getAdjustScaleRangeCallback() {
		return null;
	}

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	default ScaleId getXScaleID() {
		return DefaultScaleId.X;
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	default String getXMaxAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	default double getXMaxAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the right edge of the box.
	 * 
	 * @return the right edge of the box
	 */
	default Date getXMaxAsDate() {
		return null;
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	default String getXMinAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	default double getXMinAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the left edge of the box, in units along the x axis.
	 * 
	 * @return the left edge of the box
	 */
	default Date getXMinAsDate() {
		return null;
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	default ScaleId getYScaleID() {
		return DefaultScaleId.Y;
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	default String getYMaxAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	default double getYMaxAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the top edge of the box in units along the y axis.
	 * 
	 * @return the top edge of the box in units along the y axis
	 */
	default Date getYMaxAsDate() {
		return null;
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	default String getYMinAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	default double getYMinAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the bottom edge of the box.
	 * 
	 * @return the bottom edge of the box
	 */
	default Date getYMinAsDate() {
		return null;
	}

	/**
	 * Returns the callback called to set the left edge of the box, in units along the x axis.
	 * 
	 * @return the callback called to set the left edge of the box, in units along the x axis
	 */
	default ValueCallback getXMinCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the right edge of the box.
	 * 
	 * @return the callback called to set the right edge of the box
	 */
	default ValueCallback getXMaxCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the left edge of the box, in units along the x axis.
	 * 
	 * @return the callback called to set the left edge of the box, in units along the x axis
	 */
	default ValueCallback getYMinCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the right edge of the box.
	 * 
	 * @return the callback called to set the right edge of the box
	 */
	default ValueCallback getYMaxCallback() {
		return null;
	}
}

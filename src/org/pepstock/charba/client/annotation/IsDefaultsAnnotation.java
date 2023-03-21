/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.annotation;

import java.util.Date;

import org.pepstock.charba.client.annotation.callbacks.AdjustScaleRangeCallback;
import org.pepstock.charba.client.annotation.callbacks.DrawTimeCallback;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.annotation.callbacks.ZCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.listeners.ElementHookCallback;
import org.pepstock.charba.client.callbacks.SimpleDisplayCallback;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ScaleId;

/**
 * This is the {@link AnnotationPlugin#ID} plugin annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsAnnotation extends IsDefaultsBorderOptionsHandler, IsDefaultsShadowOptionsHandler, IsDefaultsEventsHandler {

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
	 * Returns the property determines the drawing stack level of the box annotation element.<br>
	 * All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 * 
	 * @return the property determines the drawing stack level of the box annotation element.<br>
	 *         All visible elements will be drawn in ascending order of `z` option, with the same "drawTime" option.
	 */
	default int getZ() {
		return AbstractAnnotation.DEFAULT_Z;
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
	default SimpleDisplayCallback<AnnotationContext> getDisplayCallback() {
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
	 * Returns the callback called to set the property determines the drawing stack level of the box annotation element.
	 * 
	 * @return the callback called to set the property determines the drawing stack level of the box annotation element
	 */
	default ZCallback getZCallback() {
		return null;
	}

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	default ScaleId getXScaleID() {
		return null;
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
		return null;
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

	/**
	 * Returns the callback called to get the control before the annotation element drawing.
	 * 
	 * @return the callback called to get the control before the annotation element drawing
	 */
	default ElementHookCallback getBeforeDrawCallback() {
		return null;
	}

	/**
	 * Returns the callback called to get the control after the annotation element drawing.
	 * 
	 * @return the callback called to get the control after the annotation element drawing
	 */
	default ElementHookCallback getAfterDrawCallback() {
		return null;
	}

}
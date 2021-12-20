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

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.annotation.callbacks.AdjustScaleRangeCallback;
import org.pepstock.charba.client.annotation.callbacks.DrawTimeCallback;
import org.pepstock.charba.client.annotation.callbacks.ValueCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.listeners.ClickCallback;
import org.pepstock.charba.client.annotation.listeners.DoubleClickCallback;
import org.pepstock.charba.client.annotation.listeners.EnterCallback;
import org.pepstock.charba.client.annotation.listeners.LeaveCallback;
import org.pepstock.charba.client.callbacks.BorderDashCallback;
import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ScaleId;

/**
 * This is the {@link AnnotationPlugin#ID} plugin annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsAnnotation {

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
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	default String getBorderColorAsString() {
		return Defaults.get().getGlobal().getBorderColorAsString();
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	int getBorderWidth();

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern
	 */
	default List<Integer> getBorderDash() {
		return Collections.emptyList();
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset
	 */
	default double getBorderDashOffset() {
		return Defaults.get().getGlobal().getElements().getLine().getBorderDashOffset();
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
	 * Returns the callback called to set the color of the border of annotation.
	 * 
	 * @return the callback called to set the color of the border of annotation
	 */
	default ColorCallback<AnnotationContext> getBorderColorCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the width of the border in pixels.
	 * 
	 * @return the callback called to set the width of the border in pixels
	 */
	default WidthCallback<AnnotationContext> getBorderWidthCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 * describe the pattern.
	 * 
	 * @return the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 *         describe the pattern
	 */
	default BorderDashCallback<AnnotationContext> getBorderDashCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the line dash pattern offset.
	 * 
	 * @return the callback called to set the line dash pattern offset
	 */
	default BorderDashOffsetCallback<AnnotationContext> getBorderDashOffsetCallback() {
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

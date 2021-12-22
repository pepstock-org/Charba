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
import java.util.List;

import org.pepstock.charba.client.annotation.callbacks.LabelPositionCallback;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.callbacks.BorderDashCallback;
import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.CapStyleCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.items.Undefined;

/**
 * This is the {@link AnnotationPlugin#ID} plugin LABEL of LINE annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsLineLabel extends IsDefaultsInnerLabel, IsDefaultsBackgroundColorHandler, IsDefaultsBorderRadiusHandler {

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	default LabelPosition getPosition() {
		return LineLabel.DEFAULT_POSITION;
	}

	/**
	 * Returns the position of label on line by the percentage (value between 0 and 1) of the line dimension.
	 * 
	 * @return the position of label on line by the percentage (value between 0 and 1) of the line dimension
	 */
	default double getPositionAsPercentage() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the rotation of label in degrees.
	 * 
	 * @return the rotation of label in degrees
	 */
	default double getRotation() {
		return LineLabel.DEFAULT_ROTATION;
	}

	/**
	 * Returns <code>true</code> whether the rotation of label must calculates automatically.
	 * 
	 * @return <code>true</code> whether the rotation of label must calculates automatically
	 */
	default boolean isAutoRotation() {
		return false;
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	default CapStyle getBorderCapStyle() {
		return LineLabel.DEFAULT_BORDER_CAP_STYLE;
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	default String getBorderColorAsString() {
		return LineLabel.DEFAULT_BORDER_COLOR_AS_STRING;
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	default int getBorderWidth() {
		return LineLabel.DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	default List<Integer> getBorderDash() {
		return Collections.emptyList();
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	default double getBorderDashOffset() {
		return LineLabel.DEFAULT_BORDER_DASH_OFFSET;
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	default JoinStyle getBorderJoinStyle() {
		return LineLabel.DEFAULT_BORDER_JOIN_STYLE;
	}

	// ----------------
	// CALLBACKS
	// ----------------

	/**
	 * Returns the callback called to set the rotation of label in degrees.
	 * 
	 * @return the callback called to set the rotation of label in degrees
	 */
	default RotationCallback<AnnotationContext> getRotationCallback() {
		return null;
	}

	/**
	 * Returns the callback called to set the anchor position of label on line.
	 * 
	 * @return the callback called to set the anchor position of label on line
	 */
	default LabelPositionCallback getPositionCallback() {
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
	 * Returns the callback called to set the color of the border of annotation.
	 * 
	 * @return the callback called to set the color of the border of annotation
	 */
	default ColorCallback<AnnotationContext> getBorderColorCallback() {
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
	 * Returns the border capstyle callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border capstyle callback, if set, otherwise <code>null</code>.
	 */
	default CapStyleCallback<AnnotationContext> getBorderCapStyleCallback() {
		return null;
	}

	/**
	 * Returns the border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback, if set, otherwise <code>null</code>.
	 */
	default JoinStyleCallback<AnnotationContext> getBorderJoinStyleCallback() {
		return null;
	}
}

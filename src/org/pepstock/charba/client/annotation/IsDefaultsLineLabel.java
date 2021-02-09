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

import org.pepstock.charba.client.annotation.enums.LineLabelPosition;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * This is the {@link AnnotationPlugin#ID} plugin LABEL of LINE annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsLineLabel {

	/**
	 * Returns the font element.
	 * 
	 * @return the font element.
	 */
	IsDefaultFont getFont();

	/**
	 * Returns <code>true</code> whether the label is enabled and should be displayed.
	 * 
	 * @return <code>true</code> whether the label is enabled and should be displayed
	 */
	default boolean isEnabled() {
		return LineLabel.DEFAULT_ENABLED;
	}

	/**
	 * Returns the background color of label.
	 * 
	 * @return the background color of label
	 */
	default String getBackgroundColorAsString() {
		return LineLabel.DEFAULT_BACKGROUND_COLOR_AS_STRING;
	}

	/**
	 * Returns the font color of text as string.
	 * 
	 * @return the font color of text
	 */
	default String getColorAsString() {
		return LineLabel.DEFAULT_FONT_COLOR_AS_STRING;
	}

	/**
	 * Returns the padding of label to add left and right.
	 * 
	 * @return the padding of label to add left and right
	 */
	default int getXPadding() {
		return LineLabel.DEFAULT_X_PADDING;
	}

	/**
	 * Returns the padding of label to add top and bottom.
	 * 
	 * @return the padding of label to add top and bottom
	 */
	default int getYPadding() {
		return LineLabel.DEFAULT_Y_PADDING;
	}

	/**
	 * Returns the radius of label rectangle.
	 * 
	 * @return the radius of label rectangle
	 */
	default double getCornerRadius() {
		return LineLabel.DEFAULT_CORNER_RADIUS;
	}

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	default LineLabelPosition getPosition() {
		return LineLabel.DEFAULT_POSITION;
	}

	/**
	 * Returns the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along x-axis (left-right) of label
	 */
	default double getXAdjust() {
		return LineLabel.DEFAULT_X_ADJUST;
	}

	/**
	 * Returns the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along y-axis (top-bottom) of label
	 */
	default double getYAdjust() {
		return LineLabel.DEFAULT_Y_ADJUST;
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
	 * Returns the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	default int getHeight() {
		return UndefinedValues.INTEGER;
	}

	/**
	 * Returns the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the height of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default String getHeightAsString() {
		return null;
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in pixels in order to scale the image when drawn
	 */
	default int getWidth() {
		return UndefinedValues.INTEGER;
	}

	/**
	 * Returns the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn.
	 * 
	 * @return the width of label content, when is set as {@link Img}, in percentage (format is "{n}%") in order to scale the image when drawn
	 */
	default String getWidthAsString() {
		return null;
	}
}

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
import java.util.Comparator;
import java.util.List;

import org.pepstock.charba.client.annotation.enums.LineLabelPosition;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.elements.TextMetricsItem;
import org.pepstock.charba.client.dom.enums.TextAlign;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.positioner.Point;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Internal class created for each line label annotation options configured from the plugin.<br>
 * It uses the line annotation and line label options to calculate the area to be drawn.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class LineLabelAnnotationElement {

	// internal comparator to sort colors by own offset
	private static final Comparator<String> COMPARATOR = (String o1, String o2) -> o1.compareTo(o2);

	private final LineAnnotationElement annotationElement;

	private final LineLabel label;

	private final List<String> content;

	private final Point labelPosition = new Point();

	private double labelWidth = UndefinedValues.DOUBLE;

	private double labelHeight = UndefinedValues.DOUBLE;

	private double autoRotationInRadians = 0D;

	/**
	 * Creates an annotation element by an annotation configuration.
	 * 
	 * @param annotationElement annotation configuration element
	 */
	LineLabelAnnotationElement(LineAnnotationElement annotationElement) {
		this.annotationElement = annotationElement;
		// stores label and content
		this.label = annotationElement.getConfiguration().getLabel();
		this.content = label.getContent();
	}

	/**
	 * Configures the line label annotation element.
	 * 
	 * @param scale scale instance, configured to be used by annotation element
	 */
	void configure(ScaleItem scale) {
		// gets the label content
		List<String> content = label.getContent();
		// checks if the label must be drawn
		// and if content is consistent
		if (label.isEnabled() && !content.isEmpty()) {
			// gets context 2d, needed to calculate
			// the height and width of label
			// based on the font
			Context2dItem ctx = annotationElement.getChart().getCanvas().getContext2d();
			// gets font definition in CSS style
			String ctxFont = Utilities.toCSSFontProperty(label.getFontStyle(), Weight.NORMAL, label.getFontSize(), label.getFontFamily());
			// sets font to context
			ctx.setFont(ctxFont);
			// defines the 2 references, for height and width
			final double textWidth;
			final double textHeight = label.getFontSize();
			// checks if there is only 1 element
			if (content.size() == 1) {
				// the label content is a simple row
				// gets text metrics by context 2d by the label content
				TextMetricsItem metrics = ctx.measureText(content.get(0));
				// in order to get the width of the content
				textWidth = metrics.getWidth();
				// calculates the height adding padding
				labelHeight = textHeight + (label.getYPadding() * 2);
			} else {
				// the label content is multiple rows
				// calculates the longest element of content of label
				// to get the max width of label
				String longestLabelContent = Collections.max(content, COMPARATOR);
				// gets text metrics by context 2d by the longest content of label
				TextMetricsItem metrics = ctx.measureText(longestLabelContent);
				// in order to get the width of the content
				textWidth = metrics.getWidth();
				// calculates the height adding padding also between every rows of content
				labelHeight = (textHeight * content.size()) + (label.getYPadding() * 2) + (label.getYPadding() * (content.size() - 1));
			}
			// calculate label position
			Point position = calculateLabelPosition(scale, textWidth, textHeight);
			// calculates the width of label, adding padding
			labelWidth = textWidth + (label.getXPadding() * 2);
			// stores the label position considering the padding
			labelPosition.setX(position.getX() - label.getXPadding());
			labelPosition.setY(position.getY() - label.getYPadding());
			// checks if the auto rotation must e calculated
			// if the line does not an end value
			// skips calculation
			if (label.isAutoRotation() && !annotationElement.isEndValueMissing()) {
				// calculates internal rotation
				calculateLabelRotation(scale);
			}
		}
	}

	/**
	 * Returns <code>true</code> if the label must be drawn.
	 * 
	 * @return <code>true</code> if the label must be drawn
	 */
	boolean isEnabled() {
		return label.isEnabled() && !content.isEmpty();
	}

	/**
	 * Returns <code>true</code> if the label can be drawn.
	 * 
	 * @return <code>true</code> if the label can be drawn
	 */
	boolean isConsistent() {
		return labelPosition.isConsistent() && !Double.isNaN(labelWidth) && !Double.isNaN(labelHeight);
	}

	/**
	 * Draws the annotation line label element.
	 * 
	 * @param area chart area item
	 * @param ctx canvas context instance in order to be able to draw the element
	 */
	void draw(ChartAreaNode area, Context2dItem ctx) {
		// checks if the label must be drawn
		// and if content is consistent
		if (label.isEnabled() && !content.isEmpty()) {
			// creates new path
			ctx.beginPath();
			// clips the whole chart area
			ctx.rect(area.getLeft(), area.getTop(), area.getWidth(), area.getHeight());
			ctx.clip();
			// applies the coordinates of label
			ctx.translate(labelPosition.getX() + (labelWidth / 2), labelPosition.getY() + (labelHeight / 2));
			// checks which rotation must be applied
			if (label.isAutoRotation() && !Double.isNaN(autoRotationInRadians)) {
				// if here, the auto rotation has been set and the auto rotation is consistent
				// then applies the rotation calculated automatically
				ctx.rotate(autoRotationInRadians);
			} else if (!label.isAutoRotation() && !Double.isNaN(label.getRotation())) {
				// if here, the auto rotation has not been set and the rotation is consistent
				// then applies the rotation passed by configuration
				// in degrees, not radians
				ctx.rotate(label.getRotation() * Math.PI / 180);
			}
			// applies background color of label rectangle
			ctx.setFillColor(label.getBackgroundColorAsString());
			// draws the rectangle which contains the label
			drawRectangle(ctx);
			// gets font definition in CSS style
			String ctxFont = Utilities.toCSSFontProperty(label.getFontStyle(), Weight.NORMAL, label.getFontSize(), label.getFontFamily());
			// sets font to context
			ctx.setFont(ctxFont);
			// sets the fill color of label content
			ctx.setFillColor(label.getFontColorAsString());
			// sets the text alignment as center
			ctx.setTextAlign(TextAlign.CENTER);
			// checks if there are many content elements
			if (content.size() > 1) {
				// the label content is multiple rows
				// gets a reference for Y position of the label content
				// which must be incremented for each row
				double textYPosition = label.getYPadding() - (labelHeight / 2);
				// scans all label contents
				for (String contentItem : content) {
					// sets the text baseline to top
					ctx.setTextBaseline(TextBaseline.TOP);
					// applies the content of the label
					ctx.fillText(contentItem, 0, textYPosition);
					// increments the Y position of next row
					// adding the font size and the padding
					textYPosition += label.getFontSize() + label.getYPadding();
				}
			} else {
				// the label content is a simple row
				// sets the text baseline
				ctx.setTextBaseline(TextBaseline.MIDDLE);
				// applies the content of the label
				ctx.fillText(content.get(0), 0, 0);
			}
		}
	}

	/**
	 * Draws the rectangle which is containing the content of the label.
	 * 
	 * @param ctx canvas context instance in order to be able to draw the element
	 */
	private void drawRectangle(Context2dItem ctx) {
		// defines the coordinates of the rectangle
		final double x = labelWidth / -2;
		final double y = labelHeight / -2;
		// checks if must be designed a rounded rectangle
		if (!Double.isNaN(label.getCornerRadius()) && label.getCornerRadius() > 0) {
			// get corner radius from configuration
			double cornerRadius = label.getCornerRadius();
			// checks and adjusts corner radius
			// if corner radius is greater than width
			if (labelWidth < (cornerRadius * 2)) {
				// forces the corner radius the half of width
				cornerRadius = labelWidth / 2;
			}
			// if corner radius is greater than height
			if (labelHeight < (cornerRadius * 2)) {
				// forces the corner radius the half of height
				cornerRadius = labelHeight / 2;
			}
			// creates a path
			ctx.beginPath();
			// designs the rounded rectangle
			ctx.moveTo(x + cornerRadius, y);
			ctx.arcTo(x + labelWidth, y, x + labelWidth, y + labelHeight, cornerRadius);
			ctx.arcTo(x + labelWidth, y + labelHeight, x, y + labelHeight, cornerRadius);
			ctx.arcTo(x, y + labelHeight, x, y, cornerRadius);
			ctx.arcTo(x, y, x + labelWidth, y, cornerRadius);
			// closes path and fill the rectangle
			ctx.closePath();
			ctx.fill();
		} else {
			// if here, the rectangle is not rounded ut normal one
			ctx.fillRect(x, y, labelWidth, labelHeight);
		}
	}

	/**
	 * Calculates and returns a point width the position of label element, which must be drawn.
	 * 
	 * @param scale scale instance, configured to be used by annotation element
	 * @param width width of label
	 * @param height height of label
	 * @return the calculated point of label position
	 */
	private Point calculateLabelPosition(ScaleItem scale, double width, double height) {
		// creates the result to return
		final Point result = new Point();
		// gets the line starting and ending points
		final Point lineStartingPoint = annotationElement.getStartingPoint();
		final Point lineEndingPoint = annotationElement.getEndingPoint();
		// calculates the position using the orientation of scale
		// checks if the position if center
		if (LineLabelPosition.CENTER.equals(label.getPosition())) {
			// calculates the center of the line based on the width of label content
			double x = ((lineStartingPoint.getX() + lineEndingPoint.getX() - width) / 2) + label.getXAdjust();
			// calculates the center of the line based on the height of label content
			double y = ((lineStartingPoint.getY() + lineEndingPoint.getY() - height) / 2) + label.getYAdjust();
			// sets the result on the result
			result.setX(x);
			result.setY(y);
		} else if (AxisKind.X.equals(scale.getAxis())) {
			// if here, the scale is HORIZONTAL
			// then the line and label will be VERTICAL
			calculateLabelPositionForHorizontalScale(result, lineStartingPoint, lineEndingPoint, scale, width, height);
		} else if (AxisKind.Y.equals(scale.getAxis())) {
			// if here, the scale is VERTICAL
			// then the line and label will be HORIZONTAL
			calculateLabelPositionForVerticalScale(result, lineStartingPoint, lineEndingPoint, scale, width, height);
		}
		return result;
	}

	/**
	 * Calculates the rotation of label element, based on angles of triangle built on the line.
	 * 
	 * @param scale scale instance, configured to be used by annotation element
	 */
	private void calculateLabelRotation(ScaleItem scale) {
		// gets the line starting and ending points
		final Point lineStartingPoint = annotationElement.getStartingPoint();
		final Point lineEndingPoint = annotationElement.getEndingPoint();
		// gets the chart area
		ChartAreaNode area = annotationElement.getChart().getNode().getChartArea();
		// checks which kind of axis are related to the annotation
		if (AxisKind.X.equals(scale.getAxis())) {
			// if here, the scale is HORIZONTAL
			// then the line and label will be VERTICAL
			// calculates the 2 cathetus
			double cathetusOpposite = area.getHeight();
			double cathetusAdjacent = Math.abs(lineStartingPoint.getX() - lineEndingPoint.getX());
			// calculates the factor because if the end value is on the left of the start then factor is -1
			// otherwise is 1 (no change)
			double factor = lineEndingPoint.getX() <= lineStartingPoint.getX() ? -1 : 1;
			// calculates the rotation
			autoRotationInRadians = Math.atan(cathetusOpposite / cathetusAdjacent * factor);
		} else if (AxisKind.Y.equals(scale.getAxis())) {
			// if here, the scale is VERTICAL
			// then the line and label will be HORIZONTAL
			// calculates the 2 cathetus
			double cathetusAdjacent = area.getWidth();
			double cathetusOpposite = Math.abs(lineStartingPoint.getY() - lineEndingPoint.getY());
			// calculates the factor because if the end value is on the bottom of the start then factor is 1
			// otherwise is -1 (no change)
			double factor = lineEndingPoint.getY() > lineStartingPoint.getY() ? 1 : -1;
			// calculates the rotation
			autoRotationInRadians = Math.atan(cathetusOpposite / cathetusAdjacent * factor);
		}
	}

	/**
	 * Calculates and sets a point width the position of label element, which must be drawn, when the scale is horizontal.
	 * 
	 * @param result the point instance to be updated with new coordinates
	 * @param lineStartingPoint the starting point instance of the line
	 * @param lineEndingPoint the ending point instance of the line
	 * @param scale scale instance, configured to be used by annotation element
	 * @param width width of label
	 * @param height height of label
	 */
	private void calculateLabelPositionForHorizontalScale(Point result, Point lineStartingPoint, Point lineEndingPoint, ScaleItem scale, double width, double height) {
		// checks if the label has been required to be to top
		if (LineLabelPosition.TOP.equals(label.getPosition())) {
			// gets the filler part on top
			double yAdjusted = label.getYPadding() + label.getYAdjust();
			// gets the adjusted X position, half of label width
			double xAdjusted = (width / 2) + label.getXAdjust();
			// sets the Y position adding the starting point of the line
			result.setY(lineStartingPoint.getY() + yAdjusted);
			// leveraging on line linear equation, gets the X position
			double tempX = Double.isFinite(annotationElement.getLineFunction().getM()) ? annotationElement.getLineFunction().getX(result.getY()) : lineStartingPoint.getX();
			// sets the X position applying the adjusted X
			result.setX(tempX - xAdjusted);
		} else if (LineLabelPosition.BOTTOM.equals(label.getPosition())) {
			// checks if the label has been required to be to bottom
			// gets the adjusted Y position with height with padding and adjustment
			double yAdjust = height + label.getYPadding() + label.getYAdjust();
			// gets the adjusted X position, half of label width
			double xAdjust = (width / 2) + label.getXAdjust();
			// sets the Y position adding the starting point of the line
			result.setY(lineEndingPoint.getY() - yAdjust);
			// leveraging on line linear equation, gets the X position
			double tempX = Double.isFinite(annotationElement.getLineFunction().getM()) ? annotationElement.getLineFunction().getX(result.getY()) : lineStartingPoint.getX();
			// sets the X position applying the adjusted X
			result.setX(tempX - xAdjust);
		}
	}

	/**
	 * Calculates and sets a point width the position of label element, which must be drawn, when the scale is vertical.
	 * 
	 * @param result the point instance to be updated with new coordinates
	 * @param lineStartingPoint the starting point instance of the line
	 * @param lineEndingPoint the ending point instance of the line
	 * @param scale scale instance, configured to be used by annotation element
	 * @param width width of label
	 * @param height height of label
	 */
	private void calculateLabelPositionForVerticalScale(Point result, Point lineStartingPoint, Point lineEndingPoint, ScaleItem scale, double width, double height) {
		// checks if the label has been required to be to left
		if (LineLabelPosition.LEFT.equals(label.getPosition())) {
			// gets the filler part on left
			double xAdjusted = label.getXPadding() + label.getXAdjust();
			// gets the adjusted Y position, half of label width
			double yAdjusted = label.getYAdjust() - (height / 2);
			// sets the X position adding the starting point of the line
			result.setX(lineStartingPoint.getX() + xAdjusted);
			// leveraging on line linear equation, sets the Y position
			result.setY(annotationElement.getLineFunction().getY(result.getX()) + yAdjusted);
		} else if (LineLabelPosition.RIGHT.equals(label.getPosition())) {
			// gets the filler part on left
			double xAdjusted = width + label.getXPadding() + label.getXAdjust();
			// gets the adjusted Y position, half of label width
			double yAdjusted = label.getYAdjust() - (height / 2);
			// sets the X position adding the starting point of the line
			result.setX(lineEndingPoint.getX() - xAdjusted);
			// leveraging on line linear equation, sets the Y position
			result.setY(annotationElement.getLineFunction().getY(result.getX()) + yAdjusted);
		}
	}

	/**
	 * Returns <code>true</code> if the event location is overriding the annotation line label element, otherwise <code>false</code>.
	 * 
	 * @param event event instance
	 * @return <code>true</code> if the event location is overriding the annotation line label element, otherwise <code>false</code>
	 */
	boolean isInside(BaseNativeEvent event) {
		// checks if consistent
		if (isEnabled() && isConsistent()) {
			// checks X
			boolean isX = event.getLayerX() >= labelPosition.getX() && event.getLayerX() <= (labelPosition.getX() + labelWidth);
			// checks Y
			boolean isY = event.getLayerY() >= labelPosition.getY() && event.getLayerY() <= (labelPosition.getY() + labelHeight);
			return isX && isY;
		}
		// if here is not consistent
		// then always false
		return false;
	}

}

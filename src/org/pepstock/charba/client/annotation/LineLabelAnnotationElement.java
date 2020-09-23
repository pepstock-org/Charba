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
import java.util.Map;

import org.pepstock.charba.client.ChartNode;
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
import org.pepstock.charba.client.items.ScalesNode;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.positioner.Point;
import org.pepstock.charba.client.utils.Utilities;

/**
 * FIXME
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

	/**
	 * Creates an annotation element by an annotation configuration.
	 * 
	 * @param chart chart instance
	 * @param configuration annotation configuration element
	 */
	LineLabelAnnotationElement(LineAnnotationElement annotationElement) {
		this.annotationElement = annotationElement;
		// stores label and content
		this.label = annotationElement.getConfiguration().getLabel();
		this.content = label.getContent();
	}

	/**
	 * 
	 */
	void configure() {
		// gets chart node
		ChartNode node = annotationElement.getChart().getNode();
		// gets chart area
		ChartAreaNode area = node.getChartArea();
		// checks if chart area is consistent
		if (!area.isConsistent()) {
			// if not, returns
			return;
		}
		// gets scales information
		// gets scale nodes
		ScalesNode scalesNode = node.getScales();
		// gets map with all scale items
		Map<String, ScaleItem> scalesMap = scalesNode.getItems();
		// checks if scale nodes contains the scale id for X
		if (scalesMap.containsKey(annotationElement.getConfiguration().getScaleID().value())) {
			ScaleItem scale = scalesMap.get(annotationElement.getConfiguration().getScaleID().value());
			configureLabel(scale);
		}
	}

	boolean isEnabled() {
		return label.isEnabled() && !content.isEmpty();
	}

	boolean isConsistent() {
		return labelPosition.isConsistent() && !Double.isNaN(labelWidth) && !Double.isNaN(labelHeight);
	}

	void draw() {
		// gets chart node
		ChartNode node = annotationElement.getChart().getNode();
		// gets chart area
		ChartAreaNode area = node.getChartArea();
		// checks if chart area is consistent
		if (!area.isConsistent() || !isConsistent()) {
			// if not, returns
			return;
		}
		Context2dItem ctx = annotationElement.getChart().getCanvas().getContext2d();

		// ---------------
		// LABEL
		// ---------------
		// checks if the label must be drawn
		// and if content is consistent
		if (label.isEnabled() && !content.isEmpty()) {

			ctx.beginPath();
			ctx.rect(area.getLeft(), area.getTop(), area.getWidth(), area.getHeight());
			ctx.clip();

			ctx.translate(labelPosition.getX() + (labelWidth / 2), labelPosition.getY() + (labelHeight / 2));
			ctx.rotate(label.getRotation() * Math.PI / 180);

			ctx.setFillColor(label.getBackgroundColorAsString());

			drawRectangle(ctx);

			String ctxFont = Utilities.toCSSFontProperty(label.getFontStyle(), Weight.NORMAL, label.getFontSize(), label.getFontFamily());
			ctx.setFont(ctxFont);
			ctx.setFillColor(label.getFontColorAsString());
			ctx.setTextAlign(TextAlign.CENTER);

			// checks if there is only 1 element
			if (content.size() > 1) {
				double textYPosition = label.getYPadding() - (labelHeight / 2);
				for (String contentItem : content) {
					ctx.setTextBaseline(TextBaseline.TOP);
					ctx.fillText(contentItem, 0, textYPosition);
					textYPosition += label.getFontSize() + label.getYPadding();
				}
			} else {
				ctx.setTextBaseline(TextBaseline.MIDDLE);
				ctx.fillText(content.get(0), 0, 0);
			}
		}
	}

	private void drawRectangle(Context2dItem ctx) {
		final double x = labelWidth / -2;
		final double y = labelHeight / -2;
		// checks if must be designed a rounded rect
		if (!Double.isNaN(label.getCornerRadius()) && label.getCornerRadius() > 0) {
			// get radius
			double radius = label.getCornerRadius();
			// checks and adjusts corner radius
			if (labelWidth < (radius * 2)) {
				radius = labelWidth / 2;
			}
			if (labelHeight < (radius * 2)) {
				radius = labelHeight / 2;
			}
			ctx.beginPath();
			ctx.moveTo(x + radius, y);
			ctx.arcTo(x + labelWidth, y, x + labelWidth, y + labelHeight, radius);
			ctx.arcTo(x + labelWidth, y + labelHeight, x, y + labelHeight, radius);
			ctx.arcTo(x, y + labelHeight, x, y, radius);
			ctx.arcTo(x, y, x + labelWidth, y, radius);
			ctx.closePath();
			ctx.fill();
		} else {
			ctx.fillRect(x, y, labelWidth, labelHeight);
		}
	}

	/**
	 * 
	 * @param area
	 * @param scale
	 * @param scaleLimit
	 * @param isXScale
	 */
	private void configureLabel(ScaleItem scale) {
		LineLabel label = annotationElement.getConfiguration().getLabel();
		List<String> content = label.getContent();
		// checks if the label must be drawn
		// and if content is consistent
		if (label.isEnabled() && !content.isEmpty()) {
			Context2dItem ctx = annotationElement.getChart().getCanvas().getContext2d();
			String ctxFont = Utilities.toCSSFontProperty(label.getFontStyle(), Weight.NORMAL, label.getFontSize(), label.getFontFamily());
			ctx.setFont(ctxFont);

			final double textWidth;
			final double textHeight = label.getFontSize();
			// checks if there is only 1 element
			if (content.size() == 1) {
				TextMetricsItem metrics = ctx.measureText(content.get(0));
				textWidth = metrics.getWidth();
				labelHeight = textHeight + (label.getYPadding() * 2);
			} else {
				String longestLabelContent = Collections.max(content, COMPARATOR);
				TextMetricsItem metrics = ctx.measureText(longestLabelContent);
				textWidth = metrics.getWidth();
				labelHeight = (textHeight * content.size()) + (label.getYPadding() * 2) + (label.getYPadding() * (content.size() - 1));
			}
			// calculate label position
			Point position = calculateLabelPosition(scale, textWidth, textHeight);
			labelWidth = textWidth + (label.getXPadding() * 2);
			labelPosition.setX(position.getX() - label.getXPadding());
			labelPosition.setY(position.getY() - label.getYPadding());
		}
	}

	/**
	 * FIXME
	 * 
	 * @param scale
	 * @param label
	 * @param width
	 * @param height
	 * @return
	 */
	private Point calculateLabelPosition(ScaleItem scale, double width, double height) {

		final Point result = new Point();
		final Point lineStartingPoint = annotationElement.getStartingPoint();
		final Point lineEndingPoint = annotationElement.getEndingPoint();

		double xa = 0;
		double ya = 0;

		if (LineLabelPosition.CENTER.equals(label.getPosition())) {

			double x = ((lineStartingPoint.getX() + lineEndingPoint.getX() - width) / 2) + label.getXAdjust();
			double y = ((lineStartingPoint.getY() + lineEndingPoint.getY() - height) / 2) + label.getYAdjust();
			result.setX(x);
			result.setY(y);

		} else if (AxisKind.X.equals(scale.getAxis())) {
			if (LineLabelPosition.TOP.equals(label.getPosition())) {
				ya = label.getYPadding() + label.getYAdjust();
				xa = (width / 2) + label.getXAdjust();
				result.setY(lineStartingPoint.getY() + ya);
				double tempX = Double.isFinite(annotationElement.getLineFunction().getM()) ? annotationElement.getLineFunction().getX(result.getY()) : lineStartingPoint.getX();
				result.setX(tempX - xa);
			} else if (LineLabelPosition.BOTTOM.equals(label.getPosition())) {
				ya = height + label.getYPadding() + label.getYAdjust();
				xa = (width / 2) + label.getXAdjust();
				result.setY(lineEndingPoint.getY() - ya);
				double tempX = Double.isFinite(annotationElement.getLineFunction().getM()) ? annotationElement.getLineFunction().getX(result.getY()) : lineStartingPoint.getX();
				result.setX(tempX - xa);
			}
		} else if (AxisKind.Y.equals(scale.getAxis())) {
			if (LineLabelPosition.LEFT.equals(label.getPosition())) {
				xa = label.getXPadding() + label.getXAdjust();
				ya = label.getYAdjust() - (height / 2);
				result.setX(lineStartingPoint.getX() + xa);
				result.setY(annotationElement.getLineFunction().getY(result.getX()) + ya);
			} else if (LineLabelPosition.RIGHT.equals(label.getPosition())) {
				xa = width + label.getXPadding() + label.getXAdjust();
				ya = label.getYAdjust() - (height / 2);
				result.setX(lineEndingPoint.getX() - xa);
				result.setY(annotationElement.getLineFunction().getY(result.getX()) + ya);
			}
		}
		return result;
	}
	
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

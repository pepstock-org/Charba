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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayImage;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.items.UndefinedValues;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This class collects a set of common field for Line and Radar charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class LiningDataset extends Dataset {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderColor,
		borderDash,
		borderDashOffset,
		borderCapStyle,
		borderJoinStyle,
		borderWidth,
		fill,
		lineTension,
		pointBackgroundColor,
		pointBorderColor,
		pointBorderWidth,
		pointRadius,
		pointStyle,
		pointHitRadius,
		pointHoverBackgroundColor,
		pointHoverBorderColor,
		pointHoverBorderWidth,
		pointHoverRadius,
		pointRotation,
		// internal key to store if point style is an image or not
		_charbaPointStyle,
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	LiningDataset() {
		super();
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	LiningDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
	}

	/**
	 * Sets the fill color under the line.
	 * 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(backgroundColor.toRGBA());
	}

	/**
	 * Sets the fill color under the line.
	 * 
	 * @param backgroundColor the fill color under the line.
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.backgroundColor);
	}

	/**
	 * Sets the fill pattern under the line.
	 * 
	 * @param backgroundColor the fill pattern under the line.
	 */
	public void setBackgroundColor(Pattern backgroundColor) {
		// sets value to patterns
		getPatternsContainer().setObjects(Property.backgroundColor, ArrayObject.of(backgroundColor));
		// removes the property
		resetBeingPatterns(Property.backgroundColor);
	}

	/**
	 * Sets the fill gradient under the line.
	 * 
	 * @param backgroundColor the fill gradient under the line.
	 */
	public void setBackgroundColor(Gradient backgroundColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.backgroundColor, ArrayObject.of(backgroundColor));
		// removes the property
		resetBeingGradients(Property.backgroundColor);
	}

	/**
	 * Returns the fill color under the line. If property is missing or not a color, returns the default background color.
	 * 
	 * @return the fill color under the line. If property is missing or not a color, returns the default background color.
	 */
	public String getBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.backgroundColor)) {
			// returns color as string
			return getValue(Property.backgroundColor, getDefaultValues().getElements().getLine().getBackgroundColorAsString());
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return getDefaultValues().getElements().getLine().getBackgroundColorAsString();
		}
	}

	/**
	 * Returns the fill color under the line. If property is missing or not a color, returns the default background color.
	 * 
	 * @return the fill color under the line. If property is missing or not a color, returns the default background color.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the fill pattern under the line. If property is missing or not a pattern, returns <code>null</code>.
	 * 
	 * @return the fill pattern under the line. If property is missing or not a pattern, returns <code>null</code>.
	 */
	public Pattern getBackgroundColorAsPattern() {
		// checks if the property is not a pattern (therefore a color or gradient)
		if (hasPatterns(Property.backgroundColor)) {
			List<Pattern> patterns = getPatternsContainer().getObjects(Property.backgroundColor);
			// returns color as pattern
			return patterns.get(0);
		} else {
			// if here, the property is not a object
			// or the property is missing or a color
			// returns null
			// FIXME verificare nel POINT element delle options
			return null;
		}
	}

	/**
	 * Returns the fill gradient under the line. If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return the fill gradient under the line. If property is missing or not a gradient, returns <code>null</code>.
	 */
	public Gradient getBackgroundColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.backgroundColor)) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Property.backgroundColor);
			// returns color as gradient
			return gradients.get(0);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns null
			// FIXME verificare nel POINT element delle options
			return null;
		}
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(borderColor.toRGBA());
	}

	/**
	 * Sets the color of the line.
	 * 
	 * @param borderColor the color of the line.
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.borderColor);
	}

	/**
	 * Sets the gradient of the line.
	 * 
	 * @param borderColor the gradient of the line.
	 */
	public void setBorderColor(Gradient borderColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.borderColor, ArrayObject.of(borderColor));
		// removes the property
		resetBeingGradients(Property.borderColor);
	}

	/**
	 * Returns the color of the line. If property is missing or not a color, returns the default border color.
	 * 
	 * @return the color of the line. If property is missing or not a color, returns the default border color.
	 */
	public String getBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.borderColor)) {
			// returns color as string
			return getValue(Property.borderColor, getDefaultValues().getElements().getLine().getBorderColorAsString());
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return getDefaultValues().getElements().getLine().getBorderColorAsString();
		}
	}

	/**
	 * Returns the color of the line. If property is missing or not a color, returns the default border color.
	 * 
	 * @return the color of the line. If property is missing or not a color, returns the default border color.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Returns the gradient of the line. If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return the gradient of the line. If property is missing or not a gradient, returns <code>null</code>.
	 */
	public Gradient getBorderColorAsGradient() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.borderColor)) {
			List<Gradient> gradients = getGradientsContainer().getObjects(Property.borderColor);
			// returns color as gradient
			return gradients.get(0);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns null
			// FIXME verificare nel POINT element delle options
			return null;
		}
	}

	/**
	 * Sets the width of the line in pixels.
	 * 
	 * @param borderWidth the width of the line in pixels.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the width of the line in pixels.
	 * 
	 * @return the width of the line in pixels.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, getDefaultValues().getElements().getLine().getBorderWidth());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		setArrayValue(Property.borderDash, ArrayInteger.of(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 *         lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.borderDash);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase".
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, getDefaultValues().getElements().getLine().getBorderDashOffset());
	}

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		setValue(Property.borderCapStyle, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		return getValue(Property.borderCapStyle, CapStyle.class, getDefaultValues().getElements().getLine().getBorderCapStyle());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter.
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		setValue(Property.borderJoinStyle, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified end points and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter.
	 */
	public JoinStyle getBorderJoinStyle() {
		return getValue(Property.borderJoinStyle, JoinStyle.class, getDefaultValues().getElements().getLine().getBorderJoinStyle());
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	public void setFill(Fill fill) {
		// checks if is no fill
		if (Fill.nofill.equals(fill)) {
			// sets the boolean value instead of string one
			setValue(Property.fill, false);
		} else {
			// sets value
			setValue(Property.fill, fill);
		}
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	public Fill getFill() {
		// gets value type
		ObjectType type = type(Property.fill);
		// if is a boolean FALSE value
		if (ObjectType.Boolean.equals(type)) {
			// returns no fill
			return getValue(Property.fill, false) ? Fill.origin : Fill.nofill;
		}
		// returns the fill object because was not stored as boolean
		return getValue(Property.fill, Fill.class, getDefaultValues().getElements().getLine().getFill());
	}

	/**
	 * Sets curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic interpolation
	 * is used.
	 * 
	 * @param lineTension curve tension of the line
	 */
	public void setLineTension(double lineTension) {
		setValue(Property.lineTension, lineTension);
	}

	/**
	 * Returns curve tension of the line. Set to 0 to draw straight lines. This option is ignored if monotone cubic
	 * interpolation is used.
	 * 
	 * @return curve tension of the line.
	 */
	public double getLineTension() {
		return getValue(Property.lineTension, getDefaultValues().getElements().getLine().getTension());
	}

	/**
	 * Sets the fill color for points.
	 * 
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(IsColor... pointBackgroundColor) {
		setValueOrArray(Property.pointBackgroundColor, pointBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointBackgroundColor);
	}

	/**
	 * Sets the fill color for points.
	 * 
	 * @param pointBackgroundColor array of the fill color for points.
	 */
	public void setPointBackgroundColor(String... pointBackgroundColor) {
		setValueOrArray(Property.pointBackgroundColor, pointBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointBackgroundColor);
	}

	/**
	 * Sets the gradients for points.
	 * 
	 * @param pointBackgroundColor array of the gradients for points.
	 */
	public void setPointBackgroundColor(Gradient... pointBackgroundColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.pointBackgroundColor, ArrayObject.of(pointBackgroundColor));
		// removes the property
		resetBeingGradients(Property.pointBackgroundColor);
	}

	/**
	 * Returns the fill color for points. If property is missing or not a color, returns the default point background color
	 * color.
	 * 
	 * @return list of the fill color for points. If property is missing or not a color, returns the point background color
	 *         color.
	 */
	public List<String> getPointBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.pointBackgroundColor)) {
			// returns color as string
			ArrayString array = getValueOrArray(Property.pointBackgroundColor, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a gradient
			// returns default value
			return ArrayListHelper.list(ArrayString.of(getDefaultValues().getElements().getPoint().getBackgroundColorAsString()));
		}
	}

	/**
	 * Returns the fill color for points.
	 * 
	 * @return list of the fill color for points.
	 */
	public List<IsColor> getPointBackgroundColor() {
		return ColorBuilder.parse(getPointBackgroundColorAsString());
	}

	/**
	 * Returns the fill color for points. If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return list of the fill color for points. If property is missing or not a gradient, returns <code>null</code>.
	 */
	public List<Gradient> getPointBackgroundColorAsGradients() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.pointBackgroundColor)) {
			return getGradientsContainer().getObjects(Property.pointBackgroundColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns null
			// FIXME verificare nel POINT element delle options
			return null;
		}
	}

	/**
	 * Sets the border color for points.
	 * 
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(IsColor... pointBorderColor) {
		setValueOrArray(Property.pointBorderColor, pointBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointBorderColor);

	}

	/**
	 * Sets the border color for points.
	 * 
	 * @param pointBorderColor array of the border color for points.
	 */
	public void setPointBorderColor(String... pointBorderColor) {
		setValueOrArray(Property.pointBorderColor, pointBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointBorderColor);
	}

	/**
	 * Sets the border gradient for points.
	 * 
	 * @param pointBorderColor array of the border gradient for points.
	 */
	public void setPointBorderColor(Gradient... pointBorderColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.pointBorderColor, ArrayObject.of(pointBorderColor));
		// removes the property
		resetBeingGradients(Property.pointBorderColor);
	}

	/**
	 * Returns the border color for points. If property is missing or not a color, returns the default point border color.
	 * 
	 * @return list of the border color for points. If property is missing or not a color, returns the default point border
	 *         color.
	 */
	public List<String> getPointBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.pointBorderColor)) {
			// returns color as string
			ArrayString array = getValueOrArray(Property.pointBorderColor, getDefaultValues().getElements().getPoint().getBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return ArrayListHelper.list(ArrayString.of(getDefaultValues().getElements().getLine().getBorderColorAsString()));
		}
	}

	/**
	 * Returns the border color for points. If property is missing or not a color, returns the default point border color.
	 * 
	 * @return list of the border color for points. If property is missing or not a color, returns the default point border
	 *         color.
	 */
	public List<IsColor> getPointBorderColor() {
		return ColorBuilder.parse(getPointBorderColorAsString());
	}

	/**
	 * Returns the border gradient for points. If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return list of the border gradient for points. If property is missing or not a gradient, returns <code>null</code>.
	 */
	public List<Gradient> getPointBorderColorAsGradients() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.pointBorderColor)) {
			return getGradientsContainer().getObjects(Property.pointBorderColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns null
			// FIXME verificare nel POINT element delle options
			return null;
		}
	}

	/**
	 * Sets the width of the point border in pixels.
	 * 
	 * @param pointBorderWidth array of the width of the point border in pixels.
	 */
	public void setPointBorderWidth(int... pointBorderWidth) {
		setValueOrArray(Property.pointBorderWidth, pointBorderWidth);
	}

	/**
	 * Returns the width of the point border in pixels.
	 * 
	 * @return list of the width of the point border in pixels.
	 */
	public List<Integer> getPointBorderWidth() {
		ArrayInteger array = getValueOrArray(Property.pointBorderWidth, getDefaultValues().getElements().getPoint().getBorderWidth());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param pointHitRadius array of the pixel size of the non-displayed point.
	 */
	public void setPointHitRadius(double... pointHitRadius) {
		setValueOrArray(Property.pointHitRadius, pointHitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getPointHitRadius() {
		ArrayDouble array = getValueOrArray(Property.pointHitRadius, getDefaultValues().getElements().getPoint().getHitRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the point background color when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(IsColor... pointHoverBackgroundColor) {
		setValueOrArray(Property.pointHoverBackgroundColor, pointHoverBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointHoverBackgroundColor);
	}

	/**
	 * Sets the point background color when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background color when hovered.
	 */
	public void setPointHoverBackgroundColor(String... pointHoverBackgroundColor) {
		setValueOrArray(Property.pointHoverBackgroundColor, pointHoverBackgroundColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointHoverBackgroundColor);
	}

	/**
	 * Sets the point background gradient when hovered.
	 * 
	 * @param pointHoverBackgroundColor array of the point background gradient when hovered.
	 */
	public void setPointHoverBackgroundColor(Gradient... pointHoverBackgroundColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.pointHoverBackgroundColor, ArrayObject.of(pointHoverBackgroundColor));
		// removes the property
		resetBeingGradients(Property.pointHoverBackgroundColor);
	}

	/**
	 * Returns the point background color when hovered. If property is missing or not a color, returns the default point
	 * background color.
	 * 
	 * @return list of the point background color when hovered. If property is missing or not a color, returns the default point
	 *         background color.
	 */
	public List<String> getPointHoverBackgroundColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.pointHoverBackgroundColor)) {
			ArrayString array = getValueOrArray(Property.pointHoverBackgroundColor, getDefaultValues().getElements().getPoint().getBackgroundColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return ArrayListHelper.list(ArrayString.of(getDefaultValues().getElements().getPoint().getBackgroundColorAsString()));
		}
	}

	/**
	 * Returns the point background color when hovered. If property is missing or not a color, returns the default point
	 * background color.
	 * 
	 * @return list of the point background color when hovered. If property is missing or not a color, returns the default point
	 *         background color.
	 */
	public List<IsColor> getPointHoverBackgroundColor() {
		return ColorBuilder.parse(getPointHoverBackgroundColorAsString());
	}

	/**
	 * Returns the point background color when hovered. If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return list of the point background color when hovered. If property is missing or not a gradient, returns
	 *         <code>null</code>.
	 */
	public List<Gradient> getPointHoverBackgroundColorAsGradients() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.pointHoverBackgroundColor)) {
			return getGradientsContainer().getObjects(Property.pointHoverBackgroundColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns null
			// FIXME verificare nel POINT element delle options
			return null;
		}
	}

	/**
	 * Sets the point border color when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(IsColor... pointHoverBorderColor) {
		setValueOrArray(Property.pointHoverBorderColor, pointHoverBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointHoverBorderColor);
	}

	/**
	 * Sets the point border color when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border color when hovered.
	 */
	public void setPointHoverBorderColor(String... pointHoverBorderColor) {
		setValueOrArray(Property.pointHoverBorderColor, pointHoverBorderColor);
		// removes the flag because default is string color
		resetBeingColors(Property.pointHoverBorderColor);
	}

	/**
	 * Sets the point border gradient when hovered.
	 * 
	 * @param pointHoverBorderColor array of the point border gradient when hovered.
	 */
	public void setPointHoverBorderColor(Gradient... pointHoverBorderColor) {
		// sets value to gradients
		getGradientsContainer().setObjects(Property.pointHoverBorderColor, ArrayObject.of(pointHoverBorderColor));
		// removes the property
		resetBeingGradients(Property.pointHoverBorderColor);
	}

	/**
	 * Returns the point border color when hovered. If property is missing or not a color, returns the default border color.
	 * 
	 * @return list of the point border color when hovered. If property is missing or not a color, returns the default border
	 *         color.
	 */
	public List<String> getPointHoverBorderColorAsString() {
		// checks if the property is not a pattern or gradient (therefore a color)
		if (hasColors(Property.pointHoverBorderColor)) {
			ArrayString array = getValueOrArray(Property.pointHoverBorderColor, getDefaultValues().getElements().getPoint().getBorderColorAsString());
			return ArrayListHelper.list(array);
		} else {
			// if here, the property is not a string
			// or the property is missing or a pattern
			// returns default value
			return ArrayListHelper.list(ArrayString.of(getDefaultValues().getElements().getPoint().getBorderColorAsString()));
		}
	}

	/**
	 * Returns the point border color when hovered. If property is missing or not a color, returns the default border color.
	 * 
	 * @return list of the point border color when hovered. If property is missing or not a color, returns the default border
	 *         color.
	 */
	public List<IsColor> getPointHoverBorderColor() {
		return ColorBuilder.parse(getPointHoverBorderColorAsString());
	}

	/**
	 * Returns the point border gradient when hovered. If property is missing or not a gradient, returns <code>null</code>.
	 * 
	 * @return list of the point border gradient when hovered. If property is missing or not a gradient, returns
	 *         <code>null</code>.
	 */
	public List<Gradient> getPointHoverBorderColorAsGradients() {
		// checks if the property is not a gradient (therefore a color or pattern)
		if (hasGradients(Property.pointHoverBorderColor)) {
			return getGradientsContainer().getObjects(Property.pointHoverBorderColor);
		} else {
			// if here, the property is not a gradient
			// or the property is missing
			// returns null
			// FIXME verificare nel POINT element delle options
			return null;
		}
	}

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param pointHoverBorderWidth array of the border width of point when hovered.
	 */
	public void setPointHoverBorderWidth(int... pointHoverBorderWidth) {
		setValueOrArray(Property.pointHoverBorderWidth, pointHoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return list of the border width of point when hovered.
	 */
	public List<Integer> getPointHoverBorderWidth() {
		ArrayInteger array = getValueOrArray(Property.pointHoverBorderWidth, getDefaultValues().getElements().getPoint().getHoverBorderWidth());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param pointHoverRadius array of the radius of the point when hovered.
	 */
	public void setPointHoverRadius(double... pointHoverRadius) {
		setValueOrArray(Property.pointHoverRadius, pointHoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getPointHoverRadius() {
		ArrayDouble array = getValueOrArray(Property.pointHoverRadius, getDefaultValues().getElements().getPoint().getHoverRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * 
	 * @param pointRadius array of the radius of the point shape.
	 */
	public void setPointRadius(double... pointRadius) {
		setValueOrArray(Property.pointRadius, pointRadius);
	}

	/**
	 * Returns the radius of the point shape.
	 * 
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getPointRadius() {
		ArrayDouble array = getValueOrArray(Property.pointRadius, getDefaultValues().getElements().getPoint().getRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		setValueOrArray(Property.pointStyle, pointStyle);
		// remove if exist flag
		removeIfExists(Property._charbaPointStyle);
	}

	/**
	 * Returns the style of the point. If property is missing or not a point style, returns <code>null</code>.
	 * 
	 * @return list of the style of the point. If property is missing or not a point style, returns <code>null</code>.
	 */
	public List<PointStyle> getPointStyle() {
		// checks if image as point style has been used
		if (!getValue(Property._charbaPointStyle, false)) {
			// if not, returns point styles
			ArrayString array = getValueOrArray(Property.pointStyle, getDefaultValues().getElements().getPoint().getPointStyle());
			return ArrayListHelper.list(PointStyle.class, array);
		} else {
			// if here, means the point style as stored as images
			return null;
		}
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle array of the style of the point as image.
	 */
	public void setPointStyle(ImageResource... pointStyle) {
		// checks if argument is consistent
		if (pointStyle != null) {
			// creates a temporary array
			ImageElement[] array = new ImageElement[pointStyle.length];
			// scans passed array of images
			for (int i = 0; i < pointStyle.length; i++) {
				// transform a image resource into image element by image object
				// creates image object
				Image img = new Image(pointStyle[i]);
				// stores into array changing in image element
				array[i] = ImageElement.as(img.getElement());
			}
			// stores it
			setPointStyle(array);
		} else {
			// if here, argument is null
			// then removes property
			remove(Property.pointStyle);
			// remove flag
			removeIfExists(Property._charbaPointStyle);
		}
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle array of the style of the point as image.
	 */
	public void setPointStyle(ImageElement... pointStyle) {
		setValueOrArray(Property.pointStyle, pointStyle);
		// sets flag
		setValue(Property._charbaPointStyle, true);
	}

	/**
	 * Returns the style of the point as image. If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return list of the style of the point as image. If property is missing or not a image, returns <code>null</code>.
	 */
	public List<ImageElement> getPointStyleAsImages() {
		// checks if image as point style has been used
		if (getValue(Property._charbaPointStyle, false)) {
			// gets array
			ArrayImage array = getValueOrArray(Property.pointStyle, UndefinedValues.IMAGE_ELEMENT);
			return ArrayListHelper.list(array);
		} else {
			// if here, means the point style as stored as strings
			return null;
		}
	}

	/**
	 * Sets the rotation of the point in degrees.
	 * 
	 * @param pointRotation array of the rotation of the point in degrees.
	 */
	public void setPointRotation(double... pointRotation) {
		setValueOrArray(Property.pointRotation, pointRotation);
	}

	/**
	 * Returns the rotation of the point in degrees.
	 * 
	 * @return list of the rotation of the point in degrees.
	 */
	public List<Double> getPointRotation() {
		ArrayDouble array = getValueOrArray(Property.pointRotation, getDefaultValues().getElements().getPoint().getRotation());
		return ArrayListHelper.list(array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyPattern(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	void applyPattern(Key key, List<CanvasPattern> canvasPatternsList) {
		// checks if background color (ONLY one which can be used with patterns)
		if (Property.backgroundColor.name().equalsIgnoreCase(key.name())) {
			// gets the first element
			CanvasPattern pattern = canvasPatternsList.get(0);
			// creates pattern and stores it
			setValue(key, pattern);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyGradient(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	void applyGradient(Key key, List<CanvasGradient> canvasGradientsList) {
		// checks if background or border colors which must be set with single value
		if (Property.backgroundColor.name().equalsIgnoreCase(key.name()) || Property.borderColor.name().equalsIgnoreCase(key.name())) {
			// gets the first element
			CanvasGradient gradient = canvasGradientsList.get(0);
			// creates gradient and stores it
			setValue(key, gradient);
		} else {
			// stores the array
			setValueOrArray(key, canvasGradientsList.toArray(new CanvasGradient[0]));
		}
	}
}
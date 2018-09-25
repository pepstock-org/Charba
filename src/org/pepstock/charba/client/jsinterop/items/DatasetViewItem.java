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
package org.pepstock.charba.client.jsinterop.items;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This item provides all information about the view where a dataset has been displayed.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public final class DatasetViewItem extends NativeObject {
	
	@JsProperty(name="backgroundColor")
	native String getNativeBackgroundColor();
	
	@JsProperty(name="controlPointPreviousX")
	native double getNativeControlPointPreviousX();
	
	@JsProperty(name="controlPointPreviousY")
	native double getNativeControlPointPreviousY();
	
	@JsProperty(name="controlPointNextX")
	native double getNativeControlPointNextX();
	
	@JsProperty(name="controlPointNextY")
	native double getNativeControlPointNextY();
	
	@JsProperty(name="datasetLabel")
	native String getNativeDatasetLabel();
	
	@JsProperty(name="label")
	native String getNativeLabel();
	
	@JsProperty(name="borderSkipped")
	native String getNativeBorderSkipped();
	
	@JsProperty(name="borderColor")
	native String getNativeBorderColor();
	
	@JsProperty(name="borderWidth")
	native int getNativeBorderWidth();
	
	@JsProperty(name="horizontal")
	native boolean isNativeHorizontal();
	
	@JsProperty(name="base")
	native double getNativeBase();
	
	@JsProperty(name="x")
	native double getNativeX();
	
	@JsProperty(name="y")
	native double getNativeY();
	
	@JsProperty(name="width")
	native double getNativeWidth();
	
	@JsProperty(name="height")
	native double getNativeHeight();
	
	@JsProperty(name="skipped")
	native boolean isNativeSkipped();
	
	@JsProperty(name="radius")
	native double getNativeRadius();
	
	@JsProperty(name="pointStyle")
	native Object getNativePointStyle();
	
	@JsProperty(name="tension")
	native double getNativeTension();
	
	@JsProperty(name="hitRadius")
	native double getNativeHitRadius();
	
	@JsProperty(name="steppedLine")
	native boolean isNativeSteppedLine();
	
	@JsProperty(name="startAngle")
	native double getNativeStartAngle();
	
	@JsProperty(name="endAngle")
	native double getNativeEndAngle();
	
	@JsProperty(name="circumference")
	native double getNativeCircumference();
	
	@JsProperty(name="outerRadius")
	native double getNativeOuterRadius();
	
	@JsProperty(name="innerRadius")
	native double getNativeInnerRadius();

	/**
	 * Returns the dataset label.
	 * 
	 * @return the dataset label. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 * @see org.pepstock.charba.client.data.Dataset#setLabel(String)
	 */
	@JsOverlay
	public final String getDatasetLabel() {
		return AssignHelper.check(getNativeDatasetLabel(), UndefinedValues.STRING);
	}

	/**
	 * Returns the label.
	 * 
	 * @return the label. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public final String getLabel() {
		return AssignHelper.check(getNativeLabel(), UndefinedValues.STRING);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	@JsOverlay
	public final Position getBorderSkipped() {
		return AssignHelper.deserialize(AssignHelper.check(getNativeBorderSkipped(), Position.top.name()), Position.class, Position.top);
	}

	/**
	 * Returns the fill color of the dataset item.
	 * 
	 * @return list of the fill color of the dataset item.  Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public final String getBackgroundColorAsString() {
		return AssignHelper.check(getNativeBackgroundColor(), UndefinedValues.STRING);
	}

	/**
	 * Returns the fill color of the dataset item.
	 * 
	 * @return list of the fill color of the dataset item.
	 */
	@JsOverlay
	public final IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the color of the dataset item border
	 * 
	 * @return list of the color of the dataset item border. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public final String getBorderColorAsString() {
		return AssignHelper.check(getNativeBorderColor(), UndefinedValues.STRING);
	}

	/**
	 * Returns the color of the dataset item border
	 * 
	 * @return list of the color of the dataset item border
	 */
	@JsOverlay
	public final IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}
	
	/**
	 * Returns the stroke width of the dataset item in pixels.
	 * 
	 * @return list of the stroke width of the dataset item in pixels. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getBorderWidth() {
		return AssignHelper.check(getNativeBorderWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns if is an horizontal view.
	 * 
	 * @return <code>true</code> if is an horizontal view. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public final boolean isHorizontal() {
		return AssignHelper.check(isNativeHorizontal(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the base value of dataset.
	 * 
	 * @return the base value of dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getBase() {
		return AssignHelper.check(getNativeBase(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the X location of dataset item in pixel.
	 * 
	 * @return the X location of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getX() {
		return AssignHelper.check(getNativeX(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the Y location of dataset item in pixel.
	 * 
	 * @return the Y location of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getY() {
		return AssignHelper.check(getNativeY(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the width of dataset item in pixel.
	 * 
	 * @return the width of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getWidth() {
		return AssignHelper.check(getNativeWidth(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the height of dataset item in pixel.
	 * 
	 * @return the height of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getHeight() {
		return AssignHelper.check(getNativeHeight(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns <code>true</code> if skipped.
	 * 
	 * @return <code>true</code> if skipped. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public final boolean isSkipped() {
		return AssignHelper.check(isNativeSkipped(), UndefinedValues.BOOLEAN);
	}
	
	/**
	 * Returns the radius of dataset item in pixel.
	 * 
	 * @return the radius of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getRadius() {
		return AssignHelper.check(getNativeRadius(), UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @return the style of the legend box
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	@JsOverlay
	public final List<PointStyle> getPointStyle() {
		// creates result
		Object objectNative = getNativePointStyle();
		if (objectNative instanceof String) {
			List<PointStyle> result = new LinkedList<>();
			result.add(AssignHelper.deserialize(AssignHelper.check((String)objectNative, PointStyle.circle.name()), PointStyle.class, PointStyle.circle));
			return result;
		} else {
			return ArrayListHelper.build(PointStyle.class, (ArrayString) objectNative);
		}
	}
	
	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @return  the Bezier curve tension (0 for no Bezier curves). Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getTension() {
		return AssignHelper.check(getNativeTension(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the hit radius.
	 * 
	 * @return the hit radius. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getHitRadius() {
		return AssignHelper.check(getNativeHitRadius(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the previous X control point of dataset item in pixel.
	 * 
	 * @return the previous X control point of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getControlPointPreviousX() {
		return AssignHelper.check(getNativeControlPointPreviousX(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the previous Y control point of dataset item in pixel.
	 * 
	 * @return the previous Y control point of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getControlPointPreviousY() {
		return AssignHelper.check(getNativeControlPointPreviousY(), UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the next X control point of dataset item in pixel.
	 * 
	 * @return the next X control point of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getControlPointNextX() {
		return AssignHelper.check(getNativeControlPointNextX(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the next Y control point of dataset item in pixel.
	 * 
	 * @return the next Y control point of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getControlPointNextY() {
		return AssignHelper.check(getNativeControlPointNextY(), UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns <code>true</code> if stepped line has been selected.
	 * 
	 * @return <code>true</code> if stepped line has been selected. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public final boolean isSteppedLine() {
		return AssignHelper.check(isNativeSteppedLine(), UndefinedValues.BOOLEAN);
	}
	
	/**
	 * Returns the start angle of dataset item.
	 * 
	 * @return the start angle of dataset item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getStartAngle() {
		return AssignHelper.check(getNativeStartAngle(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the end angle of dataset item.
	 * 
	 * @return the end angle of dataset item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getEndAngle() {
		return AssignHelper.check(getNativeEndAngle(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the circumference of dataset item.
	 * 
	 * @return the circumference of dataset item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getCircumference() {
		return AssignHelper.check(getNativeCircumference(), UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the outer radius of dataset item in pixel.
	 * 
	 * @return the outer radius of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getOuterRadius() {
		return AssignHelper.check(getNativeOuterRadius(), UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the inner radius of dataset item in pixel.
	 * 
	 * @return the inner radius of dataset item in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getInnerRadius() {
		return AssignHelper.check(getNativeInnerRadius(), UndefinedValues.DOUBLE);
	}
}
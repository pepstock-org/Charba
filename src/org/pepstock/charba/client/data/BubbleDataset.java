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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * The location of the bubble is determined by the first two dimensions and the corresponding horizontal and vertical axes.<br>
 * The third dimension is represented by the size of the individual bubbles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class BubbleDataset extends HovingDataset {
	// exception message when it's not using data points
	private static final String DATA_USAGE_MESSAGE = "Use datapoints instead of data for bubble chart";
	// data point factory
	private final DataPointListFactory factory = new DataPointListFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		data,
		hoverRadius,
		hitRadius,
		pointStyle,
		radius,
		rotation
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		setValueOrArray(Property.pointStyle, pointStyle);
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return list of the style of the point.
	 */
	public List<PointStyle> getPointStyle() {
		ArrayString array = getValueOrArray(Property.pointStyle, Defaults.get().getGlobal().getElements().getPoint().getPointStyle());
		return ArrayListHelper.list(PointStyle.class, array);
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius array of the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double... hitRadius) {
		setValueOrArray(Property.hitRadius, hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getHitRadius() {
		ArrayDouble array = getValueOrArray(Property.hitRadius, Defaults.get().getGlobal().getElements().getPoint().getHitRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius array of the radius of the point when hovered.
	 */
	public void setHoverRadius(double... hoverRadius) {
		setValueOrArray(Property.hoverRadius, hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getHoverRadius() {
		ArrayDouble array = getValueOrArray(Property.hoverRadius, Defaults.get().getGlobal().getElements().getPoint().getHoverRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double... radius) {
		setValueOrArray(Property.radius, radius);
	}

	/**
	 * Returns the radius of the point shape.
	 * 
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getRadius() {
		ArrayDouble array = getValueOrArray(Property.radius, Defaults.get().getGlobal().getElements().getPoint().getRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the rotation of the point in degrees.
	 * 
	 * @param rotation array of the rotation of the point in degrees.
	 */
	public void setRotation(double... rotation) {
		setValueOrArray(Property.rotation, rotation);
	}

	/**
	 * Returns the rotation of the point in degrees.
	 * 
	 * @return list of the rotation of the point in degrees.
	 */
	public List<Double> getRotation() {
		ArrayDouble array = getValueOrArray(Property.rotation, Defaults.get().getGlobal().getElements().getPoint().getRotation());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	public void setDataPoints(DataPoint... datapoints) {
		setArrayValue(Property.data, ArrayObject.of(datapoints));
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	public void setDataPoints(List<DataPoint> datapoints) {
		setArrayValue(Property.data, ArrayObject.of(datapoints));
	}
	
	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @return a list of data points
	 */
	public List<DataPoint> getDataPoints() {
		ArrayObject array = getArrayValue(Property.data);
		return ArrayListHelper.list(array, factory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.data.Dataset#setData(double[])
	 */
	@Override
	public void setData(double... values) {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.data.Dataset#setData(java.util.List)
	 */
	@Override
	public void setData(List<Double> values) {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.data.Dataset#getData()
	 */
	@Override
	public List<Double> getData() {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}
}
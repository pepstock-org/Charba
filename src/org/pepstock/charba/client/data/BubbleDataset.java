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

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * The location of the bubble is determined by the first two dimensions and the corresponding horizontal and vertical axes.<br>
 * The third dimension is represented by the size of the individual bubbles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class BubbleDataset extends HovingDataset implements HasDataPoints {
	// exception message when it's not using data points
	private static final String DATA_USAGE_MESSAGE = "Use datapoints instead of data for bubble chart";
	// data point factory
	private final DataPointListFactory factory = new DataPointListFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		hoverRadius,
		hitRadius,
		pointStyle,
		radius,
		rotation
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public BubbleDataset() {
		super();
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public BubbleDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
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
		ArrayString array = getValueOrArray(Property.pointStyle, getDefaultValues().getElements().getPoint().getPointStyle());
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
		ArrayDouble array = getValueOrArray(Property.hitRadius, getDefaultValues().getElements().getPoint().getHitRadius());
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
		ArrayDouble array = getValueOrArray(Property.hoverRadius, getDefaultValues().getElements().getPoint().getHoverRadius());
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
		ArrayDouble array = getValueOrArray(Property.radius, getDefaultValues().getElements().getPoint().getRadius());
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
		ArrayDouble array = getValueOrArray(Property.rotation, getDefaultValues().getElements().getPoint().getRotation());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	@Override
	public void setDataPoints(DataPoint... datapoints) {
		setArrayValue(Dataset.Property.data, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(Dataset.Property._charbaDataType, has(Dataset.Property.data) ? DataType.points : DataType.unknown);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	@Override
	public void setDataPoints(List<DataPoint> datapoints) {
		setArrayValue(Dataset.Property.data, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(Dataset.Property._charbaDataType, has(Dataset.Property.data) ? DataType.points : DataType.unknown);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#points}.
	 */
	@Override
	public List<DataPoint> getDataPoints() {
		return getDataPoints(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#points}.
	 */
	@Override
	public List<DataPoint> getDataPoints(boolean binding) {
		return getDataPoints(factory, binding);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 */
	@Override
	public void setData(double... values) {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(java.util.List)
	 */
	@Override
	public void setData(List<Double> values) {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 */
	@Override
	public List<Double> getData() {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData(boolean)
	 */
	@Override
	public List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}

}
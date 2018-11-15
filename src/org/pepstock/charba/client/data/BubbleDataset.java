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

import org.pepstock.charba.client.commons.AbstractList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsDoubleArrayList;
import org.pepstock.charba.client.commons.JsEnumValueArrayList;
import org.pepstock.charba.client.commons.JsObjectContainerArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * T
 * @author Andrea "Stock" Stocchero
 *
 */
public class BubbleDataset extends HovingDataset{
	
	// set of boolean flags to know if the value has been set as single value or as array
	private boolean isHitRadiusArray = false;
	
	private boolean isHoverRadiusArray = false;
	
	private boolean isRadiusArray = false;
	
	private boolean isPointStyleArray = false;
	
	// list of data points
	private final AbstractList<DataPoint> dataPoints = new JsObjectContainerArrayList<DataPoint>();


	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		data,
		hoverRadius,
		hitRadius,
		pointStyle,
		radius
	}

	/**
	 * Sets the style of the point.
	 * @param pointStyle array of the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		setPointStyle(ArrayListHelper.build(PointStyle.class, pointStyle));
	}

	/**
	 * Sets the style of the point.
	 * @param pointStyle the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	private void setPointStyle(JsEnumValueArrayList<PointStyle> pointStyle) {
		isPointStyleArray = checkAndSetEnumValues(Property.pointStyle, pointStyle);
	}

	/**
	 * Returns the style of the point.
	 * @return list of the style of the point. Default is <code>PointStyle.circle</code>
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public JsEnumValueArrayList<PointStyle> getPointStyle() {
		// gets the list of values in string format
		JsStringArrayList sValues = checkAndGetStringValues(Property.pointStyle, isPointStyleArray);
		// if empty, returns the default
		if (sValues.isEmpty()){
			return ArrayListHelper.build(PointStyle.class, new PointStyle[]{PointStyle.circle});
		} else {
			return ArrayListHelper.build(PointStyle.class, sValues);
		}
	}
	
	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * @param hitRadius array of the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double... hitRadius) {
		setHitRadius(ArrayListHelper.build(hitRadius));
	}
	
	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	private void setHitRadius(JsDoubleArrayList hitRadius) {
	    isHitRadiusArray = checkAndSetDoubleValues(Property.hitRadius, hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getHitRadius() {
	    return checkAndGetDoubleValues(Property.hitRadius, isHitRadiusArray);
	}
	
	/**
	 * Sets the radius of the point when hovered.
	 * @param hoverRadius array of the radius of the point when hovered.
	 */
	public void setHoverRadius(double... hoverRadius) {
		setHoverRadius(ArrayListHelper.build(hoverRadius));
	}

	/**
	 * Sets the radius of the point when hovered.
	 * @param hoverRadius the radius of the point when hovered.
	 */
	private void setHoverRadius(JsDoubleArrayList hoverRadius) {
	    isHoverRadiusArray = checkAndSetDoubleValues(Property.hoverRadius, hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getHoverRadius() {
	    return checkAndGetDoubleValues(Property.hoverRadius, isHoverRadiusArray);
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double...  radius) {
		setRadius(ArrayListHelper.build(radius));
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * @param radius the radius of the point shape.
	 */
	private void setRadius(JsDoubleArrayList radius) {
	    isRadiusArray = checkAndSetDoubleValues(Property.radius, radius);
	}

	/**
	 * Returns the radius of the point shape.
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getRadius() {
	    return checkAndGetDoubleValues(Property.radius, isRadiusArray);
	}
	
	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * @param datapoints an array of data points
	 * @see org.pepstock.charba.client.data.DataPoint
	 */
	public void setDataPoints(DataPoint... datapoints){
		setValue(Property.data, ArrayListHelper.load(this.dataPoints, datapoints));
	}
	
	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * @return a list of data points
	 * @see org.pepstock.charba.client.data.DataPoint
	 */
	public List<DataPoint> getDataPoints(){
		return dataPoints;
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 */
	@Override
	public void setData(double... values) {
		throw new UnsupportedOperationException("Use datapoints instead of data for scatter chart");
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.Dataset#setData(java.util.List)
	 */
	@Override
	public void setData(List<Double> values) {
		throw new UnsupportedOperationException("Use datapoints instead of data for scatter chart");
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 */
	@Override
	public List<Double> getData() {
		throw new UnsupportedOperationException("Use datapoints instead of data for scatter chart");
	}
}
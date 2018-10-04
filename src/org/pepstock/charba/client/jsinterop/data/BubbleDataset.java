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
package org.pepstock.charba.client.jsinterop.data;

import java.util.List;

import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.FlexibleProperty;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * T
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BubbleDataset extends HovingDataset{
	
	private final DataPointListFactory factory = new DataPointListFactory();

	/**
	 * Sets the style of the point.
	 * @param pointStyle array of the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		getNativeObject().setPointStyle(FlexibleProperty.fromKeys(pointStyle));
	}

	/**
	 * Returns the style of the point.
	 * @return list of the style of the point. Default is <code>PointStyle.circle</code>
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public List<PointStyle> getPointStyle() {
		List<PointStyle> result = FlexibleProperty.toKeys(getNativeObject().getPointStyle(), PointStyle.class);
		if (result.isEmpty()) {
			//FIXME e corretto?
			result.add(PointStyle.circle);
		}
		return result;
	}
	
	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * @param hitRadius array of the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double... hitRadius) {
		getNativeObject().setHitRadius(FlexibleProperty.fromDoubles(hitRadius));
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getHitRadius() {
	    return FlexibleProperty.toDoubles(getNativeObject().getHitRadius());
	}

	/**
	 * Sets the radius of the point when hovered.
	 * @param hoverRadius array of the radius of the point when hovered.
	 */
	public void setHoverRadius(double... hoverRadius) {
		getNativeObject().setHoverRadius(FlexibleProperty.fromDoubles(hoverRadius));
	}

	/**
	 * Returns the radius of the point when hovered.
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getHoverRadius() {
	    return FlexibleProperty.toDoubles(getNativeObject().getHoverRadius());
	}
	
	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double...  radius) {
		getNativeObject().setRadius(FlexibleProperty.fromDoubles(radius));
	}

	/**
	 * Returns the radius of the point shape.
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getRadius(){
		return FlexibleProperty.toDoubles(getNativeObject().getRadius());
	}
	
	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * @param datapoints an array of data points
	 * @see org.pepstock.charba.client.data.DataPoint
	 */
	public void setDataPoints(DataPoint... datapoints){
		getNativeObject().setData(ArrayObject.of(datapoints));
	}
	
	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * @return a list of data points
	 * @see org.pepstock.charba.client.data.DataPoint
	 */
	@SuppressWarnings("unchecked")
	public List<DataPoint> getDataPoints(){
		return ArrayListHelper.list((ArrayObject<NativeDataPoint>)getNativeObject().getData(), factory);
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
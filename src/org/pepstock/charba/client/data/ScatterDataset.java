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

import org.pepstock.charba.client.enums.Fill;
/**
 * The scatter chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Extends the line dataset to set fixed properties for scatter chart.<br>
 * The scatter dataset can use ONLY datapoints. If you try to use array of data numbers, an exception will be thrown.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.LineDataset
 * @see org.pepstock.charba.client.data.DataPoint
 */
public class ScatterDataset extends LineDataset {

	/**
	 * Builds the object setting fixed properties
	 */
	public ScatterDataset() {
		// scatter is always no fill
		super.setFill(Fill.nofill);
		// scatter has never show lines
		super.setShowLines(false);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.LineDataset#setShowLine(boolean)
	 */
	@Override
	public void setShowLines(boolean showLine) {
		// force always to false
		super.setShowLines(false);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.LiningDataset#setFill(org.pepstock.charba.client.enums.Fill)
	 */
	@Override
	public void setFill(Fill fill) {
		// sets always to no fill
		super.setFill(Fill.nofill);
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
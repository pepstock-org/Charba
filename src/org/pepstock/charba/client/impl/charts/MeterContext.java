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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * The callback context wrapper, created and passed by {@link MeterOptions} which contains the link to the native chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class MeterContext extends ChartContext {

	private double value = 0D;

	private double easing = 0D;

	private String datasetLabel = null;

	/**
	 * Creates a context getting the native object from {@link BaseContext} which is storing chart and type properties in order to be able to extend the {@link ChartContext}.
	 * 
	 * @param baseContext {@link BaseContext} instance which is storing chart and type properties in order to be able to extend the {@link ChartContext}
	 */
	MeterContext(BaseContext baseContext) {
		super(baseContext.nativeObject());
	}

	/**
	 * Returns the value of meter or gauge dataset.
	 * 
	 * @return the value of meter or gauge dataset
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the value of meter or gauge dataset.
	 * 
	 * @param value the value of meter or gauge dataset
	 */
	void setValue(double value) {
		this.value = value;
	}

	/**
	 * Returns the easing of drawing (between 0 and 1) for animation.
	 * 
	 * @return the easing of drawing (between 0 and 1) for animation
	 */
	public double getEasing() {
		return easing;
	}

	/**
	 * Sets the the easing of drawing (between 0 and 1) for animation.
	 * 
	 * @param easing the easing of drawing (between 0 and 1) for animation
	 */
	void setEasing(double easing) {
		this.easing = easing;
	}

	/**
	 * Returns the data set label.
	 * 
	 * @return the data set label
	 */
	public String getDatasetLabel() {
		return datasetLabel;
	}

	/**
	 * Sets the data set label.
	 * 
	 * @param datasetLabel the data set label
	 */
	void setDatasetLabel(String datasetLabel) {
		this.datasetLabel = datasetLabel;
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}

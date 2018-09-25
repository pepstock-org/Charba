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
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.JsObjectContainerArrayList;
import org.pepstock.charba.client.commons.Key;

/**
 * CHART.JS entity object to configure the data options of a chart.<br>
 * It contains labels and datasets.
 *  
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer
 */
public final class Data extends JavaScriptObjectContainer{
	
	// maintains the list of datasets
	private final AbstractList<Dataset> datasets = new JsObjectContainerArrayList<Dataset>();
	
	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		labels,
		datasets,
		xLabels,
		yLabels
	}
	
	/**
	 * Sets the labels of the data 
	 * @param labels array of labels
	 */
	public void setLabels(String... labels){
		// creates a label object
		Labels l = Labels.build();
		// loads
		l.load(labels);
		// sets labels
		setLabels(l);
	}

	/**
	 * Sets the labels of the data
	 * @param labels labels object to manage also multiline labels.
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public void setLabels(Labels labels){
		  setValue(Property.labels, labels);
	}

	/**
	 * Returns the labels 
	 * @return the labels
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public Labels getLabels(){
		return getValue(Property.labels);
	}

	/**
	 * Sets the labels for X axes of the data 
	 * @param labels array of labels
	 */
	public void setXLabels(String... labels){
		// creates a label object
		Labels l = Labels.build();
		// loads
		l.load(labels);
		// sets X labels
		setXLabels(l);
	}

	/**
	 * Sets the labels for X axes of the data 
	 * @param labels labels object to manage also multiline labels.
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public void setXLabels(Labels labels){
		  setValue(Property.xLabels, labels);
	}

	/**
	 * Returns the labels for X axes
	 * @return the labels for X axes
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public Labels getXLabels(){
		return getValue(Property.xLabels);
	}

	/**
	 * Sets the labels for Y axes of the data
	 * @param labels array of labels
	 */
	public void setYLabels(String... labels){
		// creates a label object
		Labels l = Labels.build();
		// loads
		l.load(labels);
		// sets Y labels
		setYLabels(l);
	}

	/**
	 * Sets the labels for Y axes of the data
	 * @param labels labels object to manage also multiline labels.
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public void setYLabels(Labels labels){
		  setValue(Property.yLabels, labels);
	}

	/**
	 * Returns the labels for Y axes
	 * @return the labels for Y axes
	 * @see org.pepstock.charba.client.data.Labels
	 */
	public Labels getYLabels(){
		return getValue(Property.yLabels);
	}

	/**
	 * Sets a set of datasets for chart
	 * @param datasets set of dataset
	 * @see org.pepstock.charba.client.data.Dataset
	 */
	public void setDatasets(Dataset... datasets){
		setValue(Property.datasets, ArrayListHelper.load(this.datasets, datasets));
	}

	/**
	 * Returns the list of datasets
	 * @return the list of datasets
	 * @see org.pepstock.charba.client.data.Dataset
	 */
	public List<Dataset> getDatasets(){
		return this.datasets;
	}
	
	public Object getObject() {
		return super.getJavaScriptObject();
	}
}
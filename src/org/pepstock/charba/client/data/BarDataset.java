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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Position;

/**
 * The bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Some properties can be specified as an array. If these are set to an array value, the first value applies to the first bar, the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.Dataset
 */
public class BarDataset extends HovingFlexDataset{

	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		xAxisID,
		yAxisID,
		borderSkipped
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public void setXAxisID(String xAxisID){
		  setValue(Property.xAxisID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public String getXAxisID(){
		  return getValue(Property.xAxisID, null);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public void setYAxisID(String yAxisID){
		  setValue(Property.yAxisID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * @return the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public String getYAxisID(){
		  return getValue(Property.yAxisID, null);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * @param position the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(Position position){
		 setValue(Property.borderSkipped, position);
	}
	
	/**
	 * Returns the edge to skip drawing the border for.
	 * @return the edge to skip drawing the border for.
	 */
	public Position getBorderSkipped(){
		return getValue(Property.borderSkipped, Position.class, Position.top);
	}

}
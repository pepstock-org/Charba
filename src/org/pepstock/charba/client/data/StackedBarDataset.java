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

/**
 * The stacked bar area chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Extends the bar dataset setting <code>stack</code> property.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.BarDataset
 */
public class StackedBarDataset extends BarDataset{

	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		stack
	}
	
	/**
	 * Sets the name of stack group.
	 * @param stackGroup name of stack group.
	 */
	public void setStackGroup(String stackGroup){
		  setValue(Property.stack, stackGroup);
	}

	/**
	 * Returns the name of stack group. 
	 * @return the name of stack group.
	 */
	public String getStackGroup(){
		  return getValue(Property.stack, (String)null);
	}

}
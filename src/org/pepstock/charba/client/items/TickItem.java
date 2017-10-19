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
package org.pepstock.charba.client.items;

import java.util.List;

import org.pepstock.charba.client.commons.Key;

public class TickItem extends BaseItem {

	private enum Property implements Key{
		value,
		index,
		values
	}
    /** 
     * Needed for GWt injection
     */
	protected TickItem() {
		
	}

	public final double getValue() {
		return getDouble(Property.value.name());
	}

	public final int getIndex() {
		return getInt(Property.index.name());
	}

	public final List<Double> getValues() {
		return getDoubleArray(Property.values.name());
	}

	public final String toContentString() {
		return "TickItem [getValue()=" + getValue() + ", getIndex()=" + getIndex() + ", getValues()=" + getValues() + "]";
	} 

}
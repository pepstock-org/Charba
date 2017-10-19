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

import org.pepstock.charba.client.commons.Key;

public class AxisMinSizeItem extends BaseItem {

	private enum Property implements Key{
		width,
		height
	}
    /** 
     * Needed for GWt injection
     */
	protected AxisMinSizeItem() {
		
	}

	public final int getWidth() {
		return getInt(Property.width.name());
	}

	public final void setWidth(int width) {
		setInt(Property.width.name(), width);
	}

	public final int getHeight() {
		return getInt(Property.height.name());
	}

	public final void setHeight(int height) {
		setInt(Property.height.name(), height);
	}

	public final String toContentString() {
		return "AxisMinSizeItem [getWidth()=" + getWidth() + ", getHeight()=" + getHeight() + "]";
	}

}
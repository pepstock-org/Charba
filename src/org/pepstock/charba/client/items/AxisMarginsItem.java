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

public class AxisMarginsItem extends BaseItem {

	private enum Property implements Key{
		left,
		right, 
		top, 
		bottom
	}
	
	/**
	 * Needed for GWt injection
	 */
	protected AxisMarginsItem() {

	}

	public final int getTop() {
		return getInt(Property.top.name());
	}

	public final void setTop(int top) {
		setInt(Property.top.name(), top);
	}

	public final int getBottom() {
		return getInt(Property.bottom.name());
	}

	public final void setBottom(int bottom) {
		setInt(Property.bottom.name(), bottom);
	}

	public final int getLeft() {
		return getInt(Property.left.name());
	}

	public final void setLeft(int left) {
		setInt(Property.left.name(), left);
	}

	public final int getRight() {
		return getInt(Property.right.name());
	}

	public final void setRight(int right) {
		setInt(Property.right.name(), right);
	}

	public final String toContentString() {
		return "AxisMarginsItem [getTop()=" + getTop() + ", getBottom()=" + getBottom() + ", getLeft()=" + getLeft() + ", getRight()=" + getRight() + "]";
	}

}
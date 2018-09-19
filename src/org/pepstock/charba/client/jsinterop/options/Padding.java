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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;

/**
 * It is applied to all sides of the chart (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Padding extends BaseModel<Layout, IsDefaultPadding, NativePadding>{

	Padding(Layout layout, IsDefaultPadding defaultValues, NativePadding delegated) {
		super(layout, defaultValues, delegated == null ? new NativePadding(): delegated);
	}
	
	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	public void setLeft(int padding) {
		getDelegated().setLeft(padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is 0.
	 */
	public int getLeft() {
		return AssignHelper.check(getDelegated().getLeft(), getDefaultValues().getLeft());
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel. Default is 0.
	 */
	public void setRight(int padding) {
		getDelegated().setRight(padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel. Default is 0.
	 */
	public int getRight() {
		return AssignHelper.check(getDelegated().getRight(), getDefaultValues().getRight());
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel. Default is 0.
	 */
	public void setTop(int padding) {
		getDelegated().setTop(padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
 	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is 0.
	 */
	public int getTop() {
		return AssignHelper.check(getDelegated().getTop(), getDefaultValues().getTop());
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel. Default is 0.
	 */
	public void setBottom(int padding) {
		getDelegated().setBottom(padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. Default is 0.
	 */
	public int getBottom() {
		return AssignHelper.check(getDelegated().getBottom(), getDefaultValues().getBottom());
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getPadding() == null) {
			getParent().getDelegated().setPadding(getDelegated());
		}
	}
}
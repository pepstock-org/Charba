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
package org.pepstock.charba.client.jsinterop.options.layout.padding;

import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.IsDelegated;

/**
 * It is applied to all sides of the chart (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Padding implements IsDelegated<NativePadding>{

	private final NativePadding delegated;
	
	private final IsDefaultPadding defaultValues;
	
	public Padding(IsDefaultPadding defaultValues) {
		this(new NativePadding(), defaultValues);
	}

	public Padding(NativePadding delegated, IsDefaultPadding defaultValues) {
		this.delegated = delegated;
		this.defaultValues = defaultValues;
	}
	
	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	public void setLeft(int padding) {
		delegated.setLeft(padding);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is 0.
	 */
	public int getLeft() {
		return AssignHelper.check(delegated.getLeft(), defaultValues.getLeft());
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel. Default is 0.
	 */
	public void setRight(int padding) {
		delegated.setRight(padding);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel. Default is 0.
	 */
	public int getRight() {
		return AssignHelper.check(delegated.getRight(), defaultValues.getRight());
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel. Default is 0.
	 */
	public void setTop(int padding) {
		delegated.setTop(padding);
 	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is 0.
	 */
	public int getTop() {
		return AssignHelper.check(delegated.getTop(), defaultValues.getTop());
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel. Default is 0.
	 */
	public void setBottom(int padding) {
		delegated.setBottom(padding);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. Default is 0.
	 */
	public int getBottom() {
		return AssignHelper.check(delegated.getBottom(), defaultValues.getBottom());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.IsDelegated#getDelegated()
	 */
	@Override
	public NativePadding getDelegated() {
		return delegated;
	}
}
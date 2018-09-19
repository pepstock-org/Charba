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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines;

/**
 * It is used to configure angled lines that radiate from the center of the chart to the point labels.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AngleLines extends BaseModel<Scale, IsDefaultAngleLines, NativeAngleLines> {

	AngleLines(Scale scale, IsDefaultAngleLines defaultValues, NativeAngleLines delegated) {
		super(scale, defaultValues, delegated == null ? new NativeAngleLines(): delegated);
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @param display if true, angle lines are shown
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @return if true, angle lines are shown. Default is true.
	 */
	public boolean isDisplay() {
		return AssignHelper.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}

	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(IsColor color) {
		setColor(color.toRGBA());
	}
	
	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(String color) {
		getDelegated().setColor(color);
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultColor()}.
	 */
	public String getColorAsString() {
		return AssignHelper.check(getDelegated().getColor(), getDefaultValues().getColor());
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultColor()}.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}
	
	/**
	 * Sets the width of angled lines.
	 * 
	 * @param lineWidth width of angled lines.
	 */
	public void setLineWidth(int lineWidth) {
		getDelegated().setLineWidth(lineWidth);
	}

	/**
	 * Returns the width of angled lines.
	 * 
	 * @return width of angled lines. Default is 1.
	 */
	public int getLineWidth() {
		return AssignHelper.check(getDelegated().getLineWidth(), getDefaultValues().getLineWidth());
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getAngleLines() == null) {
			getParent().getDelegated().setAngleLines(getDelegated());
		}
	}

}
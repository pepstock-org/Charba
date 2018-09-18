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
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the axis.<br>
 * The scale label configuration defines options for the scale title. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLabel extends FontItem<NativeScaleLabel, Scale, IsDefaultScaleLabel> {
	
	private final ScaleLabelPadding padding;

	public ScaleLabel(Scale scale, IsDefaultScaleLabel defaultValues) {
		this(new NativeScaleLabel(), scale, defaultValues);
	}

	ScaleLabel(NativeScaleLabel delegated, Scale scale, IsDefaultScaleLabel defaultValues) {
		super(delegated, scale, defaultValues);
		NativePadding paddingObject = getDelegated().getPadding();
		if (paddingObject != null) {
			padding = new ScaleLabelPadding(paddingObject, this, getDefaultValues().getPadding());
		} else {
			padding = new ScaleLabelPadding(this, getDefaultValues().getPadding());
		}
	}
	
	/**
	 * @return the padding
	 */
	public ScaleLabelPadding getPadding() {
		return padding;
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @param display if true, display the axis title.
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @return f true, display the axis title. Default is false
	 */
	public boolean isDisplay() {
		return AssignHelper.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}

	/**
	 * Sets the text for the scale string.
	 * 
	 * @param labelString The text for the scale string.
	 */
	public void setLabelString(String labelString) {
		getDelegated().setLabelString(labelString);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the text for the scale string.
	 * 
	 * @return The text for the scale string. Default is "".
	 */
	public String getLabelString() {
		return AssignHelper.check(getDelegated().getLabelString(), getDefaultValues().getLabelString());
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight Height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		getDelegated().setLineHeight(lineHeight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text. Default is 1.2
	 */
	public double getLineHeight() {
		return AssignHelper.check(getDelegated().getLineHeight(), getDefaultValues().getLineHeight());
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getScaleLabel() == null) {
			getParent().getDelegated().setScaleLabel(getDelegated());
		}
	}
}
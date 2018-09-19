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

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle;

/**
 * Configures the default chart title which defines text to draw at the top of the chart.<br>
 * "weight"property is not present.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Title extends FontItem<Options, IsDefaultTitle, NativeTitle> {

	Title(Options options, IsDefaultTitle defaultValues, NativeTitle delegated) {
		super(options, defaultValues, delegated == null ? new NativeTitle() : delegated);
	}
	
	/**
	 * Sets if the title is shown.
	 * 
	 * @param display if the title is shown.
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown. Default is true.
	 */
	public boolean isDisplay() {
		return AssignHelper.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		getDelegated().setPosition(position.name());
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return AssignHelper.deserialize(AssignHelper.check(getDelegated().getPosition(), getDefaultValues().getPosition()), Position.class, Position.top);
	}
	
	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		getDelegated().setPadding(padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented. Default is 10.
	 */
	public int getPadding() {
		return AssignHelper.check(getDelegated().getPadding(), getDefaultValues().getPadding());
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		getDelegated().setFullWidth(fullWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). Default is true.
	 */
	public boolean isFullWidth() {
		return AssignHelper.check(getDelegated().isFullWidth(), getDefaultValues().isFullWidth());
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight Height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		getDelegated().setLineHeight(lineHeight);
		// checks if the node is already added to parent
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
		if (getParent().getDelegated().getTitle() == null) {
			getParent().getDelegated().setTitle(getDelegated());
		}
	}

}
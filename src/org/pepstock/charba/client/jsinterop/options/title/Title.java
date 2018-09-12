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
package org.pepstock.charba.client.jsinterop.options.title;

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.IsDelegated;
import org.pepstock.charba.client.jsinterop.options.FontItem;

/**
 * Configures the default chart title which defines text to draw at the top of the chart.<br>
 * "weight"property is not present.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Title extends FontItem implements IsDelegated<NativeTitle> {

	private final NativeTitle delegated;
	
	private final IsDefaultTitle defaultValues;
	
	public Title(IsDefaultTitle defaultValues) {
		this(new NativeTitle(), defaultValues);
	}

	public Title(NativeTitle delegated, IsDefaultTitle defaultValues) {
		super(delegated, defaultValues);
		this.delegated = delegated;
		this.defaultValues = defaultValues;
	}
	
	/**
	 * Sets if the title is shown.
	 * 
	 * @param display if the title is shown.
	 */
	public void setDisplay(boolean display) {
		delegated.setDisplay(display);
	}

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown. Default is true.
	 */
	public boolean isDisplay() {
		return AssignHelper.check(delegated.isDisplay(), defaultValues.isDisplay());
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		delegated.setPosition(position.name());
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return AssignHelper.deserialize(AssignHelper.check(delegated.getPosition(), defaultValues.getPosition()), Position.class, Position.top);
	}
	
	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		delegated.setPadding(padding);
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented. Default is 10.
	 */
	public int getPadding() {
		return AssignHelper.check(delegated.getPadding(), defaultValues.getPadding());
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		delegated.setFullWidth(fullWidth);
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). Default is true.
	 */
	public boolean isFullWidth() {
		return AssignHelper.check(delegated.isFullWidth(), defaultValues.isFullWidth());
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight Height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		delegated.setLineHeight(lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text. Default is 1.2
	 */
	public double getLineHeight() {
		return AssignHelper.check(delegated.getLineHeight(), defaultValues.getLineHeight());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.IsDelegated#getDelegated()
	 */
	@Override
	public NativeTitle getDelegated() {
		return delegated;
	}

}
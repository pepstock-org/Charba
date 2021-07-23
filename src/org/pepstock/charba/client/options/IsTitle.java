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
package org.pepstock.charba.client.options;

import java.util.List;

import org.pepstock.charba.client.defaults.IsDefaultAbstractTitle;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;

/**
 * Interface to map a title and subtitle elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsTitle extends IsDefaultAbstractTitle {

	/**
	 * Sets <code>true</code> if the element is shown.
	 * 
	 * @param display if <code>true</code> the element is shown.
	 */
	void setDisplay(boolean display);

	/**
	 * Sets the position of element.
	 * 
	 * @param position the position of element.
	 */
	void setPosition(Position position);

	/**
	 * Sets the alignment of the element.
	 * 
	 * @param alignment alignment of the element.
	 */
	void setAlign(ElementAlign alignment);

	/**
	 * Marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @param fullSize Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	void setFullSize(boolean fullSize);

	/**
	 * Sets the title text to display.<br>
	 * If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display.<br>
	 *            If specified as an array, text is rendered on multiple lines.
	 */
	void setText(String... text);

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings or an empty list if not exist
	 */
	List<String> getText();

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	void setColor(String color);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFontContainer#getFont()
	 */
	@Override
	Font getFont();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAbstractTitle#getPadding()
	 */
	@Override
	Padding getPadding();
}

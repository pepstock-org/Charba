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
package org.pepstock.charba.client.jsinterop.data;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.commons.FlexibleProperty;

/**
 * This dataset is managing some common properties of Bar and Bubble datasets where every property can be set as a single value or an array.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.Dataset
 */
abstract class HovingFlexDataset extends Dataset{
	
	/**
	 * Sets the fill colors of the elements.
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(IsColor... backgroundColor) {
		getNativeObject().setBackgroundColor(FlexibleProperty.fromColors(backgroundColor));	
	}

	/**
	 * Sets the fill colors of the elements.
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		getNativeObject().setBackgroundColor(FlexibleProperty.fromStrings(backgroundColor));	
	}

	/**
	 * Returns the fill colors of the elements
	 * @return list of the fill colors of the elements
	 */
	public List<String> getBackgroundColorAsString() {
		return FlexibleProperty.toStrings(getNativeObject().getBackgroundColor());
	}

	/**
	 * Returns the fill colors of the elements
	 * @return list of the fill colors of the elements
	 */
	public List<IsColor> getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the color of the bar border
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(IsColor... borderColor) {
		getNativeObject().setBorderColor(FlexibleProperty.fromColors(borderColor));
	}

	/**
	 * Sets the color of the bar border
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(String... borderColor) {
		getNativeObject().setBorderColor(FlexibleProperty.fromStrings(borderColor));
	}
	
	/**
	 * Returns the color of the bar border
	 * @return list of the color of the bar border
	 */
	public List<String> getBorderColorAsString() {
		return FlexibleProperty.toStrings(getNativeObject().getBorderColor());
	}

	/**
	 * Returns the color of the bar border
	 * @return list of the color of the bar border
	 */
	public List<IsColor> getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(int... borderWidth) {
		getNativeObject().setBorderWidth(FlexibleProperty.fromIntegers(borderWidth));
	}

	/**
	 * Returns the stroke width of the bar in pixels.
	 * @return list of the stroke width of the bar in pixels.
	 */
	public List<Integer> getBorderWidth() {
		return FlexibleProperty.toIntegers(getNativeObject().getBorderWidth());
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors){
		getNativeObject().setHoverBackgroundColor(FlexibleProperty.fromColors(colors));
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(String... colors){
		getNativeObject().setHoverBackgroundColor(FlexibleProperty.fromStrings(colors));
	}
	
	/**
	 * Returns the fill color of the elements when hovered
	 * @return list of the fill color of the elements when hovered
	 */
	public List<String> getHoverBackgroundColorAsString(){
		return FlexibleProperty.toStrings(getNativeObject().getHoverBackgroundColor());
	}

	/**
	 * Returns the fill color of the elements when hovered
	 * @return list of the fill color of the elements when hovered
	 */
	public List<IsColor> getHoverBackgroundColor(){
		return ColorBuilder.parse(getHoverBackgroundColorAsString());
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(IsColor... colors){
		getNativeObject().setHoverBorderColor(FlexibleProperty.fromColors(colors));
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(String... colors){
		getNativeObject().setHoverBorderColor(FlexibleProperty.fromStrings(colors));
	}
	
	/**
	 * Returns the stroke color of the elements when hovered
	 * @return list of the stroke color of the elements when hovered
	 */
	public List<String> getHoverBorderColorAsString(){
		return FlexibleProperty.toStrings(getNativeObject().getHoverBorderColor());
	}

	/**
	 * Returns the stroke color of the elements when hovered
	 * @return list of the stroke color of the elements when hovered
	 */
	public List<IsColor> getHoverBorderColor(){
		return ColorBuilder.parse(getHoverBorderColorAsString());
	}

	/**
	 * Sets the stroke width of the elements when hovered.
	 * @param widths the stroke width of the elements when hovered.
	 */
	public void setHoverBorderWidth(int... widths){
		getNativeObject().setHoverBorderWidth(FlexibleProperty.fromIntegers(widths));
	}

	/**
	 * Returns the stroke width of the elements when hovered.
	 * @return list of the stroke width of the elements when hovered.
	 */
	public List<Integer> getHoverBorderWidth(){
		return FlexibleProperty.toIntegers(getNativeObject().getHoverBorderWidth());
	}

}
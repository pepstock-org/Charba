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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;

/**
 * This dataset is managing some common properties of Bar and Bubble datasets where every property can be set as a single value or an array.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.Dataset
 */
abstract class HovingFlexDataset extends Dataset{

	// set of boolean flags to know if the value has been set as single value or as array
	private boolean isBackgroundColorArray = false;
	
	private boolean isBorderColorArray = false;
	
	private boolean isBorderWidthArray = false;
	
	private boolean isHoverBackgroundColorArray = false;
	
	private boolean isHoverBorderColorArray = false;
	
	private boolean isHoverBorderWidthArray = false;
	
	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		backgroundColor,
		borderColor,
		borderWidth,
		hoverBackgroundColor,
		hoverBorderColor,
		hoverBorderWidth
	}

	/**
	 * Sets the fill colors of the elements.
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(IsColor... backgroundColor) {
		setBackgroundColor(ArrayListHelper.build(backgroundColor));
	}

	/**
	 * Sets the fill colors of the elements.
	 * @param backgroundColor the fill colors of the elements.
	 */
	public void setBackgroundColor(String... backgroundColor) {
		setBackgroundColor(ArrayListHelper.build(backgroundColor));
	}

	/**
	 * Sets the fill colors of the elements.
	 * @param backgroundColor the fill colors of the elements.
	 */
	private void setBackgroundColor(JsStringArrayList backgroundColor) {
		isBackgroundColorArray = checkAndSetStringValues(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the fill colors of the elements
	 * @return list of the fill colors of the elements
	 */
	public List<String> getBackgroundColorAsString() {
		return checkAndGetStringValues(Property.backgroundColor, isBackgroundColorArray);
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
		setBorderColor(ArrayListHelper.build(borderColor));
	}

	/**
	 * Sets the color of the bar border
	 * @param borderColor the color of the bar border
	 */
	public void setBorderColor(String... borderColor) {
		setBorderColor(ArrayListHelper.build(borderColor));
	}
	
	/**
	 * Sets the color of the bar border
	 * @param borderColor the color of the bar border
	 */
	private void setBorderColor(JsStringArrayList borderColor) {
		isBorderColorArray = checkAndSetStringValues(Property.borderColor, borderColor);
	}

	/**
	 * Returns the color of the bar border
	 * @return list of the color of the bar border
	 */
	public List<String> getBorderColorAsString() {
		return checkAndGetStringValues(Property.borderColor, isBorderColorArray);
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
		setBorderWidth(ArrayListHelper.build(borderWidth));
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	private void setBorderWidth(JsIntegerArrayList borderWidth) {
		isBorderWidthArray = checkAndSetIntegerValues(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the stroke width of the bar in pixels.
	 * @return list of the stroke width of the bar in pixels.
	 */
	public List<Integer> getBorderWidth() {
		return checkAndGetIntegerValues(Property.borderWidth, isBorderWidthArray);
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(IsColor... colors){
		setHoverBackgroundColor(ArrayListHelper.build(colors));
	}

	/**
	 * Sets the fill color of the elements when hovered
	 * @param colors the fill color of the elements when hovered
	 */
	public void setHoverBackgroundColor(String... colors){
		setHoverBackgroundColor(ArrayListHelper.build(colors));
	}
	
	/**
	 * Sets the fill color of the elements when hovered
	 * @param colors the fill color of the elements when hovered
	 */
	private void setHoverBackgroundColor(JsStringArrayList colors){
		isHoverBackgroundColorArray = checkAndSetStringValues(Property.hoverBackgroundColor, colors);
	}
	
	/**
	 * Returns the fill color of the elements when hovered
	 * @return list of the fill color of the elements when hovered
	 */
	public List<String> getHoverBackgroundColorAsString(){
		return checkAndGetStringValues(Property.hoverBackgroundColor, isHoverBackgroundColorArray);
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
		setHoverBorderColor(ArrayListHelper.build(colors));
	}

	/**
	 * Sets the stroke color of the elements when hovered
	 * @param colors the stroke color of the elements when hovered
	 */
	public void setHoverBorderColor(String... colors){
		setHoverBorderColor(ArrayListHelper.build(colors));
	}
	
	/**
	 * Sets the stroke color of the elements when hovered
	 * @param colors the stroke color of the elements when hovered
	 */
	private void setHoverBorderColor(JsStringArrayList colors){
		isHoverBorderColorArray = checkAndSetStringValues(Property.hoverBorderColor, colors);
	}
	
	/**
	 * Returns the stroke color of the elements when hovered
	 * @return list of the stroke color of the elements when hovered
	 */
	public List<String> getHoverBorderColorAsString(){
		return checkAndGetStringValues(Property.hoverBorderColor, isHoverBorderColorArray);
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
		setHoverBorderWidth(ArrayListHelper.build(widths));
	}

	/**
	 * Sets the stroke width of the elements when hovered.
	 * @param widths the stroke width of the elements when hovered.
	 */
	private void setHoverBorderWidth(JsIntegerArrayList widths){
		isHoverBorderWidthArray = checkAndSetIntegerValues(Property.hoverBorderWidth, widths);
	}
	
	/**
	 * Returns the stroke width of the elements when hovered.
	 * @return list of the stroke width of the elements when hovered.
	 */
	public List<Integer> getHoverBorderWidth(){
		return checkAndGetIntegerValues(Property.hoverBorderWidth, isHoverBorderWidthArray);
	}

}
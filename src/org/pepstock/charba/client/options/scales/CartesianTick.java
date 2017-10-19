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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.commons.Key;

/**
 * 
 */
abstract class CartesianTick extends Tick{
	
	private static final boolean DEFAULT_AUTO_SKIP = true;
	
	private static final int DEFAULT_AUTO_SKIP_PADDING = 0;
	
	private static final int DEFAULT_LABEL_OFFSET = 0;
	
	private static final int DEFAULT_MAX_ROTATION = 90;
	
	private static final int DEFAULT_MIN_ROTATION = 0;
	
	private static final boolean DEFAULT_MIRROR = false;
	
	private static final int DEFAULT_PADDING = 10;
	
	private enum Property implements Key {
		autoSkip,
		autoSkipPadding, 
		labelOffset,
		maxRotation,
		minRotation,
		mirror, 
		padding
	}
	
//	autoSkip Boolean true If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all labels no matter what
//	autoSkipPadding Number 0 Padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal scales.
//	labelOffset Number 0 Distance in pixels to offset the label from the centre point of the tick (in the y direction for the x axis, and the x direction for the y axis). Note: this can cause labels at the edges to be cropped by the edge of the canvas
//	maxRotation Number 90 Maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary. Note: Only applicable to horizontal scales.
//	minRotation Number 0 Minimum rotation for tick labels. Note: Only applicable to horizontal scales.
//	mirror Boolean false Flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable to vertical scales.
//	padding Number 10 Padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
//	

	CartesianTick() {
	}
	
	public void setAutoSkip(boolean autoSkip){
		  setValue(Property.autoSkip, autoSkip);
	}

	public boolean isAutoSkip(){
		  return getValue(Property.autoSkip, DEFAULT_AUTO_SKIP);
	}

	public void setAutoSkipPadding(int autoSkipPadding){
		  setValue(Property.autoSkipPadding, autoSkipPadding);
	}

	public int getAutoSkipPadding(){
		  return getValue(Property.autoSkipPadding, DEFAULT_AUTO_SKIP_PADDING);
	}

	public void setLabelOffset(int labelOffset){
		  setValue(Property.labelOffset, labelOffset);
	}

	public int getLabelOffset(){
		  return getValue(Property.labelOffset, DEFAULT_LABEL_OFFSET);
	}

	public void setMaxRotation(int maxRotation){
		  setValue(Property.maxRotation, maxRotation);
	}

	public int getMaxRotation(){
		  return getValue(Property.maxRotation, DEFAULT_MAX_ROTATION);
	}

	public void setMinRotation(int minRotation){
		  setValue(Property.minRotation, minRotation);
	}

	public int getMinRotation(){
		  return getValue(Property.minRotation, DEFAULT_MIN_ROTATION);
	}

	public void setMirror(boolean mirror){
		  setValue(Property.mirror, mirror);
	}

	public boolean isMirror(){
		  return getValue(Property.mirror, DEFAULT_MIRROR);
	}

	public void setPadding(int padding){
		  setValue(Property.padding, padding);
	}

	public int getPadding(){
		  return getValue(Property.padding, DEFAULT_PADDING);
	}
	
}
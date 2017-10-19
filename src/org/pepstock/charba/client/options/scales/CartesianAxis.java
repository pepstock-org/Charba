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
import org.pepstock.charba.client.enums.Position;

abstract class CartesianAxis<T extends CartesianTick> extends Axis {
	
	private static final boolean DEFAULT_OFFSET = false;
	
	private static final boolean DEFAULT_STACKED = false;
	
	private final GridLines grideLines = new GridLines();
	
	private final T ticks;
	
	private final CartesianScaleLabel scaleLabel = new CartesianScaleLabel();
	
	private enum Property implements Key {
		position,
		offset,
		id,
		gridLines,
		scaleLabel,
		ticks,
		stacked
	}

	protected CartesianAxis(T ticks) {
		this.ticks = ticks;
		ticks.setAxis(this);
		setValue(Property.gridLines, grideLines);
		setValue(Property.ticks, this.ticks);
		setValue(Property.scaleLabel, scaleLabel);
	}

	/**
	 * @return the scaleLabel
	 */
	public CartesianScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/**
	 * @return the ticks
	 */
	public T getTicks() {
		return ticks;
	}

	/**
	 * @return the grideLines
	 */
	public GridLines getGrideLines() {
		return grideLines;
	}

	public void setStacked(boolean stacked){
		setValue(Property.stacked, stacked);
	}
	
	/**
	 * @return the stacked
	 */
	public boolean isStacked() {
		return getValue(Property.stacked, DEFAULT_STACKED);
	}

	public void setOffset(boolean offset) {
		setValue(Property.offset, offset);
	}

	public boolean isOffset() {
		return getValue(Property.offset, DEFAULT_OFFSET);
	}
	
	public void setId(String type){
		  setValue(Property.id, type);
	}

	public String getId(){
		  return getValue(Property.id, null);
	}
	
	public void setPosition(Position position){
		setValue(Property.position, position);
	}

	public Position getPosition(){
		return getValue(Property.position, Position.values(), Position.top);
	} 
}
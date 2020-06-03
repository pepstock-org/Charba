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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.callbacks.TimeTickCallback;
import org.pepstock.charba.client.enums.TickSource;

/**
 * The time scale is use to chart time data.<br>
 * It can be placed on either the x or y axis.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianTimeTick extends CartesianTick {

	// handler for callback for category axis
	private final TimeTickHandler tickHandler;

	/**
	 * Builds the object storing the axis instance.
	 * 
	 * @param axis axis instance
	 */
	CartesianTimeTick(Axis axis) {
		super(axis);
		// creates handler
		this.tickHandler = new TimeTickHandler(axis, this);
	}

	/**
	 * Sets the property controls the ticks generation.
	 * 
	 * @param source property controls the ticks generation.
	 */
	public void setSource(TickSource source) {
		getConfiguration().setSource(source);
	}

	/**
	 * Returns the property controls the ticks generation.
	 * 
	 * @return property controls the ticks generation.
	 */
	public TickSource getSource() {
		return getConfiguration().getSource();
	}

	/**
	 * Returns the user callback instance.
	 * 
	 * @return the callback
	 */
	public TimeTickCallback getCallback() {
		return tickHandler.getCallback();
	}

	/**
	 * Sets the user callback instance.
	 * 
	 * @param callback the callback to set
	 */
	public void setCallback(TimeTickCallback callback) {
		tickHandler.setCallback(callback);
	}

	/**
	 * Returns the tick handler.
	 * 
	 * @return the tick handler
	 */
	final TimeTickHandler getTickHandler() {
		return tickHandler;
	}

}
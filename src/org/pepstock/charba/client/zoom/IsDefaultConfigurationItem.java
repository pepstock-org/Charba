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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.zoom.callbacks.CompletedCallback;
import org.pepstock.charba.client.zoom.callbacks.ModeCallback;
import org.pepstock.charba.client.zoom.callbacks.ProgressCallback;
import org.pepstock.charba.client.zoom.callbacks.RejectedCallback;

/**
 * Interface used by pan and zoom object in order to enable to provide the defaults values of properties.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
interface IsDefaultConfigurationItem {

	/**
	 * Returns <code>true</code> to enable element (panning or zooming).
	 * 
	 * @return <code>true</code> to enable element (panning or zooming)
	 */
	default boolean isEnabled() {
		return AbstractConfigurationItem.DEFAULT_ENABLED;
	}

	/**
	 * Returns the element (panning or zooming) directions.
	 * 
	 * @return the element (panning or zooming) directions
	 */
	default InteractionAxis getMode() {
		return AbstractConfigurationItem.DEFAULT_MODE;
	}

	/**
	 * Returns which of the enabled zooming directions should only be available when the mouse cursor is over one of scale.
	 * 
	 * @return which of the enabled zooming directions should only be available when the mouse cursor is over one of scale
	 */
	default InteractionAxis getOverScaleMode() {
		return AbstractConfigurationItem.DEFAULT_OVER_SCALE_MODE;
	}
	
	/**
	 * Returns the minimal pan distance required before actually applying pan.
	 * 
	 * @return the minimal pan distance required before actually applying pan
	 */
	double getThreshold();

	/**
	 * Returns the element (panning or zooming) directions callback, to set the mode at runtime.
	 * 
	 * @return the element (panning or zooming) directions callback
	 */
	default ModeCallback getModeCallback() {
		return null;
	}

	/**
	 * Returns the element (panning or zooming) directions callback, to set the mode at runtime, which of the enabled zooming directions should only be available when the mouse
	 * cursor is over one of scale
	 * 
	 * @return the element (panning or zooming) directions callback
	 */
	default ModeCallback getOverScaleModeCallback() {
		return null;
	}

	/**
	 * Returns the callback called while the user is zooming or panning.
	 * 
	 * @return the callback called while the user is zooming or panning
	 */
	default ProgressCallback getProgressCallback() {
		return null;
	}

	/**
	 * Returns the callback called once zooming or panning is completed.
	 * 
	 * @return the callback called once zooming or panning is completed
	 */
	default CompletedCallback getCompletedCallback() {
		return null;
	}

	/**
	 * Returns the callback called once zooming or panning is rejected.
	 * 
	 * @return the callback called once zooming or panning is rejected
	 */
	default RejectedCallback getRejectedCallback() {
		return null;
	}

	/**
	 * Returns the minimum element (panning or zooming) range depending on scale type.
	 * 
	 * @return the minimum element (panning or zooming) range depending on scale type
	 */
	IsDefaultRange getRangeMin();

	/**
	 * Returns the maximum element (panning or zooming) range depending on scale type.
	 * 
	 * @return the maximum element (panning or zooming) range depending on scale type
	 */
	IsDefaultRange getRangeMax();

}

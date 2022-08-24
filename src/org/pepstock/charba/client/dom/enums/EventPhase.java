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
package org.pepstock.charba.client.dom.enums;

/**
 * Enumerates the phases of the event flow which is currently being evaluated.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum EventPhase
{
	/**
	 * The event is not being processed at this time..
	 */
	NONE(0),
	/**
	 * The event is not being processed at this time.
	 */
	CAPTURING(1),
	/**
	 * The event has arrived at the event's target.
	 */
	AT_TARGET(2),
	/**
	 * The event is propagating back up through the target's ancestors in reverse order, starting with the parent, and eventually reaching the containing window.
	 */
	BUBBLING(3);

	// value for "phase" property
	private final int value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private EventPhase(int value) {
		this.value = value;
	}

	/**
	 * Returns the numeric representation of the phase.
	 * 
	 * @return the numeric representation of the phase
	 */
	public int value() {
		return value;
	}

	/**
	 * Scans all items of enumeration to get the right phase related to passed argument.
	 * 
	 * @param eventPhase specifies the current evaluation phase of the event flow
	 * @return the related event phase.<br>
	 *         If not found, returns always {@link EventPhase#NONE}.
	 */
	public static final EventPhase get(int eventPhase) {
		// scans all phases
		for (EventPhase phase : values()) {
			// checks if equals to passed phase
			if (phase.value == eventPhase) {
				return phase;
			}
		}
		// if here, the argument does not match with any phase
		// then returns none.
		return NONE;
	}
}

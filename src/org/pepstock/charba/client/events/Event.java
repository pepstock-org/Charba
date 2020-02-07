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
package org.pepstock.charba.client.events;

/**
 * Base object for chart events which can be consumed by an handler.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Event {

	// source of the event, always chart instance
	private Object source;

	/** Constructor. */
	protected Event() {
	}

	/**
	 * Returns the event type used to register this event to find handlers of the appropriate class.
	 *
	 * @return the event type
	 */
	public abstract EventType getType();

	/**
	 * Returns the source for this event.<br>
	 * It should be always a chart instance.
	 *
	 * @return object representing the source of this event
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * Implemented by subclasses to dispatch event on the handler.
	 *
	 * @param handler handler instance
	 */
	protected abstract void dispatch(EventHandler handler);

	/**
	 * Set the source that triggered this event.<br>
	 * It should be always a chart instance.
	 *
	 * @param source the source of this event.
	 */
	protected void setSource(Object source) {
		this.source = source;
	}
}

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

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.Key;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;

/**
 * Abstract event which represents a CHART.JS event, which contains a function context, as chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractChartEvent<H extends EventHandler> extends AbstractEvent<H> {

	// instance of function context
	private final Chart functionContext;
	// options key where default function is stored
	private final Key key;

	/**
	 * Creates the event with legend item related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param functionContext function context provided by CHART.JS
	 * @param key options key where default function is stored
	 */
	public AbstractChartEvent(NativeEvent nativeEvent, Chart functionContext, Key key) {
		super(nativeEvent);
		// checks if arguments are consistent
		if (functionContext == null) {
			throw new IllegalArgumentException("Context is null");
		}
		Key.checkIfValid(key);
		this.functionContext = functionContext;
		this.key = key;
	}

	/**
	 * Returns the java script function context of CHART.JS events.
	 * 
	 * @return the java script function context of CHART.JS events.
	 */
	public final Chart getContext() {
		return functionContext;
	}

	/**
	 * Returns the options key where default function is stored.
	 * 
	 * @return the options key where default function is stored
	 */
	public final Key getKey() {
		return key;
	}

}
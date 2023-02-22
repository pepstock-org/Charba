/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.interaction;

import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.IsInteractionMode;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.items.InteractionItem;
import org.pepstock.charba.client.items.InteractionOptions;

/**
 * Maps the common code for native implementation of a custom interaction mode.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of callback embedded in the object. Can be {@link ExtendedInteraction} or {@link NativeInteraction}.
 *
 */
abstract class AbstractNativeInteractioner<T> extends AbstractInteractioner {

	// defaults option
	private static final InteractionOptions DEFAULT_OPTIONS = DefaultsBuilder.get().getOptions().getInteraction().create();

	// java script implementation instance
	private final T callback;

	/**
	 * Creates the interactioner passing the name of interaction mode as argument.
	 * 
	 * @param name of interaction mode.
	 * @param callback java script implementation instance
	 */
	AbstractNativeInteractioner(String name, T callback) {
		this(IsInteractionMode.create(name), callback);
	}

	/**
	 * Creates the interactioner passing the interaction mode instance as argument.
	 * 
	 * @param mode interaction mode instance
	 * @param callback java script implementation instance
	 */
	AbstractNativeInteractioner(IsInteractionMode mode, T callback) {
		super(mode);
		// checks callback consistent
		Checker.assertCheck(checkCallbackInstance(callback), "Callback instance is not consistent or not supported");
		// stores callback
		this.callback = callback;
	}

	/**
	 * Returns the callback instance.
	 * 
	 * @return the callback instance
	 */
	final T getCallback() {
		return callback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.interaction.Interactioner#invoke(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.events.ChartEventContext,
	 * org.pepstock.charba.client.items.InteractionOptions, boolean)
	 */
	@Override
	public final List<InteractionItem> invoke(IsChart chart, ChartEventContext event, InteractionOptions options, boolean useFinalPosition) {
		// gets native chart
		Chart nativeChart = Charts.getNative(IsChart.checkAndGetIfValid(chart));
		// checks native chart consistency
		Checker.assertCheck(nativeChart != null, "Native chart is undefined");
		// checks event
		Checker.assertCheck(event != null && event.isConsistent(), "Event is not consistent");
		// checks options
		InteractionOptions internalOptions = options != null ? options : DEFAULT_OPTIONS;
		// invokes interaction mode callback
		ArrayObject result = invoke(nativeChart, event.nativeObject(), internalOptions.nativeObject(), useFinalPosition);
		// returns as list
		return ArrayListHelper.unmodifiableList(result, Interactions.get().getFactory(chart));
	}

	/**
	 * Method of function to be called to invoke a custom interactioner.
	 * 
	 * @param chart the chart we are returning items from
	 * @param event the event we are find things at
	 * @param options options to use
	 * @param useFinalPosition use final element position (animation target)
	 * @return items that are found
	 */
	abstract ArrayObject invoke(Chart chart, NativeObject event, NativeObject options, boolean useFinalPosition);

	/**
	 * Returns <code>true</code> if the callback is consistent
	 * 
	 * @param callback callback instance to check
	 * @return <code>true</code> if the callback is consistent
	 */
	private boolean checkCallbackInstance(T callback) {
		return callback instanceof ExtendedInteraction || callback instanceof NativeInteraction;
	}
}

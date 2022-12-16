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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPadding;

/**
 * Object can be provided with additional configuration by callbacks to define padding options at runtime, and with the possibility to reset {@link PaddingCallback} if the
 * {@link IsScriptablePaddingProvider} instance has been set with a {@link PaddingCallback}.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of scriptable context
 */
public abstract class AbstractScriptablePadding<T extends ChartContext> extends AbstractPadding {

	private final IsScriptablePaddingProvider<T> scriptablePaddingProvider;

	/**
	 * Creates an empty padding to use for chart configuration.
	 * 
	 * @param scriptablePaddingProvider the provider of padding callback
	 * @param defaultValues default provider
	 */
	protected AbstractScriptablePadding(IsScriptablePaddingProvider<T> scriptablePaddingProvider, IsDefaultPadding defaultValues) {
		this(scriptablePaddingProvider, defaultValues, null);
	}

	/**
	 * Creates a padding to use for chart configuration, wrapping a native object instance.
	 * 
	 * @param scriptablePaddingProvider the provider of padding callback
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractScriptablePadding(IsScriptablePaddingProvider<T> scriptablePaddingProvider, IsDefaultPadding defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
		// checks if padding container is consistent
		// stores padding container
		this.scriptablePaddingProvider = Checker.checkAndGetIfValid(scriptablePaddingProvider, "Scriptable padding provider argument");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractPadding#setLeft(int)
	 */
	@Override
	public void setLeft(int value) {
		// resets callback
		resetCallback();
		// stores the values
		super.setLeft(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractPadding#setRight(int)
	 */
	@Override
	public void setRight(int value) {
		// resets callback
		resetCallback();
		// stores the values
		super.setRight(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractPadding#setTop(int)
	 */
	@Override
	public void setTop(int value) {
		// resets callback
		resetCallback();
		// stores the values
		super.setTop(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractPadding#setBottom(int)
	 */
	@Override
	public void setBottom(int value) {
		// resets callback
		resetCallback();
		// stores the values
		super.setBottom(value);
	}

	/**
	 * Invokes when any property of the padding is being set, in order to reset the {@link PaddingCallback} if exists
	 */
	protected void resetCallback() {
		// checks if the padding has been set previously as a callback
		if (scriptablePaddingProvider.getPaddingCallback() != null) {
			// if yes, resets it
			scriptablePaddingProvider.setPadding((PaddingCallback<T>) null);
		}
	}

}
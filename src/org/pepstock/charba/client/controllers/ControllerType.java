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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;

/**
 * Represent the type of new controller. Must be created for every controller implementation.<br>
 * It can be created ONLY extending an existing chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ControllerType implements Type {
	// type of chart
	private final String type;
	// type of extended chart
	private final Type chartType;
	// if the default options of base type must be cloned
	private final boolean cloneDefaults;
	// controller provider instance
	private final ControllerProvider provider;
	// controller registration handler
	private final ControllerRegistrationHandler registrationHandler;

	/**
	 * Creates new chart type based on existing chart type, as extension.<br>
	 * Scale type is the existing chart one.
	 * 
	 * @param type new chart type as string.
	 * @param chartType existing chart type, as extension.
	 * @param provider controller provider instance to use for controller registering
	 */
	public ControllerType(String type, Type chartType, ControllerProvider provider) {
		this(type, chartType, provider, null);
	}
	
	/**
	 * Creates new chart type based on existing chart type, as extension.<br>
	 * Scale type is the existing chart one.
	 * 
	 * @param type new chart type as string.
	 * @param chartType existing chart type, as extension.
	 * @param provider controller provider instance to use for controller registering
	 * @param handler controller registration handler instance
	 */
	public ControllerType(String type, Type chartType, ControllerProvider provider, ControllerRegistrationHandler handler) {
		this(type, chartType, provider, handler, true);
	}
	
	/**
	 * Creates new chart type based on existing chart type, as extension.<br>
	 * Scale type is the existing chart one.
	 * 
	 * @param type new chart type as string.
	 * @param chartType existing chart type, as extension.
	 * @param provider controller provider instance to use for controller registering
	 * @param cloneDefaults if <code>true</code>, clones the default options of base chart type.
	 */
	public ControllerType(String type, Type chartType, ControllerProvider provider, boolean cloneDefaults) {
		this(type, chartType, provider, null, cloneDefaults);
	}

	/**
	 * Creates new chart type based on existing chart type, as extension.<br>
	 * Scale type is the existing chart one.
	 * 
	 * @param type new chart type as string.
	 * @param chartType existing chart type, as extension.
	 * @param provider controller provider instance to use for controller registering
	 * @param handler controller registration handler instance
	 * @param cloneDefaults if <code>true</code>, clones the default options of base chart type.
	 */
	public ControllerType(String type, Type chartType, ControllerProvider provider, ControllerRegistrationHandler handler, boolean cloneDefaults) {
		// checks type if consistent
		Checker.checkIfValid(type, "Type");
		// checks if the controller type is already defined for a default chart type
		// because a controller type can non called as a default one
		Checker.assertCheck(!Key.hasKeyByValue(ChartType.values(), type), "Type '" + type + "' is a default chart type");
		// checks if controller provider instance is consistent
		Checker.checkIfValid(provider, "Controller provider");
		// checks chart type if is consistent
		Type.checkIfValid(chartType);
		this.type = type;
		this.chartType = chartType;
		this.provider = provider;
		this.registrationHandler = handler;
		this.cloneDefaults = cloneDefaults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Type#scaleType()
	 */
	@Override
	public ScaleType scaleType() {
		return chartType.scaleType();
	}

	/**
	 * Returns the extended chart type of controller.
	 * 
	 * @return the extended chart type of controller
	 */
	public Type getChartType() {
		return chartType;
	}

	/**
	 * Returns <code>true</code> if it clones the default options of base chart type.
	 * 
	 * @return <code>true</code> if it clones the default options of base chart type
	 */
	public boolean isCloneDefaults() {
		return cloneDefaults;
	}

	/**
	 * Registers the controller if it is not already registered.
	 * 
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the controller is already registered with the controller type of controller instance.
	 */
	public boolean register() {
		// checks if already register
		if (!Defaults.get().getControllers().isRegistered(type)) {
			// invokes the controller provider
			Controller controller = provider.provide(this);
			// checks if controller is consistent
			// checks if the controller type of controller is equals to this
			if (controller != null && this.equals(controller.getType()) && Key.equals(chartType, controller.getType().getChartType())) {
				// checks if handler is consistent
				if (registrationHandler != null) {
					// invokes before
					registrationHandler.onBeforeRegister(this);
				}
				// if not, adds a controller
				boolean register = Defaults.get().getControllers().register(controller);
				// checks if handler is consistent
				if (registrationHandler != null) {
					// invokes after
					registrationHandler.onAfterRegister(this, register);
				}
				// returns the boolean if the controller has been added
				return register;
			}
		}
		// if here, already registered
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// checks if the object is a type
		if (obj instanceof Type) {
			// casts
			Type objType = (Type) obj;
			// checks if there are consistent
			return Key.equals(this, objType);
		}
		return false;
	}

}

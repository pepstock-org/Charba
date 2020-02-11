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
package org.pepstock.charba.client.resources;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.adapters.AbstractModule;

/**
 * Base class to extend in order to have an java script injection, needed to CHARBA, where CHART.JS and date library will be
 * load in embedded mode.<br>
 * Every instance must have a module related to date adapter and library.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractEmbeddedResources extends AbstractResources implements IsDateAdpaterResources {

	// chart js source code
	private static final ChartJsResource CHARTJS = new ChartJsResource();

	/**
	 * Creates an embedded resource object by passed module, which represents the date adapter and library, as argument.
	 * 
	 * @param module module of date adapter and library.
	 */
	AbstractEmbeddedResources(AbstractModule module) {
		super(module);
	}

	/**
	 * Injects CHART.JS, date adapter and library if not already injected.
	 */
	@Override
	public final void inject() {
		// checks if module has been already injected
		if (!getModule().isInjected()) {
			// inject Chart.js if not already loaded
			ensureInjected(CHARTJS);
			// to be sure that date time library has been injected
			ensureInjected(datetimeLibrary());
			// to be sure that date time chart.js adapter has been injected
			ensureInjected(datetimeAdapter());
			// notify to module that has been injected
			getModule().injectionComplete(EmbeddedDateAdapterInjectionComplete.get());
		}
	}

	/**
	 * Injects a script resource if not injected yet.
	 * 
	 * @param resource script resource
	 */
	private void ensureInjected(InjectableResource resource) {
		// checks if prototype is a text resource and not already injected
		if (!Injector.isInjected(resource)) {
			// inject Chart.js if not already loaded
			Injector.ensureInjected(resource);
		}
	}
}

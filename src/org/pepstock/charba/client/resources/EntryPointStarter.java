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

import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;

/**
 * Utility to start an entry point leveraging on deferred resources mode.<br>
 * This helps when the GWT application is leveraging on code splitting.<br>
 * <br>
 * 
 * <pre>
 * GWT.runAsync(new RunAsyncCallback() {
 * 
 * 	&#64;Override
 * 	public void onFailure(Throwable throwable) {
 * 		Window.alert("Code download failed");
 * 	}
 * 
 * 	&#64;Override
 * 	public void onSuccess() {
 * 		EntryPointStarter.run(DeferredResources.INSTANCE, new Runnable() {
 * 
 * 			&#64;Override
 * 			public void run() {
 * 				// CHARBA charts and api...
 * 			}
 * 		});
 * 	}
 * });
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EntryPointStarter {

	/**
	 * To avoid any instantiation
	 */
	private EntryPointStarter() {
		// do nothing
	}

	/**
	 * Start an entry point as a runnable.<br>
	 * This runnable instance must contains all calls to chart.<br>
	 * This helps when the GWT application is leveraging on code splitting.
	 * 
	 * @param resources deferred resource client bundle to set
	 * @param runnable the entry point instance as runnable
	 */
	public static void run(IsDeferredResources resources, final Runnable runnable) {
		// checks if the entry point is consistent
		if (runnable == null) {
			// if not, exception
			throw new IllegalArgumentException("Runnable argument is null");
		}
		// sets deferred resources
		ResourcesType.setClientBundle(resources);
		// starts loading the CHART.JS
		loadChartJS(resources, runnable);
	}

	/**
	 * Loads CHART.JS source code in async way.<br>
	 * After CHART.JS loading, it will load the date time library.
	 * 
	 * @param resources deferred resource client bundle to set
	 * @param runnable the entry point instance as runnable
	 */
	private static void loadChartJS(IsDeferredResources resources, final Runnable runnable) {
		try {
			// loads CHART.JS in async
			// using the deferred client instance
			resources.chartJs().getText(new ResourceCallback<TextResource>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * com.google.gwt.resources.client.ResourceCallback#onError(com.google.gwt.resources.client.ResourceException)
				 */
				@Override
				public void onError(ResourceException e) {
					throw new ResourceLoadException("Unable to load CHART.JS resource", e);
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * com.google.gwt.resources.client.ResourceCallback#onSuccess(com.google.gwt.resources.client.ResourcePrototype)
				 */
				@Override
				public void onSuccess(TextResource resource) {
					// injects the CHART.JS
					Injector.ensureInjected(resource);
					// loads date-time library
					EntryPointStarter.loadDatetimeLibrary(resources, runnable);
				}
			});
		} catch (ResourceException e) {
			// if here, there is an error on resource callback
			throw new ResourceLoadException("Unable to load CHART.JS resource", e);
		}
	}

	/**
	 * Loads date time library source code in async way.<br>
	 * After date time library loading, it will execute the runnable.
	 * 
	 * @param resources deferred resource client bundle to set
	 * @param runnable the entry point instance as runnable
	 */
	private static void loadDatetimeLibrary(IsDeferredResources resources, final Runnable runnable) {
		try {
			// loads date time library in async
			// using the deferred client instance
			resources.datetimeLibrary().getText(new ResourceCallback<TextResource>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * com.google.gwt.resources.client.ResourceCallback#onError(com.google.gwt.resources.client.ResourceException)
				 */
				@Override
				public void onError(ResourceException e) {
					throw new ResourceLoadException("Unable to load date time library resource", e);
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * com.google.gwt.resources.client.ResourceCallback#onSuccess(com.google.gwt.resources.client.ResourcePrototype)
				 */
				@Override
				public void onSuccess(TextResource resource) {
					// injects the date time library
					Injector.ensureInjected(resource);
					// injects also the date time adapter, always sync
					Injector.ensureInjected(resources.datetimeAdapter());
					// loads date-time adapter library
					// executes the entry point
					runnable.run();
				}
			});
		} catch (ResourceException e) {
			// if here, there is an error on resource callback
			throw new ResourceLoadException("Unable to load date time library resource", e);
		}
	}
}

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

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.DeferredCharba;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.commons.IsEnvelop;

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
 * 	&#64;Override
 * 	public void onFailure(Throwable throwable) {
 * 		Window.alert("Code injection failed");
 * 	}
 * 
 * 	&#64;Override
 * 	public void onSuccess() {
 * 		EntryPointStarter.run(DeferredResources.INSTANCE, new Runnable() {
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
	 * This helps when the GWT application is leveraging on code splitting.<br>
	 * This is deprecated, use {@link DeferredCharba}.
	 * 
	 * @param runnable the entry point instance as runnable
	 * @param envelop envelop which contains the deferred resources instance to set
	 */
	public static void run(final Runnable runnable, ChartEnvelop<DeferredResources> envelop) {
		// checks envelop
		IsEnvelop.checkIfValid(envelop);
		// checks if the entry point is consistent
		if (runnable == null) {
			// if not, exception
			throw new IllegalArgumentException("Runnable argument is null");
		}
		// gets resources
		DeferredResources resources = envelop.getContent();
		// sets deferred resources
		ResourcesType.setResources(envelop);
		// checks if is already injected
		if (!ResourcesType.isInjected()) {
			// starts loading the CHART.JS
			loadChartJS(resources, runnable);
		} else {
			// if here the resources have been already injected
			// then invokes the runnable
			runnable.run();
		}
	}

	/**
	 * Loads CHART.JS source code in asynchronously way.<br>
	 * After CHART.JS loading, it will load the date time library.
	 * 
	 * @param resources deferred date adapter resources instance to set
	 * @param runnable the entry point instance as runnable
	 */
	private static void loadChartJS(DeferredResources resources, final Runnable runnable) {
		try {
			// loads CHART.JS in asynchronous
			// using the deferred client instance
			resources.getDeferredResourcesResource().chartJs().getText(new ResourceCallback<TextResource>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see com.google.gwt.resources.client.ResourceCallback#onError(com.google.gwt.resources.client.ResourceException)
				 */
				@Override
				public void onError(ResourceException e) {
					throw new ResourceLoadException("Unable to load CHART.JS resource", e);
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see com.google.gwt.resources.client.ResourceCallback#onSuccess(com.google.gwt.resources.client.ResourcePrototype)
				 */
				@Override
				public void onSuccess(TextResource resource) {
					// creates an injector items
					AbstractInjectableResource injectorItem = new InternalInjectableTextResource(ResourceName.CHART, resource);
					// injects the CHART.JS
					Injector.ensureInjected(injectorItem);
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
	 * Loads date time library source code in asynchronously way.<br>
	 * After date time library loading, it will execute the runnable.
	 * 
	 * @param resources deferred date adapter resources instance to set
	 * @param runnable the entry point instance as runnable
	 */
	private static void loadDatetimeLibrary(DeferredResources resources, final Runnable runnable) {
		try {
			// loads date time library in asynchronous
			// using the deferred client instance
			resources.getDeferredResourcesResource().datetimeLibrary().getText(new ResourceCallback<TextResource>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see com.google.gwt.resources.client.ResourceCallback#onError(com.google.gwt.resources.client.ResourceException)
				 */
				@Override
				public void onError(ResourceException e) {
					throw new ResourceLoadException("Unable to load date time library resource", e);
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see com.google.gwt.resources.client.ResourceCallback#onSuccess(com.google.gwt.resources.client.ResourcePrototype)
				 */
				@Override
				public void onSuccess(TextResource resource) {
					// creates an injector items
					AbstractInjectableResource injectorLibItem = new InternalInjectableTextResource(ResourceName.DATE_TIME_LIBRARY, resource);
					// injects the date time library
					Injector.ensureInjected(injectorLibItem);
					// creates an injector items
					AbstractInjectableResource injectorAdapterItem = new InternalInjectableTextResource(ResourceName.DATE_TIME_ADAPTER, resources.getDeferredResourcesResource().datetimeAdapter());
					// injects also the date time adapter, always sync
					Injector.ensureInjected(injectorAdapterItem);
					// notify for injection
					resources.injected();
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

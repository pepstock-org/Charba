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
package org.pepstock.charba.client;

/**
 * Default implementation of charts life cycle listener to help who wants to implement only some methods and not the complete
 * interface.<br>
 * If the implementation doesn't override the methods, the method doesn't do anything.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractChartsLifecycleListener implements ChartsLifecycleListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ChartsLifecycleListener#onBeforeInit(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeInit(IsChart chart) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ChartsLifecycleListener#onAfterInit(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterInit(IsChart chart) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ChartsLifecycleListener#onBeforeDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeDestroy(IsChart chart) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ChartsLifecycleListener#onAfterDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterDestroy(IsChart chart) {
		// do nothing
	}

}

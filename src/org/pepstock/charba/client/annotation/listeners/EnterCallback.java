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
package org.pepstock.charba.client.annotation.listeners;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.AbstractAnnotation;
import org.pepstock.charba.client.annotation.AnnotationPlugin;

/**
 * Callback interface of {@link AnnotationPlugin#ID} plugin that is called once enter is fired.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface EnterCallback {

	/**
	 * Method called once enter is fired.
	 * 
	 * @param chart chart instance
	 * @param annotation annotation instance where event has been performed
	 */
	void onEnter(IsChart chart, AbstractAnnotation annotation);

}

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
package org.pepstock.charba.client.ext.datalabels;

import org.pepstock.charba.client.AbstractChart;

/**
 * Callback interface of labels plugin to change font color at runtime, using the arguments.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface BackgroundColorCallback {

	/**
	 * FIXME
	 * 
	 */
	String backgroundColor(AbstractChart<?, ?> chart, Context context);

}

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
package org.pepstock.charba.client.ext.labels;

import org.pepstock.charba.client.AbstractChart;

/**
 * Callback interface of labels plugin to provide the item to be rendered as string at runtime, using the arguments.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface RenderStringCallback {

	/**
	 * Called to provide the item to be rendered as string at runtime, using the arguments.
	 * 
	 * @param chart chart instance
	 * @param item render item, passed by plugin
	 * @return a string instance to show as label
	 */
	String render(AbstractChart<?, ?> chart, RenderItem item);

}

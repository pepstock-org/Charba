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
package org.pepstock.charba.client.labels.callbacks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.labels.LabelsScriptableContext;
import org.pepstock.charba.client.labels.LabelsPlugin;

/**
 * Callback interface of {@link LabelsPlugin#ID} plugin to provide the item to be rendered as string or as image at runtime, using the arguments.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see Img
 */
public interface RenderCallback {

	/**
	 * Called to provide the item to be rendered as string or as image at runtime, using the arguments.
	 * 
	 * @param chart chart instance
	 * @param context callback context, passed by plugin
	 * @return a string or as image instance to show as label
	 */
	Object invoke(IsChart chart, LabelsScriptableContext context);

}

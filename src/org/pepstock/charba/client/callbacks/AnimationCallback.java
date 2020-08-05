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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.options.AbstractAnimationOptions;

/**
 * Defines the callback to implement in order to return an animation options at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of animation options
 */
public interface AnimationCallback<T extends AbstractAnimationOptions> {

	/**
	 * Callback that is invoked to get an animation options.
	 * 
	 * @param chart chart instance
	 * @param context context instance
	 * @param animationOptions empty object already prepared, ready to be configured
	 * @return an animation options used to configure the chart
	 */
	T invoke(IsChart chart, ScriptableContext context, T animationOptions);

}
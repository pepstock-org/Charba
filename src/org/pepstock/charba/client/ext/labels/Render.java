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

import org.pepstock.charba.client.commons.Key;

/**
 * Enumeration of available render to use to configure LABELS plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Render implements Key
{
	/**
	 * The label will be rendered.
	 */
	label,
	/**
	 * The value will be rendered.
	 */
	value,
	/**
	 * The percentage will be rendered. Is the default.
	 */
	percentage,
	/**
	 * The images will be rendered.
	 */
	image,
	/**
	 * The render is defined by callback. This can not be used to be set.
	 * 
	 * @see RenderStringCallback
	 * @see RenderImageCallback
	 */
	callback
}

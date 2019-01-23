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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumeration to set the type of gradient, linear or radial.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum GradientType implements Key
{

	/**
	 * A linear gradient creates a band of colors that progress in a straight line.
	 */
	linear,
	/**
	 * Radial gradients are similar to linear gradients, except that they radiate out from a central point. 
	 */
	radial
}
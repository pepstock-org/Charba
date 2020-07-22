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
package org.pepstock.charba.client.options;

/**
 * This is a standard implementation of an animation mode
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class StandardAnimationMode extends AbstractStandardKey implements IsAnimationMode {

	/**
	 * Builds the object with the animation mode value as string
	 * 
	 * @param value value of key as String
	 */
	StandardAnimationMode(String value) {
		super(value, true);
	}

}

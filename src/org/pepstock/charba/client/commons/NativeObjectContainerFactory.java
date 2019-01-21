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
package org.pepstock.charba.client.commons;

/**
 * Interface to be implemented to load elements from an array of native object
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface NativeObjectContainerFactory<T extends NativeObjectContainer> {

	/**
	 * Creates a native object container instance by a native object
	 * 
	 * @param nativeObject native object
	 * @return native object container element instance
	 */
	T create(final NativeObject nativeObject);

}
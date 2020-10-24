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
package org.pepstock.charba.client.resources;

/**
 * Java script resources container to reference LUXON as date time library (synchronous mode).<br>
 * The same of {@link LuxonEmbeddedResources}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EmbeddedResources extends LuxonEmbeddedResources {

	/**
	 * Static reference to LUXON resources.
	 */
	public static final EmbeddedResources INSTANCE = new EmbeddedResources();

	/**
	 * To avoid any instantiation
	 */
	private EmbeddedResources() {
		super();
	}

}
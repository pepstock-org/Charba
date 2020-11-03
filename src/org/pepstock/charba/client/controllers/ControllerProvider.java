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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.Controller;

/**
 * The implementation of this interface is mandatory when you create a controller, which has got the purpose to provide a controller instance.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ControllerProvider {

	/**
	 * Provides a controller instance for the specific controller type.
	 * 
	 * @param controllerType controller type of controller instance
	 * @return a controller instance for the specific controller type
	 */
	Controller provide(ControllerType controllerType);

}

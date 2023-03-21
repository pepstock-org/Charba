/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.zoom;

/**
 * {@link ZoomPlugin#ID} plugin default options interface.<br>
 * It contains all default values, PAN and ZOOM.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultOptions {

	/**
	 * Returns the pan element.
	 * 
	 * @return the pan element.
	 */
	IsDefaultPan getPan();

	/**
	 * Returns the zoom element.
	 * 
	 * @return the zoom element.
	 */
	IsDefaultZoom getZoom();

	/**
	 * Returns the limits element.
	 * 
	 * @return the limits element.
	 */
	IsDefaultLimits getLimits();

}
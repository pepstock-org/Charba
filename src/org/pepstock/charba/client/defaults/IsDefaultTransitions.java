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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.options.TransitionKey;

/**
 * Interface to define animation node for update mode element defaults, TRANSITIONS name space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultTransitions {

	/**
	 * Returns <code>true</code> if an animation transition instance is stored in the animation options.
	 * 
	 * @param transition transition instance used to check in the animation options
	 * @return <code>true</code> if an animation collection instance is stored in the animation options
	 */
	boolean has(TransitionKey transition);

	/**
	 * Returns an animation transition instance if stored in the animation options.
	 * 
	 * @param transition transition instance used to get for animation options
	 * @return an animation transition instance or <code>null</code> if does not exists
	 */
	IsDefaultAnimationTransition get(TransitionKey transition);

}
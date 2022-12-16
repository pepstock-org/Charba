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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.defaults.IsDefaultTransitions;

/**
 * Interface to map a transitions element, TRANSITIONS name space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsTransitions extends IsDefaultTransitions {

	/**
	 * Returns an animation transition instance if stored in the animation options.
	 * 
	 * @param transition transition instance used to get for animation options
	 * @return an animation transition instance or <code>null</code> if does not exists
	 */
	@Override
	AnimationTransition get(TransitionKey transition);

	/**
	 * Sets an animation transition instance to store in the animation options.
	 * 
	 * @param transition transition instance used to get for animation options
	 * @param animationTransition an animation transition instance to set
	 */
	void set(TransitionKey transition, AnimationTransition animationTransition);

	/**
	 * Creates an animation transition instance and stores in the animation options.
	 * 
	 * @param transition transition key used to create the animation transitions
	 * @return a transition animation options
	 */
	AnimationTransition create(TransitionKey transition);

	/**
	 * Removes an animation transition previously added.
	 * 
	 * @param transition transition instance used to remove from animation options
	 */
	void remove(TransitionKey transition);

}
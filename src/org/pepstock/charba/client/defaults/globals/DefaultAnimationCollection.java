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
package org.pepstock.charba.client.defaults.globals;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.callbacks.FromCallback;
import org.pepstock.charba.client.callbacks.ToCallback;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.items.Undefined;

/**
 * CHART.JS default values for animation collection.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimationCollection extends AbstractDefaultAnimation implements IsDefaultAnimationCollection {

	private static final double DEFAULT_FROM = Undefined.DOUBLE;

	private static final boolean DEFAULT_FROM_AS_BOOLEAN = Undefined.BOOLEAN;

	private static final String DEFAULT_FROM_AS_STRING = Undefined.STRING;

	private static final double DEFAULT_TO = Undefined.DOUBLE;

	private static final boolean DEFAULT_TO_AS_BOOLEAN = Undefined.BOOLEAN;

	private static final String DEFAULT_TO_AS_STRING = Undefined.STRING;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getProperties()
	 */
	@Override
	public List<Key> getProperties() {
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getType()
	 */
	@Override
	public AnimationType getType() {
		return AnimationType.UNKNOWN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getFrom()
	 */
	@Override
	public double getFrom() {
		return DEFAULT_FROM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getFromAsBoolean()
	 */
	@Override
	public boolean getFromAsBoolean() {
		return DEFAULT_FROM_AS_BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getFromAsString()
	 */
	@Override
	public String getFromAsString() {
		return DEFAULT_FROM_AS_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getTo()
	 */
	@Override
	public double getTo() {
		return DEFAULT_TO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getToAsBoolean()
	 */
	@Override
	public boolean getToAsBoolean() {
		return DEFAULT_TO_AS_BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getToAsString()
	 */
	@Override
	public String getToAsString() {
		return DEFAULT_TO_AS_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getFromCallback()
	 */
	@Override
	public FromCallback getFromCallback() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getToCallback()
	 */
	@Override
	public ToCallback getToCallback() {
		return null;
	}

}
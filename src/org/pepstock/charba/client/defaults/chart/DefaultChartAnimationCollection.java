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
package org.pepstock.charba.client.defaults.chart;

import java.util.List;

import org.pepstock.charba.client.callbacks.FromCallback;
import org.pepstock.charba.client.callbacks.ToCallback;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.options.AnimationPropertyKey;

/**
 * CHART.JS default values for animation collection.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartAnimationCollection extends AbstractDefaultChartAnimation<IsDefaultAnimationCollection> implements IsDefaultAnimationCollection {

	/**
	 * Creates a default animation collection.
	 * 
	 * @param collection a default animation collection to wrap
	 */
	DefaultChartAnimationCollection(IsDefaultAnimationCollection collection) {
		super(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getProperties()
	 */
	@Override
	public List<AnimationPropertyKey> getProperties() {
		return getDefaults().getProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getType()
	 */
	@Override
	public AnimationType getType() {
		return getDefaults().getType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getFrom()
	 */
	@Override
	public double getFrom() {
		return getDefaults().getFrom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getFromAsBoolean()
	 */
	@Override
	public boolean getFromAsBoolean() {
		return getDefaults().getFromAsBoolean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getFromAsString()
	 */
	@Override
	public String getFromAsString() {
		return getDefaults().getFromAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getTo()
	 */
	@Override
	public double getTo() {
		return getDefaults().getTo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getToAsBoolean()
	 */
	@Override
	public boolean getToAsBoolean() {
		return getDefaults().getToAsBoolean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationProperty#getToAsString()
	 */
	@Override
	public String getToAsString() {
		return getDefaults().getToAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getFromCallback()
	 */
	@Override
	public FromCallback getFromCallback() {
		return getDefaults().getFromCallback();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getToCallback()
	 */
	@Override
	public ToCallback getToCallback() {
		return getDefaults().getToCallback();
	}

}
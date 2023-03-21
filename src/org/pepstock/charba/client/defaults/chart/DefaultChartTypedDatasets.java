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

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.defaults.IsDefaultTransitions;
import org.pepstock.charba.client.defaults.IsDefaultTypedDataset;

/**
 * Default values for DATASETS element for a specific {@link Type}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartTypedDatasets implements IsDefaultTypedDataset {

	private final IsDefaultTypedDataset dataset;

	/**
	 * Creates the object by typed data set option element instance.
	 * 
	 * @param dataset typed data set option element instance.
	 */
	public DefaultChartTypedDatasets(IsDefaultTypedDataset dataset) {
		this.dataset = dataset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTypedDatasets#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return dataset.getAnimation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationContainer#getTransitions()
	 */
	@Override
	public IsDefaultTransitions getTransitions() {
		return dataset.getTransitions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimations()
	 */
	@Override
	public IsDefaultAnimations getAnimations() {
		return dataset.getAnimations();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTypedDatasets#getBarPercentage()
	 */
	@Override
	public double getBarPercentage() {
		return dataset.getBarPercentage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTypedDatasets#getCategoryPercentage()
	 */
	@Override
	public double getCategoryPercentage() {
		return dataset.getCategoryPercentage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTypedDatasets#getBarThickness()
	 */
	@Override
	public int getBarThickness() {
		return dataset.getBarThickness();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTypedDatasets#getMaxBarThickness()
	 */
	@Override
	public int getMaxBarThickness() {
		return dataset.getMaxBarThickness();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTypedDatasets#getMinBarLength()
	 */
	@Override
	public int getMinBarLength() {
		return dataset.getMinBarLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBarDatasets#isGrouped()
	 */
	@Override
	public boolean isGrouped() {
		return dataset.isGrouped();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTypedDatasets#isShowLine()
	 */
	@Override
	public boolean isShowLine() {
		return dataset.isShowLine();
	}

}
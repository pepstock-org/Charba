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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.FillCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.IsFill;

/**
 * The scatter chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Extends the line dataset to set fixed properties for scatter chart.<br>
 * The scatter dataset can use ONLY data points. If you try to use array of data numbers, an exception will be thrown.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScatterDataset extends LineDataset {

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public ScatterDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public ScatterDataset(boolean hidden) {
		this(ChartType.SCATTER, hidden);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public ScatterDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public ScatterDataset(IsDefaultOptions defaultValues, boolean hidden) {
		this(ChartType.SCATTER, defaultValues, hidden);
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected ScatterDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected ScatterDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// scatter is always no fill
		super.setFill(Fill.FALSE);
		// scatter has never show lines
		super.setShowLine(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#mustUseDataPoints()
	 */
	@Override
	final boolean mustUseDataPoints() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.LiningDataset#setFill(org.pepstock.charba.client.enums.Fill)
	 */
	@Override
	public void setFill(IsFill fill) {
		// sets always to no fill
		super.setFill(Fill.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFill#setFillBaseline(double)
	 */
	@Override
	public void setFillBaseline(double baseline) {
		// sets always to no fill
		super.setFill(Fill.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.LiningDataset#setFill(org.pepstock.charba.client.callbacks.FillCallback)
	 */
	@Override
	public void setFill(FillCallback fillCallback) {
		// sets always to no fill
		super.setFill(Fill.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.LiningDataset#setFill(org.pepstock.charba.client.callbacks.NativeCallback)
	 */
	@Override
	public void setFill(NativeCallback fillCallback) {
		// sets always to no fill
		super.setFill(Fill.FALSE);
	}

}
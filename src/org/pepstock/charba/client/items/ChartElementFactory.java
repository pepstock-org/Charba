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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Maps the methods to implement an factory to map {@link ChartElement}, used by each controlloer to draw the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface ChartElementFactory extends NativeObjectContainerFactory<ChartElement> {

	/**
	 * Returns the type of the {@link ChartElement}.
	 * 
	 * @return the type of the {@link ChartElement}
	 */
	String getType();

	/**
	 * Creates a {@link ChartElementOptions} instance by a native object.
	 * 
	 * @param parent chart element where the options belong to
	 * @param nativeObject native object
	 * @return a {@link ChartElementOptions} instance by a native object
	 */
	ChartElementOptions createOptions(final ChartElement parent, final NativeObject nativeObject);

	/**
	 * Returns the context instance from an element.
	 * 
	 * @param nativeObject native object
	 * @return the context instance from an element
	 */
	ChartContext createContext(final NativeObject nativeObject);
}
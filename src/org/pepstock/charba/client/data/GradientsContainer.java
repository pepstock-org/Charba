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

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientBuilder;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.data.Dataset.CanvasObjectKey;

/**
 * Utility to manage the gradients inside the configuration item of dataset, setting to the specific properties of the elements.<br>
 * It stores the gradients information in the a native object added to Charba configuration, on specific property names for Charba.<br>
 * The canvas object are stored in the native object by the "original" property names to use to configure CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class GradientsContainer extends AbstractContainer<Gradient> {

	// static instance for the gradient factory
	private static final GradientContainerFactory FACTORY = new GradientContainerFactory();

	/**
	 * Creates the object by an empty native java script object and stores the dataset instance it belongs to.
	 * 
	 * @param dataset the dataset instance it belongs to
	 */
	GradientsContainer(Dataset dataset) {
		super(dataset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjects#getFactory()
	 */
	@Override
	NativeObjectContainerFactory<Gradient> getFactory() {
		return FACTORY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.AbstractContainer#createCallback(org.pepstock.charba.client.data.Dataset.CanvasObjectKey)
	 */
	@Override
	AbstractCanvasObjectCallback<Gradient> createCallback(CanvasObjectKey key) {
		return new DatasetGrandientCallback(this, key);
	}

	/**
	 * Factory to create a gradients objects from a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class GradientContainerFactory implements NativeObjectContainerFactory<Gradient> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public Gradient create(NativeObject nativeObject) {
			return GradientBuilder.build(nativeObject);
		}

	}

}
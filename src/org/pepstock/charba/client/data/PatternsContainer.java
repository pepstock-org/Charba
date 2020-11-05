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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.PatternBuilder;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Utility to manage the patterns inside the configuration item of dataset, setting to the specific properties of the elements.<br>
 * It stores the patterns information into a native object added to Charba configuration, on specific property names for Charba.<br>
 * The canvas object are stored into native object by the "original" property names to use to configure CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class PatternsContainer extends AbstractContainer<Pattern> {

	// static instance for the patter factory
	private static final PatternContainerFactory FACTORY = new PatternContainerFactory();

	/**
	 * Creates the object by an empty native java script object and stores the dataset instance it belongs to.
	 * 
	 * @param dataset the dataset instance it belongs to
	 */
	PatternsContainer(Dataset dataset) {
		super(dataset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjects#getFactory()
	 */
	@Override
	NativeObjectContainerFactory<Pattern> getFactory() {
		return FACTORY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.AbstractContainer#createCallback(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	AbstractCanvasObjectCallback<Pattern> createCallback(Key property) {
		return new DatasetPatternCallback(this, property);
	}

	/**
	 * Factory to create a pattern objects from a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class PatternContainerFactory implements NativeObjectContainerFactory<Pattern> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public Pattern create(NativeObject nativeObject) {
			return PatternBuilder.build(nativeObject);
		}

	}

}

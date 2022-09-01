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
package org.pepstock.charba.client.items;

import java.util.HashMap;
import java.util.Map;

/**
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartElementFactories {

	// private instance for singleton
	private static final ChartElementFactories INSTANCE = new ChartElementFactories();
	// maps with all factories
	// K = data element type, V = data element factory
	private final Map<String, ChartElementFactory<?>> factories = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private ChartElementFactories() {
		// loads elements factories OOTB of CHART.JS
		register(BarElement.FACTORY);
		register(PointElement.FACTORY);
		register(ArcElement.FACTORY);
	}

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static ChartElementFactories get() {
		return INSTANCE;
	}

	/**
	 * FIXME
	 * 
	 * @param factory
	 * @return
	 */
	public boolean register(ChartElementFactory<?> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// gets the type and
			String type = factory.getType();
			// check if consistent and not already registered
			if (type != null && !factories.containsKey(type)) {
				// adds factory
				factories.put(type, factory);
				// then returns true
				return true;
			}
		}
		// if here, factory not consistent or
		// already register, then returns false
		return false;
	}

	/**
	 * FIXME
	 * 
	 * @param item
	 * @return
	 */
	public ChartElementFactory<?> getFactory(DatasetItem item) {
		// checks if items is consistent
		if (item != null) {
			// gets and returns the factory
			return getFactory(item.getController().getDataElementType());
		}
		// if here, item not consistent
		return ChartElement.FACTORY;
	}

	/**
	 * FIXME
	 * 
	 * @param type
	 * @return
	 */
	public ChartElementFactory<?> getFactory(String type) {
		// check if consistent and registered
		if (type != null && factories.containsKey(type)) {
			// then returns factory
			return factories.get(type);
		}
		// if here, type not consistent or
		// type not register
		// then returns the undefined factory
		return ChartElement.FACTORY;
	}

}

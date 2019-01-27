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
package org.pepstock.charba.client.colors;

import java.util.List;

import org.pepstock.charba.client.colors.Pattern.PatternFactory;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * FIXME javadoc
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Patterns extends NativeObjectContainer{
	
	private final PatternFactory factory = new PatternFactory();
	
	private int count = 0;

	public Patterns() {
		super();
	}

	/**
	 */
	public void setPatterns(Key key, Pattern... patterns) {
		if (patterns != null) {
			setArrayValue(key, ArrayObject.of(patterns));
			count++;
		}
	}

	/**
	 */
	public List<Pattern> getPatterns(Key key) {
		ArrayObject array = getArrayValue(key);
		// returns the configuration creating a key.
		return ArrayListHelper.unmodifiableList(array, factory);
	}
	
	public boolean hasPatterns(Key key) {
		return has(key);
	}

	public void removePatterns(Key key) {
		// checks if there is
		if (has(key)) {
			// and then remove
			remove(key);
			count--;
		}
	}

	public boolean isEmpty() {
		return count == 0;
	}
	
	public List<Key> getKeys(){
		return keys();
	}
	

}

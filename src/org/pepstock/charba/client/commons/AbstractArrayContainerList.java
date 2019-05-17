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
package org.pepstock.charba.client.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract list for enumeration and object container list which has got common methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractArrayContainerList<E, A extends Array> extends AbstractArrayList<E, A> {

	/**
	 * Retains only the elements in this list that are contained in the specified collection.<br>
	 * In other words, removes from this list all of its elements that are not contained in the specified collection.
	 */
	@Override
	public final boolean retainAll(Collection<?> collection) {
		// set modified checking if collection is empty
		boolean modified = (collection != null) && !collection.isEmpty();
		if (modified) {
			// creates a copy of elements
			List<E> contained = new ArrayList<>();
			// scans all current elements
			for (int i = 0; i < size(); i++) {
				E value = get(i);
				// checks if not present into
				// passed collection
				if (!collection.contains(value)) {
					// adds to temporary list
					contained.add(value);
				}
			}
			// if temporary list is not empty
			if (!contained.isEmpty()) {
				// scans all elements
				for (E toRemove : contained) {
					// removes and checks if modified
					modified = modified && remove(toRemove);
				}
			}
		}
		return modified;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by
	 * the specified collection's iterator
	 */
	@Override
	public final boolean addAll(Collection<? extends E> collection) {
		// set modified
		boolean modified = collection != null && !collection.isEmpty();
		// checks if argument is consistent
		if (modified) {
			Iterator<? extends E> iter = collection.iterator();
			// scans all elements
			while (iter.hasNext()) {
				// adds and
				// sets modified
				modified = modified && add(iter.next());
			}
		}
		return modified;
	}

}
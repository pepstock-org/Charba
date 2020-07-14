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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Method[] ms = BarDataset.class.getMethods();
		Arrays.sort(ms, new Comparator<Method>() {

			@Override
			public int compare(Method o1, Method o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Method m : ms) {
			if (m.getName().startsWith("set") && Modifier.isPublic(m.getModifiers())) {
				StringBuilder sb = new StringBuilder(m.getName().substring(3));
				sb.append(" ");
				for (Parameter param : m.getParameters()) {
					sb.append(param.getType().getSimpleName()).append(" ");
				}
				System.out.println(sb);
			}
		}

	}

}

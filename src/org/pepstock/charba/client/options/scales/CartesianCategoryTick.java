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
package org.pepstock.charba.client.options.scales;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;

/**
 * 
 */
public final class CartesianCategoryTick extends CartesianTick{
	
	private enum Property implements Key {
		labels,
		min,
		max
	}
	
//	labels  Array[String]  -  An array of labels to display.
//	min  String   The minimum item to display. more...
//	max  String   The maximum item to display. more...
	
	CartesianCategoryTick() {
	}

	public void setLabels(String... labels){
		setInternalLabels(ArrayListHelper.build(labels));
	}

	public void setLabels(List<String> labels){
		if (labels instanceof JsStringArrayList){
			setInternalLabels((JsStringArrayList)labels);
		} else {
			JsStringArrayList list = new JsStringArrayList();
			list.addAll(labels);
			setInternalLabels(list);
		}
	}

	private void setInternalLabels(JsStringArrayList labels){
		setStringArray(Property.labels, labels);
	}

	public List<String> getLabels(){
		return getStringArray(Property.labels);
	}
	
	public void setMin(String min){
		  setValue(Property.min, min);
	}

	public String getMin(){
		  return getValue(Property.min, null);
	}

	public void setMax(String max){
		  setValue(Property.max, max);
	}

	public String getMax(){
		  return getValue(Property.max, null);
	}

}
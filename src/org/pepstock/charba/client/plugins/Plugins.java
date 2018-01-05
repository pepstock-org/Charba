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
package org.pepstock.charba.client.plugins;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsObjectArrayList;
import org.pepstock.charba.client.options.InvalidPluginIdException;

/**
 * 
 */
public final class Plugins {
	// chart instance
	private final AbstractChart<?, ?> chart;
	
	private final List<WrapperPlugin> plugins = new LinkedList<WrapperPlugin>();
	
	public Plugins(AbstractChart<?, ?> chart) {
		this.chart = chart;
	}

	public void add(Plugin plugin) throws InvalidPluginIdException{
		PluginIdChecker.check(plugin.getId());
		WrapperPlugin wPlugin = new WrapperPlugin(chart, plugin);	
		plugins.add(wPlugin);
	}
	
	public void remove(String id) {
		Iterator<WrapperPlugin> iter = plugins.iterator();
		while(iter.hasNext()){
			WrapperPlugin plugin = iter.next();
			if (plugin.getId().equals(id)){
				iter.remove();
			}
		}
	}

	public JsObjectArrayList<GenericJavaScriptObject> getArrayList(){
		JsObjectArrayList<GenericJavaScriptObject> list = new JsObjectArrayList<GenericJavaScriptObject>();
		for (WrapperPlugin plugin : plugins){
			list.add(plugin.getObject());
		}
		return list;
	}
	
}
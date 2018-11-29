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
package org.pepstock.charba.client.jsinterop.plugins;

import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.items.DatasetPluginItem;
import org.pepstock.charba.client.jsinterop.items.SizeItem;
import org.pepstock.charba.client.jsinterop.items.TooltipPluginItem;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Implements a puglin interface to help who will create a plugin do not create all methods.<br>
 * The only method not implemented is <code>getId</code> which must implemented.<br>
 * All <code>onBefore*</code> cancellable methods return <code>true</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType
final class NativePlugin {
	
	private final WrapperPlugin wrapper;
	
	/**
	 * @param wrapper
	 */
	NativePlugin(WrapperPlugin wrapper) {
		this.wrapper = wrapper;
	}

	@JsProperty
	public native String getId();

	@JsProperty
	public native void setId(String id);

	// init
	@JsMethod
	public void beforeInit(Chart chart, Object option){
		wrapper.onBeforeInit(chart.getCharbaId());
	}

	@JsMethod
	public void afterInit(Chart chart, Object option){
		wrapper.onAfterInit(chart.getCharbaId());
	}

	// update
	@JsMethod
	public boolean beforeUpdate(Chart chart, Object option){
		return wrapper.onBeforeUpdate(chart.getCharbaId());
	}
	@JsMethod
	public void afterUpdate(Chart chart, Object option){
		wrapper.onAfterUpdate(chart.getCharbaId());
	}
	
	// layout
	@JsMethod	
	public boolean beforeLayout(Chart chart, Object option){
		return wrapper.onBeforeLayout(chart.getCharbaId());
	}
	@JsMethod
	public void afterLayout(Chart chart, Object option){
		wrapper.onAfterLayout(chart.getCharbaId());
	}
	
	// datasets
	@JsMethod	
	public boolean beforeDatasetsUpdate(Chart chart, Object option){
		return wrapper.onBeforeDatasetsUpdate(chart.getCharbaId());
	}
	@JsMethod
	public void afterDatasetsUpdate(Chart chart, Object option){
		wrapper.onAfterDatasetsUpdate(chart.getCharbaId());
	}
	
	// dataset
	@JsMethod	
	public boolean beforeDatasetUpdate(Chart chart, NativeObject item, Object option){
		return wrapper.onBeforeDatasetUpdate(chart.getCharbaId(), new DatasetPluginItem(item));
	}
	
	@JsMethod	
	public void afterDatasetUpdate(Chart chart, NativeObject item, Object option){
		wrapper.onAfterDatasetUpdate(chart.getCharbaId(), new DatasetPluginItem(item));
	}

	// render
	@JsMethod
	public boolean beforeRender(Chart chart, Object option){
		return wrapper.onBeforeRender(chart.getCharbaId());
	}
	@JsMethod
	public void afterRender(Chart chart, Object option){
		wrapper.onAfterRender(chart.getCharbaId());
	}
	
	// draw
	@JsMethod	
	public boolean beforeDraw(Chart chart, double easing){
		return wrapper.onBeforeDraw(chart.getCharbaId(), easing);
	}
	@JsMethod
	public void afterDraw(Chart chart, double easing){
		wrapper.onAfterDraw(chart.getCharbaId(), easing);
	}

	// datasets draw
	@JsMethod	
	public boolean beforeDatasetsDraw(Chart chart, double easing){
		return wrapper.onBeforeDatasetsDraw(chart.getCharbaId(), easing);
	}
	@JsMethod
	public void afterDatasetsDraw(Chart chart, double easing){
		wrapper.onAfterDatasetsDraw(chart.getCharbaId(), easing);
	}

	// dataset draw
	@JsMethod	
	public boolean beforeDatasetDraw(Chart chart, NativeObject item, Object option){
		return wrapper.onBeforeDatasetDraw(chart.getCharbaId(), new DatasetPluginItem(item));
	}
	@JsMethod
	public void afterDatasetDraw(Chart chart, NativeObject item, Object option){
		wrapper.onAfterDatasetDraw(chart.getCharbaId(), new DatasetPluginItem(item));
	}

	// tooltip draw
	@JsMethod	
	public boolean beforeTooltipDraw(Chart chart, NativeObject item, Object option){
		return wrapper.onBeforeTooltipDraw(chart.getCharbaId(), new TooltipPluginItem(item));
	}
	@JsMethod
	public void afterTooltipDraw(Chart chart, NativeObject item, Object option){
		wrapper.onAfterTooltipDraw(chart.getCharbaId(),  new TooltipPluginItem(item));
	}

	// event
	@JsMethod	
	public boolean beforeEvent(Chart chart, ChartNativeEvent event, Object option){
		return wrapper.onBeforeEvent(chart.getCharbaId(), event);
	}
	@JsMethod
	public void afterEvent(Chart chart, ChartNativeEvent event, Object option){
		wrapper.onAfterEvent(chart.getCharbaId(), event);
	}

	// resize
	@JsMethod	
	public void resize(Chart chart, NativeObject size, Object option){
		wrapper.onResize(chart.getCharbaId(), new SizeItem(size));
	}

	// destroy
	@JsMethod	
	public void destroy(Chart chart, Object option){
		wrapper.onDestroy(chart.getCharbaId());
	}

}

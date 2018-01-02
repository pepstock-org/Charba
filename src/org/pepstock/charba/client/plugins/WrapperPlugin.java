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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipModel;

final class WrapperPlugin extends ChartContainer{
	
	private final Plugin delegation;

	/**
	 * 
	 * @param chart
	 * @param delegation
	 */
	WrapperPlugin(AbstractChart<?, ?> chart, Plugin delegation) {
		super(chart);
		this.delegation = delegation;
		registerNativePluginsHandler(getJavaScriptObject());
	}

	final String getId() {
		return delegation.getId();
	}

	protected void onBeforeInit(){
		delegation.onBeforeInit(getChart());
	}

	protected void onAfterInit(){
		delegation.onAfterInit(getChart());
	}

	protected boolean onBeforeUpdate(){
		return delegation.onBeforeUpdate(getChart());
	}

	protected void onAfterUpdate(){
		delegation.onAfterUpdate(getChart());
	}
	
	protected boolean onBeforeLayout(){
		return delegation.onBeforeLayout(getChart());
	}

	protected void onAfterLayout(){
		delegation.onAfterLayout(getChart());
	}
	
	protected boolean onBeforeDatasetsUpdate(){
		return delegation.onBeforeDatasetsUpdate(getChart());
	}

	protected void onAfterDatasetsUpdate(){
		delegation.onAfterDatasetsUpdate(getChart());
	}
	
	protected boolean onBeforeDatasetUpdate(int datasetIndex){
		return delegation.onBeforeDatasetUpdate(getChart(), datasetIndex);
	}

	protected void onAfterDatasetUpdate(int datasetIndex){
		delegation.onAfterDatasetUpdate(getChart(), datasetIndex);
	}
	
	protected boolean onBeforeRender(){
		return delegation.onBeforeRender(getChart());
	}
	
	protected void onAfterRender(){
		delegation.onAfterRender(getChart());
	}
	
	protected boolean onBeforeDraw(String easing){
		return delegation.onBeforeDraw(getChart(), Double.valueOf(easing));
	}
	
	protected void onAfterDraw(String easing){
		delegation.onAfterDraw(getChart(), Double.valueOf(easing));
	}

	protected boolean onBeforeDatasetsDraw(String easing){
		return delegation.onBeforeDatasetsDraw(getChart(), Double.valueOf(easing));
	}
	
	protected void onAfterDatasetsDraw(String easing){
		delegation.onAfterDatasetsDraw(getChart(), Double.valueOf(easing));
	}

	protected boolean onBeforeDatasetDraw(int datasetIndex, String easing){
		return delegation.onBeforeDatasetDraw(getChart(), datasetIndex, Double.valueOf(easing));
	}
	
	protected void onAfterDatasetDraw(int datasetIndex, String easing){
		delegation.onAfterDatasetDraw(getChart(), datasetIndex, Double.valueOf(easing));
	}

	protected boolean onBeforeTooltipDraw(TooltipModel model, String easing){
		return delegation.onBeforeTooltipDraw(getChart(), Double.valueOf(easing));
	}
	
	protected void onAfterTooltipDraw(TooltipModel model, String easing){
		delegation.onAfterTooltipDraw(getChart(), Double.valueOf(easing));
	}

	protected void onBeforeEvent(ChartNativeEvent event){
		delegation.onBeforeEvent(getChart(), event);
	}
	
	protected void onAfterEvent(ChartNativeEvent event){
		delegation.onAfterEvent(getChart(), event);
	}

	protected void onResize(SizeItem item){
		delegation.onResize(getChart(), item);
	}
	
	protected void onDestroy(){
		delegation.onDestroy(getChart());
	}
	
	public GenericJavaScriptObject getObject(){
		return getJavaScriptObject();
	}
	
	private native void registerNativePluginsHandler(GenericJavaScriptObject config)/*-{
	var self = this;

	// init
	config.beforeInit = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeInit()();
		return;
	}
	config.afterInit = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterInit()();
		return;
	}

	// update
	config.beforeUpdate = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeUpdate()();
	}
	config.afterUpdate = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterUpdate()();
		return;
	}
	
	// layout
	config.beforeLayout = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeLayout()();
	}
	config.afterLayout = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterLayout()();
		return;
	}
	
	// datasets
	config.beforeDatasetsUpdate = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetsUpdate()();
	}
	config.afterDatasetsUpdate = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetsUpdate()();
		return;
	}
	
	// dataset
	config.beforeDatasetUpdate = function(chart, args, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetUpdate(I)(args.index);
	}
	config.afterDatasetUpdate = function(chart, args, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetUpdate(I)(args.index);
		return;
	}

	// render
	config.beforeRender = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeRender()();
	}
	config.afterRender = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterRender()();
		return;
	}
	
	// draw
	config.beforeDraw = function(chart, easing) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDraw(Ljava/lang/String;)(easing);
	}
	config.afterDraw = function(chart, easing) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDraw(Ljava/lang/String;)(easing);
		return;
	}

	// datasets draw
	config.beforeDatasetsDraw = function(chart, easing) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetsDraw(Ljava/lang/String;)(easing);
	}
	config.afterDatasetsDraw = function(chart, easing) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetsDraw(Ljava/lang/String;)(easing);
		return;
	}

	// dataset draw
	config.beforeDatasetDraw = function(chart, args, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetDraw(ILjava/lang/String;)(args.index, args.easingValue);
	}
	config.afterDatasetDraw = function(chart, args, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetDraw(ILjava/lang/String;)(args.index, args.easingValue);
		return;
	}

	// tooltip draw
	config.beforeTooltipDraw = function(chart, args, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeTooltipDraw(Lorg/pepstock/charba/client/items/TooltipModel;Ljava/lang/String;)(args.tooltip._model, args.easingValue);
	}
	config.afterTooltipDraw = function(chart, args, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterTooltipDraw(Lorg/pepstock/charba/client/items/TooltipModel;Ljava/lang/String;)(args.tooltip._model, args.easingValue);
		return;
	}

	// event
	config.beforeEvent = function(chart, event, option) {
		// uses the syntax ["native"] because . syntaz is not accepted 
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeEvent(Lorg/pepstock/charba/client/events/ChartNativeEvent;)(event["native"]);
		return;
	}
	config.afterEvent = function(chart, event, option) {
		// uses the syntax ["native"] because . syntaz is not accepted
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterEvent(Lorg/pepstock/charba/client/events/ChartNativeEvent;)(event["native"]);
		return;
	}

	// resize
	config.resize = function(chart, size, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onResize(Lorg/pepstock/charba/client/items/SizeItem;)(size);
		return;
	}


	// destroy
	config.destroy = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onDestroy()();
		return;
	}


	}-*/;

}

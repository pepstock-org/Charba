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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.PluginDatasetArgument;
import org.pepstock.charba.client.items.PluginEventArgument;
import org.pepstock.charba.client.items.PluginResizeArgument;
import org.pepstock.charba.client.items.PluginScaleArgument;
import org.pepstock.charba.client.items.PluginTooltipArgument;
import org.pepstock.charba.client.items.PluginUpdateArgument;
import org.pepstock.charba.client.plugins.hooks.AfterBuildTicksHook;
import org.pepstock.charba.client.plugins.hooks.AfterDataLimitsHook;
import org.pepstock.charba.client.plugins.hooks.AfterDatasetDrawHook;
import org.pepstock.charba.client.plugins.hooks.AfterDatasetUpdateHook;
import org.pepstock.charba.client.plugins.hooks.AfterDatasetsDrawHook;
import org.pepstock.charba.client.plugins.hooks.AfterDatasetsUpdateHook;
import org.pepstock.charba.client.plugins.hooks.AfterDestroyHook;
import org.pepstock.charba.client.plugins.hooks.AfterDrawHook;
import org.pepstock.charba.client.plugins.hooks.AfterEventHook;
import org.pepstock.charba.client.plugins.hooks.AfterInitHook;
import org.pepstock.charba.client.plugins.hooks.AfterLayoutHook;
import org.pepstock.charba.client.plugins.hooks.AfterRenderHook;
import org.pepstock.charba.client.plugins.hooks.AfterTooltipDrawHook;
import org.pepstock.charba.client.plugins.hooks.AfterUpdateHook;
import org.pepstock.charba.client.plugins.hooks.BeforeBuildTicksHook;
import org.pepstock.charba.client.plugins.hooks.BeforeDataLimitsHook;
import org.pepstock.charba.client.plugins.hooks.BeforeDatasetDrawHook;
import org.pepstock.charba.client.plugins.hooks.BeforeDatasetUpdateHook;
import org.pepstock.charba.client.plugins.hooks.BeforeDatasetsDrawHook;
import org.pepstock.charba.client.plugins.hooks.BeforeDatasetsUpdateHook;
import org.pepstock.charba.client.plugins.hooks.BeforeDestroyHook;
import org.pepstock.charba.client.plugins.hooks.BeforeDrawHook;
import org.pepstock.charba.client.plugins.hooks.BeforeElementsUpdateHook;
import org.pepstock.charba.client.plugins.hooks.BeforeEventHook;
import org.pepstock.charba.client.plugins.hooks.BeforeInitHook;
import org.pepstock.charba.client.plugins.hooks.BeforeLayoutHook;
import org.pepstock.charba.client.plugins.hooks.BeforeRenderHook;
import org.pepstock.charba.client.plugins.hooks.BeforeTooltipDrawHook;
import org.pepstock.charba.client.plugins.hooks.BeforeUpdateHook;
import org.pepstock.charba.client.plugins.hooks.ConfigureHook;
import org.pepstock.charba.client.plugins.hooks.InstallHook;
import org.pepstock.charba.client.plugins.hooks.ResetHook;
import org.pepstock.charba.client.plugins.hooks.ResizeHook;
import org.pepstock.charba.client.plugins.hooks.StartHook;
import org.pepstock.charba.client.plugins.hooks.StopHook;
import org.pepstock.charba.client.plugins.hooks.UninstallHook;

/**
 * Wraps a plugin, delegating the execution of all hooks to it.<br>
 * the needed hooks can be added and CHART.JS will invoke only those hooks, improving the drawing performance.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class SmartPlugin extends AbstractBasePlugin {

	// hook instance for configure
	private ConfigureHook configureHook = null;
	// hook instance for beforeInit
	private BeforeInitHook beforeInitHook = null;
	// hook instance for afterInit
	private AfterInitHook afterInitHook = null;
	// hook instance for beforeUpdate
	private BeforeUpdateHook beforeUpdateHook = null;
	// hook instance for afterUpdate
	private AfterUpdateHook afterUpdateHook = null;
	// hook instance for beforeElementsUpdate
	private BeforeElementsUpdateHook beforeElementsUpdateHook = null;
	// hook instance for beforeLayout
	private BeforeLayoutHook beforeLayoutHook = null;
	// hook instance for afterLayout
	private AfterLayoutHook afterLayoutHook = null;
	// hook instance for beforeDatasetsUpdate
	private BeforeDatasetsUpdateHook beforeDatasetsUpdateHook = null;
	// hook instance for afterDatasetsUpdate
	private AfterDatasetsUpdateHook afterDatasetsUpdateHook = null;
	// hook instance for beforeDatasetUpdate
	private BeforeDatasetUpdateHook beforeDatasetUpdateHook = null;
	// hook instance for afterDatasetUpdate
	private AfterDatasetUpdateHook afterDatasetUpdateHook = null;
	// hook instance for beforeRender
	private BeforeRenderHook beforeRenderHook = null;
	// hook instance for afterRender
	private AfterRenderHook afterRenderHook = null;
	// hook instance for beforeDraw
	private BeforeDrawHook beforeDrawHook = null;
	// hook instance for afterDraw
	private AfterDrawHook afterDrawHook = null;
	// hook instance for beforeDatasetsDraw
	private BeforeDatasetsDrawHook beforeDatasetsDrawHook = null;
	// hook instance for afterDatasetsDraw
	private AfterDatasetsDrawHook afterDatasetsDrawHook = null;
	// hook instance for beforeDatasetDraw
	private BeforeDatasetDrawHook beforeDatasetDrawHook = null;
	// hook instance for afterDatasetDraw
	private AfterDatasetDrawHook afterDatasetDrawHook = null;
	// hook instance for beforeTooltipDraw
	private BeforeTooltipDrawHook beforeTooltipDrawHook = null;
	// hook instance for afterTooltipDraw
	private AfterTooltipDrawHook afterTooltipDrawHook = null;
	// hook instance for beforeEvent
	private BeforeEventHook beforeEventHook = null;
	// hook instance for afterEvent
	private AfterEventHook afterEventHook = null;
	// hook instance for resize
	private ResizeHook resizeHook = null;
	// hook instance for reset
	private ResetHook resetHook = null;
	// hook instance for beforeDestroy
	private BeforeDestroyHook beforeDestroyHook = null;
	// hook instance for afterDestroy
	private AfterDestroyHook afterDestroyHook = null;
	// hook instance for install
	private InstallHook installHook = null;
	// hook instance for start
	private StartHook startHook = null;
	// hook instance for stop
	private StopHook stopHook = null;
	// hook instance for uninstall
	private UninstallHook uninstallHook = null;
	// hook instance for beforeDataLimits
	private BeforeDataLimitsHook beforeDataLimitsHook = null;
	// hook instance for afterDataLimits
	private AfterDataLimitsHook afterDataLimitsHook = null;
	// hook instance for beforeBuildTicks
	private BeforeBuildTicksHook beforeBuildTicksHook = null;
	// hook instance for afterBuildTicks
	private AfterBuildTicksHook afterBuildTicksHook = null;

	/**
	 * Builds the object with plugin id
	 * 
	 * @param id plugin instance
	 */
	public SmartPlugin(String id) {
		super(id);
	}

	// ----------------------------
	// -- SETTER/GETTER ---
	// ----------------------------
	/**
	 * Called before initializing configuration of 'chart'.
	 * 
	 * @param configureHook the user hook for 'configure' plugin
	 */
	public final void setConfigureHook(ConfigureHook configureHook) {
		// sets the hook
		this.configureHook = configureHook;
	}

	/**
	 * Called before initializing configuration of 'chart'.
	 * 
	 * @return the user hook for 'configure' plugin
	 */
	public final ConfigureHook getConfigureHook() {
		return configureHook;
	}

	/**
	 * Sets the user hook for 'beforeInit' plugin.<br>
	 * Called before initializing 'chart'.
	 * 
	 * @param beforeInitHook the user hook for 'beforeInit' plugin
	 */
	public final void setBeforeInitHook(BeforeInitHook beforeInitHook) {
		// sets the hook
		this.beforeInitHook = beforeInitHook;
		// stores and manages hook
		setHook(Property.BEFORE_INIT, beforeInitHook, getBeforeInitCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeInit' plugin.<br>
	 * Called before initializing 'chart'.
	 * 
	 * @param nativeBeforeInitHook the user hook for 'beforeInit' plugin
	 */
	public final void setBeforeInitHook(NativeHook nativeBeforeInitHook) {
		// resets the hook
		setBeforeInitHook((BeforeInitHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_INIT, nativeBeforeInitHook);
	}

	/**
	 * Returns the user hook for 'beforeInit' plugin.<br>
	 * Called before initializing 'chart'.
	 * 
	 * @return the user hook for 'beforeInit' plugin
	 */
	public final BeforeInitHook getBeforeInitHook() {
		return beforeInitHook;
	}

	/**
	 * Sets the user hook for 'afterInit' plugin.<br>
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param afterInitHook the user hook for 'afterInit' plugin
	 */
	public final void setAfterInitHook(AfterInitHook afterInitHook) {
		// sets the hook
		this.afterInitHook = afterInitHook;
		// stores and manages hook
		setHook(Property.AFTER_INIT, afterInitHook, getAfterInitCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterInit' plugin.<br>
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param nativeAfterInitHook the user hook for 'afterInit' plugin
	 */
	public final void setAfterInitHook(NativeHook nativeAfterInitHook) {
		// resets the hook
		setAfterInitHook((AfterInitHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_INIT, nativeAfterInitHook);
	}

	/**
	 * Returns the user hook for 'afterInit' plugin.<br>
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @return the user hook for 'afterInit' plugin
	 */
	public final AfterInitHook getAfterInitHook() {
		return afterInitHook;
	}

	/**
	 * Sets the user hook for 'beforeUpdate' plugin.<br>
	 * Called before updating 'chart'.<br>
	 * If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
	 * 
	 * @param beforeUpdateHook the user hook for 'beforeUpdate' plugin
	 */
	public final void setBeforeUpdateHook(BeforeUpdateHook beforeUpdateHook) {
		// sets the hook
		this.beforeUpdateHook = beforeUpdateHook;
		// stores and manages hook
		setHook(Property.BEFORE_UPDATE, beforeUpdateHook, getBeforeUpdateCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeUpdate' plugin.<br>
	 * Called before updating 'chart'.<br>
	 * If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
	 * 
	 * @param nativeBeforeUpdateHook the user hook for 'beforeUpdate' plugin
	 */
	public final void setBeforeUpdateHook(NativeHook nativeBeforeUpdateHook) {
		// resets the hook
		setBeforeUpdateHook((BeforeUpdateHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_UPDATE, nativeBeforeUpdateHook);
	}

	/**
	 * Returns the user hook for 'beforeUpdate' plugin.<br>
	 * Called before updating 'chart'.<br>
	 * If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
	 * 
	 * @return the user hook for 'beforeUpdate' plugin
	 */
	public final BeforeUpdateHook getBeforeUpdateHook() {
		return beforeUpdateHook;
	}

	/**
	 * Sets the user hook for 'afterUpdate' plugin.<br>
	 * Called after 'chart' has been updated and before rendering.<br>
	 * Note that this hook will not be called if the chart update has been previously cancelled.
	 * 
	 * @param afterUpdateHook the user hook for 'afterUpdate' plugin
	 */
	public final void setAfterUpdateHook(AfterUpdateHook afterUpdateHook) {
		// sets the hook
		this.afterUpdateHook = afterUpdateHook;
		// stores and manages hook
		setHook(Property.AFTER_UPDATE, afterUpdateHook, getAfterUpdateCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterUpdate' plugin.<br>
	 * Called after 'chart' has been updated and before rendering.<br>
	 * Note that this hook will not be called if the chart update has been previously cancelled.
	 * 
	 * @param nativeAfterUpdateHook the user hook for 'afterUpdate' plugin
	 */
	public final void setAfterUpdateHook(NativeHook nativeAfterUpdateHook) {
		// resets the hook
		setAfterUpdateHook((AfterUpdateHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_UPDATE, nativeAfterUpdateHook);
	}

	/**
	 * Returns the user hook for 'afterUpdate' plugin.<br>
	 * Called after 'chart' has been updated and before rendering.<br>
	 * Note that this hook will not be called if the chart update has been previously cancelled.
	 * 
	 * @return the user hook for 'afterUpdate' plugin
	 */
	public final AfterUpdateHook getAfterUpdateHook() {
		return afterUpdateHook;
	}

	/**
	 * Sets the user hook for 'beforeElementsUpdate' plugin.<br>
	 * Called during the update process, before any chart elements have been created.
	 * 
	 * @param beforeElementsUpdateHook the user hook for 'beforeElementsUpdate' plugin
	 */
	public final void setBeforeElementsUpdateHook(BeforeElementsUpdateHook beforeElementsUpdateHook) {
		// sets the hook
		this.beforeElementsUpdateHook = beforeElementsUpdateHook;
		// stores and manages hook
		setHook(Property.BEFORE_ELEMENTS_UPDATE, beforeElementsUpdateHook, getBeforeElementsUpdateCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeElementsUpdate' plugin.<br>
	 * Called during the update process, before any chart elements have been created.
	 * 
	 * @param nativeBeforeElementsUpdateHook the user hook for 'beforeElementsUpdate' plugin
	 */
	public final void setBeforeElementsUpdateHook(NativeHook nativeBeforeElementsUpdateHook) {
		// resets the hook
		setBeforeElementsUpdateHook((BeforeElementsUpdateHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_ELEMENTS_UPDATE, nativeBeforeElementsUpdateHook);
	}

	/**
	 * Returns the user hook for 'beforeElementsUpdate' plugin.<br>
	 * Called during the update process, before any chart elements have been created.
	 * 
	 * @return the user hook for 'beforeElementsUpdate' plugin
	 */
	public final BeforeElementsUpdateHook getBeforeElementsUpdateHook() {
		return beforeElementsUpdateHook;
	}

	/**
	 * Sets the user hook for 'beforeLayout' plugin.<br>
	 * Called before laying out 'chart'.<br>
	 * If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
	 * 
	 * @param beforeLayoutHook the user hook for 'beforeLayout' plugin
	 */
	public final void setBeforeLayoutHook(BeforeLayoutHook beforeLayoutHook) {
		// sets the hook
		this.beforeLayoutHook = beforeLayoutHook;
		// stores and manages hook
		setHook(Property.BEFORE_LAYOUT, beforeLayoutHook, getBeforeLayoutCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeLayout' plugin.<br>
	 * Called before laying out 'chart'.<br>
	 * If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
	 * 
	 * @param nativeBeforeLayoutHook the user hook for 'beforeLayout' plugin
	 */
	public final void setBeforeLayoutHook(NativeHook nativeBeforeLayoutHook) {
		// resets the hook
		setBeforeLayoutHook((BeforeLayoutHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_LAYOUT, nativeBeforeLayoutHook);
	}

	/**
	 * Returns the user hook for 'beforeLayout' plugin.<br>
	 * Called before laying out 'chart'.<br>
	 * If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
	 * 
	 * @return the user hook for 'beforeLayout' plugin
	 */
	public final BeforeLayoutHook getBeforeLayoutHook() {
		return beforeLayoutHook;
	}

	/**
	 * Sets the user hook for 'afterLayout' plugin.<br>
	 * Called after the 'chart' has been layed out.<br>
	 * Note that this hook will not be called if the layout update has been previously cancelled.
	 * 
	 * @param afterLayoutHook the user hook for 'afterLayout' plugin
	 */
	public final void setAfterLayoutHook(AfterLayoutHook afterLayoutHook) {
		// sets the hook
		this.afterLayoutHook = afterLayoutHook;
		// stores and manages hook
		setHook(Property.AFTER_LAYOUT, afterLayoutHook, getAfterLayoutCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterLayout' plugin.<br>
	 * Called after the 'chart' has been layed out.<br>
	 * Note that this hook will not be called if the layout update has been previously cancelled.
	 * 
	 * @param nativeAfterLayoutHook the user hook for 'afterLayout' plugin
	 */
	public final void setAfterLayoutHook(NativeHook nativeAfterLayoutHook) {
		// resets the hook
		setAfterLayoutHook((AfterLayoutHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_LAYOUT, nativeAfterLayoutHook);
	}

	/**
	 * Returns the user hook for 'afterLayout' plugin.<br>
	 * Called after the 'chart' has been layed out.<br>
	 * Note that this hook will not be called if the layout update has been previously cancelled.
	 * 
	 * @return the user hook for 'afterLayout' plugin
	 */
	public final AfterLayoutHook getAfterLayoutHook() {
		return afterLayoutHook;
	}

	/**
	 * Sets the user hook for 'beforeDatasetsUpdate' plugin.<br>
	 * Called before updating the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param beforeDatasetsUpdateHook the user hook for 'beforeDatasetsUpdate' plugin
	 */
	public final void setBeforeDatasetsUpdateHook(BeforeDatasetsUpdateHook beforeDatasetsUpdateHook) {
		// sets the hook
		this.beforeDatasetsUpdateHook = beforeDatasetsUpdateHook;
		// stores and manages hook
		setHook(Property.BEFORE_DATASETS_UPDATE, beforeDatasetsUpdateHook, getBeforeDatasetsUpdateCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeDatasetsUpdate' plugin.<br>
	 * Called before updating the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param nativeBeforeDatasetsUpdateHook the user hook for 'beforeDatasetsUpdate' plugin
	 */
	public final void setBeforeDatasetsUpdateHook(NativeHook nativeBeforeDatasetsUpdateHook) {
		// resets the hook
		setBeforeDatasetsUpdateHook((BeforeDatasetsUpdateHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_DATASETS_UPDATE, nativeBeforeDatasetsUpdateHook);
	}

	/**
	 * Returns the user hook for 'beforeDatasetsUpdate' plugin.<br>
	 * Called before updating the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @return the user hook for 'beforeDatasetsUpdate' plugin
	 */
	public final BeforeDatasetsUpdateHook getBeforeDatasetsUpdateHook() {
		return beforeDatasetsUpdateHook;
	}

	/**
	 * Sets the user hook for 'afterDatasetsUpdate' plugin.<br>
	 * Called after the 'chart' datasets have been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param afterDatasetsUpdateHook the user hook for 'afterDatasetsUpdate' plugin
	 */
	public final void setAfterDatasetsUpdateHook(AfterDatasetsUpdateHook afterDatasetsUpdateHook) {
		// sets the hook
		this.afterDatasetsUpdateHook = afterDatasetsUpdateHook;
		// stores and manages hook
		setHook(Property.AFTER_DATASETS_UPDATE, afterDatasetsUpdateHook, getAfterDatasetsUpdateCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterDatasetsUpdate' plugin.<br>
	 * Called after the 'chart' datasets have been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param nativeAfterDatasetsUpdateHook the user hook for 'afterDatasetsUpdate' plugin
	 */
	public final void setAfterDatasetsUpdateHook(NativeHook nativeAfterDatasetsUpdateHook) {
		// resets the hook
		setAfterDatasetsUpdateHook((AfterDatasetsUpdateHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_DATASETS_UPDATE, nativeAfterDatasetsUpdateHook);
	}

	/**
	 * Returns the user hook for 'afterDatasetsUpdate' plugin.<br>
	 * Called after the 'chart' datasets have been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @return the user hook for 'afterDatasetsUpdate' plugin
	 */
	public final AfterDatasetsUpdateHook getAfterDatasetsUpdateHook() {
		return afterDatasetsUpdateHook;
	}

	/**
	 * Sets the user hook for 'beforeDatasetUpdate' plugin.<br>
	 * Called before updating the 'chart' dataset at the given 'args.index'.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param beforeDatasetUpdateHook the user hook for 'beforeDatasetUpdate' plugin
	 */
	public final void setBeforeDatasetUpdateHook(BeforeDatasetUpdateHook beforeDatasetUpdateHook) {
		// sets the hook
		this.beforeDatasetUpdateHook = beforeDatasetUpdateHook;
		// stores and manages hook
		setHook(Property.BEFORE_DATASET_UPDATE, beforeDatasetUpdateHook, getBeforeDatasetUpdateCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeDatasetUpdate' plugin.<br>
	 * Called before updating the 'chart' dataset at the given 'args.index'.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param nativeBeforeDatasetUpdateHook the user hook for 'beforeDatasetUpdate' plugin
	 */
	public final void setBeforeDatasetUpdateHook(NativeHook nativeBeforeDatasetUpdateHook) {
		// resets the hook
		setBeforeDatasetUpdateHook((BeforeDatasetUpdateHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_DATASET_UPDATE, nativeBeforeDatasetUpdateHook);
	}

	/**
	 * Returns the user hook for 'beforeDatasetUpdate' plugin.<br>
	 * Called before updating the 'chart' dataset at the given 'args.index'.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @return the user hook for 'beforeDatasetUpdate' plugin
	 */
	public final BeforeDatasetUpdateHook getBeforeDatasetUpdateHook() {
		return beforeDatasetUpdateHook;
	}

	/**
	 * Sets the user hook for 'afterDatasetUpdate' plugin.<br>
	 * Called after the 'chart' datasets at the given 'args.index' has been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param afterDatasetUpdateHook the user hook for 'afterDatasetUpdate' plugin
	 */
	public final void setAfterDatasetUpdateHook(AfterDatasetUpdateHook afterDatasetUpdateHook) {
		// sets the hook
		this.afterDatasetUpdateHook = afterDatasetUpdateHook;
		// stores and manages hook
		setHook(Property.AFTER_DATASET_UPDATE, afterDatasetUpdateHook, getAfterDatasetUpdateCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterDatasetUpdate' plugin.<br>
	 * Called after the 'chart' datasets at the given 'args.index' has been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param nativeAfterDatasetUpdateHook the user hook for 'afterDatasetUpdate' plugin
	 */
	public final void setAfterDatasetUpdateHook(NativeHook nativeAfterDatasetUpdateHook) {
		// resets the hook
		setAfterDatasetUpdateHook((AfterDatasetUpdateHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_DATASET_UPDATE, nativeAfterDatasetUpdateHook);
	}

	/**
	 * Returns the user hook for 'afterDatasetUpdate' plugin.<br>
	 * Called after the 'chart' datasets at the given 'args.index' has been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @return the user hook for 'afterDatasetUpdate' plugin
	 */
	public final AfterDatasetUpdateHook getAfterDatasetUpdateHook() {
		return afterDatasetUpdateHook;
	}

	/**
	 * Sets the user hook for 'beforeRender' plugin.<br>
	 * Called before rendering 'chart'. <br>
	 * If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
	 * 
	 * @param beforeRenderHook the user hook for 'beforeRender' plugin
	 */
	public final void setBeforeRenderHook(BeforeRenderHook beforeRenderHook) {
		// sets the hook
		this.beforeRenderHook = beforeRenderHook;
		// stores and manages hook
		setHook(Property.BEFORE_RENDER, beforeRenderHook, getBeforeRenderCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeRender' plugin.<br>
	 * Called before rendering 'chart'. <br>
	 * If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
	 * 
	 * @param nativeBeforeRenderHook the user hook for 'beforeRender' plugin
	 */
	public final void setBeforeRenderHook(NativeHook nativeBeforeRenderHook) {
		// resets the hook
		setBeforeRenderHook((BeforeRenderHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_RENDER, nativeBeforeRenderHook);
	}

	/**
	 * Returns the user hook for 'beforeRender' plugin.<br>
	 * Called before rendering 'chart'. <br>
	 * If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
	 * 
	 * @return the user hook for 'beforeRender' plugin
	 */
	public final BeforeRenderHook getBeforeRenderHook() {
		return beforeRenderHook;
	}

	/**
	 * Sets the user hook for 'afterRender' plugin.<br>
	 * Called after the 'chart' has been fully rendered (and animation completed).<br>
	 * Note that this hook will not be called if the rendering has been previously cancelled.
	 * 
	 * @param afterRenderHook the user hook for 'afterRender' plugin
	 */
	public final void setAfterRenderHook(AfterRenderHook afterRenderHook) {
		// sets the hook
		this.afterRenderHook = afterRenderHook;
		// stores and manages hook
		setHook(Property.AFTER_RENDER, afterRenderHook, getAfterRenderCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterRender' plugin.<br>
	 * Called after the 'chart' has been fully rendered (and animation completed).<br>
	 * Note that this hook will not be called if the rendering has been previously cancelled.
	 * 
	 * @param nativeAfterRenderHook the user hook for 'afterRender' plugin
	 */
	public final void setAfterRenderHook(NativeHook nativeAfterRenderHook) {
		// resets the hook
		setAfterRenderHook((AfterRenderHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_RENDER, nativeAfterRenderHook);
	}

	/**
	 * Returns the user hook for 'afterRender' plugin.<br>
	 * Called after the 'chart' has been fully rendered (and animation completed).<br>
	 * Note that this hook will not be called if the rendering has been previously cancelled.
	 * 
	 * @return the user hook for 'afterRender' plugin
	 */
	public final AfterRenderHook getAfterRenderHook() {
		return afterRenderHook;
	}

	/**
	 * Sets the user hook for 'beforeDraw' plugin.<br>
	 * Called before drawing 'chart' at every animation frame.<br>
	 * If any plugin returns <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param beforeDrawHook the user hook for 'beforeDraw' plugin
	 */
	public final void setBeforeDrawHook(BeforeDrawHook beforeDrawHook) {
		// sets the hook
		this.beforeDrawHook = beforeDrawHook;
		// stores and manages hook
		setHook(Property.BEFORE_DRAW, beforeDrawHook, getBeforeDrawCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeDraw' plugin.<br>
	 * Called before drawing 'chart' at every animation frame.<br>
	 * If any plugin returns <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param nativeBeforeDrawHook the user hook for 'beforeDraw' plugin
	 */
	public final void setBeforeDrawHook(NativeHook nativeBeforeDrawHook) {
		// resets the hook
		setBeforeDrawHook((BeforeDrawHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_DRAW, nativeBeforeDrawHook);
	}

	/**
	 * Returns the user hook for 'beforeDraw' plugin.<br>
	 * Called before drawing 'chart' at every animation frame.<br>
	 * If any plugin returns <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @return the user hook for 'beforeDraw' plugin
	 */
	public final BeforeDrawHook getBeforeDrawHook() {
		return beforeDrawHook;
	}

	/**
	 * Sets the user hook for 'afterDraw' plugin.<br>
	 * Called after the 'chart' has been drawn.<br>
	 * Note that this hook will not be called if the drawing has been previously cancelled.
	 * 
	 * @param afterDrawHook the user hook for 'afterDraw' plugin
	 */
	public final void setAfterDrawHook(AfterDrawHook afterDrawHook) {
		// sets the hook
		this.afterDrawHook = afterDrawHook;
		// stores and manages hook
		setHook(Property.AFTER_DRAW, afterDrawHook, getAfterDrawCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterDraw' plugin.<br>
	 * Called after the 'chart' has been drawn.<br>
	 * Note that this hook will not be called if the drawing has been previously cancelled.
	 * 
	 * @param nativeAfterDrawHook the user hook for 'afterDraw' plugin
	 */
	public final void setAfterDrawHook(NativeHook nativeAfterDrawHook) {
		// resets the hook
		setAfterDrawHook((AfterDrawHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_DRAW, nativeAfterDrawHook);
	}

	/**
	 * Returns the user hook for 'afterDraw' plugin.<br>
	 * Called after the 'chart' has been drawn.<br>
	 * Note that this hook will not be called if the drawing has been previously cancelled.
	 * 
	 * @return the user hook for 'afterDraw' plugin
	 */
	public final AfterDrawHook getAfterDrawHook() {
		return afterDrawHook;
	}

	/**
	 * Sets the user hook for 'beforeDatasetsDraw' plugin.<br>
	 * Called before drawing the 'chart' datasets. <br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param beforeDatasetsDrawHook the user hook for 'beforeDatasetsDraw' plugin
	 */
	public final void setBeforeDatasetsDrawHook(BeforeDatasetsDrawHook beforeDatasetsDrawHook) {
		// sets the hook
		this.beforeDatasetsDrawHook = beforeDatasetsDrawHook;
		// stores and manages hook
		setHook(Property.BEFORE_DATASETS_DRAW, beforeDatasetsDrawHook, getBeforeDatasetsDrawCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeDatasetsDraw' plugin.<br>
	 * Called before drawing the 'chart' datasets. <br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param nativeBeforeDatasetsDrawHook the user hook for 'beforeDatasetsDraw' plugin
	 */
	public final void setBeforeDatasetsDrawHook(NativeHook nativeBeforeDatasetsDrawHook) {
		// resets the hook
		setBeforeDatasetsDrawHook((BeforeDatasetsDrawHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_DATASETS_DRAW, nativeBeforeDatasetsDrawHook);
	}

	/**
	 * Returns the user hook for 'beforeDatasetsDraw' plugin.<br>
	 * Called before drawing the 'chart' datasets. <br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @return the user hook for 'beforeDatasetsDraw' plugin
	 */
	public final BeforeDatasetsDrawHook getBeforeDatasetsDrawHook() {
		return beforeDatasetsDrawHook;
	}

	/**
	 * Sets the user hook for 'afterDatasetsDraw' plugin.<br>
	 * Called after the 'chart' datasets have been drawn.<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param afterDatasetsDrawHook the user hook for 'afterDatasetsDraw' plugin
	 */
	public final void setAfterDatasetsDrawHook(AfterDatasetsDrawHook afterDatasetsDrawHook) {
		// sets the hook
		this.afterDatasetsDrawHook = afterDatasetsDrawHook;
		// stores and manages hook
		setHook(Property.AFTER_DATASETS_DRAW, afterDatasetsDrawHook, getAfterDatasetsDrawCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterDatasetsDraw' plugin.<br>
	 * Called after the 'chart' datasets have been drawn.<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param nativeAfterDatasetsDrawHook the user hook for 'afterDatasetsDraw' plugin
	 */
	public final void setAfterDatasetsDrawHook(NativeHook nativeAfterDatasetsDrawHook) {
		// resets the hook
		setAfterDatasetsDrawHook((AfterDatasetsDrawHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_DATASETS_DRAW, nativeAfterDatasetsDrawHook);
	}

	/**
	 * Returns the user hook for 'afterDatasetsDraw' plugin.<br>
	 * Called after the 'chart' datasets have been drawn.<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @return the user hook for 'afterDatasetsDraw' plugin
	 */
	public final AfterDatasetsDrawHook getAfterDatasetsDrawHook() {
		return afterDatasetsDrawHook;
	}

	/**
	 * Sets the user hook for 'beforeDatasetDraw' plugin.<br>
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order).<br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param beforeDatasetDrawHook the user hook for 'beforeDatasetDraw' plugin
	 */
	public final void setBeforeDatasetDrawHook(BeforeDatasetDrawHook beforeDatasetDrawHook) {
		// sets the hook
		this.beforeDatasetDrawHook = beforeDatasetDrawHook;
		// stores and manages hook
		setHook(Property.BEFORE_DATASET_DRAW, beforeDatasetDrawHook, getBeforeDatasetDrawCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeDatasetDraw' plugin.<br>
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order).<br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param nativeBeforeDatasetDrawHook the user hook for 'beforeDatasetDraw' plugin
	 */
	public final void setBeforeDatasetDrawHook(NativeHook nativeBeforeDatasetDrawHook) {
		// resets the hook
		setBeforeDatasetDrawHook((BeforeDatasetDrawHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_DATASET_DRAW, nativeBeforeDatasetDrawHook);
	}

	/**
	 * Returns the user hook for 'beforeDatasetDraw' plugin.<br>
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order).<br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @return the user hook for 'beforeDatasetDraw' plugin
	 */
	public final BeforeDatasetDrawHook getBeforeDatasetDrawHook() {
		return beforeDatasetDrawHook;
	}

	/**
	 * Sets the user hook for 'afterDatasetDraw' plugin.<br>
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param afterDatasetDrawHook the user hook for 'afterDatasetDraw' plugin
	 */
	public final void setAfterDatasetDrawHook(AfterDatasetDrawHook afterDatasetDrawHook) {
		// sets the hook
		this.afterDatasetDrawHook = afterDatasetDrawHook;
		// stores and manages hook
		setHook(Property.AFTER_DATASET_DRAW, afterDatasetDrawHook, getAfterDatasetDrawCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterDatasetDraw' plugin.<br>
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param nativeAfterDatasetDrawHook the user hook for 'afterDatasetDraw' plugin
	 */
	public final void setAfterDatasetDrawHook(NativeHook nativeAfterDatasetDrawHook) {
		// resets the hook
		setAfterDatasetDrawHook((AfterDatasetDrawHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_DATASET_DRAW, nativeAfterDatasetDrawHook);
	}

	/**
	 * Returns the user hook for 'afterDatasetDraw' plugin.<br>
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @return the user hook for 'afterDatasetDraw' plugin
	 */
	public final AfterDatasetDrawHook getAfterDatasetDrawHook() {
		return afterDatasetDrawHook;
	}

	/**
	 * Sets the user hook for 'beforeTooltipDraw' plugin.<br>
	 * Called before drawing the 'tooltip'.<br>
	 * If any plugin returns <code>false</code>, the tooltip drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param beforeTooltipDrawHook the user hook for 'beforeTooltipDraw' plugin
	 */
	public final void setBeforeTooltipDrawHook(BeforeTooltipDrawHook beforeTooltipDrawHook) {
		// sets the hook
		this.beforeTooltipDrawHook = beforeTooltipDrawHook;
		// stores and manages hook
		setHook(Property.BEFORE_TOOLTIP_DRAW, beforeTooltipDrawHook, getBeforeTooltipDrawCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeTooltipDraw' plugin.<br>
	 * Called before drawing the 'tooltip'.<br>
	 * If any plugin returns <code>false</code>, the tooltip drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param nativeBeforeTooltipDrawHook the user hook for 'beforeTooltipDraw' plugin
	 */
	public final void setBeforeTooltipDrawHook(NativeHook nativeBeforeTooltipDrawHook) {
		// resets the hook
		setBeforeTooltipDrawHook((BeforeTooltipDrawHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_TOOLTIP_DRAW, nativeBeforeTooltipDrawHook);
	}

	/**
	 * Returns the user hook for 'beforeTooltipDraw' plugin.<br>
	 * Called before drawing the 'tooltip'.<br>
	 * If any plugin returns <code>false</code>, the tooltip drawing is cancelled until another 'render' is triggered.
	 * 
	 * @return the user hook for 'beforeTooltipDraw' plugin
	 */
	public final BeforeTooltipDrawHook getBeforeTooltipDrawHook() {
		return beforeTooltipDrawHook;
	}

	/**
	 * Sets the user hook for 'afterTooltipDraw' plugin.<br>
	 * Called after drawing the 'tooltip'.<br>
	 * Note that this hook will not be called if the tooltip drawing has been previously cancelled.
	 * 
	 * @param afterTooltipDrawHook the user hook for 'afterTooltipDraw' plugin
	 */
	public final void setAfterTooltipDrawHook(AfterTooltipDrawHook afterTooltipDrawHook) {
		// sets the hook
		this.afterTooltipDrawHook = afterTooltipDrawHook;
		// stores and manages hook
		setHook(Property.AFTER_TOOLTIP_DRAW, afterTooltipDrawHook, getAfterTooltipDrawCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterTooltipDraw' plugin.<br>
	 * Called after drawing the 'tooltip'.<br>
	 * Note that this hook will not be called if the tooltip drawing has been previously cancelled.
	 * 
	 * @param nativeAfterTooltipDrawHook the user hook for 'afterTooltipDraw' plugin
	 */
	public final void setAfterTooltipDrawHook(NativeHook nativeAfterTooltipDrawHook) {
		// resets the hook
		setAfterTooltipDrawHook((AfterTooltipDrawHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_TOOLTIP_DRAW, nativeAfterTooltipDrawHook);
	}

	/**
	 * Returns the user hook for 'afterTooltipDraw' plugin.<br>
	 * Called after drawing the 'tooltip'.<br>
	 * Note that this hook will not be called if the tooltip drawing has been previously cancelled.
	 * 
	 * @return the user hook for 'afterTooltipDraw' plugin
	 */
	public final AfterTooltipDrawHook getAfterTooltipDrawHook() {
		return afterTooltipDrawHook;
	}

	/**
	 * Sets the user hook for 'beforeEvent' plugin.<br>
	 * Called before processing the specified 'event'.<br>
	 * If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param beforeEventHook the user hook for 'beforeEvent' plugin
	 */
	public final void setBeforeEventHook(BeforeEventHook beforeEventHook) {
		// sets the hook
		this.beforeEventHook = beforeEventHook;
		// stores and manages hook
		setHook(Property.BEFORE_EVENT, beforeEventHook, getBeforeEventCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeEvent' plugin.<br>
	 * Called before processing the specified 'event'.<br>
	 * If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param nativeBeforeEventHook the user hook for 'beforeEvent' plugin
	 */
	public final void setBeforeEventHook(NativeHook nativeBeforeEventHook) {
		// resets the hook
		setBeforeEventHook((BeforeEventHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_EVENT, nativeBeforeEventHook);
	}

	/**
	 * Returns the user hook for 'beforeEvent' plugin.<br>
	 * Called before processing the specified 'event'.<br>
	 * If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @return the user hook for 'beforeEvent' plugin
	 */
	public final BeforeEventHook getBeforeEventHook() {
		return beforeEventHook;
	}

	/**
	 * Sets the user hook for 'afterEvent' plugin.<br>
	 * Called after the 'event' has been consumed.<br>
	 * Note that this hook will not be called if the 'event' has been previously discarded.
	 * 
	 * @param afterEventHook the user hook for 'afterEvent' plugin
	 */
	public final void setAfterEventHook(AfterEventHook afterEventHook) {
		// sets the hook
		this.afterEventHook = afterEventHook;
		// stores and manages hook
		setHook(Property.AFTER_EVENT, afterEventHook, getAfterEventCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterEvent' plugin.<br>
	 * Called after the 'event' has been consumed.<br>
	 * Note that this hook will not be called if the 'event' has been previously discarded.
	 * 
	 * @param nativeAfterEventHook the user hook for 'afterEvent' plugin
	 */
	public final void setAfterEventHook(NativeHook nativeAfterEventHook) {
		// resets the hook
		setAfterEventHook((AfterEventHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_EVENT, nativeAfterEventHook);
	}

	/**
	 * Returns the user hook for 'afterEvent' plugin.<br>
	 * Called after the 'event' has been consumed.<br>
	 * Note that this hook will not be called if the 'event' has been previously discarded.
	 * 
	 * @return the user hook for 'afterEvent' plugin
	 */
	public final AfterEventHook getAfterEventHook() {
		return afterEventHook;
	}

	/**
	 * Sets the user hook for 'resize' plugin.<br>
	 * Called after the chart as been resized.
	 * 
	 * @param resizeHook the user hook for 'resize' plugin
	 */
	public final void setResizeHook(ResizeHook resizeHook) {
		// sets the hook
		this.resizeHook = resizeHook;
		// stores and manages hook
		setHook(Property.RESIZE, resizeHook, getResizeCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'resize' plugin.<br>
	 * Called after the chart as been resized.
	 * 
	 * @param nativeResizeHook the user hook for 'resize' plugin
	 */
	public final void setResizeHook(NativeHook nativeResizeHook) {
		// resets the hook
		setResizeHook((ResizeHook) null);
		// stores and manages native hook
		setValue(Property.RESIZE, nativeResizeHook);
	}

	/**
	 * Returns the user hook for 'resize' plugin.<br>
	 * Called after the chart as been resized.
	 * 
	 * @return the user hook for 'resize' plugin
	 */
	public final ResizeHook getResizeHook() {
		return resizeHook;
	}

	/**
	 * Sets the user hook for 'reset' plugin.<br>
	 * Called during chart reset.
	 * 
	 * @param resetHook the user hook for 'reset' plugin
	 */
	public final void setResetHook(ResetHook resetHook) {
		// sets the hook
		this.resetHook = resetHook;
		// stores and manages hook
		setHook(Property.RESET, resetHook, getResetCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'reset' plugin.<br>
	 * Called during chart reset.
	 * 
	 * @param nativeResetHook the user hook for 'reset' plugin
	 */
	public final void setResetHook(NativeHook nativeResetHook) {
		// resets the hook
		setResetHook((ResetHook) null);
		// stores and manages native hook
		setValue(Property.RESET, nativeResetHook);
	}

	/**
	 * Returns the user hook for 'reset' plugin.<br>
	 * Called during chart reset.
	 * 
	 * @return the user hook for 'reset' plugin
	 */
	public final ResetHook getResetHook() {
		return resetHook;
	}

	/**
	 * Sets the user hook for 'beforeDestroy' plugin.<br>
	 * Called before the chart is being destroyed.
	 * 
	 * @param beforeDestroyHook the user hook for 'beforeDestroy' plugin
	 */
	public final void setBeforeDestroyHook(BeforeDestroyHook beforeDestroyHook) {
		// sets the hook
		this.beforeDestroyHook = beforeDestroyHook;
		// stores and manages hook
		setHook(Property.BEFORE_DESTROY, beforeDestroyHook, getBeforeDestroyCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeDestroy' plugin.<br>
	 * Called before the chart is being destroyed.
	 * 
	 * @param nativeBeforeDestroyHook the user hook for 'beforeDestroy' plugin
	 */
	public final void setBeforeDestroyHook(NativeHook nativeBeforeDestroyHook) {
		// resets the hook
		setBeforeDestroyHook((BeforeDestroyHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_DESTROY, nativeBeforeDestroyHook);
	}

	/**
	 * Returns the user hook for 'beforeDestroy' plugin.<br>
	 * Called before the chart is being destroyed.
	 * 
	 * @return the user hook for 'beforeDestroy' plugin
	 */
	public final BeforeDestroyHook getBeforeDestroyHook() {
		return beforeDestroyHook;
	}

	/**
	 * Sets the user hook for 'afterDestroy' plugin.<br>
	 * Called after the chart has been destroyed.
	 * 
	 * @param afterDestroyHook the user hook for 'afterDestroy' plugin
	 */
	public final void setAfterDestroyHook(AfterDestroyHook afterDestroyHook) {
		// sets the hook
		this.afterDestroyHook = afterDestroyHook;
		// stores and manages hook
		setHook(Property.AFTER_DESTROY, afterDestroyHook, getAfterDestroyCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterDestroy' plugin.<br>
	 * Called after the chart has been destroyed.
	 * 
	 * @param nativeAfterDestroyHook the user hook for 'afterDestroy' plugin
	 */
	public final void setAfterDestroyHook(NativeHook nativeAfterDestroyHook) {
		// resets the hook
		setAfterDestroyHook((AfterDestroyHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_DESTROY, nativeAfterDestroyHook);
	}

	/**
	 * Returns the user hook for 'afterDestroy' plugin.<br>
	 * Called after the chart has been destroyed.
	 * 
	 * @return the user hook for 'afterDestroy' plugin
	 */
	public final AfterDestroyHook getAfterDestroyHook() {
		return afterDestroyHook;
	}

	/**
	 * Sets the user hook for 'install' plugin.<br>
	 * Called when plugin is installed for this chart instance.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param installHook the user hook for 'install' plugin
	 */
	public final void setInstallHook(InstallHook installHook) {
		// sets the hook
		this.installHook = installHook;
		// stores and manages hook
		setHook(Property.INSTALL, installHook, getInstallCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'install' plugin.<br>
	 * Called when plugin is installed for this chart instance.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param nativeInstallHook the user hook for 'install' plugin
	 */
	public final void setInstallHook(NativeHook nativeInstallHook) {
		// resets the hook
		setInstallHook((InstallHook) null);
		// stores and manages native hook
		setValue(Property.INSTALL, nativeInstallHook);
	}

	/**
	 * Returns the user hook for 'install' plugin.<br>
	 * Called when plugin is installed for this chart instance.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @return the user hook for 'install' plugin
	 */
	public final InstallHook getInstallHook() {
		return installHook;
	}

	/**
	 * Sets the user hook for 'start' plugin.<br>
	 * Called when a plugin is starting.<br>
	 * This happens when chart is created or plugin is enabled.
	 * 
	 * @param startHook the user hook for 'start' plugin
	 */
	public final void setStartHook(StartHook startHook) {
		// sets the hook
		this.startHook = startHook;
		// stores and manages hook
		setHook(Property.START, startHook, getStartCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'start' plugin.<br>
	 * Called when a plugin is starting.<br>
	 * This happens when chart is created or plugin is enabled.
	 * 
	 * @param nativeStartHook the user hook for 'start' plugin
	 */
	public final void setStartHook(NativeHook nativeStartHook) {
		// resets the hook
		setStartHook((StartHook) null);
		// stores and manages native hook
		setValue(Property.START, nativeStartHook);
	}

	/**
	 * Returns the user hook for 'start' plugin.<br>
	 * Called when a plugin is starting.<br>
	 * This happens when chart is created or plugin is enabled.
	 * 
	 * @return the user hook for 'start' plugin
	 */
	public final StartHook getStartHook() {
		return startHook;
	}

	/**
	 * Sets the user hook for 'stop' plugin.<br>
	 * Called when a plugin stopping.<br>
	 * This happens when chart is destroyed or plugin is disabled.
	 * 
	 * @param stopHook the user hook for 'stop' plugin
	 */
	public final void setStopHook(StopHook stopHook) {
		// sets the hook
		this.stopHook = stopHook;
		// stores and manages hook
		setHook(Property.STOP, stopHook, getStopCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'stop' plugin.<br>
	 * Called when a plugin stopping.<br>
	 * This happens when chart is destroyed or plugin is disabled.
	 * 
	 * @param nativeStopHook the user hook for 'stop' plugin
	 */
	public final void setStopHook(NativeHook nativeStopHook) {
		// resets the hook
		setStopHook((StopHook) null);
		// stores and manages native hook
		setValue(Property.STOP, nativeStopHook);
	}

	/**
	 * Returns the user hook for 'stop' plugin.<br>
	 * Called when a plugin stopping.<br>
	 * This happens when chart is destroyed or plugin is disabled.
	 * 
	 * @return the user hook for 'stop' plugin
	 */
	public final StopHook getStopHook() {
		return stopHook;
	}

	/**
	 * Sets the user hook for 'uninstall' plugin.<br>
	 * Called after chart is destroyed on all plugins that were installed for that chart.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param uninstallHook the user hook for 'uninstall' plugin
	 */
	public final void setUninstallHook(UninstallHook uninstallHook) {
		// sets the hook
		this.uninstallHook = uninstallHook;
		// stores and manages hook
		setHook(Property.UNINSTALL, uninstallHook, getUninstallCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'uninstall' plugin.<br>
	 * Called after chart is destroyed on all plugins that were installed for that chart.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param nativeUninstallHook the user hook for 'uninstall' plugin
	 */
	public final void setUninstallHook(NativeHook nativeUninstallHook) {
		// resets the hook
		setUninstallHook((UninstallHook) null);
		// stores and manages native hook
		setValue(Property.UNINSTALL, nativeUninstallHook);
	}

	/**
	 * Returns the user hook for 'uninstall' plugin.<br>
	 * Called after chart is destroyed on all plugins that were installed for that chart.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @return the user hook for 'uninstall' plugin
	 */
	public final UninstallHook getUninstallHook() {
		return uninstallHook;
	}

	/**
	 * Sets the user hook for 'beforeDataLimits' plugin.<br>
	 * Called before scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param beforeDataLimitsHook the user hook for 'beforeDataLimits' plugin
	 */
	public final void setBeforeDataLimitsHook(BeforeDataLimitsHook beforeDataLimitsHook) {
		// sets the hook
		this.beforeDataLimitsHook = beforeDataLimitsHook;
		// stores and manages hook
		setHook(Property.BEFORE_DATA_LIMITS, beforeDataLimitsHook, getBeforeDataLimitsCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeDataLimits' plugin.<br>
	 * Called before scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param nativeBeforeDataLimitsHook the user hook for 'beforeDataLimits' plugin
	 */
	public final void setBeforeDataLimitsHook(NativeHook nativeBeforeDataLimitsHook) {
		// resets the hook
		setBeforeDataLimitsHook((BeforeDataLimitsHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_DATA_LIMITS, nativeBeforeDataLimitsHook);
	}

	/**
	 * Returns the user hook for 'beforeDataLimits' plugin.<br>
	 * Called before scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @return the user hook for 'beforeDataLimits' plugin
	 */
	public final BeforeDataLimitsHook getBeforeDataLimitsHook() {
		return beforeDataLimitsHook;
	}

	/**
	 * Sets the user hook for 'afterDataLimits' plugin.<br>
	 * Called after scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param afterDataLimitsHook the user hook for 'afterDataLimits' plugin
	 */
	public final void setAfterDataLimitsHook(AfterDataLimitsHook afterDataLimitsHook) {
		// sets the hook
		this.afterDataLimitsHook = afterDataLimitsHook;
		// stores and manages hook
		setHook(Property.AFTER_DATA_LIMITS, afterDataLimitsHook, getAfterDataLimitsCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterDataLimits' plugin.<br>
	 * Called after scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param nativeAfterDataLimitsHook the user hook for 'afterDataLimits' plugin
	 */
	public final void setAfterDataLimitsHook(NativeHook nativeAfterDataLimitsHook) {
		// resets the hook
		setAfterDataLimitsHook((AfterDataLimitsHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_DATA_LIMITS, nativeAfterDataLimitsHook);
	}

	/**
	 * Returns the user hook for 'afterDataLimits' plugin.<br>
	 * Called after scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @return the user hook for 'afterDataLimits' plugin
	 */
	public final AfterDataLimitsHook getAfterDataLimitsHook() {
		return afterDataLimitsHook;
	}

	/**
	 * Sets the user hook for 'beforeBuildTicks' plugin.<br>
	 * Called before scale builds its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param beforeBuildTicksHook the user hook for 'beforeBuildTicks' plugin
	 */
	public final void setBeforeBuildTicksHook(BeforeBuildTicksHook beforeBuildTicksHook) {
		// sets the hook
		this.beforeBuildTicksHook = beforeBuildTicksHook;
		// stores and manages hook
		setHook(Property.BEFORE_BUILD_TICKS, beforeBuildTicksHook, getBeforeBuildTicksCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'beforeBuildTicks' plugin.<br>
	 * Called before scale builds its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param nativeBeforeBuildTicksHook the user hook for 'beforeBuildTicks' plugin
	 */
	public final void setBeforeBuildTicksHook(NativeHook nativeBeforeBuildTicksHook) {
		// resets the hook
		setBeforeBuildTicksHook((BeforeBuildTicksHook) null);
		// stores and manages native hook
		setValue(Property.BEFORE_BUILD_TICKS, nativeBeforeBuildTicksHook);
	}

	/**
	 * Returns the user hook for 'beforeBuildTicks' plugin.<br>
	 * Called before scale builds its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @return the user hook for 'beforeBuildTicks' plugin
	 */
	public final BeforeBuildTicksHook getBeforeBuildTicksHook() {
		return beforeBuildTicksHook;
	}

	/**
	 * Sets the user hook for 'afterBuildTicks' plugin.<br>
	 * Called after scale has build its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param afterBuildTicksHook the user hook for 'afterBuildTicks' plugin
	 */
	public final void setAfterBuildTicksHook(AfterBuildTicksHook afterBuildTicksHook) {
		// sets the hook
		this.afterBuildTicksHook = afterBuildTicksHook;
		// stores and manages hook
		setHook(Property.AFTER_BUILD_TICKS, afterBuildTicksHook, getAfterBuildTicksCallbackProxy());
	}

	/**
	 * Sets the user native hook for 'afterBuildTicks' plugin.<br>
	 * Called after scale has build its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param nativeAfterBuildTicksHook the user hook for 'afterBuildTicks' plugin
	 */
	public final void setAfterBuildTicksHook(NativeHook nativeAfterBuildTicksHook) {
		// resets the hook
		setAfterBuildTicksHook((AfterBuildTicksHook) null);
		// stores and manages native hook
		setValue(Property.AFTER_BUILD_TICKS, nativeAfterBuildTicksHook);
	}

	/**
	 * Returns the user hook for 'afterBuildTicks' plugin.<br>
	 * Called after scale has build its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @return the user hook for 'afterBuildTicks' plugin
	 */
	public final AfterBuildTicksHook getAfterBuildTicksHook() {
		return afterBuildTicksHook;
	}

	// ----------------------------
	// -- INITIALIZATION ---
	// ----------------------------

	/**
	 * Called before creation of 'chart' java script.
	 * 
	 * @param chart chart instance.
	 */
	@Override
	void invokeConfigure(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getConfigureHook())) {
			getConfigureHook().onConfigure(chart);
		}
	}

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeBeforeInit(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeInitHook())) {
			// invokes plugin method
			getBeforeInitHook().onBeforeInit(chart);
		}
	}

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chart chart instance
	 * @param nativeChart CHART.JS chart instance
	 */
	@Override
	void invokeAfterInit(IsChart chart, Chart nativeChart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterInitHook())) {
			// invokes plugin method
			getAfterInitHook().onAfterInit(chart, nativeChart);
		}
	}

	// ----------------------------
	// -- UPDATE ---
	// ----------------------------

	/**
	 * Called before updating 'chart'.<br>
	 * If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 * @return <code>false</code> to cancel the chart update.
	 */
	@Override
	boolean invokeBeforeUpdate(IsChart chart, PluginUpdateArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeUpdateHook())) {
			// invokes plugin method
			return getBeforeUpdateHook().onBeforeUpdate(chart, argument);
		}
		return true;
	}

	/**
	 * Called after 'chart' has been updated and before rendering.<br>
	 * Note that this hook will not be called if the chart update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 */
	@Override
	void invokeAfterUpdate(IsChart chart, PluginUpdateArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterUpdateHook())) {
			// invokes plugin method
			getAfterUpdateHook().onAfterUpdate(chart, argument);
		}
	}

	/**
	 * Called during the update process, before any chart elements have been created.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeBeforeElementsUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeElementsUpdateHook())) {
			// invokes plugin method
			getBeforeElementsUpdateHook().onBeforeElementsUpdate(chart);
		}
	}

	/**
	 * Called before laying out 'chart'.<br>
	 * If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart layout.
	 */
	@Override
	boolean invokeBeforeLayout(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeLayoutHook())) {
			// invokes plugin method
			return getBeforeLayoutHook().onBeforeLayout(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been layed out.<br>
	 * Note that this hook will not be called if the layout update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeAfterLayout(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterLayoutHook())) {
			// invokes plugin method
			getAfterLayoutHook().onAfterLayout(chart);
		}
	}

	/**
	 * Called before updating the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 * @return <code>false</code> to cancel the datasets update.
	 */
	@Override
	boolean invokeBeforeDatasetsUpdate(IsChart chart, PluginUpdateArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeDatasetsUpdateHook())) {
			// invokes plugin method
			return getBeforeDatasetsUpdateHook().onBeforeDatasetsUpdate(chart, argument);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 */
	@Override
	void invokeAfterDatasetsUpdate(IsChart chart, PluginUpdateArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterDatasetsUpdateHook())) {
			// invokes plugin method
			getAfterDatasetsUpdateHook().onAfterDatasetsUpdate(chart, argument);
		}
	}

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @param item dataset item.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	@Override
	boolean invokeBeforeDatasetUpdate(IsChart chart, PluginDatasetArgument item) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeDatasetUpdateHook())) {
			// invokes plugin method
			return getBeforeDatasetUpdateHook().onBeforeDatasetUpdate(chart, item);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item dataset item.
	 */
	@Override
	void invokeAfterDatasetUpdate(IsChart chart, PluginDatasetArgument item) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterDatasetUpdateHook())) {
			// invokes plugin method
			getAfterDatasetUpdateHook().onAfterDatasetUpdate(chart, item);
		}
	}

	// ----------------------------
	// -- RENDER ---
	// ----------------------------

	/**
	 * Called before rendering 'chart'.<br>
	 * If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	@Override
	boolean invokeBeforeRender(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeRenderHook())) {
			// invokes plugin method
			return getBeforeRenderHook().onBeforeRender(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed).<br>
	 * Note that this hook will not be called if the rendering has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeAfterRender(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterRenderHook())) {
			// invokes plugin method
			getAfterRenderHook().onAfterRender(chart);
		}
	}

	/**
	 * Called before drawing 'chart' at every animation frame.<br>
	 * If any plugin returns <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	@Override
	boolean invokeBeforeDraw(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeDrawHook())) {
			// invokes plugin method
			return getBeforeDrawHook().onBeforeDraw(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been drawn.<br>
	 * Note that this hook will not be called if the drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeAfterDraw(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterDrawHook())) {
			// invokes plugin method
			getAfterDrawHook().onAfterDraw(chart);
		}
	}

	/**
	 * Called before drawing the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	@Override
	boolean invokeBeforeDatasetsDraw(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeDatasetsDrawHook())) {
			// invokes plugin method
			return getBeforeDatasetsDrawHook().onBeforeDatasetsDraw(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been drawn.<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeAfterDatasetsDraw(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterDatasetsDrawHook())) {
			// invokes plugin method
			getAfterDatasetsDrawHook().onAfterDatasetsDraw(chart);
		}
	}

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any plugin returns <code>false</code>, the datasets drawing
	 * is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @param item dataset item instance
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	@Override
	boolean invokeBeforeDatasetDraw(IsChart chart, PluginDatasetArgument item) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeDatasetDrawHook())) {
			// invokes plugin method
			return getBeforeDatasetDrawHook().onBeforeDatasetDraw(chart, item);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order). Note that this hook will not be called if the datasets
	 * drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item dataset item instance
	 */
	@Override
	void invokeAfterDatasetDraw(IsChart chart, PluginDatasetArgument item) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterDatasetDrawHook())) {
			// invokes plugin method
			getAfterDatasetDrawHook().onAfterDatasetDraw(chart, item);
		}
	}

	// ----------------------------
	// -- TOOLTIP ---
	// ----------------------------

	/**
	 * Called before drawing the 'tooltip'. If any plugin returns <code>false</code>, the tooltip drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item instance
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	@Override
	boolean invokeBeforeTooltipDraw(IsChart chart, PluginTooltipArgument item) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeTooltipDrawHook())) {
			// invokes plugin method
			return getBeforeTooltipDrawHook().onBeforeTooltipDraw(chart, item);
		}
		return true;
	}

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item instance
	 */
	@Override
	void invokeAfterTooltipDraw(IsChart chart, PluginTooltipArgument item) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterTooltipDrawHook())) {
			// invokes plugin method
			getAfterTooltipDrawHook().onAfterTooltipDraw(chart, item);
		}
	}

	// ----------------------------
	// -- EVENT ---
	// ----------------------------

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param chart chart instance
	 * @param argument argument of event callback
	 * @return <code>false</code> to discard the event.
	 */
	@Override
	boolean invokeBeforeEvent(IsChart chart, PluginEventArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeEventHook())) {
			// invokes plugin method
			return getBeforeEventHook().onBeforeEvent(chart, argument);
		}
		return true;
	}

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously discarded.
	 * 
	 * @param chart chart instance
	 * @param argument argument of event callback
	 */
	@Override
	void invokeAfterEvent(IsChart chart, PluginEventArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterEventHook())) {
			// invokes plugin method
			getAfterEventHook().onAfterEvent(chart, argument);
		}
	}

	// ----------------------------
	// -- RESIZE, RESET, DESTORY --
	// ----------------------------

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chart chart instance
	 * @param argument argument of method which contains the new canvas display size (eq. canvas.style width and height).
	 */
	@Override
	void invokeResize(IsChart chart, PluginResizeArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getResizeHook())) {
			// invokes plugin method
			getResizeHook().onResize(chart, argument);
		}
	}

	/**
	 * Called during chart reset.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeReset(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getResetHook())) {
			// invokes plugin method
			getResetHook().onReset(chart);
		}
	}

	/**
	 * Called before the chart is being destroyed.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeBeforeDestroy(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeDestroyHook())) {
			// invokes plugin method
			getBeforeDestroyHook().onBeforeDestroy(chart);
		}
	}

	/**
	 * Called after the chart has been destroyed.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeAfterDestroy(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterDestroyHook())) {
			// invokes plugin method
			getAfterDestroyHook().onAfterDestroy(chart);
		}
	}

	// ---------------------------
	// -- PLUGIN LIFECYCLE
	// ---------------------------

	/**
	 * Called when plugin is installed for this chart instance.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeInstall(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getInstallHook())) {
			// invokes plugin method
			getInstallHook().onInstall(chart);
		}
	}

	/**
	 * Called when a plugin is starting.<br>
	 * This happens when chart is created or plugin is enabled.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeStart(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getStartHook())) {
			// invokes plugin method
			getStartHook().onStart(chart);
		}
	}

	/**
	 * Called when a plugin stopping.<br>
	 * This happens when chart is destroyed or plugin is disabled.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeStop(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getStopHook())) {
			// invokes plugin method
			getStopHook().onStop(chart);
		}
	}

	/**
	 * Called after chart is destroyed on all plugins that were installed for that chart.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeUninstall(IsChart chart) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getUninstallHook())) {
			// invokes plugin method
			getUninstallHook().onUninstall(chart);
		}
	}

	// ---------------------------
	// -- SCALES DATA LIMITS
	// ---------------------------

	/**
	 * Called before scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	@Override
	void invokeBeforeDataLimits(IsChart chart, PluginScaleArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeDataLimitsHook())) {
			// invokes plugin method
			getBeforeDataLimitsHook().onBeforeDataLimits(chart, argument);
		}
	}

	/**
	 * Called after scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	@Override
	void invokeAfterDataLimits(IsChart chart, PluginScaleArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterDataLimitsHook())) {
			// invokes plugin method
			getAfterDataLimitsHook().onAfterDataLimits(chart, argument);
		}
	}

	// ---------------------------
	// -- SCALES BUILD TICKS
	// ---------------------------

	/**
	 * Called before scale builds its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	@Override
	void invokeBeforeBuildTicks(IsChart chart, PluginScaleArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getBeforeBuildTicksHook())) {
			// invokes plugin method
			getBeforeBuildTicksHook().onBeforeBuildTicks(chart, argument);
		}
	}

	/**
	 * Called after scale has build its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	@Override
	void invokeAfterBuildTicks(IsChart chart, PluginScaleArgument argument) {
		// if consistent, calls plugin
		if (checkHookInvocation(chart, getAfterBuildTicksHook())) {
			// invokes plugin method
			getAfterBuildTicksHook().onAfterBuildTicks(chart, argument);
		}
	}

	// ---------------------------
	// -- COMMON
	// ---------------------------

	/**
	 * Sets the hook that plugin can activate.
	 * 
	 * @param property property name
	 * @param callBack the callback instance
	 * @param proxy the proxy instance
	 */
	private void setHook(Key property, Object callBack, CallbackProxy<?> proxy) {
		// checks if consistent
		if (callBack != null) {
			// adds the callback proxy function to java script object
			setValue(property, proxy.getProxy());
		} else {
			// otherwise removes the property from java script object
			remove(property);
		}
	}

	/**
	 * Checks if the hook can be invoked, checking chart and callback consistency.
	 * 
	 * @param chart chart instance to check
	 * @param hook hook instance to check
	 * @return <code>true</code> if the hook can be invoked, checking chart and callback consistency, otherwise <code>false</code>.
	 */
	private boolean checkHookInvocation(IsChart chart, Object hook) {
		return hook != null && IsChart.isValid(chart);
	}

}
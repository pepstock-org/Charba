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
package org.pepstock.charba.client.matrix;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-chart-matrix.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class MatrixControllerResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-chart-matrix.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * chartjs-chart-matrix v2.0.1",
		" * https://chartjs-chart-matrix.pages.dev/",
		" * (c) 2023 Jukka Kurkela",
		" * Released under the MIT license",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?e(exports,require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"exports\",\"chart.js\",\"chart.js/helpers\"],e):e((t=\"undefined\"!=typeof globalThis?globalThis:t||self)[\"chartjs-chart-matrix\"]={},t.Chart,t.Chart.helpers)}(this,(function(t,e,i){\"use strict\";class r extends e.DatasetController{static id=\"matrix\";static version=\"2.0.1\";static defaults={dataElementType:\"matrix\",animations:{numbers:{type:\"number\",properties:[\"x\",\"y\",\"width\",\"height\"]}}};static overrides={interaction:{mode:\"nearest\",intersect:!0},scales:{x:{type:\"linear\",offset:!0},y:{type:\"linear\",reverse:!0}}};initialize(){this.enableOptionSharing=!0,super.initialize()}update(t){const e=this._cachedMeta;this.updateElements(e.data,0,e.data.length,t)}updateElements(t,e,i,r){const s=this,a=\"reset\"===r,{xScale:h,yScale:l}=s._cachedMeta,d=s.resolveDataElementOptions(e,r),c=s.getSha",
		"redOptions(r,t[e],d);for(let d=e;d<e+i;d++){const e=!a&&s.getParsed(d),i=a?h.getBasePixel():h.getPixelForValue(e.x),c=a?l.getBasePixel():l.getPixelForValue(e.y),u=s.resolveDataElementOptions(d,r),{width:g,height:p,anchorX:f,anchorY:x}=u,y={x:n(f,i,g),y:o(x,c,p),width:g,height:p,options:u};s.updateElement(t[d],d,y,r)}s.updateSharedOptions(c,r)}draw(){const t=this,e=t.getMeta().data||[];let i,r;for(i=0,r=e.length;i<r;++i)e[i].draw(t._ctx)}}function n(t,e,i){return\"left\"===t||\"start\"===t?e:\"right\"===t||\"end\"===t?e-i:e-i/2}function o(t,e,i){return\"top\"===t||\"start\"===t?e:\"bottom\"===t||\"end\"===t?e-i:e-i/2}function s(t,e){const{x:i,y:r,width:n,height:o}=t.getProps([\"x\",\"y\",\"width\",\"height\"],e);return{left:i,top:r,right:i+n,bottom:r+o}}function a(t,e,i){return Math.max(Math.min(t,i),e)}function h(t){const e=s(t),r=e.right-e.left,n=e.bottom-e.top,o=function(t,e,r){const n=t.options.borderWidth;let o,s,h,l;return i.isObject(n)?(o=+n.top||0,s=+n.right||0,h=+n.bottom||0,l=+",
		"n.left||0):o=s=h=l=+n||0,{t:a(o,0,r),r:a(s,0,e),b:a(h,0,r),l:a(l,0,e)}}(t,r/2,n/2);return{outer:{x:e.left,y:e.top,w:r,h:n},inner:{x:e.left+o.l,y:e.top+o.t,w:r-o.l-o.r,h:n-o.t-o.b}}}function l(t,e,i,r){const n=null===e,o=null===i,a=!(!t||n&&o)&&s(t,r);return a&&(n||e>=a.left&&e<=a.right)&&(o||i>=a.top&&i<=a.bottom)}class d extends e.Element{static id=\"matrix\";static defaults={backgroundColor:void 0,borderColor:void 0,borderWidth:void 0,borderRadius:0,anchorX:\"center\",anchorY:\"center\",width:20,height:20};constructor(t){super(),this.options=void 0,this.width=void 0,this.height=void 0,t&&Object.assign(this,t)}draw(t){const e=this.options,{inner:r,outer:n}=h(this),o=i.toTRBLCorners(e.borderRadius);t.save(),n.w!==r.w||n.h!==r.h?(t.beginPath(),i.addRoundedRectPath(t,{x:n.x,y:n.y,w:n.w,h:n.h,radius:o}),i.addRoundedRectPath(t,{x:r.x,y:r.y,w:r.w,h:r.h,radius:o}),t.fillStyle=e.backgroundColor,t.fill(),t.fillStyle=e.borderColor,t.fill(\"evenodd\")):(t.beginPath(),i.addRoundedRectPath(t,{x:r.x",
		",y:r.y,w:r.w,h:r.h,radius:o}),t.fillStyle=e.backgroundColor,t.fill()),t.restore()}inRange(t,e,i){return l(this,t,e,i)}inXRange(t,e){return l(this,t,null,e)}inYRange(t,e){return l(this,null,t,e)}getCenterPoint(t){const{x:e,y:i,width:r,height:n}=this.getProps([\"x\",\"y\",\"width\",\"height\"],t);return{x:e+r/2,y:i+n/2}}tooltipPosition(){return this.getCenterPoint()}getRange(t){return\"x\"===t?this.width/2:this.height/2}}e.Chart.register(r,d),t.MatrixController=r,t.MatrixElement=d}));",
		"//# sourceMappingURL=chartjs-chart-matrix.min.js.map"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-chart-matrix.min.js</code> content.
	 */
	MatrixControllerResource() {
		super(ResourceName.MATRIX_CONTROLLER, CONTENT);
	}

}

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
		" * chartjs-chart-matrix v1.3.0",
		" * https://chartjs-chart-matrix.pages.dev/",
		" * (c) 2022 Jukka Kurkela",
		" * Released under the MIT license",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?e(require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\"],e):e((t=\"undefined\"!=typeof globalThis?globalThis:t||self).Chart,t.Chart.helpers)}(this,(function(t,e){\"use strict\";class i extends t.DatasetController{initialize(){this.enableOptionSharing=!0,super.initialize()}update(t){const e=this._cachedMeta;this.updateElements(e.data,0,e.data.length,t)}updateElements(t,e,i,o){const s=this,h=\"reset\"===o,{xScale:a,yScale:d}=s._cachedMeta,l=s.resolveDataElementOptions(e,o),u=s.getSharedOptions(o,t[e],l);for(let l=e;l<e+i;l++){const e=!h&&s.getParsed(l),i=h?a.getBasePixel():a.getPixelForValue(e.x),u=h?d.getBasePixel():d.getPixelForValue(e.y),c=s.resolveDataElementOptions(l,o),{width:g,height:p,anchorX:f,anchorY:x}=c,y={x:r(f,i,g),y:n(x,u,p),width:g,height:p,options:c};s.updateElement(t[l],l,y,o)}s.updateSharedOptions(u,o)}draw(){const t=th",
		"is,e=t.getMeta().data||[];let i,r;for(i=0,r=e.length;i<r;++i)e[i].draw(t._ctx)}}function r(t,e,i){return\"left\"===t||\"start\"===t?e:\"right\"===t||\"end\"===t?e-i:e-i/2}function n(t,e,i){return\"top\"===t||\"start\"===t?e:\"bottom\"===t||\"end\"===t?e-i:e-i/2}function o(t,e){const{x:i,y:r,width:n,height:o}=t.getProps([\"x\",\"y\",\"width\",\"height\"],e);return{left:i,top:r,right:i+n,bottom:r+o}}function s(t,e,i){return Math.max(Math.min(t,i),e)}function h(t){const i=o(t),r=i.right-i.left,n=i.bottom-i.top,h=function(t,i,r){const n=t.options.borderWidth;let o,h,a,d;return e.isObject(n)?(o=+n.top||0,h=+n.right||0,a=+n.bottom||0,d=+n.left||0):o=h=a=d=+n||0,{t:s(o,0,r),r:s(h,0,i),b:s(a,0,r),l:s(d,0,i)}}(t,r/2,n/2);return{outer:{x:i.left,y:i.top,w:r,h:n},inner:{x:i.left+h.l,y:i.top+h.t,w:r-h.l-h.r,h:n-h.t-h.b}}}function a(t,e,i,r){const n=null===e,s=null===i,h=!(!t||n&&s)&&o(t,r);return h&&(n||e>=h.left&&e<=h.right)&&(s||i>=h.top&&i<=h.bottom)}i.id=\"matrix\",i.version=\"1.3.0\",i.defaults",
		"={dataElementType:\"matrix\",animations:{numbers:{type:\"number\",properties:[\"x\",\"y\",\"width\",\"height\"]}}},i.overrides={interaction:{mode:\"nearest\",intersect:!0},scales:{x:{type:\"linear\",offset:!0},y:{type:\"linear\",reverse:!0}}};class d extends t.Element{constructor(t){super(),this.options=void 0,this.width=void 0,this.height=void 0,t&&Object.assign(this,t)}draw(t){const i=this.options,{inner:r,outer:n}=h(this),o=e.toTRBLCorners(i.borderRadius);t.save(),n.w!==r.w||n.h!==r.h?(t.beginPath(),e.addRoundedRectPath(t,{x:n.x,y:n.y,w:n.w,h:n.h,radius:o}),e.addRoundedRectPath(t,{x:r.x,y:r.y,w:r.w,h:r.h,radius:o}),t.fillStyle=i.backgroundColor,t.fill(),t.fillStyle=i.borderColor,t.fill(\"evenodd\")):(t.beginPath(),e.addRoundedRectPath(t,{x:r.x,y:r.y,w:r.w,h:r.h,radius:o}),t.fillStyle=i.backgroundColor,t.fill()),t.restore()}inRange(t,e,i){return a(this,t,e,i)}inXRange(t,e){return a(this,t,null,e)}inYRange(t,e){return a(this,null,t,e)}getCenterPoint(t){const{x:e,y:i,width:r,height:n}=t",
		"his.getProps([\"x\",\"y\",\"width\",\"height\"],t);return{x:e+r/2,y:i+n/2}}tooltipPosition(){return this.getCenterPoint()}getRange(t){return\"x\"===t?this.width/2:this.height/2}}d.id=\"matrix\",d.defaults={backgroundColor:void 0,borderColor:void 0,borderWidth:void 0,borderRadius:0,anchorX:\"center\",anchorY:\"center\",width:20,height:20},t.register(i,d)}));",
		"//# sourceMappingURL=chartjs-chart-matrix.min.js.map"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-chart-matrix.min.js</code> content.
	 */
	MatrixControllerResource() {
		super(ResourceName.MATRIX_CONTROLLER, CONTENT);
	}

}

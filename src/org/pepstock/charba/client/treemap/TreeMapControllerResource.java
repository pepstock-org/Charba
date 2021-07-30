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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-chart-treemap.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TreeMapControllerResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-chart-treemap.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * chartjs-chart-treemap v1.0.2",
		" * https://chartjs-chart-treemap.pages.dev/",
		" * (c) 2021 Jukka Kurkela",
		" * Released under the MIT license",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?e(exports,require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"exports\",\"chart.js\",\"chart.js/helpers\"],e):e((t=\"undefined\"!=typeof globalThis?globalThis:t||self)[\"chartjs-chart-treemap\"]={},t.Chart,t.Chart.helpers)}(this,(function(t,e,i){\"use strict\";function n(t){const e=[...t],i=[];for(;e.length;){const t=e.pop();Array.isArray(t)?e.push(...t):i.push(t)}return i.reverse()}function r(t,e,i,n,r){const o=Object.create(null),s=Object.create(null),a=[];let l,h,u,d;for(h=0,u=t.length;h<u;++h)d=t[h],n&&d[n]!==r||(l=d[e]||\"\",l in o||(o[l]=0,s[l]=[]),o[l]+=+d[i],s[l].push(d));return Object.keys(o).forEach((t=>{d={children:s[t]},d[i]=+o[t],d[e]=t,n&&(d[n]=r),a.push(d)})),a}function o(t){const e=typeof t;return\"function\"===e||\"object\"===e&&!!t}function s(t,e){let i,n=t.length;if(!n)return e;const r=o(t[0]);for(e=r?e:\"v\",i=0,n=t.length;i<n;++i)r?t[i]._idx=i:t",
		"[i]={v:t[i],_idx:i};return e}function a(t,e){e?t.sort(((t,i)=>+i[e]-+t[e])):t.sort(((t,e)=>+e-+t))}function l(t,e){let i,n,r;for(i=0,n=0,r=t.length;n<r;++n)i+=e?+t[n][e]:+t[n];return i}function h(t,e){return+(Math.round(t+\"e+\"+e)+\"e-\"+e)||0}function u(t,e,i,n){const r=t._normalized,o=e*r/i,s=Math.sqrt(r*o),a=r/s;return{d1:s,d2:a,w:\"_ix\"===n?s:a,h:\"_ix\"===n?a:s}}const d=(t,e)=>h(t.rtl?t.x+t.w-t._ix-e:t.x+t._ix,4);function c(t,e,i,n){const r={x:d(t,i.w),y:h(t.y+t._iy,4),w:h(i.w,4),h:h(i.h,4),a:h(e._normalized,4),v:e.value,s:n,_data:e._data};return e.group&&(r.g=e.group,r.l=e.level,r.gs=e.groupSum),r}class g{constructor(t){const e=this;t=t||{w:1,h:1},e.rtl=!!t.rtl,e.x=t.x||t.left||0,e.y=t.y||t.top||0,e._ix=0,e._iy=0,e.w=t.w||t.width||t.right-t.left,e.h=t.h||t.height||t.bottom-t.top}get area(){return this.w*this.h}get iw(){return this.w-this._ix}get ih(){return this.h-this._iy}get dir(){const t=this.ih;return t<=this.iw&&t>0?\"y\":\"x\"}get side(){return\"x\"===this.dir?this.iw:this",
		".ih}map(t){const e=this,i=[],n=t.nsum,r=t.get(),o=e.dir,s=e.side,a=s*s,l=\"x\"===o?\"_ix\":\"_iy\",h=n*n;let d=0,g=0;for(const n of r){const r=u(n,a,h,l);g+=r.d1,d=Math.max(d,r.d2),i.push(c(e,n,r,t.sum)),e[l]+=r.d1}return e[\"y\"===o?\"_ix\":\"_iy\"]+=d,e[l]-=g,i}}const p=Math.min,f=Math.max;function m(t,e){const i=+e[t.key],n=i*t.ratio;return e._normalized=n,{min:p(t.min,i),max:f(t.max,i),sum:t.sum+i,nmin:p(t.nmin,n),nmax:f(t.nmax,n),nsum:t.nsum+n}}function x(t,e,i){t._arr.push(e),function(t,e){Object.assign(t,e)}(t,i)}class y{constructor(t,e){const i=this;i.key=t,i.ratio=e,i.reset()}get length(){return this._arr.length}reset(){const t=this;t._arr=[],t._hist=[],t.sum=0,t.nsum=0,t.min=1/0,t.max=-1/0,t.nmin=1/0,t.nmax=-1/0}push(t){x(this,t,m(this,t))}pushIf(t,e,...i){const n=m(this,t);if(!e((r=this,{min:r.min,max:r.max,sum:r.sum,nmin:r.nmin,nmax:r.nmax,nsum:r.nsum}),n,i))return t;var r;x(this,t,n)}get(){return this._arr}}function v(t,e,i){if(0===t.sum)return!0;const[n]=i,r=t.nsum*t.nsum,",
		"o=e.nsum*e.nsum,s=n*n,a=Math.max(s*t.nmax/r,r/(s*t.nmin));return Math.max(s*e.nmax/o,o/(s*e.nmin))<=a}function b(t,e,i,r,o,h){t=t||[];const u=[],d=new g(e),c=new y(\"value\",d.area/l(t,i));let p=d.side;const f=t.length;let m,x;if(!f)return u;const b=t.slice();i=s(b,i),a(b,i);const w=t=>r&&b[t][r];for(m=0;m<f;++m)x={value:(_=m,i?+b[_][i]:+b[_]),groupSum:h,_data:t[b[m]._idx],level:void 0,group:void 0},r&&(x.level=o,x.group=w(m)),x=c.pushIf(x,v,p),x&&(u.push(d.map(c)),p=d.side,c.reset(),c.push(x));var _;return c.length&&u.push(d.map(c)),n(u)}function w(t,e){if(!e)return!1;const i=t.width||t.w,n=t.height||t.h,r=2*e.lineHeight;return i>r&&n>r}function _(t,e,i,n,r){if(t.save(),t.fillStyle=n.color,t.font=n.font.string,t.beginPath(),t.rect(e.x,e.y,e.width,e.height),t.clip(),\"l\"in i&&i.l!==r){if(n.groupLabels){t.textAlign=n.rtl?\"end\":\"start\",t.textBaseline=\"top\";const r=n.rtl?e.x+e.width-n.borderWidth-3:e.x+n.borderWidth+3;t.fillText(i.g,r,e.y+n.borderWidth+3)}}else t.textAlign=\"center\"",
		",t.textBaseline=\"middle\",function(t,e,i){const n=i.options,r=n.font.lineHeight,o=(n.label||e.g+\"\\n\"+e.v).split(\"\\n\"),s=i.y+i.height/2-o.length*r/4;o.forEach(((e,n)=>t.fillText(e,i.x+i.width/2,s+n*r)))}(t,i,e);t.restore()}function D(t,e){const i=e.options,n=e.width||e.w,r=e.height||e.h;if(t.save(),t.strokeStyle=i.dividerColor||\"black\",t.lineCap=i.dividerCapStyle,t.setLineDash(i.dividerDash||[]),t.lineDashOffset=i.dividerDashOffset,t.lineWidth=i.dividerWidth,t.beginPath(),n>r){const i=n/2;t.moveTo(e.x+i,e.y),t.lineTo(e.x+i,e.y+r)}else{const i=r/2;t.moveTo(e.x,e.y+i),t.lineTo(e.x+n,e.y+i)}t.stroke(),t.restore()}class O extends e.DatasetController{constructor(t,e){super(t,e),this._rect=void 0,this._key=void 0,this._groups=void 0}initialize(){this.enableOptionSharing=!0,super.initialize()}update(t){const e=this,n=e.getMeta(),o=e.getDataset(),s=o.groups||(o.groups=[]),a=i.toFont(o.font),l=e.chart.chartArea,h=o.key||\"\",u=!!o.rtl,d={x:l.left,y:l.top,w:l.right-l.left,h:l.bottom-l.top",
		",rtl:u};var c,g;\"reset\"!==t&&(c=e._rect,g=d,c&&g&&c.x===g.x&&c.y===g.y&&c.w===g.w&&c.h===g.h)&&e._key===h&&!function(t,e){let i,n;if(t.lenght!==e.length)return!0;for(i=0,n=t.length;i<n;++i)if(t[i]!==e[i])return!0;return!1}(e._groups,s)||(e._rect=d,e._groups=s.slice(),e._key=h,o.data=function(t,e,n){const o=t.key||\"\";let s=t.tree||[];const a=t.groups||[],l=a.length,h=(t.spacing||0)+(t.borderWidth||0);return!s.length&&t.data.length&&(s=t.tree=t.data),l?function e(u,d,c,g){const p=a[u],f=u>0&&a[u-1],m=b(r(s,p,o,f,c),d,o,p,u,g),x=m.slice();let y;return u<l-1&&m.forEach((r=>{y={x:r.x+h,y:r.y+h,w:r.w-2*h,h:r.h-2*h},i.valueOrDefault(t.groupLabels,!0)&&w(r,n)&&(y.y+=n.lineHeight,y.h-=n.lineHeight),x.push(...e(u+1,y,r.g,r.s))})),x}(0,e):b(s,e,o)}(o,d,a),e._dataCheck(),e._resyncElements()),e.updateElements(n.data,0,n.data.length,t)}resolveDataElementOptions(t,e){const n=super.resolveDataElementOptions(t,e),r=Object.isFrozen(n)?Object.assign({},n):n;return r.font=i.toFont(n.font),r}updateEleme",
		"nts(t,e,i,n){const r=this,o=\"reset\"===n,s=r.getDataset(),a=r._rect.options=r.resolveDataElementOptions(e,n),l=r.getSharedOptions(a),h=r.includeOptions(n,l);for(let a=e;a<e+i;a++){const e=s.data[a],i=l||r.resolveDataElementOptions(a,n),u=o?0:e.h-2*i.spacing,d=o?0:e.w-2*i.spacing,c={x:e.x+i.spacing,y:e.y+i.spacing,width:d,height:u};h&&(c.options=i),r.updateElement(t[a],a,c,n)}r.updateSharedOptions(l,n,a)}_drawDividers(t,e,i){for(let n=0,r=i.length;n<r;++n){const r=i[n],o=e[n];r.options.groupDividers&&o._data.children.length>1&&D(t,r)}this.getDataset().groupDividers&&D(t,this._rect)}_drawRects(t,e,i,n){for(let r=0,o=i.length;r<o;++r){const o=i[r],s=e[r];if(!o.hidden){o.draw(t);const e=o.options;w(o,e.font)&&s.g&&_(t,o,s,e,n)}}}draw(){const t=this,e=t.chart.ctx,i=t.getMeta().data||[],n=t.getDataset(),r=(n.groups||[]).length-1,o=n.data||[];t._drawRects(e,o,i,r),t._drawDividers(e,o,i)}}function k(t,e){const{x:i,y:n,width:r,height:o}=t.getProps([\"x\",\"y\",\"width\",\"height\"],e);return{le",
		"ft:i,top:n,right:i+r,bottom:n+o}}function C(t,e,i){return Math.max(Math.min(t,i),e)}function j(t){const e=k(t),i=e.right-e.left,n=e.bottom-e.top,r=function(t,e,i){let n,r,s,a;return o(t)?(n=+t.top||0,r=+t.right||0,s=+t.bottom||0,a=+t.left||0):n=r=s=a=+t||0,{t:C(n,0,i),r:C(r,0,e),b:C(s,0,i),l:C(a,0,e)}}(t.options.borderWidth,i/2,n/2);return{outer:{x:e.left,y:e.top,w:i,h:n},inner:{x:e.left+r.l,y:e.top+r.t,w:i-r.l-r.r,h:n-r.t-r.b}}}function E(t,e,i,n){const r=null===e,o=null===i,s=!(!t||r&&o)&&k(t,n);return s&&(r||e>=s.left&&e<=s.right)&&(o||i>=s.top&&i<=s.bottom)}O.id=\"treemap\",O.version=\"1.0.2\",O.defaults={dataElementType:\"treemap\",groupLabels:!0,borderWidth:0,spacing:.5,groupDividers:!1,dividerWidth:1},O.overrides={interaction:{mode:\"point\",intersect:!0},hover:{},plugins:{tooltip:{position:\"treemap\",intersect:!0,callbacks:{title(t){if(t.length){return t[0].dataset.key||\"\"}return\"\"},label(t){const e=t.dataset,i=e.data[t.dataIndex],n=i.g||e.label;return(n?n+\": \":\"\")+i.v}",
		"}}},scales:{x:{type:\"linear\",display:!1},y:{type:\"linear\",display:!1}}},O.afterRegister=function(){const t=e.registry.plugins.get(\"tooltip\");t&&(t.positioners.treemap=function(t){if(!t.length)return!1;return t[t.length-1].element.tooltipPosition()})},O.afterUnregister=function(){const t=e.registry.plugins.get(\"tooltip\");t&&delete t.positioners.treemap};class S extends e.Element{constructor(t){super(),this.options=void 0,this.width=void 0,this.height=void 0,t&&Object.assign(this,t)}draw(t){const e=this.options,{inner:i,outer:n}=j(this);t.save(),n.w!==i.w||n.h!==i.h?(t.beginPath(),t.rect(n.x,n.y,n.w,n.h),t.clip(),t.rect(i.x,i.y,i.w,i.h),t.fillStyle=e.backgroundColor,t.fill(),t.fillStyle=e.borderColor,t.fill(\"evenodd\")):(t.fillStyle=e.backgroundColor,t.fillRect(i.x,i.y,i.w,i.h)),t.restore()}inRange(t,e,i){return E(this,t,e,i)}inXRange(t,e){return E(this,t,null,e)}inYRange(t,e){return E(this,null,t,e)}getCenterPoint(t){const{x:e,y:i,width:n,height:r}=this.getProps([\"x\",\"y\",\"w",
		"idth\",\"height\"],t);return{x:e+n/2,y:i+r/2}}tooltipPosition(){return this.getCenterPoint()}getRange(t){return\"x\"===t?this.width/2:this.height/2}}S.id=\"treemap\",S.defaults={borderSkipped:void 0,borderWidth:void 0,color:void 0,dividerCapStyle:\"butt\",dividerColor:\"black\",dividerDash:void 0,dividerDashOffset:0,dividerWidth:0,font:{},groupDividers:!1,groupLabels:void 0,spacing:void 0,label:void 0,rtl:void 0},S.defaultRoutes={backgroundColor:\"backgroundColor\",borderColor:\"borderColor\"},e.Chart.register(O,S),t.flatten=n,t.group=r,t.index=s,t.isObject=o,t.sort=a,t.sum=l,Object.defineProperty(t,\"__esModule\",{value:!0})}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-chart-treemap.min.js</code> content.
	 */
	TreeMapControllerResource() {
		super(ResourceName.TREEMAP_CONTROLLER, CONTENT);
	}

}

require.config({
	baseUrl:'js/app',
	paths:{
        'jquery':'../lib/jquery.min',
        'echarts':'../lib/echarts',
        'datatool':'../lib/dataTool',
        'bootstrap':'../lib/bootstrap',
        'bootpick':'../lib/bootstrap-datepicker',
        'weathermap':'../lib/WeatherMap',
        'tdtmap':'../lib/tdtmap',
        'tdtmapconn':'../lib/iConnectorTianditu',
        'weahterfont':'../lib/Lang/zh-CN',
        'mapbase':'../lib/SuperMap-8.0.2-13626',
        'whitemap':'../lib/LocalTiledCacheLayerWhiteMap',
        'fillcolor':'../lib/FillColorLayer',
        'fillrangecolor':'../lib/FillRangeColorLayer',
        'vue':'../lib/vue.min',
        'mapUtil':'MapUtil',
        'layerManageUtil':'util/LayerManageUtil',
        'vlegend':'controls/VLegend',
        'fileCacheUtil':'util/FileCacheUtil',
        'gridUtil':'util/GridUtil',
        'jqueryload':'../lib/jquery.shCircleLoader',
        'jquery-ui':'../lib/jquery-ui.min',
        'moment':'../lib/moment-with-locales',
        'displayUtil':'util/DisplayUtil',
        'mapTool':'controls/MapTool',
        'processControl':'controls/ProcessControl',
    },
    shim:{
        'fillrangecolor':{
            'deps':['fillcolor']
        },
        'weahterfont':{
            'deps':['mapbase']
        },
        'tdtmapconn':{
            'deps':['weahterfont']
        },
        "jqueryload":{
			deps:['jquery']
        },
        "jquery-ui":{
			deps:['jquery']
        },
        "bootpick":{
			deps:['jquery','moment']
        },
        /*'datatool':{ 
            'deps':['echarts']
        },*/
    }
});
require(['main','NJY','zdfx','jqueryload'],function(main,njy,zdfx){
    main.init();
    let title=window.document.title;
    if(title=="年季月预测"){
        njy.Init(); 
    }
    else if(title=="诊断分析"){
        zdfx.Init();
    }
});
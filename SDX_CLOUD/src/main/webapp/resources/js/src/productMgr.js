var productMgr=(function(config,functions){


    return {
        searchParams:{
            brand:[],
            category:[],
            date:[],
            color:[],
            size:[]
        },
        loadedData:{},
        showDetail:function(id){
            var data=this.loadedData[id];
            $(".mustSetValue").each(function(index,el){
                $(this).text(data[$(this).data("name")]);
            })
            $(".mustSetSrc").each(function(index,el){
                $(this).attr("src",data[$(this).data("name")]);
            })
            $("#showDetailModal").modal("show");
        }
    }
})(config,functions);

$(document).ready(function(){

    var table=new ZYTableHandler({
        removeUrl:"#",
        ownTable:function(){
            var ownTable=$("#myTable").dataTable({
                "bServerSide": true,
                "sAjaxSource": config.ajaxUrls.productGetAll,
                "bInfo":true,
                "bLengthChange": false,
                "bFilter": false,
                "bSort":false,
                "bAutoWidth": false,
                "iDisplayLength":config.perLoadCounts.table,
                "sPaginationType":"full_numbers",
                "oLanguage": {
                    "sUrl":config.dataTable.langUrl
                },
                "aoColumns": [
                    { "mDataProp": "imageUrl1"},
                    { "mDataProp": "hp_num"},
                    { "mDataProp": "brandList"},
                    { "mDataProp": "categoryList"},
                    { "mDataProp": "timeCategoryList"},
                    { "mDataProp": "dataStatus",
                        "fnRender":function(oObj){
                            return  config.status.product[oObj.aData.dataStatus];
                        }
                    },
                    { "mDataProp": "opt",
                        "fnRender":function(oObj){
                            return  '<a href="'+oObj.aData.id+'" class="check">查看</a>&nbsp;&nbsp;'+
                                '<a href="hpgl/hpManage/productCOU/'+oObj.aData.id+'">编辑</a>&nbsp;&nbsp;'+
                                '<a href="'+oObj.aData.id+'" class="remove">作废</a>';
                        }
                    }
                ] ,
                "fnServerParams": function ( aoData ) {
                    aoData.push({
                        name:"dataCategory",
                        value:$("#searchStatus").val()
                    },{
                        name:"hp_num",
                        value:$("#searchNo").val()
                    },{
                        name:"brand",
                        value:productMgr.searchParams.brand.join(",")
                    },{
                        name:"category",
                        value:productMgr.searchParams.category.join(",")
                    },{
                        name:"date",
                        value:productMgr.searchParams.date.join(",")
                    },{
                        name:"size",
                        value:productMgr.searchParams.size.join(",")
                    },{
                        name:"color",
                        value:productMgr.searchParams.color.join(",")
                    })
                },
                "fnServerData": function(sSource, aoData, fnCallback) {

                    //回调函数
                    $.ajax({
                        "dataType":'json',
                        "type":"get",
                        "url":sSource,
                        "data":aoData,
                        "success": function (response) {
                            if(response.success===false){
                                functions.ajaxReturnErrorHandler(response.message);
                            }else{
                                var json = {
                                    "sEcho" : response.sEcho
                                };

                                productMgr.loadedData={};

                                for (var i = 0, iLen = response.aaData.length; i < iLen; i++) {
                                    response.aaData[i].opt="opt";
                                    response.aaData[i].dataStatus=0;
                                    productMgr.loadedData[response.aaData[i].id]=response.aaData[i];
                                }

                                json.aaData=response.aaData;
                                json.iTotalRecords = response.iTotalRecords;
                                json.iTotalDisplayRecords = response.iTotalDisplayRecords;
                                fnCallback(json);
                            }

                        }
                    });
                },
                "fnFormatNumber":function(iIn){
                    return iIn;
                }
            });

            return ownTable;
        }
    });

    $("#myTable").on("click","a.remove",function(){
        if(confirm(config.messages.confirmDelete)){
            table.delete($(this).attr("href"));
        }
        return false;
    }).on("click","a.check",function(){
            productMgr.showDetail($(this).attr("href"));
            return false;
        });

    $("#searchBtn").click(function(){
        productMgr.searchParams={
            brand:[],
            category:[],
            date:[],
            color:[],
            size:[]
        };

        $("#searchPanel .active").removeClass("active");
        table.tableRedraw();
    });

    $("#searchPanel").on("click",".item",function(){
        var el=$(this);
        var type=el.data("type");
        var id= el.data("id");
        var index;

        $("#searchNo").val("");
        $("#searchStatus").val(0);

        if(el.hasClass("active")){
            index=productMgr.searchParams[type].indexOf(id);
            productMgr.searchParams[type].splice(index,1);
            el.removeClass("active");
        }else{
            productMgr.searchParams[type].push(id);
            el.addClass("active");
        }
        table.tableRedraw();
    });

    $("#searchPanelCtrl").click(function(){
        if($(this).data("target").indexOf("down")!=-1){
            $("#searchPanel .row").hide(400);
            $(this).find(".glyphicon").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
            $(this).data("target","up");
            $(this).find(".text").text("展开选项");
        }else{
            $("#searchPanel .row").show(400);
            $(this).find(".glyphicon").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
            $(this).data("target","down");
            $(this).find(".text").text("收起选项");
        }

    });

});
var guideMgr=(function(config,functions){
    return {
        currentShopId:0,
        initShop:function(){
            $.ajax({
                "dataType":'json',
                "type":"post",
                "url":config.ajaxUrls.shopGetAll,
                "data":{
                    iDisplayLength:100000,
                    iDisplayStart:0,
                    sEcho:1
                },
                "success": function (response) {
                    if(response.success===false){
                        functions.ajaxReturnErrorHandler(response.message);
                    }else{
                        var list=response.aaData,arr=[];

                        for(var i= 0,len=list.length;i<len;i++){
                            arr.push('<li data-id="'+list[i].id+'" class="list-group-item">'+list[i].shopname+'</li>');
                        }

                        $("#allShop").append(arr.join(''));
                    }

                },
                error:function(){
                    functions.ajaxErrorHandler();
                }
            })
        }
    }
})(config,functions);

$(document).ready(function(){

    var table=new ZYTableHandler({
        removeUrl:config.ajaxUrls.guideDelete,
        ownTable:function(){
            var ownTable=$("#myTable").dataTable({
                "bServerSide": true,
                "sAjaxSource": config.ajaxUrls.guideGetAll,
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
                    { "mDataProp": "headicon",
                        "fnRender":function(oObj){
                            return '<img class="thumb" src="'+oObj.aData.headicon+'">';
                        }
                    },
                    { "mDataProp": "username",
                        "fnRender":function(oObj){
                            return oObj.aData.username+"<p>"+oObj.aData.shop.shopname+"</p>";
                        }
                    },
                    { "mDataProp": "valid",
                        "fnRender":function(oObj){
                            return config.status.guide[oObj.aData.valid];
                        }
                    },
                    { "mDataProp": "opt",
                        "fnRender":function(oObj){
                            var removeText="删除";
                            if(oObj.aData.valid==config.status.guide[1]){
                                removeText="启用";
                            }
                            return  '<a href="dggl/match/matchOfGuide/'+oObj.aData.id+'">搭配</a>&nbsp;&nbsp;'+
                                '<a href="dggl/appUser/guideCOU/'+oObj.aData.id+'">编辑</a>&nbsp;&nbsp;'+
                                '<a href="'+oObj.aData.id+'" class="remove">'+removeText+'</a>';
                        }
                    }
                ] ,
                "fnServerParams": function ( aoData ) {
                    aoData.push({
                        name:"username",
                        value:$("#searchName").val()
                    },{
                        name:"shopId",
                        value:guideMgr.currentShopId
                    });
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

                                for (var i = 0, iLen = response.aaData.length; i < iLen; i++) {
                                    response.aaData[i].opt="opt";
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
            table.delete($(this).attr("href"),"userId");
        }
        return false;
    });

    $("#searchBtn").click(function(){
        table.tableRedraw();
    });

    guideMgr.initShop();

    $("#allShop").on('click',".list-group-item:not(.list-group-title)",function(){
        var el=$(this);
        if(el.hasClass("active")){
            el.removeClass("active");
            guideMgr.currentShopId=0;
        }else{
            $("#allShop .active").removeClass("active");
            el.addClass("active");
            guideMgr.currentShopId=el.data("id");
        }

        table.tableRedraw();
    });
});

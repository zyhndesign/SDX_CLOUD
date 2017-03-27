var shopMgr=(function(config,functions){
    return {

    }
})(config,functions);

$(document).ready(function(){

    var table=new ZYTableHandler({
        removeUrl:config.ajaxUrls.shopDelete,
        ownTable:function(){
            var ownTable=$("#myTable").dataTable({
                "bServerSide": true,
                "sAjaxSource": config.ajaxUrls.shopGetAll,
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
                    { "mDataProp": "shopname" },
                    { "mDataProp": "opt",
                        "fnRender":function(oObj){
                            return  '<a href="dggl/shop/shopCOU/'+oObj.aData.id+'">编辑</a>&nbsp;&nbsp;';
                        }
                    }
                ] ,
                "fnServerParams": function ( aoData ) {

                },
                "fnServerData": function(sSource, aoData, fnCallback) {

                    //回调函数
                    $.ajax({
                        "dataType":'json',
                        "type":"post",
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
            table.delete({
                shopId:$(this).attr("href")
            });
        }
        return false;
    });

    $("#searchBtn").click(function(){
        table.tableRedraw();
    });
});

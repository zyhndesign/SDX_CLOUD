var guideMgr=(function(config,functions){

})(config,functions);

$(document).ready(function(){
    var table=new ZYTableHandler({
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
                            return oObj.aData.username+"<p>"+oObj.aData.shopname+"</p>";
                        }
                    },
                    { "mDataProp": "opt",
                        "fnRender":function(oObj){
                            return  '<a href="dggl/appUser/guideCOU/'+oObj.aData.id+'">编辑</a>&nbsp;&nbsp;'+
                                '<a href="'+oObj.aData.id+'" class="remove">删除</a>';
                        }
                    }
                ] ,
                "fnServerParams": function ( aoData ) {
                    aoData.push({
                        name:"name",
                        value:$("#searchName").val()
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
        },
        removeUrl:config.ajaxUrls.guideDelete
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
});

var matchMgr=(function(config,functions){
    return {

    }
})(config,functions);

$(document).ready(function(){

    var table=new ZYTableHandler({
        ownTable:function(){
            var ownTable=$("#myTable").dataTable({
                "bServerSide": true,
                "sAjaxSource": config.ajaxUrls.matchGetAll,
                "bInfo":true,
                "bLengthChange": false,
                "bFilter": false,
                "bProcessing":true,
                "bSort":false,
                "bAutoWidth": false,
                "iDisplayLength":config.perLoadCounts.table,
                "sPaginationType":"full_numbers",
                "oLanguage": {
                    "sUrl":config.dataTable.langUrl
                },
                "aoColumns": [
                    { "mDataProp": "createtime",
                        "fnRender":function(oObj){
                            return functions.formatDate("Y-m-d",oObj.aData.createtime);
                        }
                    },
                    { "mDataProp": "matchlists",
                        "fnRender":function(oObj){
                            return '<img class="thumb" src="'+oObj.aData.matchlists[0].modelurl+'">';
                        }
                    },
                    { "mDataProp": "seriesname",
                        "fnRender":function(oObj){
                            return '<span class="glyphicon glyphicon-heart"></span>24';
                        }
                    }
                ] ,
                "fnServerParams": function ( aoData ) {
                    aoData.push({
                        name:"shareStatus",
                        value:-1
                    })
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

});

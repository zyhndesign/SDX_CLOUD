$(document).ready(function(){
    var treeHandler=new ZYTreeHandler({
        getAllUrl:config.ajaxUrls.seasonGetAll,
        addUrl:config.ajaxUrls.seasonAdd,
        removeUrl:config.ajaxUrls.seasonDelete,
        renameUrl:config.ajaxUrls.seasonUpdate,
        newDefaultName:"新时间"
    })
    $.fn.zTree.init($("#treeDemo"), treeHandler.getSettings());

});


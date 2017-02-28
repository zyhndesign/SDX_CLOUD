$(document).ready(function(){
    var treeHandler=new ZYTreeHandler({
        getAllUrl:config.ajaxUrls.brandGetAll,
        addUrl:config.ajaxUrls.brandAdd,
        removeUrl:config.ajaxUrls.brandDelete,
        renameUrl:config.ajaxUrls.brandUpdate,
        newDefaultName:"新系列"
    })
    $.fn.zTree.init($("#treeDemo"), treeHandler.getSettings());

});


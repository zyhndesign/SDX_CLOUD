$(document).ready(function(){
    var treeHandler=new ZYTreeHandler({
        getAllUrl:config.ajaxUrls.colorGetAll,
        addUrl:config.ajaxUrls.categoryAdd,
        removeUrl:config.ajaxUrls.categoryDelete,
        renameUrl:config.ajaxUrls.categoryUpdate,
        newDefaultName:"新颜色"
    })
    $.fn.zTree.init($("#treeDemo"), treeHandler.getSettings());

});

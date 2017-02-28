$(document).ready(function(){
    var treeHandler=new ZYTreeHandler({
        getAllUrl:config.ajaxUrls.categoryGetAll,
        addUrl:config.ajaxUrls.categoryAdd,
        removeUrl:config.ajaxUrls.categoryDelete,
        renameUrl:config.ajaxUrls.categoryUpdate,
        newDefaultName:"新品类"
    })
    $.fn.zTree.init($("#treeDemo"), treeHandler.getSettings());

});

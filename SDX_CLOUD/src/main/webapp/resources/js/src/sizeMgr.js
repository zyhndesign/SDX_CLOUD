$(document).ready(function(){
    var treeHandler=new ZYTreeHandler({
        getAllUrl:config.ajaxUrls.sizeGetAll,
        addUrl:config.ajaxUrls.sizeAdd,
        removeUrl:config.ajaxUrls.sizeDelete,
        renameUrl:config.ajaxUrls.sizeUpdate,
        newDefaultName:"新尺寸"
    })
    $.fn.zTree.init($("#treeDemo"), treeHandler.getSettings());

});

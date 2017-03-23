function ZYTableHandler(params) {
    this.ownTable = params.ownTable();
    this.removeUrl=params.removeUrl;
}
ZYTableHandler.prototype.tableRedraw = function () {
    this.ownTable.fnSettings()._iDisplayStart = 0;
    this.ownTable.fnDraw();
};
ZYTableHandler.prototype.delete = function (id,paramName) {
    functions.showLoading();
    var me = this;
    paramName=paramName||"id";
    $.ajax({
        url: me.removeUrl + "?"+paramName+"=" + id,
        type: "post",
        dataType: "json",
        success: function (response) {
            if (response.success) {
                $().toastmessage("showSuccessToast", config.messages.optSuccess);
                me.ownTable.fnDraw();
                functions.hideLoading();
            } else {
                functions.ajaxReturnErrorHandler(response.error_code);
            }

        },
        error: function () {
            functions.ajaxErrorHandler();
        }
    });
};
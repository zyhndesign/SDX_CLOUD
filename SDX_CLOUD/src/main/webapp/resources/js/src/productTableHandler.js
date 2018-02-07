function ZYTableHandler(params) {
	this.ownTable = params.ownTable();
    this.removeUrl=params.removeUrl;
}

ZYTableHandler.prototype.tableRedraw = function () {
    this.ownTable.fnSettings()._iDisplayStart = 0;
    this.ownTable.fnDraw();
};

ZYTableHandler.prototype.load = function (page) {
    this.ownTable.fnSettings()._iDisplayStart = (page - 1)*10;
    this.ownTable.fnDraw();
};

ZYTableHandler.prototype.delete = function (param) {
    functions.showLoading();
    var me = this;
    $.ajax({
        url: me.removeUrl,
        type: "post",
        data:param,
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
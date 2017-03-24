function ZYFormHandler(params){
    this.submitUrl=params.submitUrl;
    this.redirectUrl=params.redirectUrl;
}
/**
 * form控件提交form，form-data
 * @param form
 * @param data 额外的参数
 * @param isJsonString 是否采用json字符串形式
 */
ZYFormHandler.prototype.submitForm=function(form,data,isJsonString){
    var me=this;
    functions.showLoading();
    if(isJsonString){
        $.ajax({
            dataType:"json",
            type:"post",
            contentType :"application/json; charset=UTF-8",
            url:$(form).attr("action"),
            data:data,
            success:function(response){
                if(response.success){
                    $().toastmessage("showSuccessToast",config.messages.optSuccess);
                    setTimeout(function(){
                        window.location.href=me.redirectUrl;
                    },3000);
                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        })
    }else{
        $(form).ajaxSubmit({
            dataType:"json",
            data:data,
            success:function(response){
                if(response.success){
                    $().toastmessage("showSuccessToast",config.messages.optSuccess);
                    setTimeout(function(){
                        window.location.href=me.redirectUrl;
                    },3000);
                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        });
    }
}
/**
 * 用ajax提交form，数据以json字符串的形式提交
 * @param form
 */
ZYFormHandler.prototype.submitFormWithPS=function(form){
    var me=this,
        formObj=$(form).serializeObject();
    //formObj.intro=tinyMCE.editors[0].getContent();
    functions.showLoading();
    $.ajax({
        url:me.submitUrl,
        type:"post",
        dataType:"json",
        contentType :"application/json; charset=UTF-8",
        data:JSON.stringify(formObj),
        success:function(response){
            if(response.success){
                $().toastmessage("showSuccessToast",config.messages.optSuccRedirect);
                setTimeout(function(){
                    window.location.href=document.getElementsByTagName('base')[0].href+me.redirectUrl;
                },3000);
            }else{
                functions.ajaxReturnErrorHandler(response.error_code);
            }
        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    });
}

$(".reply-btn").click(function(){
    if($(this).parent().parent().find(".replybox").length > 0){
        $(".replybox").remove();
    }else{
        $(".replybox").remove();
        replyClick($(this));
    }
    var cid

});
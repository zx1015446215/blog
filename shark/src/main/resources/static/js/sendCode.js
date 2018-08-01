const SendCodeUrl = "http://47.106.213.88:8884/sendCode";
// const SendCodeUrl = "http://localhost:8884/sendCode";

$('#regis_code').click(function () {
    var email = $('#email').val();
    var reg = /\w+[@]{1}\w+[.]\w+/;
    if (!!!reg.test($('#email').val())) {
        alert('请输入正确的email地址');
        return;
    } else {
        alert("邮件发送成功，请注意查收");
        $.ajax({
            url: SendCodeUrl,
            type: 'POST',
            datatype: "json",
            data: {
                email: email
            },
            success: function (res) {
              trueCode=res;
            },
            error : function () {
                alert("邮件发送失败");
            }
        });
    }
});

const SendCodeUrl = "http://localhost:8884/sendCode";

$('#regis_code').click(function () {
    var email = $('#email').val();
    var reg = /\w+[@]{1}\w+[.]\w+/;
    if (!!!reg.test($('#email').val())) {
        alert('请输入正确的email地址');
        return;
    } else {
        $.ajax({
            url: SendCodeUrl,
            type: 'POST',
            data: {
                email: email,
            },
            success: function (res) {
              trueCode=res;
                alert("邮件发送成功，请注意查收");
            },
        });
    }
});

const  loginUrl="http://localhost:8884/index/login"

$(document).ready(function () {
    $("#login_btn").on('click',function(){login();});

});


function login() {
    var $k = $('input');
    // console.log($k);
    for (let i = 0; i < $k.length; i++) {
        if ($k[i].value === '') {
            alert('请输入完整的信息');
            return;
        }
    }
  var name = $("#username").val(),
    pass = $("#password").val();

  $.ajax({
    url: loginUrl,
    type: "POST",
    data: {
      username: name,
      password: pass,
      role : 'user'
    },
    error: function(e) {
      console.log("error", e.statusText);
    },
    success: function(res) {
      var num = res.status;
      if (num === 200) {
        alert("登录成功，即将跳转到主界面")
        location.href = "/yummy/index_v1";
      } else if (num === 500) {
        var nb = res.msg;
        if (nb == 110) {
          alert("密码错误，请重新填写");
        } else if (nb == 120) {
          alert("用户名错误，请重新填写");
        }
      }
    },
    complete: function(d) {
      console.log("done");
    }
  });
}

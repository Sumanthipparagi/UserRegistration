$(document).ready(function() {


    $("#save").click(function (e) {
        e.preventDefault();
        let obj = {
            loginId: $("#LoginId").val(),
            password: $("#password").val(),
        };


        if (obj.loginId == "") {

            return false;
        }

        if (obj.password == "") {
            alert("Please Enter password");
            return false;
        }



        $.ajax({
            url: `/userregistration/login?loginId=${obj.loginId}&password=${obj.password}`,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                let results = data;
                console.log(results);

                if (results.status == "success") {

                    Swal.fire({
                        title: 'login sucessfully',
                        showClass: {
                            popup: 'animate__animated animate__fadeInDown'
                        },
                        hideClass: {
                            popup: 'animate__animated animate__fadeOutUp'
                        }
                    })
                    window.location.href='/userregistration/view';
                } else {
                    Swal.fire({
                        title: 'login failed',
                        showClass: {
                            popup: 'animate__animated animate__fadeInDown'
                        },
                        hideClass: {
                            popup: 'animate__animated animate__fadeOutUp'
                        }
                    })

                }


            },
            error: function (err) {
                console.log(err);
            },
        });
    });


});

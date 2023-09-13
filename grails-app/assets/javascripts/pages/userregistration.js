$(document).ready(function () {
    let id = null;


    getDesignation();



    // if data exist

    let userDetails = localStorage.getItem("userDetails");
    if (userDetails != null) {
        userDetails = JSON.parse(userDetails);
        id = userDetails.id;
        $("#Name").val(userDetails.name.trim());
        $("#Loginid").val(userDetails.loginid);

        $("#Address").val(userDetails.address);
        $("#Mobileno").val(userDetails.mobileno);

        localStorage.removeItem("userDetails");
    }

    $("#save").click(function (e) {

        e.preventDefault();
        let obj = {
            id: id,
            name: $("#Name").val(),     //#Name etc is the id given to particular input function in html
            loginId: $("#Loginid").val(),        //name, loginId, password, address, mobileNo should be
            password: $("#Password").val(),      //in same case as of UserRegistrationServices page in
            address: $("#Address").val(),        //grails
            mobileNo: $("#Mobileno").val(),
            designation: $("#designation").val()
        };

        if (obj.name == "") {
            alert("Please Enter UserName");
            return false;
        }
        if (obj.loginId == "") {
            alert("Please Enter loginid");
            return false;
        }
        if (obj.password == "" && id== null) {
            alert("Please Enter password");
            return false;
        }
        if (obj.address == "") {
            alert("Please Enter address");
            return false;
        }
        if (obj.mobileNo == "") {
            alert("Please Enter no");
            return false;
        }

        if (obj.designation == "") {
            alert("Please Enter designation");
            return false;
        }

        let type = "POST";
        if (id) {
            type = "PUT";
        }

        $.ajax({
            url: "http://localhost:8085/userregistration",
            type: type,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(obj),
            success: function (data) {
                let results = data;
                console.log(results);

                if (results.status == "success") {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: 'Your work has been saved',
                        showConfirmButton: false,

                    })
                } else {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'warning',
                        title: results.status,
                        showConfirmButton: false,
                    })
                }


            },
            error: function (err) {
                console.log(err);
            },
        });
    });

    $("#edit").click(function () {
        var rowIndex = $(this).closest("tr").index();
        window.location.href = "table.html?rowIndex=" + rowIndex;
       // window.location.href='/userregistration/viewTable';
    });



});


function getDesignation() {
    //get designation data

    $.ajax({
        url: "http://localhost:8085/designationmaster",
        type: "GET",
        contentType: "application/json; charset=utf-8",

        success: function (data) {
            console.log("designation", data);

            const designationdropdown = $("#designation");
            designationdropdown.empty();

            designationdropdown.append("<option value=''> Please Select a Designation </option>");

            data.results.forEach((data) => {
                const row = `      
                        <option value=${data.id}>${data.designation}</option>
                    `;
                designationdropdown.append(row);
            });

            $('#designation').select2();
        },
        error: function (err) {
            console.log(err);
        },
    });
}


$(document).ready(function () {
    let id = null;


    getGenre();



    // if data exist

    let bookDetails = localStorage.getItem("bookDetails");
    if (bookDetails != null) {
        bookDetails = JSON.parse(bookDetails);
        id = bookDetails.id;
        $("#book-name").val(bookDetails.bookName.trim());
        $("#author").val(bookDetails.authorName);

        $("#book-price").val(bookDetails.bookPrice);
        // $("#Mobileno").val(userDetails.mobileno);

        localStorage.removeItem("bookDetails");
    }

    $("#submit").click(function (e) {

        e.preventDefault();
        let obj = {
            id: id,
            bookName: $("#book-name").val(),     //#Name etc is the id given to particular input function in html
            authorName: $("#author").val(),        //name, loginId, password, address, mobileNo should be
            genre: $("#genre").val(),      //in same case as of UserRegistrationServices page in
            bookPrice: $("#book-price").val(),        //grails
        };

        if (obj.bookName == "") {
            alert("Please Enter BookName");
            return false;
        }
        if (obj.authorName == "") {
            alert("Please Enter authorName");
            return false;
        }

        if (obj.genre == "") {
            alert("Please Select Genre");
            return false;
        }
        if (obj.bookPrice == "") {
            alert("Please Enter bookPrice");
            return false;
        }

        let type = "POST";
        if (id) {
            type = "PUT";
        }

        $.ajax({
            url: "http://localhost:8085/bookdetails",
            type: type,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(obj),
            success: function (data) {
                let results = data;
                console.log(results);

                if (results.status == "success") {

                    Swal.fire({
                        title: "Good job!",
                        text: "You have saved the book!",
                        icon: "success",
                    })

                    // Swal.fire({
                    //   position: 'centre',
                    //   icon: 'success',
                    //   title: 'Your work has been saved',
                    //   showConfirmButton: false,
                    // })

                } else {
                    Swal.fire({
                        position: 'centre',
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
        window.location.href = "usertable.html?rowIndex=" + rowIndex;
    });



});


function getGenre() {
    //get designation data

    $.ajax({
        url: "http://localhost:8085/genremaster",
        type: "GET",
        contentType: "application/json; charset=utf-8",

        success: function (data) {
            console.log("genre", data);

            const genredropdown = $("#genre");
            genredropdown.empty();

            genredropdown.append("<option value=''> Please Select a Genre </option>");

            data.results.forEach((data) => {
                const row = `      
                        <option value=${data.id}>${data.genre}</option>
                    `;
                genredropdown.append(row);
            });

            $('#genre').select2();
        },
        error: function (err) {
            console.log(err);
        },
    });
}


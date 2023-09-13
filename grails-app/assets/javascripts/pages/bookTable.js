
$(document).ready(function () {
    function usertable() {
        $.ajax({
            url: "http://localhost:8085/bookdetails",
            method: "GET",
            success: function (data) {
                console.log(data);
                const tableBody = $("#userTbBody");
                tableBody.empty();

                data.results.forEach((item) => {
                    const row = `  
                    <tr>
                      <td  data-id="${item.id}">
                      ${item.bookName}</td>
                      <td>${item.authorName}</td>
                      <td data-genre="${item.genreId}">${item.genreName}</td>
                      <td>${item.bookPrice}</td>
                      <td><button class="edit-button" data-id="${item.id}">Edit</button></td>
                      <td><button class="delete-button" data-id="${item.id}">Delete</button></td>
                    </tr>
                  `;
                    tableBody.append(row);
                });

                $("#userTable").DataTable();


                $("#userTable tbody tr .edit-button").click(function () {
                    let obj = {
                        id: $(this).closest("tr").find("td:eq(0)").data("id"),
                        bookName: $(this).closest("tr").find("td:eq(0)").text(),
                        authorName: $(this).parent().parent().find("td:eq(1)").text(),
                        genre: $(this).parent().parent().find("td:eq(2)").data("genre"),
                        bookPrice: $(this).parent().parent().find("td:eq(3)").text(),
                    };

                    localStorage.setItem("bookDetails", JSON.stringify(obj));
                    window.location.href = "book.html";
                });

                $("#userTable tbody tr .delete-button").click(function () {
                    Swal.fire({
                        title: "Are you sure?",
                        text: "You won't be able to revert this!",
                        icon: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#3085d6",
                        cancelButtonColor: "#d33",
                        confirmButtonText: "Yes, delete it!",
                    }).then((result) => {
                        if (result.isConfirmed) {
                            let id = $(this).closest("tr").find("td:eq(0)").data("id");

                            if (!id) {
                                return false;
                            }

                            let url = `http://localhost:8085/bookdetails?id=${id}`;

                            $.ajax({
                                url: url,
                                type: "DELETE",
                                contentType: "application/json; charset=utf-8",
                                success: function (data) {
                                    Swal.fire(
                                        "Deleted!",
                                        "Deleted Succesfully.",
                                        "success"
                                    );
                                    usertable();
                                },
                                error: function (err) {
                                    console.log(err);
                                },
                            });
                        }
                    });
                });
            },

            error: function (error) {
                console.error("Error fetching data:", error);
            },
        });
    }
    usertable();
});






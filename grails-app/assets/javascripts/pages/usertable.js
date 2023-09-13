/** @format */

$(document).ready(function () {
    function usertable() {
        $.ajax({
            url: "http://localhost:8085/userregistration",
            method: "GET",
            success: function (data) {
                console.log(data);
                const tableBody = $("#userTbBody");
                tableBody.empty();

                data.results.forEach((item) => {
                    const row = `  
                    <tr >
                      <td  data-id="${item.id}">
                      ${item.name}</td>
                      <td>${item.loginid}</td>
                      <td>${item.address}</td>
                      <td>${item.mobileno}</td>
                      <td data-designation="${item.designationId}">${item.designationName}</td>
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
                        name: $(this).closest("tr").find("td:eq(0)").text(),
                        loginid: $(this).parent().parent().find("td:eq(1)").text(),
                        address: $(this).parent().parent().find("td:eq(2)").text(),
                        mobileno: $(this).parent().parent().find("td:eq(4)").text(),
                        designation: $(this).parent().parent().find("td:eq(3)").data("designation"),

                    };

                    localStorage.setItem("userDetails", JSON.stringify(obj));
                    window.location.href='/userregistration/view';


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

                            let url = `http://localhost:8085/userregistration?id=${id}`;

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






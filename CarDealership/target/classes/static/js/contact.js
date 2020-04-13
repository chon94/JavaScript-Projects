
function makeContact() {
    let name = $("#name").val(),
        email = $("#email").val(),
        phone = $("#phone").val(),
        message = $("#message").val();

    let url = "/message";
    console.log(url);

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify({

            name: name,
            email: email,
            phone: phone,
            message: message
        }),

        success: function (contact) {
            alert("Contact Made");
            console.log(contact);
        },
        error: function () {
            alert("Error With Contact");
        }
    });

}
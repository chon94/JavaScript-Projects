$(document).ready(function() {
  getAllSpecials();
});

let specialsContainer = $(".specials");

function getAllSpecials() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/special/all",
    success: function(specials) {
      loadSpecials(specials);
    },
    error: function() {
      alert("Error With Specials");
    }
  });
}

function loadSpecials(specials) {
  specialsContainer.empty();
  specials.forEach(special => {
    specialsContainer.append(`<div class="special">
        <div class="special-details">
          <h3 class="special-title">${special.title}</h3>
          <P class="special-description"
            >${special.description}</P
          >
        </div>
      </div>`);
  });
}

  // $(function (){

  //   var $specials = $('#specials');
  //   $.ajax({
  //     type: "GET",
  //     url: "http://localhost:8080/special/all",
  //     success: function(specials) {
  //       $.each(specials, function(i, special){
  //         $specials.append('<li>title: '+ special.name +' description ' + special.description +'</li>');
  //       });
  //     }
  //   });
  // });


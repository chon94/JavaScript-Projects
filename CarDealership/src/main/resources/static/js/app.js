$(document).ready(function() {
    getAllSpecials();
    getFeaturedVehicles();
    getCurrentUser();
  });
  
  // Declare variables
  let specialsContainer = $(".specials-container");
  let featuresContainer = $(".features-display");
  
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
  
  function getFeaturedVehicles() {
    featuresContainer.empty();
  
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/vehicle/featured",
      success: function(vehicles) {
        loadFeatured(vehicles);
      },
      error: function() {
        alert("Error With Vehicles");
      }
    });
  }
  
  function loadSpecials(specials) {
    for (i = 0; i < specials.length; i++) {
      if (i == 0) {
        specialsContainer.append(
          `<div class="carousel-item active special">
                <img
                  src="../images/${specials[i].vehicle.image}"
                  class="d-block w-100 special-img"
                  alt="vehicle image"
                />
                <div class="carousel-caption d-none d-md-block special-details">
                  <h5 class="special-title">${specials[i].title}</h5>
                  <p class="special-details">${specials[i].description}</p>
                </div>
              </div>`
        );
      } else {
        specialsContainer.append(
          `<div class="carousel-item special">
                <img
                  src="../images/${specials[i].vehicle.image}"
                  class="d-block w-100 special-img"
                  alt="vehicle image"
                />
                <div class="carousel-caption d-none d-md-block special-details">
                  <h5 class="special-title">${specials[i].title}</h5>
                  <p class="special-details">${specials[i].description}</p>
                </div>
              </div>`
        );
      }
    }
  }
  
  function loadFeatured(features) {
    features.forEach(feature => {
      console.log(feature);
      featuresContainer.append(`<div class="feature">
      <img
        class="feature-img"
        src="https://www.bing.com/th?id=OIP.7v-dqWXEcGp7PtRn4yd_1AHaE7&pid=Api&rs=1"
        alt="vehicle-image"
      />
      <div class="feature-details">
      <h3 style="font-size: 40px" class="feature-title1">${
        feature.year
      }</h3>
        <h3 style="font-size: 40px" class="feature-title1">${
          feature.make.make
        }</h3>
        <h3  style="font-size: 2em" class="feature-title2">${
          feature.model.model
        }</h3>
        <a href="inventory/details.html#${feature.vehicleId}"
          ><button type="button" class="detail-btn btn btn-info">
            Details
          </button></a>
      </div>
    </div>`);
    });
  }
  

$(document).ready(function() {
    let id = window.location.hash.substring(1);
    console.log(id);
    getDetails(id);
  });
  
  function getDetails(vehicleId) {
    console.log(vehicleId);
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/vehicle/${vehicleId}`,
      success: function(vehicle) {
        loadDetails(vehicle);
      },
      error: function () {
        alert("Error With Vehicle Description Search");
    }
    });
  }

  function loadDetails(vehicle) {
    let detailsContainer = $("#detail-wrap");
    console.log(vehicle);
    detailsContainer.empty();
  
    if (vehicle.sold == false) {
      detailsContainer.append(`<div class="vehicle">
    <div class="vehicle-title">
      <h3 class="title-item vehicle-year">${vehicle.year}</h3>
      <h3 class="title-item vehicle-make">${vehicle.make.make}</h3>
      <h3 class="title-item vehicle-model">${vehicle.model.model}</h3>
    </div>
    <div class="vehicle-details-container">
      <img class="vehicle-img" src="https://www.bing.com/th?id=OIP.7v-dqWXEcGp7PtRn4yd_1AHaE7&pid=Api&rs=1" alt="shoe" />
      <div class="vehicle-details">
        <div class="detail-key-value">
          <label class="label" for="bodyStyle">Body Style:</label>
          <p id="bodyStyle" class="body-style">${vehicle.bodyStyle}</p>
        </div>
        <div class="detail-key-value">
          <label for="transmission" class="label">Trans:</label>
          <p id="transmission" class="body-style">${vehicle.transmission}</p>
        </div>
        <div class="detail-key-value">
          <label for="exteriorColor" class="label">Color:</label>
          <p id="exteriorColor" class="body-style">${vehicle.color}</p>
        </div>
      </div>
      <div class="vehicle-details">
        <div class="detail-key-value">
          <label for="interiorColor" class="label">Interior:</label>
          <p id="interiorColor" class="body-style">${vehicle.interior}</p>
        </div>
        <div class="detail-key-value">
          <label for="mileage" class="label">Mileage:</label>
          <p id="mileage" class="body-style">${vehicle.mileage}</p>
        </div>
        <div class="detail-key-value">
          <label for="vin" class="label">Vin #:</label>
          <p id="vin" class="body-style">${vehicle.vin}</p>
        </div>
      </div>
      <div id="vehicle-details" class="vehicle-details">
        <div for="listPrice" class="detail-key-value">
          <label class="label">Sale Price:</label>
          <p id="listPrice" class="body-style">${vehicle.salePrice}</p>
        </div>
        <div class="detail-key-value">
          <label for="msrp" class="label">MSRP:</label>
          <p id="msrp" class="body-style">${vehicle.msrp}</p>
        </div>
        <a href="/contact.html#${vehicle.vehicleId}">
        <button type="button" class="btn btn-info">
            Contact
          </button></a>       
      </div>
    </div>
  </div>
  
  <div id="description-value" class="description-value">
        <label>Vehicle Description: </label>
        <h4>${vehicle.description}</h4>
        </div>`);

    } else {
      detailsContainer.append(`<div class="vehicle">
    <div class="vehicle-title">
      <h3 class="title-item vehicle-year">${vehicle.year}</h3>
      <h3 class="title-item vehicle-make">${vehicle.make.make}</h3>
      <h3 class="title-item vehicle-model">${vehicle.model.model}</h3>
    </div>
    <div class="vehicle-details-container">
      <img class="vehicle-img" src="https://www.bing.com/th?id=OIP.7v-dqWXEcGp7PtRn4yd_1AHaE7&pid=Api&rs=1" alt="shoe" />
      <div class="vehicle-details">
        <div class="detail-key-value">
          <label class="label" for="bodyStyle">Body Style:</label>
          <p id="bodyStyle" class="body-style">${vehicle.bodyStyle}</p>
        </div>
        <div class="detail-key-value">
          <label for="transmission" class="label">Trans:</label>
          <p id="transmission" class="body-style">${vehicle.transmission}</p>
        </div>
        <div class="detail-key-value">
          <label for="exteriorColor" class="label">Color:</label>
          <p id="exteriorColor" class="body-style">${vehicle.color}</p>
        </div>
      </div>
      <div class="vehicle-details">
        <div class="detail-key-value">
          <label for="interiorColor" class="label">Interior:</label>
          <p id="interiorColor" class="body-style">${vehicle.interior}</p>
        </div>
        <div class="detail-key-value">
          <label for="mileage" class="label">Mileage:</label>
          <p id="mileage" class="body-style">${vehicle.mileage}</p>
        </div>
        <div class="detail-key-value">
          <label for="vin" class="label">Vin #:</label>
          <p id="vin" class="body-style">${vehicle.vin}</p>
        </div>
      </div>
      <div id="vehicle-details" class="vehicle-details">
        <div for="listPrice" class="detail-key-value">
          <label class="label">Sale Price:</label>
          <p id="listPrice" class="body-style">${vehicle.salePrice}</p>
        </div>
        <div class="detail-key-value">
          <label for="msrp" class="label">MSRP:</label>
          <p id="msrp" class="body-style">${vehicle.msrp}</p>
        </div><p style="font-weight: bold">SOLD</p>
      </div>
    </div>
  </div>
  
  <div id="description-value" class="description-value">
        <label>Vehicle Description: </label>
        <p>${vehicle.description}</p>
        </div>`);
    }
  }
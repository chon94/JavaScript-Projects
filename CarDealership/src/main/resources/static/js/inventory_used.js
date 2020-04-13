// var newCarContainer = $("#newCarContainer");

let usedInventoryContainer = $(".usedInventories");

function getUsedVehicles() {
    let makeModelYear = $("#query").val(),
        minPrice = $("#price-min").val(),
        maxPrice = $("#price-max").val(),
        minYear = $("#year-min").val(),
        maxYear = $("#year-max").val()
        

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/vehicle/used",
        contentType: "application/json",
        data: JSON.stringify({

            makeModelYear: makeModelYear,
            minPrice: minPrice,
            maxPrice: maxPrice,
            minYear: minYear,
            maxYear: maxYear            
        }),

        success: function (usedVehicle) {
            alert("Used Vehicles Search Completed");
            // render(usedVehicle);
            loadUsedInventory(usedVehicle);
        },
        error: function () {
            alert("Error With Used Vehicle Search");
        }
    });

}

function loadUsedInventory(vehicles) {
    usedInventoryContainer.empty();
    vehicles.forEach(vehicle => {
        usedInventoryContainer.append(`<div class="newInventory">
        <div class="vehicle-title">
        <h3 class="title-item vehicle-year">${vehicle.year}</h3>
        <h3 class="title-item vehicle-make">${vehicle.make.make}</h3>
        <h3 class="title-item vehicle-model">${vehicle.model.model}</h3>
      </div>
        
        
        <div class="vehicle-details-container">
        <img src="https://www.bing.com/th?id=OIP.7v-dqWXEcGp7PtRn4yd_1AHaE7&pid=Api&rs=1" class="vehicle-img">

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
    <div class="vehicle-details">
      <div for="listPrice" class="detail-key-value">
        <label class="label">Sale Price:</label>
        <p id="listPrice" class="body-style">${vehicle.salePrice}</p>
      </div>
      <div class="detail-key-value">
        <label for="msrp" class="label">MSRP:</label>
        <p id="msrp" class="body-style">${vehicle.msrp}</p>
      </div>

        <a href="details.html#${vehicle.vehicleId}"
          ><button type="button" class="detail-btn btn btn-info">
            Details
          </button></a>
            </div>
          </div>
        </div>`);
    });
  }

  
  
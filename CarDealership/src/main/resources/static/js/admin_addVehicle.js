$(document).ready(function() {
    getMakes();
  });
  
  function getMakes() {
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/make/all`,
      success: function(makes) {
        getModelsByMake(loadMakes(makes));
      },
      error: function(err) {
        alert("error with makes");
      }
    });
  }

  function loadMakes(makes) {
    let makesDropdown = $("#make");
    makesDropdown.empty();
    makes.forEach(make => {
      makesDropdown.append(`<option value="${make.makeId}">
              ${make.make}
            </option>`);
    });
    return makes[0].makeId;
  }
  
  function getModelsByMake(makeId) {
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/model/all`,
      success: function(models) {
        loadModels(models);
      },
      failure: function(err) {
        console.log(err);
      }
    });
  }
  
  function getModelsByMakeId() {
    let makeId = $("#make option:selected").val();
  
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/model/all/${makeId}`,
      success: function(models) {
        loadModels(models);
      },
      failure: function(err) {
        console.log(err);
      }
    });
  }
  
  $("#make").on("select", getModelsByMakeId());
  
  function loadModels(models) {
    let modelDropdown = $("#model");
    modelDropdown.empty();
    models.forEach(model => {
      modelDropdown.append(`<option value="${model.modelId}">
        ${model.model}
      </option>`);
    });
  }
  
  // Add vehicle
  
  function addVehicle() {
    let data = {
      make: { makeId: $("#make option:selected").val() },
      model: {
        modelId: $("#model option:selected").val(),
        model: $("#model option:selected")
          .text()
          .trim()
      },
      type: $("#type option:selected").val(),
      bodyStyle: $("#body-style").val(),
      year: $("#year").val(),
      transmission: $("#transmission option:selected").val(),
      color: $("#exterior-color option:selected").val(),
      interior: $("#interior-color option:selected").val(),
      mileage: $("#mileage").val(),
      vin: $("#vin").val(),
      msrp: $("#msrp").val(),
      salePrice: $("#sale-price").val(),
      description: $("#description").val(),
      photo: $("#picture").val()
    };
  
    let url = `http://localhost:8080/vehicle/build`;
  
    console.log(data);
  
    $.ajax({
      type: "POST",
      url: url,
      data: JSON.stringify(data),
      dataType: "json",
      headers: { Accept: "application/json", "Content-Type": "application/json" },
      success: function(vehicle) {
        alert("vehicle added");
        console.log(vehicle);
      },
      error: function(err) {
        alert("vehicle add fail");
        console.log(err);
      }
    });
  }
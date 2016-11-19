/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Document ready function
$(document).ready(function () {
    loadFish();

    // Add new fish via Ajax
    $('#add-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'fish',
            data: JSON.stringify({
                name: $('#add-name').val(),
                scientificName: $('#add-scientific-name').val(),
                pictureURL: $('#add-picture-url').val(),
                waterType: $('#fresh-water-radio').is(':checked') ? "Freshwater" :
                        $('#salt-water-radio').is(':checked') ? "Saltwater" : ""
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            // If call is successful, clear form and reload summary fish table
            $('#validationErrors').hide();
            $('#add-name').val('');
            $('#add-scientific-name').val('');
            $('#add-picture-url').val('');
            $('#fresh-water-radio').prop("checked", false);
            $('#salt-water-radio').prop("checked", false);
            loadFish();
        }).error(function (data, status) {
            var errorDiv = $('#validationErrors');
            errorDiv.empty();
            errorDiv.show();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errorDiv.append(validationError.message);
                errorDiv.append('<br>');
            });
        });
    });
// edit fish via Ajax
    $('#edit-button').click(function (event) {
        // prevent button press from submitting the whole page
        event.preventDefault();
        var id = $('#edit-id').val();

        $.ajax({
            type: 'PUT',
            url: 'fish/' + id,
            data: JSON.stringify({
                id: $('#edit-id').val(),
                name: $('#edit-name').val(),
                scientificName: $('#edit-scientific-name').val(),
                pictureURL: $('#edit-picture-url').val(),
                waterType: $('#edit-fresh-water-radio').is(':checked') ? "Freshwater" :
                        $('#edit-salt-water-radio').is(':checked') ? "Saltwater" : ""
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadFish();
        });
    });

    $('#search-button').click(function (e) {
        e.preventDefault();
        searchFish();
    });
// This code runs in response to the show.bs.modal event - it gets the correct
// fish data and renders it to the dialog
    $('#detailsModal').on('show.bs.modal', function (event) {
        // Get the element that triggered this event 
        var element = $(event.relatedTarget);
        var id = element.data('fish-id');
        var modal = $(this);
        // make an ajax call to get fish information for id
        $.ajax({
            type: 'GET',
            url: 'fish/' + id
        }).success(function (fish) {
            modal.find('#id').text(fish.id);
            modal.find('#fish-name').text(fish.name);
            modal.find('#fish-scientific-name').text(fish.scientificName);
            modal.find('#fish-picture-url').text(fish.pictureURL);
            modal.find('#fish-water-type').text(fish.waterType);
            modal.find('#fish-picture').empty();
            modal.find('#fish-picture').append($('<img>')
                    .attr({
                        'class': 'img-responsive',
                        'src': fish.pictureURL
                    })
                    );
        });
    });

    $('#editModal').on('show.bs.modal', function (event) {

        var element = $(event.relatedTarget);
        var id = element.data('fish-id');

        var modal = $(this);

        $.ajax({
            type: 'GET',
            url: 'fish/' + id
        }).success(function (fish) {
            modal.find('#fish-id').text(fish.id);
            modal.find('#edit-id').val(fish.id);
            modal.find('#edit-name').val(fish.name);
            modal.find('#edit-scientific-name').val(fish.scientificName);
            modal.find('#edit-picture-url').val(fish.pictureURL);
            fish.waterType === "Freshwater" ? $('#edit-fresh-water-radio').prop("checked", true) : $('#edit-salt-water-radio').prop("checked", true);
        });
    });



});

//==========
// FUNCTIONS
//==========
// Load fish into the summary table
function loadFish() {
    // Clear the previous list
    clearFishTable();

    // Make an Ajax GET call to the 'allfish' endpoint. Iterate through all
    // JSON objects and render them to the summary table
    $.ajax({
        url: 'allfish'
    }).success(function (data, status) {
        fillFishTable(data, status);
    });
}

// Clear all content rows from the summary table
function clearFishTable() {
    $('#contentRows').empty();
}

// function to delete a fish
function deleteFish(id) {
    var answer = confirm("Do you really want to delete this fish?");

    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'fish/' + id
        }).success(function () {
            loadFish();
        });
    }
}

// search for fish
function searchFish() {

    searchTerm = $('#search-name').val();

    if (searchTerm) {

        $.ajax({
            type: 'GET',
            url: 'search/' + searchTerm
        }).success(function (data, status) {
            $('#search-name').val('');
            fillFishTable(data, status);
        });
    } else {
        loadFish();
    }
}

// Generate the fish table with links
function fillFishTable(fishList, status) {

    clearFishTable();

    var fTable = $('#contentRows');

    $.each(fishList, function (index, fish) {
        fTable.append($('<tr>')
//                .append($('<td>').append('<img src="' + fish.pictureURL + '" height="64px" width="64px">'))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-fish-id': fish.id,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .append($('<img>')
                                        .attr({
                                            'class': 'img-responsive',
                                            'src': fish.pictureURL
                                        })
                                        )
                                )
                        )
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-fish-id': fish.id,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(fish.name)
                                )
                        )
                .append($('<td>').text(fish.waterType))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-fish-id': fish.id,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteFish(' + fish.id + ')'
                                })
                                .text('Delete')
                                ) // ends the <a> tag
                        ) // end <td> tag
                );
    });
}


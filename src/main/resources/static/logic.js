$('#submit-button').on("click", function(){
	var option_id = $('input:radio[name=poll-option]:checked').val();
	console.log("Option-id to vote: " + option_id)
	if(option_id) {
		var poll_id = this.name;
		url = 'http://localhost:8080/polls/' + poll_id + '/' + option_id + '/inc';
		
		$.post(url, function(data) {
			var number_votes_field_id = 'number_votes_' + option_id;
			var old_number_votes = $('#' + number_votes_field_id).html();
			old_number_votes++;
			$('#' + number_votes_field_id).html(old_number_votes);
		});
	}
});

// Remove option
$(document).on("click", '.remove-option', function() {
	 console.log("Remove: " + '#' + option_to_remove_id);
	var option_to_remove = $(this).attr('id');
	var option_to_remove_id = option_to_remove.split('_')[1] + '_' + option_to_remove.split('_')[2];
	$('#' + option_to_remove_id).remove();
});

// Add option
$('.add-option').on("click", function() {
	//alert("add-option was clicked");
	var optionsNumber = $("#options-wrapper").children().length + 1;
	console.log(optionsNumber);
	var optionalTemplate = $('#option_template').clone();
	optionalTemplate.removeClass('hidden');
	optionalTemplate.prop('id', 'option_' + optionsNumber);
	
	var removeButton = optionalTemplate.find(".remove-option");
	removeButton.prop('id', 'remove_option_' + optionsNumber);
	
	var optionLabel = optionalTemplate.find(".option-label");
	optionLabel.html('Option ' + optionsNumber);

	
	$('#options-wrapper').append(optionalTemplate);
	
});

$('#submit-button-create').on('click', function() {
	var url = 'http://localhost:8080/api/polls';
	var json = getData();
	
	$.ajax({
		  type: "POST",
		  url: url,
		  data: JSON.stringify(json),
		  success: function() {
		        alert("Poll Added with success!");
		    },
		  contentType: 'application/json; charset=utf-8',
		  dataType: 'json'
		});
	
});

// Will get the data inside the form and return the json object
function getData() {
	var sendData = new Object();
	sendData.pollName = $('#poll-name-input').val();
	sendData.pollQuestion = $('#poll-question-input').val();
	sendData.pollOptions = new Array();
	
	$(".option-input").each(function(){
		var optionValue = $(this).val();
		if(optionValue)
			sendData.pollOptions.push($(this).val());
	});
	
	return sendData;
}
var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;

var numericData = [];
var labelData = [];

for (var i = 0; i < arrayLength; i++) {
	numericData[i] = chartJsonArray[i].stageCount;
	labelData[i] = chartJsonArray[i].projectStage;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"), {
	type : 'pie',
	// The data for our dataset
	data : {
		labels : labelData,
		datasets : [ {
			label : 'Project Status',
			backgroundColor : [ "#3e95cd", "#8e5ea2", "#3cba9f" ],
			data : numericData
		} ]
	},

	// Configuration options go here
	options : {
		title : {
			display : true,
			text : 'Project Status'
		}

	}
});

// "[{"value": 1, "label": "COMPLETED"},{"value": 2, "label": "INPROGRESS"},{"value": 1, "label": "NOTSTARTED"}]"
function decodeHtml(html) {
	console.log('chart data ' + html);
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}

google.charts.load('current', {
	'packages' : [ 'timeline' ]
});

var dateFormat = function (dateVal) {
	var dateSplit = dateVal.split('-');
	return new Date(dateSplit[0],dateSplit[1]-1,dateSplit[2]);
}

var getTimeLineData = function(){
	var dataText = document.getElementById('timeLineDataModel').textContent;
	return JSON.parse(dataText.trim());
}

google.charts.setOnLoadCallback(drawChart);
function drawChart() {
	var container = document.getElementById('timeline');
	var chart = new google.visualization.Timeline(container);
	var dataTable = new google.visualization.DataTable();

	dataTable.addColumn({
		type : 'string',
		id : 'Project Name'
	});
	dataTable.addColumn({
		type : 'date',
		id : 'Start'
	});
	dataTable.addColumn({
		type : 'date',
		id : 'End'
	});
	var timeLineData = getTimeLineData();
	let rows = [];
	timeLineData.forEach(data => {
		rows.push([data[0],dateFormat(data[1]),dateFormat(data[2])]);
	});

	dataTable.addRows(rows);

	chart.draw(dataTable);
}


google.charts.load('current', {'packages':['table']});
google.charts.setOnLoadCallback(drawTable);

var getProjectStatusData = function(){
	var dataText = document.getElementById('projectCompletionStatusId').textContent;
	return JSON.parse(dataText.trim());
}

function drawTable() {
  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Project');
  data.addColumn('date', 'Target Date');
  data.addColumn('string', 'Completed');
  
  var statusData = getProjectStatusData();
  let rows = [];
  statusData.forEach(data => {
		rows.push([data[0],dateFormat(data[1]),data[2]]);
  });
  
  data.addRows(rows);
  
  var table = new google.visualization.Table(document.getElementById('table_div'));

  table.draw(data, {showRowNumber: true, width: '90%', height: '100%'});
}
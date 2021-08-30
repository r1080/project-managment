
google.charts.load('current', {
	'packages' : [ 'timeline' ]
});

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

	function dateFormat(dateVal){
		var dateSplit = dateVal.split('-');
		return new Date(dateSplit[0],dateSplit[1]-1,dateSplit[2]);
	}
	
	dataTable.addRows(rows);

	chart.draw(dataTable);
}


google.charts.load('current', {'packages':['table']});
google.charts.setOnLoadCallback(drawTable);

function drawTable() {
  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Name');
  data.addColumn('number', 'Salary');
  data.addColumn('boolean', 'Full Time Employee');
  data.addRows([
	['Mike',  {v: 10000, f: '$10,000'}, true],
	['Jim',   {v:8000,   f: '$8,000'},  false],
	['Alice', {v: 12500, f: '$12,500'}, true],
	['Bob',   {v: 7000,  f: '$7,000'},  true]
  ]);

  var table = new google.visualization.Table(document.getElementById('table_div'));

  table.draw(data, {showRowNumber: true, width: '90%', height: '100%'});
}
$(document).ready(function(){
 $.ajax({
  type: "GET",
  url: "skills.xml", 
  dataType: "xml",
  success: function(xml) {	
   $(xml).find('Row').each(function(){	
    var Col0 = $(this).find('Col0').text();
    var Col1 = $(this).find('Col1').text();
    var Col2 = $(this).find('Col2').text();
    var Col3 = $(this).find('Col2').text();
    var Col4 = $(this).find('Col2').text();
    var Col5 = $(this).find('Col2').text();
    var Col6 = $(this).find('Col2').text();
    var Col7 = $(this).find('Col2').text();
    var Col8 = $(this).find('Col2').text();
    var Col9 = $(this).find('Col2').text();
    var Col10 = $(this).find('Col2').text();
    var Col11 = $(this).find('Col2').text();
    var Col12 = $(this).find('Col2').text();
    var Col13 = $(this).find('Col2').text();
    var Col14 = $(this).find('Col2').text();
    var Col15 = $(this).find('Col2').text();
    var Col16 = $(this).find('Col2').text();
    var Col17 = $(this).find('Col2').text();
    var Col18 = $(this).find('Col2').text();
    var Col19 = $(this).find('Col2').text();
    var Col20 = $(this).find('Col2').text();
    var Col21 = $(this).find('Col2').text();
    var Col22 = $(this).find('Col2').text();
    var Col23 = $(this).find('Col2').text();
    var Col24 = $(this).find('Col2').text();
    var Col25 = $(this).find('Col2').text();
    var Col26 = $(this).find('Col2').text();
    var Col27 = $(this).find('Col2').text();
    var Col28 = $(this).find('Col2').text();
    var Col29 = $(this).find('Col2').text();
    var Col30 = $(this).find('Col2').text();

    //could probably find a way to encapsulate this in a loop
    $('<tr></tr>').html('<th>'+Col0+'</th><td>$'+Col1+'</td><td>$'+Col2+'</td>'+Col3+'</td>'+Col4+'</td>'+Col5+'</td>'+Col6+'</td>'+Col7+'</td>'+Col8+'</td>'+Col9+'</td>'+Col10+'</td>'+Col11+'</td>'+Col12+'</td>'+Col13+'</td>'+Col14+'</td>'+Col15+'</td>'+Col16+'</td>'+Col17+'</td>'+Col18+'</td>'+Col19+'</td>'+Col20+'</td>'+Col21+'</td>'+Col22+'</td>'+Col23+'</td>'+Col24+'</td>'+Col25+'</td>'+Col26+'</td>'+Col27+'</td>'+Col28+'</td>'+Col29+'</td>'+Col30+'</td>').appendTo('#chart');
   });
  }
 });
});

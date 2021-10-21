google.charts.load('current', {packages: ['corechart', 'bar']});

google.charts.setOnLoadCallback(callTotalProductSalesAPI);

function callTotalProductSalesAPI() {
    $.ajax({
        url: "TotalProductSalesAPI",
        type: "POST",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (msg) {
            formatJsonAccordingToGoogleChart(msg);
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });
}

function formatJsonAccordingToGoogleChart(msg)
{
    //zipcodeArray => productNameArr
    //productNameArr => productTotalSalesArr

    //alert(msg);
    //[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object]
    var newMsg = JSON.stringify(msg);
    //alert(newMsg);
    //[{"productName":"Basic Plan","productPrice":"22.99","noOfProductsSold":"3","productTotalSales":"68.97"},{"productName":"LG FitnessWatch","productPrice":"399.99","noOfProductsSold":"2","productTotalSales":"799.98"},{"productName":"LG Headphone","productPrice":"20.99","noOfProductsSold":"2","productTotalSales":"41.98"},{"productName":"LG Laptop","productPrice":"689.99","noOfProductsSold":"2","productTotalSales":"1379.98"},{"productName":"LG Phone","productPrice":"389.99","noOfProductsSold":"2","productTotalSales":"779.98"},{"productName":"LG SmartWatch","productPrice":"289.99","noOfProductsSold":"2","productTotalSales":"579.98"},{"productName":"LG SoundSystem","productPrice":"72.99","noOfProductsSold":"2","productTotalSales":"145.98"},{"productName":"LG Television","productPrice":"87.99","noOfProductsSold":"2","productTotalSales":"175.98"},{"productName":"LG VoiceAssistant","productPrice":"199.99","noOfProductsSold":"2","productTotalSales":"399.98"},{"productName":"Microsoft FitnessWatch","productPrice":"40.99","noOfProductsSold":"2","productTotalSales":"81.98"},{"productName":"Microsoft Headphone","productPrice":"149.99","noOfProductsSold":"2","productTotalSales":"299.98"},{"productName":"Microsoft Laptop","productPrice":"849.99","noOfProductsSold":"2","productTotalSales":"1699.98"},{"productName":"Microsoft Phone","productPrice":"589.99","noOfProductsSold":"2","productTotalSales":"1179.98"},{"productName":"Microsoft SmartWatch","productPrice":"389.99","noOfProductsSold":"2","productTotalSales":"779.98"},{"productName":"Microsoft SoundSystem","productPrice":"50.99","noOfProductsSold":"2","productTotalSales":"101.98"},{"productName":"Microsoft Television","productPrice":"150.49","noOfProductsSold":"3","productTotalSales":"451.47"},{"productName":"Microsoft VoiceAssistant","productPrice":"150.99","noOfProductsSold":"2","productTotalSales":"301.98"},{"productName":"Onida FitnessWatch","productPrice":"289.99","noOfProductsSold":"2","productTotalSales":"579.98"},{"productName":"Onida Headphone","productPrice":"89.99","noOfProductsSold":"2","productTotalSales":"179.98"},{"productName":"Onida Laptop","productPrice":"489.99","noOfProductsSold":"2","productTotalSales":"979.98"},{"productName":"Onida Phone","productPrice":"300.99","noOfProductsSold":"2","productTotalSales":"601.98"},{"productName":"Onida SmartWatch","productPrice":"149.99","noOfProductsSold":"2","productTotalSales":"299.98"},{"productName":"Onida SoundSystem","productPrice":"59.99","noOfProductsSold":"2","productTotalSales":"119.98"},{"productName":"Onida Television","productPrice":"75.99","noOfProductsSold":"2","productTotalSales":"151.98"},{"productName":"Onida VoiceAssistant","productPrice":"149.99","noOfProductsSold":"2","productTotalSales":"299.98"},{"productName":"Premium Plan","productPrice":"32.99","noOfProductsSold":"2","productTotalSales":"65.98"},{"productName":"Samsung FitnessWatch","productPrice":"499.99","noOfProductsSold":"2","productTotalSales":"999.98"},{"productName":"Samsung Headphone ","productPrice":"89.99","noOfProductsSold":"2","productTotalSales":"179.98"},{"productName":"Samsung Laptop","productPrice":"520.99","noOfProductsSold":"2","productTotalSales":"1041.98"},{"productName":"Samsung Phone","productPrice":"489.99","noOfProductsSold":"2","productTotalSales":"979.98"},{"productName":"Samsung SmartWatch","productPrice":"280.99","noOfProductsSold":"2","productTotalSales":"561.98"},{"productName":"Samsung SoundSystem","productPrice":"200.99","noOfProductsSold":"2","productTotalSales":"401.98"},{"productName":"Samsung Television","productPrice":"99.99","noOfProductsSold":"2","productTotalSales":"199.98"},{"productName":"Samsung VoiceAssistant","productPrice":"50.99","noOfProductsSold":"2","productTotalSales":"101.98"},{"productName":"Sony FitnessWatch","productPrice":"289.99","noOfProductsSold":"2","productTotalSales":"579.98"},{"productName":"Sony Headphone","productPrice":"189.99","noOfProductsSold":"2","productTotalSales":"379.98"},{"productName":"Sony Laptop","productPrice":"549.99","noOfProductsSold":"2","productTotalSales":"1099.98"},{"productName":"Sony Phone","productPrice":"189.99","noOfProductsSold":"2","productTotalSales":"379.98"},{"productName":"Sony SmartWatch","productPrice":"389.99","noOfProductsSold":"2","productTotalSales":"779.98"},{"productName":"Sony SoundSystem","productPrice":"112.99","noOfProductsSold":"2","productTotalSales":"225.98"},{"productName":"Sony Television","productPrice":"119.99","noOfProductsSold":"2","productTotalSales":"239.98"},{"productName":"Sony VoiceAssistant","productPrice":"149.99","noOfProductsSold":"2","productTotalSales":"299.98"},{"productName":"Ultimate Plan","productPrice":"42.99","noOfProductsSold":"2","productTotalSales":"85.98"}]
    var parsedData = $.parseJSON(newMsg);
    //alert(parsedData);
    //[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object]
    var data = new Array();
    var productNameArr = new Array();
    var productTotalSalesArr = new Array();

    productNameArr.push("Product Name");
    productNameArr.push("Product Total Sales");

    var newArray = [];
    newArray.push(productNameArr);


    //Create an array of product name and an array of zipcodes
    for(var i=0; i<parsedData.length; i++)
    {
        var productName = parsedData[i]["productName"];
        var productTotalSales = Number.parseInt(parsedData[i]["productTotalSales"]);

        var array = [].concat([productName, productTotalSales]);
        newArray.push(array);
    }
    console.log(newArray)
    drawChart(newArray, productNameArr);
}

//Plot the chart using 2d array and product names as subtitles;
function drawChart(data, productNameArr)
{
    //alert(typeof(data));
    /*var productNames = "";
    for(var i=0; i<productNameArr.length; i++)
    {
        productNames += productNameArr[i] + ",";
    }*/

    //Invoke google's built in method to get data table object required by google.
    var chartData = google.visualization.arrayToDataTable(data);
    /*
    [
    ['Product Name', 'Number of available products'],
    ['2014', 10],
    ['2015', 12],
    ['2016', 7],
    ['2017', 15]
    ]
    */

    var options = {
        // 'width':900,
        'height':650,
        chart: {
            title: 'Total Product Sales',
            //subtitle: productNames,
        },
        bars: 'horizontal' // Required for Material Bar Charts.
    };

    var chart = new google.visualization.BarChart(document.getElementById('chartDivTotalProductSales')); //chartDivNumberOfAvailableProducts chart_div
    chart.draw(chartData, options);
}
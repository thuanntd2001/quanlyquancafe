
function checkTotalDay() {

    var year = $('input[name=year]').val();
    var month = $('select[name=month]').val();
    var totalDate = 31;
    if (year !== '' && month !== '') {
        totalDate = new Date(year, month, 0).getDate();
    }

    $('select[name=day]').empty();
    for (var i = 1; i <= totalDate; i++) {
        $('select[name=day]').append("<option value='" + i + "'>" + i + "</option>");
    }
}



//gio tu dong
function Dong_ho() {
    var date_now = document.getElementById("date-now");
    var current_time = document.getElementById("current-time");

    var today = new Date();

    date_now.innerHTML = today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getFullYear();
    current_time.innerHTML = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
}
var Dem_gio = setInterval(Dong_ho, 1000);

//select-menu
var sel1 = document.querySelector('#sel1');
var sel2 = document.querySelector('#sel2');
var options2 = sel2.querySelectorAll('option');
function giveSelection(selValue) {
	
	
    sel2.innerHTML = '';
    for (var i = 0; i < options2.length; i++) {
    	/*alert(options2[i].dataset.option);
    	alert(selValue);*/
        if (options2[i].dataset.option === selValue) {
            sel2.appendChild(options2[i]);
            
        }
    }
}
giveSelection(sel1.value);

//select-icon
var selector = '.table-icon .icon-coffee';
$(selector).on('click', function () {
    $(selector).removeClass('active-icon');
    $(this).addClass('active-icon');
});

// edit button

function EditNumber(myid) {
    let name2 = 'inp-' + myid.substring(4);
    // alert(name2);

    if ($(`#` + name2).attr('disabled')) {
        $(`#` + name2).removeAttr('disabled');
        $('#' + myid).text('LƯU').addClass('btn-success');
    } else {
        $(`#` + name2).attr('disabled', 2);
        $('#' + myid).text('SỬA').removeClass('btn-success');
    }
};

